package com.xbcxs.common.eventclient.event.jar;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ApprovalEventRegister {

    private static Logger log = LoggerFactory.getLogger(ApprovalEventRegister.class);

    /**
     * Application生命周期只执行一遍
     */
    private static boolean IS_EXECUTED = false;

    /**
     * 反射工具扫描路径
     */
    private static String SCAN_PATH = "com.xbcxs";

    /**
     * 审批模型对应事件实现类的对应关系表
     */
    public static Map<String, Class> approvalEventModelClassMap = new HashMap();

    /**
     *  TODO 改为启动初始化
     */
    static {
        init();
    }

    /**
     * 初始化
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void init() {
        Reflections reflections = new Reflections(SCAN_PATH);
        ApprovalEventConfig config = getConfigServer(reflections);
        JSONArray eventRegisterInfo = getEventRegisterInfo(config, reflections);
        executeRegister(config, eventRegisterInfo);
    }

    /**
     *  获取配置信息
     * @param reflections
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private static ApprovalEventConfig getConfigServer(Reflections reflections) {
        ApprovalEventConfig approvalEventConfig = null;
        Set<Class<? extends ApprovalEventConfig>> subConfigServer = reflections.getSubTypesOf(ApprovalEventConfig.class);
        Class<ApprovalEventConfig> configServerClass = ApprovalEventConfig.class;
        if (!subConfigServer.isEmpty()) {
            configServerClass = (Class<ApprovalEventConfig>) subConfigServer.iterator().next();
        }
        try {
            approvalEventConfig = configServerClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return approvalEventConfig;
    }

    /**
     * 获取事件列表
     * @return
     */
    private static ArrayList getEventList() {
        ArrayList eventList;
        eventList = new ArrayList<String>();
        Class approvalEventClass = ApprovalEventListener.class;
        Method[] approvalEventMethods = approvalEventClass.getDeclaredMethods();
        for (Method m : approvalEventMethods) {
            if (m.getAnnotation(ApprovalEventAnnotation.class) != null) {
                eventList.add(m.getName());
            }
        }
        return eventList;
    }

    /**
     * 构建注册信息
     *
     * @param config
     * @param reflections
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private static JSONArray getEventRegisterInfo(ApprovalEventConfig config, Reflections reflections) {
        String approvalModel;
        JSONObject eventJson;
        JSONArray eventApiArray = new JSONArray();
        Set<Class<? extends ApprovalEventListener>> subTypes = reflections.getSubTypesOf(ApprovalEventListener.class);
        ArrayList eventList = getEventList();
        try {
            for (Class cls : subTypes) {
                ApprovalEventListener approvalEvent = (ApprovalEventListener) cls.newInstance();
                approvalModel = approvalEvent.bindApprovalModel();
                approvalEventModelClassMap.put(approvalModel, cls);
                Method[] methodMethod = cls.getDeclaredMethods();
                for (Method method : methodMethod) {
                    if (eventList.contains(method.getName())) {
                        eventJson = new JSONObject();
                        eventJson.put("receiveAddress", config.getReceiveAddress());
                        eventJson.put("api", ApprovalHttpClient.APPROVAL_HTTP_URI + "/" + approvalModel + "/" + method.getName());
                        eventJson.put("approvalModel", approvalModel);
                        eventJson.put("approvalEvent", method.getName());
                        eventApiArray.add(eventJson);
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return eventApiArray;
    }

    /**
     * 向服务中心注册
     * @param config
     * @param eventRegisterInfo
     */
    private static void executeRegister(ApprovalEventConfig config, JSONArray eventRegisterInfo) {
        // 循环调度发送，保证推送消息达到。
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleWithFixedDelay(() -> {
            if (!IS_EXECUTED) {
                CloseableHttpClient httpClient = null;
                CloseableHttpResponse response = null;
                try {
                    httpClient = HttpClientBuilder.create().build();
                    HttpPost httpPost = new HttpPost(config.getApprovalServerAddress());
                    httpPost.setEntity(new StringEntity(eventRegisterInfo.toString()));
                    httpPost.setHeader("Content-Type", "application/json;charset=utf8");
                    response = httpClient.execute(httpPost);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        IS_EXECUTED = true;
                        executor.shutdown();
                    }
                } catch (UnsupportedEncodingException e) {
                    log.warn("无法连接的审核流程事件注册中心！", e);
                } catch (ClientProtocolException e) {
                    log.warn("无法连接的审核流程事件注册中心！", e);
                } catch (IOException e) {
                    log.warn("无法连接的审核流程事件注册中心！", e);
                } finally {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 60 * 1000, TimeUnit.MILLISECONDS);
    }

}

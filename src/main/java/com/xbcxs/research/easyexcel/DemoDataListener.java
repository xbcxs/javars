package com.xbcxs.research.easyexcel;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class DemoDataListener extends AnalysisEventListener<DemoData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);

    private static int autoDeptNum = 0;

    public static HashMap<String, Dept> deptMap = new HashMap<>();

    public static HashMap<String, User> userMap = new HashMap<>();

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        // LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        String [] depts = data.getDeptNamePath().trim().substring(1).split("/");
        // 解析人员数据
        generateUser(data, depts);
        // 解析部门数据
        generateDept(depts);
    }

    private void generateUser(DemoData data, String[] depts) {
        User user = new User();
        user.setName(data.getUsername());
        user.setLoginName(data.getEmailAddress().trim().split("@")[0]);
        user.setMainDept(depts[depts.length - 1]);
        user.setMainDeptDeep(depts.length);
        user.setSecurity("内部");
        user.setSex("男");

        // 用户数据重复，关联层级最高部门为主部门
        if(!userMap.containsKey(user.getLoginName())){
            userMap.put(user.getLoginName(), user);
        } else {
            User mapUser = userMap.get(user.getLoginName());
            if(mapUser.getMainDeptDeep() > user.getMainDeptDeep()){
                mapUser.setMainDept(user.getMainDept());
                mapUser.setMainDeptDeep(user.getMainDeptDeep());
            }
        }

    }

    private void generateDept(String[] depts) {
        String deptName = null;
        for (int i = 0; i < depts.length; i++){
            deptName = depts[i];
            // LOGGER.info("deptName:{}", deptName);
            Dept dept = new Dept();
            // 去重
            if(deptMap.containsKey(deptName)){
                continue;
            }
            dept.setName(deptName);
            dept.setCode(String.valueOf(++autoDeptNum));
            if(deptMap.isEmpty()){
                dept.setParentCode("0");
            } else {
                dept.setParentCode(deptMap.get(depts[i-1]).getCode());
            }
            deptMap.put(dept.getName(), dept);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        LOGGER.info(JSON.toJSONString(userMap));
        LOGGER.info(JSON.toJSONString(deptMap));
        LOGGER.info("所有数据解析完成！");
    }
}


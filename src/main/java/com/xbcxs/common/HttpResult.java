package com.xbcxs.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * HTTP/JSON数据封装
 * @author xiaosh
 * @date 2019/9/18
 */
public class HttpResult<T>  {

    private Integer code;
    private String message;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private HttpResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> String success(String message, T data) {
        return new HttpResult(1, message, data).toJSONString();
    }

    public static <T> String success(T data) {
        return new HttpResult(1, "success!", data).toJSONString();
    }

    public static String error(String message) {
        return new HttpResult(0, message, null).toJSONString();
    }

    public static String error(Integer code, String message) {
        return new HttpResult(code, message, null).toJSONString();
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty);
    }

}

package com.xbcxs.common.exception.base;

import com.xbcxs.common.i18n.InternationalizationConfig;

/**
 * @author xiaosh
 * @date 2019/9/18
 */
public class CheckedException extends Exception{

    private Integer code = 0;

    public Integer getCode() {
        return code;
    }

    public CheckedException(String message) {
        super(InternationalizationConfig.getString(message));
    }

    public CheckedException(Integer code, String message) {
        super(InternationalizationConfig.getString(message));
        this.code = code;
    }

    public CheckedException(String message, Object[] params) {
        super(InternationalizationConfig.getFormatString(message, params));
    }

    public CheckedException(Integer code, String message, Object[] params) {
        super(InternationalizationConfig.getFormatString(message, params));
        this.code = code;
    }
}

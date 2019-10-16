package com.xbcxs.common.exception.base;

import com.xbcxs.common.i18n.InternationalizationConfig;

/**
 * @author xiaosh
 * @date 2019/9/18
 */
public class UncheckedException extends RuntimeException{

    private Integer code = 0;

    public Integer getCode() {
        return code;
    }

    public UncheckedException(String message) {
        super(InternationalizationConfig.getString(message));
    }

    public UncheckedException(Integer code, String message) {
        super(InternationalizationConfig.getString(message));
        this.code = code;
    }

    public UncheckedException(String message, Object[] params) {
        super(InternationalizationConfig.getFormatString(message, params));
    }

    public UncheckedException(Integer code, String message, Object[] params) {
        super(InternationalizationConfig.getFormatString(message, params));
        this.code = code;
    }
}

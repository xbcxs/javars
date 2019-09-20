package com.xbcxs.common.exception;

import com.xbcxs.common.exception.base.UncheckedException;

/**
 * 参数无效异常
 * @author xiaosh
 * @date 2019/9/20
 */
public class ParameterInvalidException extends UncheckedException {

    public ParameterInvalidException(String message) {
        super(1101, message);
    }

    public ParameterInvalidException(Integer code, String message) {
        super(code, message);
    }
}

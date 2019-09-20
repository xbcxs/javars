package com.xbcxs.common.exception;

import com.xbcxs.common.exception.base.UncheckedException;

/**
 * 内部服务异常
 * @author xiaosh
 * @date 2019/9/20
 */
public class InternalServerException extends UncheckedException {

    public InternalServerException(String message) {
        super(1103, message);
    }

    public InternalServerException(Integer code, String message) {
        super(code, message);
    }
}

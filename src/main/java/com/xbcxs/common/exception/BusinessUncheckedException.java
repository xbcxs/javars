package com.xbcxs.common.exception;

import com.xbcxs.common.exception.base.UncheckedException;

/**
 * @author xiaosh
 * @date 2019/9/18
 */
public class BusinessUncheckedException extends UncheckedException {

    public BusinessUncheckedException(String message) {
        super(message);
    }

    public BusinessUncheckedException(Integer code, String message) {
        super(code, message);
    }
}

package com.xbcxs.common.exception;

import com.xbcxs.common.exception.base.CheckedException;

/**
 * @author xiaosh
 * @date 2019/9/18
 */
public class BusinessCheckedException extends CheckedException {

    public BusinessCheckedException(String message) {
        super(message);
    }

    public BusinessCheckedException(Integer code, String message) {
        super(code, message);
    }

}

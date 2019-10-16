package com.xbcxs.common.exception;

import com.xbcxs.common.exception.base.UncheckedException;

/**
 * 权限不足异常
 * @author xiaosh
 * @date 2019/9/20
 */
public class PermissionForbiddenException extends UncheckedException {
    public PermissionForbiddenException(String message) {
        super(message);
    }

    public PermissionForbiddenException(Integer code, String message) {
        super(code, message);
    }
}

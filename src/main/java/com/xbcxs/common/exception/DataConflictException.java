package com.xbcxs.common.exception;

import com.xbcxs.common.exception.base.UncheckedException;

/**
 * 数据冲突异常
 * @author xiaosh
 * @date 2019/9/20
 */
public class DataConflictException extends UncheckedException {
    public DataConflictException(String message) {
        super(1105, message);
    }

    public DataConflictException(Integer code, String message) {
        super(code, message);
    }
}

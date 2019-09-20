package com.xbcxs.common.exception;

import com.xbcxs.common.exception.base.UncheckedException;

/**
 * 数据未找到异常
 * @author xiaosh
 * @date 2019/9/20
 */
public class DataNotFoundException extends UncheckedException {

    public DataNotFoundException(String message) {
        super(1104, message);
    }

    public DataNotFoundException(Integer code, String message) {
        super(code, message);
    }
}

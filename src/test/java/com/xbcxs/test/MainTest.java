package com.xbcxs.test;

import com.xbcxs.common.extend.ExtendFactory;
import com.xbcxs.common.extend.test.AbcExtend;

/**
 * @author xiaosh
 * @date 2019/9/18
 */
public class MainTest {

    public static void main(String[] args) throws Exception {
        AbcExtend abcExtend = (AbcExtend) ExtendFactory.getExtendInstance(AbcExtend.class);
        abcExtend.method1();
    }
}

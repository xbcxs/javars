package com.xbcxs.common.extend.test;

import com.xbcxs.common.extend.ExtendFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaosh
 * @date 2019/10/15
 */
public class ExtendTest {

    private static Logger log = LoggerFactory.getLogger(ExtendTest.class);

    public static void main(String[] args) {
        AbcExtend abcExtend = (AbcExtend) ExtendFactory.getExtendInstance(AbcExtend.class);
        abcExtend.method1();
    }
}

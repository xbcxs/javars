package com.xbcxs.common.extend.test;

import com.xbcxs.common.extend.ExtendFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaosh
 * @date 2019/10/15
 */
public class ExtenderTest {

    private static Logger log = LoggerFactory.getLogger(ExtenderTest.class);

    public static void main(String[] args) {
        AbcExtender abcExtender = (AbcExtender) ExtendFactory.getExtendInstance(AbcExtender.class);
        abcExtender.method1();
    }
}

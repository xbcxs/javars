package com.xbcxs.test;

import com.xbcxs.extend.ExtendFactory;
import com.xbcxs.service.AbcExtend;

/**
 * @author xiaosh
 * @date 2019/9/18
 */
public class MainTest {

    public static void main(String[] args) throws Exception {
        AbcExtend abcExtend = (AbcExtend) ExtendFactory.getExtendInstance(AbcExtend.class);
        abcExtend.event1();
    }
}

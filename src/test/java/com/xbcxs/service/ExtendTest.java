package com.xbcxs.service;

import com.xbcxs.extend.ExtendFactory;
import org.junit.Test;

/**
 * @author xiaosh
 * @date 2019/10/15
 */
public class ExtendTest {

    @Test
    public void test(){
        AbcExtend abcExtend = (AbcExtend) ExtendFactory.getExtendInstance(AbcExtend.class);
        abcExtend.event1();
    }
}

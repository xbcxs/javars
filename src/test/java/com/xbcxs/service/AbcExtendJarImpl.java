package com.xbcxs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaosh
 * @date 2019/10/14
 */
public class AbcExtendJarImpl implements AbcExtend {

    private static Logger log = LoggerFactory.getLogger(AbcExtend.class);

    @Override
    public Object event1() {
        log.debug("come AbcExtendJarImpl...");
        return null;
    }

}

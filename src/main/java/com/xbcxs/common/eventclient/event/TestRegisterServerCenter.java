package com.xbcxs.common.eventclient.event;

import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testRegisterServerCenter")
public class TestRegisterServerCenter {

    private static final Logger log = LoggerFactory.getLogger(TestRegisterServerCenter.class);

    @RequestMapping("register")
    public void register(@RequestBody JSONArray api) {

        log.debug("TestRegisterServerCenter.register...api:{}", api);
    }

}

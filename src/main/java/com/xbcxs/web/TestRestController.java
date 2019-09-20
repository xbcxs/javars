package com.xbcxs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaosh
 * @date 2019/9/20
 */
@RestController
@RequestMapping("testRest")
public class TestRestController {

    private Logger log = LoggerFactory.getLogger(TestRestController.class);

    @RequestMapping("page")
    public String page(){
        log.debug("testRest/page...");
        return "staticTest";
    }
}

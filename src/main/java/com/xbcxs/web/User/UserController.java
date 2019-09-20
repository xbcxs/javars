package com.xbcxs.web.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiaosh
 * @date 2019/9/18
 */
@Controller
@RequestMapping("user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("getUser")
    public String getUser(){

        return "index.html";
    }

}

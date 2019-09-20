package com.xbcxs.web.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaosh
 * @date 2019/9/18
 */
@RestController
@RequestMapping("userRest")
public class UserRestController {

    @RequestMapping("getUser")
    public UserVO getUser(){

        return new UserVO();
    }

}

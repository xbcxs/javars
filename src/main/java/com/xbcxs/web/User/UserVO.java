package com.xbcxs.web.User;

import java.io.Serializable;

/**
 * @author xiaosh
 * @date 2019/9/18
 */
public class UserVO implements Serializable{

    private String id;
    private String name;
    private String address;

    public UserVO(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

package com.xbcxs.web;

import com.xbcxs.common.HttpResult;
import com.xbcxs.common.ResponseWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author xiaosh
 * @date 2019/9/20
 */
@Controller
@RequestMapping("test")
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("page")
    public String page(){
        log.debug("test/page...");
        return "test";
    }

    @RequestMapping("page2")
    public void page2(HttpServletResponse response){
        log.debug("test/page2...");
        ResponseWriter.writer(response, HttpResult.success("成功"));
    }

    @RequestMapping("page3")
    @ResponseBody
    public String page3(@RequestBody Map map){

        return HttpResult.success("成功3");
    }
}

package com.example.demo.html;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/8/8
 */
//https://blog.csdn.net/qq_39301708/article/details/101021446
@RestController
@RequestMapping("html")
public class ControllerHtml {

// http://localhost:8080/static/demo/html/HtmlDemo3.html
    @RequestMapping(value = "test",method = RequestMethod.POST)
    @ResponseBody
    public String htmlTest(UserRegister userRegister){
        JSONObject.toJSONString(userRegister);

        return "Success";
    }

    //待确认
//    @GetMapping(value = "getHtml")
//    public ModelAndView getHtml(){
//        System.out.println("进入页面");
//        ModelAndView modelAndView = new ModelAndView("localhost:8082/static/demo/html/HtmlDemo3.html");
//        return modelAndView;
//    }
}

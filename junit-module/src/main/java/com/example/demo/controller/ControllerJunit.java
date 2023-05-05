package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.base.request.UpdateMemberRequest;
import com.example.demo.base.response.UpdateMemberResponse;
import com.example.demo.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import com.example.demo.result.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/11
 */
@RestController
@RequestMapping("controllerJunit")
@Slf4j
public class ControllerJunit {

    //RequestMapping 的 method 如果不设置任何值 则所有请求类型的请求都可以调用
    @RequestMapping(value = "test1")
    @ResponseBody
    public String controllerTest1(){
        return "controllerTest1";
    }

    /**
     * 测试请求 HttpServletRequest
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "test2")
    @ResponseBody
    public String controllerTest2(HttpServletRequest httpServletRequest){
        return "controllerTest2";
    }


    /**
     * 设置响应
     * @param
     * @return
     */
    @RequestMapping(value = "test3")
    @ResponseBody
    public Result controllerTest3(){
        UpdateMemberResponse updateMemberResponse = new UpdateMemberResponse();
        updateMemberResponse.setId(1L);
        updateMemberResponse.setName("HeSuiJin");
        updateMemberResponse.setAge("18");

        Result result = new Result();
        result.setMsg("Success");
        result.setData(updateMemberResponse);
        result.setStatus(ResponseStatus.OK);
        return result;
    }

    /**
     * 模拟JSON请求
     * @param updateMemberRequest
     * @return
     */
    @RequestMapping(value = "test4",method = RequestMethod.POST)
    @ResponseBody
    public UpdateMemberResponse controllerTest4(@RequestBody UpdateMemberRequest updateMemberRequest){
        log.info("我是@RequestBody  JSON格式 的入参啊：{}", JSONObject.toJSONString(updateMemberRequest.toString()));
        UpdateMemberResponse updateMemberResponse = new UpdateMemberResponse();
        updateMemberResponse.setId(1L);
        updateMemberResponse.setName("HeSuiJin");
        updateMemberResponse.setAge(updateMemberRequest.getAge()+10);
        return updateMemberResponse;
    }

    /**
     * 模拟表单请求
     * @param updateMemberRequest
     * @return
     */
    @RequestMapping(value = "test5",method = RequestMethod.POST)
    @ResponseBody
    public UpdateMemberResponse controllerTest5(UpdateMemberRequest updateMemberRequest){
        log.info("我是  表单格式 的入参啊：{}", JSONObject.toJSONString(updateMemberRequest.toString()));
        UpdateMemberResponse updateMemberResponse = new UpdateMemberResponse();
        updateMemberResponse.setId(1L);
        updateMemberResponse.setName("HeSuiJin");
        updateMemberResponse.setAge(updateMemberRequest.getAge()+10);
        return updateMemberResponse;
    }

}

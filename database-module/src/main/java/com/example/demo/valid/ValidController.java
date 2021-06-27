package com.example.demo.valid;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.valid.base.ValidRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//表单中什么是空（null）： 左边没有key
//表单中什么是空值（如空字符串）：右边没有value

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/6/27
 */
@RestController
@RequestMapping("validController")
@Slf4j
public class ValidController {

    @GetMapping("validGetTest")
    public String validGetTest(@Valid ValidRequest validRequest) {
        log.info("validGetTest 入参：{}", JSONObject.toJSONString(validRequest));
        return "validGetTest success";
    }

    @PostMapping("validPostTest")
    public String validPostTest(@Valid ValidRequest validRequest) {
        log.info("validPostTest 入参：{}", JSONObject.toJSONString(validRequest));
        return "validPostTest success";
    }

    @GetMapping("validGetRequestBodyTest")
    public String validGetRequestBodyTest(@Valid @RequestBody ValidRequest validRequest) {
        log.info("validGetRequestBodyTest 入参：{}", JSONObject.toJSONString(validRequest));
        return "validGetRequestBodyTest success";
    }

}

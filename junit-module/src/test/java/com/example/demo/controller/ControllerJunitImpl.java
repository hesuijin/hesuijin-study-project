package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.base.request.UpdateMemberRequest;
import com.example.demo.base.response.UpdateMemberResponse;
import com.example.demo.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONString;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Description:
 *
 * http://tengj.top/categories/Spring-Boot%E5%B9%B2%E8%B4%A7%E7%B3%BB%E5%88%97/
 * https://zhuanlan.zhihu.com/p/98074553
 * https://juejin.cn/post/6844904179731791879
 *
 * @Author HeSuiJin
 * @Date 2021/7/11
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ControllerJunitImpl {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setupMockMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();
        //假如有拦截器 那么 有可能会判断用户是否登录，因此有可能需要注入一个用户
//        User user =new User("root","root");
//        session.setAttribute("user",user);
    }


    /**
     * 进行最简单的请求 不做任何判断
     *
     * @throws Exception
     */
    @Test
    public void publicMethodTest1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/controllerJunit/test1")
//                注意：有可能随着版本的变化，默认值发生改变，因此建议指定.
//                请求格式 默认JSON
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) //表单 FORM
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)   //JSON
//                返回格式  默认JSON
//                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }



    /**
     * 进行最简单的请求  不做任何判断  并获取返回值
     * (注意MockMvcRequestBuilders 自带HttpServletRequest请求)
     *
     * @throws Exception
     */
    @Test
    public void publicMethodTest2() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.post("/controllerJunit/test2")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        log.info("打印返回结果：{}", result);
    }

    /**
     * 模拟请求 进行断言判断
     *
     * @throws Exception
     */
    @Test
    public void publicMethodTest3() throws Exception {
        String resultString = mvc.perform(MockMvcRequestBuilders.get("/controllerJunit/test3")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("10001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("Success"))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("10001"))
                .andExpect(MockMvcResultMatchers.jsonPath("msg").value("Success"))

                //data中具有 id  name  age 的字段
                .andExpect(MockMvcResultMatchers.jsonPath("data.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.name").value("HeSuiJin"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.age").value("18"))
                .andReturn().getResponse().getContentAsString();

        Result result = JSONObject.parseObject(resultString, Result.class);
        log.info("模拟请求数据为JSON格式  打印返回结果：{}", JSONObject.toJSONString(result));
    }

    /**
     * 模拟请求数据为JSON格式
     *
     * @throws Exception
     */
    @Test
    public void publicMethodTest4() throws Exception {
        UpdateMemberRequest updateMemberRequest = new UpdateMemberRequest();
        updateMemberRequest.setId(0L);
        updateMemberRequest.setName("River");
        updateMemberRequest.setAge("18");
        String requestString = JSONObject.toJSONString(updateMemberRequest);

        String result = mvc.perform(MockMvcRequestBuilders.post("/controllerJunit/test4")
                //设置请求数据类型为 JSON
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //设置返回格式为JSON
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
                .content(requestString)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        UpdateMemberResponse updateMemberResponse = JSONObject.parseObject(result, UpdateMemberResponse.class);
        log.info("模拟请求数据为JSON格式  打印返回结果：{}", JSONObject.toJSONString(updateMemberResponse));
    }


    /**
     * 模拟请求数据为表单格式
     *
     * @throws Exception
     */
    @Test
    public void publicMethodTest5() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.post("/controllerJunit/test5")
                //设置请求数据类型为 FROM 表单
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                //设置返回格式为JSON
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)

                .param("id", "0")
                .param("name", "HSJ")
                .param("age", "100")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        UpdateMemberResponse updateMemberResponse = JSONObject.parseObject(result, UpdateMemberResponse.class);
        log.info("模拟请求数据为表单格式 打印返回结果：{}", JSONObject.toJSONString(updateMemberResponse));
    }

    //每个@Test执行完后会重新执行
    @After
    public void endTest() {
        log.info("单元测试结束");
    }
}

package com.example.demo.privateMethod;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.base.request.UpdateMemberRequest;
import com.example.demo.base.response.UpdateMemberResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/11
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PrivateMethodClassImplTest {

    @Autowired
    private PrivateMethodClass privateMethodClass;

    /**
     * 测试公共方法
     */
    @Test
    public void publicMethodTest() {
        UpdateMemberRequest updateMemberRequest = new UpdateMemberRequest();
        updateMemberRequest.setId(0L);
        updateMemberRequest.setName("HSJ");
        updateMemberRequest.setAge("18");
        UpdateMemberResponse updateMemberResponse = privateMethodClass.publicMethodUpdateMember(updateMemberRequest);
        log.info("publicMethodTest返回：{}", JSONObject.toJSONString(updateMemberResponse));
    }


    /**
     * 测试私有方法  使用反射机制获取
     */
    @Test
    public void privateMethodReflectTest() throws Exception {
        //1:获取目标类
        //2:获取目标类的实例对象
        Class<PrivateMethodClass> clazz = PrivateMethodClass.class;
        Object privateMethodClassObject = clazz.newInstance();

        UpdateMemberRequest updateMemberRequest = new UpdateMemberRequest();
        updateMemberRequest.setId(0L);
        updateMemberRequest.setName("HSJ");
        updateMemberRequest.setAge("18");

        //3：获取方法
        Method publicMethodUpdateMember = clazz.getMethod("publicMethodUpdateMember", UpdateMemberRequest.class);
        //最后：调用方法  (UpdateMemberResponse)privateMethodVoid.invoke(privateMethodClassObject, updateMemberRequest);
//                1：privateMethodVoid方法使用invoke（）
//                2：privateMethodClassObject 目标类的对象
//                3：updateMemberRequest 方法参数
//                4：返回结果为Object需要转移
        UpdateMemberResponse updateMemberResponse = (UpdateMemberResponse) publicMethodUpdateMember.invoke(privateMethodClassObject, updateMemberRequest);
        log.info("updateMemberResponse返回：{}", JSONObject.toJSONString(updateMemberResponse));


        //注意1：getMethod仅能获取公共方法   getDeclaredMethod才能获取所有方法   否则会出现异常java.lang.NoSuchMethodException
//        Method privateMethodVoid = clazz.getMethod("privateMethodVoid");
        //注意2：私有方法需要使用 setAccessible(true)(暴力破解 忽略权限修饰符) 才能被invoke调用 否则会出现异常PrivateMethodClass with modifiers "private"
//        privateMethodVoid.setAccessible();

        Method privateMethodVoid = clazz.getDeclaredMethod("privateMethodVoid");
        privateMethodVoid.setAccessible(true);
        privateMethodVoid.invoke(privateMethodClassObject);
    }

    /**
     * 测试私有方法  使用工具类Junit自带的功能类ReflectionTestUtils
     */
    @Test
    public void privateMethodReflectUtilsTest() {
        //调用无参私有方法 privateMethodVoid
        ReflectionTestUtils.invokeMethod(privateMethodClass, "privateMethodVoid");

        //调用有参私有方法 privateMethodString
        String privateMethodStringReturn = ReflectionTestUtils.invokeMethod(privateMethodClass, "privateMethodString", "string");
        log.info("privateMethodStringReturn返回:{}", privateMethodStringReturn);

        //调用有参私有方法 privateMethodInteger  入参Integer类型
        Integer privateMethodIntegerReturn = ReflectionTestUtils.invokeMethod(privateMethodClass, "privateMethodInteger", 1);
        log.info("privateMethodIntegerReturn返回:{}", privateMethodIntegerReturn);

        //调用有参私有方法 privateMethod 入参String类型
        String privateMethod = ReflectionTestUtils.invokeMethod(privateMethodClass, "privateMethod", "HeSuiJin", 1);
        log.info("privateMethod返回:{}", JSONObject.toJSONString(privateMethod));

        //调用私有方法 privateMethodUpdateMember  入参UpdateMemberRequest类型
        UpdateMemberRequest updateMemberRequest = new UpdateMemberRequest();
        updateMemberRequest.setId(0L);
        updateMemberRequest.setName("HSJ");
        updateMemberRequest.setAge("18");
        UpdateMemberResponse updateMemberResponse = ReflectionTestUtils.invokeMethod(privateMethodClass, "privateMethodUpdateMember", updateMemberRequest);

        log.info("updateMemberResponse返回：{}", JSONObject.toJSONString(updateMemberResponse));
    }

}

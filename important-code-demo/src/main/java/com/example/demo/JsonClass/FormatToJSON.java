package com.example.demo.JsonClass;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/6/21
 */
@Slf4j
public class FormatToJSON {

    public static void main(String[] args) {
        List<BusinessTypeDTO> businessTypeDTOList = new ArrayList<>();


        BusinessTypeDTO businessTypeDTO = new BusinessTypeDTO();
        businessTypeDTO.setVcBusinTypeDesc("我是一级分类1");
        businessTypeDTO.setVcBusinCode("000");
        businessTypeDTO.setVcBusinName("我是000的名称");

        BusinessTypeDTO businessTypeDTO1 = new BusinessTypeDTO();
        businessTypeDTO1.setVcBusinTypeDesc("我是一级分类1");
        businessTypeDTO1.setVcBusinCode("111");
        businessTypeDTO1.setVcBusinName("我是111的名称");
//
//
//        BusinessTypeDTO businessTypeDTO2 = new BusinessTypeDTO();
//        businessTypeDTO2.setVcBusinTypeDesc("我是一级分类2");
//        businessTypeDTO2.setVcBusinCode("222");
//        businessTypeDTO2.setVcBusinName("我是2222的名称");

        businessTypeDTOList.add(businessTypeDTO);
        businessTypeDTOList.add(businessTypeDTO1);
//        businessTypeDTOList.add(businessTypeDTO2);
        log.info(JSONObject.toJSONString(businessTypeDTOList));
    }
}

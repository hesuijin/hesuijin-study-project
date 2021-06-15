package com.example.demo.JsonClass;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/6/15
 */
@Slf4j
public class JsonTest {

    public static void main(String[] args) {
            ResponseDTO responseDTO = new ResponseDTO();

            List<BusinessTypeDTO> businessTypeDTOList = new ArrayList<>();

            BusinessTypeDTO businessTypeDTO = new BusinessTypeDTO();
            businessTypeDTO.setBusinessDescOne("我是一级分类1");
            businessTypeDTO.setBusinessTypeTwo("000");
            businessTypeDTO.setBusinessDescTwo("我是000的名称");

            BusinessTypeDTO businessTypeDTO1 = new BusinessTypeDTO();
            businessTypeDTO1.setBusinessDescOne("我是一级分类1");
            businessTypeDTO1.setBusinessTypeTwo("111");
            businessTypeDTO1.setBusinessDescTwo("我是111的名称");


            BusinessTypeDTO businessTypeDTO2 = new BusinessTypeDTO();
            businessTypeDTO2.setBusinessDescOne("我是一级分类2");
            businessTypeDTO2.setBusinessTypeTwo("222");
            businessTypeDTO2.setBusinessDescTwo("我是2222的名称");

            businessTypeDTOList.add(businessTypeDTO);
            businessTypeDTOList.add(businessTypeDTO1);
            businessTypeDTOList.add(businessTypeDTO2);

            List<BusinessTypeDTOFirst> businessTypeDTOFirstList = new ArrayList<>();

            BusinessTypeDTOFirst businessTypeDTOFirst = new BusinessTypeDTOFirst();
            businessTypeDTOFirst.setBusinessDescOne("我是一级分类1");

            BusinessTypeDTOFirst businessTypeDTOFirst2 = new BusinessTypeDTOFirst();
            businessTypeDTOFirst2.setBusinessDescOne("我是一级分类2");

            businessTypeDTOFirstList.add(businessTypeDTOFirst);
            businessTypeDTOFirstList.add(businessTypeDTOFirst2);


            businessTypeDTOFirstList.forEach(first -> {
                businessTypeDTOList.forEach(second -> {
                    if (second.getBusinessDescOne().equals(first.getBusinessDescOne())) {
                        first.getBusinessTypeDTOList().add(second);
                    }
                });
            });


            responseDTO.setList(businessTypeDTOFirstList);

            log.info(JSONObject.toJSONString(responseDTO));
        }
    }


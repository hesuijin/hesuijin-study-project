package com.example.demo.JsonClass;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/6/15
 */
@Data
public class BusinessTypeDTOFirst {

    String  vcBusinTypeDesc;

    List<BusinessTypeDTO> businessTypeDTOList = new ArrayList<>();
}

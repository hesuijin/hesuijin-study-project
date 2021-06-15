package com.example.demo.JsonClass;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/6/15
 */
@Data
public class ResponseDTO {



    String isSuccess;

    String msg;

    String code;

    private List<BusinessTypeDTOFirst> Data ;
}

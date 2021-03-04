package com.example.jwt.base;

import lombok.Data;

/**
 * @Author hesuijin
 * @Description
 * @Param
 * @Return
 * @Date 2021/3/3
 */
@Data
public class BaseModel {

    private Long createdAt;

    private Long updatedAt;

    private String createdBy;

    private String updatedBy;

}

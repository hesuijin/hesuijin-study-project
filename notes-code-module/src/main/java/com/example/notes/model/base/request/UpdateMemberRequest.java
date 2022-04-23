package com.example.notes.model.base.request;

import lombok.Data;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/11
 */
@Data
public class UpdateMemberRequest {

    private Long id;

    private String name;

    private Integer age;

}

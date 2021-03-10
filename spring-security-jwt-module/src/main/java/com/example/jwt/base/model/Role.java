package com.example.jwt.base.model;

import com.example.jwt.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 主键ID：role
 * @author hsj 2021-03-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseModel {

    private Long id;
    private String name;
    private String description;

}
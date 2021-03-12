package com.example.jwt.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.jwt.base.BaseModel;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 主键ID：role
 * @author hsj 2021-03-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Role extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String description;

}
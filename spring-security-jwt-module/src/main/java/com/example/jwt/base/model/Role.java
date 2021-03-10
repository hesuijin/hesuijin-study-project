package com.example.jwt.base.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.jwt.base.BaseModel;
import lombok.*;

/**
 * 主键ID：role
 * @author hsj 2021-03-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String description;

    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
    @TableField(fill = FieldFill.INSERT)
    private String createUserName;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
}
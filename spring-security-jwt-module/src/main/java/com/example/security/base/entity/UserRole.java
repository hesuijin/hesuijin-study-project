package com.example.security.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.security.base.BaseModel;
import lombok.*;


/**
 * 主键Id：user_role
 * @author hsj 2021-03-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String userName;
    private String fullName;
    private String password;
    private Boolean enabled;

    private String name;
    private String description;

}
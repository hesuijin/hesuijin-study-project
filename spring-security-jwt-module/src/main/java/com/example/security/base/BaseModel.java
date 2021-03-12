package com.example.security.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

/**
 * @Author hesuijin
 * @Description
 * @Param
 * @Return
 * @Date 2021/3/3
 */
@Data
public class BaseModel {

    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
    @TableField(fill = FieldFill.INSERT)
    private String createUserName;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;

}

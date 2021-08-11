package com.example.demo.valid.base;


//Hibernate Validator包含了jakarta.validation（javax.validation.constraints）
//        （JSR）（全称Java Specification Requests ）Java规范请求
//        可使用以下常用注解：
//
//@NotNull 被注释的元素必须不为 null
//@Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
//@Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
//@Size(max=, min=) 被注释的元素的大小必须在指定的范围内
//@Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
//
//@NotEmpty 被注释的字符串的必须非空
//@NotBlank(message =) 验证字符串非 null，且长度必须大于 0
//@Email 被注释的元素必须是电子邮箱地址
//@Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
//@Range(min=,max=,message=) 被注释的元素必须在合适的范围内
//
//        注意： 可以使用 (message = "我是自定义信息")


import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;


/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/6/28
 */
@Data
public class ValidRequest {
//    @NotNull://CharSequence, Collection, Map 和 Array 对象不能是 null, 但可以是空集（size = 0）。
//    @NotEmpty://CharSequence, Collection, Map 和 Array 对象不能是 null 并且相关对象的 size 大于 0。
//    @NotBlank://String 不是 null 且去除两端空白字符后的长度（trimmed length）大于 0。

//    @Valid 可以用于嵌套校验（即校验在 ValidRequest 里面的对象）

    //被注释的元素必须为 null
    @Null(message = "必须为null")
    private String nullTest;

    //被注释的元素必须不为 null
    @NotNull(message = "必须不为null  作用于字符串或者集合  但可以是空字符串 或者空集合")
    private String notNull;

    @NotEmpty(message = "必须不为null  作用于字符串或者集合   但不可以是空字符串 或者空集合")
    private String notEmpty;

    @NotBlank(message = "必须不为为null  只能作用于字符串  不能为空字符串")
    private String notBlank;

    @NotEmpty(message = "必须不为null  作用于字符串或者集合   但不可以是空字符串 或者空集合")
    private List<String> listIsNotEmpty;



//    @DecimalMin(value = " 1.01", message = "最小值为1")
//    private String decimalMin;
//
//    @DecimalMax(value = "9.99", message = "最大值为10")
//    private String decimalMax;
//
//    @Min(value = 1, message = "最小值为1")
//    private String min;
//
//    @Max(value = 10, message = "最大值为10")
//    private String max;
//
//    @Size(min=1,max=10,message = "被注释的元素的大小必须在指定的范围内")
//    private String size;
//
//    //validator中final jar包中的注解
//    @Length(min=1,max=10,message = "被注释的字符串的大小必须在指定的范围内")
//    private String length;
//
//    //validator中final jar包中的注解
//    @Range(min=1,max=10,message="被注释的元素必须在合适的范围内")
//    private String range;
//
//    @Email(message = "email 格式不正确")
//    private String email;
//
//    @Pattern(regexp = "((^Man$|^Woman$))",
//            message = "sex 值不在可选范围")
//    private String sex;


}

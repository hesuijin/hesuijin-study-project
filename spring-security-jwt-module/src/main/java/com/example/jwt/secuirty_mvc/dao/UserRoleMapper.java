package com.example.jwt.secuirty_mvc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jwt.base.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 主键Id数据操作接口
 * @author hsj 2021-03-04
*/
@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
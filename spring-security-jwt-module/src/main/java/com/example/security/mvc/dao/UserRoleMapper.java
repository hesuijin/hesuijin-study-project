package com.example.security.mvc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.security.base.entity.UserRole;
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
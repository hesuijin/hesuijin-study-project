package com.example.jwt.secuirty.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jwt.base.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 主键Id数据操作接口
 * @author hsj 2021-03-04
*/
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
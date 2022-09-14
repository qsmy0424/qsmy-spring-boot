package com.qsmy.mybatis.mapper;

import com.qsmy.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author qsmy
 * @time 2022/9/14
 */

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM orm_user")
    List<User> selectAllUser();

    int saveUser(@Param("user") User user);

    User selectById(@Param("id") int id);

    void deleteById(int id);
}

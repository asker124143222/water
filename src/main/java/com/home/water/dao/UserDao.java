package com.home.water.dao;

import com.home.water.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.annotation.Generated;
import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author xu.dm
 * @since 2020-03-30 22:41:44
 */
@Repository
//@Mapper
public interface UserDao {
    @Select("select * from user order by userid desc")
    List<User> getAll();

    @Select("select * from user where userid=#{id}")
    User getOne(Integer id);

    @Select("select * from user where username=#{username} and password=#{password}")
    User getOneByNameAndPassword(User user);

    // 回写主键
    @Options(useGeneratedKeys = true,keyProperty = "userid")
    @Insert("INSERT INTO `user` (`username`,`name`,`password`,`salt`,`state`,createTime) " +
            "VALUES (#{username}, #{name}, #{password}, #{salt}, 1,sysdate())")
    int insert(User user);

    int update(User user);

    int delete(Integer id);
}
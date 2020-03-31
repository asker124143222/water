package com.home.water.dao;

import com.home.water.entity.User;
import com.home.water.model.UserWeather;
import org.apache.ibatis.annotations.*;
import org.springframework.jdbc.core.SqlProvider;
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

    @Update("update `user` set name=#{name},username=#{username} where userid=#{userid}")
    int update(User user);

    @Delete("delete from user where userid=#{id}")
    int delete(Integer id);

    @Select("select u.`userId`,u.`userName`,u.name,wd.weather_year,wd.avg_value " +
            "from `user` u inner join weather_data wd  on u.`userId`= wd.userid; ")
    List<UserWeather> getAllUserAndWeather();

    @Select("select u.`userId`,u.`userName`,u.name,wd.weather_year,wd.avg_value " +
            "from `user` u inner join weather_data wd on u.`userId`= wd.userid where u.userid=#{id} ")
    List<UserWeather> getUserAndWeatherByID(Integer id);
}
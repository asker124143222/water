package com.home.water.dao;

import com.home.water.entity.User;
import com.home.water.entity.WeatherData;
import com.home.water.model.UserVO;
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
//@Mapper
public interface UserDao {
    @Select("SELECT `user`.`userId`,\n" +
            "    `user`.`createTime`,\n" +
            "    `user`.`email`,\n" +
            "    `user`.`expiredDate`,\n" +
            "    `user`.`name`,  \n" +
            "    CASE `user`.`state`\n" +
            "    WHEN 0 THEN '未认证'\n" +
            "    WHEN '1' THEN '正常'\n" +
            "    ELSE '用户锁定'  END as state,    \n" +
            "    `user`.`tel`,\n" +
            "    `user`.`userName`\n" +
            "FROM `busdata`.`user` order by userid asc")
    List<UserVO> getAll();

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Select("SELECT `user`.`userId`,\n" +
            "    `user`.`createTime`,\n" +
            "    `user`.`email`,\n" +
            "    `user`.`expiredDate`,\n" +
            "    `user`.`name`,  \n" +
            "    CASE `user`.`state`\n" +
            "    WHEN 0 THEN '未认证'\n" +
            "    WHEN '1' THEN '正常'\n" +
            "    ELSE '用户锁定'  END as state,    \n" +
            "    `user`.`tel`,\n" +
            "    `user`.`userName`\n" +
            " from `busdata`.`user` limit #{offset}, #{limit}")
    List<UserVO> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    @Select("select * from user where userid=#{id}")
    User getOne(Integer id);

    @Select("select * from user where username=#{username} and password=#{password}")
    User getOneByNameAndPassword(User user);

    @Select("select * from user where username=#{username}")
    User getUserByName(String username);


    // 回写主键
    @Options(useGeneratedKeys = true,keyProperty = "userid")
    @Insert("INSERT INTO `user` (`username`,`name`,`password`,`salt`,`state`,createTime) " +
            "VALUES (#{username}, #{name}, #{password}, #{salt}, #{state},sysdate())")
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
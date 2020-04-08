package com.home.water.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author xu.dm
 * @since 2020-03-30 21:43:25
 */
@ApiModel(value="user对象",description="用户对象user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -62465686931818589L;
    
    private Integer userid;
    
    private Date createtime;
    
    private String email;
    
    private Date expireddate;
    
    private String name;
    
    private String password;
    
    private String salt;
    
    private byte state;
    
    private String tel;
    
    private String username;

}
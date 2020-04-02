package com.home.water.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xu.dm
 * @Date: 2020/4/2 20:15
 * @Description:
 */
@Data
public class UserVO implements Serializable {
    private Integer userid;

    private Date createtime;

    private String email;

    private Date expireddate;

    private String name;

    private String state;

    private String tel;

    private String username;
}

package com.home.water.entity;

import com.home.water.common.EntityStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门(Dept)实体类
 *
 * @author xu.dm
 * @since 2020-09-24 13:55:48
 */
@Data
public class Dept implements Serializable {
    private static final long serialVersionUID = -53071591926595976L;
    /**
     * ID
     */
    private Long id;
    /**
     * 名称
     */
    private String deptName;
    /**
     * 上级部门
     */
    private String pid;
    /**
     * 状态 1=true 0=false
     */
    private Integer state;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新日期
     */
    private Date updateTime;

    public String getState() {
        return EntityStatus.getMessage(this.state);
    }
}
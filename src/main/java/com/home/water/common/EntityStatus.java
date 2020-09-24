package com.home.water.common;


/**
 * @Author: xu.dm
 * @Date: 2020/9/24 13:51
 * @Version: 1.0
 * @Description: TODO
 **/
public enum EntityStatus {
    NORMAL(1,"正常"),
    BAN(0,"禁用");

    private Integer code;
    private String message;

    EntityStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static String getMessage(Integer code) {
        for (EntityStatus value : values()) {
            if(value.code.equals(code)){
                return value.message;
            }
        }
        return null;
    }
}

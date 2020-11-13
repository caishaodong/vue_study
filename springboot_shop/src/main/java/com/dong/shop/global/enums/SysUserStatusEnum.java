package com.dong.shop.global.enums;

/**
 * @Author caishaodong
 * @Date 2020-11-13 14:30
 * @Description
 **/
public enum SysUserStatusEnum {
    NORMAL(1, "正常"),
    CANCEL(2, "冻结"),
    FROZEN(3, "注销");

    private int status;
    private String desc;

    SysUserStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

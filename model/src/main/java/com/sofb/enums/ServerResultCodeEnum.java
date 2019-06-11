package com.sofb.enums;


public enum ServerResultCodeEnum {
    C0000("成功"),
    C0001("操作失败"),
    C0004("未登录"),
    C0005("无权限"),
    C0006("用户名/密码错误"),
    C0007("账号被锁定"),
    C0008("参数异常"),
    C0009("系统异常");

    String desc;

    ServerResultCodeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}

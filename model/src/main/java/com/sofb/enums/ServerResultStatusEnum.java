package com.sofb.enums;


public enum ServerResultStatusEnum {
    C0000("成功"), C0001("操作失败");

    String desc;

    ServerResultStatusEnum(String desc) {
        this.desc = desc;
    }
}

package com.sofb.enums;

public enum UserStatusEnum implements CodeBaseEnum {
    LOCKED("锁定", 1),
    FREEZE("冻结", 2),
    NORMAL("正常", 3);

    private String desc;

    private int value;

    UserStatusEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int value() {
        return getValue();
    }
}

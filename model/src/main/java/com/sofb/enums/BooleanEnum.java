package com.sofb.enums;

public enum BooleanEnum {
    TURE("", 1),
    FALSE("", 2);

    private String desc;

    private int value;

    BooleanEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public int getValue() {
        return value;
    }
}

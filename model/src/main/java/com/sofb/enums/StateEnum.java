package com.sofb.enums;

public enum StateEnum implements CodeBaseEnum{
    EFFECTIVE("启用",0),
    DISABLED("禁用",1);

    private String desc;

    private int value;

    StateEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int value() {
        return getValue();
    }
}

package com.sofb.enums;

public enum CommonEnum {


    YES("YES", 1), NO("NO", 2), UNKNOWN("未知", 3);
    private String desc;
    private int value;

    CommonEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public String getValue() {
        return desc;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

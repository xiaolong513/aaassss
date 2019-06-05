package com.sofb.enums;

public enum WindowTypeEnum implements CodeBaseEnum {
    NORMAL("普通窗", 1),
    NO("无窗", 2),
    BAY("飘窗", 3),
    FRENCH("落地窗", 4),
    FRENCH_BAY("落地飘窗", 5),
    LUNETTE("落地飘窗", 6),
    UNKNOWN("未知", 7);

    private String desc;
    private int value;

    WindowTypeEnum(String desc, int value) {
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

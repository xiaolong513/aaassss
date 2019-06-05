package com.sofb.enums;

public enum PropertyOwnerShipEnum {
    COMMUNITY("共有", 1),
    NO_COMMUNITY("非共有", 2),
    UNKNOWN("未知", 3);

    private String desc;

    private int value;

    PropertyOwnerShipEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

}

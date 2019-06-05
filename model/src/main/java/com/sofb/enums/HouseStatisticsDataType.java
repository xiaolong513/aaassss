package com.sofb.enums;

public enum HouseStatisticsDataType {
    ONLINE("线上", 1),
    UNONLINE("下架", 2),
    TOTAL("总量", 3);

    private String desc;
    private int value;

    HouseStatisticsDataType(String desc, int value) {
        this.value = value;
        this.desc = desc;
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
}

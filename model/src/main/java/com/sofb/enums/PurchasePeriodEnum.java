package com.sofb.enums;

public enum PurchasePeriodEnum {
    LESSTWOYEARS("未满2年", "未满2年", 1),
    OVERTWOYEARS("满两年", "满两年", 2),
    OVERFIVEYEARS("满五年", "满五年", 3),
    UNKNOWN("未知", "", 4);

    private int level;

    private String desc;

    private String alias;

    PurchasePeriodEnum(String desc, String alias, int level) {
        this.desc = desc;
        this.level = level;
        this.alias = alias;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

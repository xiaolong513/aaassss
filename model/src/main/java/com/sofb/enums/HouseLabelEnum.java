package com.sofb.enums;

public enum HouseLabelEnum {
    LIGHTSPOT("房源亮点", 1),
    DECORATION("布局装修", 2),
    TRAFFIC("交通出行", 3),
    GARDEN_ADVANTAGE("小区优势", 4),
    PERIPHERALSUPPORTING("周边配套", 5),
    TAX_ANALYSIS("税费分析", 6),
    SELL_REASON("售房原因", 7),
    LAYOUT_DESC("户型描述", 8),
    EDUCATION_FACILITIES("教育配套", 9),
    BUILDING_DESC("大厦介绍", 10),
    PROPERTY_DESC("物业介绍", 11),
    CORE_POINT("核心卖点", 12);

    private String desc;
    private int value;

    HouseLabelEnum(String desc, int value) {
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
}

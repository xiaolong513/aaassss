package com.sofb.enums;

public enum CityEnum {
    SHANGHAI("上海","SHANGHAI","SH"),
    BEIJING("北京","BEIJING", "BJ"),
    SHENZHEN("深圳", "SHENZHEN","SZ"),
    ZHUHAI("珠海", "ZHUHAI","ZH"),
    ZHONGSHAN("中山", "ZHONGSHAN","ZS"),
    DONGGUAN("东莞", "DONGGUAN","DG"),
    GUANGZHOU("广州","GUANGZHOU","GZ"),
    CHENGDU("成都", "CHENGDU", "CD"),
    CHONGQING("重庆", "CHONGQING","CQ"),
    WUHAN("武汉", "WUHAN", "WH"),
    SUZHOU("苏州", "SUZHOU", "SZ"),
    NANJING("南京", "NANJING", "NJ"),
    ZHENGZHOU("郑州", "ZHENGZHOU", "ZZ");

    private String alias;
    private String value;
    private String simplePin;


    CityEnum(String alias, String value, String simplePin){
        this.alias = alias;
        this.value = value;
        this.simplePin = simplePin;
    }
}

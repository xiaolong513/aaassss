package com.sofb.enums;

import java.util.ArrayList;
import java.util.List;

public enum SourceEnum implements CodeBaseEnum {
    ANJUKE("安居客", 1),
    WB("58", 2),
    FANGTIANXIA("房天下", 3),
    BEIKE("贝壳", 4),
    ZHUGE("诸葛", 5),
    LIANJIA("链家", 6),
    QFANG("Q房", 7),
    LEYOUJIA("乐有家", 8),
    ZHONGYUAN("中原", 9);

    private String desc;
    int value;

    SourceEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String[] getNameAndDesc(SourceEnum sourceEnum){
        String[] strings = new String[2];
        strings[0] = sourceEnum.name();
        strings[1] = sourceEnum.getDesc();
        return strings;
    }

    public static SourceEnum[] getZJEnum() {
        SourceEnum[] zjEnums = {WB, ANJUKE, FANGTIANXIA, BEIKE, ZHUGE};

        return zjEnums;
    }

    public static List<SourceEnum> getZJEnumList() {
        List<SourceEnum> zjEnumList = new ArrayList<>();
        zjEnumList.add(WB);
        zjEnumList.add(ANJUKE);
        zjEnumList.add(FANGTIANXIA);
        zjEnumList.add(BEIKE);
        zjEnumList.add(ZHUGE);
        return zjEnumList;
    }

    public static List<String[]> getZJEnumAttrList() {
        List<String[]> zjEnumList = new ArrayList<>();
        zjEnumList.add(getNameAndDesc(WB));
        zjEnumList.add(getNameAndDesc(ANJUKE));
        zjEnumList.add(getNameAndDesc(FANGTIANXIA));
        zjEnumList.add(getNameAndDesc(BEIKE));
        zjEnumList.add(getNameAndDesc(ZHUGE));
        return zjEnumList;
    }

    public static SourceEnum[] getNOZJEnum() {
        SourceEnum[] zjEnums = {LIANJIA, QFANG, LEYOUJIA, ZHONGYUAN};

        return zjEnums;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int value() {
        return getValue();
    }
}

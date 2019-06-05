package com.sofb.enums;

import java.util.List;

public enum HouseStatusEnum {
    RENT("租", 1),
    SALE("售", 2);

    HouseStatusEnum(String alias, int value) {
        this.alias = alias;
        this.value = value;
    }

    private String alias; //别名
    private Integer value; //值

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static HouseStatusEnum getEnumById(int value) {
        HouseStatusEnum[] enums = HouseStatusEnum.values();
        for (HouseStatusEnum houseStatusEnum : enums) {
            if (houseStatusEnum.getValue() == value) {
                return houseStatusEnum;
            }
        }
        return null;
    }

    public static HouseStatusEnum getEnumByName(String name) {
        HouseStatusEnum[] enums = HouseStatusEnum.values();
        for (HouseStatusEnum houseStatusEnum : enums) {
            if (houseStatusEnum.name().equals(name)) {
                return houseStatusEnum;
            }
        }
        return null;
    }

    /**
     * 取枚举的json字符串
     *
     * @return
     */
    public static String getJsonStr(List<HouseStatusEnum> enums) {
        StringBuffer jsonStr = new StringBuffer("[");
        for (HouseStatusEnum houseStatusEnum : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'")
                    .append(houseStatusEnum)
                    .append("',name:'")
                    .append(houseStatusEnum.getAlias())
                    .append("',value:'")
                    .append(houseStatusEnum)
                    .append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }

    /**
     * 取枚举的json字符串
     *
     * @return
     */
    public static String getJsonStr(HouseStatusEnum houseStatusEnum) {
        StringBuffer jsonStr = new StringBuffer("");
        jsonStr.append("{id:'")
                .append(houseStatusEnum)
                .append("',name:'")
                .append(houseStatusEnum.getAlias())
                .append("',value:'")
                .append(houseStatusEnum)
                .append("'}");
        return jsonStr.toString();
    }

    /**
     * 取枚举的json字符串
     *
     * @return
     */
    public static String getJsonStr() {
        HouseStatusEnum[] enums = HouseStatusEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (HouseStatusEnum enumTemp : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'")
                    .append(enumTemp)
                    .append("',name:'")
                    .append(enumTemp.getAlias())
                    .append("',value:'")
                    .append(enumTemp)
                    .append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }
}

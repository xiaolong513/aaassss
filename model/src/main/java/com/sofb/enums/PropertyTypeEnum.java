
package com.sofb.enums;

/**
 * 物业类型
 *
 * @author Hou Peiqin
 */
public enum PropertyTypeEnum {
    APARTMENT_APARTMENT("普通住宅", 1, "APARTMENT"),
    APARTMENT_VILLA("别墅", 2, "APARTMENT"),
    APARTMENT_RAISEFUNDS("集资房", 3, "APARTMENT"),
    APARTMENT_WELFARE("福利房", 4, "APARTMENT"),
    LODGINGHOUSE_NORMAL("普通公寓", 5, "LODGINGHOUSE"),
    LODGINGHOUSE_HOTEL("酒店式公寓", 6, "LODGINGHOUSE"),
    LODGINGHOUSE_BIZ("商务公寓", 7, "LODGINGHOUSE"),
    BUILDING("写字楼", 8, "BUILDING"),
    LIVINGBUILDING("商住", 9, "LIVINGBUILDING"),
    SHOP("商铺", 10, "SHOP"),
    SHOP_SUBWAY("地铁商铺", 11, "SHOP");

    PropertyTypeEnum(String desc, int value, String parentType) {
        this.desc = desc;
        this.value = value;
        this.parentType = parentType;
    }

    private String desc;
    private int value;
    private String parentType;

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

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }
}

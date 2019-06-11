package com.sofb.enums;

public enum ResourceTypeEnum implements CodeBaseEnum {
    MENU("菜单", 1),
    BUTTON("按钮", 2);

    private String info;

    private int value;

    ResourceTypeEnum(String info, int value) {
        this.info = info;
        this.value = value;
    }

    public String getInfo() {
        return info;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int value() {
        return getValue();
    }
}

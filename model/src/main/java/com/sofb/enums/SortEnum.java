package com.sofb.enums;

public enum SortEnum {

    ASC("升序"),
    DESC("降序");

    SortEnum(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

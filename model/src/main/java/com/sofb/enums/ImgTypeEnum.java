package com.sofb.enums;

import java.util.concurrent.ConcurrentHashMap;

public enum ImgTypeEnum {
    BEDROOM("卧室"),
    LAYOUT("户型"),
    PARLOUR("客厅"),
    RESTAURANT("餐厅"),
    BALCONY("阳台"),
    KITCHEN("厨房"),
    BATHROOM("卫生间"),
    STUDY("书房"),
    LOCKER("储物间"),
    WASHROOM("洗手间"),
    OFFICE("办公室"),
    OPEN_OFFICE("办公室开间"),
    OTHERS("其他");

    private String desc;

    ImgTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    private static ConcurrentHashMap<String, ImgTypeEnum> cacheDescMap = new ConcurrentHashMap();

    public static ImgTypeEnum getImgTypeByDesc(String desc) {
        if (desc == null) {
            return ImgTypeEnum.OTHERS;
        }
        ImgTypeEnum result = cacheDescMap.get(desc);
        if (result != null) {
            return result;
        }
        ImgTypeEnum[] imgTypeEnums = ImgTypeEnum.values();
        for (ImgTypeEnum imgEnum : imgTypeEnums) {
            if (imgEnum.desc.equals(desc)) {
                cacheDescMap.put(desc, imgEnum);
                return imgEnum;
            }
        }
        return ImgTypeEnum.OTHERS;
    }
}

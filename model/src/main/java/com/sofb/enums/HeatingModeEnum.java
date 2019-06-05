package com.sofb.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum HeatingModeEnum {
    NONE("无供暖"),
    FOCUS("集中供暖"),
    CONFESSION("自供暖"),
    CENTER("中央空调");


    private String desc;

    HeatingModeEnum(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static List<Map<String,Object>> getMap(){
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        HeatingModeEnum[] enums = HeatingModeEnum.values();
        for(HeatingModeEnum e : enums){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",e.name());
            map.put("name",e.getDesc());
            result.add(map);
        }
        return result;
    }
}

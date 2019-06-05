package com.sofb.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum RoomStructuralEnum {
    STEPTYPE("STEPTYPE", "跃式", "3"),
    DOUBLE("DOUBLE", "复式", "4"),
    LAYER("LAYER", "错层", "2"),
    PLANE("PLANE", "平面", "1");

    RoomStructuralEnum(String key, String description, String todgo) {
        this.key = key;
        this.description = description;
        this.todgo = todgo;
    }

    private String key;
    private String description;
    private String todgo;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTodgo() {
        return todgo;
    }

    public void setTodgo(String todgo) {
        this.todgo = todgo;
    }

    /**
     * 取枚举的json字符串
     *
     * @return
     */
    public static String getJsonStr() {
        RoomStructuralEnum[] enums = RoomStructuralEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (RoomStructuralEnum senum : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'")
                    .append(senum.getKey())
                    .append("',name:'")
                    .append(senum.getDescription())
                    .append("',value:'")
                    .append(senum)
                    .append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }

    public static List<Map<String, Object>> convertMap() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        RoomStructuralEnum[] enums = RoomStructuralEnum.values();
        for (RoomStructuralEnum e : enums) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("value", e.name());
            map.put("text", e.getDescription());
            result.add(map);
        }
        return result;
    }
}

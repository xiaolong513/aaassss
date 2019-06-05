package com.sofb.enums;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DirectionEnum {
	EAST("东","1"),
	SOUTH("南","2"),
	WEST("西","3"),
	NORTH("北","4"),
	NORTHEAST("东北","5"),
	SOUTHEAST("东南","6"),
	WESTEAST("东西","7"),
	NORTHSOUTH("南北","8"),
	NORTHWEST("西北","9"),
	SOUTHWEST("西南","10");
	
	private DirectionEnum(String name, String value) {
		this.nameStr=name;
		this.value=value;
	}
	private String nameStr;
	private String value;
	
	public String getNameStr() {
		return nameStr;
	}
	
	public String getValue() {
		return value;
	}
	public static void main(String[] args){
		System.out.println(DirectionEnum.valueOf("EAST"));
	}
	
	/**
	 * 取枚举的json字符串
	 * @return
	 */
	public static String getJsonStr(){
		DirectionEnum[] enums = DirectionEnum.values();
		StringBuffer jsonStr = new StringBuffer("[");
		for (DirectionEnum senum : enums) {
			if(!"[".equals(jsonStr.toString())){
				jsonStr.append(",");
			}
			jsonStr.append("{id:'")
					.append(senum)
					.append("',name:'")
					.append(senum.getNameStr())
					.append("',value:'")
					.append(senum)
					.append("'}");
		}
		jsonStr.append("]");
		return jsonStr.toString();
	}

	public static List<Map<String,Object>> convertMap(){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		DirectionEnum[] enums = DirectionEnum.values();
		for(DirectionEnum e : enums){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("value",e.name());
			map.put("text",e.getNameStr());
			result.add(map);
		}
		return result;
	}

	public static DirectionEnum getDirectionFromValue(String value){
		if(StringUtils.isEmpty(value)) return  null;
		DirectionEnum[] enums = DirectionEnum.values();
		DirectionEnum ret = null;
		for(DirectionEnum directionEnum : enums){
			if(value.equals(directionEnum.getValue())) {
				ret = directionEnum;
				break;
			}
		}
		return ret;
	}
}

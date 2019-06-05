package com.sofb.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public enum DecorationEnum {
	LUXURY("豪华装修",4),
	HARDCOVER("精装修",3),
	SIMPLE("普通装修",2),
	BLANK("毛坯",1);
	
	private String nameStr;
	private int value;
	public String getNameStr() {
		return nameStr;
	}
	public int getValue() {
		return value;
	}
	DecorationEnum(String nameStr, int value){
		this.nameStr=nameStr;
		this.value=value;
	}

	public static DecorationEnum[] getValidDecoration() {
		return Stream.of(HARDCOVER, SIMPLE, BLANK).toArray(DecorationEnum[]::new);
	}

	public static String getValidDecorationJson() {
		DecorationEnum[] enums = getValidDecoration();
		StringBuffer jsonStr = new StringBuffer("[");
		for (DecorationEnum senum : enums) {
			if(!"[".equals(jsonStr.toString())){
				jsonStr.append(",");
			}
			jsonStr.append("{id:'")
					.append(senum.getValue())
					.append("',name:'")
					.append(senum.getNameStr())
					.append("',value:'")
					.append(senum)
					.append("'}");
		}
		jsonStr.append("]");
		return jsonStr.toString();
	}

	/**
	 * 取枚举的json字符串
	 * @return
	 */
	public static String getJsonStr(){
		DecorationEnum[] enums = DecorationEnum.values();
		StringBuffer jsonStr = new StringBuffer("[");
		for (DecorationEnum senum : enums) {
			if(!"[".equals(jsonStr.toString())){
				jsonStr.append(",");
			}
			jsonStr.append("{id:'")
					.append(senum.getValue())
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
		DecorationEnum[] enums = DecorationEnum.values();
		for(DecorationEnum e : enums){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("value",e.name());
			map.put("text",e.getNameStr());
			result.add(map);
		}
		return result;
	}
}
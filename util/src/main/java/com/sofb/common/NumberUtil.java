package com.sofb.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {

	// 负数会转换为正数
	public static Integer toInteger(Object obj,Integer defaultValue){
		try {
			return Integer.parseInt(getNumbers(StringUtil.valueOf(obj)));
		} catch (Exception e) {
               return defaultValue;
		}
	}



	// 负数会转换为正数
	public static Integer toInteger(Object obj){
	    try {
	        return Integer.parseInt(getNumbers(StringUtil.valueOf(obj)));
	    } catch (Exception e) {
	        return Integer.valueOf(0);
	    }
	}

	// 正数是正数，负数是负数
	public static int toInt(Object obj) {
		try {
			if (null == obj) {
				return 0;
			}

			return Integer.valueOf(obj.toString()).intValue();
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String getNumbers(String content) {
		if(StringUtil.isEmpty(content)){
			return "0";
		}
		Pattern pattern = Pattern.compile("[+-]?\\d+(\\.\\d+)?");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "0";
	}
	
	public static Double toDouble(Object obj,Double defaultValue){
		try {
			return Double.parseDouble(StringUtil.valueOf(obj));
		} catch (Exception e) {
               return defaultValue;
		}
	}
	
    public static Double toDouble(Object obj) {
        try {
            return Double.parseDouble(StringUtil.valueOf(obj));
        } catch (Exception e) {
            return Double.valueOf(0D);
        }
    }

	public static boolean isNumber(Object obj) {
		if (null == obj)
			return false;
		String numStr = obj + "";
		if (numStr.trim().length() < 1)
			return false;
		numStr = numStr.replaceAll("\\d", "");
		if (numStr.equals(".") || numStr.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validNumbers(String... numbers) {
		for(String number: numbers) {
			if(! number.matches("\\d{11}")) {
				return false;
			}
		}
		return true;
	}
	
    /**
     * @MethodName isInteger
     * @Description 判断是否为整数
     * 
     * @author zhangliancai
     * @date 2017年3月14日 上午9:06:52
     * @param obj
     * @return
     */
    public static boolean isInteger(Object obj) {
        if (null == obj) {
            return false;
        }
        
        String[] ary = obj.toString().split("\\.");
        
        if (ary.length == 1 && isNumber(ary[0])) {
            return true;
        }
        
        if (ary.length > 2) {
            return false;
        }
        
        Integer i = Integer.valueOf(ary[1]);
        
        return i == 0;
    }

	public static Integer integerAdd(Integer... ages) {
		Integer result = new Integer(0);
		if (ages == null || ages.length < 1) {
			return result;
		}
		for (Integer b : ages) {
			if (b != null) {
				result += b;
			}
		}
		return result;
	}

	public static BigDecimal bigDecimalAdd(BigDecimal... ages) {
		BigDecimal result = BigDecimal.valueOf(0);
		if (ages == null || ages.length < 1) {
			return result;
		}
		for (BigDecimal b : ages) {
			if (b != null) {
				result=result.add(b);
			}
		}
		return result;
	}
	
	public static BigDecimal createBigDecimal(String value,BigDecimal defualtBigDecimal){
		 if(StringUtil.isEmpty(value)){
			 return defualtBigDecimal;
		 }
		 return new BigDecimal(value);
	}
	
	public static BigDecimal createBigDecimal(Object value){
	    
	    if(StringUtil.isEmpty(value)){
	        return new BigDecimal(0);
	    }
	    
	    return new BigDecimal(String.valueOf(value));
	}

	public static BigDecimal setScale(BigDecimal bigDecimal, int scale, RoundingMode roundingMode){

		if(bigDecimal == null){
			return new BigDecimal(0);
		}

		return bigDecimal.setScale(scale,roundingMode);
	}
	
	/**
	 * 
	 */
	private static int numberAddValue=1;
	public synchronized static String createNumber(){
		long l=System.currentTimeMillis();
		l+=numberAddValue++;//闃叉寰幆鍒涘缓鍚屾牱鐨�
		if(numberAddValue>=Integer.MAX_VALUE)numberAddValue=0;
		final String number=String.valueOf(l);
		return number;
	}

	public static Double reserve(Double value,int digit){
		if(null==value){
			return (double) 0;
		}
		return Double.parseDouble(String.format("%."+digit+"f", value));
	}
	
	public static String reserveString(Double value,int digit){
		if(null==value){
			return "0";
		}
		return String.format("%." + digit + "f", value);
	}

	/**
	 *
	 * 功能说明：智能处理数字格式 1.00 处理的结果是1
	 * @author liuli
	 * @date 2014-9-16
	 * @param number
	 * @return
	 */
	public static String intelligenceFormat(Number number){
		if(number==null)
			return null;
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setGroupingUsed(false);
		return numberFormat.format(number);
	}

	// 负数会转换为正数
	public static int toInt(long obj){
		try {
			return Long.valueOf(obj).intValue();
		} catch (Exception e) {
			return 0;
		}
	}
	
}

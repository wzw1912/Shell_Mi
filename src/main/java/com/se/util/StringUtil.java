package com.se.util;


/**
 * 字符串 工具类
 * @author shuiming.tang
 * @date 2018-07-18
 * */
public class StringUtil {
	
	private StringUtil() {
		throw new RuntimeException(GlobalConstant.NOT_ALLOWED_INSTANCE);
	}
	
	public static boolean isEmpty(String str){

        return (str == null || "".equals(str))? true : false;
    }

}

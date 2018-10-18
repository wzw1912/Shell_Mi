package com.se.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class IpQueryApi {
	
	private static final String IP_QUERY="http://www.ip138.com/ips138.asp?ip=";
	private static Pattern ipAdressPtn=Pattern.compile("<li>\u672c\u7ad9\u6570\u636e\uff1a(.+?)</li>",Pattern.DOTALL);
	
	public static String getAddress(String ip){
		String ipAdress="";
		try {
			String infoPage=URLContextUtil.getURLContext(IP_QUERY+ip, "GBK");
			Matcher ipM=ipAdressPtn.matcher(infoPage);
			if (ipM.find()) {
				ipAdress=ipM.group(1);
			}
			return ipAdress;
		} catch (IOException e) {
			System.out.println("ip系统错误");
			e.printStackTrace();
			return null;
		}
	}

}

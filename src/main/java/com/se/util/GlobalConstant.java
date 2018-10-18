package com.se.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;

public class GlobalConstant {
	
	public static final String NOT_ALLOWED_INSTANCE = "It's not allowed instance!";
	
	public static final String SQL_COUNT_ID = "select count(*) from shell_app_config where id=?";
	public static final String SQL_COUNT_IP = "select count(*) from shell_app_ips where ip=?";
	
	public static final String SQL_CONFIGDATA_BY_ID = "select * from shell_app_config where id=?";
	public static final String SQL_IPENTITY_BY_IP = "select * from shell_app_ips where ip=?";
	/**通过ip，查询id sql(shell_app_ips表)*/
	public static final String SQL_QUERY_ID_BY_IP = "select id from shell_app_ips where ip=?";
	
	public static final String ID = "id";
	public static final String IP = "ip";
	public static final String ON_OFF = "on_off";
	public static final String DATA = "data";
	public static final String TIME = "time";
	
	public static final String NULL = "";
	
	public static final SimpleDateFormat SIMPLE_DATA_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	public static final String SQL_SAVE_IP = "insert into shell_app_ips values(?, ?, ?)";
	public static final String SQL_SAVE_ID = "insert into shell_app_config values(?, ?, ?)";
	
	public static final String SWITCH_OFF = "0";
	public static final String SWITCH_ON = "1";
	
	public static void main(String[] args) {
		String s = "0%";
		String[] arr = s.split("%");
		System.out.println("arr length: " + arr.length + ",foreach: " + Arrays.toString(arr));
	}
	
}

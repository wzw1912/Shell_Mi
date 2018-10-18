package com.se.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.se.service.IpService;

public class Test {
	
	@Autowired
	private static IpService ipService;
	
	public static void main(String[] args) {
		System.out.println("查询Id结果：" + ipService.selectIdByIp("115.194.106.193"));
	}
}

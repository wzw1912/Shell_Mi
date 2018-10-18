package com.se.base;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;

import com.avos.avoscloud.AVOSCloud;

@Component
public class InitImpl implements ServletContextListener {


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		String AppId = System.getenv("LEANCLOUD_APP_ID");
		String AppKey = System.getenv("LEANCLOUD_APP_KEY");
		String MasterKey = System.getenv("LEANCLOUD_APP_MASTER_KEY");

		AVOSCloud.initialize(AppId,AppKey,MasterKey);
		
	}

}

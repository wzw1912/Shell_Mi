package com.se.service;

import com.avos.avoscloud.AVException;
import com.se.entity.ConfigEntity;

public interface ConfigService {
	
	public boolean idIsExists(String id) throws AVException;

	public ConfigEntity selectConfigData(String idStr);
	
	public boolean saveIdData(ConfigEntity configEntity);
}

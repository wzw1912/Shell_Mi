package com.se.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.se.entity.ConfigEntity;
import com.se.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {

	/**
	 * id是否存在
	 * 
	 * @throws AVException
	 */
	public boolean idIsExists(String id) throws AVException {
		// AVQuery<AVObject> avQuery=new AVQuery<>("id");
		try {
//			String objectId = "5b8e2d4afb4ffe003119c001";
//			AVQuery<AVObject> avQuery = new AVQuery<>("shell_app_config");
//			AVObject object = avQuery.get(objectId);
			AVCloudQueryResult result = AVQuery.doCloudQuery("select count(*) from shell_app_config where id=? ", id);
			int i = result.getCount();
			if (i > 0) {
				return true;
			}
			
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		// int count = configMapper.idIsExists(id);
		// return count >= 1 ? true : false;
	}

	/**
	 * 根据id查找配置数据
	 */
	public ConfigEntity selectConfigData(String idStr) {
		ConfigEntity configEntity = new ConfigEntity();
		try {
			AVCloudQueryResult result = AVQuery.doCloudQuery("select * from shell_app_config where id=?", idStr);
			AVObject re = result.getResults().get(0);
			HashMap<String, Object> map=(HashMap<String, Object>) re.getMap("serverData");
			Boolean flag=false;
			int code=re.getInt("on_off");
			if (code==1) {
				flag=true;
			}
			if (re != null) {
				configEntity.setId(re.getString("id"));
				configEntity.setOn(flag);
				configEntity.setData(re.getString("data"));
				configEntity.setReserve(re.getString("reserve"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return configEntity;

		// configEntity = configMapper.selectConfigData(idStr);
		// return configEntity;
	}

	public boolean saveIdData(ConfigEntity configEntity) {
		try {
			AVObject config = new AVObject("shell_app_config");
			config.put("id", configEntity.getId());
			config.put("date", configEntity.getData());
			config.put("on_off", configEntity.isOn_off());
			config.put("reserve", configEntity.getReserve());
		//	config.save();
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		// int resultCode=configMapper.saveIdData(configEntity);
		// System.out.println("saveIdData 存储结果：" +resultCode);
		// return resultCode>=1?true:false;
	}

}

package com.se.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.se.service.IpShieldService;

@Service
public class IpShieldServiceImpl implements IpShieldService{

	@Override
	public List<? extends AVObject> selectIpRange() {
		try {
			
			AVCloudQueryResult result = AVQuery.doCloudQuery("select ipRange from shell_app_iprange ");
			List<? extends AVObject>  list=result.getResults();
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Integer selectByIp(String ip) {
		try {
			AVCloudQueryResult result = AVQuery.doCloudQuery("select count(*) from shell_app_ipdetail where ip=?",ip);
			int count=result.getCount();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

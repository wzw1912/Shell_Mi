package com.se.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.se.entity.IpEntity;
import com.se.service.IpService;

@Service
public class IpServiceImpl implements IpService {

	public boolean ipIsExists(String ip) {

		try {
			AVCloudQueryResult result = AVQuery.doCloudQuery("select count(*) from shell_app_ips where ip=? ", ip);
			int i = result.getCount();
			if (i >0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		// int count = ipMapper.ipIsExists(ip);
		//
		// return count >= 1 ? true : false;

	}

	@SuppressWarnings("unchecked")
	public Set<String> selectIdByIp(String ip) {

		Set<String> idsSet = new HashSet<String>();
		try {
			AVCloudQueryResult result = AVQuery.doCloudQuery("select id from shell_app_ips where ip=? ", ip);
			List<AVObject> resultList = (List<AVObject>) result.getResults();
			if (resultList != null && resultList.size() > 0) {
				for (AVObject avObject : resultList) {
					idsSet.add(avObject.getString("id"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idsSet;
	}

	public boolean saveIpData(IpEntity ipEntity) {
		try {
			AVObject ipEn = new AVObject("shell_app_ips");
			ipEn.put("ip", ipEntity.getIp());
			ipEn.put("id", ipEntity.getId());
			ipEn.put("time", ipEntity.getTime());
			ipEn.save();
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Boolean selectFlag() {
		try {
			AVCloudQueryResult result = AVQuery.doCloudQuery("select on_off from shell_app_ipsConf ");
			AVObject avObj=result.getResults().get(0);
			Integer code=(Integer) avObj.getNumber("on_off");
			if (code==1) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return false;
	}
}

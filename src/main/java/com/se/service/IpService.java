package com.se.service;

import java.util.Set;

import com.se.entity.IpEntity;

public interface IpService {

	public boolean ipIsExists(String ip);
	
	public Set<String> selectIdByIp(String ip);

	public boolean saveIpData(IpEntity ipEntity);

	public Boolean selectFlag();
}

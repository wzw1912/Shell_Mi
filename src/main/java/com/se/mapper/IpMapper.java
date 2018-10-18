package com.se.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.se.entity.IpEntity;

@Repository
public interface IpMapper {

	public Integer ipIsExists(String ip);
	
	public List<String> selectIdByIp (String ip);

	public Integer saveIpData(IpEntity ipEntity);
	
	public IpEntity selectIpEnti(String ip);
	
}

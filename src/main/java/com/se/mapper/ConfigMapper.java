package com.se.mapper;

import org.springframework.stereotype.Repository;

import com.se.entity.ConfigEntity;

@Repository
public interface ConfigMapper {

	public Integer idIsExists(String id);

	public ConfigEntity selectConfigData(String idStr);
	
	public Integer saveIdData(ConfigEntity configEntity);
}

package com.se.service;

import java.util.List;

import com.avos.avoscloud.AVObject;

public interface IpShieldService {

	List<? extends AVObject> selectIpRange();

	Integer selectByIp(String ip);

}

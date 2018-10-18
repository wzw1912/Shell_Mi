package com.se.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.avos.avoscloud.AVObject;
import com.se.entity.ConfigEntity;
import com.se.entity.IpEntity;
import com.se.service.ConfigService;
import com.se.service.IpService;
import com.se.service.IpShieldService;
import com.se.util.AES;
import com.se.util.GlobalConstant;
import com.se.util.IpQueryApi;
import com.se.util.IpUtil;
import com.se.util.StringUtil;

@Controller
public class H5Controller implements Serializable {

	private static final long serialVersionUID = 555712527200197234L;

	/*
	 * private static final String FIXED_PARAM_ID = "id"; private static final
	 * String FIXED_PARAM_KE = "ke";
	 */
	private static final String ON_OR_OFF_KEY = "t";
	private static final String H5_URL_KEY = "u";
	private static final String H5_RESERVE_KEY = "r";

	private static final String AES_KEY = "D5ue9bB6qnrZflU1WPK/Iw==";

	@Autowired
	private ConfigService configService;
	@Autowired
	private IpService ipService;
	@Autowired
	private IpShieldService ipShieldService;

	@ResponseBody
	@RequestMapping("/")
	public String leanCloud() {
		return "200";
	}

	/**
	 * 存储ip数据入库 处理逻辑方法
	 */
	@RequestMapping("/saveIp")
	private void saveIP(String ip, String idStr) {
		// 查询是否存在当前ip
		boolean b2 = ipService.ipIsExists(ip);

		if (b2) {// 已存在
			Set<String> idSet = ipService.selectIdByIp(ip);
			if (!idSet.contains(idStr)) {
				// System.out.println("已存在当前ip数据，配置id不同，存储数据");
				ipService.saveIpData(new IpEntity(ip, idStr, GlobalConstant.SIMPLE_DATA_FORMAT.format(new Date())));
			}
		} else {// 不存在
			// System.out.println("不存在当前ip，直接存储");
			ipService.saveIpData(new IpEntity(ip, idStr, GlobalConstant.SIMPLE_DATA_FORMAT.format(new Date())));
		}
	}

	@ResponseBody
	@RequestMapping("/refresh")
	public synchronized String disposeH5(HttpServletRequest req, HttpServletResponse resp, String id, String ke) {
		long start = System.currentTimeMillis();
		//AVOSCloud.initialize("4Up0YGJ7Db9r7kTLcI508U1C-gzGzoHsz", "ePnsJkcfcP2x2Szjaxu8ozPc","WymmqLWltOjEEm5q9cHs8JbV");
		String resultCode = "";
		String idStr = id;
		String keStr = ke;
		Boolean isIpBlank = ipShield(req);
		if (isIpBlank) {
			System.out.println("ip被屏蔽");
			resultCode = "500";
			return resultCode;
		}
		try {
			//System.out.println("获取到传递参数id: " + idStr + ",ke: " + keStr + ",thread: " + Thread.currentThread().getName());
			if (StringUtil.isEmpty(idStr) || StringUtil.isEmpty(keStr)) {
				//System.out.println("非法请求，缺少必备参数！");

				resultCode = "500";
			} else {
				boolean b = configService.idIsExists(idStr);

				//System.out.println("是否配置id: " + b);
				if (!b) {
					//System.out.println("不存在当前渠道：" + idStr);
					resultCode = "405";

				} else {
					// 获取ip
					String ip = IpUtil.getIpAddr(req);
					//System.out.println("Servlet 多级获取ip: " + ip);
					if (!StringUtil.isEmpty(ip)) {
//						Boolean flag=ipService.selectFlag();
//						if (flag) {
//							saveIP(ip, idStr);
//						}
					} else {
						//System.out.println("获取Ip失败");
					}
					// 下发数据
					try {
						resultCode = pullData(idStr);
					} catch (Exception e) {
						//System.out.println("数据库/加密异常");
						return "405";

					}
				}
			}

			//System.out.println("当次请求完成，耗时(毫秒)：" + (System.currentTimeMillis() - start));

		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("服务器异常h5");
		}
		return resultCode;
	}

	/**
	 * 下发返回数据 逻辑 方法
	 * 
	 * @param printWriter
	 * @param idStr
	 */
	private String pullData(String idStr) {
		ConfigEntity configEntity = configService.selectConfigData(idStr);

		String resultCode = "";
		if (configEntity != null) {
			boolean isOpen = configEntity.on_off();
			String s1 = GlobalConstant.SWITCH_OFF;
			String s2 = GlobalConstant.NULL;
			String s3 = GlobalConstant.NULL;
			if (!isOpen) {
				//System.out.println("当前开关状态关闭！");
			} else {
				s1 = GlobalConstant.SWITCH_ON;
				s2 = configEntity.getData();
				s3 = configEntity.getReserve();
			}
			JSONObject result = new JSONObject();
			result.put(ON_OR_OFF_KEY, s1);
			result.put(H5_URL_KEY, s2);
			result.put(H5_RESERVE_KEY, s3);
			String data = result.toString();
			data = AES.encrypt(data, AES_KEY);
			if (data == null) {
				//System.out.println("加密密文为空");
				resultCode = "405";
			} else {
				//System.err.println(AES.decrypt(data, AES_KEY));
				resultCode = data;
			}
		}
		return resultCode;
	}
	
	public Boolean ipShield(HttpServletRequest request) {
		String ip = IpUtil.getIpAddr(request);
		System.out.println("ip检测："+ip);
		List<? extends AVObject> ipRangeList = ipShieldService.selectIpRange();
		for (AVObject avObject : ipRangeList) {
			if (ip.indexOf(avObject.getString("ipRange")) >= 0) {
				return true;
			}
		}
		Integer code = ipShieldService.selectByIp(ip);	
		if (code != null && code > 0) {
			return true;
		}
		
		String ipAddress=IpQueryApi.getAddress(ip);
		System.out.println(ipAddress);

		if ("北京".indexOf(ipAddress)!=-1 || "河北".indexOf(ipAddress)!=-1) {
			return true;
		}
		return false;
	}

}

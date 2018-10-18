package com.se.entity;

import java.io.Serializable;


/**
 * ip表 实体类
 * @author shuiming.tang
 * @date 2018-07-16
 * */
public class IpEntity implements Serializable{
	
	private static final long serialVersionUID = -6921597194766793226L;
	
	/**请求的客户端的ip地址*/
	private String ip;
	/**配置id*/
	private String id;
	/**存储到数据库中的时间 格式：yyyy-MM-dd HH:mm:ss*/
	private String time;
	
	public IpEntity() {
		
	}

	public IpEntity(String ip, String id) {
		super();
		this.ip = ip;
		this.id = id;
	}
	
	public IpEntity(String ip, String id, String time) {
		super();
		this.ip = ip;
		this.id = id;
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == this) {
			return true;
		}
		if(obj instanceof IpEntity) {
			IpEntity other = (IpEntity)obj;
			return this.ip.equals(other.ip) && this.id.equals(other.id)
					&& this.time.equals(other.time);
		}
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.ip.hashCode()*1000 + this.id.hashCode() + this.time.hashCode()*101;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Ip表对象数据：ip=%s,id=%s,time=%s", ip, id, time);
	}
	

}

package com.se.entity;

import java.io.Serializable;


/**
 * 配置表 实体类
 * @author shuiming.tang
 * @date 2018-07-16
 * */
public class ConfigEntity implements Serializable{

	private static final long serialVersionUID = -8905971706043846978L;
	/**配置id*/
	private String id;
	/**是否打开开关 true: 打开 默认: false*/
	private boolean on_off;
	/**具体开关、链接数据*/
	private String data;
	/**备用*/
	private String reserve;
	
	public ConfigEntity() {
		
	}
	
	public ConfigEntity(String id, boolean on_off) {
		super();
		this.id = id;
		this.on_off = on_off;
	}
	
	public ConfigEntity(String id, boolean on_off, String data) {
		super();
		this.id = id;
		this.on_off = on_off;
		this.data = data;
	}

	
	
	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public boolean isOn_off() {
		return on_off;
	}

	public void setOn_off(boolean on_off) {
		this.on_off = on_off;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean on_off() {
		return on_off;
	}
	public void setOn(boolean on_off) {
		this.on_off = on_off;
	}
	
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		if(obj instanceof ConfigEntity) {
			ConfigEntity other = (ConfigEntity)obj;
			return other.id.equals(this.id) && other.on_off == this.on_off && other.data.equals(this.data);
		}
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.id.hashCode()*1000 + (this.on_off?113*100 : 111*100) + this.data.hashCode()*111;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("配置表对象数据：id=%s,on_off=%b,data=%s", id, on_off, data);
	}
	
	
}

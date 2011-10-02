package com.jchatting.db.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-17 下午3:08:20
 *
 */
public class Group implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "groups";
	
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String CREATETIME = "createtime";
	public static final String INFO = "info";
	public static final String MEMO = "memo";
	
	private int id;
	private String name;
	private Timestamp createtime;
	private String info;
	private String memo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

package com.jchatting.db.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-17 下午3:08:27
 *
 */
public class User implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME = "users";
	
	public static final String ID = "id";
	public static final String ACCOUNT = "account";
	public static final String PASSWORD = "password";
	public static final String SEX = "sex";
	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String VALIDATED = "validated";
	public static final String ONLINE = "online";
	public static final String FORBIDDEN = "forbidden";
	public static final String REGTIME = "regtime";
	public static final String INFO = "info";
	public static final String MEMO = "memo";
	
	private int id;
	private String account;
	private String password;
	private String sex;
	private String name;
	private String email;
	private boolean validated;
	private boolean online;
	private boolean forbidden;
	private Timestamp regtime;
	private String info;
	private String memo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public boolean isForbidden() {
		return forbidden;
	}
	public void setForbidden(boolean forbidden) {
		this.forbidden = forbidden;
	}
	public Timestamp getRegtime() {
		return regtime;
	}
	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
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

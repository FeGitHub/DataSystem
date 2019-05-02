package com.DS.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setAccount(java.lang.String account) {
		set("account", account);
	}
	
	public java.lang.String getAccount() {
		return getStr("account");
	}

	public void setPassword(java.lang.String password) {
		set("password", password);
	}
	
	public java.lang.String getPassword() {
		return getStr("password");
	}

	public void setMail(java.lang.String mail) {
		set("mail", mail);
	}
	
	public java.lang.String getMail() {
		return getStr("mail");
	}

	public void setUserFlag(java.lang.Integer userFlag) {
		set("userFlag", userFlag);
	}
	
	public java.lang.Integer getUserFlag() {
		return getInt("userFlag");
	}

	public void setLeve(java.lang.Integer leve) {
		set("leve", leve);
	}
	
	public java.lang.Integer getLeve() {
		return getInt("leve");
	}

	public void setTaskAnalyse(java.lang.String taskAnalyse) {
		set("taskAnalyse", taskAnalyse);
	}
	
	public java.lang.String getTaskAnalyse() {
		return getStr("taskAnalyse");
	}

	public void setCustomizeAnalyse(java.lang.String customizeAnalyse) {
		set("customizeAnalyse", customizeAnalyse);
	}
	
	public java.lang.String getCustomizeAnalyse() {
		return getStr("customizeAnalyse");
	}

}

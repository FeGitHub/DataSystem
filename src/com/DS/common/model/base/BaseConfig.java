package com.DS.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseConfig<M extends BaseConfig<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setConfig(java.lang.String config) {
		set("config", config);
	}
	
	public java.lang.String getConfig() {
		return getStr("config");
	}

	public void setParameter1(java.lang.String parameter1) {
		set("parameter1", parameter1);
	}
	
	public java.lang.String getParameter1() {
		return getStr("parameter1");
	}

	public void setParameter2(java.lang.String parameter2) {
		set("parameter2", parameter2);
	}
	
	public java.lang.String getParameter2() {
		return getStr("parameter2");
	}

}

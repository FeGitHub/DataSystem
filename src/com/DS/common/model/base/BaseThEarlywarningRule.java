package com.DS.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseThEarlywarningRule<M extends BaseThEarlywarningRule<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setEarlywarningName(java.lang.String earlywarningName) {
		set("earlywarning_name", earlywarningName);
	}
	
	public java.lang.String getEarlywarningName() {
		return getStr("earlywarning_name");
	}

	public void setEarlywarningType(java.lang.Integer earlywarningType) {
		set("earlywarning_type", earlywarningType);
	}
	
	public java.lang.Integer getEarlywarningType() {
		return getInt("earlywarning_type");
	}

	public void setRangeMax(java.lang.Integer rangeMax) {
		set("range_max", rangeMax);
	}
	
	public java.lang.Integer getRangeMax() {
		return getInt("range_max");
	}

	public void setRangeMin(java.lang.Integer rangeMin) {
		set("range_min", rangeMin);
	}
	
	public java.lang.Integer getRangeMin() {
		return getInt("range_min");
	}

	public void setMaterialClassId(java.lang.Long materialClassId) {
		set("material_class_id", materialClassId);
	}
	
	public java.lang.Long getMaterialClassId() {
		return getLong("material_class_id");
	}

	public void setTransactors(java.lang.String transactors) {
		set("transactors", transactors);
	}
	
	public java.lang.String getTransactors() {
		return getStr("transactors");
	}

	public void setState(java.lang.Integer state) {
		set("state", state);
	}
	
	public java.lang.Integer getState() {
		return getInt("state");
	}

	public void setOperTime(java.util.Date operTime) {
		set("oper_time", operTime);
	}
	
	public java.util.Date getOperTime() {
		return get("oper_time");
	}

	public void setOperId(java.lang.Long operId) {
		set("oper_id", operId);
	}
	
	public java.lang.Long getOperId() {
		return getLong("oper_id");
	}

}
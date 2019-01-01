package com.DS.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseTask<M extends BaseTask<M>> extends Model<M> implements IBean {

	public void setTaskId(java.lang.Integer taskId) {
		set("taskId", taskId);
	}
	
	public java.lang.Integer getTaskId() {
		return getInt("taskId");
	}

	public void setTaskName(java.lang.String taskName) {
		set("taskName", taskName);
	}
	
	public java.lang.String getTaskName() {
		return getStr("taskName");
	}

	public void setAddTime(java.util.Date addTime) {
		set("addTime", addTime);
	}
	
	public java.util.Date getAddTime() {
		return get("addTime");
	}

}
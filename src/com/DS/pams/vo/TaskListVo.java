package com.DS.pams.vo;

public class TaskListVo {
	private String startDates;
	private String endDates;
	private String taskName;
	private String taskType;
	
	
   public String getStartDates() {
		return startDates;
	}
	public String getTaskType() {
	return taskType;
}
public void setTaskType(String taskType) {
	this.taskType = taskType;
}
	public void setStartDates(String startDates) {
		this.startDates = startDates;
	}
	public String getEndDates() {
		return endDates;
	}
	public void setEndDates(String endDates) {
		this.endDates = endDates;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
   
}

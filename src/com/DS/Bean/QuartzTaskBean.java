package com.DS.Bean;

public class QuartzTaskBean {
	  private String jobName;
	  private String jobGroup;
	  private String triggerName;
	  private String triggerGroup;
	  private String dateStr;
	  private String description="";
 

public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getTriggerGroup() {
		return triggerGroup;
	}
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
 
}

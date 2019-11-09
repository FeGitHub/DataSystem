package com.DS.pams.vo;

import java.util.Date;

public class TaskVo {
   private String taskName;
   
   private String description;
   
   public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getTaskName() {
	return taskName;
}
public void setTaskName(String taskName) {
	this.taskName = taskName;
}
public int getGoal() {
	return goal;
}
public void setGoal(int goal) {
	this.goal = goal;
}
public Date getDeadline() {
	return deadline;
}
public void setDeadline(Date deadline) {
	this.deadline = deadline;
}
private int goal;
   private Date deadline;
   
}

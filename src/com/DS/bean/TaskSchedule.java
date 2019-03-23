package com.DS.bean;

public class TaskSchedule {
	private int tasksize;//工程内部任务总数
    private int underway;//正在进行
    private int done;//已完成
    private int undone;//未开始
    public int getTasksize() {
		return tasksize;
	}
	public void setTasksize(int tasksize) {
		this.tasksize = tasksize;
	}
	public int getUnderway() {
		return underway;
	}
	public void setUnderway(int underway) {
		this.underway = underway;
	}
	public int getDone() {
		return done;
	}
	public void setDone(int done) {
		this.done = done;
	}
	public int getUndone() {
		return undone;
	}
	public void setUndone(int undone) {
		this.undone = undone;
	}
	
}

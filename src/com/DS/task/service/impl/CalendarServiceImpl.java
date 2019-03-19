package com.DS.task.service.impl;

import com.DS.bean.TaskCalendarBean;
import com.DS.common.model.Task;
import com.DS.task.service.CalendarService;

public class CalendarServiceImpl implements CalendarService {
    public  TaskCalendarBean convert(Task task){
    	TaskCalendarBean bean=new TaskCalendarBean();
    	 bean.setId(task.getTaskId());
		 bean.setStart(task.getStart());
		 bean.setEnd(task.getEnd());
		 bean.setTitle(task.getTaskName());
		 bean.setId(task.getUserId());
    	 return bean;
    }
    
    @Override
	public Task createTask(Task task) {
		if(task!=null){
			task.save();
			return task;
		}
		return null;
	}   	
}

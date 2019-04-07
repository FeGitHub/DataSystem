package com.DS.task.service.impl;
import com.DS.bean.TaskCalendarBean;
import com.DS.common.model.Task;
import com.DS.task.service.CalendarService;
public class CalendarServiceImpl implements CalendarService {
	/****
	 * 将task类型转换成TaskCalendarBean
	 */
    public  TaskCalendarBean convert(Task task){
    	TaskCalendarBean bean=new TaskCalendarBean();   	
    	 bean.setId(task.getTaskId());
    	 bean.setTitle(task.getTaskName());
		 bean.setStart(task.getStart());
		 if(task.getEnd()!=null){
			 bean.setEnd(task.getEnd());
		 }				
		 if(task.getDescription()!=null){
			bean.setDescription(task.getDescription());
		 }
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

package com.DS.task.service;

import com.DS.bean.TaskCalendarBean;
import com.DS.common.model.Task;

public interface CalendarService {
	TaskCalendarBean convert(Task task);
	
	 
    Task createTask(Task task);
}

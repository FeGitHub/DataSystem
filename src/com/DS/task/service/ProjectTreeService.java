package com.DS.task.service;

import java.util.List;
import java.util.Map;

import com.DS.bean.TaskSchedule;
import com.DS.common.model.ProjectTree;

public interface ProjectTreeService {
	List<ProjectTree> getTodayTreeTask(String userId);
	
	/****
	 * 获取工程任务树信息
	 * @param projectId
	 * @param userId
	 * @return
	 */
	Map<String,Object> getProjectTree(String projectId,String userId); 
	
	TaskSchedule getTaskSchedule(String projectId,String userId);
}

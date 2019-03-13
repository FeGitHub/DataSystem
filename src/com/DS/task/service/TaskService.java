package com.DS.task.service;

import java.util.Map;

import com.DS.common.model.Project;
import com.alibaba.fastjson.JSONArray;

public interface TaskService {
  /****
   * 得到任务列表数据
   * @param cond
   * @return
   */
    Map<String, Object> getTargetTaskList(Map<String,Object> cond);
    
    Map<String, Object> getProjectList(Map<String,Object> cond);
    
    JSONArray getProjectGantt(String userId);
    
    int createProject(Project project);
    
    int deleteProject(String projectId);
}

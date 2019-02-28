package com.DS.task.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;

public interface TaskService {
  /****
   * 得到任务列表数据
   * @param cond
   * @return
   */
    Map<String, Object> getTaskList(Map<String,Object> cond);
    
    JSONArray getProjectGantt(String projectId);
}

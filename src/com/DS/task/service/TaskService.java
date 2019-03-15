package com.DS.task.service;
import java.util.List;
import java.util.Map;
import com.DS.common.model.Project;
import com.DS.common.model.Task;
import com.alibaba.fastjson.JSONArray;

/****
 * 
 * @author jeff
 * 目标任务服务层
 */
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
    
    /***
     * 得到用户的当天的目标任务
     * @param userId
     * @return
     */
    List<Task> getTodayTarget(String userId);
}

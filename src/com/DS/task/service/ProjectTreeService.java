package com.DS.task.service;
import java.util.List;
import java.util.Map;
import com.DS.bean.TaskSchedule;
import com.DS.common.model.Project;
import com.DS.common.model.ProjectTree;

/****
 * 
 * @author jeff
 * 工程任务树服务层接口
 */
public interface ProjectTreeService {
	
	/***
	 * 得到用户今日的工程任务
	 * @param userId
	 * @return
	 */
	List<ProjectTree> getTodayTreeTask(String userId);
	
	/****
	 * 获取用户某个工程任务树信息
	 * @param projectId 
	 * @param userId
	 * @return
	 */
	Map<String,Object> getProjectTree(String projectId,String userId); 
	
	/****
	 * 获取用户某个工程的进度信息（已完成，正在进行，未开始）
	 * @param projectId
	 * @param userId
	 * @return
	 */
	TaskSchedule getTaskSchedule(String projectId,String userId);
	
	  /***
     * 创建新的工程
     * @param project
     * @return
     */
    int createProject(Project project);
}

package com.DS.pams.service;
import java.util.List;
import java.util.Map;
import com.DS.bean.TaskSchedule;
import com.DS.common.model.Project;
import com.DS.common.model.ProjectTree;
import com.jfinal.plugin.activerecord.Record;

/****
 * 
 * @author jeff
 * 工程任务树服务层接口
 */
public interface ProjectService {
	
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
    
    
    ProjectTree addProjectTask(ProjectTree projectTree);
    
    boolean isNodeHaveChild(int pId);
    
   
    
    ProjectTree updateProjectTask(ProjectTree projectTree,int taskId);
    
    boolean delProjectTasks(List<String> ids,int pId);
    
    /****
     * 得到工程分析数据
     * @return
     */
    List<Record>  getProjectAnalyse(String userId,String projectId,String actuallyFinshDate);
}

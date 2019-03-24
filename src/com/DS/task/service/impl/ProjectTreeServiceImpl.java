package com.DS.task.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.bean.TaskSchedule;
import com.DS.common.model.Project;
import com.DS.common.model.ProjectTree;
import com.DS.task.service.ProjectTreeService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
public class ProjectTreeServiceImpl implements ProjectTreeService {
  
	/****
	 * 得到用户今日应该开始或正在进行的工程任务
	 */
	@Override
	public List<ProjectTree> getTodayTreeTask(String userId) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("userId", userId);
		paramMap.put("dataLimit", true);
		SqlPara sql=Db.getSqlPara("projectTree.getProjectTreeTask", paramMap);
		ProjectTree dao=new ProjectTree();
		List<ProjectTree> projectTreeList=dao.find(sql);
		return projectTreeList;
	}
    
	
	/****
	 * 获取工程任务树信息
	 * @param projectId
	 * @param userId
	 * @return
	 */
	@Override
	public Map<String, Object> getProjectTree(String projectId, String userId) {
		 Map<String,Object> paramMap=new HashMap<String,Object>();
   	     paramMap.put("projectId", projectId);
   	     paramMap.put("userId", userId);
   	     SqlPara projectTreeSql=Db.getSqlPara("projectTree.getProjectById",paramMap);
		 List<Record> projectTree= Db.find(projectTreeSql);
		 Map<String,Object> json=new HashMap<String,Object>();
		 json.put("projectTree", projectTree);//工程任务信息
		 //处理详细信息
		 TaskSchedule taskSchedule=getTaskSchedule(projectId,userId);
		 json.put("done", taskSchedule.getDone());//已完成任务数量
		 json.put("underway", taskSchedule.getUnderway());//正在进行的任务数量
		 json.put("undone", taskSchedule.getUndone());//未进行的任务数量
		 return json;
	}

   /****
    * 得到工程任务的进度信息
    */
	@Override
	public TaskSchedule getTaskSchedule(String projectId, String userId) {
		 TaskSchedule taskSchedule=new TaskSchedule();
		 Map<String,Object> paramMap=new HashMap<String,Object>();
   	     paramMap.put("projectId", projectId);
   	     paramMap.put("userId", userId);
   	     SqlPara TaskScheduleSql=Db.getSqlPara("projectTree.getProjectSchedule",paramMap);
   	     Record record=Db.findFirst(TaskScheduleSql);   
   	    taskSchedule.setTasksize(record.getInt("tasksize"));
   	    taskSchedule.setUnderway(record.getInt("underway"));
   	    taskSchedule.setDone(record.getInt("done"));
   	    int undone=taskSchedule.getTasksize()-taskSchedule.getDone()-taskSchedule.getUnderway();
   	    taskSchedule.setUndone(undone);
		return taskSchedule;
	}
    
	
    /****
     * 创建新的工程任务
     * return id 新创建的工程任务的id
     */
	@Override
	public int createProject(Project project) {
		ProjectTree projectTree=new ProjectTree();
		boolean success=Db.tx(()->{
			project.save();
			Integer projectId=project.getId();			
			projectTree.setUserId(project.getUserId());
			projectTree.setTaskName(project.getProjectName());
			projectTree.setPId(0);
			projectTree.setProjectId(projectId);
			projectTree.save();
			return true;
		});
		if(success){
			return project.getId();
		}else{
			return -1;		
		}			
	}
}

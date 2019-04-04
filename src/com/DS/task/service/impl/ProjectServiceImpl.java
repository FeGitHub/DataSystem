package com.DS.task.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.bean.TaskSchedule;
import com.DS.common.model.Project;
import com.DS.common.model.ProjectTree;
import com.DS.common.model.Task;
import com.DS.task.service.ProjectService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
public class ProjectServiceImpl implements ProjectService {
  
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
     * 创建新的工程
     * return id 新创建的工程任务的id
     */
	@Override
	public int createProject(Project project) {
		ProjectTree projectTree=new ProjectTree();
		boolean success=Db.tx(()->{
			project.save();//工程信息保存
			Integer projectId=project.getId();			
			projectTree.setUserId(project.getUserId());
			projectTree.setTaskName(project.getProjectName());
			projectTree.setPId(0);
			projectTree.setProjectId(projectId);
			//任务保存
			Task task=projectTreeToTask(projectTree);
			task.setTaskType(2); 
			task.save();
			//工程树保存
			projectTree.setTaskId(task.getTaskId());
			projectTree.save();
		
		
			return true;
		});
		if(success){
			return project.getId();
		}else{
			return -1;		
		}			
	}


	Task projectTreeToTask(ProjectTree projectTree){
		Task task=new Task();
		task.setTaskName(projectTree.getTaskName());
		task.setDescription(projectTree.getDepiction());
		task.setStart(projectTree.getStartDate());
		task.setEnd(projectTree.getEndDate());
		task.setUserId(projectTree.getUserId());
		return task;
	}
	
	/****
	 * 增加工程任务
	 */
	@Override
	public ProjectTree addProjectTask(ProjectTree projectTree) {
		 Map<String,Object> paramMap=new HashMap<String,Object>();
		 paramMap.put("id", projectTree.getPId());
		 paramMap.put("taskType", 2);//父节点更新为父节点任务类型
		 SqlPara TaskScheduleSql=Db.getSqlPara("task.updateTaskType",paramMap);
		 Task task=projectTreeToTask(projectTree);
		 task.setTaskType(3);
		 boolean success=Db.tx(() -> {
			  task.save();	
			  projectTree.setTaskId(task.getTaskId());
			  projectTree.save();
			  Db.update(TaskScheduleSql);
			  return true;
			});
		 if(success){
			 return projectTree;
		 }else{
			 return null;
		 }		
	}

   /****
    * 判断节点是否有子节点
    */
	@Override
	public boolean isNodeHaveChild(int pId) {
		 Map<String,Object> paramMap=new HashMap<String,Object>();
		 paramMap.put("pId", pId);
		 SqlPara getChildsSql=Db.getSqlPara("projectTree.getChildsOfNode",paramMap);
		 Record record=Db.findFirst(getChildsSql);
		 int size=record.getInt("size");
		 if(size>0){
			 return true;
		 }else{
			 return false;
		 }
	
	}


 
/***
 * 更新工程任务
 * @param projectTree
 * @return
 */
 public ProjectTree updateProjectTask(ProjectTree projectTree,int taskId){
	 Task task=projectTreeToTask(projectTree);
	 task.setTaskId(taskId);
	 if(projectTree.getId()==null){
		 return null;
	 }
	 boolean success=Db.tx(() -> {
		 projectTree.update();
		 task.update();
		 return true;
		});
	 
	 if(success){
		 return projectTree;
	 }else{
		 return null;
	 }
		
}


@Override
public boolean delProjectTasks(List<String> ids,int pId) {
	 Map<String,Object> paramMap=new HashMap<String,Object>();
	 paramMap.put("ids", ids);
	 SqlPara delTask=Db.getSqlPara("projectTree.deleteTasksByProjectTasks",paramMap);	
	 SqlPara delProjectTask=Db.getSqlPara("projectTree.delProjectTasks",paramMap);	
	 boolean success=Db.tx(() -> {
	     Db.update(delTask);
	     Db.update(delProjectTask);
	     if(!isNodeHaveChild(pId)){//更新父节点任务类型
			 Map<String,Object> param=new HashMap<String,Object>();
			 param.put("id", pId);
			 param.put("taskType", 1);//父节点更新为父节点任务类型
			 SqlPara TaskScheduleSql=Db.getSqlPara("task.updateTaskType",param);
			 Db.update(TaskScheduleSql);
		 }
		 return true;
		});
	 return  success;
 }
  
}

package com.DS.task.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.common.model.Project;
import com.DS.common.model.Task;
import com.DS.task.service.TaskService;
import com.DS.utils.common.DataTablesUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.json.FastJson;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
/****
 * 
 * @author jeff
 * 任务的服务层实现类
 *
 */
public class TaskServiceImpl implements TaskService {
    
	/****
	 * 获取目标任务的分页数据
	 */
	@Override
	public Map<String, Object> getTargetTaskList(Map<String,Object> cond) {
		  SqlPara sqlShow = Db.getSqlPara("task.getTargetList",cond);
		  SqlPara sqlTotal = Db.getSqlPara("task.getTargetListSize",cond);      
		  Map<String,Object> map=DataTablesUtil.getPageData(sqlShow, sqlTotal);
          return map;
	}
	
	public Map<String, Object> getProjectList(Map<String,Object> cond) {
		  SqlPara sqlShow = Db.getSqlPara("projectTree.getPTaskList",cond);
		  SqlPara sqlTotal = Db.getSqlPara("projectTree.getPTaskListSize",cond);      
		  Map<String,Object> map=DataTablesUtil.getPageData(sqlShow, sqlTotal);
        return map;
	}
	
	
   
	
	/****
	 * 得到工程任务的甘特图展示数据
	 */
	@Override
	public JSONArray getProjectGantt(String userId) {
		Map<String,Object> cond=new HashMap<String,Object>();
		cond.put("userId", userId);
		SqlPara getUserSql = Db.getSqlPara("task.getUserByProjectId",cond);
		SqlPara getTaskSql = Db.getSqlPara("task.getTaskByProjectId",cond);   
		List<Record> userList= Db.find(getUserSql);
		List<Record> taskList= Db.find(getTaskSql);
		String userListJson=FastJson.getJson().toJson(userList);
		String taskListJson=FastJson.getJson().toJson(taskList);
        JSONObject hash = new JSONObject();
        JSONArray userArr= JSONArray.parseArray(userListJson);
        JSONArray taskArr= JSONArray.parseArray(taskListJson);
        for(int i=0;i<userArr.size();i++){//将数组转换成键值对，自身节点为key,自身为value
		      JSONObject json = (JSONObject) userArr.get(i);
		      hash.put(json.getString("id"), json);
		   }
        for(int i=0;i<taskArr.size();i++){
        	 JSONObject aVal = (JSONObject) taskArr.get(i);//从中取一条数据
        	 String user_Id= aVal.get("id").toString();
        	 JSONObject hashVP = (JSONObject) hash.get(user_Id);        	 
        	 if(hashVP.get("series")!=null){
        		 JSONArray ch = (JSONArray)hashVP.get("series");
        		 ch.add(aVal);
        		 hashVP.put("series", ch);
        	 }else{
        		 JSONArray ch = new JSONArray();
		         ch.add(aVal);
		         hashVP.put("series", ch);
        	 }
        }  
        JSONArray r = new JSONArray();
        for(int i=0;i<userArr.size();i++){
        	 JSONObject json = (JSONObject) userArr.get(i);
        	 JSONObject obj=(JSONObject) hash.get(json.getString("id"));
        	 r.add(obj);
        }
		return r;
	}

    /****
     * 创建新的工程任务
     * return id 新创建的工程任务的id
     */
	@Override
	public int createProject(Project project) {
		Record record = new Record();
		record.set("projectName", project.getProjectName());
		record.set("userId", project.getUserId());
		record.set("userName", project.getUserName());
		record.set("planStartDate", project.getPlanStartDate());
		record.set("plantFinshDate", project.getPlantFinshDate());
		Db.save("project",record); 
		int id=record.getInt("id");
		return id;
			
	}
   
	
	/*****
	 * 删除工程任务
	 */
	@Override
	public int deleteProject(String projectId) {
		 Map<String,Object> delMap=new HashMap<String,Object>();
		 delMap.put("projectId", projectId);
		 SqlPara delProjectTree=Db.getSqlPara("projectTree.deleteAll", delMap);	
		 SqlPara delProject=Db.getSqlPara("projectTree.deleteProject", delMap);	
		  if(!Db.tx(() -> {
			  Db.update(delProjectTree);
			  Db.update(delProject);			
			  return true;
			})){
			  return 0;
		  }
		   return 1;
	}
   
	/****
	 * 获取用户当日的目标任务
	 */
	@Override
	public List<Task> getTodayTarget(String userId) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("userId", Integer.parseInt(userId));
		SqlPara sql=Db.getSqlPara("task.getTodayTask", paramMap);
		Task dao=new Task();
		List<Task> taskList=dao.find(sql);
		return taskList;		
	}   		
}

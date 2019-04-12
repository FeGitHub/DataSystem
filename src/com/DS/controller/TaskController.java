 package com.DS.controller;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.bean.TaskCalendarBean;
import com.DS.common.model.Project;
import com.DS.common.model.ProjectTree;
import com.DS.common.model.Task;
import com.DS.common.model.User;
import com.DS.task.service.CalendarService;
import com.DS.task.service.ProjectService;
import com.DS.task.service.TaskService;
import com.DS.task.service.impl.CalendarServiceImpl;
import com.DS.task.service.impl.ProjectServiceImpl;
import com.DS.task.service.impl.TaskServiceImpl;
import com.DS.task.vo.ProjectListVo;
import com.DS.task.vo.TaskListVo;
import com.DS.utils.common.ObjectUtil;
import com.DS.utils.common.TimeUtil;
import com.DS.web.base.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
/****
 * 
 * @author jeff
 *  任务控制器
 */
public class TaskController extends BaseController{
	@Inject(TaskServiceImpl.class)
	private TaskService taskService;
	
	@Inject(ProjectServiceImpl.class)
	private ProjectService projectService;
	
	
	@Inject(CalendarServiceImpl.class)
	private CalendarService calendarService;
	
	/***
	 * 跳转到日历任务页面
	 */
	  public void goTaskCalendar(){
    	  render("taskCalendar.jsp");
      }
	
	
	
	  /***
	   *  跳转到任务列表
	   */
       public void goTargetList(){   	 
    	  render("targetList.jsp");
       }
       
       /****
        * 获取任务列表详情
        */
       public void getTargetList(){	
    	TaskListVo vo=getBean(TaskListVo.class,"");
    	vo.setTaskType("1");//普通任务列表
   		Map<String,Object> limit=ObjectUtil.convertBeanToMap(vo);
   		limit=getDivPageParam(limit);		
        renderJson(taskService.getTargetTaskList(limit));
   		
   	}
       
     /****
   	 * 删除目标任务
   	 */
   	public void delTarget(){
   		String taskId=getPara("taskId");
   		if(taskId==null||"".equals(taskId)){
   			renderJson(ajaxDoneError("事务主键为空"));
   			return;
   		}
   		Map<String,Object> paramMap=new HashMap<String,Object>();
   		paramMap.put("taskId",taskId );
   		SqlPara delsql=Db.getSqlPara("task.delById",paramMap);
   		int result=Db.update(delsql);
   		if(result>0){
   			renderJson(ajaxDoneSuccess("删除成功"));
   		}else{
   			renderJson(ajaxDoneError("删除失败"));
   		}
   	}
   	
   	/**
	 *  跳转到创建新的调度器任务页面
	 */
	public void goCreateTarget(){
		render("createTarget.jsp");
	}	
	
	/****
	 * 跳转到工程任务树页面
	 */
	public void goProjectTree(){
		 User nowUser = (User)getSession().getAttribute("user");
		 String projectId=getPara("projectId");
		 Project project=new Project();
 		 project=project.findById(projectId); 		  
         //工程总计划时间
 		 String suminfo=TimeUtil.getDatePoor(project.getFinshDate(), project.getStartDate());
 		 long projectTime=project.getFinshDate().getTime()-project.getStartDate().getTime();
 		 String remindinfo;
 	     if(TimeUtil.underway(project.getFinshDate(), project.getStartDate())){//目前在工程时间内
 	        long difftime=project.getFinshDate().getTime()-new Date().getTime();
 	        remindinfo=TimeUtil.getDatePoor(project.getFinshDate(), new Date());
 	    	NumberFormat numberFormat = NumberFormat.getInstance();   
 	    	numberFormat.setMaximumFractionDigits(2); 
 	    	String pct = numberFormat.format((float)difftime/(float)projectTime*100);
 	    	setAttr("remindDay","剩余"+remindinfo);
 	    	setAttr("pct",pct+"%"); 
 	    	setAttr("pnum",pct); 
 	     }else if(new Date().getTime()>project.getStartDate().getTime()){
 	    	setAttr("remindDay","已结束");
 	    	setAttr("pct","0%"); 
 	     }else{
 	    	 setAttr("remindDay","此工程计划时间为"+suminfo+"，目前未到开始时间");
 	    	 setAttr("pct","100%");
 	     }
 	    setAttr("projectId",projectId);
		setAttr("userId",nowUser.getId()+"");
		render("projectTree.jsp");
	}	
	
	/**
	 *  跳转到目标任务详情页面
	 */
	public void goTargetDetail(){
		 Task task=new Task();
		 if(getPara("taskId")==null||"".equals(getPara("taskId"))){
			 return;
		 }
		 task=task.findById(getPara("taskId"));		
		 setAttr("vo",task);
		 render("targetDetail.jsp");
	}	
	
	
	/***
	 * 创建目标任务
	 */
	 public void createTarget(){		
		  Task vo=getModel(Task.class,"");
		  User nowUser = (User)getSession().getAttribute("user");
		  vo.setUserId(nowUser.getId());
		  boolean result=vo.save();
		  if(result){
				renderJson(ajaxDoneSuccess("操作成功"));
			}else{
				renderJson(ajaxDoneError("操作失败"));
			}		 	   						 		   
	 }
	 
	 /*****
	  * 获取用户的工程任务甘特图
	  */
     public void getProjectGantt(){
    	 User user = (User)getSession().getAttribute("user");
    	 JSONArray r=taskService.getProjectGantt(user.getId()+"");
    	 renderJson(r);
     }
     
     
     
     public void goProjectList(){
      	 render("projectList.jsp");
        }
     
     /****
      * 创建新的工程
      */
     public void createProject(){   	 
    	 User user = (User)getSession().getAttribute("user");
    	 Project project=getModel(Project.class,"");
    	 project.setUserId(user.getId());
    	 project.setUserName(user.getAccount());
    	 int projectId=projectService.createProject(project);
    	 if(projectId>0){
    		 Map<String,Object> result=new HashMap<String,Object>();
    		 result.put("projectId", projectId) ; 
    		 renderJson(ajaxDoneSuccess(result));
    	 }else{
    		 renderJson(ajaxDoneError("操作失败"));
    	 } 	 
     }
     
     /****
      * 提交工程任务数据
      */
     public void submitProject(){	
		  String pTaskJson=getPara("pTaskJson");
		  List<ProjectTree> pTaskList= JSON.parseArray(pTaskJson,ProjectTree.class); 
		  ProjectTree projectTree=pTaskList.get(0);
		  Map<String, List<ProjectTree>> map=new HashMap<String, List<ProjectTree>>();
		  map.put("cond", pTaskList);
		  SqlPara updateSql=Db.getSqlPara("projectTree.insertDataBatch", map);	
		  Map<String,Object> delMap=new HashMap<String,Object>();
		  delMap.put("projectId",projectTree.getProjectId());
		  SqlPara deleteSql=Db.getSqlPara("projectTree.deleteAll", delMap);	
		  Db.tx(() -> {
			  Db.update(deleteSql);
			  Db.update(updateSql);			
			  return true;
			}); 
		  renderJson(ajaxDoneSuccess("操作成功"));  
   }
     
     /****
      * 获取工程任务列表数据
      */
     public void getProjectList(){
    	 ProjectListVo vo=getBean(ProjectListVo.class,"");
 		 Map<String,Object> limit=ObjectUtil.convertBeanToMap(vo);
 		 limit=getDivPageParam(limit);   	
		 renderJson(taskService.getProjectList(limit));
     }
     
     /*****
      * 获取用户的工程任务树的数据
      */
     public void getProjectTree(){
    	 User user = (User)getSession().getAttribute("user");
    	 String projectId=getPara("projectId");
    	 String userId=user.getId()+"";
    	 Map<String,Object> json=projectService.getProjectTree(projectId, userId); 		
    	 renderJson(json);   	 
     }
     
     /****
      * 删除工程
      */
     public  void delProject(){
    	 String projectId=getPara("projectId");
    	 if(taskService.deleteProject(projectId)>0){
    		 renderJson(ajaxDoneSuccess("删除成功"));
    	 }else{
    		 renderJson(ajaxDoneError("删除失败"));
    	 }
     }
     
     /*****
      * 获取日历任务信息
      */
     public void getTaskCalendar(){
		 User user = (User)getSession().getAttribute("user");
		 Map<String,Object> paramMap=new HashMap<String,Object>();
    	 paramMap.put("userId", user.getId());
    	 SqlPara sql=Db.getSqlPara("task.getTaskCalendar",paramMap);
 		 List<Record> projectTree= Db.find(sql);		
 		 renderJson(projectTree);   	 
	}
     
     /***
      * 更新日历任务
      */ 
     public void updateTaskCalendar(){
    	 Task task=getModel(Task.class,"");
    	 if(task.getTaskId()==null){
    		 renderJson(ajaxDoneError("更新主键为null"));
    		 return;   		 
    	 }
    	 Map<String,Object> paramMap=ObjectUtil.convertBeanToMap(task);	
    	 SqlPara sql=Db.getSqlPara("task.updateTaskCalendar",paramMap);
    	 int result=Db.update(sql);
    	 if(result>0){
    		 TaskCalendarBean bean=calendarService.convert(task); 
    		 Map<String,Object> map=new HashMap<String,Object>();
    		 map.put("task", bean);
    		 map.put("msg", "更新成功");
    		 renderJson(ajaxDoneSuccess(map));
    	 }else{
    		 renderJson(ajaxDoneError("更新失败"));
    	 }

     }
     
     public void updateTask(){
    	 Task task=getModel(Task.class,"");
    	 boolean result=task.update();
    	 if(result){
    		 renderJson(ajaxDoneSuccess("更新成功"));
    	 }else{
    		 renderJson(ajaxDoneError("更新失败"));
    	 }
     }
     
     
     
     
     /****
      * 添加日历任务
      */
     public void addTaskCalendar(){
    	 User user = (User)getSession().getAttribute("user");
    	 Task task=getModel(Task.class,"");	
    	 task.setUserId(user.getId());
    	 task=calendarService.createTask(task);
    	 if(task!=null){
    		 TaskCalendarBean bean=calendarService.convert(task); 
    		 Map<String,Object> map=new HashMap<String,Object>();
    		 map.put("task", bean);
    		 map.put("msg", "成功新建");
    		 renderJson(ajaxDoneSuccess(map));
    	 }else{
    		 renderJson(ajaxDoneError("操作失败"));
    	 }
     }
     
     
     /****
      * 修改工程任务
      */
     public void updateProjectTask(){
    	 User user = (User)getSession().getAttribute("user");
    	 ProjectTree projectTree=getModel(ProjectTree.class,"");
    	 if(projectTree.getId()==null){
    		 renderJson(ajaxDoneError("修改失败，主键为NULL"));
    		 return;
    	 }
    	 ProjectTree temp=projectTree.findById(projectTree.getId()); 
    	 projectTree.setUserId(user.getId());
    	 projectTree=projectService.updateProjectTask(projectTree, temp.getTaskId());
    	 if(projectTree!=null){
    		 renderJson(ajaxDoneSuccess("修改成功"));
    	 }else{
    		 renderJson(ajaxDoneError("修改失败"));
    	 }
     }
     
     /****
      * 新增工程任务
      */
     public  void addProjectTask(){
    	 Map<String,Object> map=new HashMap<String,Object>();
    	 ProjectTree projectTree=getModel(ProjectTree.class,"");
    	 projectTree=projectService.addProjectTask(projectTree);
    	 if(projectTree!=null){
    		 map.put("id", projectTree.getId());
    		 map.put("msg", "新增成功");
    		 renderJson(ajaxDoneSuccess(map));
    	 }else{
    		 renderJson(ajaxDoneError("新增失败"));
    	 }
     }
     
     /****
      * 删除工程任务
      */
     public  void delProjectTask(){
    	 String pId=getPara("pId");
    	 String ids=getPara("ids");
    	 if(pId==null||ids==null){
    		 renderJson(ajaxDoneError("删除失败，相关主键为NULL"));
    		 return;
    	 }
    	 List<String> list = Arrays.asList(ids.split(","));
    	 boolean success=projectService.delProjectTasks(list, Integer.parseInt(pId)); 
    	 if(success){   		
    		 renderJson(ajaxDoneSuccess("删除成功"));
    	 }else{
    		 renderJson(ajaxDoneError("删除失败"));
    	 }
     }
     
     /***
      * 获取用户未分配的任务
      */
     public void getUndisTasks(){
    	 User user = (User)getSession().getAttribute("user");
    	 Map<String,Object> cond=new HashMap<String,Object>();
    	 cond.put("userId", user.getId());
    	 SqlPara sql = Db.getSqlPara("task.getUndisTasks",cond);	
    	 Task dao=new Task();
    	 List<Task> tasks=dao.find(sql);
    	 renderJson(tasks);
      }
     
}

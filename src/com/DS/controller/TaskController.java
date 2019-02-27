package com.DS.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.DS.task.service.TaskService;
import com.DS.task.service.impl.TaskServiceImpl;
import com.DS.task.vo.TaskVo;
import com.DS.utils.common.ObjectUtil;
import com.DS.web.base.BaseController;
import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
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
	
	  /***
	   *  跳转到任务列表
	   */
       public void goTaskList(){
    	 
    	  render("taskList.jsp");
       }
       
       /****
        * 获取任务列表详情
        */
       public void getTaskList(){	
   		DivPageCondition=getDivPageCondition();
   		DivPageCondition.put("startDates", getPara("startDates"));
   		DivPageCondition.put("endDates", getPara("endDates"));
   		DivPageCondition.put("taskName", getPara("taskName"));				
        renderJson(taskService.getTaskList(DivPageCondition));
   		
   	}
       
     /****
   	 * 删除备忘事务
   	 */
   	public void delTask(){
   		Map<String,Object> paramMap=new HashMap<String,Object>();
   		paramMap.put("taskId", getPara("taskId"));
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
	public void goCreateTask(){
		render("createTask.jsp");
	}	
	
	/**
	 *  跳转到目标任务详情页面
	 */
	public void goTaskDetail(String taskId){
		 Record record=Db.findFirst("select * from task where taskId="+getPara("taskId"));
		 setAttr("vo",record);
		 render("taskDetail.jsp");
	}	
	
	
	/***
	 * 创建目标任务
	 */
	 public void createTask(){		
		  TaskVo vo=getBean(TaskVo.class,"");
		  Map<String,Object> paramMap=ObjectUtil.convertBeanToMap(vo);		
		  paramMap.put("addTime",new Date());
		  SqlPara insertSql=Db.getSqlPara("task.insertData",paramMap);
		  int result=Db.update(insertSql);
		  if(result>0){
				renderJson(ajaxDoneSuccess("操作成功"));
			}else{
				renderJson(ajaxDoneError("操作失败"));
			}		 	   						 		   
	 }
}

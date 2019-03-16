package com.DS.controller;
import com.DS.task.service.TaskService;
import com.DS.task.service.impl.TaskServiceImpl;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
/***
 * 
 * @author jeff
 * 为了方便跳转用于测试页面的控制器
 *
 */
public class GoController extends BaseController{	
	@Inject(TaskServiceImpl.class)
	private TaskService taskService;
	
		 public void goMenu(){					
		 	 render("main.jsp");
		 }
		 
		
		
		 @Clear
		 public void goTree(){
			   render("tree.jsp");
		 }
		
		
		 
		 @Clear
		 public void goValidate(){
			   render("validationEngine.jsp");
		 }	 
		
		 
		
	 
	   
	    
	    /***
	     * 测试弹出框
	     */
	    public void goComponent(){
	    	   render("component.jsp");
	    }
	    
	    
	    
}


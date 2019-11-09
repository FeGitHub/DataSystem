package com.DS.pams.web.controller;

import com.DS.pams.web.base.BaseController;

/***
 * 
 * @author jeff
 * 为了方便跳转用于测试页面的控制器
 *
 */
public class GoController extends BaseController{	
	
	public void goCsvData(){
		render("csvdata.jsp");
	}
		
	public void goDemo(){
		render("demo.jsp");
	}	      
	    
	
	public void goShow(){	
		render("showAnalyse.jsp");
	}
	
}


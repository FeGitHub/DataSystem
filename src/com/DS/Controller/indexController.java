package com.DS.Controller;

import com.DS.web.base.BaseController;
import com.jfinal.aop.Clear;

public class indexController extends BaseController{
	@Clear
	public void index(){
		 render("login.jsp");	 	  
	 }
}

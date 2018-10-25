package com.DS.Controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

public class indexController extends Controller{
	@Clear
	public void index(){
		 render("login.jsp");	 	  
	 }
}

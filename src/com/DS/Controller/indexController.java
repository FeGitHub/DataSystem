package com.DS.Controller;

import com.jfinal.core.Controller;

public class indexController extends Controller{
	public void index(){
		 render("login.html");
	 	  
	 }
	public void list(){
		 render("index.jsp");
	 }
}

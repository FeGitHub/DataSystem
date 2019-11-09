package com.DS.pams.web.controller;

import com.DS.bean.MenuInfo;
import com.DS.pams.service.MenuService;
import com.DS.pams.service.impl.MenuServiceImpl;
import com.DS.pams.web.base.BaseController;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;

public class indexController extends BaseController{
	@Inject(MenuServiceImpl.class)
	private MenuService menuService;
	
	@Clear
	public void index(){
		if(getSessionAttr("allMenuUrl")==null){
			 MenuInfo menuInfo =menuService.getTreeMenu(3);
			 setSessionAttr("allMenuUrl", menuInfo.getUrls());
		}		
		 render("login.jsp");	 	  
	 }
	
	@Clear
	public  void goRegister(){
		 render("register.jsp");
	}
}

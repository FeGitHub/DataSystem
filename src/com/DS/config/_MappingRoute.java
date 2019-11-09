package com.DS.config;

import com.DS.pams.web.controller.AnalyseController;
import com.DS.pams.web.controller.DemoController;
import com.DS.pams.web.controller.ExcelController;
import com.DS.pams.web.controller.FileController;
import com.DS.pams.web.controller.GoController;
import com.DS.pams.web.controller.LoginController;
import com.DS.pams.web.controller.MenuController;
import com.DS.pams.web.controller.QrtzController;
import com.DS.pams.web.controller.RemindController;
import com.DS.pams.web.controller.RemoteHTTPAction;
import com.DS.pams.web.controller.TaskController;
import com.DS.pams.web.controller.UserController;
import com.DS.pams.web.controller.indexController;
import com.DS.pams.web.controller.comm.InterfaceController;
import com.jfinal.config.Routes;
/******
 *  @author jeff
 *  路由映射
 */
public class _MappingRoute {
	public static void mapping(Routes me){
		    me.add("/", indexController.class);//默认处理
	        me.add("/go", GoController.class);//用于跳转页面
	        me.add("/login", LoginController.class);//登录处理
	        me.add("/user",UserController.class);//用户
	        me.add("/RemoteAction",RemoteHTTPAction.class);//提供给远程调用的action
	        me.add("/qrtz",QrtzController.class);//提供给远程调用的action
	        me.add("/remind",RemindController.class);//备忘提醒的相关处理	      
	        me.add("/task",TaskController.class);
	        me.add("/demo",DemoController.class);
	        me.add("/menu",MenuController.class);
	        me.add("/file",FileController.class);
	        me.add("/excel",ExcelController.class);
	        me.add("/analyse",AnalyseController.class);
	        me.add("/interface",InterfaceController.class);//接口，提供json数据
	}
}

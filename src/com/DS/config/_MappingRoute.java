package com.DS.config;
import com.DS.controller.DemoController;
import com.DS.controller.ExcelController;
import com.DS.controller.FileController;
import com.DS.controller.GoController;
import com.DS.controller.LoginController;
import com.DS.controller.MenuController;
import com.DS.controller.QrtzController;
import com.DS.controller.RemindController;
import com.DS.controller.RemoteHTTPAction;
import com.DS.controller.TaskController;
import com.DS.controller.UserController;
import com.DS.controller.indexController;
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
	}
}

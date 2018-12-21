package com.DS.Controller1;
import java.util.List;
import com.DS.web.base.BaseController;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/***
 * 
 * @author jeff qiu
 * 用户信息处理
 *s
 */
public class UserController extends BaseController {
	public void index(){
		 List<Record> blogs=Db.find("select * from user");
	        renderJson(blogs);//传递json数据
		System.out.println("UserController");
	}

}

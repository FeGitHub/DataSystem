package com.DS.Controller;
import java.util.List;
import com.DS.Model.UserModel;
import com.jfinal.core.Controller;
/***
 * 
 * @author jeff qiu
 * 用户信息处理
 *
 */
public class UserController extends Controller {
	public void index(){
		 List<UserModel> blogs=UserModel.dao.find("select * from user");
	        renderJson(blogs);//传递json数据
		System.out.println("UserController");
	}

}

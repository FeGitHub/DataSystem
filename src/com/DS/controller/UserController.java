package com.DS.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.DS.common.model.User;
import com.DS.user.service.UserService;
import com.DS.user.service.impl.UserServiceImpl;
import com.DS.user.vo.UserVo;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/***
 * 
 * @author jeff qiu
 * 用户信息处理
 *s
 */
public class UserController extends BaseController {
	@Inject(UserServiceImpl.class)
	private UserService userService;
	public void index(){
		 List<Record> blogs=Db.find("select * from user");
	        renderJson(blogs);//传递json数据
		System.out.println("UserController");
	}
	
	@Clear
	public void register(){		
		  UserVo user=getBean(UserVo.class,"");		
		  HttpSession session=getSession();
		  Object obj=session.getAttribute(user.getMail());
		  if(obj==null){			  
			  renderJson(ajaxDoneError("操作失败"));
			  return;
		  }
		  String userCode=(String)obj;
		  if(!userCode.equals(user.getFormCode())){
			  renderJson(ajaxDoneError("验证码错误"));
			  return;
		  }
		  if(userService.register(user)>0){
			  renderJson(ajaxDoneSuccess("操作成功"));
		  }else{
			  renderJson(ajaxDoneError("操作失败"));
		  }
	    
	}
	

}

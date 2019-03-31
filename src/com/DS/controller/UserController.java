package com.DS.controller;
import java.util.Map;
import javax.servlet.http.HttpSession;

import com.DS.common.model.User;
import com.DS.user.service.UserService;
import com.DS.user.service.impl.UserServiceImpl;
import com.DS.user.vo.UserListVo;
import com.DS.user.vo.UserVo;
import com.DS.utils.common.ObjectUtil;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
/***
 * 
 * @author jeff 
 * 用户信息处理
 *s
 */
public class UserController extends BaseController {
	@Inject(UserServiceImpl.class)
	private UserService userService;
	
	
	/*****
	 * 用户注册
	 */
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
	
	/***
	 * 跳转到用户列表
	 */
	public void goUserList(){
		render("userList.jsp");
	}
	
	/****
	 * 获取用户列表
	 */
	public void getUserList(){	
		UserListVo vo=getBean(UserListVo.class,"");
		Map<String,Object> limit=ObjectUtil.convertBeanToMap(vo);
		limit=getDivPageParam(limit);
        renderJson(userService.getUserList(limit));		
	}
	
	
	/****
	 * 更新用户信息
	 */
	public void updateUser(){
		User user=getModel(User.class,"");
		if(user.update()){
			renderJson(ajaxDoneSuccess("修改成功"));
		}else{
			renderJson(ajaxDoneError("修改成功"));
		}
	}
    
	
	 public void goMenu(){					
	 	 render("main.jsp");
	 }
}

package com.DS.pams.web.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import com.DS.common.model.Notification;
import com.DS.common.model.User;
import com.DS.pams.service.NotificationService;
import com.DS.pams.service.UserService;
import com.DS.pams.service.impl.NotificationServiceImpl;
import com.DS.pams.service.impl.UserServiceImpl;
import com.DS.pams.vo.UserListVo;
import com.DS.pams.vo.UserVo;
import com.DS.pams.web.base.BaseController;
import com.DS.utils.common.Util;
import com.DS.utils.common.SecretUtil;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Record;
/***
 * 
 * @author jeff 
 * 用户信息处理
 *s
 */
public class UserController extends BaseController {
	@Inject(UserServiceImpl.class)
	private UserService userService;
	
	
	@Inject(NotificationServiceImpl.class)
	NotificationService notificationService;
	
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
		Map<String,Object> limit=Util.convertBeanToMap(vo);
		limit=getDivPageParam(limit);
        renderJson(userService.getUserList(limit));		
	}
	
	
	/****
	 * 更新用户信息
	 */
	public void updateUser(){
		User user=getModel(User.class,"");
		if(user.getId()==null){
			 User nowUser = (User)getSession().getAttribute("user");
			 user.setId(nowUser.getId());
			 Object obj=getSession().getAttribute("pass");
			  if(obj==null){
				  renderJson(ajaxDoneError("非法修改"));
				  return;
			  }
		}
		if(user.getPassword()!=null){
			String password=SecretUtil.getMD5(user.getPassword());
			user.setPassword(password);;
		}			
		if(user.update()){
			renderJson(ajaxDoneSuccess("修改成功"));
		}else{
			renderJson(ajaxDoneError("修改失败"));
		}
	}
    
	/****
	 * 获取用户信息
	 */
	public void getUser(){
		String id=getPara("id");
		if(id==null||id==""){
			renderJson(ajaxDoneError());
			return;
		}
		User user=new User();
		user=user.findById(id);
		Map<String,Object> result=new HashMap<String,Object>();
		if(user!=null){
			result.put("user", user);
			result.put("msg", "修改成功");
			renderJson(ajaxDoneSuccess(result));
		}else{
			renderJson(ajaxDoneError());
		}
	}
	
	
	 public void goMenu(){					
		 render("user_taskCalendar.jsp");
	 }
	 
	       /***
			 * 跳转到日历任务页面
			 */
			  public void goUserTaskCalendar(){
		    	  render("user_taskCalendar.jsp");
		      }
		  
	     /***
		  * 跳转到信息详情
		  */
		public void goNotification(){
			 String id=getPara("id");
			 if(id==null||id==null){
				 return;
			 }
			 Notification n=new Notification();
			 n=n.findById(id);
			 setAttr("info",n);
			render("notification.jsp");
		}	
		
		/****
		 * 处理信息通知
		 */
		public void updateNotification(){
			 String id=getPara("id");
			 if(id==null||id==null){
				 renderJson(ajaxDoneError("主键为空，修改失败"));
				 return;
			 }
			 HttpSession session = getSession();
			 User user = (User)session.getAttribute("user");			
			 Notification n=new Notification();
			 n=n.findById(id);
			 n.setReadFlag(1);//设置为已处理		
			 if(n.update()){
				 //更新用户通知信息栏--只加载未处理数据
				 List<Record> notifications=notificationService.getNotification(user.getStr("id"),"limit");
				 Long notificationSize=notificationService.getNotificationSize(user.getStr("id"),"limit");
				 session.setAttribute("notifications", notifications);
				 session.setAttribute("notificationSize", notificationSize);
				 renderJson(ajaxDoneSuccess("处理成功"));
				}else{
					renderJson(ajaxDoneError("修改失败"));
			}
		}	
		
		/***
		 *  校验用户密码
		 */
		public void checkUser(){
			HttpSession session =getSession();
			 String pass=getPara("pass");
			 if(pass==null||pass==""){
				 renderJson(ajaxDoneError("校验失败"));
				 return;
			 }
			pass= SecretUtil.getMD5(pass);
			User user = (User)session.getAttribute("user");
			if(user!=null&&user.getPassword().equals(pass)){
				 session.setAttribute("pass", pass);
				 Map<String,Object> result=new HashMap<String,Object>();
				 result.put("mail", user.getMail());
				 result.put("msg", "校验成功");
				 renderJson(ajaxDoneSuccess(result));
			}else{
				renderJson(ajaxDoneError("校验失败"));
			}
		}
		
	/***
	 * 刷新用户通知信息
	 */
     public void refresh(){
    	 HttpSession session = getSession();
		 User user = (User)session.getAttribute("user");	
		 notificationService.refresh(session, user);
		 renderJson(ajaxDoneSuccess());
     }
}

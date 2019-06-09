package com.DS.controller;
import java.util.HashMap;
import java.util.Map;
import com.DS.common.model.User;
import com.DS.menu.service.MenuService;
import com.DS.menu.service.impl.MenuServiceImpl;
import com.DS.utils.common.ObjectUtil;
import com.DS.utils.common.SecretUtil;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
/****
 * 
 * @author jeff
 * 登陆登出控制器
 */
public class LoginController extends BaseController{
	@Inject(MenuServiceImpl.class)
	private MenuService menuService;
	
	/*
	 * 登陆验证
	 */
	@Clear
   public void index(){
	   User user=getModel(User.class,"");
	   if(user==null||user.getAccount()==null||user.getPassword()==null){
    	   renderJson(ajaxDoneError("账号或密码出错"));
    	   return;
       } 	
	   String password = SecretUtil.getMD5(user.getPassword());
       Map<String,Object> paramMap=ObjectUtil.convertBeanToMap(user);
       SqlPara sql=Db.getSqlPara("user.getUserInfoByAccount", paramMap);
       user= user.findFirst(sql); 
       if(user!=null&&user.getPassword().equals(password)){
    	     //用户信息
    	     setSessionAttr("user", user);      	   			
    	     Map<String,Object> map=new HashMap<String,Object>();
    	     map.put("url", "/user/goMenu");
    	     renderJson(ajaxDoneSuccess(map)); 
       }else{
    	     renderJson(ajaxDoneError("账号或密码出错"));
       } 
   }
	
   /***
    * 登出处理
    */
   public void signOut(){
	   getSession().invalidate();
	   forwardAction("/");	
   }
}

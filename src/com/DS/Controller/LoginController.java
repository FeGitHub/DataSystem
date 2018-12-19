package com.DS.Controller;
import com.DS.common.model.User;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
public class LoginController extends BaseController{
	/*
	 * 登陆验证
	 */
	@Clear
   public void index(){
	   String  account=getPara("username");
       String  password=getPara("password");
       String sql="select * from user where account=?";
       Record user=Db.findFirst(sql, account);
       if(user!=null&&user.getStr("password").equals(password)){
    	   setSessionAttr("user", user);
    	  redirect("/go/goMenu");//重定向	   
       }else{
    	   redirect("/test/goHello");	
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

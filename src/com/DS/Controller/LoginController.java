package com.DS.Controller;
import com.DS.Model.UserModel;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
public class LoginController extends Controller{
	/*
	 * 登陆验证
	 */
	@Clear
   public void index(){
	   String  account=getPara("username");
       String  password=getPara("password");
       String sql="select * from user where account=?";
       UserModel user=UserModel.dao.findFirst(sql, account);
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

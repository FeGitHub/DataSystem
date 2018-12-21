package com.DS.Controller1;
import java.util.HashMap;
import java.util.Map;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
public class LoginController extends BaseController{
	/*
	 * 登陆验证
	 */
	@Clear
   public void index(){
	   String  account=getPara("username");
       String  password=getPara("password");
       Map<String,Object> paramMap=new HashMap<String,Object>();
       paramMap.put("account", account);
       SqlPara sql=Db.getSqlPara("user.getUserInfoByAccount", paramMap);
       Record user=Db.findFirst(sql);
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

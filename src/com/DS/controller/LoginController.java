package com.DS.controller;
import java.util.HashMap;
import java.util.Map;
import com.DS.common.model.User;
import com.DS.utils.ObjectUtil;
import com.DS.utils.SecretUtil;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
/****
 * 
 * @author jeff
 * 登陆登出控制器
 */
public class LoginController extends BaseController{
	/*
	 * 登陆验证
	 */
	@Clear
   public void index(){
	   User user=getModel(User.class,"");
       Map<String,Object> paramMap=ObjectUtil.convertBeanToMap(user);
       SqlPara sql=Db.getSqlPara("user.getUserInfoByAccount", paramMap);
       Record record=Db.findFirst(sql);
   	   String password = SecretUtil.getMD5(user.getPassword());
       if(record!=null&&record.getStr("password").equals(password)){
    	   setSessionAttr("user", record);
    	   //redirect("/go/goMenu");//重定向	
    	   Map<String,Object> map=new HashMap<String,Object>();
    	   map.put("url", "/go/goMenu");
    	   renderJson(ajaxDoneSuccess(map));
       }else{
    	   //redirect("/test/goHello");	
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

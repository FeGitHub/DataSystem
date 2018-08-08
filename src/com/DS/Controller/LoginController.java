package com.DS.Controller;
import java.util.List;
import com.DS.Model.UserModel;
import com.jfinal.core.Controller;
public class LoginController extends Controller{
	/*
	 * 登陆验证
	 */
   public void index(){
	   String  account=getPara("username");
       String  password=getPara("password");
       String sql="select * from user where account='"+account+"'";
       List<UserModel> user=UserModel.dao.find(sql);      
     if((user.size()!=0)&&(user.get(0).getStr("password").equals(password))){
    	  redirect("/go/goMenu");//重定向	   
       }else{
    	   redirect("/go/goHello");	
       }
     
   }
}

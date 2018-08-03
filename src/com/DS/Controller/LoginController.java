package com.DS.Controller;

import java.util.List;

import com.DS.Model.TestModel;
import com.jfinal.core.Controller;

public class LoginController extends Controller{
   public void index(){
	   String  username=getPara("username");
       String  password=getPara("password");
       String sql="select * from blog where title='"+username+"'";
       List<TestModel> blogs=TestModel.dao.find(sql);  
     
     // if((blogs.size()!=0)&&(blogs.get(0).getStr("content").equals(password))){
    	  if(1==1){
    	  redirect("/go/goMenu");//重定向	   
       }else{
    	   redirect("/go/goHello");	
       }
     
   }
}

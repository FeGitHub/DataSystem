package com.DS.Controller;

import java.util.HashMap;
import java.util.Map;

import com.DS.Model.TestModel;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class BlogController extends Controller {
	public void index(){
		System.out.println("index");
	}
	public void editBlog(){
		String  id=getPara("id");
		Record record = new Record();
		record=Db.findById("blog",Integer.parseInt(id));
		setAttr("record",record);
		render("editBlog.html");
		//redirect("/go/goEditBlog");	
	}
	
	public void saveBlog(){
		   Map<String, String> data=new HashMap<String, String>();
		   String  id=getPara("id");
		   String  title=getPara("title");
	       String  content=getPara("content");
	       Record record = new Record();
	        record=Db.findById("blog",Integer.parseInt(id)).set("title",title).set("content",content);	     
	        if( Db.update("blog",record)){
	        	 data.put("info", "ok");
	        }else{
	        	 data.put("info", "no");
	        }	      
		   data.put("info", "ok");
	        renderJson(data);
	}
	public void delBlog(){
		 Map<String, String> data=new HashMap<String, String>();
		  String  id=getPara("id");
		 // Db.deleteById("blog",Integer.parseInt(id));
	      if( Db.deleteById("blog",Integer.parseInt(id))){
	    	   data.put("info", "ok");
	       }else{
	    	   data.put("info", "no");
	       }
	       renderJson(data);
	}
}

package com.DS.Controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class BlogController extends Controller {
	public void editBlog(){
		String  id=getPara("id");
		Record record = new Record();
		record=Db.findById("blog",Integer.parseInt(id));
		System.out.println(record.getStr("title"));
	}
}

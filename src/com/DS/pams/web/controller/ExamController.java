package com.DS.pams.web.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.DS.pams.web.base.BaseController;
import com.DS.utils.common.Util;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
/*****
 * 
 * @author jeff
 * 试题控制器
 */
public class ExamController extends BaseController {
	
	
	/***
	 * 获取随机试题
	 */
	public void getExam(){		
   		try {
   			String filter=getPara("filter");
   		    Map<String,Object>  param=new HashMap<String,Object>();
 	        param.put("filter", filter);
      		SqlPara getExam=Db.getSqlPara("exam.getExam",param);
      		Record record=Db.findFirst(getExam);
      		if(record==null){
      			throw new Exception("暂无符合条件的数据");
      		}     		
      		//更新操作时间
      		record.set("opertime", new Date());
      		Db.update("exam", record);
      		Map<String,Object> resultMap=new HashMap<String,Object>();
      		resultMap.put("question", record.get("question"));
      		resultMap.put("answer", record.get("answer"));
      		resultMap.put("id", record.get("id"));
      		renderJson(ajaxDoneSuccess(resultMap)); 
   		  }catch(Exception e) {
   			e.printStackTrace();
   			renderJson(ajaxDoneError(e.getMessage()));
   		}  		   		  		  		   				  		
   	}
	
	/***
	 * 添加新的试题
	 */
	public void addQuestion(){
		String question=getPara("question");
		String answer=getPara("answer");
		String id=getPara("key");
	    Map<String,Object>  param=new HashMap<String,Object>();
	    param.put("question", question);
	    param.put("answer", answer);
	    param.put("id", id);
	    Record record=new Record();
	    record.set("question", question);
	    record.set("answer", answer);
	    record.set("id", id);
	    SqlPara sql=Db.getSqlPara("exam.addQuestion", param);
		try {
		  if(Util.isEmpty(id)){
			  Db.update(sql);
			  renderJson(ajaxDoneSuccess("添加成功！")); 
		  }else{
			  Db.update("exam", record);
			  renderJson(ajaxDoneSuccess("保存成功！")); 
		  }
		 
		}catch(Exception e) {
   			e.printStackTrace();
   			renderJson(ajaxDoneError(e.getMessage()));
   		}  	
	    
	}
	
	
	
	
	 public void goExam(){
	   	  render("exam.jsp");
	     }
}

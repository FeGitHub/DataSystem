package com.DS.pams.web.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import com.DS.common.model.User;
import com.DS.pams.service.FileService;
import com.DS.pams.service.NotificationService;
import com.DS.pams.service.TaskService;
import com.DS.pams.service.impl.FileServiceImpl;
import com.DS.pams.service.impl.NotificationServiceImpl;
import com.DS.pams.service.impl.TaskServiceImpl;
import com.DS.pams.web.base.BaseController;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
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
      		SqlPara getJson=Db.getSqlPara("exam.getExam");
      		Record record=Db.findFirst(getJson);
      		if(record==null){
      			throw new Exception("暂无符合条件的数据");
      		}     		
      		//更新操作时间
      		record.set("opertime", new Date());
      		Db.update("exam", record);
      		Map<String,Object> resultMap=new HashMap<String,Object>();
      		resultMap.put("question", record.get("question"));
      		resultMap.put("answer", record.get("answer"));
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
	    Map<String,Object>  param=new HashMap<String,Object>();
	    param.put("question", question);
	    param.put("answer", answer);
	    SqlPara sql=Db.getSqlPara("exam.addQuestion", param);
		try {
		  Db.update(sql);
		  renderJson(ajaxDoneSuccess("添加成功！")); 
		}catch(Exception e) {
   			e.printStackTrace();
   			renderJson(ajaxDoneError(e.getMessage()));
   		}  	
	    
	}
	
	
	
	
	 public void goExam(){
	   	  render("exam.jsp");
	     }
}

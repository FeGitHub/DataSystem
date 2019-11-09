package com.DS.controller.comm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.DS.common.model.ProjectTree;
import com.DS.common.model.User;
import com.DS.file.service.FileService;
import com.DS.file.service.impl.FileServiceImpl;
import com.DS.notification.service.NotificationService;
import com.DS.notification.service.impl.NotificationServiceImpl;
import com.DS.task.service.TaskService;
import com.DS.task.service.impl.TaskServiceImpl;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
/*****
 * 
 * @author jeff
 * 
 */
public class InterfaceController extends BaseController {
	
	@Clear
	public void getJson(){
   		String serviceid=getPara("serviceid");
   	    Map<String, Object> map=new HashMap<String, Object>();
	    map.put("serviceid", serviceid);
   		SqlPara getJson=Db.getSqlPara("interfaceservice.getJson", map);
   		Record record=Db.findFirst(getJson);
   		String json="{\"info\":\"无对应的接口数据\"}";
   		if(record!=null){
   			 json=record.getStr("jsonstr");
   		}   		
   		renderJson(json);   		
   	}
	
	
	
	   public void goTest(){
	   	  render("interface.jsp");
	     }
}

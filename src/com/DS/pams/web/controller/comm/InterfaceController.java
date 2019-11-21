package com.DS.pams.web.controller.comm;
import java.util.HashMap;
import java.util.Map;
import com.DS.pams.web.base.BaseController;
import com.jfinal.aop.Clear;
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
   		//String json="{\"info\":\"无对应的接口数据\"}";
   		String json="";
   		try {
   		    Map<String, Object> map=new HashMap<String, Object>();
   	        map.put("serviceid", serviceid);
      		SqlPara getJson=Db.getSqlPara("interfaceservice.getJson", map);
      		Record record=Db.findFirst(getJson);
      		if(record==null){
      			throw new Exception("无对应的接口数据");
      		} 
      		 json=record.getStr("jsonstr");
      		renderJson(json); 
   		  }catch(Exception e) {
   			e.printStackTrace();
   			renderJson(ajaxDoneError(e.getMessage()));
   		}  		   		  		  		   				  		
   	}
	
	
	
	   public void goTest(){
	   	  render("interface.jsp");
	     }
}

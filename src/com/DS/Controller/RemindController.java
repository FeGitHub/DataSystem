package com.DS.Controller;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.Model.RemindModel;
import com.DS.Model.TestModel;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/***
 * 
 * @author jeff
 * 备忘提醒的控制器
 *
 */
public class RemindController extends Controller {
	
	/***
	 * 备忘提醒的管理页面
	 */
	public void goRemindList(){		
		render("remindList.jsp");
	}
	
	/****
	 * 获取预警规则信息
	 */
	public void getRemindDetails(){	
		String sql="SELECT * FROM  ds_remind order by addTime desc";
		List<Record> remindDetails=Db.find(sql);
		Map<String, List<Record>> map = new HashMap<String, List<Record>>();
		map.put("data", remindDetails);
        renderJson(map);
	}
	
	/****
	 * 添加备忘事项
	 */
	public void addRemindDetail(){
		boolean result;
		Map<String, String> map=new HashMap<String, String>();
		RemindModel  record = getModel(RemindModel.class,"record");	
		if(record.getStr("id")!=""&&record.getStr("id")!=null){
			result=record.update();
		}else{
			record.set("addTime", new Date());
			result=record.save();
		}
		if(result){
			map.put("code","233");
		}else{
			map.put("code","444");
		}
		renderJson(map);
	}
	
	/***
	 * 删除备忘事项
	 */
	public void delRemindDetail(){
		String id=getPara("id");
		Map<String, String> map=new HashMap<String, String>();
		if(RemindModel.dao.deleteById(Integer.parseInt(id))){
			map.put("code","233");
        }else{
        	map.put("code","444");
        }
		renderJson(map);
	}
	
}

package com.DS.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		render("remindList.html");
	}
	
	/****
	 * 获取预警规则信息
	 */
	public void getRemindDetails(){	
		String sql="SELECT * FROM  ds_remind";
		List<Record> remindDetails=Db.find(sql);
		Map<String, List<Record>> map = new HashMap<String, List<Record>>();
		map.put("data", remindDetails);
        renderJson(map);
	}
	
}

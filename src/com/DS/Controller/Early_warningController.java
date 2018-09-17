package com.DS.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.utils.JsonUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/***
 * 
 * @author jeff
 *	用于预警信息的控制器
 */
public class Early_warningController extends Controller {
	
	/***
	 * 跳转预警规则编辑页面
	 */
	public void goWarnRule(){
		render("warnRule.html");
	}
	
	/****
	 * 获取预警规则信息
	 */
	public void getWarnRuleDetails(){	
		String sql="SELECT * FROM  early_warning_rule";
		List<Record> WarnRuleDetails=Db.find(sql);
		Map<String, List<Record>> map = new HashMap<String, List<Record>>();
		map.put("data", WarnRuleDetails);
        renderJson(map);
	}
	/***
	 * 跳转预警规则编辑页面
	 */
	public void goWarnRuleAdd(){
		render("warnRuleAdd.html");
	}
	
	/***
	 * 订单预警判定
	 */
	public void judgeOrder(){
		JSONArray result=getTree();//得到物料的树形结构关系
	}
	
	/***
	 * 获取物料类型的树形结构
	 */
	public JSONArray getTree(){
		String sql="SELECT own_node,parent_node,material_name FROM  material";
		List<Record> treeDetails=Db.find(sql);	
		List<Map<String,Object>> data = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		for(Record c:treeDetails){
			 map = new HashMap<>();
			 map.put("id",c.get("own_node"));
			 map.put("pid",c.get("parent_node"));
			 map.put("name",c.get("material_name"));
			 data.add(map);
		}	
		// System.out.println("原始："+JSON.toJSONString(data));
		 JSONArray result = JsonUtil.listToTree(JSONArray.parseArray(JSON.toJSONString(data)),"id","pid","children");
		 //System.out.println(JSON.toJSONString(result));
		 renderJson(result);
		 return result;
	}
}

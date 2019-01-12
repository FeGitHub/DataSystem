package com.DS.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.common.model.Menu;
import com.DS.utils.JsonUtil;
import com.DS.web.base.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.json.FastJson;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;

public class MenuController extends BaseController{
	 public void goMenu(){
   	  render("menu.jsp");
     }
	 
	 public void getZtreeJsonFromView(){
		  String ztreeJson=getPara("ztreeJson");
		  List<Menu> temp= JSON.parseArray(ztreeJson,Menu.class); 
		  Map<String, List<Menu>> map=new HashMap<String, List<Menu>>();
		  map.put("cond", temp);
		  SqlPara sql=Db.getSqlPara("menu.insertDataBatch", map);
		  //Db.update(sql);
		  renderJson(ajaxDoneSuccess("操作成功"));
	  }
	 
	 /****
	   * 取出数据库的ztree表的数据
	   */
	  public void getZtreeJsonFromDB(){
		  String sql=Db.getSql("menu.selectAllData");
		 List<Record> ztreeList= Db.find(sql);		
		 renderJson(ztreeList);
	  }
	  
	  public void getTreeMenu(){
		     String sql=Db.getSql("menu.selectAllData");
			 List<Record> ztreeList= Db.find(sql);
			 String json=FastJson.getJson().toJson(ztreeList);
			 JSONArray array= JSONArray.parseArray(json);
			 JSONArray test=JsonUtil.listToTree(array, "id", "pId", "subMenuList");
			 System.out.println(test);
			 Map<String,Object> map=new HashMap<String,Object>();
			 map.put("menuTree", test);
			 renderJson(map);
	  }
}

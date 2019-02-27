package com.DS.menu.service.impl;
import java.util.List;
import com.DS.menu.service.MenuService;
import com.DS.utils.common.JsonUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.json.FastJson;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/****
 * 
 * @author jeff
 * 菜单服务实现层
 */
public class MenuServiceImpl implements MenuService {
	
	/****
	 * 获取树形菜单数据
	 */
	@Override
	public JSONArray getTreeMenu() {
		 JSONArray r = new JSONArray();//存放重组的的数据
		 String sql=Db.getSql("menu.selectMenuData");
		 List<Record> ztreeList= Db.find(sql);
		 String json=FastJson.getJson().toJson(ztreeList);
		 JSONArray array= JSONArray.parseArray(json);
		 JSONArray menuJSONArray=JsonUtil.listToTree(array, "id", "pId", "subMenuList");
		 for(int i=0;i<menuJSONArray.size();i++){
			 JSONObject aVal = (JSONObject) menuJSONArray.get(i);
			 if(aVal.get("subMenuList")==null){
				 aVal.put("subMenuList", "");
			 }
			 r.add(aVal);
		 }
		 return r;
	}
}

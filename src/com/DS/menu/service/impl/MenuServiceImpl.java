package com.DS.menu.service.impl;
import java.util.List;
import com.DS.menu.service.MenuService;
import com.DS.utils.common.JsonUtil;
import com.alibaba.fastjson.JSONArray;
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
		 String sql=Db.getSql("menu.selectMenuData");
		 List<Record> ztreeList= Db.find(sql);
		 String json=FastJson.getJson().toJson(ztreeList);
		 JSONArray array= JSONArray.parseArray(json);
		 JSONArray menuJSONArray=JsonUtil.listToTree(array, "id", "pId", "subMenuList");
		 return menuJSONArray;
	}
}

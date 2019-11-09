package com.DS.pams.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.DS.bean.MenuInfo;
import com.DS.pams.service.MenuService;
import com.DS.utils.common.JsonUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.json.FastJson;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
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
	public MenuInfo getTreeMenu(int menuLevel) {
		MenuInfo menuInfo=new MenuInfo();
		 Map<String,Object> cond=new HashMap<String,Object>();
		 List<String> urls=new ArrayList<String>();
		 cond.put("menu_level", menuLevel);
		 JSONArray r = new JSONArray();//存放重组的的数据
		 SqlPara sql=Db.getSqlPara("menu.selectMenuData",cond);
		 List<Record> ztreeList= Db.find(sql);
		 for(Record record:ztreeList){
			 urls.add(record.getStr("url"));
		 }
		 menuInfo.setUrls(urls);
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
		 menuInfo.setTreeMenu(r);
		 return menuInfo;
	}
}

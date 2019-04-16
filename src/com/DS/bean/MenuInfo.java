package com.DS.bean;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

public class MenuInfo {
   private JSONArray treeMenu;//树形菜单信息
   
   private  List<String> urls;//菜单链接
   
   public JSONArray getTreeMenu() {
	return treeMenu;
}

public void setTreeMenu(JSONArray treeMenu) {
	this.treeMenu = treeMenu;
}

public List<String> getUrls() {
	return urls;
}

public void setUrls(List<String> urls) {
	this.urls = urls;
}


   
}

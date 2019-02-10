package com.DS.menu.service;

import com.alibaba.fastjson.JSONArray;

public interface MenuService {
	/***
	 * 获取菜单
	 * @return
	 */
	JSONArray getTreeMenu();
}

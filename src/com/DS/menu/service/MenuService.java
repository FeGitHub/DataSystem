package com.DS.menu.service;

import com.DS.bean.MenuInfo;

public interface MenuService {
	/***
	 * 获取菜单
	 * @return
	 */
	MenuInfo getTreeMenu(int menuLevel);
}

package com.DS.task.service.impl;

import java.util.Map;

import com.DS.task.service.TaskService;
import com.DS.utils.DataTablesUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;

public class TaskServiceImpl implements TaskService {

	@Override
	public Map<String, Object> getTaskList(Map<String,Object> cond) {
		  SqlPara sqlShow = Db.getSqlPara("task.getTaskList",cond);
		  SqlPara sqlTotal = Db.getSqlPara("task.getSize",cond);      
		  Map<String,Object> map=DataTablesUtil.getPageData(sqlShow, sqlTotal);
          return map;
	}

}

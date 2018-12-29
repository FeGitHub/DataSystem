package com.DS.remind.service.impl;
import java.util.Map;
import com.DS.remind.service.RemindService;
import com.DS.utils.DataTablesUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;

public class RemindServiceImpl implements RemindService {

	@Override
	public Map<String, Object> getRemindDetails(Map<String,Object> cond) {
		  SqlPara sqlShow = Db.getSqlPara("remind.getRemindDetails",cond);
		  SqlPara sqlTotal = Db.getSqlPara("remind.getSize",cond);      
		  Map<String,Object> map=DataTablesUtil.getPageData(sqlShow, sqlTotal);
          return map;
	}

}

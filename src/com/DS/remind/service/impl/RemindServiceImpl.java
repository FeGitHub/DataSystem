package com.DS.remind.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.DS.remind.service.RemindService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;

public class RemindServiceImpl implements RemindService {

	@Override
	public Map<String, Object> getRemindDetails(Map<String,Object> cond) {
		SqlPara sq = Db.getSqlPara("remind.getRemindDetails",cond);			
		List<Record> remindDetails=Db.find(sq);
		Map<String, Object> map = new HashMap<String, Object>();
		SqlPara sqlTotal = Db.getSqlPara("remind.getSize",cond);	
		Record rec=Db.findFirst(sqlTotal);
		map.put("recordsTotal", rec.getLong("total"));
		map.put("recordsFiltered", rec.getLong("total"));
		map.put("data", remindDetails);//此页展示数据
        return map;
	}

}

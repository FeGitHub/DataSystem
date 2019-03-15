package com.DS.remind.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.common.model.Remind;
import com.DS.remind.service.RemindService;
import com.DS.utils.common.DataTablesUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
/***
 * 
 * @author jeff
 * 备忘提醒服务层实现类
 */
public class RemindServiceImpl implements RemindService {

	@Override
	public Map<String, Object> getRemindDetails(Map<String,Object> cond) {
		  SqlPara sqlShow = Db.getSqlPara("remind.getRemindDetails",cond);
		  SqlPara sqlTotal = Db.getSqlPara("remind.getSize",cond);      
		  Map<String,Object> map=DataTablesUtil.getPageData(sqlShow, sqlTotal);
          return map;
	}
   
	/****
	 * 得到用户当日的备忘提醒事务
	 */
	@Override
	public List<Remind> getTodayRemind(String userId) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("userId", userId);
		SqlPara sql=Db.getSqlPara("remind.getTodayRemind", paramMap);
		Remind dao=new Remind();
		List<Remind> recordList=dao.find(sql);
		return recordList;		
	}

}

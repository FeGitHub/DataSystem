package com.DS.pams.service;
import java.util.List;
import java.util.Map;
import com.DS.common.model.Remind;
/***
 * 
 * @author jeff
 * 备忘提醒服务层
 */
public interface RemindService {
	 Map<String, Object> getRemindDetails(Map<String,Object> cond);
	 
	 /***
	  * 得到用户当日的备忘提醒事务
	  * @param userId
	  * @return
	  */
	 List<Remind> getTodayRemind(String userId);
}

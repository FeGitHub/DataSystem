package com.DS.quartz.service;
import java.util.Map;
import com.DS.bean.QuartzTaskBean;
import com.DS.quartz.vo.QuartzParamVo;
import com.DS.quartz.vo.QuartzTransferVo;
/***
 * 
 * @author jeff
 * quartz调度任务服务层
 *
 */
public interface QuartzService {	
	/***
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 * @param cron
	 */
	void modifyJobTime(QuartzTaskBean bean);
	
	String addJob(QuartzTaskBean bean);
	
	Map<String,Object> transfer(QuartzTransferVo paramVo);
	
	void removeJob(QuartzParamVo vo);
}

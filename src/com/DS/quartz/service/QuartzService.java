package com.DS.quartz.service;
import java.util.Map;

import com.DS.bean.QuartzTaskBean;
import com.DS.quartz.vo.QuartzParamVo;
import com.DS.quartz.vo.QuartzTransferVo;
import com.jfinal.plugin.activerecord.Record;
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
	
	void addJob(QuartzTaskBean bean);
	
	Map transfer(QuartzTransferVo paramVo);
	
	void removeJob(QuartzParamVo vo);
}

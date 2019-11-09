package com.DS.pams.service;
import java.util.Map;
import com.DS.bean.QuartzTaskBean;
import com.DS.pams.vo.QuartzParamVo;
import com.DS.pams.vo.QuartzTransferVo;

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
	
	boolean removeJob(QuartzParamVo vo);
	
	Map<String,Object> getQuartzList(Map<String,Object> DivPageCondition);		
	
}

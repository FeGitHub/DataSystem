package com.DS.quartz.service.impl;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import com.DS.bean.QuartzTaskBean;
import com.DS.exception.BusinessException;
import com.DS.quartz.service.QuartzService;
import com.DS.quartz.vo.QuartzParamVo;
import com.DS.quartz.vo.QuartzTransferVo;
import com.DS.utils.CronUtil;
import com.DS.utils.quartz.QuartzManager;
/***
 * @author jeff
 * 调度任务服务实现层
 */
public class QuartzServiceImpl implements QuartzService {
	private static final String SYS_NO = "QuartzServiceImpl";
		
	/***
	 * 改变调度器任务触发时间
	 */
	@Override
	public void modifyJobTime(QuartzTaskBean bean) {
		String Cron="";
		try {
			Cron = CronUtil.getCron("day", bean.getDateStr());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		QuartzManager.modifyJobTime(bean.getJobName(), bean.getJobGroup(), bean.getTriggerGroup(), bean.getTriggerName(),Cron,bean.getDescription());		
	}

	/***
	 * 新增调度器任务
	 */
	@Override
	public void addJob(QuartzTaskBean bean){
		bean.setTriggerName(bean.getJobName()+"TriggerName");
		bean.setTriggerGroup(bean.getJobGroup()+"TriggerGroup");		
		bean.setJobClassStr(bean.getJobClassStr());
	    @SuppressWarnings("rawtypes")
		Class jobClass=null;
		try {
			jobClass = Class.forName(bean.getJobClassStr());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		QuartzManager.addJob(bean.getJobName(), bean.getJobName(), bean.getTriggerName(), bean.getTriggerGroup(), jobClass, bean.getCron(), bean.getDescription());
		
	}
	
	/****
	 * 将普通日期转换成Cron表达式
	 */
	@Override
	public Map<String,Object> transfer(QuartzTransferVo paramVo) {
		  if(paramVo==null){
			  throw new BusinessException(SYS_NO,"transfer","Cron表达式为null");
		  }
		  Map<String,Object> map = new HashMap<String,Object>();
		  String cron="";
		  if(paramVo.getWeekType()!=null&&paramVo.getPeriod().equals("week")){
			  paramVo.setPeriod(paramVo.getWeekType());
		  }		
		  if(paramVo.getDataStr()==null||paramVo.getDataStr().equals("")){
			  map.put("code", "409");
			  return map;
		  }		  		 
		  try {
			if(paramVo.getPeriod()!=null&&!paramVo.getPeriod().equals("once")){
				cron=CronUtil.getCron(paramVo.getPeriod(), paramVo.getDataStr());
			}else{
				cron=CronUtil.getCronByOnce(paramVo.getDataStr()); 
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}		  		
		  map.put("code", "200");
		  map.put("cron", cron);
		return map;
	}
	
	/****
	 * 删除调度任务
	 */
	@Override
	public void removeJob(QuartzParamVo vo) {
		QuartzManager.removeJob(vo.getJobName(), vo.getJobGroupName(), vo.getTriggerName(), vo.getTriggerGroupName());	
	}
   
	
}

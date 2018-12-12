package com.DS.quartz.service.impl;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.DS.Bean.QuartzTaskBean;
import com.DS.quartz.dao.QuartzDao;
import com.DS.quartz.service.QuartzService;
import com.DS.quartz.vo.QuartzTransferVo;
import com.DS.utils.CronUtil;
import com.DS.utils.quartz.QuartzManager;
import com.jfinal.plugin.activerecord.Record;
/***
 * @author jeff
 * 调度任务服务实现层
 */
public class QuartzServiceImpl implements QuartzService {
	
	/***
	 * 根据调度任务获取调度任务的相关信息
	 * @param jobName 调度任务名
	 * @return
	 */
	@Override
	public Record getQuartzTaskByName(String jobName) {
		QuartzDao q=new QuartzDao();
		return q.getQuartzTaskByName(jobName);
	}	
	
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
	    Class jobClass=null;
		try {
			jobClass = Class.forName(bean.getJobClassStr());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		QuartzManager.addJob(bean.getJobName(), bean.getJobName(), bean.getTriggerName(), bean.getTriggerGroup(), jobClass, bean.getCron(), bean.getDescription());
		
	}

	@Override
	public Map transfer(QuartzTransferVo paramVo) {
		  Map<String, String> map = new HashMap<String, String>();
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
   
}

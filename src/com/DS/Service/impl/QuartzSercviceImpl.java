package com.DS.Service.impl;
import java.text.ParseException;
import com.DS.Bean.QuartzTaskBean;
import com.DS.Service.QuartzService;
import com.DS.dao.QuartzDao;
import com.DS.utils.CronUtil;
import com.DS.utils.quartz.QuartzManager;
import com.jfinal.plugin.activerecord.Record;
/***
 * @author jeff
 * 调度任务服务实现层
 */
public class QuartzSercviceImpl implements QuartzService {
	
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

}

package com.DS.Controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.Bean.QuartzTaskBean;
import com.DS.Service.QuartzService;
import com.DS.Service.impl.QuartzSercviceImpl;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/***
 * @author jeff
 * 处理quartz调度器任务的控制器
 */
public class QrtzController extends Controller{
	/**
	 * 获取调度器任务的相关信息
	 * 三表连接：qrtz_job_details,qrtz_triggers,qrtz_cron_triggers
	 */
	public void getJobDetails(){	
		String sql=" select detail.JOB_NAME,detail.JOB_GROUP,triggers.DESCRIPTION,detail.JOB_CLASS_NAME,cron.CRON_EXPRESSION,triggers.TRIGGER_NAME,triggers.TRIGGER_GROUP from qrtz_job_details as detail "
				  +" LEFT JOIN qrtz_triggers as triggers on triggers.JOB_NAME=detail.JOB_NAME and triggers.JOB_GROUP=detail.JOB_GROUP and triggers.SCHED_NAME=detail.SCHED_NAME"
				  +" LEFT JOIN qrtz_cron_triggers as cron on cron.TRIGGER_NAME=triggers.TRIGGER_NAME and cron.TRIGGER_GROUP=triggers.TRIGGER_GROUP and cron.SCHED_NAME=triggers.SCHED_NAME";
		List<Record> jobDetails=Db.find(sql);
		Map<String, List<Record>> map = new HashMap<String, List<Record>>();
		map.put("data", jobDetails);
        renderJson(map);
	}
	
	public void goQuartzJob(){
		render("qrtzTable.jsp");
	}
	
	/***
	 * 根据调度任务名得到调度任务的相关信息
	 */
	public void getQuartzTaskByName(){
		String  jobName=getPara("jobName");
		Map<String, Record> map = new HashMap<String, Record>();
		QuartzService q=new QuartzSercviceImpl();	
		Record record=q.getQuartzTaskByName(jobName);
		map.put("record",record);
        renderJson(map);
	}
	 
	/***
	 * 修改调度任务时间
	 */
	 public void ChangeQuartzTaskTime(){
		 Map<String, String> map = new HashMap<String, String>();
		 QuartzTaskBean bean=getBean(QuartzTaskBean.class,"record");
		 QuartzService q=new QuartzSercviceImpl();
		 q.modifyJobTime(bean);
		 map.put("record","dd");
	     renderJson(map);
		 
	 }
}

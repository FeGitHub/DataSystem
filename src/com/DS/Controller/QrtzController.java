package com.DS.Controller;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.Bean.QuartzTaskBean;
import com.DS.quartz.service.QuartzService;
import com.DS.quartz.service.impl.QuartzSercviceImpl;
import com.DS.utils.CronUtil;
import com.DS.utils.PackageUtil;
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
		String sql=Db.getSql("qrtz.getJobDetails");
		List<Record> jobDetails=Db.find(sql);
		Map<String, List<Record>> map = new HashMap<String, List<Record>>();
		map.put("data", jobDetails);
        renderJson(map);
	}
	
	public void goQuartzJob(){
		render("qrtzTable.jsp");
	}
	
	
	/**
	 *  跳转到创建新的调度器任务页面
	 */
	public void goCreateQuartz(){
		render("createQuartz.jsp");
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
	 
	 /***
	  * 转换普通日期变为cron
	  */
	  public void transfer(){
		  Map<String, String> map = new HashMap<String, String>();
		  String dataStr=getPara("dataStr");//时间字符串
		  String period=getPara("period");//周期
		  String weekType=getPara("weekType");//
		  String cron="";
		  if(weekType!=null&&period.equals("week")){
			  period=weekType;
		  }		
		  if(dataStr==null||dataStr.equals("")){		
			  return;
		  }		  		 
		  try {
			if(period!=null&&!period.equals("once")){
				cron=CronUtil.getCron(period, dataStr);
			}else{
				cron=CronUtil.getCronByOnce(dataStr); 
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}		  		
		  map.put("code", "200");
		  map.put("cron", cron);
		  renderJson(map);
	  }
	  
	  public void getAllJob(){
		  Map<String, Object> map = new HashMap<String, Object>();
		  String packageName = "com.DS.utils.quartz.jobs";  
		  List<String> allJobList=PackageUtil.getClassName(packageName);
		  map.put("code", "200");
		  map.put("allJobList", allJobList);
		  renderJson(map);
	  }
}

package com.DS.Controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.Bean.QuartzTaskBean;
import com.DS.quartz.service.QuartzService;
import com.DS.quartz.service.impl.QuartzServiceImpl;
import com.DS.quartz.vo.QuartzTransferVo;
import com.DS.utils.PackageUtil;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/***
 * @author jeff
 * 处理quartz调度器任务的控制器
 */
public class QrtzController extends Controller{
	@Inject(QuartzServiceImpl.class)
	private QuartzService quartzService;
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
		QuartzService q=new QuartzServiceImpl();	
		Record record=q.getQuartzTaskByName(jobName);
		map.put("record",record);
        renderJson(map);
	}
	 
	/***
	 * 修改调度任务时间
	 */
	 public void modifyJobTime(){
		 Map<String, String> map = new HashMap<String, String>();
		 QuartzTaskBean bean=getBean(QuartzTaskBean.class,"");
		 QuartzService q=new QuartzServiceImpl();
		 q.modifyJobTime(bean);
		 map.put("record","dd");
	     renderJson(map);	 
	 }
	 
	 /***
	  * 转换普通日期变为cron
	  */
	  public void transfer(){
		  QuartzTransferVo paramVo=getBean(QuartzTransferVo.class,"");
		  QuartzService QuartzService=new QuartzServiceImpl();
		  renderJson(QuartzService.transfer(paramVo));
	  }
	  
	  /***
	   * 获取quartz的job类下拉列表
	   */
	  public void getAllJob(){
		  Map<String, Object> map = new HashMap<String, Object>();
		  String packageName = "com.DS.utils.quartz.jobs";  
		  List<String> allJobList=PackageUtil.getClassName(packageName);
		  map.put("code", "200");
		  map.put("allJobList", allJobList);
		  renderJson(map);
	  }
	  
	  /***
	   * 创建一个调度任务
	   */
	  public void createQuartzTask(){
		  Map<String, Object> map = new HashMap<String, Object>();
		  QuartzTaskBean task=getBean(QuartzTaskBean.class, "");
		  //QuartzService quartzService=new QuartzSercviceImpl();
		  quartzService.addJob(task);
		  map.put("code", "200");
		  renderJson(map);
		  
	  }
}

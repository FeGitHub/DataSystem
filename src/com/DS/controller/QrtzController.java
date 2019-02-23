package com.DS.controller;
import java.util.List;
import java.util.Map;
import com.DS.bean.QuartzTaskBean;
import com.DS.quartz.service.QuartzService;
import com.DS.quartz.service.impl.QuartzServiceImpl;
import com.DS.quartz.vo.QuartzParamVo;
import com.DS.quartz.vo.QuartzTransferVo;
import com.DS.utils.common.PackageUtil;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Inject;
/***
 * @author jeff
 * 处理quartz调度器任务的控制器
 */
public class QrtzController extends BaseController{
	@Inject(QuartzServiceImpl.class)
	private QuartzService quartzService;
	/**
	 * 获取调度器任务的相关信息
	 * 三表连接：qrtz_job_details,qrtz_triggers,qrtz_cron_triggers
	 */
	public void getJobDetails(){
		DivPageCondition=getDivPageCondition();//基本分页参数
		DivPageCondition.put("jobName", getPara("jobName"));
		Map<String, Object> map=quartzService.getQuartzList(DivPageCondition);
        renderJson(ajaxDoneSuccess(map));
	}
	
	/***
	 * 跳转到调取任务列表页面
	 */
	public void goQrtzTaskList(){
		render("qrtzTaskList.jsp");
	}
	
	
	/**
	 *  跳转到创建新的调度器任务页面
	 */
	public void goCreateQuartz(){
		render("createQuartz.jsp");
	}	
	 
	/***
	 * 修改调度任务时间
	 */
	 public void modifyJobTime(){
		 QuartzTaskBean bean=getBean(QuartzTaskBean.class,"");
		 quartzService.modifyJobTime(bean);
	     renderJson(ajaxDoneSuccess());	 
	 }
	 
	 /***
	  * 转换普通日期变为cron
	  */
	  public void transfer(){
		  QuartzTransferVo paramVo=getBean(QuartzTransferVo.class,"");	  
		  renderJson(quartzService.transfer(paramVo));
	  }
	  
	  /***
	   * 获取quartz的job类下拉列表
	   */
	  public void getAllJob(){
		  String packageName = "com.DS.utils.quartz.jobs";//将quartz的job类统一写在这个包下 
		  List<String> allJobList=PackageUtil.getClassName(packageName);
		  resultMap.put("allJobList", allJobList);
		  renderJson(ajaxDoneSuccess());
	  }
	  
	  /***
	   * 创建一个调度任务
	   */
	  public void createQuartzTask(){
		        QuartzTaskBean task=getBean(QuartzTaskBean.class, "");		 	  
				String errorMsg=quartzService.addJob(task);
				if(errorMsg==null){
					renderJson(ajaxDoneSuccess("成功创建调度器任务!"));		
				}else{
					renderJson(ajaxDoneError(errorMsg));
				}	    		   						 		   
	  }
	  
	  /***
	   * 删除调度任务
	   */
	  public void removeJob(){
		  QuartzParamVo vo=getBean(QuartzParamVo.class, "");
		   if(quartzService.removeJob(vo)){
			   renderJson(ajaxDoneSuccess("成功删除调度器任务!"));		
		   }else{
			   renderJson(ajaxDoneSuccess("操作失败"));	
		   }		
	  }
}

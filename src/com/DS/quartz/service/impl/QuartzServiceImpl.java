package com.DS.quartz.service.impl;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.quartz.SchedulerException;
import com.DS.bean.QuartzTaskBean;
import com.DS.exception.BusinessException;
import com.DS.quartz.service.QuartzService;
import com.DS.quartz.vo.QuartzParamVo;
import com.DS.quartz.vo.QuartzTransferVo;
import com.DS.utils.common.CronUtil;
import com.DS.utils.common.DataTablesUtil;
import com.DS.utils.quartz.QuartzManager;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
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
	 * @throws ClassNotFoundException 
	 * @throws SchedulerException 
	 */
	@Override
	public String addJob(QuartzTaskBean bean){
		String errorMsg=null;
		//修改：调度任务的命名参数由系统生成
		UUID uuid = UUID.randomUUID();
		bean.setJobName(uuid.toString());
		//以下命名参数根据jobName修改
		bean.setJobGroup(bean.getJobName()+"JobGroup");		
		bean.setTriggerName(bean.getJobName()+"TriggerName");
		bean.setTriggerGroup(bean.getJobGroup()+"TriggerGroup");		
		bean.setJobClassStr(bean.getJobClassStr());
	    @SuppressWarnings("rawtypes")
		Class jobClass=null;
		try {
			jobClass = Class.forName(bean.getJobClassStr());			
			QuartzManager.addJob(bean.getJobName(), bean.getJobGroup(), bean.getTriggerName(), bean.getTriggerGroup(), jobClass, bean.getCron(), bean.getDescription());
		} catch (ClassNotFoundException e) {
			errorMsg="对应的class没有找到";
			e.printStackTrace();
		}catch (SchedulerException e) {	
			errorMsg="调度器参数出错";
			e.printStackTrace();
		}catch(Exception e){
			errorMsg="操作失败";
			e.printStackTrace();
		}							
	   return errorMsg;
	}
	
	/****
	 * 将普通日期转换成Cron表达式
	 */
	@Override
	public Map<String,Object> transfer(QuartzTransferVo paramVo) {
		String comment=CronUtil.mappingComment(paramVo);
		  if(paramVo==null){
			  throw new BusinessException(SYS_NO,"transfer","Cron表达式为null");
		  }
		  Map<String,Object> map = new HashMap<String,Object>();
		  String cron="";
		  if(paramVo.getWeekType()!=null&&paramVo.getPeriod().equals("week")){
			  paramVo.setPeriod(paramVo.getWeekType());
		  }		  		 
		  try {
			if(paramVo.getPeriod()!=null&&!paramVo.getPeriod().equals("once")){//频率调度表达式
					cron=CronUtil.getCron(paramVo.getPeriod(), paramVo.getDataStr());
					  map.put("code", "200");
				}else{//一次使用
					cron=CronUtil.getCronByOnce(paramVo.getDataStr()); 
					  map.put("code", "200");
					}
			} catch (ParseException e) {
				 map.put("code", "409");
				 e.printStackTrace();
		     }		  				
		    map.put("cron", cron);
		    map.put("comment", comment);
		    return map;
	}
	
	/****
	 * 删除调度任务
	 */
	@Override
	public boolean removeJob(QuartzParamVo vo) {
		boolean result=true;
		try {
			QuartzManager.removeJob(vo.getJobName(), vo.getJobGroupName(), vo.getTriggerName(), vo.getTriggerGroupName());
		} catch (SchedulerException e) {
			e.printStackTrace();
			result=false;
		}	
		return result;
	}
	
	
	/****
	 * 获取调度器任务分页数据
	 */
	@Override
	public Map<String, Object> getQuartzList(Map<String, Object> DivPageCondition) {
		SqlPara sqlShow = Db.getSqlPara("qrtz.getJobDetails",DivPageCondition);	
		SqlPara sqlTotal = Db.getSqlPara("qrtz.getSize",DivPageCondition);			
		Map<String,Object> map=DataTablesUtil.getPageData(sqlShow, sqlTotal);
		return map;
	}
   
}

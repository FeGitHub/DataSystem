package com.DS.Controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.Model.QrtzJobDetailsModel;
import com.jfinal.core.Controller;
/***
 * 
 * @author jeff
 * 处理quartz调度器任务的控制器
 */
public class QrtzController extends Controller{
	/**
	 * 获取调度器任务的相关信息
	 */
	public void getJobDetails(){
		List<QrtzJobDetailsModel> jobDetails=QrtzJobDetailsModel.dao.find("select * from qrtz_job_details");
        Map<String, List<QrtzJobDetailsModel>> map = new HashMap<String, List<QrtzJobDetailsModel>>();
        map.put("data", jobDetails);
        renderJson(map);
	}
	
	public void goQuartzJob(){
		render("qrtzTable.html");
	}
}

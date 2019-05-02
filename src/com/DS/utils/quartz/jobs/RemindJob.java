package com.DS.utils.quartz.jobs;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/****
 * 
 * @author jeff
 * 离截止时间3天内的任务将会被
 * 以邮件的方式
 * 提醒
 *
 */
public class RemindJob implements Job{
	private static Logger logger = Logger.getLogger(RemindJob.class); 
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {				
		//以今天为标准，后三天内的数据
		//String sql="select * from ds_remind where remindTime>=DATE_SUB(NOW(),INTERVAL 3 DAY)";
		//List<Record> remindList=Db.find(sql);
		//String text="";//邮件内容
		//String mail="";//邮件地址	
	
		//======
		//logger.info("暂时没有要提醒的事项");
		//return;
		//=========
		/*if(remindList.size()==0){
			logger.info("暂时没有要提醒的事项");
		}
		for(int i=0;i<remindList.size();i++){
			mail=remindList.get(0).getStr("mail");//目前统一用一个邮件接收，方便测试
			if(i==0){
				text="(时间="+remindList.get(i).getStr("remindTime")+",条目="+remindList.get(i).getStr("remindText")+")";
			}else{
				text+="<br>"+"(时间="+remindList.get(i).getStr("remindTime")+",条目="+remindList.get(i).getStr("remindText")+")";
			}		
		}
		try {			
			MailUtil.sendMail(text,mail);
			logger.info("提醒邮件发送");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}

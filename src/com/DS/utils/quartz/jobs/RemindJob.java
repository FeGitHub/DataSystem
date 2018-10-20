package com.DS.utils.quartz.jobs;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.DS.utils.MailUtil;
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
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		/*JobDataMap map = arg0.getJobDetail().getJobDataMap();
		String str = map.getString("Test");
		System.out.println("测试："+str);*/		
		//以今天为标准，后三天内的数据
		String sql="select * from ds_remind where remindTime>=DATE_SUB(NOW(),INTERVAL 3 DAY)";
		List<Record> remindList=Db.find(sql);
		String text="";//邮件内容
		String mail="";//邮件地址
		mail=remindList.get(0).getStr("mail");//目前统一用一个邮件接收，方便测试
		for(int i=0;i<remindList.size();i++){
			if(i==0){
				text="(时间="+remindList.get(i).getStr("remindTime")+",条目="+remindList.get(i).getStr("remindText")+")";
			}else{
				text+="<br>"+"(时间="+remindList.get(i).getStr("remindTime")+",条目="+remindList.get(i).getStr("remindText")+")";
			}		
		}
		try {
			System.out.println("发送邮件...");
			MailUtil.sendMail(text,mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

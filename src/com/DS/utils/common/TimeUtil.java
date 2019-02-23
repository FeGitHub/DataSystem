package com.DS.utils.common;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.DS.bean.DateBean;

public class TimeUtil {
	/****
	 * 将时间字符串逐个拆分
	 * @param timeStr
	 * @return
	 */
	 public static DateBean dateStrToDiv(String timeStr){
		 DateBean bean=new DateBean();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date=null;
		try {
			date = sdf.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date); // 转为新Calendar			
			bean.setYear(cal.get(Calendar.YEAR)+"");
			bean.setMonth(cal.get(Calendar.MONTH)+1+"");
			bean.setDay(cal.get(Calendar.DATE)+"");
			bean.setHour(cal.get(Calendar.HOUR_OF_DAY)+"");
			bean.setMinute(cal.get(Calendar.MINUTE)+"");
			bean.setSecond(cal.get(Calendar.SECOND)+"");
		 return bean;
	 }
	 	
	 	/*****
	 	 * 比较两个时间点的天数差
	 	 * @param A
	 	 * @param B
	 	 * @return
	 	 */
	    public static int  compareTime(Date A,Date B){
		 long diff=Math.abs(A.getTime()-B.getTime());
		 diff=diff/(1000 * 60 * 60 * 24);
		 int day=(int)diff;
		 return day;
	 }
}

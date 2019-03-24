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
	    
	    /***
	     * 距离截止日期的时间
	     * @param end
	     * @param start
	     * @return
	     */
	    public static int  remindDay(Date end,Date start){
	    	 long diff=end.getTime()-start.getTime();
	    	 if(diff<=0){
	    		 diff=0;
	    	 }else if(diff>0&&diff<=(1000 * 60 * 60 * 24)){
	    		 diff=1;
	    	 }else{
	    		 diff=diff/(1000 * 60 * 60 * 24);
	    	 }					
			 int day=(int)diff;
			 return day;
		 }
	    
	    
	   /****
	    * 判断目前时间是否在计划时间段内
	    * @param end
	    * @param start
	    * @return
	    */
	    public static boolean  underway(Date end,Date start){
	    	Date now=new Date();
	    	if(end.getTime()<start.getTime()){
	    		return false;
	    	}	    	
	    	if(now.getTime()>=start.getTime()&&now.getTime()<=end.getTime()){
	    		return true;
	    	}else{
	    		return false;
	    	}
	    }
	    
	    /****
	     * 两个时间点之差(X天X小时X分)
	     * @param endDate
	     * @param nowDate
	     * @return
	     */
	    public static String getDatePoor(Date endDate, Date nowDate) {	    	 
	        long nd = 1000 * 24 * 60 * 60;
	        long nh = 1000 * 60 * 60;
	        long nm = 1000 * 60;	     
	        long diff = endDate.getTime() - nowDate.getTime();	   
	        long day = diff / nd;	     
	        long hour = diff % nd / nh;     
	        long min = diff % nd % nh / nm;
	        return day + "天" + hour + "小时" + min + "分钟";
	    }
}

package com.DS.utils;
/*****
 * 
 * @author jeff
 * 系统工具类
 */
public class SystemUtil {
	 public static void main(String[] args){
		 if(isLinux()){
			 System.out.println("yes");
		 }else{
			 System.out.println("no"); 
		 }
	 }
	 /****
	  * 判断当前的系统是否是linux系统
	  * @return
	  */
	public static boolean isLinux() { 
		return System.getProperty("os.name").toLowerCase().indexOf("linux") >= 0; 
	}
}

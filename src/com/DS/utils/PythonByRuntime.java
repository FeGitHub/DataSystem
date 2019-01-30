package com.DS.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.kit.PathKit;
/****
 * 
 *  @author jeff
 *  调用python的工具
 */
public class PythonByRuntime {
	private static String exe = "python";
	public static void main(String[] args) throws IOException,InterruptedException {				
		runPython3(PathKit.getRootClassPath()+"\\py\\","house_data5.py");
    }
	
	/* 可使用，暂时弃用
	 * public static String runPython3(String command,String num1,String num2) throws IOException, InterruptedException{
		    String[] cmdArr = new String[] {exe, command, num1,num2};
	        Process process = Runtime.getRuntime().exec(cmdArr);
	        InputStream is = process.getInputStream();
	        DataInputStream dis = new DataInputStream(is);
	        String str = dis.readLine();
	        process.waitFor();
	        System.out.println(str);
	        return str;
	}
	
	public static String runPython3(String command) throws IOException, InterruptedException{
		    String[] cmdArr = new String[] {exe, command};
	        Process process = Runtime.getRuntime().exec(cmdArr);
	        InputStream is = process.getInputStream();
	        DataInputStream dis = new DataInputStream(is);
	        String str = dis.readLine();	       
	        process.waitFor();
	        System.out.println(str);
	        return str;
}*/
	
	/****
	 * 
	 * @param locate python的父级绝对路径（同时也用于定位资源文件）
	 * @param PYFile 要执行的pyhton脚本文件
	 */
	public static List<String> runPython3(String locate,String PYFile){
		List<String> result=new ArrayList<String>();
        Process proc;
        try {
        	String exec="python "+locate+PYFile+" "+locate;
        	System.out.println("执行语句："+exec);
            proc = Runtime.getRuntime().exec(exec);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"GBK"));          
            while (in.readLine() != null) {
            	String line =in.readLine();
                System.out.println(line);
                result.add(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}

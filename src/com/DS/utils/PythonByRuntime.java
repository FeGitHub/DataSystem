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
	//private static String exe = "python";
	private static String PY_ROOT_PATH=PathKit.getRootClassPath()+"\\py\\";//默认的python脚本文件路径
	private static String PY_RES_PATH=PathKit.getRootClassPath()+"\\py\\resources";//默认的python资源文件路径
	public static void main(String[] args) throws IOException,InterruptedException {				
		//runPython3(PY_ROOT_PATH,PY_RES_PATH,"house_data5.py");
		runPython3("house_data5.py");
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
	 * @param PY_Path pyhton文件的所在路径 
	 * @param PY_ResPath  python 文件资源路径
	 * @param PY_FileName python文件名
	 * @return
	 */
	public static List<String> runPython3(String PY_Path,String PY_ResPath,String PY_FileName){
		List<String> result=new ArrayList<String>();
        Process proc;
        try {
        	String exec="python "+PY_Path+PY_FileName+" "+PY_ResPath;
        	System.out.println("执行语句："+exec);
            proc = Runtime.getRuntime().exec(exec);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"GBK"));                  
            String line =null;
            while ((line=in.readLine() )!= null) {        
                System.out.println(line);
                result.add(line);
            }
            in.close();
            proc.waitFor();
            System.out.println("结束");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
	
	/***
	 * 
	 * @param PY_FileName  python脚本名称
	 * 默认python脚本路径 项目/py/...
	 * 默认python资源文件路径 /py/resources/...
	 * @return
	 */
	public static List<String> runPython3(String PY_FileName){
		List<String> result=new ArrayList<String>();
        Process proc;
        try {
        	String exec="python "+PY_ROOT_PATH+PY_FileName+" "+PY_RES_PATH;
        	System.out.println("执行语句："+exec);
            proc = Runtime.getRuntime().exec(exec);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"GBK"));                  
            String line =null;
            while ((line=in.readLine() )!= null) {        
                System.out.println(line);
                result.add(line);
            }
            in.close();
            proc.waitFor();
            System.out.println("结束");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}

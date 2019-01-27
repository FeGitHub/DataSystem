package com.DS.utils;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.jfinal.kit.PathKit;
/****
 * 
 * @author jeff
 *  调用python的工具
 */
public class PythonByRuntime {
	private static String exe = "python";
	public static void main(String[] args) throws IOException,InterruptedException {		
		  try {
				PythonByRuntime.runPython3(PathKit.getRootClassPath()+"/py/hello.py");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
	
	public static String runPython3(String command,String num1,String num2) throws IOException, InterruptedException{
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
}
}

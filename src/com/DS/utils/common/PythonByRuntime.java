package com.DS.utils.common;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.jfinal.kit.PathKit;
/****
 * 
 *  @author jeff  2019-3-25
 *  调用python的工具
 *  ps:运行相关python文件失败的可能原因
 *  1.没有配置相关的python开发环境或环境变量
 *  2.没有导入相关python相关包
 *  3.python文件是否混进tab制表符(https://www.cnblogs.com/heimanba/p/3783022.html)
 *  4.python文件没有更新（真正运行的python的项目发布的相关文件夹内详细见控制台）
 *  
 *  解决方案：单独在cmd下运行python文件(推荐使用pycharm开发)
 *  
 *  https://jingyan.baidu.com/album/3f16e0031e87522591c10320.html?picindex=1
 */
public class PythonByRuntime {
	private static Logger logger = Logger.getLogger(PythonByRuntime.class);
	private static String PY_ROOT_PATH=PathKit.getRootClassPath()+"\\py\\";//默认的python脚本文件路径
	private static String PY_RES_PATH=PathKit.getRootClassPath()+"\\py\\resources";//默认的python资源文件路径
	private static String PY_MODEL_PATH=PathKit.getRootClassPath()+"\\py\\resources\\model";//默认的python资源文件路径
	public static void main(String[] args) throws IOException,InterruptedException {					
		runPython3("custom_build_model.py","plantime,tasknum,effic,abstime");
    }
	
	
	/****
	 * 指定资源路径和python文件路径，运行python文件
	 * @param PY_Path pyhton文件的所在路径 
	 * @param PY_ResPath  python 文件资源路径
	 * @param PY_FileName python文件名
	 * @return
	 */
	public static List<String> runPython3(String PY_Path,String PY_ResPath,String PY_FileName){
		List<String> result=new ArrayList<String>();
		String exec="python "+PY_Path+PY_FileName+" "+PY_ResPath;
		result= cmdRunPython(exec);
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
		String exec="python "+PY_ROOT_PATH+PY_FileName+" "+PY_RES_PATH+" "+PY_MODEL_PATH;
		result= cmdRunPython(exec);
		return result;
    }
	
	/***
	 * 
	 * @param PY_FileName 
	 * @param argStr python 执行的第三参数
	 * @return
	 */
	public static List<String> runPython3(String PY_FileName,String argStr){
		List<String> result=new ArrayList<String>();
		String exec="python "+PY_ROOT_PATH+PY_FileName+" "+PY_RES_PATH+" "+PY_MODEL_PATH+" "+argStr;
		result= cmdRunPython(exec);
		return result;
    }
	
	/****
	 * 执行python
	 * @param exec  python命令语句
	 * @return  List<String> 控制台信息
	 */
	private static List<String> cmdRunPython(String exec){
		List<String> result=new ArrayList<String>();
        Process proc;
		 try {	        	
	        	logger.info("---------python执行语句-----------");
	        	logger.info(exec);
	            proc = Runtime.getRuntime().exec(exec);// 执行py文件
	            //用输入输出流来截取结果
	            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"GBK"));                  
	            String line =null;
	            while ((line=in.readLine() )!= null) {        
	            	logger.info(line);
	                result.add(line);
	            }
	            if(result.size()==0){           	
	            	logger.info("请检查python的环境是否配置，请用cmd单独运行下面文件");	                      	 
	            }
	            in.close();
	            proc.waitFor();
	            logger.info("---------python执行结束----------");
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        return result;
	}
}

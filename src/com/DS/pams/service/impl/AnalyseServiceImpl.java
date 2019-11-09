package com.DS.pams.service.impl;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.DS.common.model.Analyse;
import com.DS.common.model.User;
import com.DS.pams.service.AnalyseService;
import com.DS.utils.common.CSVUtil;
import com.DS.utils.common.PythonByRuntime;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;

/****
 * 任务分析服务层实现类
 */
public class AnalyseServiceImpl implements AnalyseService {
	private static Logger logger = Logger.getLogger(AnalyseServiceImpl.class);
	
	/****
	 * 获取用户自定义分析函数参数
	 */
	@Override
	public List<String> customAnalyse(String heads,String customfile,User user) {
	    String filepath=PythonByRuntime.PY_ROOT_PATH+"custom_build_model.py";//python文件
	    String modlepath=PythonByRuntime.PY_MODEL_PATH+"\\"+customfile+".m";//训练模型
	    Prop p =PropKit.use("config.properties");	 
		String uploadfile=p.get("uploadFile")+"\\"+customfile+".csv"; 
		//python--->avg[1]=读取的资源文件  avg[2]-->训练模型  avg[3]-->资源文件标题
	    String exec="python "+filepath+" "+uploadfile+" "+modlepath+" "+heads;
	    List<String> result=PythonByRuntime.cmdRunPython(exec);
	    if(result!=null&&result.size()!=0){
	    	String coef=result.get(result.size()-2);//参数权重
			String intercept=result.get(result.size()-1);//截距
			 Map<String,Object> param=new HashMap<String,Object>();
				param.put("analyseType", "customAnalyse");
				param.put("userId", user.getId());
				SqlPara getsql=Db.getSqlPara("analyse.getAnalyseByType", param);	
				Analyse analyse=new Analyse();
				analyse=analyse.findFirst(getsql);
				if(analyse!=null){//更新 
					analyse.setFunction(getAnalyseArgs(coef,intercept));
					analyse.update();
				}else{//新增
					analyse=new Analyse();
					analyse.setFunction(getAnalyseArgs(coef,intercept));
					analyse.setAnalyseType("customAnalyse");
					analyse.setUserId(user.getId());
					analyse.save();
				}
	    }
	    
		return result;
	}
  
	
	
  /*****
   * 更新用户的任务分析函数参数
   */
	@Override
	public boolean updateTaskAnalyse(User user) {
		List<String> result=getTaskAnalyseArgsformPy(user);		
		if(result==null||result.size()!=2){
			return false;
		}
		String coef=result.get(0);//参数权重
		String intercept=result.get(1);//截距
		//数据更新
		 Map<String,Object> param=new HashMap<String,Object>();
		param.put("analyseType", "taskAnalyse");
		param.put("userId", user.getId());
		SqlPara getsql=Db.getSqlPara("analyse.getAnalyseByType", param);	
		Analyse analyse=new Analyse();
		analyse=analyse.findFirst(getsql);
		if(analyse!=null){//更新 
			analyse.setFunction(getAnalyseArgs(coef,intercept));
			analyse.update();
		}else{//新增
			analyse=new Analyse();
			analyse.setFunction(getAnalyseArgs(coef,intercept));
			analyse.setAnalyseType("taskAnalyse");
			analyse.setUserId(user.getId());
			analyse.save();
		}
		return true;
	}
    
	
	/*****
	 * 将分析参数进行组合
	 * @param coefStr  权重参数字符串(形如："[1 2 3]")
	 * @param intercept 截距字符串
	 * @return
	 */
	public String getAnalyseArgs(String coefStr,String intercept){
		coefStr=coefStr.substring(1,coefStr.length()-1);
		String[] coefList = coefStr.split(" "); 
		List<String> functionParam=new ArrayList<String>();
		for(int i=0;i<coefList.length;i++){
			if(coefList[i]!=null&&!coefList[i].isEmpty()){
				functionParam.add(coefList[i]);
			}
		}
		StringBuilder buff=new StringBuilder();
		for(int i=0;i<functionParam.size();i++){
			if(i==0){
				buff.append(functionParam.get(i));
			}else{
				buff.append(",").append(functionParam.get(i));
			}
			
		}
		buff.append(",").append(intercept);
		return buff+"";
	}
	
	
	/***
	 * 获取分析结果
	 * @return
	 */
	public String getAnalyseResult(String functionArgs,List<String> param){
		List<String> functionParam = Arrays.asList(functionArgs.split(","));
		if((functionParam.size()-1)!=param.size()){
			return null;//对应参数有误
		}
		String lastFunctionArg=functionParam.get(functionParam.size()-1);
		double result=Double.parseDouble(lastFunctionArg);
		for(int i=0;i<param.size();i++){
			result+=Double.parseDouble(param.get(i))*Double.parseDouble(functionParam.get(i));
		}
		return result+"";
	}
	
	/****
	 * 获取用户任务分析参数
	 * @return
	 */
	public List<String> getTaskAnalyseArgsformPy(User user){
		Prop p =PropKit.use("config.properties");
		String heads="plantime,level,tasknum,taskNumInWeek";//可随算法策略调整
		String py =PythonByRuntime.PY_ROOT_PATH+"taskAnalyse.py";//python文件
		String filename=user.getId()+user.getAccount()+"CSV";//生成的资源文件名称
		String res=p.get("pams")+"\\"+filename+".csv";
		File file=new File(res);
		if(!file.exists()){
			logger.error(res+"文件资源不存在");
			return null;
		}
		String exec="python "+py+" "+heads+" "+res;
		List<String> result=PythonByRuntime.cmdRunPython(exec);	
		return result;
	}
	 
	
	
	
	
	/****
	 * 产生用户的任务分析数据对应的csv文件
	 * @return
	 */
	public boolean createAnalyseCSV(User user){
		if(user.getId()==null){
			logger.error("用户id为空");
			return false;
		}
		Prop p =PropKit.use("config.properties");
		String outPutPath=p.get("pams")+"\\";
		String filename=user.getId()+user.getAccount()+"CSV";//生成的资源文件名称
		String[] heads={"plantime","level","tasknum","taskNumInWeek"};//根据算法策略进行改变
		TaskServiceImpl taskServiceImpl=new TaskServiceImpl();
		List<Record> list=taskServiceImpl.getAnalyseDataByUser(user);		
		//转换
		List<Object[]> rows=new ArrayList<Object[]>();
		if(list.size()<5){//数据量太少，不足于进行数据分析，不进行导出操作
			return true;
		}
		for(int i=0;i<list.size();i++){
			String str[] = new String[4]; 
			Record record=list.get(i);
			str[0]=record.getStr("plantime");
			str[1]=record.getStr("level");
			str[2]=record.getStr("tasknum");
			str[3]=record.getStr("taskNumInWeek");
			rows.add(str);
		}
		boolean result=CSVUtil.createCSVFile(heads,rows,outPutPath,filename);		
		return result;
	}
	
	//===============
	public List<String> getProjectAnalyseArgsformPy(User user){
		Prop p =PropKit.use("config.properties");
		String heads="planTime,projectTaskNum,taskInProject,actualyTime";//可随算法策略调整
		String py =PythonByRuntime.PY_ROOT_PATH+"taskAnalyse.py";//python文件
		String filename=user.getId()+user.getAccount()+"Project";//生成的资源文件名称
		String res=p.get("pams")+"\\"+filename+".csv";
		File file=new File(res);
		if(!file.exists()){
			logger.error(res+"文件资源不存在");
			return null;
		}
		String exec="python "+py+" "+heads+" "+res;
		List<String> result=PythonByRuntime.cmdRunPython(exec);	
		return result;
	}



	@Override
	public boolean updateProjectAnalyse(User user) {
		List<String> result=getProjectAnalyseArgsformPy(user);		
		if(result==null||result.size()!=2){
			return false;
		}
		String coef=result.get(0);//参数权重
		String intercept=result.get(1);//截距
		//数据更新
		 Map<String,Object> param=new HashMap<String,Object>();
		param.put("analyseType", "projectAnalyse");
		param.put("userId", user.getId());
		SqlPara getsql=Db.getSqlPara("analyse.getAnalyseByType", param);	
		Analyse analyse=new Analyse();
		analyse=analyse.findFirst(getsql);
		if(analyse!=null){//更新 
			analyse.setFunction(getAnalyseArgs(coef,intercept));
			analyse.update();
		}else{//新增
			analyse=new Analyse();
			analyse.setFunction(getAnalyseArgs(coef,intercept));
			analyse.setAnalyseType("projectAnalyse");
			analyse.setUserId(user.getId());
			analyse.save();
		}
		return true;
	}
}

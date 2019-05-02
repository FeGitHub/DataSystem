package com.DS.analyse.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.analyse.service.AnalyseService;
import com.DS.common.model.Analyse;
import com.DS.common.model.User;
import com.DS.utils.common.PythonByRuntime;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;

/****
 * 任务分析服务层实现类
 */
public class AnalyseServiceImpl implements AnalyseService {
  
	
	/****
	 * 获取用户自定义分析函数参数
	 */
	@Override
	public List<String> customAnalyse(String heads,String customfile) {
	    String filepath=PythonByRuntime.PY_ROOT_PATH+"custom_build_model.py";//python文件
	    String modlepath=PythonByRuntime.PY_MODEL_PATH+"\\"+customfile+".m";//训练模型
	    Prop p =PropKit.use("config.properties");	 
		String uploadfile=p.get("uploadFile")+"\\"+customfile+".csv"; 
		//python--->avg[1]=读取的资源文件  avg[2]-->训练模型  avg[3]-->资源文件标题
	    String exec="python "+filepath+" "+uploadfile+" "+modlepath+" "+heads;
	    List<String> result=PythonByRuntime.cmdRunPython(exec);
		return result;
	}
  
	
	
  /*****
   * 更新用户的任务分析函数参数
   */
	@Override
	public void updateTaskAnalyse(User user) {
		String coef="[1 2 3]";
		String intercept="4";
		//数据更新
		 Map<String,Object> param=new HashMap<String,Object>();
		param.put("taskAnalyse", "taskAnalyse");
		param.put("userId", user.getId());
		SqlPara getsql=Db.getSqlPara("analyse.getAnalyseByType", param);	
		Analyse analyse=new Analyse();
		analyse=analyse.findFirst(getsql);
		if(analyse!=null){//更新 
			analyse.setFunction(getAnalyseArgs(coef,intercept));
			analyse.update();
		}else{//新增
			analyse.setFunction(getAnalyseArgs(coef,intercept));
			analyse.setAnalyseType("taskAnalyse");
			analyse.setUserId(user.getId());
			analyse.save();
		}
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
		StringBuilder buff=new StringBuilder();
		for(int i=0;i<coefList.length;i++){
			buff.append(coefList[i]).append(",");
		}
		buff.append(intercept);
		return buff+"";
	}
}

package com.DS.analyse.service.impl;
import java.util.List;
import com.DS.analyse.service.AnalyseService;
import com.DS.utils.common.PythonByRuntime;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

public class AnalyseServiceImpl implements AnalyseService {

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

}

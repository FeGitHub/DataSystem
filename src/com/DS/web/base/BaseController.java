package com.DS.web.base;
import java.util.HashMap;
import java.util.Map;
import com.jfinal.core.Controller;
/****
 * 
 * @author jeff
 *
 */
public abstract class BaseController extends Controller{	
	protected Map<String,Object> resultMap=new HashMap<String,Object>();//Ajax返回数据
	
	protected Map<String,Object> DivPageCondition=new HashMap<String,Object>();//分页约束条件
	
	protected  int start;//开始展示的数据
	protected  int length;//每页展示数据条数
	
	protected Map<String,Object> ajaxDoneSuccess(String message){
		resultMap.put("code", "200");
		resultMap.put("msg",message);
		return resultMap;
	}
	protected Map<String,Object> ajaxDoneSuccess(){
		resultMap.put("code", "200");
		return resultMap;
	}
	
	protected Map<String,Object> ajaxDoneSuccess(Map<String,Object> map){
		resultMap=map;
		resultMap.put("code", "200");
		return resultMap;
	}
	protected Map<String,Object> ajaxDoneError(String message){
		resultMap.put("code", "409");
		resultMap.put("msg",message);
		return resultMap;
	}
	protected Map<String,Object> ajaxDoneError(){
		resultMap.put("code", "409");
		return resultMap;
	}
	
	/****
	 * 得到dataTables基本的分页约束条件
	 * @return
	 */
	 public Map<String,Object> getDivPageCondition(){					
			start=Integer.parseInt(getPara("start"));
			length=Integer.parseInt(getPara("length"));		
			DivPageCondition.put("start", start);
			DivPageCondition.put("length", length);	
			return DivPageCondition;
	 }
	 
}

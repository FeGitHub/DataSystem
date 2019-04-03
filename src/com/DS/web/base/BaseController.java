package com.DS.web.base;
import java.util.HashMap;
import java.util.Map;
import com.DS.common.model.User;
import com.jfinal.core.Controller;
/****
 * 
 * @author jeff
 * 基本控制器
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
		resultMap.put("msg","操作成功");
		return resultMap;
	}
	
	protected Map<String,Object> ajaxDoneSuccess(Map<String,Object> map){
		resultMap=map;
		resultMap.put("code", "200");
		if(resultMap.get("msg")==null){
			resultMap.put("msg", "操作成功");
		}
		return resultMap;
	}
	protected Map<String,Object> ajaxDoneError(String message){
		resultMap.put("code", "400");
		resultMap.put("msg",message);
		return resultMap;
	}
	protected Map<String,Object> ajaxDoneError(){
		resultMap.put("code", "400");
		resultMap.put("msg","操作失败");
		return resultMap;
	}
	protected Map<String,Object> ajaxDoneError(Map<String,Object> map){
		resultMap=map;
		resultMap.put("code", "400");
		if(resultMap.get("msg")==null){
			resultMap.put("msg", "操作失败");
		}
		return resultMap;
	}
	/****
	 * 基本分页参数
	 * @return
	 */
	 public Map<String,Object> getDivPageCondition(){
		    User nowUser = (User)getSession().getAttribute("user");
		    String userId=nowUser.getId()+"";		 
			start=Integer.parseInt(getPara("start"));  
			length=Integer.parseInt(getPara("length"));	
			DivPageCondition.put("userId", userId);
			DivPageCondition.put("start", start);
			DivPageCondition.put("length", length);	
			return DivPageCondition;
	 }
	 
	 /****
	  * 将用户的参数限制和基本分页参数限制结合
	  * @param param
	  * @return
	  */
	 public Map<String,Object> getDivPageParam(Map<String,Object> param){		   
		    User nowUser = (User)getSession().getAttribute("user");
		    String userId=nowUser.getId()+"";		 
			start=Integer.parseInt(getPara("start"));
			length=Integer.parseInt(getPara("length"));	
			DivPageCondition.put("userId", userId);
			DivPageCondition.put("start", start);
			DivPageCondition.put("length", length);	
			param.putAll(DivPageCondition);
			return param;
	 }
	 
}

package com.DS.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.common.model.User;
import com.DS.utils.JsonUtil;
import com.DS.utils.ObjectUtil;
import com.DS.utils.SecretUtil;
import com.DS.web.base.BaseController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Clear;
import com.jfinal.json.FastJson;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
/****
 * 
 * @author jeff
 * 登陆登出控制器
 */
public class LoginController extends BaseController{
	/*
	 * 登陆验证
	 */
	@Clear
   public void index(){
	   User user=getModel(User.class,"");
       Map<String,Object> paramMap=ObjectUtil.convertBeanToMap(user);
       SqlPara sql=Db.getSqlPara("user.getUserInfoByAccount", paramMap);
       Record record=Db.findFirst(sql);
       if(record==null){
    	   renderJson(ajaxDoneError("账号或密码出错"));
    	   return;
       }
   	   String password = SecretUtil.getMD5(user.getPassword());
       if(record!=null&&record.getStr("password").equals(password)){
    	     //用户信息
    	     setSessionAttr("user", record);  
    	     //菜单信息
    	     String sql1=Db.getSql("menu.selectMenuData");
			 List<Record> ztreeList= Db.find(sql1);
			 String json=FastJson.getJson().toJson(ztreeList);
			 JSONArray array= JSONArray.parseArray(json);
			 JSONArray menu=JsonUtil.listToTree(array, "id", "pId", "subMenuList");			 
			 JSONObject hash = new JSONObject();
			 hash.put("menuTree", menu);	
			 setSessionAttr("menuTree", hash);  
    	     Map<String,Object> map=new HashMap<String,Object>();
    	     map.put("url", "/go/goMenu");
    	     renderJson(ajaxDoneSuccess(map));
       }else{
    	     renderJson(ajaxDoneError("账号或密码出错"));
       } 
   }
   /***
    * 登出处理
    */
   public void signOut(){
	   getSession().invalidate();
	   forwardAction("/");	
   }
}

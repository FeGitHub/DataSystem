package com.DS.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.bean.ZtreeJsonBean;
import com.DS.web.base.BaseController;
import com.alibaba.fastjson.JSON;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
/*****
 * 
 * @author jeff
 * 案例页面的控制器
 */
public class DemoController extends BaseController {
	 //ztree
	  public void goTreePage(){
    	  render("ztreeBootstrap.jsp");
      }
	  
	  
	  public void getZtreeJson(){
		  String ztreeJson=getPara("ztreeJson");
		  List<ZtreeJsonBean> temp= JSON.parseArray(ztreeJson,ZtreeJsonBean.class); 
		  Map<String, List<ZtreeJsonBean>> map=new HashMap<String, List<ZtreeJsonBean>>();
		  map.put("cond", temp);
		  SqlPara sql=Db.getSqlPara("ztree.insertDataBatch", map);
		  //Db.update(sql);
		  renderJson(ajaxDoneSuccess("操作成功"));
	  }
}

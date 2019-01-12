package com.DS.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.common.model.Ztree;
import com.DS.utils.JsonUtil;
import com.DS.web.base.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.json.FastJson;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
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
	  
	  public void goEChartsPage(){
    	  render("eChartsDemo.jsp");
      }
	  
	  /****
	   * 将页面上传递来的json格式的ztree数据转换成可插入的数据库信息
	   */
	  public void getZtreeJsonFromView(){
		  String ztreeJson=getPara("ztreeJson");
		  List<Ztree> temp= JSON.parseArray(ztreeJson,Ztree.class); 
		  Map<String, List<Ztree>> map=new HashMap<String, List<Ztree>>();
		  map.put("cond", temp);
		 // SqlPara sql=Db.getSqlPara("ztree.insertDataBatch", map);
		  SqlPara sql=Db.getSqlPara("menu.insertDataBatch", map);
		  //Db.update(sql);
		  renderJson(ajaxDoneSuccess("操作成功"));
	  }
	  
	  /****
	   * 取出数据库的ztree表的数据
	   */
	  public void getZtreeJsonFromDB(){
		  String sql=Db.getSql("ztree.getZtreeJsonFromDB");
		 List<Record> ztreeList= Db.find(sql);
		 //==========
		 String json=FastJson.getJson().toJson(ztreeList);
		 JSONArray array= JSONArray.parseArray(json);
		 JSONArray test=JsonUtil.listToTree(array, "id", "pId", "subMenuList");
		 System.out.println(test);
		 //========
		 renderJson(ztreeList);
	  }
	  
	  /****
	   * 得到EchartsData数据的测试返回
	   */
	  public void getEChartsData(){
		  String sql=Db.getSql("demo.getECharts");
		  List<Record> list=Db.find(sql);
		  renderJson(list);
	  }
	  
	  /***
	   * 测试jfinal的事务处理
	   */
	  public void testJfinalTX(){
		  int size=0;
		  Db.tx(() -> {
			  Db.update("update echarts set categories = ? where id=12", "测试成功+1");
			  if(size==0){
				   throw new RuntimeException();
			   }
			  return true;
			});
	  }
}

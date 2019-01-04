package com.DS.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.bean.ZtreeJsonBean;
import com.DS.web.base.BaseController;
import com.alibaba.fastjson.JSON;
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
	   * 将页面上传递来的json的ztree数据转换成可插入数据可的对应数据
	   */
	  public void getZtreeJson(){
		  String ztreeJson=getPara("ztreeJson");
		  List<ZtreeJsonBean> temp= JSON.parseArray(ztreeJson,ZtreeJsonBean.class); 
		  Map<String, List<ZtreeJsonBean>> map=new HashMap<String, List<ZtreeJsonBean>>();
		  map.put("cond", temp);
		  SqlPara sql=Db.getSqlPara("ztree.insertDataBatch", map);
		  //Db.update(sql);
		  renderJson(ajaxDoneSuccess("操作成功"));
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

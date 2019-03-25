package com.DS.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.common.model.Menu;
import com.DS.common.model.ProjectTree;
import com.DS.menu.service.MenuService;
import com.DS.menu.service.impl.MenuServiceImpl;
import com.DS.web.base.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
/****
 * 
 * @author jeff
 * 菜单控制器
 */
public class MenuController extends BaseController{
	@Inject(MenuServiceImpl.class)
	private MenuService menuService;
	
	/*****
	 * 跳转到菜单设置页面
	 */
	 public void goMenu(){
   	   render("menuSettings.jsp");
     }
	 
	 /****
	  * 更新菜单数据
	  */
	 public void updateZtreeJson(){
		  String ztreeJson=getPara("ztreeJson");
		  List<Menu> menuList= JSON.parseArray(ztreeJson,Menu.class); 
		  Map<String, List<Menu>> map=new HashMap<String, List<Menu>>();
		  map.put("cond", menuList);
		  SqlPara updateSql=Db.getSqlPara("menu.insertDataBatch", map);		
		  String deleteSql=Db.getSql("menu.deleteAll");
		  Db.tx(() -> {
			  Db.delete(deleteSql);
			  Db.update(updateSql);			
			  return true;
			});
		  //更新即时菜单数据
		/*  JSONArray menu=menuService.getTreeMenu();
 	      JSONObject hash = new JSONObject();
		  hash.put("menuTree", menu);	
		  setSessionAttr("menuTree", hash);*/  
		  renderJson(ajaxDoneSuccess("操作成功"));
	  }
	 
	 /****
	   * 取出数据库的ztree表的数据
	   */
	  public void getZtreeJsonFromDB(){
		  String sql=Db.getSql("menu.selectAllData");
		 List<Record> ztreeList= Db.find(sql);		
		 renderJson(ztreeList);
	  }
	  
	  /****
	   * 获取菜单信息数据
	   */
	  public void getTreeMenu(){		   
		     JSONArray menuJsonArray=menuService.getTreeMenu();
			 Map<String,Object> map=new HashMap<String,Object>();
			 map.put("menuTree", menuJsonArray);
			 renderJson(map);
	  }
	  
	  
	 public void addMenuNode(){
		 Menu menu=getModel(Menu.class,"");
		 Map<String,Object> map=new HashMap<String,Object>();
    	 if(menu.save()){
    		 map.put("id", menu.getId());
    		 renderJson(ajaxDoneSuccess(map));
    	 }else{
    		 renderJson(ajaxDoneError("新增失败"));
    	 }
	 }
}

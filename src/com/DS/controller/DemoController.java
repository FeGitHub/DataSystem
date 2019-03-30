package com.DS.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.DS.common.model.User;
import com.DS.file.service.FileService;
import com.DS.file.service.impl.FileServiceImpl;
import com.DS.notification.service.NotificationService;
import com.DS.notification.service.impl.NotificationServiceImpl;
import com.DS.task.service.TaskService;
import com.DS.task.service.impl.TaskServiceImpl;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/*****
 * 
 * @author jeff
 * 案例页面的控制器
 */
public class DemoController extends BaseController {
	@Inject(FileServiceImpl.class)
	private FileService fileService;
	 
	@Inject(TaskServiceImpl.class)
	private TaskService taskService;
	
	@Inject(NotificationServiceImpl.class)
	private NotificationService notificationService;
	 /****
	  * 跳转到上传下载页面
	  */
	  public void goDownUpLoadPage(){
    	  render("downUpLoad.jsp");
      }
	  public void goTreePage(){
    	  render("ztreeBootstrap.jsp");
      }
	  
	  /****
	   * 跳转到Echarts数据分析页面
	   */
	  public void goEChartsPage(){
    	  render("eChartsDemo.jsp");
      }
	  
	  /***
	   * 跳转到进度条控制页面
	   */
	  public void goProgress(){
    	  render("progressBar.jsp");
      }
	  /***
	   * 跳转到甘特图
	   */
	  public void goGant(){
    	  render("ganttView.jsp");
      }
	 
	  /***
	   * bootstrap组件
	   */
	  public void goBootstrap(){
    	  render("bootstrap-components.jsp");
      }
	  	  
	  public void goFullCalendar(){
    	  render("fullcalendar.jsp");
      }
	  
	  public void goMailBox(){
		  User user=(User)getSession().getAttribute("user");
		  long endPageNumber=notificationService.getNotificationSize(user.getId()+"");
		  endPageNumber=(long) Math.floor(endPageNumber/5);
		  if(endPageNumber==0){
			  endPageNumber=endPageNumber+1;
		  }
		  setAttr("endPageNumber",endPageNumber);
    	  render("page-mailbox.jsp");
      }
	  
	  /***
	   * 加载通知信息列表
	   */
	  public void loadNotifyList(){		  
	      User user=(User)getSession().getAttribute("user");
	      Map<String, Object> map=notificationService.loadNotifyList(user.getId()+"", getPara("pageNumber"));
	      renderJson(ajaxDoneSuccess(map));
	  }
	  
	  
	  /****
	   * 批量删除信息通知
	   */
	  public void batchDelNotifications(){
		  String[] ids = this.getParaValues("ids[]");
		  List<String>  list=new ArrayList<String>();
		  for(int i=0;i<ids.length;i++){
			  list.add(ids[i]);
		  }
		  int result=notificationService.batchDel(list);
		  if(result>0){
			  renderJson(ajaxDoneSuccess("操作成功"));
		  }else{
			  renderJson(ajaxDoneError("操作失败"));
		  }
	  }
	  
	  @Clear
	  public void sendCode(){		 
		  String mailAdress=getPara("mailAdress");		 		
		  int code=notificationService.sendCode(mailAdress);
			  if(code>0){				
				  setSessionAttr(mailAdress, code+"");				  
				  renderJson(ajaxDoneSuccess("操作成功"));
			  }else{
				  renderJson(ajaxDoneError("操作失败"));
			  }			
		  }			  
	  
	 /* public void getZtreeJsonFromView(){
		  String ztreeJson=getPara("ztreeJson");
		  List<Ztree> temp= JSON.parseArray(ztreeJson,Ztree.class); 
		  Map<String, List<Ztree>> map=new HashMap<String, List<Ztree>>();
		  map.put("cond", temp);
		  SqlPara sql=Db.getSqlPara("menu.insertDataBatch", map);
		  Db.update(sql);
		  renderJson(ajaxDoneSuccess("操作成功"));
	  }*/
	  
	 
	/*  public void getZtreeJsonFromDB(){
		  String sql=Db.getSql("ztree.getZtreeJsonFromDB");
		 List<Record> ztreeList= Db.find(sql);		
		 String json=FastJson.getJson().toJson(ztreeList);
		 JSONArray array= JSONArray.parseArray(json);
		 JSONArray test=JsonUtil.listToTree(array, "id", "pId", "subMenuList");
		 System.out.println(test);	
		 renderJson(ztreeList);
	  }
	  */
	  
	  /****
	   * 得到EchartsData数据的测试返回
	   */
	  public void getEChartsData(){
		  String sql=Db.getSql("demo.getECharts");
		  List<Record> list=Db.find(sql);
		  renderJson(list);
	  }
	  
	  /***
	   * 测试jfinal的事务异常处理
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
	  
	 public void goCustom(){
		 render("form-custom.jsp");
	 }
}

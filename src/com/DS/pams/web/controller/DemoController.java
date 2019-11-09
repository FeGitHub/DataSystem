package com.DS.pams.web.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import com.DS.common.model.User;
import com.DS.pams.service.FileService;
import com.DS.pams.service.NotificationService;
import com.DS.pams.service.TaskService;
import com.DS.pams.service.impl.FileServiceImpl;
import com.DS.pams.service.impl.NotificationServiceImpl;
import com.DS.pams.service.impl.TaskServiceImpl;
import com.DS.pams.web.base.BaseController;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
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
	  
	  /****
	   * 进入用户邮箱
	   */
	  public void goMailBox(){
		  User user=(User)getSession().getAttribute("user");
		  //加载本用户全部通知条数
		  long endPageNumber=notificationService.getNotificationSize(user.getId()+"",null);
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
		  HttpSession session = getSession();
		  User user = (User)session.getAttribute("user");	
		  int result=notificationService.batchDel(list);
		  if(result>0){
			  //加载用户全部通知
			  List<Record> notifications=notificationService.getNotification(user.getStr("id"),null);
			  Long notificationSize=notificationService.getNotificationSize(user.getStr("id"),null);
			  session.setAttribute("notifications", notifications);
			  session.setAttribute("notificationSize", notificationSize);
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
				  renderJson(ajaxDoneSuccess("发送成功"));
			  }else{
				  renderJson(ajaxDoneError("发送失败"));
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
	  
	  
	 public void goCustom(){
		 render("form-custom.jsp");
	 }
}

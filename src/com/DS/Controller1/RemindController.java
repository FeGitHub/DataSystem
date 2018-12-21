package com.DS.Controller1;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.DS.common.model.DsRemind;
import com.DS.remind.service.RemindService;
import com.DS.remind.service.impl.RemindServiceImpl;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Inject;
/***
 * 
 * @author jeff
 * 备忘提醒的控制器
 *
 */
public class RemindController extends BaseController {
	@Inject(RemindServiceImpl.class)
	private RemindService remindService;
	/***
	 * 备忘提醒的管理页面
	 */
	public void goRemindList(){		
		render("remindList.jsp");
	}
	
	/****
	 * 获取预警规则信息
	 */
	public void getRemindDetails(){	
		DivPageCondition=getDivPageCondition();
		DivPageCondition.put("startDates", getPara("startDates"));
		DivPageCondition.put("endDates", getPara("endDates"));
		DivPageCondition.put("taskName", getPara("taskName"));				
        renderJson(remindService.getRemindDetails(DivPageCondition));
		
	}
	
	/****
	 * 添加备忘事项
	 */
	public void addRemindDetail(){
		boolean result;
		Map<String, String> map=new HashMap<String, String>();
		DsRemind  record = getModel(DsRemind.class,"record");	
		if(record.getStr("id")!=""&&record.getStr("id")!=null){
			result=record.update();
		}else{
			record.set("addTime", new Date());
			result=record.save();
		}
		if(result){
			map.put("code","233");
		}else{
			map.put("code","444");
		}
		renderJson(map);
	}
	
	/***
	 * 删除备忘事项
	 */
	/*public void delRemindDetail(){
		String id=getPara("id");
		Map<String, String> map=new HashMap<String, String>();
		if(RemindModel.dao.deleteById(Integer.parseInt(id))){
			map.put("code","233");
        }else{
        	map.put("code","444");
        }
		renderJson(map);
	}
	*/
}

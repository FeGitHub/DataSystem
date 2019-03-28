package com.DS.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.DS.common.model.Remind;
import com.DS.remind.service.RemindService;
import com.DS.remind.service.impl.RemindServiceImpl;
import com.DS.remind.vo.RemindVo;
import com.DS.utils.common.ObjectUtil;
import com.DS.web.base.BaseController;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
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
		RemindVo vo=getBean(RemindVo.class,"");
		Map<String,Object> limit=ObjectUtil.convertBeanToMap(vo);
		limit=getDivPageParam(limit);
        renderJson(remindService.getRemindDetails(limit));
		
	}
	
	/****
	 * 添加备忘事项
	 */
	public void updateRemindDetail(){	
		int result;
		Remind  record = getModel(Remind.class,"");	
		Record user = (Record)getSession().getAttribute("user");
		record.setUserId(user.get("id"));
		record.setUserName(user.get("userName"));
		if(record.getStr("id")!=""&&record.getStr("id")!=null){
			Map<String,Object> paramMap=ObjectUtil.convertBeanToMap(record);
		    SqlPara updateSql=Db.getSqlPara("remind.updateData", paramMap);
		    result=Db.update(updateSql);
		}else{
			Map<String,Object> paramMap=ObjectUtil.convertBeanToMap(record);
			paramMap.put("addTime", new Date());
		    SqlPara insertSql=Db.getSqlPara("remind.insertData", paramMap);
		    result=Db.update(insertSql);
		}
		if(result>0){
			renderJson(ajaxDoneSuccess("操作成功"));
		}else{
			renderJson(ajaxDoneError("操作失败"));
		}
		
	}
	
	/****
	 * 删除备忘事务
	 */
	public void delRemindDetail(){
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("id", getPara("id"));
		SqlPara delsql=Db.getSqlPara("remind.delById",paramMap);
		int result=Db.update(delsql);
		if(result>0){
			renderJson(ajaxDoneSuccess("删除成功"));
		}else{
			renderJson(ajaxDoneError("删除失败"));
		}
	}
}

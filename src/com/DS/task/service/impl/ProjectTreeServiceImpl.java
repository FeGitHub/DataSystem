package com.DS.task.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.DS.common.model.ProjectTree;
import com.DS.task.service.ProjectTreeService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
public class ProjectTreeServiceImpl implements ProjectTreeService {
  
	/****
	 * 得到用户今日应该开始或正在进行的工程任务
	 */
	@Override
	public List<ProjectTree> getTodayTreeTask(String userId) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("userId", userId);
		paramMap.put("dataLimit", true);
		SqlPara sql=Db.getSqlPara("projectTree.getProjectTreeTask", paramMap);
		ProjectTree dao=new ProjectTree();
		List<ProjectTree> projectTreeList=dao.find(sql);
		return projectTreeList;
	}

}

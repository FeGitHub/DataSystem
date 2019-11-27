package com.DS.pams.service.impl;
import java.util.List;
import java.util.Map;
import com.DS.common.model.User;
import com.DS.pams.service.UserService;
import com.DS.pams.vo.UserVo;
import com.DS.utils.common.DataTablesUtil;
import com.DS.utils.common.Util;
import com.DS.utils.common.SecretUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
/****
 * 
 * @author jeff
 * 用户服务层实现
 */
public class UserServiceImpl implements UserService {

	@Override
	public int register(UserVo user) {
		String password=SecretUtil.getMD5(user.getPassword());
		user.setPassword(password);
		Map<String,Object> paramMap=Util.convertBeanToMap(user);
		SqlPara sql = Db.getSqlPara("user.addData",paramMap);
		return Db.update(sql);
	}

	@Override
	public List<User> getAllUser() {
	    String sql=Db.getSql("user.getAllUser");
	    User dao=new User();
	    List<User> userList=dao.find(sql);	    
		return userList;
	}
  
	
	/****
	 * 获取用户分页列表
	 */
	@Override
	public Map<String, Object> getUserList(Map<String,Object> cond) {
		SqlPara sqlShow = Db.getSqlPara("user.getUserList",cond);
		SqlPara sqlTotal = Db.getSqlPara("user.getUserListSize",cond);      
		Map<String,Object> map=DataTablesUtil.getPageData(sqlShow, sqlTotal);
		return map;
	}

	

}

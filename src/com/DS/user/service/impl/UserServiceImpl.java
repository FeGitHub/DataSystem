package com.DS.user.service.impl;
import java.util.Map;
import com.DS.user.service.UserService;
import com.DS.user.vo.UserVo;
import com.DS.utils.common.ObjectUtil;
import com.DS.utils.common.SecretUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;

public class UserServiceImpl implements UserService {

	@Override
	public int register(UserVo user) {
		String password=SecretUtil.getMD5(user.getPassword());
		user.setPassword(password);
		Map<String,Object> paramMap=ObjectUtil.convertBeanToMap(user);
		SqlPara sql = Db.getSqlPara("user.addData",paramMap);
		return Db.update(sql);
	}

}

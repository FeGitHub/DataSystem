package com.DS.pams.service;

import com.DS.common.model.Config;

/***
 * 配置服务
 * 
 * @author jeffqiu
 *
 */
public interface ConfigService {
	/***
	 * 获取系统配置表信息
	 * 
	 * @param configName
	 *            配置名称
	 * @return
	 */
	public Config getConfig(String configName);
}

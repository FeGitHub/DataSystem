package com.DS.pams.service.impl;

import com.DS.common.model.Config;
import com.DS.pams.service.ConfigService;

public class ConfigServiceImpl implements ConfigService {

	@Override
	public Config getConfig(String configName) {
		Config config = Config.dao.findFirst("select * from config where config=?", configName);
		return config;
	}

}

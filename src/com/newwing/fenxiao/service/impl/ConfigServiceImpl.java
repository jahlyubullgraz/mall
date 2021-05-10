package com.newwing.fenxiao.service.impl;

import com.newwing.fenxiao.service.impl.BaseServiceImpl;
import com.newwing.fenxiao.entities.Config;
import com.newwing.fenxiao.service.IConfigService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("configService")
@Scope("prototype")
public class ConfigServiceImpl<T extends Config> extends BaseServiceImpl<T> implements IConfigService<T> {
	
}
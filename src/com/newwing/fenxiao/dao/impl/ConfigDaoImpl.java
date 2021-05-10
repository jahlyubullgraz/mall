package com.newwing.fenxiao.dao.impl;

import com.newwing.fenxiao.dao.impl.BaseDaoImpl;
import com.newwing.fenxiao.dao.IConfigDao;
import com.newwing.fenxiao.entities.Config;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("configDao")
@Scope("prototype")
public class ConfigDaoImpl extends BaseDaoImpl<Config> implements IConfigDao {
	
}

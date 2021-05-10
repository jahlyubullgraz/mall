package com.newwing.fenxiao.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.dao.IMenuDao;
import com.newwing.fenxiao.entities.Menu;

@Repository("menuDao")
@Scope("prototype")
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements IMenuDao {
	
}

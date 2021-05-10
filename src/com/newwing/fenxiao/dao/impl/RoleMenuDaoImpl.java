package com.newwing.fenxiao.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.dao.IRoleMenuDao;
import com.newwing.fenxiao.entities.RoleMenu;

@Repository("roleMenuDao")
@Scope("prototype")
public class RoleMenuDaoImpl extends BaseDaoImpl<RoleMenu> implements IRoleMenuDao {
	
}

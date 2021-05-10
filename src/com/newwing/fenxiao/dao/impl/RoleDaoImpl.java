package com.newwing.fenxiao.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.dao.IRoleDao;
import com.newwing.fenxiao.entities.Role;

@Repository("roleDao")
@Scope("prototype")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {
	
}

package com.newwing.fenxiao.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.newwing.fenxiao.dao.IRoleDao;
import com.newwing.fenxiao.entities.Role;
import com.newwing.fenxiao.service.IRoleService;

@Service("roleService")
@Scope("prototype")
public class RoleServiceImpl<T extends Role> extends BaseServiceImpl<T> implements IRoleService<T> {

	@Resource(name = "roleDao")
	private IRoleDao roleDao;

}
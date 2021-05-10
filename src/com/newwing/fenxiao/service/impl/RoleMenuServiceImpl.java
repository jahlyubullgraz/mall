package com.newwing.fenxiao.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.newwing.fenxiao.entities.RoleMenu;
import com.newwing.fenxiao.service.IRoleMenuService;

@Service("roleMenuService")
@Scope("prototype")
public class RoleMenuServiceImpl<T extends RoleMenu> extends BaseServiceImpl<T> implements IRoleMenuService<T> {

}
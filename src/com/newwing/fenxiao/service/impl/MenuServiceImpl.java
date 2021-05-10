package com.newwing.fenxiao.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.newwing.fenxiao.entities.Menu;
import com.newwing.fenxiao.service.IMenuService;

@Service("menuService")
@Scope("prototype")
public class MenuServiceImpl<T extends Menu> extends BaseServiceImpl<T> implements IMenuService<T> {

}
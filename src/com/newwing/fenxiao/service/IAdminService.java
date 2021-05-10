package com.newwing.fenxiao.service;

import com.newwing.fenxiao.service.IBaseService;
import com.newwing.fenxiao.entities.Admin;

public abstract interface IAdminService<T extends Admin> extends IBaseService<T> {
	
	public abstract Admin login(Admin paramAdmin);

	public abstract Admin getAdminName(String paramString);

}

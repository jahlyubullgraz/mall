package com.newwing.fenxiao.dao;

import com.newwing.fenxiao.dao.IBaseDao;
import com.newwing.fenxiao.entities.Admin;

public abstract interface IAdminDao extends IBaseDao<Admin> {
	
	public abstract Admin login(Admin paramAdmin);

	public abstract Admin getAdminName(String paramString);
}

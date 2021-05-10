package com.newwing.fenxiao.dao;

import com.newwing.fenxiao.dao.IBaseDao;
import com.newwing.fenxiao.entities.Orders;

public abstract interface IOrdersDao extends IBaseDao<Orders> {
	
	public abstract Orders findByNo(String paramString);

}

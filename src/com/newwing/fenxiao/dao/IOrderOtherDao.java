package com.newwing.fenxiao.dao;

import com.newwing.fenxiao.entities.OrderOther;

public abstract interface IOrderOtherDao extends IBaseDao<OrderOther> {
	
	public abstract OrderOther findByNo(String paramString);

}

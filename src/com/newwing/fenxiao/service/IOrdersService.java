package com.newwing.fenxiao.service;

import com.newwing.fenxiao.service.IBaseService;
import com.newwing.fenxiao.entities.Orders;

public abstract interface IOrdersService<T extends Orders> extends IBaseService<T> {
	
	public abstract Orders findByNo(String paramString);
	
	public abstract void saveSynchroOrder(Orders orders) throws Exception;
	
	public void saveTotalProfit() throws Exception;
	
}
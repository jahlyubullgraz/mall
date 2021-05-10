package com.newwing.fenxiao.service;

import com.newwing.fenxiao.entities.OrderOther;

public abstract interface IOrderOtherService<T extends OrderOther> extends IBaseService<T> {
	
	public abstract OrderOther findByNo(String paramString);
	
}
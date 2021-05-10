package com.newwing.fenxiao.service;

import com.newwing.fenxiao.service.IBaseService;
import com.newwing.fenxiao.entities.Recharge;

public abstract interface IRechargeService<T extends Recharge> extends IBaseService<T> {
	public abstract Recharge findByNo(String paramString);
}
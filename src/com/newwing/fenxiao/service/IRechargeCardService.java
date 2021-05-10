package com.newwing.fenxiao.service;

import com.newwing.fenxiao.service.IBaseService;
import com.newwing.fenxiao.entities.RechargeCard;

public abstract interface IRechargeCardService<T extends RechargeCard> extends IBaseService<T> {
	public abstract RechargeCard getByNo(String paramString);
}
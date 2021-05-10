package com.newwing.fenxiao.service;

import com.newwing.fenxiao.service.IBaseService;
import com.newwing.fenxiao.entities.Financial;
import java.util.List;

public abstract interface IFinancialService<T extends Financial> extends IBaseService<T> {
	public abstract List<Financial> getByUser(Integer paramInteger);
}
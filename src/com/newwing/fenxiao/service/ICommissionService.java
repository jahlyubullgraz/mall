package com.newwing.fenxiao.service;

import com.newwing.fenxiao.service.IBaseService;
import com.newwing.fenxiao.entities.Commission;
import java.util.List;

public abstract interface ICommissionService<T extends Commission> extends IBaseService<T> {
	public abstract List<Commission> getByUser(Integer paramInteger);
}
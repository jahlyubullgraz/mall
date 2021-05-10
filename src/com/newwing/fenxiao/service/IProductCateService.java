package com.newwing.fenxiao.service;

import com.newwing.fenxiao.service.IBaseService;
import com.newwing.fenxiao.entities.ProductCate;
import java.util.List;

public abstract interface IProductCateService<T extends ProductCate> extends IBaseService<T> {
	public abstract List<T> listByFatherId(int paramInt);
}
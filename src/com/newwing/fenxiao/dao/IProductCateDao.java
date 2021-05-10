package com.newwing.fenxiao.dao;

import com.newwing.fenxiao.dao.IBaseDao;
import com.newwing.fenxiao.entities.ProductCate;
import java.util.List;

public abstract interface IProductCateDao<T extends ProductCate> extends IBaseDao<T> {
	
	public abstract List<T> listByFatherId(int paramInt);

}

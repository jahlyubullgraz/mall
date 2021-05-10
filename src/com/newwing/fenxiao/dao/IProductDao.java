package com.newwing.fenxiao.dao;

import com.newwing.fenxiao.dao.IBaseDao;
import com.newwing.fenxiao.entities.Product;

public abstract interface IProductDao<T extends Product> extends IBaseDao<T> {
	
}

package com.newwing.fenxiao.service;

import com.newwing.fenxiao.service.IBaseService;
import com.newwing.fenxiao.entities.Product;

public abstract interface IProductService<T extends Product> extends IBaseService<T> {
}
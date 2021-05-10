package com.newwing.fenxiao.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.entities.Kami;
import com.newwing.fenxiao.entities.Product;
import com.newwing.fenxiao.service.IKamiService;
import com.newwing.fenxiao.service.IProductService;

@Repository("productService")
@Scope("prototype")
public class ProductServiceImpl<T extends Product> extends BaseServiceImpl<T> implements IProductService<T> {

	@Resource(name = "kamiService")
	private IKamiService<Kami> kamiService;

	public boolean delete(T baseBean) {
		return this.baseDao.delete(baseBean);
	}
	
}

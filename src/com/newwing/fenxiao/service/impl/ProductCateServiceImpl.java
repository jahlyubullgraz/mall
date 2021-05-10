package com.newwing.fenxiao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.dao.IProductCateDao;
import com.newwing.fenxiao.entities.Product;
import com.newwing.fenxiao.entities.ProductCate;
import com.newwing.fenxiao.service.IProductCateService;
import com.newwing.fenxiao.service.IProductService;

@Repository("productCateService")
@Scope("prototype")
public class ProductCateServiceImpl<T extends ProductCate> extends BaseServiceImpl<T>
		implements IProductCateService<T> {

	@Resource(name = "productCateDao")
	private IProductCateDao<T> productCateDao;

	@Resource(name = "productService")
	private IProductService<Product> productService;

	public boolean delete(T baseBean) {
		return this.productCateDao.delete(baseBean);
	}

	public List<T> listByFatherId(int fid) {
		return this.productCateDao.listByFatherId(fid);
	}
}
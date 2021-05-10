package com.newwing.fenxiao.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.dao.IProductCateDao;
import com.newwing.fenxiao.entities.ProductCate;

@Repository("productCateDao")
@Scope("prototype")
public class ProductCateDaoImpl<T extends ProductCate> extends BaseDaoImpl<T> implements IProductCateDao<T> {
	public List<T> listByFatherId(int fid) {
		String hql = "from ProductCate where fatherId=" + fid;
		List list = createQuery(hql).list();
		return list;
	}
}
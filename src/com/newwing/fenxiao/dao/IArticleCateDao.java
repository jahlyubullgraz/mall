package com.newwing.fenxiao.dao;

import com.newwing.fenxiao.dao.IBaseDao;
import com.newwing.fenxiao.entities.ArticleCate;
import java.util.List;

public abstract interface IArticleCateDao<T extends ArticleCate> extends IBaseDao<T> {
	
	public abstract List<T> listByFatherId(int paramInt);

}

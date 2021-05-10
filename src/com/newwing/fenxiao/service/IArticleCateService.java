package com.newwing.fenxiao.service;

import com.newwing.fenxiao.service.IBaseService;
import com.newwing.fenxiao.entities.ArticleCate;
import java.util.List;

public abstract interface IArticleCateService<T extends ArticleCate> extends IBaseService<T> {
	public abstract List<T> listByFatherId(int paramInt);
}

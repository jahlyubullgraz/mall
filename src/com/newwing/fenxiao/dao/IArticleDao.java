package com.newwing.fenxiao.dao;

import com.newwing.fenxiao.dao.IBaseDao;
import com.newwing.fenxiao.entities.Article;

public abstract interface IArticleDao<T extends Article> extends IBaseDao<T> {
	
}

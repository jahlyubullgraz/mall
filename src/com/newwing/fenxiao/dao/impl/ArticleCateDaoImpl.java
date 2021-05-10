package com.newwing.fenxiao.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.dao.IArticleCateDao;
import com.newwing.fenxiao.entities.ArticleCate;

@Repository("articleCateDao")
@Scope("prototype")
public class ArticleCateDaoImpl<T extends ArticleCate> extends BaseDaoImpl<T> implements IArticleCateDao<T> {
	public List<T> listByFatherId(int fid) {
		String hql = "from ArticleCate where fatherId=" + fid + " and deleted = 0";
		List list = createQuery(hql).list();
		return list;
	}
}

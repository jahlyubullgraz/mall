package com.newwing.fenxiao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.dao.IArticleCateDao;
import com.newwing.fenxiao.entities.ArticleCate;
import com.newwing.fenxiao.service.IArticleCateService;

@Repository("articleCateService")
@Scope("prototype")
public class ArticleCateServiceImpl<T extends ArticleCate> extends BaseServiceImpl<T>
		implements IArticleCateService<T> {

	@Resource(name = "articleCateDao")
	private IArticleCateDao<T> articleCateDao;

	public List<T> listByFatherId(int fid) {
		return this.articleCateDao.listByFatherId(fid);
	}
	
}
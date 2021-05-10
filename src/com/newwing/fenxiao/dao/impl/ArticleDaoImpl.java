package com.newwing.fenxiao.dao.impl;

import com.newwing.fenxiao.dao.impl.BaseDaoImpl;
import com.newwing.fenxiao.dao.IArticleDao;
import com.newwing.fenxiao.entities.Article;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("articleDao")
@Scope("prototype")
public class ArticleDaoImpl<T extends Article> extends BaseDaoImpl<T> implements IArticleDao<T> {

}
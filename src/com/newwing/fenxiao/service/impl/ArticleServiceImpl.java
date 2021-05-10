package com.newwing.fenxiao.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.entities.Article;
import com.newwing.fenxiao.service.IArticleService;

@Repository("articleService")
@Scope("prototype")
public class ArticleServiceImpl<T extends Article> extends BaseServiceImpl<T> implements IArticleService<T> {

}
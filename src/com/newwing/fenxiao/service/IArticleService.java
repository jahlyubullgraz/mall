package com.newwing.fenxiao.service;

import com.newwing.fenxiao.service.IBaseService;
import com.newwing.fenxiao.entities.Article;

public abstract interface IArticleService<T extends Article> extends IBaseService<T> {
}
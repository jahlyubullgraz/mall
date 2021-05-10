package com.newwing.fenxiao.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.dao.IKamiDao;
import com.newwing.fenxiao.entities.Kami;

@Repository("kamiDao")
@Scope("prototype")
public class KamiDaoImpl<T extends Kami> extends BaseDaoImpl<T> implements IKamiDao<T> {

}
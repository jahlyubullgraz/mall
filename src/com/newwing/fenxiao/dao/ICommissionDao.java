package com.newwing.fenxiao.dao;

import java.util.List;

import com.newwing.fenxiao.entities.Commission;

public abstract interface ICommissionDao extends IBaseDao<Commission> {
	
	public abstract List<Commission> getByUser(Integer paramInteger);

}

package com.newwing.fenxiao.dao;

import java.util.List;

import com.newwing.fenxiao.entities.Financial;

public abstract interface IFinancialDao extends IBaseDao<Financial> {
	
	public abstract List<Financial> getByUser(Integer paramInteger);

}

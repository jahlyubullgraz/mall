package com.newwing.fenxiao.dao;

import com.newwing.fenxiao.dao.IBaseDao;
import com.newwing.fenxiao.entities.Recharge;

public abstract interface IRechargeDao extends IBaseDao<Recharge> {
	
	public abstract Recharge findByNo(String paramString);

}

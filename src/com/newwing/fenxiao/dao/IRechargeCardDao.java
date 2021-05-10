package com.newwing.fenxiao.dao;

import com.newwing.fenxiao.dao.IBaseDao;
import com.newwing.fenxiao.entities.RechargeCard;

public abstract interface IRechargeCardDao extends IBaseDao<RechargeCard> {
	
	public abstract RechargeCard getByNo(String paramString);

}

package com.newwing.fenxiao.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.newwing.fenxiao.dao.IRechargeCardDao;
import com.newwing.fenxiao.entities.RechargeCard;
import com.newwing.fenxiao.service.IRechargeCardService;

@Service("rechargeCardService")
@Scope("prototype")
public class RechargeCardServiceImpl<T extends RechargeCard> extends BaseServiceImpl<T>
		implements IRechargeCardService<T> {

	@Resource(name = "rechargeCardDao")
	IRechargeCardDao rechargeCardDao;

	public RechargeCard getByNo(String no) {
		return this.rechargeCardDao.getByNo(no);
	}
	
}
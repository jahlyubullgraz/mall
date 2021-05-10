package com.newwing.fenxiao.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.newwing.fenxiao.dao.IRechargeDao;
import com.newwing.fenxiao.entities.Recharge;
import com.newwing.fenxiao.service.IRechargeService;

@Service("rechargeService")
@Scope("prototype")
public class RechargeServiceImpl<T extends Recharge> extends BaseServiceImpl<T> implements IRechargeService<T> {

	@Resource(name = "rechargeDao")
	private IRechargeDao rechargeDao;

	public Recharge findByNo(String no) {
		return this.rechargeDao.findByNo(no);
	}
	
}

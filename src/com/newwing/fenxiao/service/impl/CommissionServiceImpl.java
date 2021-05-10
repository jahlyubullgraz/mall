package com.newwing.fenxiao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.newwing.fenxiao.dao.ICommissionDao;
import com.newwing.fenxiao.entities.Commission;
import com.newwing.fenxiao.service.ICommissionService;

@Service("commissionService")
@Scope("prototype")
public class CommissionServiceImpl<T extends Commission> extends BaseServiceImpl<T> implements ICommissionService<T> {

	@Resource(name = "commissionDao")
	private ICommissionDao commissionDao;

	public List<Commission> getByUser(Integer userId) {
		return this.commissionDao.getByUser(userId);
	}
	
}
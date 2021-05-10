package com.newwing.fenxiao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.newwing.fenxiao.dao.IFinancialDao;
import com.newwing.fenxiao.entities.Financial;
import com.newwing.fenxiao.service.IFinancialService;

@Service("financialService")
@Scope("prototype")
public class FinancialServiceImpl<T extends Financial> extends BaseServiceImpl<T> implements IFinancialService<T> {

	@Resource(name = "financialDao")
	private IFinancialDao financialDao;

	public List<Financial> getByUser(Integer userId) {
		return this.financialDao.getByUser(userId);
	}
	
}
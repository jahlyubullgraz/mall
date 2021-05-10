package com.newwing.fenxiao.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.newwing.fenxiao.dao.IOrderOtherDao;
import com.newwing.fenxiao.entities.OrderOther;
import com.newwing.fenxiao.service.IOrderOtherService;

@Service("orderOtherService")
@Scope("prototype")
public class OrderOtherServiceImpl<T extends OrderOther> extends BaseServiceImpl<T> implements IOrderOtherService<T> {

	@Resource(name = "orderOtherDao")
	private IOrderOtherDao orderOtherDao;
	
	public OrderOther findByNo(String no) {
		return this.orderOtherDao.findByNo(no);
	}
	
}

package com.newwing.fenxiao.service.impl;

import com.newwing.fenxiao.service.impl.BaseServiceImpl;
import com.newwing.fenxiao.dao.IOrdersDao;
import com.newwing.fenxiao.entities.Orders;
import com.newwing.fenxiao.service.IOrdersService;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("ordersService")
@Scope("prototype")
public class OrdersServiceImpl<T extends Orders> extends BaseServiceImpl<T> implements IOrdersService<T> {

	@Resource(name = "ordersDao")
	private IOrdersDao ordersDao;

	public Orders findByNo(String no) {
		return this.ordersDao.findByNo(no);
	}
}

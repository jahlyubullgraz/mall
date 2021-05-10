package com.newwing.fenxiao.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.dao.IOrderOtherDao;
import com.newwing.fenxiao.entities.OrderOther;

@Repository("orderOtherDao")
@Scope("prototype")
public class OrderOtherDaoImpl extends BaseDaoImpl<OrderOther> implements IOrderOtherDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public OrderOther findByNo(String no) {
		String hql = "from OrderOther where no=:no";
		OrderOther orderOther = (OrderOther) getSession().createQuery(hql).setString("no", no).uniqueResult();
		return orderOther;
	}

}

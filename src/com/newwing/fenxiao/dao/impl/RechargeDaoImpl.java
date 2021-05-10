package com.newwing.fenxiao.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.dao.IRechargeDao;
import com.newwing.fenxiao.entities.Recharge;

@Repository("rechargeDao")
@Scope("prototype")
public class RechargeDaoImpl extends BaseDaoImpl<Recharge> implements IRechargeDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Recharge findByNo(String no) {
		String hql = "from Recharge where no=:no";
		Recharge recharge = (Recharge) getSession().createQuery(hql).setString("no", no).uniqueResult();
		return recharge;
	}

}

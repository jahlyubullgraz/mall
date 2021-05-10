package com.newwing.fenxiao.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.dao.ICommissionDao;
import com.newwing.fenxiao.entities.Commission;

@Repository("commissionDao")
@Scope("prototype")
public class CommissionDaoImpl extends BaseDaoImpl<Commission> implements ICommissionDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public List<Commission> getByUser(Integer userId) {
		String hql = "from Commission where user.id=:userId and deleted=0";

		List commissionList = getSession().createQuery(hql).setInteger("userId", userId.intValue()).list();
		return commissionList;
	}
	
}

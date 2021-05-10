package com.newwing.fenxiao.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.dao.IPhoneValidateCodeDao;
import com.newwing.fenxiao.entities.PhoneValidateCode;

@Repository("phoneValidateCodeDao")
@Scope("prototype")
public class PhoneValidateCodeDaoImpl<T extends PhoneValidateCode> extends BaseDaoImpl<T>
		implements IPhoneValidateCodeDao<T> {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public PhoneValidateCode getPhoneValidateCode(String phone, String code) {
		String hql = "from PhoneValidateCode where phone=:phone and code=:code and deleted=0 and status=0";
		PhoneValidateCode phoneValidateCode = (PhoneValidateCode) getSession().createQuery(hql)
				.setString("phone", phone).setString("code", code).uniqueResult();
		return phoneValidateCode;
	}
}
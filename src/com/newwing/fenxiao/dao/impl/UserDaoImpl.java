package com.newwing.fenxiao.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.newwing.fenxiao.dao.IUserDao;
import com.newwing.fenxiao.entities.User;

@Repository("userDao")
@Scope("prototype")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public User getUserByName(String name) {
		String hql = "from User where name=:name and deleted=0";
		User user = (User) getSession().createQuery(hql).setString("name", name).uniqueResult();
		return user;
	}

	public User login(String name, String password) {
		String hql = "from User where name=:name and password=:password and deleted=0";
		User user = (User) getSession().createQuery(hql).setString("name", name).setString("password", password)
				.uniqueResult();
		return user;
	}

	public User getUserByPhone(String phone) {
		String hql = "from User where phone=:phone and deleted=0";
		User user = (User) getSession().createQuery(hql).setString("phone", phone).uniqueResult();
		return user;
	}
	
	public User getUserByNo(String no) {
		String hql = "from User where no=:no and deleted=0";
		User user = (User) getSession().createQuery(hql).setString("no", no).uniqueResult();
		return user;
	}
	
	public User getUserByNoAndType(String no, String type) {
		String hql = "from User where no=:no and type=:type and deleted=0";
		User user = (User) getSession().createQuery(hql).setString("no", no).setString("type", type).uniqueResult();
		return user;
	}
	
	public User getUserByOpenId(String openId) {
		String hql = "from User where openId=:openId and deleted=0";
		User user = (User) getSession().createQuery(hql).setString("openId", openId).uniqueResult();
		return user;
	}


	public List<User> levelUserList(String no) {
		String hql = "from User where superior like :no and deleted=0";

		List levelUserList = getSession().createQuery(hql).setString("no", "%;" + no + ";%").list();
		return levelUserList;
	}

	public List<User> levelUserTodayList(String no) {
		String hql = "from User where superior like '%:no%' and deleted=0 and date(createDate)=curdate()";

		List levelUserTodayList = getSession().createQuery(hql).setString("no", no).list();
		return levelUserTodayList;
	}

	public List<User> levelUserTodayStatusList(String no) {
		String hql = "from User where superior like '%:no%' and deleted=0 and date(statusDate)=curdate()";

		List levelUserTodayStatusList = getSession().createQuery(hql).setString("no", no).list();
		return levelUserTodayStatusList;
	}
	
	public List<User> findProfitUserList() {
		String hql = "from User where type = '4' ";

		List profitUserList = getSession().createQuery(hql).list();
		return profitUserList;
	}

	public User getUserByNameAndPhone(String name, String phone) {
		String hql = "from User where name=:name and phone=:phone and deleted=0";
		User user = (User) getSession().createQuery(hql).setString("name", name).setString("phone", phone)
				.uniqueResult();
		return user;
	}
	
}
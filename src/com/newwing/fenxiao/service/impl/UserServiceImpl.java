package com.newwing.fenxiao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.newwing.fenxiao.dao.IUserDao;
import com.newwing.fenxiao.entities.User;
import com.newwing.fenxiao.service.IUserService;

@Service("userService")
@Scope("prototype")
public class UserServiceImpl<T extends User> extends BaseServiceImpl<T> implements IUserService<T> {

	@Resource(name = "userDao")
	private IUserDao userDao;

	public User getUserByName(String name) {
		return this.userDao.getUserByName(name);
	}

	public User login(String name, String password) {
		return this.userDao.login(name, password);
	}

	public User getUserByPhone(String phone) {
		return this.userDao.getUserByPhone(phone);
	}

	public User getUserByNo(String no) {
		return this.userDao.getUserByNo(no);
	}
	
	public User getUserByOpenId(String openId) {
		return this.userDao.getUserByOpenId(openId);
	}

	public List<User> levelUserList(String no) {
		return this.userDao.levelUserList(no);
	}

	public List<User> levelUserTodayList(String no) {
		return this.userDao.levelUserTodayList(no);
	}

	public List<User> levelUserTodayStatusList(String no) {
		return this.userDao.levelUserTodayStatusList(no);
	}

	public User getUserByNameAndPhone(String name, String phone) {
		return this.userDao.getUserByNameAndPhone(name, phone);
	}
	
}
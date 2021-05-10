package com.newwing.fenxiao.dao;

import com.newwing.fenxiao.dao.IBaseDao;
import com.newwing.fenxiao.entities.User;
import java.util.List;

public abstract interface IUserDao extends IBaseDao<User> {
	
	public abstract User getUserByName(String paramString);

	public abstract User getUserByPhone(String paramString);

	public abstract User getUserByNameAndPhone(String paramString1, String paramString2);

	public abstract User login(String paramString1, String paramString2);

	public abstract User getUserByNo(String paramString);
	
	public abstract User getUserByNoAndType(String paramString1, String paramString2);
	
	public abstract User getUserByOpenId(String openId);

	public abstract List<User> levelUserList(String paramString);

	public abstract List<User> levelUserTodayList(String paramString);

	public abstract List<User> levelUserTodayStatusList(String paramString);
	
	public abstract List<User> findProfitUserList();
	
}

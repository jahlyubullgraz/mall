package com.newwing.fenxiao.service;

import com.newwing.fenxiao.service.IBaseService;
import com.newwing.fenxiao.entities.User;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public abstract interface IUserService<T extends User> extends IBaseService<T> {
	
	public abstract User getUserByName(String paramString);

	public abstract User getUserByPhone(String paramString);

	public abstract User getUserByNameAndPhone(String paramString1, String paramString2);

	public abstract User login(String paramString1, String paramString2);

	public abstract User getUserByNo(String paramString);
	
	public abstract User getUserByNoAndType(String no, String type);
	
	public abstract User getUserByOpenId(String openId);

	public abstract List<User> levelUserList(String paramString);

	public abstract List<User> levelUserTodayList(String paramString);

	public abstract List<User> levelUserTodayStatusList(String paramString);
	
	public abstract List<User> findProfitUserList();
	
	public void register(User user, String type, String phone, String password,
			String password2, String source, HttpServletRequest request) throws Exception;
	
	public void getSuperUser(int counter, String type, User user) throws Exception;
	
	public String getSuperUserids(User user);
	
	public String generateQrCode(String userNo, HttpServletRequest request) throws Exception;
	
}

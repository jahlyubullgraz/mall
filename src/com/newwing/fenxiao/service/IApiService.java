package com.newwing.fenxiao.service;

public abstract interface IApiService {
	
	public void userRegister(String phone, String password, String password2, String source) throws Exception;
	
	public void agencyRegister(String phone, String password, String source) throws Exception;

}

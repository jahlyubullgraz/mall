package com.newwing.fenxiao.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.newwing.fenxiao.service.IApiService;
import com.newwing.fenxiao.utils.HttpSender;

@Service("apiService")
@Scope("prototype")
public class ApiServiceImpl implements IApiService {

	// 125.76.246.102:8082
	public void userRegister(String phone, String password, String password2, String source) throws Exception {
		HttpSender httpSender = new HttpSender();
		String url = "http://www.9hgwsc.com/system/appUserCenter/userRegister.do?phone=" + phone
				+ "&password=" + password + "&password2" + password2 + "&source=" + source;
		String successFlag = httpSender.sendPost(url, null);
		if (!"SUCCESS".equalsIgnoreCase(successFlag)) {
			throw new Exception("用户同步失败");
		}
	}
	
	public void agencyRegister(String phone, String password, String source) throws Exception {
		HttpSender httpSender = new HttpSender();
		String url = " http://www.9hgwsc.com/system/appUserCenter/agencyRegister.do?phone=" + phone
				+ "&password=" + password + "&source=" + source;
		String successFlag = httpSender.sendPost(url, null);
		if (!"SUCCESS".equalsIgnoreCase(successFlag)) {
			throw new Exception("商户同步失败");
		}
	}
	
}
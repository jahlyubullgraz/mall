package com.newwing.fenxiao.service;

import java.util.Map;

public interface IWeixinService {
	
	public Map<String, String> transfer(String openid, Double amount, String partner_trade_no) throws Exception;
	
}

package com.weixin.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.utils.Sha1Util;

public class MainServlet extends HttpServlet {

	//网页授权获取用户信息
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//共账号及商户相关参数
		String appid = "wxba27d22962d8af13";
//		String backUri = "http://" +request.getServerName() + "/topayServlet";
		String backUri = "http://www.genobien.com/topayServlet";
		//授权后要跳转的链接所需的参数一般有会员号，金额，订单号之类，
		//最好自己带上一个加密字符串将金额加上一个自定义的key用MD5签名或者自己写的签名,
		//比如 Sign = %3D%2F%CS% 
		String orderNo = appid + Sha1Util.getTimeStamp();
		backUri = backUri + "?userId=b88001&orderNo=" + orderNo + "&describe=test&money=0.01";
		//URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
		backUri = URLEncoder.encode(backUri);
		//scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid=" + appid+
				"&redirect_uri=" + backUri+
				"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		response.sendRedirect(url);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

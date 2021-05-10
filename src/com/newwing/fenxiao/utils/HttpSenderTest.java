package com.newwing.fenxiao.utils;

public class HttpSenderTest {
	public static void main(String[] args) {
		String mobile = "18950179627";// 手机号码，多个号码使用","分割
		String msg = "您好，您的验证码是123456";// 短信内容
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String extno = null;// 扩展码
		try {
			String returnString = HttpSender.batchSend(mobile, msg, needstatus, extno);
			System.out.println(returnString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

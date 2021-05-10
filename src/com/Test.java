package com;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.newwing.fenxiao.utils.Base64;
import com.newwing.fenxiao.utils.Md5;

public class Test {

	public static void main(String[] args) {
//		String ORDER_NO = "201607071651231885";
//		System.out.println(Base64.getBase64(ORDER_NO));
//		System.out.println(Base64.getFromBase64(Base64.getBase64(ORDER_NO)));
		
		String ORDER_NO = Base64.getFromBase64("MTgwNDkxMzE3NTM=");
		String USER_ID = Base64.getFromBase64("OTc1YjRkMTg0YTUzNDU3ODliNjllOWI5MmI4YzU2Mjk=");
		String ORDER_PRICE = Base64.getFromBase64("MC44MA==");
		String ORDER_CONFIRMTIME = Base64.getFromBase64("MTQ2Nzk2NDMxNDAwMA==");
		String ORDER_STATUS = Base64.getFromBase64("Qg==");
		String TIMESTAMP = Base64.getFromBase64("MTQ4NDUzODI4MjgwOQ==");
		
		String strObj = ORDER_NO + USER_ID + ORDER_PRICE + ORDER_CONFIRMTIME + ORDER_STATUS + TIMESTAMP;
		System.out.println("ORDER_NO >>> " + ORDER_NO);
		System.out.println("USER_ID >>> " + USER_ID);
		System.out.println("ORDER_CONFIRMTIME >>> " + ORDER_CONFIRMTIME);
		System.out.println("ORDER_PRICE >>> " + ORDER_PRICE);
		System.out.println("ORDER_STATUS >>> " + ORDER_STATUS);
		System.out.println("TIMESTAMP >>> " + TIMESTAMP);
		System.out.println(strObj);
		System.out.println(Md5.getMD5Code(strObj));
		
		String res = stampToDate(TIMESTAMP);
		System.out.println("res >>>>>>>>>> " + res);
	}
	
	/* 
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
//		Date date = simpleDateFormat.parse(res);
		return res;
	}

}

package com.newwing.fenxiao.dao;

import com.newwing.fenxiao.dao.IBaseDao;
import com.newwing.fenxiao.entities.PhoneValidateCode;

public abstract interface IPhoneValidateCodeDao<T extends PhoneValidateCode> extends IBaseDao<T> {
	
	public abstract PhoneValidateCode getPhoneValidateCode(String paramString1, String paramString2);

}

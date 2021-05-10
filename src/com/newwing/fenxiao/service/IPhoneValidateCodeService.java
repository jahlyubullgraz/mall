package com.newwing.fenxiao.service;

import com.newwing.fenxiao.service.IBaseService;
import com.newwing.fenxiao.entities.PhoneValidateCode;

public abstract interface IPhoneValidateCodeService<T extends PhoneValidateCode> extends IBaseService<T> {
	public abstract PhoneValidateCode getPhoneValidateCode(String paramString1, String paramString2);
}
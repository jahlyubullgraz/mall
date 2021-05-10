package com.newwing.fenxiao.service.impl;

import com.newwing.fenxiao.service.impl.BaseServiceImpl;
import com.newwing.fenxiao.dao.IPhoneValidateCodeDao;
import com.newwing.fenxiao.entities.PhoneValidateCode;
import com.newwing.fenxiao.service.IPhoneValidateCodeService;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("phoneValidateCodeService")
@Scope("prototype")
public class PhoneValidateCodeServiceImpl<T extends PhoneValidateCode> extends BaseServiceImpl<T>
		implements IPhoneValidateCodeService<T> {

	@Resource(name = "phoneValidateCodeDao")
	private IPhoneValidateCodeDao<PhoneValidateCode> phoneValidateCodeDao;

	public PhoneValidateCode getPhoneValidateCode(String phone, String code) {
		return this.phoneValidateCodeDao.getPhoneValidateCode(phone, code);
	}
	
}
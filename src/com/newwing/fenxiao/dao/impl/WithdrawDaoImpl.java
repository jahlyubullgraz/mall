package com.newwing.fenxiao.dao.impl;

import com.newwing.fenxiao.dao.impl.BaseDaoImpl;
import com.newwing.fenxiao.dao.IWithdrawDao;
import com.newwing.fenxiao.entities.Withdraw;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("withdrawDao")
@Scope("prototype")
public class WithdrawDaoImpl extends BaseDaoImpl<Withdraw> implements IWithdrawDao {
	
}

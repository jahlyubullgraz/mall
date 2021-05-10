package com.newwing.fenxiao.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.newwing.fenxiao.entities.Withdraw;
import com.newwing.fenxiao.service.IWithdrawService;

@Service("withdrawService")
@Scope("prototype")
public class WithdrawServiceImpl<T extends Withdraw> extends BaseServiceImpl<T> implements IWithdrawService<T> {

}
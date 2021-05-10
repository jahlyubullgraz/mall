package com.newwing.fenxiao.service;

import com.newwing.fenxiao.service.IBaseService;
import com.newwing.fenxiao.entities.Message;

public abstract interface IMessageService<T extends Message> extends IBaseService<T> {
}
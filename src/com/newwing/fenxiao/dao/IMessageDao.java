package com.newwing.fenxiao.dao;

import com.newwing.fenxiao.dao.IBaseDao;
import com.newwing.fenxiao.entities.Message;

public abstract interface IMessageDao<T extends Message> extends IBaseDao<T> {
	
}

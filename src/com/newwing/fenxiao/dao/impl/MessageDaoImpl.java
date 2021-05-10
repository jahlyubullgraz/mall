package com.newwing.fenxiao.dao.impl;

import com.newwing.fenxiao.dao.impl.BaseDaoImpl;
import com.newwing.fenxiao.dao.IMessageDao;
import com.newwing.fenxiao.entities.Message;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("messageDao")
@Scope("prototype")
public class MessageDaoImpl<T extends Message> extends BaseDaoImpl<T> implements IMessageDao<T> {

}

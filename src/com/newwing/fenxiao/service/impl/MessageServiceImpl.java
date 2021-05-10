package com.newwing.fenxiao.service.impl;

import com.newwing.fenxiao.service.impl.BaseServiceImpl;
import com.newwing.fenxiao.entities.Message;
import com.newwing.fenxiao.service.IMessageService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("messageService")
@Scope("prototype")
public class MessageServiceImpl<T extends Message> extends BaseServiceImpl<T> implements IMessageService<T> {

}

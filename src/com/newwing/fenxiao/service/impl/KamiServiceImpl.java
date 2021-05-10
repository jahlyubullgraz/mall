package com.newwing.fenxiao.service.impl;

import com.newwing.fenxiao.service.impl.BaseServiceImpl;
import com.newwing.fenxiao.entities.Kami;
import com.newwing.fenxiao.service.IKamiService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("kamiService")
@Scope("prototype")
public class KamiServiceImpl<T extends Kami> extends BaseServiceImpl<T> implements IKamiService<T> {
	
}
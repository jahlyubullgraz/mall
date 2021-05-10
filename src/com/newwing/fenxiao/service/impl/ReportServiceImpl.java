package com.newwing.fenxiao.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.newwing.fenxiao.dao.IOrdersDao;
import com.newwing.fenxiao.entities.ReportVO;
import com.newwing.fenxiao.service.IReportService;

@Service("reportService")
@Scope("prototype")
public class ReportServiceImpl implements IReportService {

	@Resource(name = "ordersDao")
	private IOrdersDao ordersDao;

	public ReportVO getReportVO(Integer adminId) {
		ReportVO reportVO = new ReportVO();
		// 昨日数据
		this.ordersDao.list("select count(*) from Orders ");// 
		Double tradeAmtHistory = null;
		Double tradeAmtMonth = null;
		Double tradeAmtYestoday = null;
		Integer tradeCountHistory = null;
		Integer tradeCountMonth = null;
		Integer tradeCountYestoday = null;
		Integer userCountHistory = null;
		Integer userCountMonth = null;
		Integer userCountYestoday = null;
		
//		return this.ordersDao.getAdminName(name);
		reportVO.setTradeAmtHistory(tradeAmtHistory);
		reportVO.setTradeAmtMonth(tradeAmtMonth);
		reportVO.setTradeAmtYestoday(tradeAmtYestoday);
		reportVO.setTradeCountHistory(tradeCountHistory);
		reportVO.setTradeCountMonth(tradeCountMonth);
		reportVO.setTradeCountYestoday(tradeCountYestoday);
		reportVO.setUserCountHistory(userCountHistory);
		reportVO.setUserCountMonth(userCountMonth);
		reportVO.setUserCountYestoday(userCountYestoday);
		
		// 当月数据
		
		// 历史数据
		return null;
	}
	
}
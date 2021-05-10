package com.newwing.fenxiao.service;

import com.newwing.fenxiao.entities.ReportVO;

public abstract interface IReportService {
	
	public abstract ReportVO getReportVO(Integer adminId);

}

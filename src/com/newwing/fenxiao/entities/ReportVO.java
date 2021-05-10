package com.newwing.fenxiao.entities;

import java.io.Serializable;

public class ReportVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer tradeCountYestoday;// 昨日交易笔数
	private Double tradeAmtYestoday;// 昨日交易金额
	private Integer userCountYestoday;// 昨日活跃终端数量

	private Integer tradeCountMonth;// 当月交易笔数
	private Double tradeAmtMonth;// 当月交易金额
	private Integer userCountMonth;// 当月活跃终端数量
	
	private Integer tradeCountHistory;// 历史交易笔数
	private Double tradeAmtHistory;// 历史交易金额
	private Integer userCountHistory;// 历史活跃终端数量

	public Integer getTradeCountYestoday() {
		return tradeCountYestoday;
	}

	public void setTradeCountYestoday(Integer tradeCountYestoday) {
		this.tradeCountYestoday = tradeCountYestoday;
	}

	public Double getTradeAmtYestoday() {
		return tradeAmtYestoday;
	}

	public void setTradeAmtYestoday(Double tradeAmtYestoday) {
		this.tradeAmtYestoday = tradeAmtYestoday;
	}

	public Integer getUserCountYestoday() {
		return userCountYestoday;
	}

	public void setUserCountYestoday(Integer userCountYestoday) {
		this.userCountYestoday = userCountYestoday;
	}

	public Integer getTradeCountMonth() {
		return tradeCountMonth;
	}

	public void setTradeCountMonth(Integer tradeCountMonth) {
		this.tradeCountMonth = tradeCountMonth;
	}

	public Double getTradeAmtMonth() {
		return tradeAmtMonth;
	}

	public void setTradeAmtMonth(Double tradeAmtMonth) {
		this.tradeAmtMonth = tradeAmtMonth;
	}

	public Integer getUserCountMonth() {
		return userCountMonth;
	}

	public void setUserCountMonth(Integer userCountMonth) {
		this.userCountMonth = userCountMonth;
	}

	public Integer getTradeCountHistory() {
		return tradeCountHistory;
	}

	public void setTradeCountHistory(Integer tradeCountHistory) {
		this.tradeCountHistory = tradeCountHistory;
	}

	public Double getTradeAmtHistory() {
		return tradeAmtHistory;
	}

	public void setTradeAmtHistory(Double tradeAmtHistory) {
		this.tradeAmtHistory = tradeAmtHistory;
	}

	public Integer getUserCountHistory() {
		return userCountHistory;
	}

	public void setUserCountHistory(Integer userCountHistory) {
		this.userCountHistory = userCountHistory;
	}

}

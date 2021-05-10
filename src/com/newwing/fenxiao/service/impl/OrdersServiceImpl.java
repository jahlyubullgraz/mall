package com.newwing.fenxiao.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.newwing.fenxiao.dao.IOrdersDao;
import com.newwing.fenxiao.entities.Commission;
import com.newwing.fenxiao.entities.Config;
import com.newwing.fenxiao.entities.Financial;
import com.newwing.fenxiao.entities.Orders;
import com.newwing.fenxiao.entities.User;
import com.newwing.fenxiao.service.ICommissionService;
import com.newwing.fenxiao.service.IConfigService;
import com.newwing.fenxiao.service.IFinancialService;
import com.newwing.fenxiao.service.IOrdersService;
import com.newwing.fenxiao.service.IUserService;

@Service("ordersService")
@Scope("prototype")
public class OrdersServiceImpl<T extends Orders> extends BaseServiceImpl<T> implements IOrdersService<T> {

	@Resource(name = "ordersDao")
	private IOrdersDao ordersDao;
	@Resource(name = "financialService")
	private IFinancialService<Financial> financialService;
	@Resource(name = "configService")
	private IConfigService<Config> configService;
	@Resource(name = "userService")
	private IUserService<User> userService;
	@Resource(name = "commissionService")
	private ICommissionService<Commission> commissionService;
	
	public Orders findByNo(String no) {
		return this.ordersDao.findByNo(no);
	}
	
	/**
	 * 同步订单
	 * @throws Exception
	 */
	public void saveSynchroOrder(Orders orders) throws Exception {
		if (orders != null) {// 订单存在
			// 更新订单信息
			this.ordersDao.saveOrUpdate(orders);
			// 保存财务信息
			Financial financial = new Financial();
			financial.setType(Integer.valueOf(0));
			financial.setMoney(Double.valueOf(-orders.getMoney().doubleValue()));
			financial.setNo("" + System.currentTimeMillis());
			financial.setOperator(orders.getUser().getName());
			financial.setUser(orders.getUser());
			financial.setCreateDate(new Date());
			financial.setDeleted(false);
			financial.setBalance(orders.getUser().getBalance());
			financial.setPayment("西安支付");
			financial.setRemark("购买" + orders.getProductName());
			this.financialService.saveOrUpdate(financial);
			
			Config findConfig = (Config) this.configService.findById(Config.class, 1);
			Double profitAmt = orders.getMoney();
			
			String levelNos = orders.getShop().getSuperior();
			if (!StringUtils.isEmpty(levelNos)) {
				String[] leverNoArr = levelNos.split(";");
				int i = leverNoArr.length - 1;
				for (int j = 1; i > 0; j++) {
					if (!StringUtils.isEmpty(leverNoArr[i])) {
						User levelUser = this.userService.getUserByNo(leverNoArr[i]);
						if (levelUser != null) {
							double commissionRate = 0.0D;
							if (j == 1) {// 一级 0.005
								if ("4".equals(levelUser.getType())) {
									commissionRate = findConfig.getFirstLevel().doubleValue();
								} else if ("3".equals(levelUser.getType())) {
									commissionRate = findConfig.getFirstLevel().doubleValue();
								} else if ("2".equals(levelUser.getType())) {
									commissionRate = findConfig.getFirstLevel().doubleValue();
								}
							} else if (j == 2) {// 二级 0.003
								if ("4".equals(levelUser.getType())) {
									commissionRate = findConfig.getSecondLevel().doubleValue();
								} else if ("3".equals(levelUser.getType())) {
									commissionRate = findConfig.getSecondLevel().doubleValue();
								}
							} else if (j == 3) {// 三级 0.002
								if ("4".equals(levelUser.getType())) {
									commissionRate = findConfig.getThirdLevel().doubleValue();
								} else if ("3".equals(levelUser.getType())) {
									commissionRate = findConfig.getThirdLevel().doubleValue();
								}
							}
							double commissionNum = orders.getMoney().doubleValue() * commissionRate;
							levelUser.setCommission(levelUser.getCommission() + commissionNum);
							levelUser.setBalance(levelUser.getBalance() + commissionNum);
							
							this.userService.saveOrUpdate(levelUser);
							Commission commission = new Commission();
							commission.setType(Integer.valueOf(1));
							commission.setMoney(Double.valueOf(commissionNum));
							commission.setNo("" + System.currentTimeMillis());
							commission.setOperator(orders.getUser().getName());
							commission.setUser(levelUser);
							commission.setCreateDate(new Date());
							commission.setDeleted(false);
							commission.setLevel(Integer.valueOf(j));
							commission.setRemark("第" + j + "级用户:编号【" + orders.getUser().getNo() + "】购买商品奖励");
							if (commission.getMoney() > 0) {
								this.commissionService.saveOrUpdate(commission);
							}
							profitAmt = profitAmt - commissionNum;
						}
					}
					i--;
				}
			}
			findConfig.setTotalProfitAmt(findConfig.getTotalProfitAmt() + profitAmt);
			findConfig.setUnProfitAmt(findConfig.getUnProfitAmt() + profitAmt);
			
			this.configService.saveOrUpdate(findConfig);
			
		}
	}
	
	public void saveTotalProfit() throws Exception {
		// 1、查找符合条件的商户列表
		List<User> userList = this.userService.findProfitUserList();
		// 2、查找目前待结算的平台收益
		Config findConfig = (Config) this.configService.findById(Config.class, 1);
		Double profitAmt = findConfig.getUnProfitAmt();
		// 3、分配收益
		for (User user : userList) {
			Double userProfitAmt = profitAmt;
			if ("4".equals(user.getType())) {
				userProfitAmt = profitAmt * 0.003;
				user.setBalance(user.getBalance() + userProfitAmt);
//			} else if ("3".equals(user.getType())) {
//				userProfitAmt = profitAmt * 0.003;
//				user.setBalance(user.getBalance() + userProfitAmt);
			}
			this.userService.saveOrUpdate(user);
			// 保存佣金信息表 
			Commission commission = new Commission();
			commission.setType(Integer.valueOf(1));
			commission.setMoney(Double.valueOf(userProfitAmt));
			commission.setNo("" + System.currentTimeMillis());
			commission.setOperator(user.getName());
			commission.setUser(user);
			commission.setCreateDate(new Date());
			commission.setDeleted(false);
			commission.setLevel(1);
			commission.setRemark("平台总收益分润奖励");
			this.commissionService.saveOrUpdate(commission);
		}
		// 清零代结算的平台收益
		findConfig.setUnProfitAmt(new Double(0));
		this.configService.saveOrUpdate(findConfig);
	}
}

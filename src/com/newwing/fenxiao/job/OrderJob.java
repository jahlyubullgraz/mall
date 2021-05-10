package com.newwing.fenxiao.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.newwing.fenxiao.entities.Commission;
import com.newwing.fenxiao.entities.Config;
import com.newwing.fenxiao.entities.User;
import com.newwing.fenxiao.service.ICommissionService;
import com.newwing.fenxiao.service.IConfigService;
import com.newwing.fenxiao.service.IUserService;

@Component("orderJob")
@Lazy(false)
public class OrderJob {

	@Resource(name = "configService")
	private IConfigService<Config> configService;
	@Resource(name = "userService")
	private IUserService<User> userService;
	@Resource(name = "commissionService")
	private ICommissionService<Commission> commissionService;
	
	Log log = LogFactory.getLog(OrderJob.class);
	
//	@Scheduled(cron = "0/10 * * * * ? ")
	@Scheduled(cron = "0 0 0 1 * ? ")
	public void saveTotalProfit() throws Exception {
		System.out.println(" >>>>>>>>>> 开始进行批量处理 <<<<<<<<<< ");
		// 1、查找符合条件的商户列表
		List<User> userList = this.userService.findProfitUserList();
		// 2、查找目前待结算的平台收益
		Config findConfig = (Config) this.configService.findById(Config.class, 1);
		Double profitAmt = findConfig.getUnProfitAmt();
		// 3、分配收益
		if (profitAmt > 0) {
			for (User user : userList) {
				Double userProfitAmt = profitAmt;
				if ("4".equals(user.getType())) {
					userProfitAmt = profitAmt * 0.003;
					user.setBalance(user.getBalance() + userProfitAmt);
					user.setCommission(user.getCommission() + userProfitAmt);
//				} else if ("3".equals(user.getType())) {
//					userProfitAmt = profitAmt * 0.003;
//					user.setBalance(user.getBalance() + userProfitAmt);
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
				commission.setRemark("平台每月总收益分润奖励");
				this.commissionService.saveOrUpdate(commission);
			}
			// 清零代结算的平台收益
			findConfig.setUnProfitAmt(new Double(0));
			this.configService.saveOrUpdate(findConfig);
		}
	}

}
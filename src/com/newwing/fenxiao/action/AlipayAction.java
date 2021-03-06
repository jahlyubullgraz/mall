package com.newwing.fenxiao.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.newwing.fenxiao.entities.Config;
import com.newwing.fenxiao.entities.Financial;
import com.newwing.fenxiao.entities.Orders;
import com.newwing.fenxiao.entities.Recharge;
import com.newwing.fenxiao.entities.User;
import com.newwing.fenxiao.pay.alipay.AlipayConfig;
import com.newwing.fenxiao.pay.alipay.AlipayNotify;
import com.newwing.fenxiao.pay.alipay.AlipaySubmit;
import com.newwing.fenxiao.service.IConfigService;
import com.newwing.fenxiao.service.IFinancialService;
import com.newwing.fenxiao.service.IOrdersService;
import com.newwing.fenxiao.service.IRechargeService;
import com.newwing.fenxiao.service.IUserService;

@Controller("alipayAction")
@Scope("prototype")
public class AlipayAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Resource(name = "ordersService")
	private IOrdersService<Orders> ordersService;

	@Resource(name = "userService")
	private IUserService<User> userService;
	private Orders orders;
	private String ftlFileName;

	@Resource(name = "configService")
	private IConfigService<Config> configService;

	@Resource(name = "financialService")
	private IFinancialService<Financial> financialService;

	@Resource(name = "rechargeService")
	private IRechargeService<Recharge> rechargeService;

	public String alipayApi() throws Exception {
		String payment_type = "1";

		String notify_url = this.request.getScheme() + "://" + this.request.getServerName() + ":"
				+ this.request.getServerPort() + this.request.getContextPath() + "/" + "notifyUrl";

		String return_url = this.request.getScheme() + "://" + this.request.getServerName() + ":"
				+ this.request.getServerPort() + this.request.getContextPath() + "/" + "returnUrl";

		Random random = new Random();
		int n = random.nextInt(9999);
		n += 10000;
		String out_trade_no = "" + System.currentTimeMillis() + n;

		String subject = out_trade_no;

		String money = this.request.getParameter("money");

		String body = out_trade_no;

		String show_url = this.request.getScheme() + "://" + this.request.getServerName() + ":"
				+ this.request.getServerPort() + this.request.getContextPath() + "/";

		String anti_phishing_key = "";

		String exter_invoke_ip = "";

		String enable_paymethod = this.request.getParameter("enable_paymethod");
		Config findConfig = (Config) this.configService.findById(Config.class, 1);

		Map sParaTemp = new HashMap();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", findConfig.getAlipayPartner());
		sParaTemp.put("optEmail", findConfig.getAlipaySellerEmail());
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
//		sParaTemp.put("total_fee", Double.parseDouble(money));
		sParaTemp.put("total_fee", money);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		sParaTemp.put("enable_paymethod", "debitCardExpress");

		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "??????", findConfig.getAlipayKey());
		HttpSession session = this.request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		Recharge recharge = new Recharge();
		recharge.setNo(out_trade_no);
		recharge.setMoney(Double.valueOf(Double.parseDouble(money)));
		recharge.setUser(loginUser);
		recharge.setStatus(Integer.valueOf(0));

		recharge.setCreateDate(new Date());
		this.rechargeService.saveOrUpdate(recharge);

		PrintWriter out = this.response.getWriter();
		out.println(sHtmlText);
		out.flush();
		out.close();
		return null;
	}

	public String notifyUrl() throws Exception {
		PrintWriter out = this.response.getWriter();

		Map params = new HashMap();
		Map requestParams = this.request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = valueStr + values[i] + ",";
			}

			params.put(name, valueStr);
		}

		String out_trade_no = new String(this.request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

		String trade_no = new String(this.request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

		String trade_status = new String(this.request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

		Config findConfig = (Config) this.configService.findById(Config.class, 1);
		if (AlipayNotify.verify(params, findConfig.getAlipayKey())) {
			if (trade_status.equals("TRADE_FINISHED")) {
				Recharge findRecharge = this.rechargeService.findByNo(out_trade_no);
				if (findRecharge.getStatus().intValue() == 0) {
					findRecharge.setStatus(Integer.valueOf(1));
					this.rechargeService.saveOrUpdate(findRecharge);
					User findUser = (User) this.userService.findById(User.class,
							findRecharge.getUser().getId().intValue());
					findUser.setBalance(Double
							.valueOf(findUser.getBalance().doubleValue() + findRecharge.getMoney().doubleValue()));
					this.userService.saveOrUpdate(findUser);
				}

			} else if (trade_status.equals("TRADE_SUCCESS")) {
				Recharge findRecharge = this.rechargeService.findByNo(out_trade_no);
				if (findRecharge.getStatus().intValue() == 0) {
					findRecharge.setStatus(Integer.valueOf(1));
					this.rechargeService.saveOrUpdate(findRecharge);
					User findUser = (User) this.userService.findById(User.class,
							findRecharge.getUser().getId().intValue());
					findUser.setBalance(Double
							.valueOf(findUser.getBalance().doubleValue() + findRecharge.getMoney().doubleValue()));
					this.userService.saveOrUpdate(findUser);
				}

			}

			out.println("success");
		} else {
			out.println("fail");
		}
		out.flush();
		out.close();
		return null;
	}

	public String returnUrl() throws Exception {
		PrintWriter out = this.response.getWriter();

		Map params = new HashMap();
		Map requestParams = this.request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = valueStr + values[i] + ",";
			}

			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		String out_trade_no = new String(this.request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

		String trade_status = new String(this.request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
		Config findConfig = (Config) this.configService.findById(Config.class, 1);

		boolean verify_result = AlipayNotify.verify(params, findConfig.getAlipayKey());

		if (verify_result) {
			Recharge findRecharge = this.rechargeService.findByNo(out_trade_no);

			if ((trade_status.equals("TRADE_FINISHED")) || (trade_status.equals("TRADE_SUCCESS"))) {
				if (findRecharge.getStatus().intValue() == 0) {
					findRecharge.setStatus(Integer.valueOf(1));
					this.rechargeService.saveOrUpdate(findRecharge);
					User findUser = (User) this.userService.findById(User.class,
							findRecharge.getUser().getId().intValue());
					findUser.setBalance(Double
							.valueOf(findUser.getBalance().doubleValue() + findRecharge.getMoney().doubleValue()));
					this.userService.saveOrUpdate(findUser);
				}

			}

			out.println("<br>????????????!<br>?????????:" + out_trade_no + "<br>????????????:" + findRecharge.getMoney());
		} else {
			out.println("????????????");
		}
		out.flush();
		out.close();
		return null;
	}

	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public String getFtlFileName() {
		return this.ftlFileName;
	}

	public void setFtlFileName(String ftlFileName) {
		this.ftlFileName = ftlFileName;
	}
}

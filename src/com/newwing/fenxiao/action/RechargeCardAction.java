package com.newwing.fenxiao.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.newwing.fenxiao.entities.Admin;
import com.newwing.fenxiao.entities.Financial;
import com.newwing.fenxiao.entities.RechargeCard;
import com.newwing.fenxiao.entities.User;
import com.newwing.fenxiao.service.IAdminService;
import com.newwing.fenxiao.service.IFinancialService;
import com.newwing.fenxiao.service.IRechargeCardService;
import com.newwing.fenxiao.service.IUserService;
import com.newwing.fenxiao.utils.BjuiJson;
import com.newwing.fenxiao.utils.BjuiPage;
import com.newwing.fenxiao.utils.FreemarkerUtils;
import com.newwing.fenxiao.utils.Uuid;

import freemarker.template.Configuration;

@Controller("rechargeCardAction")
@Scope("prototype")
public class RechargeCardAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Resource(name = "rechargeCardService")
	private IRechargeCardService<RechargeCard> rechargeCardService;
	private RechargeCard rechargeCard;

	@Resource(name = "adminService")
	private IAdminService<Admin> adminService;

	@Resource(name = "userService")
	private IUserService<User> userService;

	@Resource(name = "financialService")
	private IFinancialService<Financial> financialService;

	public void list() {
		String no = this.request.getParameter("no");
		String status = this.request.getParameter("status");

		int count = 0;
		String countHql = "select count(*) from RechargeCard where deleted=0";
		if (StringUtils.isNotEmpty(no)) {
			countHql = countHql + " and no like '%" + no + "%'";
		}
		if (StringUtils.isNotEmpty(status)) {
			countHql = countHql + " and status=" + status;
		}
		count = this.rechargeCardService.getTotalCount(countHql, new Object[0]);

		this.page = new BjuiPage(this.pageCurrent, this.pageSize);
		this.page.setTotalCount(count);
		this.cfg = new Configuration();

		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
		String hql = "from RechargeCard where deleted=0";
		if (StringUtils.isNotEmpty(no)) {
			hql = hql + " and no like '%" + no + "%'";
		}
		if (StringUtils.isNotEmpty(status)) {
			hql = hql + " and status=" + status;
		}
		hql = hql + " order by id desc";
		List rechargeCardList = this.rechargeCardService.list(hql, this.page.getStart(), this.page.getPageSize(),
				new Object[0]);
		Map root = new HashMap();
		root.put("rechargeCardList", rechargeCardList);
		root.put("page", this.page);
		root.put("no", no);
		FreemarkerUtils.freemarker(this.request, this.response, "rechargeCardList.ftl", this.cfg, root);
	}

	public void add() {
		this.cfg = new Configuration();

		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
		Map root = new HashMap();
		FreemarkerUtils.freemarker(this.request, this.response, "rechargeCardAdd.ftl", this.cfg, root);
	}

	public void save() {
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String numStr = this.request.getParameter("num");
		String moneyStr = this.request.getParameter("money");
		int num = Integer.parseInt(numStr);
		double money = Double.parseDouble(moneyStr);
		String callbackData = "";
		try {
			Date date = new Date();
			for (int i = 0; i < num; i++) {
				String no = Uuid.getUUID();
				RechargeCard rechargeCard = new RechargeCard();
				rechargeCard.setDeleted(false);
				rechargeCard.setCreateDate(date);
				rechargeCard.setMoney(money);
				rechargeCard.setStatus(Integer.valueOf(0));
				rechargeCard.setNo(no);
				this.rechargeCardService.saveOrUpdate(rechargeCard);
			}
			callbackData = BjuiJson.json("200", "????????????" + num + "????????????", "", "", "", "true", "", "");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(callbackData);
		out.flush();
		out.close();
	}

	public void info() {
		String no = this.request.getParameter("no");
		this.cfg = new Configuration();

		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
		Map root = new HashMap();
		root.put("no", no);
		FreemarkerUtils.freemarker(this.request, this.response, "rechargeCardChongzhi.ftl", this.cfg, root);
	}

	public void delete() {
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String callbackData = "";
		try {
			String idStr = this.request.getParameter("id");

			if ((idStr == null) || ("".equals(idStr))) {
				callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
			} else {
				int id = 0;
				try {
					id = Integer.parseInt(idStr);
				} catch (Exception e) {
					callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
				}
				RechargeCard findRechargeCard = (RechargeCard) this.rechargeCardService.findById(RechargeCard.class,
						id);
				if (findRechargeCard == null) {
					callbackData = BjuiJson.json("300", "??????????????????", "", "", "", "", "", "");
				} else {
					boolean result = this.rechargeCardService.delete(findRechargeCard);
					if (result)
						callbackData = BjuiJson.json("200", "????????????", "", "", "", "", "", "");
					else
						callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(callbackData);
		out.flush();
		out.close();
	}

	public void userUseRechargeCard() {
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpSession session = this.request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		User findUser = (User) this.userService.findById(User.class, loginUser.getId().intValue());
		String no = this.request.getParameter("no");
		RechargeCard findRechargeCard = this.rechargeCardService.getByNo(no);
		JSONObject json = new JSONObject();
		if (findRechargeCard == null) {
			json.put("status", "0");
			json.put("message", "??????????????????");
		} else if (findRechargeCard.getStatus().intValue() == 1) {
			json.put("status", "0");
			json.put("message", "?????????????????????");
		} else {
			Financial financial = new Financial();
			financial.setType(Integer.valueOf(1));
			financial.setMoney(Double.valueOf(findRechargeCard.getMoney()));
			financial.setNo("" + System.currentTimeMillis());

			financial.setOperator(loginUser.getName());

			financial.setUser(findUser);

			financial.setCreateDate(new Date());
			financial.setDeleted(false);

			financial.setBalance(findUser.getCommission());
			financial.setPayment("???????????????");
			financial.setRemark("???????????????,???????????????:" + findRechargeCard.getNo());
			this.financialService.saveOrUpdate(financial);
			findUser.setBalance(Double.valueOf(findUser.getBalance().doubleValue() + findRechargeCard.getMoney()));
			this.userService.saveOrUpdate(findUser);

			findRechargeCard.setStatus(Integer.valueOf(1));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			findRechargeCard.setUseTime(sdf.format(new Date()));
			findRechargeCard.setUseUserNo(findUser.getNo());
			this.rechargeCardService.saveOrUpdate(findRechargeCard);
			json.put("status", "1");
			json.put("message", "???????????????????????????:" + findRechargeCard.getMoney() + "???");
		}

		out.print(json.toString());
		out.flush();
		out.close();
	}

	public RechargeCard getRechargeCard() {
		return this.rechargeCard;
	}

	public void setRechargeCard(RechargeCard rechargeCard) {
		this.rechargeCard = rechargeCard;
	}
	
}

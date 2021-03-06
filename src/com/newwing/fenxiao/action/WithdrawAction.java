package com.newwing.fenxiao.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.newwing.fenxiao.entities.Config;
import com.newwing.fenxiao.entities.Financial;
import com.newwing.fenxiao.entities.User;
import com.newwing.fenxiao.entities.Withdraw;
import com.newwing.fenxiao.service.IConfigService;
import com.newwing.fenxiao.service.IFinancialService;
import com.newwing.fenxiao.service.IUserService;
import com.newwing.fenxiao.service.IWithdrawService;
import com.newwing.fenxiao.utils.BjuiJson;
import com.newwing.fenxiao.utils.BjuiPage;
import com.newwing.fenxiao.utils.FreemarkerUtils;
import com.newwing.fenxiao.utils.PageModel;

import freemarker.template.Configuration;

@Controller("withdrawAction")
@Scope("prototype")
public class WithdrawAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Resource(name = "withdrawService")
	private IWithdrawService<Withdraw> withdrawService;

	@Resource(name = "configService")
	private IConfigService<Config> configService;

	@Resource(name = "userService")
	private IUserService<User> userService;

	@Resource(name = "financialService")
	private IFinancialService<Financial> financialService;
	private Withdraw withdraw;
	private String ftlFileName;

	public void list() {
		String key = this.request.getParameter("key");
		String countHql = "select count(*) from Withdraw where deleted=0";
		String hql = "from Withdraw where deleted=0";
		if (StringUtils.isNotEmpty(key)) {
			countHql = countHql + " and (user.name='" + key + "')";
			hql = hql + " and (user.name='" + key + "')";
		}
		hql = hql + " order by id desc";

		int count = 0;
		count = this.withdrawService.getTotalCount(countHql, new Object[0]);
		this.page = new BjuiPage(this.pageCurrent, this.pageSize);
		this.page.setTotalCount(count);
		this.cfg = new Configuration();

		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
		List withdrawList = this.withdrawService.list(hql, this.page.getStart(), this.page.getPageSize(),
				new Object[0]);
		Map root = new HashMap();
		root.put("withdrawList", withdrawList);
		root.put("page", this.page);
		root.put("key", key);
		FreemarkerUtils.freemarker(this.request, this.response, this.ftlFileName, this.cfg, root);
	}

	public void add() {
		this.cfg = new Configuration();

		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
		Map root = new HashMap();
		FreemarkerUtils.freemarker(this.request, this.response, this.ftlFileName, this.cfg, root);
	}

	public void save() {
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		if (this.withdraw.getMoney().doubleValue() <= 0.0D) {
			json.put("status", "0");
			json.put("message", "??????????????????0");
		} else {
			HttpSession session = this.request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			User findUser = (User) this.userService.findById(User.class, loginUser.getId().intValue());
			if (this.withdraw.getMoney().doubleValue() > findUser.getBalance().doubleValue()) {
				json.put("status", "0");
				json.put("message", "????????????");
			} else {
				this.withdraw.setUser(findUser);
				this.withdraw.setStatus(Integer.valueOf(0));
				this.withdraw.setDeleted(false);
				this.withdraw.setCreateDate(new Date());
				boolean result = this.withdrawService.saveOrUpdate(this.withdraw);
				if (result) {
					findUser.setCommission(Double
							.valueOf(findUser.getCommission().doubleValue() - this.withdraw.getMoney().doubleValue()));
					this.userService.saveOrUpdate(findUser);

					Financial financial = new Financial();
					financial.setType(Integer.valueOf(0));
					financial.setMoney(this.withdraw.getMoney());
					financial.setNo("" + System.currentTimeMillis());

					financial.setOperator(loginUser.getName());

					financial.setUser(findUser);

					financial.setCreateDate(new Date());
					financial.setDeleted(false);

					financial.setBalance(findUser.getCommission());
					financial.setPayment("??????");
					financial.setRemark("??????");
					this.financialService.saveOrUpdate(financial);
					json.put("status", "1");
					json.put("message", "????????????????????????");
				} else {
					json.put("status", "0");
					json.put("message", "????????????????????????????????????");
				}
			}
		}
		out.print(json.toString());
		out.flush();
		out.close();
	}

	public void info() {
		String idStr = this.request.getParameter("id");
		String callbackData = "";
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if ((idStr == null) || ("".equals(idStr))) {
				callbackData = BjuiJson.json("300", "??????????????????", "", "", "", "", "", "");
			} else {
				int id = 0;
				try {
					id = Integer.parseInt(idStr);
				} catch (Exception e) {
					callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
				}
				Withdraw findwithdraw = (Withdraw) this.withdrawService.findById(Withdraw.class, id);
				if (findwithdraw == null) {
					callbackData = BjuiJson.json("300", "???????????????", "", "", "", "", "", "");
				} else {
					this.cfg = new Configuration();

					this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(),
							"WEB-INF/templates/admin");
					Map root = new HashMap();
					root.put("withdraw", findwithdraw);
					FreemarkerUtils.freemarker(this.request, this.response, this.ftlFileName, this.cfg, root);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(callbackData);
		out.flush();
		out.close();
	}

	public void detail() {
		String idStr = this.request.getParameter("id");
		String callbackData = "";
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if ((idStr == null) || ("".equals(idStr))) {
				callbackData = BjuiJson.json("300", "??????????????????", "", "", "", "", "", "");
			} else {
				int id = 0;
				try {
					id = Integer.parseInt(idStr);
				} catch (Exception e) {
					callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
				}
				Withdraw findwithdraw = (Withdraw) this.withdrawService.findById(Withdraw.class, id);
				if (findwithdraw == null) {
					callbackData = BjuiJson.json("300", "???????????????", "", "", "", "", "", "");
				} else {
					findwithdraw.setStatus(Integer.valueOf(1));
					this.withdrawService.saveOrUpdate(findwithdraw);
					callbackData = BjuiJson.json("200", "????????????", "", "", "", "", "", "");
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(callbackData);
		out.flush();
		out.close();
	}

	public void update() {
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String callbackData = "";
		try {
			if (this.withdraw == null) {
				callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
			} else {
				Withdraw findwithdraw = (Withdraw) this.withdrawService.findById(Withdraw.class,
						this.withdraw.getId().intValue());
				this.withdraw.setCreateDate(findwithdraw.getCreateDate());
				this.withdraw.setDeleted(findwithdraw.isDeleted());
				this.withdraw.setVersion(findwithdraw.getVersion());
				boolean result = this.withdrawService.saveOrUpdate(this.withdraw);

				if (result) {
					callbackData = BjuiJson.json("200", "????????????", "", "", "", "true", "", "");
				} else
					callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(callbackData);
		out.flush();
		out.close();
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
				Withdraw findwithdraw = (Withdraw) this.withdrawService.findById(Withdraw.class, id);
				if (findwithdraw == null) {
					callbackData = BjuiJson.json("300", "???????????????", "", "", "", "true", "", "");
				} else {
					boolean result = this.withdrawService.delete(findwithdraw);
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

	public void userWithdrawList() {
		String pStr = this.request.getParameter("p");
		int p = 1;
		if (!StringUtils.isEmpty(pStr)) {
			p = Integer.parseInt(pStr);
		}

		HttpSession session = this.request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		String countHql = "select count(*) from Withdraw where deleted=0 and user.id=" + loginUser.getId();
		String hql = "from Withdraw where deleted=0 and user.id=" + loginUser.getId();
		hql = hql + " order by id desc";

		int count = 0;
		count = this.withdrawService.getTotalCount(countHql, new Object[0]);
		PageModel pageModel = new PageModel();
		pageModel.setAllCount(count);
		pageModel.setCurrentPage(p);
		List withdrawList = this.withdrawService.list(hql, pageModel.getStart(), pageModel.getPageSize(),
				new Object[0]);
		JSONObject json = new JSONObject();
		if (withdrawList.size() == 0) {
			json.put("status", "0");

			json.put("isNextPage", "0");
		} else {
			json.put("status", "1");
			if (withdrawList.size() == pageModel.getPageSize()) {
				json.put("isNextPage", "1");
			} else {
				json.put("isNextPage", "0");
			}
			JSONArray listJson = (JSONArray) JSONArray.toJSON(withdrawList);
			json.put("list", listJson);
		}
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		out.print(json);
		out.flush();
		out.close();
	}

	public Withdraw getWithdraw() {
		return this.withdraw;
	}

	public void setWithdraw(Withdraw withdraw) {
		this.withdraw = withdraw;
	}

	public String getFtlFileName() {
		return this.ftlFileName;
	}

	public void setFtlFileName(String ftlFileName) {
		this.ftlFileName = ftlFileName;
	}
	
}

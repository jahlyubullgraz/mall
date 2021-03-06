package com.newwing.fenxiao.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.newwing.fenxiao.entities.Config;
import com.newwing.fenxiao.entities.Recharge;
import com.newwing.fenxiao.entities.User;
import com.newwing.fenxiao.service.IConfigService;
import com.newwing.fenxiao.service.IRechargeService;
import com.newwing.fenxiao.service.IUserService;
import com.newwing.fenxiao.utils.BjuiJson;
import com.newwing.fenxiao.utils.BjuiPage;
import com.newwing.fenxiao.utils.FreemarkerUtils;

import freemarker.template.Configuration;

@Controller("rechargeAction")
@Scope("prototype")
public class RechargeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Resource(name = "rechargeService")
	private IRechargeService<Recharge> rechargeService;

	@Resource(name = "userService")
	private IUserService<User> userService;
	private Recharge recharge;
	private String ftlFileName;

	@Resource(name = "configService")
	private IConfigService<Config> configService;

	public void list() {
		String key = this.request.getParameter("key");
		String countHql = "select count(*) from Recharge where deleted=0";
		String hql = "from Recharge where deleted=0";
		if (StringUtils.isNotEmpty(key)) {
			countHql = countHql + " and (user.name='" + key + "' or no='" + key + "')";
			hql = hql + " and (user.name='" + key + "' or no='" + key + "')";
		}
		hql = hql + " order by id desc";

		int count = 0;
		count = this.rechargeService.getTotalCount(countHql, new Object[0]);
		this.page = new BjuiPage(this.pageCurrent, this.pageSize);
		this.page.setTotalCount(count);
		this.cfg = new Configuration();

		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
		List rechargeList = this.rechargeService.list(hql, this.page.getStart(), this.page.getPageSize(),
				new Object[0]);
		Map root = new HashMap();
		root.put("rechargeList", rechargeList);
		root.put("page", this.page);
		root.put("key", key);
		FreemarkerUtils.freemarker(this.request, this.response, this.ftlFileName, this.cfg, root);
	}

	String formatString(String text) {
		if (text == null) {
			return "";
		}
		return text;
	}

	public void save() {
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String callbackData = "";
		if (this.recharge == null) {
			callbackData = "<script>alert('????????????');window.location.href='javascript:history.go(-1);'</script>";
		} else {
			Random random = new Random();
			int n = random.nextInt(9999);
			n += 10000;

			String no = "" + System.currentTimeMillis() + n;
			this.recharge.setNo(no);

			this.recharge.setCreateDate(new Date());
			boolean result = this.rechargeService.saveOrUpdate(this.recharge);
			if (result)
				callbackData = "<script>alert('????????????');window.location.href='recharge.jsp'</script>";
			else {
				callbackData = "<script>alert('????????????????????????');window.location.href='javascript:history.go(-1);'</script>";
			}
		}
		out.print(callbackData);
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
				Recharge findrecharge = (Recharge) this.rechargeService.findById(Recharge.class, id);
				if (findrecharge == null) {
					callbackData = BjuiJson.json("300", "???????????????", "", "", "", "", "", "");
				} else {
					this.cfg = new Configuration();

					this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(),
							"WEB-INF/templates/admin");
					Map root = new HashMap();
					root.put("recharge", findrecharge);
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

	public void update() {
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String callbackData = "";
		try {
			if (this.recharge == null) {
				callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
			} else {
				Recharge findrecharge = (Recharge) this.rechargeService.findById(Recharge.class,
						this.recharge.getId().intValue());
				this.recharge.setCreateDate(findrecharge.getCreateDate());
				this.recharge.setDeleted(findrecharge.isDeleted());
				this.recharge.setVersion(findrecharge.getVersion());
				boolean result = this.rechargeService.saveOrUpdate(this.recharge);

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
				Recharge findrecharge = (Recharge) this.rechargeService.findById(Recharge.class, id);
				if (findrecharge == null) {
					callbackData = BjuiJson.json("300", "???????????????", "", "", "", "true", "", "");
				} else {
					boolean result = this.rechargeService.delete(findrecharge);
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

	public Recharge getRecharge() {
		return this.recharge;
	}

	public void setRecharge(Recharge recharge) {
		this.recharge = recharge;
	}

	public String getFtlFileName() {
		return this.ftlFileName;
	}

	public void setFtlFileName(String ftlFileName) {
		this.ftlFileName = ftlFileName;
	}
	
}

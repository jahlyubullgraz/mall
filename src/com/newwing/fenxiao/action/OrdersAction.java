package com.newwing.fenxiao.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.newwing.fenxiao.entities.Commission;
import com.newwing.fenxiao.entities.Config;
import com.newwing.fenxiao.entities.Financial;
import com.newwing.fenxiao.entities.Kami;
import com.newwing.fenxiao.entities.Orders;
import com.newwing.fenxiao.entities.Product;
import com.newwing.fenxiao.entities.User;
import com.newwing.fenxiao.service.ICommissionService;
import com.newwing.fenxiao.service.IConfigService;
import com.newwing.fenxiao.service.IFinancialService;
import com.newwing.fenxiao.service.IKamiService;
import com.newwing.fenxiao.service.IOrdersService;
import com.newwing.fenxiao.service.IProductService;
import com.newwing.fenxiao.service.IUserService;
import com.newwing.fenxiao.utils.BjuiJson;
import com.newwing.fenxiao.utils.BjuiPage;
import com.newwing.fenxiao.utils.FreemarkerUtils;
import com.newwing.fenxiao.utils.PageModel;
import com.weixin.utils.GetWxOrderno;
import com.weixin.utils.RequestHandler;
import com.weixin.utils.Sha1Util;
import com.weixin.utils.TenpayUtil;

import freemarker.template.Configuration;

@Controller("ordersAction")
@Scope("prototype")
public class OrdersAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private String appid = "wx80c6f861374d68fc";
	private String appsecret = "0d75a21684944d43120ce82102046244";
	private String partner = "1401404002";
//	private String partnerkey = "hsbd74mfimjeFKr74dd8Nhd83bsmdi7e";
	private String partnerkey = "QHTqht201688HYDTxrxy1688NJZYCHIZ";
	private String notify_url ="http://www.genobien.com/fx/api/callback";// ????????????
	private String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";// ??????????????????url
	
	@Resource(name = "ordersService")
	private IOrdersService<Orders> ordersService;

	@Resource(name = "userService")
	private IUserService<User> userService;

	@Resource(name = "productService")
	private IProductService<Product> productService;

	@Resource(name = "kamiService")
	private IKamiService<Kami> kamiService;

	@Resource(name = "financialService")
	private IFinancialService<Financial> financialService;

	@Resource(name = "commissionService")
	private ICommissionService<Commission> commissionService;
	private Orders orders;
	private String ftlFileName;

	@Resource(name = "configService")
	private IConfigService<Config> configService;

	public void list() {
		String key = this.request.getParameter("key");
		String countHql = "select count(*) from Orders where deleted=0";
		String hql = "from Orders where deleted=0";
		if (StringUtils.isNotEmpty(key)) {
			countHql = countHql + " and (user.name='" + key + "' or no='" + key + "' or productName='" + key + "')";
			hql = hql + " and (user.name='" + key + "' or no='" + key + "' or productName='" + key + "')";
		}
		hql = hql + " order by id desc";

		int count = 0;
		count = this.ordersService.getTotalCount(countHql, new Object[0]);
		this.page = new BjuiPage(this.pageCurrent, this.pageSize);
		this.page.setTotalCount(count);
		this.cfg = new Configuration();

		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
		List ordersList = this.ordersService.list(hql, this.page.getStart(), this.page.getPageSize(), new Object[0]);
		Map root = new HashMap();
		root.put("ordersList", ordersList);
		root.put("page", this.page);
		root.put("key", key);
		FreemarkerUtils.freemarker(this.request, this.response, this.ftlFileName, this.cfg, root);
	}

	public void add() {
		String pidStr = this.request.getParameter("pid");
		int pid = 0;
		try {
			pid = Integer.parseInt(pidStr);
		} catch (Exception e) {
			this.request.setAttribute("status", "0");
			this.request.setAttribute("message", "????????????");
			try {
				this.request.getRequestDispatcher("cart.jsp").forward(this.request, this.response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return;
		}
		Product findProduct = (Product) this.productService.findById(Product.class, pid);
		if (findProduct == null) {
			this.request.setAttribute("status", "0");
			this.request.setAttribute("message", "???????????????");
		} else {
			this.request.setAttribute("status", "1");
			this.request.setAttribute("product", findProduct);
		}
		try {
			this.request.getRequestDispatcher("cart.jsp").forward(this.request, this.response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		String pidStr = this.request.getParameter("pid");
		String numStr = this.request.getParameter("num");
		int pid = 0;
		int num = 1;
		try {
			pid = Integer.parseInt(pidStr);
			num = Integer.parseInt(numStr);
		} catch (Exception e) {
			this.request.setAttribute("status", "0");
			this.request.setAttribute("message", "????????????");
			try {
				this.request.getRequestDispatcher("orderAdd.jsp").forward(this.request, this.response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return;
		}
		Product findProduct = (Product) this.productService.findById(Product.class, pid);
		if (findProduct == null) {
			this.request.setAttribute("status", "0");
			this.request.setAttribute("message", "???????????????");
		} else {
			HttpSession session = this.request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			if ((loginUser == null) || (loginUser.getId() == null)) {
				this.request.setAttribute("status", "0");
				this.request.setAttribute("message", "????????????????????????????????????????????????");
			} else {
				Orders newOrders = new Orders();
				newOrders.setProductId("" + findProduct.getId());
				newOrders.setProductName(findProduct.getTitle());
				newOrders.setProductNum(Integer.valueOf(num));
				newOrders.setProductMoney(findProduct.getMoney());
				newOrders.setUser(loginUser);
				newOrders.setStatus(Integer.valueOf(0));
				newOrders.setMoney(Double.valueOf(num * findProduct.getMoney().doubleValue()));

				Random random = new Random();
				int n = random.nextInt(9999);
				n += 10000;

				String no = "" + System.currentTimeMillis() + n;
				newOrders.setNo(no);

				newOrders.setCreateDate(new Date());
				newOrders.setDeleted(false);
				this.ordersService.saveOrUpdate(newOrders);
				try {
					this.response.sendRedirect("settle?no=" + no);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void settle() {
		String no = this.request.getParameter("no");
		Orders findOrders = this.ordersService.findByNo(no);
		if (findOrders == null) {
			this.request.setAttribute("status", "0");
			this.request.setAttribute("message", "???????????????");
		} else {
			HttpSession session = this.request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			if ((loginUser == null) || (loginUser.getId() == null)) {
				this.request.setAttribute("status", "0");
				this.request.setAttribute("message", "????????????????????????????????????????????????");
			} else {
				this.request.setAttribute("orders", findOrders);
				try {
					// TODO ??????????????????????????????????????????
					this.request.getRequestDispatcher("settle.jsp").forward(this.request, this.response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void pay() throws Exception {
		String no = this.request.getParameter("no");
		Orders findOrders = this.ordersService.findByNo(no);
		HttpSession session = this.request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		JSONObject json = new JSONObject();
		User findUser = (User) this.userService.findById(User.class, loginUser.getId().intValue());
		if (findOrders == null) {
			json.put("status", "0");
			json.put("message", "???????????????");
		} else if (findOrders.getUser().getId() != findUser.getId()) {
			json.put("status", "0");
			json.put("message", "????????????");
		} else if (findOrders.getStatus().intValue() == 1) {
			json.put("status", "0");
			json.put("message", "????????????????????????????????????????????????");
		} else {
//			findUser.setBalance(
//					Double.valueOf(findUser.getBalance().doubleValue() - findOrders.getMoney().doubleValue()));
//			if (findUser.getStatus().intValue() == 0) {
//				findUser.setStatus(Integer.valueOf(1));
//				findUser.setStatusDate(new Date());
//			}
//			this.userService.saveOrUpdate(findUser);
			// TODO ??????????????????
			findOrders.setStatus(Integer.valueOf(1));
			String openId = findUser.getOpenId();
			Double money = findOrders.getMoney();
			String finalmoney = String.format("%.2f", money);//???????????????????????????
			finalmoney = finalmoney.replace(".", "");
			String currTime = TenpayUtil.getCurrTime();//??????openId???????????????????????????https://api.mch.weixin.qq.com/pay/unifiedorder
			String strTime = currTime.substring(8, currTime.length());//8?????????
			String strRandom = TenpayUtil.buildRandom(4) + "";//???????????????
			String strReq = strTime + strRandom;//10????????????,?????????????????????
			String mch_id = partner;//?????????
			String nonce_str = strReq;//????????? 
//			String body = "?????????";//??????????????????????????????
			String body = "TEST";//??????????????????????????????
			String attach = findOrders.getUser().getNo();//????????????
			String out_trade_no = findOrders.getNo();//???????????????
			int intMoney = Integer.parseInt(finalmoney);
			int total_fee = intMoney;//??????????????????????????????????????????
			String spbill_create_ip = request.getRemoteAddr();//????????????????????? IP
			String trade_type = "JSAPI";
			String openid = openId;
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("appid", appid);  
			packageParams.put("mch_id", mch_id);  
			packageParams.put("nonce_str", nonce_str);  
			packageParams.put("body", body);  
			packageParams.put("attach", attach);  
			packageParams.put("out_trade_no", out_trade_no);  
			packageParams.put("total_fee", String.valueOf(total_fee));  
			packageParams.put("spbill_create_ip", spbill_create_ip);  
			packageParams.put("notify_url", notify_url);  
			packageParams.put("trade_type", trade_type);  
			packageParams.put("openid", openid);  
			RequestHandler reqHandler = new RequestHandler(request, response);// RequestHandler???????????????????????????????????????????????????
			reqHandler.init(appid, appsecret, partnerkey);
			// ??????md5??????
			String sign = reqHandler.createSign(packageParams);
			String xml="<xml>"+
					"<appid>"+appid+"</appid>"+
					"<attach>"+attach+"</attach>"+
					"<body><![CDATA["+body+"]]></body>"+
					"<mch_id>"+mch_id+"</mch_id>"+
					"<nonce_str>"+nonce_str+"</nonce_str>"+
					"<notify_url>"+notify_url+"</notify_url>"+
					"<openid>"+openid+"</openid>"+
					"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
					"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
					"<sign>"+sign+"</sign>"+
					"<total_fee>"+total_fee+"</total_fee>"+
					"<trade_type>"+trade_type+"</trade_type>"+
					"</xml>";
			System.out.println(xml);
//			xml = new String(xml.toString().getBytes(), "ISO8859-1");
			String prepay_id = "";
			try {
				prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
				if("".equals(prepay_id)){
					request.setAttribute("ErrorMsg", "?????????????????????????????????????????????");
					response.sendRedirect("error.jsp");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println("prepay_id : " + prepay_id);
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			String appid2 = appid;
			String timestamp = Sha1Util.getTimeStamp();// ?????????????????????
			String nonceStr2 = nonce_str;// ?????????
			String prepay_id2 = "prepay_id="+prepay_id;// ?????????ID
			String packages = prepay_id2;
			finalpackage.put("appId", appid2);  
			finalpackage.put("timeStamp", timestamp);  
			finalpackage.put("nonceStr", nonceStr2);  
			finalpackage.put("package", packages);  
			finalpackage.put("signType", "MD5");// ????????????
			String finalsign = reqHandler.createSign(finalpackage);
			
			json.put("appid", appid2);
			json.put("timeStamp", timestamp);
			json.put("nonceStr", nonceStr2);
			json.put("packageValue", packages);
			json.put("money", money);
			json.put("sign", finalsign);
			json.put("status", "1");
			json.put("no", out_trade_no);
		}
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json.toString());
		out.flush();
		out.close();
	}

	public void detail() {
		String no = this.request.getParameter("no");
		Orders findOrders = this.ordersService.findByNo(no);
		if (findOrders == null) {
			this.request.setAttribute("status", "0");
			this.request.setAttribute("message", "???????????????");
		} else {
			HttpSession session = this.request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			if (findOrders.getUser().getId() != loginUser.getId()) {
				this.request.setAttribute("status", "0");
				this.request.setAttribute("message", "????????????");
			} else {
				this.request.setAttribute("orders", findOrders);
				try {
					this.request.getRequestDispatcher("ordersDetail.jsp").forward(this.request, this.response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void indexList() {
		String pStr = this.request.getParameter("p");
		int p = 1;
		if (!StringUtils.isEmpty(pStr)) {
			p = Integer.parseInt(pStr);
		}

		String type = this.request.getParameter("type");
		HttpSession session = this.request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		String countHql = "select count(*) from Orders where deleted=0 and user.id=" + loginUser.getId();
		String hql = "from Orders where deleted=0 and user.id=" + loginUser.getId();
		if (("0".equals(type)) || ("1".equals(type))) {
			countHql = countHql + " and status=" + type;
			hql = hql + " and status=" + type;
		}
		hql = hql + " order by id desc";

		int count = 0;
		count = this.ordersService.getTotalCount(countHql, new Object[0]);
		PageModel pageModel = new PageModel();
		pageModel.setAllCount(count);
		pageModel.setCurrentPage(p);
		List ordersList = this.ordersService.list(hql, pageModel.getStart(), pageModel.getPageSize(), new Object[0]);
		JSONObject json = new JSONObject();
		if (ordersList.size() == 0) {
			json.put("status", "0");

			json.put("isNextPage", "0");
		} else {
			json.put("status", "1");
			if (ordersList.size() == pageModel.getPageSize()) {
				json.put("isNextPage", "1");
			} else {
				json.put("isNextPage", "0");
			}
			JSONArray listJson = (JSONArray) JSONArray.toJSON(ordersList);
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
				Orders findorders = (Orders) this.ordersService.findById(Orders.class, id);
				if (findorders == null) {
					callbackData = BjuiJson.json("300", "???????????????", "", "", "", "", "", "");
				} else {
					this.cfg = new Configuration();

					this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(),
							"WEB-INF/templates/admin");
					Map root = new HashMap();
					root.put("orders", findorders);
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
			if (this.orders == null) {
				callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
			} else {
				Orders findorders = (Orders) this.ordersService.findById(Orders.class, this.orders.getId().intValue());
				this.orders.setCreateDate(findorders.getCreateDate());
				this.orders.setDeleted(findorders.isDeleted());
				this.orders.setVersion(findorders.getVersion());
				boolean result = this.ordersService.saveOrUpdate(this.orders);

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
				Orders findorders = (Orders) this.ordersService.findById(Orders.class, id);
				if (findorders == null) {
					callbackData = BjuiJson.json("300", "???????????????", "", "", "", "true", "", "");
				} else {
					boolean result = this.ordersService.delete(findorders);
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

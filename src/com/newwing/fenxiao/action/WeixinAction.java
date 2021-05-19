package com.newwing.fenxiao.action;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
import com.newwing.fenxiao.utils.IpUtils;
import com.newwing.fenxiao.utils.Md5;
import com.weixin.utils.CommonUtil;
import com.weixin.utils.GetWxOrderno;
import com.weixin.utils.PayCommonUtil;

/**
 * 微信客户端 Controller
 */
@Controller("weixinAction")
@Scope("prototype")
public class WeixinAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "financialService")
	private IFinancialService<Financial> financialService;
	@Resource(name = "configService")
	private IConfigService<Config> configService;
	@Resource(name = "commissionService")
	private ICommissionService<Commission> commissionService;
	
	private String appid = "wx80c6f861374d68fc";
	private String appsecret = "0d75a21684944d43120ce82102046244";
	private String partner = "1401404002";
	private String partnerkey = "QHTqht201688HYDTxrxy1688NJZYCHIZ";
	private String backUri = "http://www.genobien.com/fx/api/main";
	
	@Resource(name = "ordersService")
	private IOrdersService<Orders> ordersService;
	@Resource(name = "userService")
	private IUserService<User> userService;
	
	
	/**
	 * 授权页面
	 */
	public void auth() throws Exception {
		//共账号及商户相关参数
		String tuijianren = request.getParameter("tuijianren");
		if (tuijianren == null || "".equals(tuijianren)) {
			tuijianren = "000";
		}
		//授权后要跳转的链接所需的参数一般有会员号，金额，订单号之类，
		//最好自己带上一个加密字符串将金额加上一个自定义的key用MD5签名或者自己写的签名,
		//比如 Sign = %3D%2F%CS% 
		backUri = backUri+"?tuijianren=" + tuijianren;
		//URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
		backUri = URLEncoder.encode(backUri);
		//scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid=" + appid +
				"&redirect_uri=" + backUri + 
				"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		response.sendRedirect(url);
	}
	
	/**
	 * 授权后的回调页面
	 * @throws Exception
	 */
	public void main() throws Exception {
		//网页授权后获取传递的参数
		String openId = null;// 微信openid
		String access_token = null;
		String headimgurl = null;
		String nickname = null;
		String code = request.getParameter("code");
		String tuijianren = this.request.getParameter("tuijianren");// 推荐人
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid
				+ "&secret=" + appsecret
				+ "&code=" + code
				+ "&grant_type=authorization_code";
		JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
		if (null != jsonObject && jsonObject.containsKey("openid")) {
			openId = jsonObject.getString("openid");
			access_token = jsonObject.getString("access_token");
			String userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token 
					+ "&openid=" + openId
					+ "&lang=zh_CN";
			JSONObject userJsonObject = CommonUtil.httpsRequest(userUrl, "GET", null);
			headimgurl = userJsonObject.getString("headimgurl");// 用户头像
			nickname = userJsonObject.getString("nickname");// 用户昵称
		} else {
			this.response.sendRedirect("/api/auth");
			return;
		}
 		
		User user = this.userService.getUserByOpenId(openId);
		String ip = null;
		try {
			ip = IpUtils.getIpAddress(this.request);
		} catch (Exception e) {
			ip = "0.0.0.0";
		}
		if (user != null) {// 1、如果会员已存在则调用登陆方法
			HttpSession session = this.request.getSession();
			User loginUser = this.userService.login(user.getName(), user.getPassword());
			loginUser.setLoginCount(Integer.valueOf(loginUser.getLoginCount().intValue() + 1));
			session.setAttribute("loginUser", loginUser);
			loginUser.setLastLoginIp(ip);
			loginUser.setLastLoginTime(new Date());
			this.userService.saveOrUpdate(loginUser);
		} else {// 2、如果会员不存在则调用注册方法
			user = new User();
			user.setOpenId(openId);
			user.setName(nickname);
			user.setPhone(nickname);
//			user.setSuperior(tuijianren);
			user.setPassword(Md5.getMD5Code("123456"));// TODO 所有密码都为123456
			user.setLoginCount(Integer.valueOf(0));
			user.setStatus(Integer.valueOf(0));
			user.setBalance(Double.valueOf(0.0D));
			user.setCommission(Double.valueOf(0.0D));
			user.setHeadimgurl(headimgurl);
			user.setDeleted(false);
			user.setCreateDate(new Date());
			User tjrUser = this.userService.getUserByNo(tuijianren);
			if (tjrUser == null) {
				user.setSuperior("");
			} else {
				if (StringUtils.isEmpty(tjrUser.getSuperior())) {
					user.setSuperior(";" + tuijianren + ";");
				} else if (tjrUser.getSuperior().split(";").length > 3)
					user.setSuperior(";" + tjrUser.getSuperior().split(";", 3)[2] + tuijianren + ";");
				else {
					user.setSuperior(tjrUser.getSuperior() + tuijianren + ";");
				}
			}
			
			boolean res = this.userService.saveOrUpdate(user);
			if (res) {// 注册成功后跳转“会员中心”页面
				User loginUser = this.userService.getUserByName(user.getName());
//				loginUser.setLoginCount(Integer.valueOf(loginUser.getLoginCount().intValue() + 1));
				loginUser.setLastLoginIp(ip);
				loginUser.setLastLoginTime(new Date());
				loginUser.setNo(loginUser.getId().toString());
				this.userService.saveOrUpdate(loginUser);
				HttpSession session = this.request.getSession();
				session.setAttribute("loginUser", loginUser);
			}
		}
		this.response.sendRedirect("../index.jsp");
	}
	
	/**
	 * 微信支付回调方法
	 */
	public void callback() throws Exception {
		// 验证支付信息合法性
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        br.close();
        request.getInputStream().close();
		Map<String, String> map = (Map<String, String>)GetWxOrderno.doXMLParse(sb.toString());
		// 过滤空 设置 TreeMap
		SortedMap<Object, Object> newPackageParams = new TreeMap<Object, Object>();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String parameter = (String) it.next();
			String parameterValue = map.get(parameter);
			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			newPackageParams.put(parameter, v);
		}
		if (PayCommonUtil.isTenpaySign("UTF-8", newPackageParams, partnerkey)) {
			String return_code  = (String) map.get("return_code");// 返回结果
			String resXml = "";
			// 重新签名并核对信息确认是真实交易
			if ("SUCCESS".equals(return_code)) {
				String out_trade_no = (String) map.get("out_trade_no");// 订单编号
				String openid = (String) map.get("openid");// 微信用户openId
				String total_fee = (String) map.get("total_fee");// 交易金额
				// 查询红包订单是否已经存在
				Orders findOrders = this.ordersService.findByNo(out_trade_no);
				if (findOrders != null) {// 订单存在
					if ("1".equals(findOrders.getStatus())) {
						return;
					}
					// 更新订单信息
					findOrders.setStatus(1);
					this.ordersService.saveOrUpdate(findOrders);
					// 保存财务信息
					Financial financial = new Financial();
					financial.setType(Integer.valueOf(0));
					financial.setMoney(Double.valueOf(-findOrders.getMoney().doubleValue()));
					financial.setNo("" + System.currentTimeMillis());
					financial.setOperator(findOrders.getUser().getName());
					financial.setUser(findOrders.getUser());
					financial.setCreateDate(new Date());
					financial.setDeleted(false);
					financial.setBalance(findOrders.getUser().getBalance());
					financial.setPayment("微信支付");
					financial.setRemark("购买" + findOrders.getProductName());
					this.financialService.saveOrUpdate(financial);
					Config findConfig = (Config) this.configService.findById(Config.class, 1);
					String levelNos = findOrders.getUser().getSuperior();
					if (!StringUtils.isEmpty(levelNos)) {
						String[] leverNoArr = levelNos.split(";");
						int i = leverNoArr.length - 1;
						for (int j = 1; i > 0; j++) {
							if (!StringUtils.isEmpty(leverNoArr[i])) {
								User levelUser = this.userService.getUserByNo(leverNoArr[i]);
								if (levelUser != null) {
									double commissionRate = 0.0D;
									if (j == 1)
										commissionRate = findConfig.getFirstLevel().doubleValue();
									else if (j == 2)
										commissionRate = findConfig.getSecondLevel().doubleValue();
									else if (j == 3) {
										commissionRate = findConfig.getThirdLevel().doubleValue();
									}
									double commissionNum = findOrders.getMoney().doubleValue() * commissionRate;
									levelUser.setCommission(
											Double.valueOf(levelUser.getCommission().doubleValue() + commissionNum));
									this.userService.saveOrUpdate(levelUser);
									Commission commission = new Commission();
									commission.setType(Integer.valueOf(1));
									commission.setMoney(Double.valueOf(commissionNum));
									commission.setNo("" + System.currentTimeMillis());
									commission.setOperator(findOrders.getUser().getName());
									commission.setUser(levelUser);
									commission.setCreateDate(new Date());
									commission.setDeleted(false);
									commission.setLevel(Integer.valueOf(j));
									commission.setRemark("第" + j + "级用户:编号【" + findOrders.getUser().getNo() + "】购买商品奖励");
									this.commissionService.saveOrUpdate(commission);
								}
							}
							i--;
						}
					}
				}
				resXml = "<xml>"
						+ "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
			} else {
				resXml = "<xml>"
						+ "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
			System.out.println("微信支付回调结束");
		}
	}
	
}

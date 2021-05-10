package com.newwing.fenxiao.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.newwing.fenxiao.entities.Commission;
import com.newwing.fenxiao.entities.Config;
import com.newwing.fenxiao.entities.Financial;
import com.newwing.fenxiao.entities.OrderOther;
import com.newwing.fenxiao.entities.Orders;
import com.newwing.fenxiao.entities.User;
import com.newwing.fenxiao.service.ICommissionService;
import com.newwing.fenxiao.service.IConfigService;
import com.newwing.fenxiao.service.IFinancialService;
import com.newwing.fenxiao.service.IOrderOtherService;
import com.newwing.fenxiao.service.IOrdersService;
import com.newwing.fenxiao.service.IUserService;
import com.newwing.fenxiao.service.IWeixinService;
import com.newwing.fenxiao.utils.Base64;
import com.newwing.fenxiao.utils.Md5;
import com.weixin.utils.CommonUtil;

import net.sf.json.JSONObject;

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
	@Resource(name = "weixinService")
	private IWeixinService weixinService;
	
	
	private String appid = "wxcb1e625e652a1e19";
	private String appsecret = "87f0c0b2d8523bd15ea2efed2abfcbbf";
	private String partner = "1401404002";
	private String partnerkey = "QHTqht201688HYDTxrxy1688NJZYCHIZ";
	private String backUri = "http://www.wesdzsw.com/api/main";
	
	@Resource(name = "ordersService")
	private IOrdersService<Orders> ordersService;
	@Resource(name = "userService")
	private IUserService<User> userService;
	@Resource(name = "orderOtherService")
	private IOrderOtherService<OrderOther> orderOtherService;
	
	/**
	 * 授权页面
	 */
	public void auth() throws Exception {
		String userNo = request.getParameter("userNo");
		String type = request.getParameter("type");
		backUri = backUri + "?userNo=" + userNo;
		if (type != null && "qr".equals(type)) {
			backUri = backUri + "&type=qr";
		}
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
		String userNo = request.getParameter("userNo");
		String type = request.getParameter("type");
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
		if (type != null && "qr".equals(type)) {
			String qrCode = this.userService.generateQrCode(openId, request);
			this.response.sendRedirect("../myQrCode.jsp?qrCode=" + qrCode);
		} else {
			this.response.sendRedirect("../shopRegister.jsp?headimgurl=" + headimgurl 
					+ "&openId=" + openId + "&nickname=" + nickname + "&userNo=" + userNo);
		}
	}
	
	/**
	 * 订单同步：提供给对方调用来同步订单的数据
	 * @throws Exception
	 */
	public void order() throws Exception {
		PrintWriter out = null;
		String code = "";
		String message = "";
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String ORDER_NO = request.getParameter("ORDER_NO");// 订单编号
			String ORDER_PRICE = request.getParameter("ORDER_PRICE");// 订单金额
			String ORDER_CONFIRMTIME = request.getParameter("ORDER_CONFIRMTIME");// 订单日期
			String USER_ID = request.getParameter("USER_ID");// 会员编号
			String ORDER_STATUS = request.getParameter("ORDER_STATUS");// 订单状态
			String SHOP_ID = request.getParameter("SHOP_ID");// 商家编号
			String TIMESTAMP = request.getParameter("TIMESTAMP");// 时间戳
			String SIGN = request.getParameter("SIGN");// 签名
			String key = "Xmwes2016";// 密钥
			
			System.out.println("ORDER_NO : " + ORDER_NO);
			System.out.println("ORDER_PRICE : " + ORDER_PRICE);
			System.out.println("ORDER_CONFIRMTIME : " + ORDER_CONFIRMTIME);
			System.out.println("USER_ID : " + USER_ID);
			System.out.println("ORDER_STATUS : " + ORDER_STATUS);
			System.out.println("SHOP_ID : " + SHOP_ID);
			System.out.println("TIMESTAMP : " + TIMESTAMP);
			System.out.println("SIGN : " + SIGN);
			System.out.println("key : " + key);
			
			if (ORDER_NO == null || "".equals(ORDER_NO.trim())) {
				throw new Exception("参数有误");
			}
			if (ORDER_PRICE == null || "".equals(ORDER_PRICE.trim())) {
				throw new Exception("参数有误");
			}
			if (ORDER_CONFIRMTIME == null || "".equals(ORDER_CONFIRMTIME.trim())) {
				throw new Exception("参数有误");
			}
			if (USER_ID == null || "".equals(USER_ID.trim())) {
				throw new Exception("参数有误");
			}
			if (ORDER_STATUS == null || "".equals(ORDER_STATUS.trim())) {
				throw new Exception("参数有误");
			}
			if (SHOP_ID == null || "".equals(SHOP_ID.trim())) {
				throw new Exception("参数有误");
			}
			if (TIMESTAMP == null || "".equals(TIMESTAMP.trim())) {
				throw new Exception("参数有误");
			}
			ORDER_NO = Base64.getFromBase64(ORDER_NO);
			ORDER_PRICE = Base64.getFromBase64(ORDER_PRICE);
			ORDER_CONFIRMTIME = Base64.getFromBase64(ORDER_CONFIRMTIME);
			USER_ID = Base64.getFromBase64(USER_ID);
			ORDER_STATUS = Base64.getFromBase64(ORDER_STATUS);
			SHOP_ID = Base64.getFromBase64(SHOP_ID);
			TIMESTAMP = Base64.getFromBase64(TIMESTAMP);
			
			if (ORDER_STATUS == null || !"B".equals(ORDER_STATUS)) {
				throw new Exception("订单状态不对");
			}
			
			System.out.println("ORDER_NO : " + ORDER_NO);
			System.out.println("ORDER_PRICE : " + ORDER_PRICE);
			System.out.println("ORDER_CONFIRMTIME : " + ORDER_CONFIRMTIME);
			System.out.println("USER_ID : " + USER_ID);
			System.out.println("ORDER_STATUS : " + ORDER_STATUS);
			System.out.println("SHOP_ID : " + SHOP_ID);
			System.out.println("TIMESTAMP : " + TIMESTAMP);
			System.out.println("SIGN : " + SIGN);
			System.out.println("key : " + key);
			
			// 进行签名验证
			String strObj = ORDER_NO + USER_ID + ORDER_PRICE + ORDER_CONFIRMTIME + ORDER_STATUS + SHOP_ID + TIMESTAMP + key;
			String newSign = Md5.getMD5Code(strObj);
			
			System.out.println("newSign >>>>>>>>>> " + newSign);
			if (SIGN == null || "".equals(SIGN) || !newSign.equals(SIGN)) {
				throw new Exception("签名错误");
			}
			
			// 通过会员编号、订单金额进行分销佣金计算
			User user = this.userService.getUserByPhone(USER_ID);// 订单所属会员
			User shop = this.userService.getUserByPhone(SHOP_ID);// 订单所属商家
			if (user == null && shop == null) {
				try {
					OrderOther orderOther = new OrderOther();
					orderOther.setCreateDate(new Date());
					orderOther.setDeleted(false);
					orderOther.setMoney(new Double(ORDER_PRICE));
					orderOther.setNo(ORDER_NO);
					orderOther.setPayDate(stampToDate(ORDER_CONFIRMTIME));// 戳转日期
					orderOther.setProductId(null);
					orderOther.setProductMoney(new Double(ORDER_PRICE));
					orderOther.setProductName("");
					orderOther.setProductNum(1);
					orderOther.setShop(shop);
					orderOther.setStatus(2);
					orderOther.setSummary("西安同步的订单");
					orderOther.setUser(user);
					OrderOther OrderOtherTemp = this.orderOtherService.findByNo(ORDER_NO);
					if (OrderOtherTemp != null) {
						throw new Exception("订单已经存在，请勿重新同步！");
					}
					this.orderOtherService.saveOrUpdate(orderOther);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				Orders orders = new Orders();
				orders.setCreateDate(new Date());
				orders.setDeleted(false);
				orders.setMoney(new Double(ORDER_PRICE));
				orders.setNo(ORDER_NO);
				orders.setPayDate(stampToDate(ORDER_CONFIRMTIME));// 戳转日期
				orders.setProductId(null);
				orders.setProductMoney(new Double(ORDER_PRICE));
				orders.setProductName("");
				orders.setProductNum(1);
				orders.setShop(shop);
				orders.setStatus(2);
				orders.setSummary("西安同步的订单");
				orders.setUser(user);
				
				Orders orderTemp = this.ordersService.findByNo(ORDER_NO);
				if (orderTemp != null) {
					throw new Exception("订单已经存在，请勿重新同步！");
				}
				this.ordersService.saveSynchroOrder(orders);// 还需要进行分润处理
			}
			code = "0000";
			message = "";
		} catch (Exception e) {
			e.printStackTrace();
			code = "0001";
			message = e.getMessage();
		} finally {
			JSONObject json = new JSONObject();
			json.put("code",code);
			json.put("message",message);
			
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}
	
	/* 
     * 将时间戳转换为时间
     */
    private Date stampToDate(String s){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
		try {
			date = simpleDateFormat.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
    }
	
}

package com.newwing.fenxiao.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.newwing.fenxiao.entities.BaseBean;

@Entity
@Table(name = "user")
public class User extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String no;
	private String name;
	private String phone;
	private String password;
	private String password2;
	private Double balance;
	private Double commission;
	private String superior;
	private Integer status;
	private String type;// 会员类别 0-普通会员；1-普通商家会员 2-白金商户 3-铂金砂商户 4- 钻石商户
	private String typeName;
	private String address;// 会员地址（个人会员）
	private String remark;// 备注
	private String superNo;// 直接上级no
	private String qrCodeUrl;// 二维码

	@Temporal(TemporalType.TIMESTAMP)
	private Date statusDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;
	private String lastLoginIp;
	private String registerIp;
	private Integer loginCount;
	private String openId;
	private String headimgurl;
	private String nickname;// 微信昵称

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getCommission() {
		return this.commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public String getRegisterIp() {
		return this.registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getSuperior() {
		return this.superior;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getStatusDate() {
		return this.statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getTypeName() {
		if ("0".equals(type)) {
			typeName = "用户会员";
		} else if ("1".equals(type)) {
			typeName = "普通商户";
		} else if ("2".equals(type)) {
			typeName = "白金商户";
		} else if ("3".equals(type)) {
			typeName = "铂金商户";
		} else if ("4".equals(type)) {
			typeName = "钻石商户";
		}
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSuperNo() {
		return superNo;
	}

	public void setSuperNo(String superNo) {
		this.superNo = superNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	
}
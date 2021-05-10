<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta content="telephone=no" name="format-detection">
		<!--  ico  -->
	<link href="res/images/jylogo.ico " rel="shortcut icon"type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="css/style.css" media="all">
	<style type="text/css">
		.mainBgColor{
			background:#c72525!important;
		}
		.white{
			color:#FFF!important;
		}
	</style>
	<title>会员注册</title>
	<script type="text/javascript" async="" src="js/aywmq.js"></script>
	<script async="" src="js/analytics.js"></script>
	<script type="text/javascript" async="" src="js/da_opt.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">
		function sendSms(){
			var phone = $("#phone").val();
			var c = /^[1]\d{10}$/;
			if(phone==""){
				alertDefaultStyle("mini", "手机号不能为空");
			} else if (!c.test(phone)) {
				alertDefaultStyle("mini", "手机号码格式错误");
			} else{
				$.ajax({
				    url: "sendSms",
				    type: "POST",
				    data: {
				    	"phone":phone
				    },
				    dataType: "json",
				    async: true,
				    success: function(data) {
				    	alertDefaultStyle("mini", data.message);
				    	if(data.status=="1"){
				    		settime();
				    	}
				    }
				});
			}
		}
		var countdown=30; 
		function settime() {
			var val = $("#getCode");
			if (countdown == 0) { 
				val.attr("href", "javascript:sendSms()");
				val.html("获取验证码");
				countdown = 30; 
			} else { 
				val.attr("href", "javascript:void(0)");
				val.html("重新发送(" + countdown + ")"); 
				countdown--;
				setTimeout(function() { 
					settime();
				},1000);
			} 
		}
	</script>
</head>
<body>
    <div class="sn-nav mainBgColor">
<!-- 		<div class="sn-nav-back"> -->
<!-- 		<a class="sn-iconbtn" href="index.jsp">返回</a></div> -->
		<div class="sn-nav-title of white">会员注册</div>
	</div>
	<section class="sn-main pr">
<!-- 		<div class="input-a sn-block wbox mt30 pr"> -->
<!-- 			<span>编号</span> -->
<!-- 			<div class="wbox-flex ml30 pr"> -->
<!-- 				<input type="text" id="no" name="user.no" maxlength="6" value="123456">	 -->
<!-- 			</div> -->
<!-- 			<em class="delete" style="display:none" name="Wap_reg_person_001"></em> -->
<!-- 		</div> -->
<!-- 		<div class="input-a sn-block wbox mt30 pr"> -->
<!-- 			<span>用户名</span> -->
<!-- 			<div class="wbox-flex ml30 pr"> -->
<!-- 				<input type="text" id="name" name="user.name" value="" placeholder="请输入用户名" maxlength="32">	 -->
<!-- 			</div> -->
<!-- 			<em class="delete" style="display:none" name="Wap_reg_person_001"></em> -->
<!-- 		</div> -->
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:18%">
				<span>手机号</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="tel" id="phone" name="user.phone" value="" placeholder="请输入11位手机号码" maxlength="11">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div> 
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:18%">
				<span>验证码</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="text" id="code" name="code" value="" placeholder="请输入验证码" maxlength="6">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
			<a href="javascript:sendSms();" class="getimgcode bl" id="getCode">
				获取验证码
			</a>
		</div>
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:18%">
				<span>密码</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="password" id="password" name="user.password" value="" placeholder="请输入密码" maxlength="32">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:18%">
				<span>确认密码</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="password" id="repassword" value="" placeholder="请重新输入密码" maxlength="32">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		
		<p class="assisFun f14 m30">重要：二级密码用于转账及提现使用，请认真填写</p>
		
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:18%">
				<span>二级密码</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="password" id="password2" name="user.password2" value="" placeholder="请输入密码" maxlength="32">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:18%">
				<span>确认密码</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="password" id="repassword2" value="" placeholder="请重新输入密码" maxlength="32">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		
<!-- 		<div class="input-a sn-block wbox mt30 pr"> -->
<!-- 			<span>推荐人</span> -->
<!-- 			<div class="wbox-flex ml30 pr"> -->
<!-- 				<input type="text" id="tuijianren" name="tuijianren" value="" placeholder="请输入推荐人编号" maxlength="32">	 -->
<!-- 			</div> -->
<!-- 			<em class="delete" style="display:none" name="Wap_reg_person_001"></em> -->
<!-- 		</div> -->
		<a href="javascript:void(0)" id="nextStep" name="Wap_reg_person_005" onclick="register(); return false;" class="first-step sn-btn sn-btn-big sn-btn-block m30 sn-btn-positive mainBgColor">注册</a>
<!-- 		<p class="assisFun f14 m30">已有账号，直接<a href="http://www.9hgwsc.com/login" name="WAP_login_none_register">登录</a> -->
<!-- 		<a href="findPassword.jsp">忘记密码?</a> -->
<!-- 		</p> -->
	</section>
	<script type="text/javascript" src="js/zepto.min.js"></script>

</body></html>
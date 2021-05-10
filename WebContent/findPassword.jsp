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
	<title>忘记密码</title>
	<script type="text/javascript" async="" src="js/aywmq.js"></script>
	<script async="" src="js/analytics.js"></script>
	<script type="text/javascript" async="" src="js/da_opt.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">
	function findPassword(){
		var phone = $("#phone").val();
		var c = /^[1]\d{10}$/;
		if(phone==""){
			alertDefaultStyle("mini", "手机号不能为空");
		}else if (!c.test(phone)) {
			 alertDefaultStyle("mini", "手机号码格式错误");
		}else{
			$.ajax({
			    url: "createPhoneCode",
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
			val.attr("href", "javascript:findPassword()");
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
	
	function findPasswordSub(){
		var name = $("#name").val();
		var phone = $("#phone").val();
		var code = $("#code").val();
		var c = /^[1]\d{10}$/;
		if(name==""){
			alertDefaultStyle("mini", "用户名不能为空");
		}else if(phone==""){
			alertDefaultStyle("mini", "手机号不能为空");
		}else if (!c.test(phone)) {
			 alertDefaultStyle("mini", "手机号码格式错误");
		}else if (code=="") {
			 alertDefaultStyle("mini", "手机验证码不能为空");
		}else{
			window.location.href="validationCode?name="+name+"&phone="+phone+"&code="+code;
		}
	}
	
	// 忘记密码后重置密码
	function resetPassword(){
		var phone = $("#phone").val();
		var password = $("#password").val();
		var repassword = $("#repassword").val();
		if(password==""){
			alertDefaultStyle('mini','密码不能为空');
		}else if(repassword==""){
			alertDefaultStyle('mini','确认密码不能为空');
		}else if(password!=repassword){
			alertDefaultStyle('mini','两次密码输入不一致');
		}else{
			$.ajax({
		        url: "resetPassword",
		        type: "POST",
		        data: {
		        	"phone": phone,
		            "password": password
		        },
		        dataType: "json",
		        async: true,
		        success: function(data) {
		        	alertDefaultStyle("mini", data.message);
		            if ("1" == data.status) {
		            	setTimeout("window.location.href='login.jsp';",2000);
		            }
		        },
		        error: function() {
		            alertDefaultStyle("mini", "连接失败")
		        }
		    });
		}
	}
	</script>
</head>
<body>
    <div class="sn-nav mainBgColor">
<!-- 		<div class="sn-nav-back"> -->
<!-- 		<a class="sn-iconbtn" href="index.jsp">返回</a> -->
<!-- 		</div> -->
		<div class="sn-nav-title of white">忘记密码</div>
	</div>
	<section class="sn-main pr">
<!-- 		<div class="input-a sn-block wbox mt30 pr"> -->
<!-- 			<span>用户名</span> -->
<!-- 			<div class="wbox-flex ml30 pr"> -->
<!-- 				<input type="text" id="name" name="name" value="" placeholder="请输入用户名" maxlength="32">	 -->
<!-- 			</div> -->
<!-- 			<em class="delete" style="display:none" name="Wap_reg_person_001"></em> -->
<!-- 		</div> -->
		
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:18%">
				<span>手机号</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="text" id="phone" name="phone" value="" placeholder="请输入手机号" maxlength="11">	
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
			<a href="javascript:findPassword()" class="getimgcode bl" id="getCode">
				获取验证码
			</a>
		</div>
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:18%">
				<span>新密码</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="text" id="phone" name="phone" value="" placeholder="请输入新密码" maxlength="11">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:18%">
				<span>再次输入</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="text" id="phone" name="phone" value="" placeholder="请再次输入新密码" maxlength="32">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		<a href="javascript:void(0)" id="nextStep" name="Wap_reg_person_005" onclick="resetPassword();return false;" class="first-step sn-btn sn-btn-big sn-btn-block m30 sn-btn-positive mainBgColor">确认</a>
<!-- 		<p class="assisFun f14 m30"> -->
<!-- 		<a href="login.jsp" name="WAP_login_none_forgetPswd">登录</a> -->
<!-- 		<a href="register.jsp" name="WAP_login_none_register">快速注册</a> -->
<!-- 		</p> -->
		
	</section>
	<script type="text/javascript" src="js/zepto.min.js"></script>

</body></html>
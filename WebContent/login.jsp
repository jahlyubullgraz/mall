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
	<title>会员登录</title>
	<script type="text/javascript" async="" src="js/aywmq.js"></script>
	<script async="" src="js/analytics.js"></script>
	<script type="text/javascript" async="" src="js/da_opt.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<style type="text/css">
		.mainBgColor{
			background:#c72525!important;
		}
		.white{
			color:#FFF!important;
		}
		.sn-nav-back:before{
		 border: solid #fff;
		 border-width: 1px 0 0 1px;
		}
		.greText{
			color:#999!important
		}
	</style>
</head>
<body>
    <div class="sn-nav mainBgColor">
<!-- 		<div class="sn-nav-back"> -->
<!-- 		<a class="sn-iconbtn" href="index.jsp">返回</a></div> -->
		<div class="sn-nav-title of white">登录</div>
	</div>
	<section class="sn-main pr">
		<div class="input-a sn-block wbox mt30 pr">
			<span>账号</span>
			<div class="wbox-flex ml30 pr">
				<input type="text" id="name" name="user.name" value="" placeholder="请输入账号" maxlength="11">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		
		<div class="input-a sn-block wbox mt30 pr">
			<span>密码</span>
			<div class="wbox-flex ml30 pr">
				<input type="password" id="password" name="user.password" value="" placeholder="请输入密码" maxlength="32">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		<a href="javascript:void(0)" id="nextStep" name="Wap_reg_person_005" onclick="login();return false;" class="first-step sn-btn sn-btn-big sn-btn-block m30 sn-btn-positive mainBgColor">登录</a>
		<p class="assisFun f14 m30">
<!-- 		<a href="register.jsp">快速注册</a> -->
		<a href="findPassword.jsp" style="text-align:center;width:100%" class="greText">忘记密码?</a>
		</p>
		
	</section>
	<script type="text/javascript" src="js/zepto.min.js"></script>

</body></html>
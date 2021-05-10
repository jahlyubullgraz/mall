<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String qrCode = request.getParameter("qrCode");
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
	<title>我的二维码</title>
	<script type="text/javascript" async="" src="js/aywmq.js"></script>
	<script async="" src="js/analytics.js"></script>
	<script type="text/javascript" async="" src="js/da_opt.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body>
	<section class="sn-main pr">
		<div class="input-a sn-block wbox mt30 pr">
			<img alt="" src="<%=qrCode%>" />
		</div>
	</section>
	<script type="text/javascript" src="js/zepto.min.js"></script>

</body></html>
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
	<link rel="stylesheet" type="text/css" href="../css/style.css" media="all">
	<style type="text/css">
		.mainBgColor{
			background:#c72525!important;
		}
		.white{
			color:#FFF!important;
		}
	</style>
	<title>创建订单</title>
	<script type="text/javascript" async="" src="../js/aywmq.js"></script>
	<script async="" src="../js/analytics.js"></script>
	<script type="text/javascript" async="" src="../js/da_opt.js"></script>
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		createUserNo();
	});
	</script>
</head>
<body>
    <div class="sn-nav mainBgColor">
		<div class="sn-nav-back">
			<a class="sn-iconbtn" href="index.jsp">返回</a>
		</div>
		<div class="sn-nav-title of white">创建订单</div>
	</div>
	<section class="sn-main pr">
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:18%">
				<span>商家编号</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="tel" id="phone" name="shopNo" placeholder="请输入商家编号" maxlength="11">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div> 
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:18%">
				<span>消费金额</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="text" id="password" name="money" placeholder="请输入消费金额" maxlength="32">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:18%">
				<span>上传凭证</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="password" id="repassword" placeholder="请上传凭证" maxlength="32">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:18%">
				<span>消费项目</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="text" id="summary" name="summary" placeholder="请输入消费项目" maxlength="32">	
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		
		<p class="assisFun f14 m30"><input type="checkbox" checked />我承诺订单真实有效，如有虚假愿意承担相关后果</p>
		
		<a href="javascript:void(0)" id="nextStep" name="Wap_reg_person_005" onclick="ordersSave(); return false;" class="first-step sn-btn sn-btn-big sn-btn-block m30 sn-btn-positive mainBgColor">提交</a>
	</section>
	<script type="text/javascript" src="js/zepto.min.js"></script>

</body>
</html>
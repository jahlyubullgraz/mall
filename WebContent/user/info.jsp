<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<link href="../res/images/jylogo.ico " rel="shortcut icon"type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<link rel="stylesheet" type="text/css" href="../css/cart.css">
	<style type="text/css">
		.mainBgColor{
			background:#c72525!important;
		}
		.white{
			color:#FFF!important;
		}
	</style>
	<title>我的信息</title>
	<script type="text/javascript" async="" src="../js/aywmq.js"></script>
	<script async="" src="../js/analytics.js"></script>
	<script type="text/javascript" async="" src="../js/da_opt.js"></script>
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script>
    $(function(){
		$.ajax({
		    url: "userInfoJson",
		    type: "GET",
		    dataType: "json",
		    async: false,
		    success: function(data) {
		        $("#balance").html(data.balance+"元");
		        $("#commission").html(data.commission+"元");
		    }
		});
	});
    </script>
</head>
<body>
    <div class="sn-nav mainBgColor">
		<div class="sn-nav-back"><a href="javascript:history.back(-1)">返回</a></div>
		<div class="sn-nav-title of white" id="addAddr">我的信息</div>
	</div>
	<section class="sn-main pr">
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:20%">
				<span>姓名:</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="text" name="user.sex" value="${loginUser.name }" placeholder="请输入姓名" />
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:20%">
				<span>性别:</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="text" name="user.sex" value="${loginUser.name }" />
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:20%">
				<span>手机号:</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="text" name="user.phone" value="${loginUser.phone}" readonly />
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:20%">
				<span>详细地址:</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="text" name="user.address" value="${loginUser.address}" placeholder="请输入详细地址"/>
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		<div class="input-a sn-block wbox mt30 pr">
			<div style="width:20%">
				<span>备注:</span>
			</div>
			<div class="wbox-flex ml30 pr">
				<input type="text" name="user.address" value="${loginUser.remark}" placeholder="请输入备注" />
			</div>
			<em class="delete" style="display:none" name="Wap_reg_person_001"></em>
		</div>
		<a href="changePassword.jsp" class="first-step sn-btn sn-btn-big sn-btn-block m30 sn-btn-positive mainBgColor">保存</a>
	</section>
	<script type="text/javascript" src="../js/zepto.min.js"></script>

</body>
</html>
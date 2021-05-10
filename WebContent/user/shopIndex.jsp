<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta content="telephone=no" name="format-detection" />
	<title>商家中心</title>
	<link href="../res/images/jylogo.ico " rel="shortcut icon"type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="../css/base.css">
	<link rel="stylesheet" type="text/css" href="../css/member.rem.css">
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
			.borderTB{
				border-top: 1px solid #dcdcdc;
				border-bottom: 1px solid #dcdcdc;
			}
			.marginTop_10{
				margin-top:10px
			}
			.whiteBg{
				background:#fff;
			}
			.rightMune{
			    position: absolute;
			    top: -2px;
			    right: 25px;
			}
			.rightMune button{
				background:#c72525!important;
				color:#FFF!important;
				border:0;
				padding:3px;
				border-radius:5px
			}
			.nav_header{
				height:40px!important;
				line-height:40px!important;
			}
	</style>
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
		<div class="sn-nav-back"><a class="sn-iconbtn" href="../index.jsp">返回</a></div>
		<div class="sn-nav-title of white">商家中心</div>
	<div class="sn-block meb-list" style="background:#f2f2f2">
		<ul class="sn-list-input">
		</ul>
			<ul class="sn-list-input borderTB marginTop_10 whiteBg">
				<li>
					<a class="block wbox" >
						<label class="meb-list-ico" style="left center;background-size:1rem 1rem;padding-left:0">账号</label>
						<div class="wbox-flex tr sn-txt-muted arrow">${sessionScope.loginUser.phone }</div>
					</a>
				</li>
			</ul>
			<ul class="sn-list-input borderTB marginTop_10 whiteBg">
				<li>
					<a href="promote.jsp" class="block wbox" >
						<label class="meb-list-ico" style="left center;background-size:1rem 1rem;padding-left:0">分享</label>
						<div class="wbox-flex tr sn-txt-muted arrow">
								<span id="toExpiredCouponsNum"></span>
						</div>
					</a>
				</li>
			</ul>
			<ul class="sn-list-input borderTB marginTop_10 whiteBg">
				<li>
				<a href="shopOrdersList.jsp" class="block wbox" id="mts-allorder">
					<label class="meb-list-ico" style="left center;background-size:1rem 1rem;padding-left:0">订单</label>
					<div class="wbox-flex tr sn-txt-muted arrow">0个未确认</div>
				</a>
				</li>
			</ul>
			<ul class="sn-list-input borderTB marginTop_10 whiteBg">
				<li>
					<a href="commissionList.jsp" class="block wbox" >
						<label class="meb-list-ico" style="left center;background-size:1rem 1rem;padding-left:0">佣金</label>
						<div class="wbox-flex tr sn-txt-muted arrow">
						</div>
					</a>
				</li>
			</ul>
			<ul class="sn-list-input borderTB marginTop_10 whiteBg">
				<li>
					<a href="financialList.jsp" class="block wbox" >
						<label class="meb-list-ico" style="left center;background-size:1rem 1rem;padding-left:0">财务</label>
						<div class="wbox-flex tr sn-txt-muted arrow">
						</div>
					</a>
				</li>
<!-- 				<li> -->
<!-- 					<a href="withdraw.jsp" class="block wbox" > -->
<!-- 						<label class="meb-list-ico" style="background:url('../images/ico-myS.png') no-repeat left center;background-size:1rem 1rem;">申请提现</label> -->
<!-- 						<div class="wbox-flex tr sn-txt-muted arrow"> -->
<!-- 						</div> -->
<!-- 					</a> -->
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<a href="withdrawList.jsp" class="block wbox" > -->
<!-- 						<label class="meb-list-ico" style="background:url('../images/143558597675811964.jpg') no-repeat left center;background-size:1rem 1rem;">提现列表</label> -->
<!-- 						<div class="wbox-flex tr sn-txt-muted arrow"> -->
<!-- 						</div> -->
<!-- 					</a> -->
<!-- 				</li> -->
			</ul>
	</div>
	<div style="padding-left:10px;padding-right:10px">
		<a href="logout" class="first-step sn-btn sn-btn-big sn-btn-block m30 sn-btn-positive mainBgColor marginTop_10">退出登录</a>
	</div>
</div>

<script type="text/javascript" src="/RES/wap/common/script/lib/zepto/1.1.4/zepto.min.js?v=3.1.3"></script>
<script type="text/javascript" src="http://passport.suning.com/ids/js/passport.js?v=3.1.3"></script>
<script type="text/javascript" src="/RES/wap/common/script/module/alertBox/2.0.0/alertBox.js?v=3.1.3"></script>
<script type="text/javascript" src="/RES/wap/accountcenterv2/script/myneedtodoList.js?v=3.1.3"></script>

<script type="text/javascript" src="/RES/wap/common/script/res/buriedpoint.js?v=3.1.3" id="mts-buriedpoint" 
	mts-path-root="/RES" mts-cache-version="3.1.3">
</script>

<script type="text/javascript">
var pageNumber = 1;
var totalPages = 0;
var pageSize = 10;
var resRoot = '/RES';
var base = '';
$(function(){
	getToExpiredCouponListNum()
})

</script>

</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="css/xiaomi.css"/>
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script src="js/jquery.animate-colors-min.js"></script>
</head>
<body>
     <div class="head_box">
         <div id="head_wrap">
             <div id="head_nav">
                 <a class="head_nav_a">å¤§ç±³ç½</a>
                 <span>|</span>
                 <a class="head_nav_a">MIUI</a>
                 <span>|</span>
                 <a class="head_nav_a">ç±³è</a>
                 <span>|</span>
                 <a class="head_nav_a">æ¸¸æ</a>
                 <span>|</span>
                 <a class="head_nav_a">å¤çéè¯»</a>
                 <span>|</span>
                 <a class="head_nav_a">äºæå¡</a>
                 <span>|</span>
                 <a class="head_nav_a">å¤§ç±³ç§»å¨ç</a>
                 <span>|</span>
                 <a class="head_nav_a">é®é¢åé¦</a>
                 <span>|</span>
                 <a class="head_nav_a" id="Select_Region_but">Select Region</a>
             </div>
             <div id="head_right">
                 <div id="head_landing">
                     <a class="head_nav_a">ç»é</a>
                     <span>|</span>
                     <a class="head_nav_a">æ³¨å</a>
                 </div>
                 <div id="head_car">
                     <a class="head_car_text">è´­ç©è½¦ï¼0ï¼</a>
                     <div id="car_content" style="height: 0px;width:0px ;background-color: #edffc6;z-index: 999">
                         <a class="car_text"></a>
                     </div>
                 </div>
             </div>
         </div>
     </div>
     <div id="main_head_box">
         <div id="menu_wrap">
             <div id="menu_logo">
                 <img src="img/xiaomi_logo.png">
             </div>
             <div id="menu_nav">
                 <ul>
                     <li class="menu_li" control="xiaomiphone">å¤§ç±³ææº</li>
                     <li class="menu_li" control="hongmiphone">çº¢ç±³</li>
                     <li class="menu_li" control="pingban">å¹³æ¿</li>
                     <li class="menu_li" control="tv">çµè§&nbsp;&nbsp;çå­</li>
                     <li class="menu_li" control="luyou">è·¯ç±å¨</li>
                     <li class="menu_li" control="yingjian">æºè½ç¡¬ä»¶</li>
                     <li><a>æå¡</a></li>
                     <li><a>ç¤¾åº</a></li>
                 </ul>
             </div>
             <div id="find_wrap">
                 <div id="find_bar">
                     <input type="text" id="find_input">
                 </div>
                 <div id="find_but">GO</div>
             </div>
         </div>
     </div>
     <div id="menu_content_bg" style="height: 0px;">
         <div id="menu_content_wrap">
             <ul style="top: 0px;">
                 <li id="xiaomiphone">
                     <div class="menu_content">
                         <img src="img/mi4!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³ææº4</p>
                         <p class="menu_content_price">1499åèµ·</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/minote!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³NOTEæ åç</p>
                         <p class="menu_content_price">1999åèµ·</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/minotepro!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³NOTEé¡¶éç</p>
                         <p class="menu_content_price">2999åèµ·</p>
                     </div>
                 </li>
                 <li id="hongmiphone">
                     <div class="menu_content">
                         <img src="img/hongmi2a!160x110.jpg">
                         <p class="menu_content_tit">çº¢ç±³ææº2A</p>
                         <p class="menu_content_price">499å</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/hongmi2!160x110.jpg">
                         <p class="menu_content_tit">çº¢ç±³ææº2</p>
                         <p class="menu_content_price">599å</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/note!160x110.jpg">
                         <p class="menu_content_tit">çº¢ç±³NOTE</p>
                         <p class="menu_content_price">699å</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/note2!160x110.jpg">
                         <p class="menu_content_tit">çº¢ç±³NOTE2</p>
                         <p class="menu_content_price">799å</p>
                     </div>
                 </li>
                 <li id="pingban">
                     <div class="menu_content">
                         <img src="img/mipad16!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³å¹³æ¿16G</p>
                         <p class="menu_content_price">1299å</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/mipad64!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³å¹³æ¿64G</p>
                         <p class="menu_content_price">1499åèµ·</p>
                     </div>
                 </li>
                 <li id="tv">
                     <div class="menu_content">
                         <img src="img/mitv40!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³çµè§2&nbsp;40è±å¯¸</p>
                         <p class="menu_content_price">1999å</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/mitv48!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³çµè§2S&nbsp;48è±å¯¸</p>
                         <p class="menu_content_price">2999åèµ·</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/mitv49!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³çµè§2&nbsp;49è±å¯¸</p>
                         <p class="menu_content_price">3399åèµ·</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/mitv55!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³çµè§2&nbsp;55è±å¯¸</p>
                         <p class="menu_content_price">4499åèµ·</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/hezimini!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³çå­MINIç</p>
                         <p class="menu_content_price">199å</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/hezis!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³çå­å¢å¼ºç</p>
                         <p class="menu_content_price">299å</p>
                     </div>
                 </li>
                 <li id="luyou">
                     <div class="menu_content">
                         <img src="img/miwifi!160x110.jpg">
                         <p class="menu_content_tit">å¨æ°å¤§ç±³è·¯ç±å¨</p>
                         <p class="menu_content_price">699åèµ·</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/miwifimini!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³è·¯ç±å¨&nbsp;MINI</p>
                         <p class="menu_content_price">129å</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/miwifilite!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³è·¯ç±å¨&nbsp;éæ¥ç</p>
                         <p class="menu_content_price">79å</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/wifiExtension!160x110.jpg">
                         <p class="menu_content_tit">å¤§ç±³WIFIæ¾å¤§å¨</p>
                         <p class="menu_content_price">39å</p>
                     </div>
                 </li>
                 <li id="yingjian">
                     <div class="menu_content">
                         <img src="http://c1.mifile.cn/f/i/15/goods/nav/scale!160x110.jpg">
                         <p class="menu_content_tit">ä½éç§°</p>
                         <p class="menu_content_price">99å</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/xiaoyi!160x110.jpg">
                         <p class="menu_content_tit">æåå¤´</p>
                         <p class="menu_content_price">129åèµ·</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/yicamera!160x110.jpg">
                         <p class="menu_content_tit">è¿å¨ç¸æº</p>
                         <p class="menu_content_price">399åèµ·</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/ihealth!160x110.jpg">
                         <p class="menu_content_tit">è¡åè®¡</p>
                         <p class="menu_content_price">199å</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/chazuo!160x110.jpg">
                         <p class="menu_content_tit">æºè½æåº§</p>
                         <p class="menu_content_price">59å</p>
                     </div>
                     <span class="menu_content_line"></span>
                     <div class="menu_content">
                         <img src="img/smart!160x110.jpg">
                         <p class="menu_content_tit">æ¥çå¨é¨
                             <br>æºè½ç¡¬ä»¶</p>
                     </div>
                 </li>
             </ul>
         </div>
     </div>
     <div id="big_banner_wrap">
         <ul id="banner_menu_wrap">
             <li class="active"img>
                 <a>ææº&nbsp;å¹³æ¿</a>
                 <a class="banner_menu_i">&gt;</a>
                 <div class="banner_menu_content" style="width: 600px; top: -20px;">
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/minote.jpg"></a><a>å¤§ç±³NOTE</a><span>éè´­</span></li>
                         <li>
                             <a><img src="img/mi4.jpg"></a><a>å¤§ç±³ææº4</a><span>éè´­</span></li>
                         <li>
                             <a><img src="img/hongmi2.jpg"></a><a>çº¢ç±³ææº2</a><span>éè´­</span></li>
                         <li>
                             <a><img src="img/hongmi2a.jpg"></a><a>çº¢ç±³ææº2A</a><span>éè´­</span></li>
                         <li>
                             <a><img src="img/note2.jpg"></a><a>çº¢ç±³NOTE2</a><span>éè´­</span></li>
                         <li>
                             <a><img src="img/note2.jpg"></a><a>çº¢ç±³NOTE&nbsp;4Gåå¡</a><span>éè´­</span></li>
                     </ul>
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/mipad.jpg"></a><a>å¤§ç±³å¹³æ¿</a><span>éè´­</span></li>
                         <li>
                             <a><img src="img/telcom.jpg"></a><a>çµä¿¡ç</a></li>
                         <li>
                             <a><img src="img/heyue.jpg"></a><a>åçº¦æº</a></li>
                         <li>
                             <a><img src="img/zhongxin.jpg"></a><a>ä¸­ä¿¡ç¹æ</a></li>
                         <li>
                             <a><img src="img/compare.jpg"></a><a>å¯¹æ¯ææº</a></li>
                     </ul>
                 </div>
             </li>
             <li>
                 <a>çµè§&nbsp;çå­</a>
                 <a class="banner_menu_i">&gt;</a>
                 <div class="banner_menu_content" style="width: 600px; top: -62px;">
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/tv40.jpg"></a><a>å¤§ç±³çµè§40è±å¯¸</a></li>
                         <li>
                             <a><img src="img/tv48.jpg"></a><a>å¤§ç±³çµè§48è±å¯¸</a></li>
                         <li>
                             <a><img src="img/tv49.jpg"></a><a>å¤§ç±³çµè§49è±å¯¸</a></li>
                         <li>
                             <a><img src="img/hezis.jpg"></a><a>å¤§ç±³çµè§55è±å¯¸</a></li>
                         <li>
                             <a><img src="img/hezis.jpg"></a><a>å¤§ç±³çå­</a></li>
                         <li>
                             <a><img src="img/hezis.jpg"></a><a>å¤§ç±³çå­MINI</a></li>
                     </ul>
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/dianshipeijian.jpg"></a><a>å¤§ç±³çµè§éä»¶</a><span>éè´­</span></li>
                     </ul>
                 </div>
             </li>
             <li>
                 <a>è·¯ç±å¨&nbsp;æºè½éä»¶</a>
                 <a class="banner_menu_i">&gt;</a>
                 <div class="banner_menu_content" style="width: 900px; top: -104px;">
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/miwifi.jpg"></a><a>å¤§ç±³è·¯ç±å¨</a></li>
                         <li>
                             <a><img src="img/miwifilite.jpg"></a><a>å¤§ç±³è·¯ç±å¨&nbsp;éæ¥ç</a></li>
                         <li>
                             <a><img src="img/air.jpg"></a><a>ååå¨</a></li>
                         <li>
                             <a><img src="img/xiaoyi.jpg"></a><a>æåæº</a></li>
                         <li>
                             <a><img src="img/scale.jpg"></a><a>ä½éç§°</a></li>
                         <li>
                             <a><img src="img/scale.jpg"></a><a>æºè½æå¤´</a></li>
                     </ul>
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/miwifimini.jpg"></a><a>å¤§ç±³è·¯ç±å¨MINI</a></li>
                         <li>
                             <a><img src="img/wifiExtension.jpg"></a><a>å¤§ç±³WIFIæ¾å¤§å¨</a></li>
                         <li>
                             <a><img src="img/yicamera.jpg"></a><a>è¿å¨ç¸æº</a></li>
                         <li>
                             <a><img src="img/water.jpg"></a><a>åæ°´å¨</a></li>
                         <li>
                             <a><img src="img/ihealth.jpg"></a><a>è¡åè®¡</a></li>
                         <li>
                             <a><img src="img/ihealth.jpg"></a><a>åºå¤´ç¯</a></li>
                     </ul>
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/zhinengtaozhuang.jpg"></a><a>æºè½å®¶åº­å¥è£</a></li>
                         <li>
                             <a><img src="img/shouhuan.jpg"></a><a>å¤§ç±³æç¯</a></li>
                         <li>
                             <a><img src="img/smart.jpg"></a><a>å¨é¨æºè½ç¡¬ä»¶</a></li>
                     </ul>
                 </div>
             </li>
             <li>
                 <a>ç§»å¨çµæº&nbsp;æçº¿æ¿</a>
                 <a class="banner_menu_i">&gt;</a>
                 <div class="banner_menu_content" style="width: 300px; top: -146px;">
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/dianyuan.jpg "></a><a>å¤§ç±³ç§»å¨çµæº</a></li>
                         <li>
                             <a><img src="img/powerscript.jpg"></a><a>å¤§ç±³æçº¿æ¿</a></li>
                         <li>
                             <a><img src="img/yidongdianyuan.jpg"></a><a>åçç§»å¨çµæº</a></li>
                         <li>
                             <a><img src="img/dianyuanfujian.jpg"></a><a>ç§»å¨çµæºéä»¶</a></li>
                     </ul>
                 </div>
             </li>
             <li>
                 <a>è³æº&nbsp;é³ç®±</a>
                 <a class="banner_menu_i">&gt;</a>
                 <div class="banner_menu_content" style="width: 300px; top: -188px;">
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/headphone.jpg"></a><a>å¤§ç±³å¤´æ´å¼è³æº</a></li>
                         <li>
                             <a><img src="img/huosai.jpg"></a><a>å¤§ç±³æ´»å¡è³æº</a></li>
                         <li>
                             <a><img src="img/bluetoothheadset.jpg"></a><a>å¤§ç±³èçè³æº</a></li>
                         <li>
                             <a><img src="img/erji.jpg"></a><a>åçè³æº</a></li>
                         <li>
                             <a><img src="img/yinxiang.jpg"></a><a>é³ç®±</a></li>
                     </ul>
                 </div>
             </li>
             <li>
                 <a>çµæ± &nbsp;å­å¨å¡</a>
                 <a class="banner_menu_i">&gt;</a>
                 <div class="banner_menu_content" style="width: 300px; top: -230px;">
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/dianchi.jpg"></a><a>çµæ± </a></li>
                         <li>
                             <a><img src="img/chongdian.jpg"></a><a>åçµå¨</a></li>
                         <li>
                             <a><img src="img/xiancai.jpg"></a><a>çº¿æ</a></li>
                         <li>
                             <a><img src="img/cunchu.jpg"></a><a>å­å¨å¡</a></li>
                     </ul>
                 </div>
             </li>
             <li>
                 <a>ä¿æ¤å¥&nbsp;åç</a>
                 <a class="banner_menu_i">&gt;</a>
                 <div class="banner_menu_content" style="width: 300px; top: -272px;">
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/baohu.jpg"></a><a>ä¿æ¤å¥/ä¿æ¤å£³</a></li>
                         <li>
                             <a><img src="img/hougai.jpg"></a><a>åç</a></li>
                     </ul>
                 </div>
             </li>
             <li>
                 <a>è´´è&nbsp;å¶å®éä»¶</a>
                 <a class="banner_menu_i">&gt;</a>
                 <div class="banner_menu_content" style="width: 600px; top: -314px;">
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/tiemo.jpg"></a><a>è´´è</a></li>
                         <li>
                             <a><img src="img/zipaigan.jpg"></a><a>èªææ</a></li>
                         <li>
                             <a><img src="img/zipaigan.jpg"></a><a>èçææ</a></li>
                         <li>
                             <a><img src="img/tizhi.jpg"></a><a>è´´çº¸</a></li>
                         <li>
                             <a><img src="img/fangchensai.jpg"></a><a>é²å°å¡</a></li>
                         <li>
                             <a><img src="img/fangchensai.jpg"></a><a>ææºæ¯æ¶</a></li>
                     </ul>
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/raoxian.jpg"></a><a>è³æºç»çº¿å¨</a></li>
                         <li>
                             <a><img src="img/wifi.jpg"></a><a>éèº«WIFI</a></li>
                     </ul>
                 </div>
             </li>
             <li>
                 <a>ç±³å&nbsp;æè£</a>
                 <a class="banner_menu_i">&gt;</a>
                 <div class="banner_menu_content" style="width: 300px; top: -356px;">
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/mitu.jpg"></a><a>ç±³å</a></li>
                         <li>
                             <a><img src="img/fuzhuang.jpg"></a><a>æè£</a></li>
                     </ul>
                 </div>
             </li>
             <li>
                 <a>èå&nbsp;å¶å®å¨è¾¹</a>
                 <a class="banner_menu_i">&gt;</a>
                 <div class="banner_menu_content" style="width: 300px; top: -398px;">
                     <ul class="banner_menu_content_ul">
                         <li>
                             <a><img src="img/bag.jpg"></a><a>èå</a></li>
                         <li>
                             <a><img src="img/shubiaodian.jpg"></a><a>å¤§ç±³é¼ æ å </a></li>
                         <li>
                             <a><img src="img/zhoubian.jpg"></a><a>çæ´»å¨è¾¹</a></li>
                         <li>
                             <a><img src="img/wan.jpg"></a><a>ç©é·äº§å</a></li>
                     </ul>
                 </div>
             </li>
         </ul>
         <div id="big_banner_pic_wrap">
             <ul id="big_banner_pic">
                 <li><img src="img/T1hiDvBvVv1RXrhCrK.jpg"></li>
                 <li><img src="img/T1jrxjB_VT1RXrhCrK.jpg"></li>
                 <li><img src="img/T1oTJjBKKT1RXrhCrK.jpg"></li>
                 <li><img src="img/T1RICjB7DT1RXrhCrK.jpg"></li>
                 <li><img src="img/T1vWdTBKDv1RXrhCrK.jpg"></li>
             </ul>
         </div>
         <div id="big_banner_change_wrap">
             <div id="big_banner_change_prev"> &lt;</div>
             <div id="big_banner_change_next">&gt;</div>
         </div>
     </div>
     <div id="head_other_wrap">
         <div id="head_other">
             <ul>
                 <li>
                     <div id="div1">
                         <p>START</p>
                         <p>å¼æ¿è´­ä¹°</p>
                     </div>
                 </li>
                 <li>
                     <div id="div2">
                         <p>GROUND</p>
                         <p>ä¼ä¸å¢è´­</p>
                     </div>
                 </li>
                 <li>
                     <div id="div3">
                         <p>RETROFIT</p>
                         <p>å®æ¹äº§å</p>
                     </div>
                 </li>
                 <li>
                     <div id="div4">
                         <p>CHANNEL</p>
                         <p>Fç éé</p>
                     </div>
                 </li>
                 <li>
                     <div id="div5">
                         <p>RECHARGE</p>
                         <p>è¯è´¹åå¼</p>
                     </div>
                 </li>
                 <li>
                     <div id="div6">
                         <p>SECURITY</p>
                         <p>é²ä¼ªæ£æ¥</p>
                     </div>
                 </li>
             </ul>
         </div>
         <div class="head_other_ad"><img src="img/T184E_BQWT1RXrhCrK.jpg"></div>
         <div class="head_other_ad"><img src="img/T1yvd_BQDT1RXrhCrK.jpg"></div>
         <div class="head_other_ad"><img src="img/T1mahQBmKT1RXrhCrK.jpg"></div>
    </div>
     <div id="head_hot_goods_wrap">
         <div id="head_hot_goods_title">
             <span class="title_span">å¤§ç±³ææåå</span>
             <div id="head_hot_goods_change">
                 <span id="head_hot_goods_prave"><</span>
                 <span id="head_hot_goods_next">></span>
             </div>
         </div>
         <div id="head_hot_goods_content">
             <ul>
                 <li>
                     <a><img src="img/T1riKjB7VT1RXrhCrK.jpg"></a>
                     <a>å¤§ç±³çµè§2/2S å¨ç³»å</a>
                     <a>40/48/49/55è±å¯¸ ç°è´§è´­ä¹°</a>
                 </li>
                 <li>
                     <a><img src="img/T19AbjBCDT1RXrhCrK.jpg"></a>
                     <a>å¤§ç±³å¹³æ¿</a>
                     <a>å¨çé¦æ¬¾ NVIDIA Tegra K1 å¹³æ¿</a>
                 </li>
                 <li>
                     <a><img src="img/T1meZjBCAT1RXrhCrK.jpg"></a>
                     <a>å¤§ç±³çå­å¢å¼ºç 1G</a>
                     <a>é¦æ¬¾4Kè¶é«æ¸ç½ç»æºé¡¶ç</a>
                 </li>
                 <li>
                     <a><img src="img/T1tsEgB7DT1RXrhCrK.jpg"></a>
                     <a>å¨æ°å¤§ç±³è·¯ç±å¨</a>
                     <a>é¡¶éè·¯ç±å¨ï¼ä¼ä¸çº§æ§è½</a>
                 </li>
                 <li>
                     <a><img src="img/T10TJjB5hT1RXrhCrK.jpg"></a>
                     <a>å¤§ç±³å¤´æ´å¼è³æº</a>
                     <a>åª²ç¾ä¸»æµååçº§å¤´æ´è³æº</a>
                 </li>
                 <li>
                     <a><img src="img/T1hLhjB_AT1RXrhCrK.jpg"></a>
                     <a>å¤§ç±³æçº¿æ¿</a>
                     <a>3éå®å¨ä¿æ¤ï¼æçº¿æ¿ä¸­çèºæ¯å</a>
                 </li>
                 <li>
                     <a><img src="img/T1KDAjBCAT1RXrhCrK.jpg"></a>
                     <a>å¤§ç±³è·¯ç±å¨mini</a>
                     <a>ä¸»æµåé¢ACæºè½è·¯ç±å¨</a>
                 </li>
                 <li>
                     <a><img src="img/T16eEjBKhT1RXrhCrK.jpg"></a>
                     <a>å¤§ç±³ç§»å¨çµæº10000mAh</a>
                     <a>é«å¯åº¦è¿å£çµè¯ï¼ä»åçå¤§å°</a>
                 </li>
                 <li>
                     <a><img src="img/T1JnWjBCCT1RXrhCrK.jpg"></a>
                     <a>å¤§ç±³èçè³æº</a>
                     <a>2015å¾·å½IFå¤§å¥ï¼é«æ¸éè¯é³è´¨</a>
                 </li>
                 <li>
                     <a><img src="img/T1BsbjBvLT1RXrhCrK.jpg"></a>
                     <a>å¤§ç±³æ´»å¡è³æº</a>
                     <a>2015çº¢ç¹å¥ï¼ç¬å®¶é³è´¨ä¼åä¸å©</a>
                 </li>
             </ul>
         </div>
     </div>
     <div id="main_show_box">
         <div id="floor_1">
              <div id="floor_head">
                  <span class="title_span">æºè½ç¡¬ä»¶</span>
              </div>
         </div>
         <div class="floor_goods_wrap">
             <ul>
                 <li class="floor_goods_wrap_li">
                     <a><img src="img/T1IhLjBC_T1RXrhCrK.jpg"></a>
                 </li>
                 <li class="floor_goods_wrap_li">
                     <a class="floor_goods_img"><img src="img/T1odEjB5bT1RXrhCrK.jpg"></a>
                     <a class="floor_goods_tit">å¤§ç±³æºè½å®¶åº­å¥è£</a>
                     <a class="floor_goods_txt">3åéå¿«éå®è£ï¼30å¤ç§æºè½ç©æ³</a>
                     <a class="floor_goods_price">199å</a>
                 </li>
                 <li class="floor_goods_wrap_li">
                     <a class="floor_goods_img"><img src="img/T1JKxvBXhv1RXrhCrK.jpg"></a>
                     <a class="floor_goods_tit">å¤§ç±³éèº«WIFI 8GB Uçç</a>
                     <a class="floor_goods_txt">éèº«ä¸ç½ç¥å¨ï¼åç½®8GB Uç</a>
                     <a class="floor_goods_price">49.9å</a>
                 </li>
                 <li class="floor_goods_wrap_li">
                     <a class="floor_goods_img"><img src="img/T18yZQBCET1RXrhCrK.jpg"></a>
                     <a class="floor_goods_tit">å°èæºè½æåæº</a>
                     <a class="floor_goods_txt">è½çè½å¬è½è¯´ï¼ææºè¿ç¨è§ç</a>
                     <a class="floor_goods_price">149å</a>
                 </li>
                 <li class="floor_goods_wrap_li">
                     <a class="floor_goods_img"><img src="img/T1YoZQByYT1RXrhCrK.jpg"></a>
                     <a class="floor_goods_tit">å¤§ç±³ä½éç§°</a>
                     <a class="floor_goods_txt">é«ç²¾åº¦ååä¼ æå¨ ï½ ææºç®¡çå¨å®¶å¥åº·</a>
                     <a class="floor_goods_price">99å</a>
                 </li>
                 <li class="floor_goods_wrap_li">
                     <a class="floor_goods_img"><img src="img/T16nVjBCKT1RXrhCrK.jpg"></a>
                     <a class="floor_goods_tit">å°èè¿å¨æº</a>
                     <a class="floor_goods_txt">è¾¹ç©è¾¹å½è¾¹æï¼ææºéæ¶åäº«</a>
                     <a class="floor_goods_price">399å</a>
                 </li>
                 <li class="floor_goods_wrap_li">
                     <a class="floor_goods_img"><img src="img/T18zWQB4WT1RXrhCrK.jpg"></a>
                     <a class="floor_goods_tit">å¤§ç±³è·¯ç±å¨ mini</a>
                     <a class="floor_goods_txt">ä¸»æµåé¢ACæºè½è·¯ç±å¨ï¼æ§ä»·æ¯ä¹ç</a>
                     <a class="floor_goods_price">129å</a>
                 </li>
                 <li class="floor_goods_wrap_li">
                     <a class="floor_goods_img"><img src="img/T1oixjB5bT1RXrhCrK.jpg"></a>
                     <a class="floor_goods_tit">å¤§ç±³æºè½æåº§</a>
                     <a class="floor_goods_txt">ææºè¿ç¨é¥æ§å¼å³ï¼å¸¦USBæ¥å£</a>
                     <a class="floor_goods_price">59å</a>
                 </li>
                 <li class="floor_goods_wrap_li">
                     <a class="floor_goods_img"><img src="img/T1KzbQBvbT1RXrhCrK.jpg"></a>
                     <a class="floor_goods_tit">å¤§ç±³æ°´è´¨TDSæ£æµç¬</a>
                     <a class="floor_goods_txt">åç¡®æ£æµå®¶ä¸­æ°´è´¨çº¯åº¦</a>
                     <a class="floor_goods_price">39å</a>
                 </li>
                 <div style="clear:both;"></div>
             </ul>
         </div>
     </div>
     <div id="foot_box">
         <div id="foot_wrap">
             <div class="foot_top">
                 <ul>
                     <li>1å°æ¶å¿«ä¿®æå¡</li>
                     <li class="font_top_i">|</li>
                     <li>7å¤©æ çç±éè´§</li>
                     <li class="font_top_i">|</li>
                     <li>15å¤©åè´¹æ¢è´§</li>
                     <li class="font_top_i">|</li>
                     <li>æ»¡150ååé®</li>
                     <li class="font_top_i">|</li>
                     <li>520ä½å®¶å®åç½ç¹</li>
                 </ul>
             </div>
             <div class="foot_bottom">
                 <div class="foot_bottom_l">
                     <dl>
                         <dt>å¸®å©ä¸­å¿</dt>
                         <dd>è´­ç©æå</dd>
                         <dd>æ¯ä»æ¹å¼</dd>
                         <dd>ééæ¹å¼</dd>
                     </dl>
                     <dl>
                         <dt>æå¡æ¯æ</dt>
                         <dd>å®åæ¿ç­</dd>
                         <dd>èªå©æå¡</dd>
                         <dd>ç¸å³ä¸è½½</dd>
                     </dl>
                     <dl>
                         <dt>å¤§ç±³ä¹å®¶</dt>
                         <dd>å¤§ç±³ä¹å®¶</dd>
                         <dd>æå¡ç½ç¹</dd>
                         <dd>é¢çº¦äº²ä¸´å°åºæå¡</dd>
                     </dl>
                     <dl>
                         <dt>å³æ³¨æä»¬</dt>
                         <dd>æ°æµªå¾®å</dd>
                         <dd>å¤§ç±³é¨è½</dd>
                         <dd>å®æ¹å¾®ä¿¡</dd>
                     </dl>
                 </div>
                 <div class="foot_bottom_r">
                     <a>400-100-5678</a>
                     <a>å¨ä¸è³å¨æ¥ 8:00-18:00</a>
                     <a>ï¼ä»æ¶å¸è¯è´¹ï¼</a>
                     <span> 24å°æ¶å¨çº¿å®¢æ</span>
                 </div>
             </div>
         </div>
         <div class="foot_note_box">
             <div class="foot_note_wrap">
                 <div class="foot_note_con">
                     <span class="foot_logo"><img src="img/mi-logo.png" width="38px" height="38px"></span>
						<span class="foot_note_txt">
							<a>å¤§ç±³ç½</a><h>|</h><a>MIUI</a><h>|</h><a>ç±³è</a><h>|</h><a>å¤çä¹¦å</a><h>|</h><a>å¤§ç±³è·¯ç±å¨</a><h>|</h><a>å¤§ç±³åé¢</a><h>|</h><a>å¤§ç±³å¤©ç«åº</a><h>|</h><a>å¤§ç±³æ·å®ç´è¥åº</a><h>|</h><a>å¤§ç±³ç½ç</a><h>|</h><a>é®é¢åé¦</a><h>|</h><a>Select Region</a>
						    <a> 5555555å·</a>
						</span>
                 </div>

             </div>
         </div>
     </div>

<script type="text/javascript" src="js/xiaomi.js"></script>

</body>
</html>
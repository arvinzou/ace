<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <title>携手慈善，传递爱心</title>
    <link rel="stylesheet" type="text/css" href="css/style.css?v=<%=System.currentTimeMillis() %>" />
</head>
<body>
<div class="share_box" id="share_box">

</div>

<script id="share-tpl" type="text/template">
    <div class="container">
        <div class="head_img_box">
            <img src="\${data.headimgurl}"/>
        </div>
        <div class="dua_user_info">
            <p>\${data.nickname}</p>
            <p>感谢您的第 \${data.donateNum} 次捐赠，您已经：</p>
        </div>
        <div class="dua_statics">
                    <span class="dua_statics01">
                        <p>捐款天数</p>
                        <p><span class="dua_number">\${data.donateDays}</span>天</p>
                    </span>
                    <span class="dua_statics01">
                        <p>合计金额</p>
                        <p><span class="dua_number">\${data.donateTotal}</span>元</p>
                    </span>
        </div>
       <%-- <div class="totalAmount">
            <span class="totalAmount_title">合计金额(元)：</span>
            <span class="totalAmount_num">\${data.donateTotal}</span>
        </div>--%>
        <div class="donate" onclick="window.location.href='/cu/www/view/daydonation/daydonation.jsp'">
            <span>我&nbsp;要&nbsp;捐&nbsp;款</span>
        </div>
    </div>
    <div class="dua_footer">
        <div class="dua_left">
            <img src="img/weixin.png"/>
        </div>
        <div class="dua_right">
            <p>长按识别二维码</p>
            <p>点击右上方分享到朋友圈</p>
            <p>关注常德慈善,为慈善事业助力！</p>
        </div>
    </div>
</script>
<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="js/jweixin-1.4.0.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/juicer/juicer-min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="js/receipt.js?v=<%=System.currentTimeMillis() %>"></script>
</body>
</html>

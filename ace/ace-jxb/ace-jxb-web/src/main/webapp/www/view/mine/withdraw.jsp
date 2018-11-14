<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>提现</title>
    <link rel="stylesheet" type="text/css" href="css/withdraw.css"/>
    <jsp:include page="../../../dynamic/common/base.jsp"/>
    <script type="text/javascript" src="../../common/js/loader.js"></script>
    <script type="text/javascript" src="js/withdraw.js"></script>
</head>
<body>
<div class="money_card">
    <p class="title">收益提现</p>
    <div class="form">
        <div>￥</div>
        <div><input type="number"></div>
    </div>
    <p class="info stutas">可提现金额 0.00元</p>
    <p class="info red_text stutas1">提现金额需大于50元且不超过5000元</p>
    <p class="info red_text stutas2">输入金额超过可提现金额</p>
    <div class="clickBtn">确认提现</div>
</div>
<div class="rules">
    <div class="text">查看提现说明</div>
</div>
<div class="my_model">
    <div class="rules_text">
        <p>提现说明</p>
        <ul>
            <li>账户每月可提现1次，时间为每月1号至7号。</li>
            <li>提现金额高于50元低于5000元，且低于账户可提现金额。</li>
            <li>提现无手续费，提现过程中产生的费用由平台支付。</li>
            <li>为保障提现成功，微信账号必须实名认证，否则审核不予通过。如需实名认证，请前往<span>微信钱包—支付中心—支付管理—实名认证</span>，进行认证后进行提现。</li>
            <li>账户提交提现申请后，工作人员会在三天内进行审核。</li>
            <li>微信支付的结算周期为T+3, 提现申请审核通过后, 3个工作日内款项会自动转至您的微信钱包。</li>
            <li>如需其他帮助，请拨打 <a href="tel:0736-7083862">0736-7083862</a></li>
        </ul>
    </div>
</div>


<div class="rst_model">
    <div class="result_box">
        <p class="text1">提现申请提交成功~ </p>
        <p class="text2">工作人员会在三天内进行审核，请耐心等待~ </p>
        <p class="text3">查看提现记录</p>
        <div class="clickBtn">
            确认
        </div>
    </div>
</div>

</body>
</html>

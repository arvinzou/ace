<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>建议反馈</title>
    <jsp:include page="../../common/common-www.jsp"/>
</head>
<body ontouchstart>

<form onsubmit="return t_submit()">

    <div style="padding:12px">

        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input class="weui-input" id="title" type="text" placeholder="标题">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" placeholder="请输入建议" rows="8" id="docText"></textarea>
                    <div class="weui-textarea-counter"><span>0</span>/300</div>
                </div>
            </div>
            <div class="weui-cell weui-cell_vcode">
                <div class="weui-cell__hd"><label class="weui-label">验证码</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="number" id="j_captcha_weui" placeholder="请输入验证码">
                </div>
                <div class="weui-cell__ft">
                    <img class="weui-vcode-img" src="${pageContext.request.contextPath}/www/captcha/image.do">
                </div>
            </div>
        </div>
        <br>
        <div>
            <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">提交</a>
        </div>
</form>
</div>

<jsp:include page="../../common/footer-1-www.jsp"/>
<script src="${pageContext.request.contextPath}/content/www/js/feedback/controller.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title>作品</title>
<jsp:include page="../../common/common-www.jsp" />
<script>
var id='${param.id}';
</script>
</head>
<body ontouchstart style="padding:5px">
<a href="javascript:return false" class="weui-media-box weui-media-box_appmsg">
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title" id="name"></h4>
        <p class="weui-media-box__desc"><span id="dateOfPublication"></span>  作者：<span id="author"></span>  阅读：<span id="reading"><span></p>
    </div>
</a>
<div class="weui-panel weui-panel_access">
        <div class="weui-panel__bd">
          <div class="weui-media-box weui-media-box_text">
            <h4 class="weui-media-box__title">简介</h4>
            <p class="weui-media-box__desc" id="intro"></p>
          </div>
          <div class="weui-media-box weui-media-box_text">
              <h4 class="weui-media-box__title">正文</h4>
           </div>
        </div>
</div>
<div id="docText" style="padding:12px">
</div>
 <jsp:include page="../../common/footer-1-www.jsp" />
 <script src="${pageContext.request.contextPath}/content/common/lib/fastclick.js"></script>
 <script src="${pageContext.request.contextPath}/content/www/js/writing/preview.js"></script>
</body>
</html>
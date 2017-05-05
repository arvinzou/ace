<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title>作家</title>
<jsp:include page="../../common/common-www.jsp" />
<script>
var id='${param.id}';
</script>
</head>
<body ontouchstart style="padding:5px">
<div class="weui-flex">
      <div class="weui-flex__item"><div id="load" class="loading"></div></div>
      <div class="weui-flex__item">


<div class="weui-form-preview">
      <div class="weui-form-preview__hd">
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">姓名</label>
          <span class="weui-form-preview__value" id="name" style="font-weight:800;font-size:18px"></span>
        </div>
      </div>

      <div class="weui-form-preview__bd">
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">性别</label>
          <span class="weui-form-preview__value" id="sex"></span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">生日</label>
          <span class="weui-form-preview__value" id="birthday"></span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">类别</label>
          <span class="weui-form-preview__value" id="category"></span>
        </div>


      </div>




    </div>
      </div>
</div>

<a href="index-writing.jsp?id=${param.id}" class="weui-btn weui-btn_primary">作品列表</a>
<div class="weui-panel weui-panel_access">
    <div class="weui-panel__bd">
        <div class="weui-media-box weui-media-box_text">
            <h4 class="weui-media-box__title">简介</h4>
            <p class="weui-media-box__desc" ></p>
        </div>
    </div>
</div>
<div id="intro" style="padding:12px">
</div>

<div class="weui-panel weui-panel_access">
    <div class="weui-panel__bd">
        <div class="weui-media-box weui-media-box_text">
            <h4 class="weui-media-box__title">详情</h4>
            <p class="weui-media-box__desc" ></p>
        </div>
    </div>
</div>
<div id="descri" style="padding:12px">
</div>

 <jsp:include page="../../common/footer-1-www.jsp" />
 <script src="${pageContext.request.contextPath}/content/common/lib/fastclick.js"></script>
 <script src="${pageContext.request.contextPath}/content/www/js/writer/preview.js"></script>
</body>
</html>
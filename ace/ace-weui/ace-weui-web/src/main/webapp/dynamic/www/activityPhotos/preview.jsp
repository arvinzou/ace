<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title>活动剪影</title>
<jsp:include page="../../common/common-www.jsp" />
<script>
var id='${param.id}';
</script>
</head>
<body ontouchstart style="padding:5px">
<div class="weui-form-preview">
      <div class="weui-form-preview__hd">
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">活动名称</label>
          <em class="weui-form-preview__value" id="name"></em>
        </div>
      </div>

      <div class="weui-form-preview__bd">
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">活动时间</label>
          <span class="weui-form-preview__value" id="activityDate"></span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">活动地点</label>
          <span class="weui-form-preview__value" id="activityLocation"></span>
        </div>
        <div class="weui-form-preview__item">
                  <label class="weui-form-preview__label">阅读</label>
                  <span class="weui-form-preview__value" id="reading"></span>
        </div>
      </div>
 </div>



<div id="docText" style="padding:12px">
</div>
 <jsp:include page="../../common/footer-1-www.jsp" />
 <script src="${pageContext.request.contextPath}/content/common/lib/fastclick.js"></script>
 <script src="${pageContext.request.contextPath}/content/www/js/activityPhotos/preview.js"></script>
</body>
</html>
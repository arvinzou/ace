<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>统战人士</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
<script type="text/javascript">


</script>
<body>



<select id="languages" class="multiselect" style="width: 460px;	height: 350px;" multiple="multiple" name="languages">
</select>

<jsp:include page="../../common/footer-1.jsp"/>


<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>


<jsp:include page="../../common/footer-2.jsp"/>
<script>

        $(function(){
			$("#languages").multiselect({
				remoteUrl: "ajax.jsp"
			});
		});
    </script>

</body>
</html>
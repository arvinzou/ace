<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>课程文稿</title>
        <jsp:include page="../../../dynamic/common/base.jsp" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
        <script type="text/javascript" src="../../common/js/loader.js"></script>
        <script type="text/javascript" src="js/act.js"></script>
	</head>

	<body>
		<div class="container" id="sourceDetail">

		</div>

        <script id="sourceDetailTemp" type="text/template">
            <div class="row title">
                <div class="col-xs-12 col-md-12"><h3>\${data.name}</h3></div>
            </div>
            <div class="row content">
                <div class="col-xs-12 col-md-12" style="word-wrap:break-word;word-break:break-all;">
                    {@if data.introduction == undefined || data.introduction==null || data.introduction==''}
                        老师暂未上传文稿哦~
                    {@else}
                    \$\${data.introduction}
                    {@/if}
                </div>
            </div>
        </script>
	</body>

</html>
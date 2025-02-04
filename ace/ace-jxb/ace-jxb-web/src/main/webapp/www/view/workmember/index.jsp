<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>工作室成员</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="../../common/js/loader.js"></script>
		<script type="text/javascript" src="js/act.js"></script>
	</head>
	<body>
        <div class="main">

        </div>

		<script id="memberTemp" type="text/template">
            <div class="title"><p>成员（\${data.length}）</p></div>
            <div class="box">
                {@each data as item,index}
                <div class="memberList">
                    <div class="row">
                        <div class="col-xs-4 col-sm-4" style="padding-left: 0;">
                            <img class="head" src="\${item.imagePhotoUrl}" />
                        </div>
                        <div class="col-xs-8 col-sm-8" style="padding-left: 0;">
                            <p class="name">\${item.name}</p>
                            <p class="position">\${item.certification}</p>
                        </div>
                    </div>
                </div>
                {@/each}
            </div>
		</script>
	</body>
</html>

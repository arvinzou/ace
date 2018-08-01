<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>我的工作室</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="../../common/js/loader.js"></script>
		<script type="text/javascript" src="js/act.js"></script>
	</head>
	<body>
		<div class="box">
			<div class="row"><p class="title">我加入/创建的工作室</p></div>
			<div id="itemList">

			</div>
		</div>

		<script id="myroomTemp" type="text/template">
			{@each data.my as item,index}
            <div class="row roomlist" onclick="showDetail('\${item.id}');">
                <div class="room">
                <div class="col-xs-3 col-sm-3 topsize">
                <div class="img_box"><img src="\${item.logoImgUrl}"/></div>
                </div>
                <div class="col-xs-7 col-sm-7 topsize">
                <p class="workroom_title">\${item.name}</p>
                <p class="workroom_detail"><span class="edge">咨询师 20</span><span class="edge">课程 200</span><span class="edge">直播 10 </span></p>
            </div>
            <div class="col-xs-2 col-sm-2 invatation topsize">
                <p><img class="invate" src="img/invate.png"/></p>
                <p>邀请</p>
                </div>
                </div>
                </div>
			{@/each}
		</script>
		<script id="joinroomTemp" type="text/template">
			<div class="row roomlist" onclick="showDetail('\${joinData.id}');">
				<div class="room">
					<div class="col-xs-3 col-sm-3 topsize">
						<div class="img_box"><img src="\${joinData.logoImgUrl}"/></div>
					</div>
					<div class="col-xs-7 col-sm-7 topsize">
						<p class="workroom_title">\${joinData.name}</p>
						<p class="workroom_detail"><span class="edge">我加入的 </span></p>
					</div>
				</div>
			</div>
		</script>
	</body>
</html>

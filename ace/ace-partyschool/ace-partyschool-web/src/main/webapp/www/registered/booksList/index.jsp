<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>借阅记录</title>
		<jsp:include page="../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
	</head>
	<body>
		<div class="index">
			<!--未归还-->
			<div class="unreturn">
				<div class="uitem md">
					<div class="inner-middle">
						<div class="title">《王明中毒事件调查》</div>
						<div class="detail">
							<div class="icon-box"><img src="img/icon_time.png" class="icon-time" /></div>
							<div class="text-box"><span class="detail-title">请于</span></div>
							<div class="text-box"><span class="light-title">2020/12/18</span></div>
							<div class="text-box"><span class="detail-title">前还书</span></div>
						</div>
					</div>
				</div>
				<div class="uitem">
					<div class="inner-middle">
						<div class="title">《王明中毒事件调查》</div>
						<div class="detail">
							<div class="icon-box"><img src="img/icon_time.png" class="icon-time" /></div>
							<div class="text-box"><span class="detail-title">请于</span></div>
							<div class="text-box"><span class="light-title">2020/12/18</span></div>
							<div class="text-box"><span class="detail-title">前还书</span></div>
						</div>
					</div>
				</div>
			</div>
			<!--已归还-->
			<div class="return">
				<div class="item">
					<div class="inner-middle">
						<div class="title">《王明中毒事件调查》</div>
						<div class="detail">
							<div class="icon-box"><img src="img/icon_time.png" class="icon-time" /></div>
							<div class="text-box"><span class="detail-title">2018/12/18借阅</span></div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
	</body>
</html>

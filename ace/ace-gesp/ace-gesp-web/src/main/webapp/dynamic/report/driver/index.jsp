<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="description" content="overview &amp; stats" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>driver</title>

</head>
<jsp:include page="/dynamic/common/common.jsp" />
<style>
.charts-portal-ct1 {
	width: 500px;
	height: 300px
}

.charts-portal-ct2 {
	width: 500px;
	height: 300px
}
.charts-portal-ct3 {
	width: 500px;
	height: 300px
}
.page-content {
	background-color: #fff;
	position: relative;
	margin: 0;
	padding: 10px 20px 20px;
}
</style>


<body>
	<!-- /section:basics/sidebar -->
	<div class="page-content">
		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<div class="widget-box transparent" id="recent-box">
				<div class="widget-header">
					<h4 class="widget-title lighter smaller">
						<i class="ace-icon glyphicon glyphicon-th-large blue"></i>司机构成分析
					</h4>
					<div class="widget-toolbar no-border">
						<ul class="nav nav-tabs" id="recent-tab">
							<li class="active" style="height: 28px"><a data-toggle="tab"
								href="#tab1" style="padding-bottom: 0px; padding-top: 6px;">司机年龄分析</a></li>
							<li style="height: 28px"><a data-toggle="tab" href="#tab2"
								style="padding-bottom: 0px; padding-top: 6px;">司机驾驶年龄分析</a></li>
								
								<li style="height: 28px"><a data-toggle="tab" href="#tab3"
								style="padding-bottom: 0px; padding-top: 6px;">司机发证地分析</a></li>
						</ul>
					</div>
				</div>

				<div class="widget-body">
					<div class="widget-main padding-4">
						<div class="tab-content padding-8">
							<div id="tab1" class="tab-pane active">
								<div id="ct1" class="charts-portal-ct1"></div>
							</div>
							<div id="tab2" class="tab-pane">
								<div id="ct2" class="charts-portal-ct2"></div>
							</div>
<div id="tab3" class="tab-pane">
								<div id="ct3" class="charts-portal-ct3"></div>
							</div>
						</div>
					</div>
					<!-- /.widget-main -->
				</div>
				<!-- /.widget-body -->
			</div>
			<!-- /.widget-box -->
		</div>

	</div>
	<jsp:include page="/dynamic/common/footer-1.jsp" />


	<script
		src="${portalPath}/content/common/js/echarts-2.27/echarts.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/report/driver/config-1.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/report/driver/config-2.js?version=${cfg.version}"></script>
		<script
		src="${pageContext.request.contextPath}/content/report/driver/config-3.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/report/driver/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/report/driver/view.js?version=${cfg.version}"></script>

	

	<jsp:include page="/dynamic/common/footer-2.jsp" />

	<script type="text/javascript">
		parent.onresize = function() {
			autosize()
		}
		window.onresize = function() {
			autosize()
		}
		function autosize() {
			parent.autoWidth();
			var h = window.innerHeight;
			var w = window.innerWidth;

			var charh = 250;
			var charw = parseInt($(".page-content").width()) - 40;
			charh = parseInt(charw * 0.4);
			$('.charts-portal-ct1').css("height", charh);
			$('.charts-portal-ct1').css("width", charw);
			$('.charts-portal-ct2').css("height", charh);
			$('.charts-portal-ct2').css("width", charw);
			$('.charts-portal-ct3').css("height", charh);
			$('.charts-portal-ct3').css("width", charw);
			if (myChart1) {
				myChart1.resize();

			}

		}
	</script>
</body>
</html>
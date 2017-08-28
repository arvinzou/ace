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
<title>car</title>

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

.charts-portal-ct4 {
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
						<i class="ace-icon glyphicon glyphicon-th-large blue"></i>车辆构成分析
					</h4>
					<div class="widget-toolbar no-border">
						<ul class="nav nav-tabs" id="recent-tab">
							<li class="active" style="height: 28px"><a data-toggle="tab"
								href="#tab3" style="padding-bottom: 0px; padding-top: 6px;">车辆厂牌分析</a></li>
							<li style="height: 28px"><a data-toggle="tab" href="#tab4"
								style="padding-bottom: 0px; padding-top: 6px;">车辆吨位分析</a></li>
							<li style="height: 28px"><a data-toggle="tab" href="#tab5"
								style="padding-bottom: 0px; padding-top: 6px;">车辆违章分析</a></li>
							<li style="height: 28px"><a data-toggle="tab" href="#tab2"
								style="padding-bottom: 0px; padding-top: 6px;">车龄分析</a></li>
							<li class="" style="height: 28px"><a data-toggle="tab"
								href="#tab1" style="padding-bottom: 0px; padding-top: 6px;">车辆类型分析</a></li>
						</ul>
					</div>
				</div>

				<div class="widget-body">
					<div class="widget-main padding-4">
						<div class="tab-content padding-8">
							<div id="tab1" class="tab-pane">
								<div id="ct1" class="charts-portal-ct1"></div>
							</div>
							<div id="tab2" class="tab-pane">
								<div id="ct2" class="charts-portal-ct2"></div>
							</div>
							<div id="tab3" class="tab-pane active">
								<div id="ct3" class="charts-portal-ct3"></div>
							</div>
							<div id="tab4" class="tab-pane">
								<div id="ct4" class="col-xs-12 col-sm-5 charts-portal-ct4"></div>
								<div class="col-xs-12 col-sm-5 center table-portal-ct4" style="margin-top:50px;">
									<table id="tableCarTon" class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th class="center">核重</th>
												<th class="center">车辆数(辆)</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
							<div id="tab5" class="tab-pane">
								<div id="ct5" class="charts-portal-ct5"></div>
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
		src="${pageContext.request.contextPath}/content/report/car/config-1.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/report/car/config-2.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/report/car/config-3.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/report/car/config-4.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/report/car/config-5.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/report/car/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/report/car/view.js?version=${cfg.version}"></script>

	

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
			var charw = parseInt($(".page-content").width()) - 60;
			charh = parseInt(charw * 0.45);
			$('.charts-portal-ct1').css("height", charh);
			$('.charts-portal-ct1').css("width", charw);
			$('.charts-portal-ct2').css("height", charh);
			$('.charts-portal-ct2').css("width", charw);
			$('.charts-portal-ct3').css("height", charh);
			$('.charts-portal-ct3').css("width", charw);
			$('.charts-portal-ct4').css("height", charh);
			$('.tab4').css("width", charw);
			$('.charts-portal-ct4').css("width", 700);
			$('.charts-portal-ct5').css("height", charh);
			$('.charts-portal-ct5').css("width", charw);
			if (myChart1) {
				myChart1.resize();

			}

		}
	</script>
</body>
</html>
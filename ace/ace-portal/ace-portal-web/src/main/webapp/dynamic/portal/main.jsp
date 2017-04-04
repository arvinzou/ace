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
<title>企业资源管理-users</title>

</head>
<%!public boolean JudgeIsMoblie(HttpServletRequest request) {
		boolean isMoblie = false;
		String[] mobileAgents = { "iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
		"opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
		"nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
		"docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
		"techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
		"wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
		"pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
		"240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
		"blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
		"kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
		"mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
		"prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
		"smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
		"voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
		"Googlebot-Mobile" };
		if (request.getHeader("User-Agent") != null) {
		for (String mobileAgent : mobileAgents) {
		if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
		isMoblie = true;
		break;
		}
		}
		}
		return isMoblie;
		}%>
<%
	String cs = "widget-container-col";
	boolean isMobile = JudgeIsMoblie(request);
	if (isMobile) {
		cs = "";
	}
	request.setAttribute("cs", "cs");
%>
<jsp:include page="/dynamic/common/common.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/new.css?version=${cfg.version}" />
<style>
.layout-user {
	width: 170px;
	height: 35px;
	float: left;
	margin: 5px 5px 5px;
}

.infobox-text-north {
	font-size: 30px
}

.infobox-text-sourth {
	font-size: 14px
}

.infobox-small {
	width: 149px;
	height: 82px;
	text-align: left;
	padding-bottom: 5px;
}

.infobox-dark {
	margin: 1px 10px 0 0;
}

.infobox-portal {
	text-align: right;
	margin: 5px
}

.charts-portal-ct1 {
	width: 500px;
	height: 300px
}

.charts-portal-ct2 {
	width: 500px;
	height: 300px
}
</style>


<body>
	<!-- /section:basics/sidebar -->
	<div class="page-content">
		<!-- PAGE CONTENT BEGINS -->
		 <!-- Row starts -->
          <div class="row">
           <div class="col-lg-3 col-md-3 col-sm-6">
              <div class="mini-widget">
                <div class="mini-widget-heading clearfix">
                  <div class="pull-left">协会会员</div>
                  <div class="pull-right"> </div>
                </div>
                <div class="mini-widget-body clearfix">
                  <div class="pull-left">
                    <i class="fa fa-user-circle-o"></i>
                  </div>
                  <div class="pull-right number" id="memberCount">1135</div>
                </div>
                 <div class="mini-widget-footer center-align-text">
                  <span>总数／家</span>
                </div> 
              </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6">
              <div class="mini-widget mini-widget-green">
                <div class="mini-widget-heading clearfix">
                  <div class="pull-left">企业资源</div>
                  <div class="pull-right"> </div>
                </div>
                <div class="mini-widget-body clearfix">
                  <div class="pull-left">
                    <i class="fa fa-globe"></i>
                  </div>
                  <div class="pull-right number" id="deptCount">8757</div>
                </div>
               <div class="mini-widget-footer center-align-text">
                  <span>总数／家</span>
                </div> 
              </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6">
              <div class="mini-widget mini-widget-red">
                <div class="mini-widget-heading clearfix">
                  <div class="pull-left">司机资源</div>
                  <div class="pull-right"></div>
                </div>
                <div class="mini-widget-body clearfix">
                  <div class="pull-left">
                    <i class="fa fa-id-card"></i>
                  </div>
                  <div class="pull-right number" id="dirverCount">3780</div>
                </div>
                <div class="mini-widget-footer center-align-text">
                  <span>总数／人</span>
                </div> 
              </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6">
              <div class="mini-widget">
                <div class="mini-widget-heading clearfix">
                  <div class="pull-left">车辆资源</div>
                  <div class="pull-right"> </div>
                </div>
                <div class="mini-widget-body clearfix">
                  <div class="pull-left">
                    <i class="fa fa-car"></i>
                  </div>
                  <div class="pull-right number" id="carCount">12658</div>
                </div>
                <div class="mini-widget-footer center-align-text">
                  <span>总数／辆</span>
                </div> 
              </div>
            </div>
           
          </div>
          <!-- Row ends -->
		




		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<div class="col-xs-12 col-sm-6 widget-container-col">
				<!-- #section:custom/widget-box -->
				<div class="widget-box">
					<div class="widget-header">
						<h5 class="widget-title">会员架构分析</h5>

						<!-- #section:custom/widget-box.toolbar -->
						<div class="widget-toolbar">


							<a href="#" data-action="fullscreen" class="orange2"> <i
								class="ace-icon fa fa-expand"></i>
							</a> <a href="#" data-action="reload"> <i
								class="ace-icon fa fa-refresh"></i>
							</a> <a href="#" data-action="collapse"> <i
								class="ace-icon fa fa-chevron-up"></i>
							</a> <a href="#" data-action="close"> <i
								class="ace-icon fa fa-times"></i>
							</a>
						</div>

						<!-- /section:custom/widget-box.toolbar -->
					</div>

					<div class="widget-body">
						<div class="widget-main">
							<div id="ct1" class="charts-portal-ct1"></div>

						</div>
					</div>
				</div>

			</div>
			<!-- /.span -->
			<div class="col-xs-12 col-sm-6 widget-container-col">
				<!-- #section:custom/widget-box -->
				<div class="widget-box">
					<div class="widget-header">
						<h5 class="widget-title">待处理事项</h5>

						<!-- #section:custom/widget-box.toolbar -->
						<div class="widget-toolbar">


							<a href="#" data-action="fullscreen" class="orange2"> <i
								class="ace-icon fa fa-expand"></i>
							</a> <a href="#" data-action="reload"> <i
								class="ace-icon fa fa-refresh"></i>
							</a> <a href="#" data-action="collapse"> <i
								class="ace-icon fa fa-chevron-up"></i>
							</a> <a href="#" data-action="close"> <i
								class="ace-icon fa fa-times"></i>
							</a>
						</div>

						<!-- /section:custom/widget-box.toolbar -->
					</div>

					<div class="widget-body">
						<div class="widget-main">
							<div class="charts-portal-ct1">
								
								<table width="100%"
									class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">序号</th>
											<th>任务名称</th>
											<th>任务数</th>
											
										</tr>
									</thead>

									<tbody id="work-list-grid">

									</tbody>
								</table>
								
							</div>
						</div>
					</div>
				</div>

				<!-- /section:custom/widget-box -->
			</div>


		</div>
		<!-- /.row -->





		<div class="row">
			<div class="col-xs-12 col-sm-6 ${cs}">
				<!-- #section:custom/widget-box -->
				<div class="widget-box transparent" id="recent-box">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller">
							<i class="ace-icon glyphicon glyphicon-th-large green"></i>系统公告
						</h4>

						<div class="widget-toolbar no-border"></div>
					</div>

					<div class="widget-body">
						<div class="widget-main padding-4">
							<table width="100%">


								<tbody id="notice-list-grid">

								</tbody>
							</table>
						</div>
						<!-- /.widget-main -->
					</div>
					<!-- /.widget-body -->
				</div>
				<!-- /.widget-box -->

				<!-- /section:custom/widget-box -->
			</div>

			<div class="col-xs-12 col-sm-6 ${cs}">
				<!-- #section:custom/widget-box -->
				<div class="widget-box transparent" id="recent-box">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller">
							<i class="ace-icon glyphicon glyphicon-th-large green"></i>协会公告
						</h4>

						<div class="widget-toolbar no-border"></div>
					</div>

					<div class="widget-body">
						<div class="widget-main padding-4">
							<table width="100%">


								<tbody id="notice-list-grid-area">

								</tbody>
							</table>
						</div>
						<!-- /.widget-main -->
					</div>
					<!-- /.widget-body -->
				</div>
				<!-- /.widget-box -->

				<!-- /section:custom/widget-box -->
			</div>
			<!-- /.span -->
		</div>
		<!-- /.row -->







	</div>
	<!-- /.page-content -->




	<jsp:include page="/dynamic/common/footer-1.jsp" />

	<script
		src="${pageContext.request.contextPath}/content/common/js/echarts-2.27/echarts.js?version=${cfg.version}"></script>

	<script
		src="${pageContext.request.contextPath}/content/portal/js/main/config-1.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/main/config-2.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/main/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/main/view.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/common/js/jquery.stamper.js?version=${cfg.version}"></script>
		
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

			var ww = parseInt($(".page-content").width() / 4) - 13;
			var hh = parseInt(ww * 0.45);
			$('.infobox-small').css("height", hh);
			$('.infobox-small').css("width", ww);
			if (hh < 80) {
				$('.infobox-text-north').css("font-size", 55);
			} else {
				$('.infobox-text-north').css("font-size", 55);
			}
			var charh = 250;
			var charw = parseInt($(".page-content").width() / 2) - 40;
			charh = parseInt(charw * 0.5);
			$('.charts-portal-ct1').css("height", charh);
			$('.charts-portal-ct1').css("width", charw);
			$('.charts-portal-ct2').css("height", charh);
			$('.charts-portal-ct2').css("width", charw);
			if (myChart1) {
				myChart1.resize();

			}

		}
	</script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/main/main.js?version=${cfg.version}"></script>
	<style>
.page-content {
	background-color: #fff;
	position: relative;
	margin: 0;
	padding: 10px 20px 20px;
}
		.stamper{padding-top:10px;height:100px;}
	.stamper span{float:right;display:inline-block;height:100%;width:200px;}
	</style>
</body>
</html>
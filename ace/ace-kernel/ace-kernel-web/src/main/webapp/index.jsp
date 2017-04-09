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
<title>走向公众号管理</title>

</head>
<jsp:include page="/dynamic/common/common.jsp" />
<link rel="stylesheet" href="${portalPath}/content/common/css/new.css?version=${cfg.version}" />
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
                  <div class="pull-left">作家</div>
                  <div class="pull-right"> </div>
                </div>
                <div class="mini-widget-body clearfix">
                  <div class="pull-left">
                    <i class="fa fa-user-circle-o"></i>
                  </div>
                  <div class="pull-right number" id="memberCount">1135</div>
                </div>
                 <div class="mini-widget-footer center-align-text">
                  <span>总数／人</span>
                </div> 
              </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6">
              <div class="mini-widget mini-widget-green">
                <div class="mini-widget-heading clearfix">
                  <div class="pull-left">作品</div>
                  <div class="pull-right"> </div>
                </div>
                <div class="mini-widget-body clearfix">
                  <div class="pull-left">
                    <i class="fa fa-globe"></i>
                  </div>
                  <div class="pull-right number" id="deptCount">8757</div>
                </div>
               <div class="mini-widget-footer center-align-text">
                  <span>总数／个</span>
                </div> 
              </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6">
              <div class="mini-widget mini-widget-red">
                <div class="mini-widget-heading clearfix">
                  <div class="pull-left">活动剪影</div>
                  <div class="pull-right"></div>
                </div>
                <div class="mini-widget-body clearfix">
                  <div class="pull-left">
                    <i class="fa fa-id-card"></i>
                  </div>
                  <div class="pull-right number" id="dirverCount">3780</div>
                </div>
                <div class="mini-widget-footer center-align-text">
                  <span>总数／次</span>
                </div> 
              </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6">
              <div class="mini-widget">
                <div class="mini-widget-heading clearfix">
                  <div class="pull-left">意见反馈</div>
                  <div class="pull-right"> </div>
                </div>
                <div class="mini-widget-body clearfix">
                  <div class="pull-left">
                    <i class="fa fa-car"></i>
                  </div>
                  <div class="pull-right number" id="carCount">12658</div>
                </div>
                <div class="mini-widget-footer center-align-text">
                  <span>总数／次</span>
                </div> 
              </div>
            </div>
           
          </div>
          <!-- Row ends -->
		







		<div class="row">
			<div class="col-xs-12 col-sm-6 widget-container-col">
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

			<div class="col-xs-12 col-sm-6 widget-container-col">

			</div>
			<!-- /.span -->
		</div>
		<!-- /.row -->







	</div>
	<!-- /.page-content -->




	<jsp:include page="/dynamic/common/footer-1.jsp" />

	<script
		src="${portalPath}/content/common/js/echarts-2.27/echarts.js?version=${cfg.version}"></script>

	<script
		src="${pageContext.request.contextPath}/content/index/config-1.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/index/config-2.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/index/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/index/view.js?version=${cfg.version}"></script>
<script
		src="${portalPath}/content/common/js/jquery.stamper.js?version=${cfg.version}"></script>
		
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
		src="${pageContext.request.contextPath}/content/index/index.js?version=${cfg.version}"></script>
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
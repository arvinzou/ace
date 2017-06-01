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
           <div class="col-lg-3 col-md-4 col-sm-6">
              <div class="mini-widget">
                <div class="mini-widget-heading clearfix">
                  <div class="pull-left">作家</div>
                  <div class="pull-right"> </div>
                </div>
                <div class="mini-widget-body clearfix">
                  <div class="pull-left">
                    <i class="fa fa-user-circle-o"></i>
                  </div>
                  <div class="pull-right number" id="writer">1135</div>
                </div>
                 <div class="mini-widget-footer center-align-text">
                  <span>总数／人</span>
                </div> 
              </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
              <div class="mini-widget mini-widget-green">
                <div class="mini-widget-heading clearfix">
                  <div class="pull-left">作品</div>
                  <div class="pull-right"> </div>
                </div>
                <div class="mini-widget-body clearfix">
                  <div class="pull-left">
                    <i class="fa fa-book"></i>
                  </div>
                  <div class="pull-right number" id="writing">8757</div>
                </div>
               <div class="mini-widget-footer center-align-text">
                  <span>总数／个</span>
                </div> 
              </div>
            </div>

            <div class="col-lg-3 col-md-4 col-sm-6">
              <div class="mini-widget">
                <div class="mini-widget-heading clearfix">
                  <div class="pull-left">意见反馈</div>
                  <div class="pull-right"> </div>
                </div>
                <div class="mini-widget-body clearfix">
                  <div class="pull-left">
                    <i class="fa fa-comment-o"></i>
                  </div>
                  <div class="pull-right number" id="feedback">12658</div>
                </div>
                <div class="mini-widget-footer center-align-text">
                  <span>总数／次</span>
                </div> 
              </div>
            </div>
           
          </div>
          <!-- Row ends -->
		















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
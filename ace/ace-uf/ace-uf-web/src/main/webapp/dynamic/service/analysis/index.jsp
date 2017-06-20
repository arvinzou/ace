<%@page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>统战综合管理平台</title>

</head>
<jsp:include page="/dynamic/common/common.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/css/new.css?version=${cfg.version}"/>
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
	height: 200px
}

.charts-portal-ct2 {
	width: 500px;
	height: 200px
}
.charts-portal-ct3 {
	width: 500px;
	height: 200px
}

.charts-portal-ct4 {
	width: 500px;
	height: 200px
}

</style>


<body>
<!-- /section:basics/sidebar -->
<div class="page-content">


    <div class="row">
        <div class="col-xs-12 col-sm-6 widget-container-col">
            <!-- #section:custom/widget-box -->
            <div class="widget-box">
                <div class="widget-header">
                    <h5 class="widget-title">统战人士类型</h5>

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
                    <h5 class="widget-title">统战人士分布</h5>

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
                        <div id="ct2" class="charts-portal-ct2"></div>
                    </div>
                </div>
            </div>

            <!-- /section:custom/widget-box -->
        </div>


    </div>
    <!-- /.row -->


    <div class="row">
        <div class="col-xs-12 col-sm-6 widget-container-col">
            <!-- #section:custom/widget-box -->
            <div class="widget-box">
                <div class="widget-header">
                    <h5 class="widget-title">统战人士年龄构成</h5>

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
                        <div id="ct3" class="charts-portal-ct3"></div>

                    </div>
                </div>
            </div>

        </div>
        <!-- /.span -->
        <div class="col-xs-12 col-sm-6 widget-container-col">
            <!-- #section:custom/widget-box -->
            <div class="widget-box">
                <div class="widget-header">
                    <h5 class="widget-title">统战人士所属机构</h5>

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
                        <div id="ct4" class="charts-portal-ct4"></div>
                    </div>
                </div>
            </div>

            <!-- /section:custom/widget-box -->
        </div>


    </div>
    <!-- /.row -->

</div>
<!-- /.page-content -->


<jsp:include page="/dynamic/common/footer-1.jsp"/>

<script
        src="${portalPath}/content/common/js/echarts-2.27/echarts.js?version=${cfg.version}"></script>

<script
        src="${pageContext.request.contextPath}/content/service/analysis/config-1.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/analysis/config-2.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/analysis/config-3.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/analysis/config-4.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/analysis/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/analysis/view.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/js/jquery.stamper.js?version=${cfg.version}"></script>

<jsp:include page="/dynamic/common/footer-2.jsp"/>

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
        var charh=200;
		var charw=parseInt($(".page-content").width()/2)-40;
		charh=parseInt(charw*0.4);
		$('.charts-portal-ct1').css("height", charh);
		$('.charts-portal-ct1').css("width", charw);

		$('.charts-portal-ct2').css("height", charh);
		$('.charts-portal-ct2').css("width", charw);

		$('.charts-portal-ct3').css("height", charh);
		$('.charts-portal-ct3').css("width", charw);


		$('.charts-portal-ct4').css("height", charh);
		$('.charts-portal-ct4').css("width", charw);
		if(myChart1){
			myChart1.resize();
			myChart2.resize();
		}
		}

</script>
<script
        src="${pageContext.request.contextPath}/content/service/analysis/index.js?version=${cfg.version}"></script>
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
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
<style>

.charts{
	width: 485px;
	height: 350px
}
</style>


<body>
<!-- /section:basics/sidebar -->
<div class="page-content">


    <div class="row">
        <div class="col-xs-12 col-sm-6 widget-container-col">
            <div class="widget-box">
                <div class="widget-header">
                    <h5 class="widget-title">图表</h5>

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
                        <div id="ct" class="charts"></div>


                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6 widget-container-col">
            <div class="widget-box">
                <div class="widget-header">
                    <h5 class="widget-title">表格</h5>

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
                        <div class="charts">
                            <table id="table"
                                   class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="center" style="width:80px;">序号</th>
                                    <th>年龄段</th>
                                    <th>人数</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>

        </div>


    </div>
    <!-- /.row -->
    <div class="row" style="height:200px; overflow:scroll;">

    </div>

</div>
<!-- /.page-content -->


<jsp:include page="/dynamic/common/footer-1.jsp"/>

<script
        src="${portalPath}/content/common/js/echarts-2.27/echarts.js?version=${cfg.version}"></script>

<script
        src="${pageContext.request.contextPath}/content/service/analysis/personageAge/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/analysis/personageAge/controller.js?version=${cfg.version}"></script>
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
<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>智慧治超管理平台</title>

</head>

<jsp:include page="/dynamic/common/common.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/css/new.css?version=${cfg.version}"/>
<link href="${portalPath}/content/common/assets/global/css/components.min.css" rel="stylesheet"
      id="style_components" type="text/css"/>
<style>
    .charts-portal-ct1 {
        width: 100%;
        height: 300px
    }

    .charts-portal-ct2 {
        width: 100%;
        height: 300px
    }
</style>

<body>
<div style="height:10px;" class="background-white"></div>
<div class="page-content background">
    <!-- PAGE CONTENT BEGINS -->
    <!-- Row starts -->
    <div class="row">
        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="dashboard-stat2 ">
                <div class="display">
                    <div class="number">
                        <h3 class="font-red-haze">
                            <span data-counter="counterup" data-value="0" id="traffic-count">0</span>
                            <small class="font-red-haze">次</small>
                        </h3>
                        <small>今日通行记录数</small>
                    </div>
                    <div class="icon">
                        <i class="fa fa-bank"></i>
                    </div>
                </div>
                <div class="progress-info">
                    <div class="progress">
                        <span style="width: 100%;"
                              class="progress-bar progress-bar-success red-haze">
                            <span class="sr-only"></span>
                        </span>
                    </div>
                    <div class="status">
                        <div class="status-title"></div>
                        <div class="status-number"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="dashboard-stat2 ">
                <div class="display">
                    <div class="number">
                        <h3 class="font-green-sharp">
                            <span data-counter="counterup" data-value="" id="illegal-traffic-count">0</span>
                            <small class="font-green-sharp">次</small>
                        </h3>
                        <small>今日违章记录数</small>
                    </div>
                    <div class="icon">
                        <i class="fa fa-user-circle-o"></i>
                    </div>
                </div>
                <div class="progress-info">
                    <div class="progress">
                        <span style="width: 100%;"
                              class="progress-bar progress-bar-success green-sharp">
                            <span class="sr-only"></span>
                        </span>
                    </div>
                    <div class="status">
                        <div class="status-title"></div>
                        <div class="status-number"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="dashboard-stat2 ">
                <div class="display">
                    <div class="number">
                        <h3 class="font-blue-sharp">
                            <span data-counter="counterup" data-value="0" id="pt-traffic-count">0</span>
                            <small class="font-blue-sharp">次</small>
                        </h3>
                        <small>待审通行记录数</small>
                    </div>
                    <div class="icon">
                        <i class="fa fa-comment-o"></i>
                    </div>
                </div>
                <div class="progress-info">
                    <div class="progress">
                        <span style="width: 100%;"
                              class="progress-bar progress-bar-success blue-sharp">
                            <span class="sr-only"></span>
                        </span>
                    </div>
                    <div class="status">
                        <div class="status-title"></div>
                        <div class="status-number"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="dashboard-stat2 ">
                <div class="display">
                    <div class="number">
                        <h3 class="font-purple-soft">
                            <span data-counter="counterup" data-value="0" id="pt-cases-count">0</span>
                            <small class="font-purple-sharp">件</small>
                        </h3>
                        <small>
                            <a class="nav-link" title="待审案件数" url="/woc/dynamic/service/cases/index.jsp">待审案件数</a>
                        </small>
                    </div>
                    <div class="icon">
                        <i class="fa fa-flag"></i>
                    </div>
                </div>
                <div class="progress-info">
                    <div class="progress">
                        <span style="width: 100%;"
                              class="progress-bar progress-bar-success purple-soft">
                            <span class="sr-only"></span>
                        </span>
                    </div>
                    <div class="status">
                        <div class="status-title"></div>
                        <div class="status-number"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="page-content-2 background">
    <div class="row">
        <div class="col-xs-12 col-sm-6">
            <!-- #section:custom/widget-box -->
            <%--今日折线图--%>
            <div class="widget-box transparent background-white padding1" id="day-box">
                <div class="widget-header">
                    <h4 class="widget-title lighter smaller">
                        <i class="ace-icon glyphicon glyphicon-th-large green"></i>今日曲线
                    </h4>
                    <div class="widget-toolbar no-border"></div>
                </div>

                <div class="widget-body" style="min-height:250px">
                    <div class="widget-main">
                        <div id="ct1" class="charts-portal-ct1"></div>
                    </div>
                    <!-- /.widget-main -->
                </div>
                <!-- /.widget-body -->
            </div>
            <!-- /.widget-box -->
            <!-- /section:custom/widget-box -->
        </div>
        <%--当月折线图--%>
        <div class="col-xs-12 col-sm-6">
            <!-- #section:custom/widget-box -->
            <div class="widget-box transparent background-white padding1" id="month-box">
                <div class="widget-header">
                    <h4 class="widget-title lighter smaller">
                        <i class="ace-icon glyphicon glyphicon-th-large green"></i>当月曲线
                    </h4>
                    <div class="widget-toolbar no-border"></div>
                </div>
                <div class="widget-body" style="min-height:250px">
                    <div class="widget-main">
                        <div id="ct2" class="charts-portal-ct2"></div>
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
</div>

<div class="hide page-content background">
    <div class="row">
        <div class="col-xs-12 col-sm-5">
            <div class="widget-box transparent background-white padding1">
                <ul class="liveTraffic">
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                    <li>
                        我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容 我是通行记录内容我是通行记录内容
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-xs-12 col-sm-5">
            <div class="widget-box transparent background-white padding1">
                <div id="ct3" style="height:300px"></div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-2">
            <div class="widget-box transparent background-white padding1">
                <div id="ct4" style="height:300px"></div>
            </div>
        </div>
    </div>
</div>
<!-- /.page-content -->


<jsp:include page="/dynamic/common/footer-1.jsp"/>

<script src="${portalPath}/content/common/js/echarts-2.27/echarts.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/index/config-1.js?version=${cfg.version}"></script>
<%--<script src="${pageContext.request.contextPath}/content/index/config-2.js?version=${cfg.version}"></script>--%>
<script src="${pageContext.request.contextPath}/content/index/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/index/view.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jquery.stamper.js?version=${cfg.version}"></script>

<jsp:include page="/dynamic/common/footer-2.jsp"/>

<script type="text/javascript">
    window.onresize = function () {
        console.log("-------------123-------------");
        parent.autoWidth();

        //        console.log("-------------");
        //        console.log($(".widget-main").width());
    }

</script>
<script src="${pageContext.request.contextPath}/content/index/index.js?version=${cfg.version}"></script>
<style>
    .padding1 {
        padding: 15px 15px 15px 15px;
    }

    .page-content {
        background-color: #fff;
        position: relative;
        margin: 0;
        padding: 10px 10px 0px 10px;
    }

    .page-content-2 {
        background-color: #fff;
        position: relative;
        margin: 0;
        padding: 0px 10px 10px 10px;
    }

    .background {
        background-color: #BFCAD1;
    }

    .background-white {
        background-color: #fff;
    }

    .stamper {
        padding-top: 10px;
        height: 100px;
    }

    .stamper span {
        float: right;
        display: inline-block;
        height: 100%;
        width: 200px;
    }

    .liveTraffic {
        height: 300px;
        overflow-y: auto;
    }

    .liveTraffic > li {
        height: 30px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        line-height: 40px;
    }

</style>
</body>
</html>
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
    <title>走向公众号管理</title>

</head>
<jsp:include page="/dynamic/common/common.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/css/new.css?version=${cfg.version}"/>
<link href="${portalPath}/content/common/assets/global/css/components.min.css" rel="stylesheet"
      id="style_components" type="text/css"/>
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

<div style="height:10px;" class="background-white"></div>
<div class="page-content background">

    <!-- PAGE CONTENT BEGINS -->
    <!-- Row starts -->
    <div class="row">
        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="dashboard-stat2 ">
                <div class="display">
                    <div class="number">
                        <h3 class="font-green-sharp">
                            <span data-counter="counterup" data-value="0" id="writer">0</span>
                            <small class="font-green-sharp">人</small>
                        </h3>
                        <small>作家</small>
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
                        <h3 class="font-red-haze">
                            <span data-counter="counterup" data-value="0" id="writing">0</span>
                        </h3>
                        <small>作品</small>
                    </div>
                    <div class="icon">
                        <i class="fa fa-book"></i>
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
                        <h3 class="font-blue-sharp">
                            <span data-counter="counterup" data-value="0" id="feedback">0</span>
                        </h3>
                        <small>意见反馈</small>
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
                            <span data-counter="counterup" data-value="0" id="user">0</span>
                        </h3>
                        <small>系统用户</small>
                    </div>
                    <div class="icon">
                        <i class="fa fa-user-circle-o"></i>
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
            <div class="widget-box transparent background-white padding1" id="recent-box">
                <div class="widget-header">
                    <h4 class="widget-title lighter smaller">
                        <i class="ace-icon glyphicon glyphicon-th-large green"></i>系统公告
                    </h4>

                    <div class="widget-toolbar no-border">

                    </div>
                </div>

                <div class="widget-body" style="min-height:250px">
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

        <div class="col-xs-12 col-sm-6">
            <!-- #section:custom/widget-box -->
            <div class="widget-box transparent background-white padding1" id="recent-box">
                <div class="widget-header">
                    <h4 class="widget-title lighter smaller">
                        <i class="ace-icon glyphicon glyphicon-th-large green"></i>业务公告
                    </h4>

                    <div class="widget-toolbar no-border">

                    </div>
                </div>

                <div class="widget-body" style="min-height:250px">
                    <div class="widget-main padding-4">
                        <table width="100%;">


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
</div>
<!-- /.page-content -->


<jsp:include page="/dynamic/common/footer-1.jsp"/>

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

<jsp:include page="/dynamic/common/footer-2.jsp"/>

<script type="text/javascript">
    parent.onresize = function () {
        autosize()
    }
    window.onresize = function () {
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
</style>


</body>
</html>
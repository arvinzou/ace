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
    <title>心阳光联盟</title>

</head>


<jsp:include page="/dynamic/common/base.jsp" />

<link rel="stylesheet" href="${portalPath}/content/common/assets/css/font-awesome.min.css">
<link rel="stylesheet" href="${portalPath}/content/common/assets/global/css/components.min.css">
<link rel="stylesheet" href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css">
<script src="${pageContext.request.contextPath}/content/common/js/loader.js?v=${cfg.version}"></script>
<link rel="stylesheet" href="${portalPath}/content/common/assets/global/css/components.min.css">
<link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css">






<body>
<div class="page-wrapper">

    <div class="page-wrapper-row full-height">
        <div class="page-wrapper-middle">
            <div class="page-container">
                <div class="page-content-wrapper">
                    <div class="page-content">
                        <div class="container">
                            <ul class="page-breadcrumb breadcrumb">
                                <li>
                                    <a href="${pageContext.request.contextPath}/index.jsp">首页</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <li>
                                    <span>仪表盘</span>
                                </li>
                            </ul>
                            <div class="page-content-inner">
                                <!---==============================================-->



                            <!--=======================================-->

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="bottom"></div>
</div>
<script
        src="${pageContext.request.contextPath}/content/index/act.js?version=${cfg.version}"></script>
<script id="tpl-portal" type="text/template">

        <!-- PAGE CONTENT BEGINS -->
    <!-- Row starts -->
    <div class="row widget-row">
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">咨询师</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green icon-user"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.counselor}">\${data.counselor}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">工作室</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-red  icon-screen-desktop"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.studio}">\${data.studio}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">课程</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple icon-layers"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.course}">\${data.course}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">心里评测</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-blue  icon-graduation"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.evaluat_tpl}">\${data.evaluat_tpl}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
    </div>

    <div class="row widget-row">
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">评论</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green icon-note"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.courseCmt}">\${data.courseCmt}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">咨询订单</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-red  icon-basket-loaded"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.counselorOrder}">\${data.counselorOrder}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">课程订单</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple icon-basket-loaded"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.courseOrder}">\${data.courseOrder}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">评测订单</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-blue icon-basket-loaded"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.evaluatOrder}">\${data.evaluatOrder}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
    </div>


</div>
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<!--[if IE 8]>
<html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]>
<html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="../../common/base.jsp"/>
    <link rel="stylesheet" href="${portalPath}/content/common/assets/pages/css/profile.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/css/components.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="${pageContext.request.contextPath}/content/common/js/echartloader.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/dynamic/service/statics/js/act.js"></script>
</head>

<body>
<div class="page-wrapper">
    <div class="page-wrapper-row full-height">
        <div class="page-wrapper-middle">
            <div class="page-container">
                <div class="page-content-wrapper">
                    <div class="page-content">
                        <div class="container">
                            <div class="page-content-inner">

                                <!---==============================================-->
                                <div class="row static_title"><h3 class="title">心阳光联盟运营看板</h3></div>
                                <div class="row" id="operationData">

                                </div>
                                <!--统计图表-->
                                <div class="row">
                                    <div class="portlet light ">
                                        <div class="portlet-title tabbable-line">
                                            <ul class="nav nav-tabs">
                                                <li class="active" onclick="weekRevenue();">
                                                    <a href="#tab_1_1" class="active" data-toggle="tab" aria-expanded="true">本周营收 </a>
                                                </li>
                                                <li onclick="monthRevenue();">
                                                    <a href="#tab_1_1" class="active" data-toggle="tab" aria-expanded="true">年度营收 </a>
                                                </li>
                                                <li class="" onclick="registStatics();">
                                                    <a href="#tab_1_1" data-toggle="tab" aria-expanded="false"> 注册用户 </a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="portlet-body">
                                            <!--BEGIN TABS-->
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="tab_1_1">
                                                   <%-- <div class="revenue_change">
                                                        <div class="btn-group btn-group-devided" data-toggle="buttons">
                                                            <label class="btn btn-transparent blue-oleo btn-no-border btn-outline btn-circle btn-sm active" onclick="weekRevenue();">
                                                                <input type="radio" name="options" class="toggle" id="option1">周</label>
                                                            <label class="btn btn-transparent blue-oleo btn-no-border btn-outline btn-circle btn-sm" onclick="monthRevenue();">
                                                                <input type="radio" name="options" class="toggle" id="option2">月</label>
                                                        </div>
                                                    </div>--%>
                                                    <div style="width: 100%;height: 500px;">
                                                        <div id="weekRevenue" style="width: 100%;height: 100%;"></div>
                                                        <div id="monthRevenue" style="width: 100%;height: 100%;"></div>
                                                        <div id="regist" style="width: 100%;height: 100%;"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--END TABS-->
                                        </div>
                                    </div>
                                </div>

                                <!--=======================================-->

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/template" id="operationDataTemp">
    <div class="row widget-row">
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">注册用户</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green icon-user"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.regUserCount}">\${data.regUserCount}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">付费用户</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-red icon-basket-loaded"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.paidUserCount}">\${data.paidUserCount}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">注册用户</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple icon-user-follow"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">今日新增</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.todayRegUserCount}">\${data.todayRegUserCount}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">咨询师</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-blue  icon-graduation"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">已入驻</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.regTeacherCount}">\${data.regTeacherCount}</span>
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
                <h4 class="widget-thumb-heading">咨询师</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green icon-note"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">今日新增</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.todayRegTeacherCount}">\${data.todayRegTeacherCount}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">年度营收</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-red icon-layers"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.yearTurnover}">\${data.yearTurnover}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">今日营收</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple icon-screen-desktop"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.todayTurnover}">\${data.todayTurnover}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">本月营收</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-blue icon-bar-chart"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">总收益</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.monthTurnover}">\${data.monthTurnover}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
    </div>
</script>
</body>
</html>
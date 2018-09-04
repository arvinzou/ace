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
                                <div class="row widget-row">
                                    <div class="col-md-3">
                                        <!-- BEGIN WIDGET THUMB -->
                                        <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                                            <h4 class="widget-thumb-heading">注册用户</h4>
                                            <div class="widget-thumb-wrap">
                                                <i class="widget-thumb-icon bg-green icon-bulb"></i>
                                                <div class="widget-thumb-body">
                                                    <span class="widget-thumb-subtitle">用户/咨询师</span>
                                                    <span class="widget-thumb-body-stat" data-counter="counterup" data-value="7,644">7,644</span>
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
                                                <i class="widget-thumb-icon bg-red icon-layers"></i>
                                                <div class="widget-thumb-body">
                                                    <span class="widget-thumb-subtitle">累计</span>
                                                    <span class="widget-thumb-body-stat" data-counter="counterup" data-value="1,293">1,293</span>
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
                                                <i class="widget-thumb-icon bg-purple icon-screen-desktop"></i>
                                                <div class="widget-thumb-body">
                                                    <span class="widget-thumb-subtitle">已入驻</span>
                                                    <span class="widget-thumb-body-stat" data-counter="counterup" data-value="815">815</span>
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
                                                <i class="widget-thumb-icon bg-blue icon-bar-chart"></i>
                                                <div class="widget-thumb-body">
                                                    <span class="widget-thumb-subtitle">累计</span>
                                                    <span class="widget-thumb-body-stat" data-counter="counterup" data-value="5,071">5,071</span>
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
                                            <h4 class="widget-thumb-heading">咨询营收</h4>
                                            <div class="widget-thumb-wrap">
                                                <i class="widget-thumb-icon bg-green icon-bulb"></i>
                                                <div class="widget-thumb-body">
                                                    <span class="widget-thumb-subtitle">累计</span>
                                                    <span class="widget-thumb-body-stat" data-counter="counterup" data-value="7,644">7,644</span>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- END WIDGET THUMB -->
                                    </div>
                                    <div class="col-md-3">
                                        <!-- BEGIN WIDGET THUMB -->
                                        <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                                            <h4 class="widget-thumb-heading">课程营收</h4>
                                            <div class="widget-thumb-wrap">
                                                <i class="widget-thumb-icon bg-red icon-layers"></i>
                                                <div class="widget-thumb-body">
                                                    <span class="widget-thumb-subtitle">累计</span>
                                                    <span class="widget-thumb-body-stat" data-counter="counterup" data-value="1,293">1,293</span>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- END WIDGET THUMB -->
                                    </div>
                                    <div class="col-md-3">
                                        <!-- BEGIN WIDGET THUMB -->
                                        <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                                            <h4 class="widget-thumb-heading">评测营收</h4>
                                            <div class="widget-thumb-wrap">
                                                <i class="widget-thumb-icon bg-purple icon-screen-desktop"></i>
                                                <div class="widget-thumb-body">
                                                    <span class="widget-thumb-subtitle">累计</span>
                                                    <span class="widget-thumb-body-stat" data-counter="counterup" data-value="815">815</span>
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
                                                <i class="widget-thumb-icon bg-blue icon-bar-chart"></i>
                                                <div class="widget-thumb-body">
                                                    <span class="widget-thumb-subtitle">总收益</span>
                                                    <span class="widget-thumb-body-stat" data-counter="counterup" data-value="5,071">5,071</span>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- END WIDGET THUMB -->
                                    </div>
                                </div>

                                <!--统计图表-->
                                <div class="row">
                                    <div id="container" style="height: 500px;"></div>
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
</body>
</html>
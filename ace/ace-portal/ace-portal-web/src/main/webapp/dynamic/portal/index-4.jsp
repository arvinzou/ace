<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
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
    <meta content="${cfg.sys_name}"
          name="description"/>
    <meta content="" name="author"/>
    <script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
	var portalType='${SESSION_USERPROP_KEY.cfg.portalType}';
	var layoutTopHeight=0;
	var activeSyId ='${SESSION_USERPROP_KEY.activeSyId}';


    </script>
    <link rel="shortcut icon" href="favicon.ico">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/system/getUserProp.do"></script>
    <!-- bootstrap & fontawesome -->
    <script
            src="${pageContext.request.contextPath}/content/common/js/base.js?version=${cfg.version}"></script>


    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/content/common/assets/css/font-awesome.min.css?version=${cfg.version}"/>

    <link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/css/components.min.css" rel="stylesheet"
          id="style_components" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/css/plugins.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout3/css/layout.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout3/css/themes/default.min.css"
          rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout3/css/custom.min.css"
          rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="favicon.ico"/>


</head>
<body class="page-container-bg-solid">
<div class="page-wrapper">
    <div class="page-wrapper-row">
        <div class="page-wrapper-top">
            <!-- BEGIN HEADER -->
            <div class="page-header">
                <!-- BEGIN HEADER TOP -->
                <div class="page-header-top">
                    <div class="container">

                        <div class="page-logo">
                            <a href="index.html">
                                <img src="${pageContext.request.contextPath}/content/common/assets/layouts/layout3/img/logo-default.jpg"
                                     alt="logo" class="logo-default">
                            </a>
                        </div>
                        <a href="#" class="navbar-brand">
                            <small>
                                ${cfg.sys_name}${cfg.version}
                            </small>
                        </a>
                        <div class="top-menu">
                            <ul class="nav navbar-nav pull-right">
                                <li class="dropdown dropdown-user dropdown-dark">
                                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"
                                       data-hover="dropdown" data-close-others="true">
                                        <img alt="" class="img-circle"
                                             src="${pageContext.request.contextPath}/content/common/assets/layouts/layout3/img/avatar9.jpg">
                                        <span class="username username-hide-mobile">${SESSION_USERPROP_KEY.name}</span>
                                    </a>
                                    <ul class="dropdown-menu dropdown-menu-default">
                                        <li><a href="javascript:userCfg();">
                                            <i class="ace-icon fa fa-cog"></i>
                                            个性化配置
                                        </a></li>
                                        <li>
                                            <a data-target="#stack1" data-toggle="modal">
                                                <i class="ace-icon fa fa-key"></i> 密码修改
                                            </a>
                                        </li>
                                        <li class="divider"></li>

                                        <li>
                                            <a href="${pageContext.request.contextPath}/dynamic/portal/security/loginOut.jsp">
                                                <i class="icon-key"></i> 安全退出 </a>
                                        </li>
                                    </ul>
                                </li>

                            </ul>
                        </div>
                        <!-- END TOP NAVIGATION MENU -->
                    </div>
                </div>
                <!-- END HEADER TOP -->
                <!-- BEGIN HEADER MENU -->
                <div class="page-header-menu">
                    <div class="container">
                        <!-- BEGIN HEADER SEARCH BOX -->
                        <form class="search-form" action="page_general_search.html" method="GET">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search" name="query">
                                <span class="input-group-btn">
                                            <a href="javascript:;" class="btn submit">
                                                <i class="icon-magnifier"></i>
                                            </a>
                                        </span>
                            </div>
                        </form>
                           <div class="hor-menu ">
                            <ul class="nav navbar-nav" id="menu">

                            </ul>
                        </div>
                        <!-- END MEGA MENU -->
                    </div>
                </div>
                <!-- END HEADER MENU -->
            </div>
            <!-- END HEADER -->
        </div>
    </div>
    <div class="page-wrapper-row full-height">
        <div class="page-wrapper-middle">
            <!-- BEGIN CONTAINER -->
            <div class="page-container">
                <!-- BEGIN CONTENT -->
                <div class="page-content-wrapper">
                    <!-- BEGIN CONTENT BODY -->

                    <!-- BEGIN PAGE CONTENT BODY -->
                    <div class="page-content">
                        <div class="container">
                            <!-- BEGIN PAGE BREADCRUMBS -->
                            <ul class="page-breadcrumb breadcrumb">
                                <li>
                                    <a href="index.html">Home</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <li>
                                    <a href="#">UI Features</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <li>
                                    <span>Color Library</span>
                                </li>
                            </ul>
                            <!-- END PAGE BREADCRUMBS -->
                            <!-- BEGIN PAGE CONTENT INNER -->
                            <div class="page-content-inner">

                            </div>
                            <!-- END PAGE CONTENT INNER -->
                        </div>
                    </div>
                    <!-- END PAGE CONTENT BODY -->
                    <!-- END CONTENT BODY -->
                </div>
                <!-- END CONTENT -->
            </div>
            <!-- END CONTAINER -->
        </div>
    </div>
    <div class="page-wrapper-row">
        <div class="page-wrapper-bottom">


            <!-- BEGIN INNER FOOTER -->
            <div class="page-footer">
                <div class="container"> 2016 &copy; Metronic Theme By
                    <a target="_blank" href="http://keenthemes.com">Keenthemes</a> &nbsp;|&nbsp;
                    <a href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes"
                       title="Purchase Metronic just for 27$ and get lifetime updates for free" target="_blank">Purchase
                        Metronic!</a>
                </div>
            </div>
            <div class="scroll-to-top">
                <i class="icon-arrow-up"></i>
            </div>
            <!-- END INNER FOOTER -->
            <!-- END FOOTER -->
        </div>
    </div>
</div>

<div id="stack1" class="modal fade" tabindex="-1" data-width="300">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">密码修改</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <form id="fm-password">
                            <fieldset>
                                新设密码： <input id="password" type="password" style="width: 200px;"/>
                            </fieldset>
                            <fieldset>
                                重复输入： <input id="repassword" type="password" style="width: 200px;"/>
                            </fieldset>
                        </form>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn blue" onclick="submitform()">提交</button>
                <button type="button" data-dismiss="modal" class="btn red">取消</button>
            </div>
        </div>
    </div>
</div>
<!--[if lt IE 9]>

<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/excanvas.min.js"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/ie8.fix.min.js"></script>
<![endif]-->
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/scripts/app.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/layouts/layout3/scripts/layout.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/pages/scripts/ui-modals.min.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/content/portal/js/main/menu4.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/portal/js/main/portal.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>

</body>

</html>
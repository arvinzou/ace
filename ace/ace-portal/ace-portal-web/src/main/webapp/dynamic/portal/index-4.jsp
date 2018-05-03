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
    <meta content="${cfg.sys_name}" name="description"/>
    <script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
    </script>
    <link rel="shortcut icon" href="favicon.ico">
    <script type="text/javascript" src="${pageContext.request.contextPath}/system/getUserProp.do"></script>
    <script src="${pageContext.request.contextPath}/content/common/js/base.js?version=${cfg.version}"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/assets/css/font-awesome.min.css?version=${cfg.version}"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout3/css/layout.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="page-wrapper">
    <div class="page-wrapper-row">
        <div class="page-wrapper-top">
            <!-- BEGIN HEADER -->
            <div class="page-header">
                <!-- BEGIN HEADER TOP -->
                <div class="page-header-top">
                    <div class="">

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
                                    <div  class="dropdown-toggle" data-toggle="dropdown"
                                       data-hover="dropdown" data-close-others="true" style="cursor:pointer">
                                        <img alt="" class="img-circle"
                                             src="${pageContext.request.contextPath}/content/common/assets/layouts/layout3/img/avatar9.jpg">
                                        <span class="username username-hide-mobile">${SESSION_USERPROP_KEY.name}</span>
                                    </div>
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
                    </div>
                </div>
                <div class="page-header-menu">
                    <div class="container">
                        <div class="hor-menu ">
                            <ul class="nav navbar-nav" id="menu">

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="page-wrapper-row full-height">
        <div class="page-wrapper-middle">
            <div class="page-container">
                <div class="page-content-wrapper">
                    <div class="page-content">
                        <div class="container">
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
                            <div class="page-content-inner">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="page-wrapper-row">
        <div class="page-wrapper-bottom">
            <div class="page-footer">
                <div class="container"> 2016 &copy; Metronic Theme By
                    &nbsp;|&nbsp;
                   Purchase
                        Metronic!
                </div>
            </div>
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
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/portal/js/main/menu4.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/portal/js/main/portal4.js?version=${cfg.version}"></script>
</body>
</html>
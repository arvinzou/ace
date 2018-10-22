<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<script id="tpl-menu-base" type="text/template">
    <li class="nav-item start" id="menu999999">
        <a href="${pageContext.request.contextPath}/index.jsp" url="${pageContext.request.contextPath}/index.jsp"
           class="nav-link nav-toggle">
            <i class="icon-home"></i>
            <span class="title">仪表盘</span>

        </a>
    </li>
</script>
<div class="page-wrapper">
    <div class="page-header navbar navbar-fixed-top">
        <div class="page-header-inner ">
            <div class="page-logo">
                <a href="#">
                    <img src="${cfg.logo}"
                         alt="logo" class="logo-default"/>
                </a>
                <a href="#" class="navbar-brand logo-default" style="color:#FFF;margin:0px">
                    <small>
                        ${cfg.sys_name}
                    </small>
                </a>

                <div class="menu-toggler sidebar-toggler">
                    <span></span>
                </div>
            </div>

            <div class="top-menu">
                <ul class="nav navbar-nav pull-right">
                    <li class="dropdown dropdown-user">
                        <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                           data-close-others="true">
                            <img alt="" class="img-circle"
                                 src="${portalPath}/content/common/assets/layouts/layout/img/avatar3_small.jpg"/>
                            <span class="username username-hide-on-mobile"> ${SESSION_USERPROP_KEY.name} </span>
                            <i class="fa fa-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-default">
                            <li><a href="${portalPath}/dynamic/service/userCfg/index.jsp">
                                <i class="ace-icon fa fa-cog"></i>
                                个性化配置
                            </a></li>
                            <li>
                                <a href="${portalPath}/dynamic/portal/modifyPwd.jsp">
                                    <i class="ace-icon fa fa-key"></i> 密码修改
                                </a>
                            </li>
                            <li class="divider"></li>

                            <li>
                                <a href="${portalPath}/dynamic/portal/security/loginOut.jsp">
                                    <i class="icon-key"></i> 安全退出 </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>

    <div class="page-container">
        <div class="page-sidebar-wrapper">
            <div class="page-sidebar navbar-collapse collapse">
                <ul id="menu" class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false"
                    data-auto-scroll="true"
                    data-slide-speed="200" style="padding-top: 20px">


                </ul>
            </div>
        </div>
        <div class="page-content-wrapper">
            <div class="page-content">
                <ul class="page-breadcrumb breadcrumb">
                    <li>
                        <a href="${pageContext.request.contextPath}/index.jsp">首页</a>

                    </li>
                    <li>
                        <span class="todo-header"></span>
                    </li>
                </ul>
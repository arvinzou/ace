<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<div class="page-wrapper">
    <div class="page-header navbar navbar-fixed-top">
        <div class="page-header-inner ">
            <div class="page-logo">
                <a href="#">
                    <img src="${cfg.logo}"
                         alt="logo" class="logo-default"/> </a>
                <div class="menu-toggler sidebar-toggler" onclick="setTimeout('autoWidth()',100)">
                    <span></span>
                </div>
            </div>

            <a href="#" class="navbar-brand" style="color:#FFFFFF">
                <small>
                    ${cfg.sys_name}${cfg.version}
                </small>
            </a>
            <div class="top-menu">
                <ul class="nav navbar-nav pull-right">
                    <li class="dropdown dropdown-user">
                        <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                           data-close-others="true">
                            <img alt="" class="img-circle"
                                 src="${pageContext.request.contextPath}/content/common/assets/layouts/layout/img/avatar3_small.jpg"/>
                            <span class="username username-hide-on-mobile"> ${SESSION_USERPROP_KEY.name} </span>
                            <i class="fa fa-angle-down"></i>
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
        </div>
    </div>
    <div class="clearfix"></div>

    <div class="page-container">
        <div class="page-sidebar-wrapper">
            <div class="page-sidebar navbar-collapse collapse">
                <ul id="menu" class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false"
                    data-auto-scroll="true"
                    data-slide-speed="200" style="padding-top: 0px">
                </ul>
            </div>
        </div>
        <div class="page-content-wrapper">
            <div class="page-content">
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
	var fastdfs_server='${cfg.fastdfs_server}';
	var default_page_list=[${cfg.default_page_list}];
    var cmenu='${param.id}';
    </script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/system/getUserProp.do"></script>
    <!-- bootstrap & fontawesome -->
    <script
            src="${pageContext.request.contextPath}/content/common/js/base.js?version=${cfg.version}"  defer="defer"></script>
    <!-- basic scripts -->
    <!--[if lte IE 8]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/content/common/assets/js/gz/jquery1x.min.js?version=${cfg.version}"  defer="defer"></script>
    <![endif]-->
    <script type="text/javascript">
			window.jQuery || document.write("<script  src='${pageContext.request.contextPath}/content/common/assets/js/gz/jquery.min.js?version=${cfg.version}'>"+"<"+"/script>");
		</script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/system/getButtonAuthority.do?id=${param.id}"></script>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/content/common/assets/css/font-awesome.min.css?version=${cfg.version}"/>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet"
          type="text/css"/>
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
    <link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout/css/layout.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout/css/themes/darkblue.min.css"
          rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout/css/custom.min.css"
          rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="favicon.ico"/>

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/themes/gray/easyui.css?version=${cfg.version}">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/content/common/assets/css/ace-fonts.css?version=${cfg.version}" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/assets/css/ace-ie8.min.css?version=${cfg.version}" />
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/content/common/assets/css/ui.jqgrid.css?version=${cfg.version}" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/ui.multiselect.css?version=${cfg.version}" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/ace-ui-custom.css?version=${cfg.version}" />

</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
<div class="page-wrapper">
    <div class="page-header navbar navbar-fixed-top">
        <div class="page-header-inner ">
            <div class="page-logo">
                <a href="#">
                    <img src="${pageContext.request.contextPath}/content/common/assets/layouts/layout/img/logo.png"
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




<!--start-->
            <div class="page-content" style="min-height: 1240px;">

                <div class="widget-box" id="widget-box">
                    <div class="widget-header">
                        <h5 class="widget-title smaller">用户管理</h5>

                        <div class="widget-toolbar"></div>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main padding-6">
                            <form action="#" id="fm-search">
                                <%-- 地区： <input id="cc2" name="areaCode"
                                                class="easyui-combotree"
                                                data-options="url:'${pageContext.request.contextPath}/system/selectProvinceTreeList.do',method:'get',animate: true,
                lines:false,"
                                                style='width: 200px; line-height: 25px; height: 25px;'> <a
                                    href="javascript:clearAreaCode()">清除</a>  --%>
                                姓名： <input name="name" type="text" style="width: 200px;height:25px" />
                                归属部门：
                                <input name="departmentId" class="easyui-combotree"
                                       data-options="url:'${pageContext.request.contextPath}/system/selectDepartmentTreeList.do?id=01',method:'get',animate: true,
                lines:false,"
                                       style='width: 200px; line-height: 25px; height: 25px;'>
                                <button class="btn btn-info" id="btn-search"
                                        authority="${pageContext.request.contextPath}/users/findUsersList.do">
                                    <i
                                            class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                                </button>

                            </form>
                            <div class="space10"></div>
                            <div id="toolbar" class="toolbar">

                                <button class="btn btn-info" id="btn-view-add"
                                        authority="${pageContext.request.contextPath}/users/insertUsers.do">
                                    <i
                                            class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                                </button>
                                <button class="btn btn-info" id="btn-view-edit"
                                        authority="${pageContext.request.contextPath}/users/updateUsers.do">
                                    <i
                                            class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                                </button>
                                <button class="btn btn-purple" id="btn-view-da"
                                        authority="${pageContext.request.contextPath}/users/insertUsersRole.do">
                                    <i
                                            class="ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right"></i>
                                </button>
                                <button class="btn btn-warning" id="btn-view-initpwd"
                                        authority="${pageContext.request.contextPath}/users/updateUsersForInitPassword.do">
                                    <i
                                            class="ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right"></i>
                                </button>
                                <button class="btn btn-warning" id="btn-view-del"
                                        authority="${pageContext.request.contextPath}/users/updateUsersStautsByPrimaryKey.do">
                                    <i
                                            class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                                </button>
                                <button class="btn btn-info" id="btn-view-import"
                                        authority="${pageContext.request.contextPath}/users/importXls.do">
                                    <i
                                            class="ace-icon glyphicon glyphicon-upload  align-middle bigger-125 icon-on-right"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <table id="grid-table"></table>

                <div class="paginationbar"><ul id="grid-pager" class="pagination"></ul></div>
            </div>
            <!--end--->
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
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/respond.min.js" defer="defer"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/excanvas.min.js" defer="defer"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/ie8.fix.min.js" defer="defer"></script>
<![endif]-->

<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js"
        type="text/javascript" defer="defer"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/scripts/app.min.js"
        type="text/javascript" defer="defer"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/layouts/layout/scripts/layout.min.js"
        type="text/javascript" defer="defer"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/pages/scripts/ui-modals.min.js"
        type="text/javascript" defer="defer"></script>
<script
        src="${pageContext.request.contextPath}/content/common/assets/js/gz/jquery-ui.min.js?version=${cfg.version}" defer="defer"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}" defer="defer"></script>
<script
        src="${pageContext.request.contextPath}/content/portal/js/main/menu.js?version=${cfg.version}" defer="defer"></script>
<script
        src="${pageContext.request.contextPath}/content/portal/js/main/portal.js?version=${cfg.version}" defer="defer"></script>


<script
        src="${pageContext.request.contextPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}" defer="defer"></script>

<script
        src="${pageContext.request.contextPath}/content/common/assets/js/gz/jquery.jqGrid.js?version=${cfg.version}" defer="defer"></script>

<!--
<script
   src="${pageContext.request.contextPath}/content/common/assets/js/uncompressed/jqGrid/jquery.jqGrid.js?version=6"></script>
   -->
<script
        src="${pageContext.request.contextPath}/content/common/assets/js/uncompressed/jqGrid/ui.multiselect.js?version=${cfg.version}" defer="defer"></script>

<script
        src="${pageContext.request.contextPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}" defer="defer"></script>

<script
        src="${pageContext.request.contextPath}/content/portal/js/users/config.js?version=${cfg.version}" defer="defer"></script>
<script
        src="${pageContext.request.contextPath}/content/portal/js/users/model.js?version=${cfg.version}" defer="defer"></script>
<script
        src="${pageContext.request.contextPath}/content/portal/js/users/controller.js?version=${cfg.version}" defer="defer"></script>
<script
        src="${pageContext.request.contextPath}/content/portal/js/users/view.js?version=${cfg.version}" defer="defer"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/common/js/jquery.form.js?version=${cfg.version}" defer="defer"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/common/js/authority.js?version=${cfg.version}" defer="defer"></script>
<script type="text/javascript">
		function checkPwd() {
			var passwd = '${SystemUser.users.password}';
			if (passwd == '4297F44B13955235245B2497399D7A93'
					|| passwd == '4297f44b13955235245b2497399d7a93') {
				alert("${SystemUser.users.name}，你好！您目前使用的是默认密码为了确保安全，请您及时更换密码！");
				$('#stack1').modal('show')
			}
		}
		function autoWidth() {
			$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());

		}


</script>
<style>

 .page-content-wrapper .page-content {
    margin-left: 200px;
    margin-top: 0;
    min-height: 600px;
    padding: 5px 5px 5px;
}
.page-sidebar {
    width: 200px;
    float: left;
    position: relative;
    margin-right: -100%;
}
.tabs-header, .tabs-tool {
    background-color: #ffffff;
}
a, button, code, div, img, input, label, li, p, pre, select, span, svg, table, td, textarea, th, ul {
    -webkit-border-radius: 0!important;
    -moz-border-radius: 0!important;
    border-radius: 2px!important;
}

</style>
</body>

</html>
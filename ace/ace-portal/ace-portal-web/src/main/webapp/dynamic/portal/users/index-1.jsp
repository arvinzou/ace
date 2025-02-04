<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <title>user</title>
</head>
<jsp:include page="../../common/common.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />

<style type="text/css">
		.excel{ background-color:#999; font-size:13px;}
		.excel td{ background-color:#fff; white-space:nowrap;}
		.excel th{ background-color:#E7E7E7; font-weight:normal;}
</style>
<body>
<div class="page-content">
    <div class="widget-box" id="widget-box">
        <div class="widget-header">
            <h5 class="widget-title smaller">设置查询条件</h5>

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
                           style='width: 200px;'>
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


    <div id="dialog-message" class="hide">
        <table >
            <tr>
                <td align="center" valign="top">
                    <table id="allrole-grid-table"></table>
                    <div id="allrole-grid-pager"></div>
                </td>
                <td align="center" valign="middle">
                    <button class="btn btn-info" id="btn-view-da-add"
                            authority="${pageContext.request.contextPath}/users/insertUsersRole.do">
                        分配<i
                            class="ace-icon fa   fa-angle-double-right  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <div style="height:3px"></div>
                    <button class="btn btn-info" id="btn-view-da-del"
                            authority="${pageContext.request.contextPath}/users/insertUsersRole.do">
                        移除<i
                            class="ace-icon fa  fa-angle-double-left  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </td>
                <td align="center" valign="top">
                    <table id="myrole-grid-table"></table>
                    <div id="myrole-grid-pager"></div>
                </td>
            </tr>
        </table>



    </div>
    <div id="dialog-message-initpwd" class="hide">
        <form action="/users/updateUsersForInitPassword.do" id="fm-initpwd">
            <fieldset>
                初始密码： <input id="init_passwd" type="password" style="width: 200px;" />
            </fieldset>
            <fieldset>
                重复输入： <input id="init_rpasswd" type="password"
                             style="width: 200px;" />
            </fieldset>
        </form>
    </div>
    <div id="dialog-confirm" class="hide">
        <div class="alert alert-info bigger-110">
            重新分配角色后，分配此角色的用户将获取新的权限.</div>

        <div class="space-6"></div>

        <p class="bigger-110 bolder center grey">
            <i class="ace-icon fa fa-hand-o-right blue bigger-120"></i> 您确定吗?
        </p>
    </div>
    <div id="dialog-confirm-initpwd" class="hide">
        <div class="alert alert-info bigger-110">初始密码后，系统将启用新的密码.</div>

        <div class="space-6"></div>

        <p class="bigger-110 bolder center grey">
            <i class="ace-icon fa fa-hand-o-right blue bigger-120"></i> 您确定吗?
        </p>
    </div>
    <div id="dialog-message-weixin" class="hide">
        <div>
            昵称： <input type="text" name="q" id="q1"/>
            <button class="btn btn-info" id="btn-search-weixin"
                    authority="false">
                查询
                <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
            </button>
        </div>
        <div id="box-weixin">

        </div>
    </div>
    <div id="dialog-message-sapp" class="hide">
        <div>
            昵称： <input type="text" name="q" id="q2"/>
            <button class="btn btn-info" id="btn-search-sapp"
                    authority="false">
                查询
                <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
            </button>
        </div>
        <div id="box-sapp">

        </div>
    </div>
</div>
<jsp:include page="../../common/footer-1.jsp" />
<script src="${portalPath}/content/common/assets/js/uncompressed/jquery.colorbox.js"></script>
<script
        src="${pageContext.request.contextPath}/content/portal/js/users/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/portal/js/users/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/portal/js/users/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/portal/js/users/view.js?version=${cfg.version}"></script>
<jsp:include page="../../common/footer-2.jsp" />

<script type="text/javascript">
window.onresize = function () {
	autoSize();
}
parent.onresize = function () {
	autoSize();
}
function autoSize(){
	//parent.autoWidth();
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight',window.innerHeight-layoutTopHeight);

}
</script>

<div id="dialog-message-upload" class="hide">

    <div id="uploader">
        <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
    </div>

    <div style="margin:5px">

        <a href="rs.xls" style="color:red">下载模板</a>.<br>


    </div>



</div>
<script
        src="${pageContext.request.contextPath}/content/portal/js/users/upload.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>

<link rel="stylesheet" href="${portalPath}/content/common/assets/css/colorbox.css" />
<style>
        .layout-user {
            width: 80px;
            height: 80px;
            float: left;
            margin: 10px 10px 10px;
            text-align:center;
        }
        .photo {
            height: 60px;
            max-height:60px;
            max-width:60px;
            vertical-align: middle;
            border-radius:60px;
        }
        #cboxContent {
            background-color: rgb(255, 255, 255);
            border-width: 1px;
            border-style: solid;
            border-color: #ddd;
            border-image: initial;
            padding: 5px;
        }
        .ace-thumbnails > li {
            float: left;
            display: block;
            position: relative;
            overflow: hidden;
            margin: 2px;
            border-width: 0px;
            border-style: solid;
            border-color: #ddd;
            border-image: initial;
        }


    </style>
</body>
</html>



 function initWeixin(userId) {
 	$.ajax({
 		type: "get",
 		url: contextPath + "/users/selectWxUser.do",
 		data: {
 			userId: userId
 		},
 		success: function(rst, textStatus) {
 			renderWeixin(rst);
 		}
 	});
 }

 function renderWeixin(rst) {
 	var html = new Array();
 	html.push('<ul class="ace-thumbnails clearfix">');
 	$.each($(rst), function(i, o) {
 		html.push('<li>');
 		html.push('<a href="' + o.headimgurl + '" title="' + o.nickname + '" data-rel="colorbox" class="cboxElement">');
 		html.push('<img class="photo" src="' + o.headimgurl + '">');
 		html.push('</a>');
 		html.push('<div class="tools tools-bottom">');
 		html.push('<a href="javascript:delWeixin(\'' + o.unionid + '\')">');
 		html.push('<i class="ace-icon fa fa-times red"></i>');
 		html.push('</a>');
 		html.push('</div>');
 		html.push('<div style="text-align:center">');
 		html.push(o.nickname);
 		html.push('</div>');
 		html.push('</li>');
 	});
 	html.push('<li>');
 	html.push(
 		'<a href="javascript:false"><img style="padding:20px" alt="60x60" id="btn-image-upload-weixin" class="photo" src="' +
 		portalPath + '/content/common/image/add.png"></a>');
 	html.push('</li>');
 	html.push('</ul>');


 	$("#custom-weixin").html(html.join(""));

 	var $overflow = '';
 	var colorbox_params = {
 		rel: 'colorbox',
 		reposition: true,
 		scalePhotos: true,
 		scrolling: false,
 		previous: '<i class="ace-icon fa fa-arrow-left"></i>',
 		next: '<i class="ace-icon fa fa-arrow-right"></i>',
 		close: '&times;',
 		current: '{current} of {total}',
 		maxWidth: '100%',
 		maxHeight: '100%',
 		onOpen: function() {
 			$overflow = document.body.style.overflow;
 			document.body.style.overflow = 'hidden';
 		},
 		onClosed: function() {
 			document.body.style.overflow = $overflow;
 		},
 		onComplete: function() {
 			$.colorbox.resize();
 		}
 	};

 	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
 	$('#btn-image-upload-weixin').on('click', function() {
 		dialog1 = $("#dialog-message-weixin").removeClass('hide').dialog({
 			modal: true,
 			width: 750,
 			title: "<div class='widget-header widget-header-small'><div class='widget-header-pd'>绑定</div></div>",
 			title_html: true,
 			buttons: [{
 				html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
 				"class": "btn btn-info btn-xs",
 				click: function() {
 					$(this).dialog("close");
 				}
 			}]
 		});
 		$(dialog1).parent().css("top", "1px");
 		$(dialog1).css("max-height", window.innerHeight - layoutTopHeight + 50);

 	});
 }

 function delWeixin(id) {
 	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
 	var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
 	$.ajax({
 		type: "post",
 		url: contextPath + "/users/deleteOpenIdById.do",
 		data: {
 			userId: r.userId
 		},
 		success: function(rst, textStatus) {
 			initWeixin(r.userId);
 		}
 	});
 }

 function insertWeixinUser(unionid, openId) {
 	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
 	var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
 	$.ajax({
 		type: "post",
 		url: contextPath + "/users/updateOpenIdById.do",
 		data: {
 			userId: r.userId,
 			openId: unionid
 		},
 		success: function(rst, textStatus) {
 			initWeixin(r.userId);
 			$(dialog1).dialog("close");
 			$("#dialog-message-weixin").addClass('hide');
 		}
 	});
 }


 /**********************************************************************************************************************/




 function initSapp(userId) {
 	$.ajax({
 		type: "get",
 		url: contextPath + "/users/selectAppWxUser.do",
 		data: {
 			userId: userId
 		},
 		success: function(rst, textStatus) {
 			renderSapp(rst);
 		}
 	});
 }

 function renderSapp(rst) {
 	var html = new Array();
 	html.push('<ul class="ace-thumbnails clearfix">');
 	$.each($(rst), function(i, o) {
 		html.push('<li>');
 		html.push('<a href="' + o.avatarUrl + '" title="' + o.nickName + '" data-rel="colorbox" class="cboxElement">');
 		html.push('<img class="photo" src="' + o.avatarUrl + '">');
 		html.push('</a>');
 		html.push('<div class="tools tools-bottom">');
 		html.push('<a href="javascript:delSapp(\'' + o.unionId + '\')">');
 		html.push('<i class="ace-icon fa fa-times red"></i>');
 		html.push('</a>');
 		html.push('</div>');
 		html.push('<div style="text-align:center">');
 		html.push(o.nickName);
 		html.push('</div>');
 		html.push('</li>');
 	});
 	html.push('<li>');
 	html.push(
 		'<a href="javascript:false"><img style="padding:20px" alt="60x60" id="btn-image-upload-sapp" class="photo" src="' +
 		portalPath + '/content/common/image/add.png"></a>');
 	html.push('</li>');
 	html.push('</ul>');


 	$("#custom-sapp").html(html.join(""));

 	var $overflow = '';
 	var colorbox_params = {
 		rel: 'colorbox',
 		reposition: true,
 		scalePhotos: true,
 		scrolling: false,
 		previous: '<i class="ace-icon fa fa-arrow-left"></i>',
 		next: '<i class="ace-icon fa fa-arrow-right"></i>',
 		close: '&times;',
 		current: '{current} of {total}',
 		maxWidth: '100%',
 		maxHeight: '100%',
 		onOpen: function() {
 			$overflow = document.body.style.overflow;
 			document.body.style.overflow = 'hidden';
 		},
 		onClosed: function() {
 			document.body.style.overflow = $overflow;
 		},
 		onComplete: function() {
 			$.colorbox.resize();
 		}
 	};

 	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
 	$('#btn-image-upload-sapp').on('click', function() {
 		dialog2 = $("#dialog-message-sapp").removeClass('hide').dialog({
 			modal: true,
 			width: 750,
 			title: "<div class='widget-header widget-header-small'><div class='widget-header-pd'>绑定</div></div>",
 			title_html: true,
 			buttons: [{
 				html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
 				"class": "btn btn-info btn-xs",
 				click: function() {
 					$(this).dialog("close");
 				}
 			}]
 		});
 		$(dialog2).parent().css("top", "1px");
 		$(dialog2).css("max-height", window.innerHeight - layoutTopHeight + 50);

 	});
 }

 function delSapp(id) {
 	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
 	var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
 	$.ajax({
 		type: "post",
 		url: contextPath + "/users/deleteAppOpenIdById.do",
 		data: {
 			userId: r.userId
 		},
 		success: function(rst, textStatus) {
 			initSapp(r.userId);
 		}
 	});
 }

 function insertSappUser(unionid, openId) {
 	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
 	var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
 	$.ajax({
 		type: "post",
 		url: contextPath + "/users/updateUserAppOpenId.do",
 		data: {
 			userId: r.userId,
 			appOpenId: unionid
 		},
 		success: function(rst, textStatus) {
 			initSapp(r.userId);
 			$(dialog2).dialog("close");
 			$("#dialog-message-sapp").addClass('hide');
 		}
 	});
 }

$('#btn-search-weixin').on('click', function() {
 		$.ajax({
 			type: "post",
 			url: contextPath + "/users/selectAllWxUserList.do",
 			data: {
 				role: "",
 				nickname: $("#q1").val()
 			},
 			success: function(rst, textStatus) {
 				var html = [];
 				$.each($(rst), function(i, o) {
 					html.push('<div class="layout-user">');
 					html.push('<a href="javascript:insertWeixinUser(\'' + o.unionid + '\',\'' + o.openid + '\')">');
 					html.push('<img  class="photo" src="' + o.headimgurl + '">');
 					html.push('</a>');
 					html.push('<div style="text-align:center">');
 					html.push(o.nickname);
 					html.push('</div>');
 					html.push('</div>');
 				});
 				$("#box-weixin").html(html.join(""));
 			}
 		});
 	});


 	$('#btn-search-sapp').on('click', function() {
 		$.ajax({
 			type: "post",
 			url: contextPath + "/users/selectAllAppWxUserList.do",
 			data: {
 				role: "",
 				nickName: $("#q2").val()
 			},
 			success: function(rst, textStatus) {
 				var html = [];
 				$.each($(rst), function(i, o) {
 					html.push('<div class="layout-user">');
 					html.push('<a href="javascript:insertSappUser(\'' + o.unionId + '\',\'' + o.openId + '\')">');
 					html.push('<img  class="photo" src="' + o.avatarUrl + '">');
 					html.push('</a>');
 					html.push('<div style="text-align:center">');
 					html.push(o.nickName);
 					html.push('</div>');
 					html.push('</div>');
 				});
 				$("#box-sapp").html(html.join(""));
 			}
 		});
 	});

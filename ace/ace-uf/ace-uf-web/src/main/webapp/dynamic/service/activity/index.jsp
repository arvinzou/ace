<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>活动</title>
</head>
<jsp:include page="../../common/common.jsp"/>

<link rel="stylesheet" href="${portalPath}/content/common/assets/css/colorbox.css"/>

<script type="text/javascript">
</script>
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

                    类别：<input
                        class="easyui-combobox" style="width: 200px" name="category"
                        data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=103&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                    名称： <input name="name" type="text"
                               style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/activity/findActivityList.do">
                        <i
                                class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>


                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">


                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/activity/insertActivity.do">
                        <i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/activity/updateActivity.do">
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/activity/deleteActivityByActivityId.do">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>


    <table id="grid-table"></table>

    <div id="grid-pager"></div>


</div>
<div id="dialog-message" class="hide">
    <div id="uploader">
        <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
    </div>
</div>
<div id="dialog-message-file" class="hide">
    <div id="load" class="loading"></div>
</div>

<div id="dialog-message-view" class="hide">
    <h5 class="header-title">基本信息</h5>
    <div class="row" style="padding:10px">

        <div class="labelItem"><span class="labelItemHeader">
活动名称</span>
            <br>
            <span id="name">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
活动地点</span>
            <br>
            <span id="activityLocation">
</span>
        </div>


        <div class="labelItem"><span class="labelItemHeader">
活动日期</span>
            <br>
            <span id="activityDate">
</span>
        </div>


        <div class="labelItem"><span class="labelItemHeader">
阅读量</span>
            <br>
            <span id="reading">
</span>
        </div>
    </div>
    <h5 class="header-title">参与人员</h5>
    <div class="row" style="padding:10px" id="activityUser">

    </div>

    <h5 class="header-title">活动相册</h5>
    <div class="row" style="padding:10px" id="custom-dia">

    </div>

    <h5 class="header-title">详细情况</h5>
    <div class="row" style="padding:10px" id="docText">

    </div>

    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">

        <div class="labelItem"><span class="labelItemHeader">
创建人姓名</span>
            <br>
            <span id="createUserName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
入库日期</span>
            <br>
            <span id="createDate">
</span>
        </div>

        <div class="labelItem"><span class="labelItemHeader">
最后更新人姓名</span>
            <br>
            <span id="lastModifyUserName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后更新时间</span>
            <br>
            <span id="lastModifyDate">
</span>
        </div>
    </div>

</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script src="${portalPath}/content/common/assets/js/uncompressed/jquery.colorbox.js"></script>

<script
        src="${pageContext.request.contextPath}/content/service/activity/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/activity/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/activity/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/activity/view.js?version=${cfg.version}"></script>

<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>

<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

<script src="${pageContext.request.contextPath}/content/service/activity/upload.js?version=${cfg.version}"></script>

<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>
<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
    window.onresize = function () {
        console.log('autoWidthJqgrid');
        $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        $(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
        parent.autoWidth();
    }
</script>

<div id="dialog-multi" class="hide">
    <select class="easyui-combogrid"
            style="width: 585px; height: 25px; line-height: 25px;"
            id="multiComponent"
            data-options="panelWidth: 585,idField: 'id',textField: 'name',url: '${pageContext.request.contextPath}/personage/selectPersonage.do',
			mode:'remote',
			fitColumns:true,
			method: 'get',columns: [[
			{field:'id',title:'编号',width:150},
			{field:'name',title:'姓名',width:100},
			{field:'mobile',title:'手机号',width:150,align:'right'},
			{field:'deptName',title:'单位',width:250,align:'right'}
			 ]]">

    </select>

    <div style="height: 5px"></div>
    <div>
        <button class="btn btn-purple" id="btn-add"
                authority="false">
            添加<i
                class="ace-icon glyphicon  glyphicon-plus  align-top bigger-125 icon-on-right"></i>
        </button>
        <button class="btn btn-purple" id="btn-del"
                authority="false">
            清除<i
                class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
        </button>
    </div>
    <div style="height: 5px"></div>
    <div id="multi-content" class="easyui-panel"
         style="padding: 5px; width: 585px; height: 200px"></div>

    <script type="text/javascript">
        jQuery(function ($) {
            $("#btn-add").on('click', function (e) {
                e.preventDefault();
                getComponent();
            });
            $('#multiComponent').combogrid({
                onSelect: function (index, row) {
                    getComponent();
                }
            });
            $("#btn-del").on('click', function (e) {
                e.preventDefault();
                $('#multi-content').html('');
            });
        });

        function getComponent() {
            var html = new Array();
            var g = $('#multiComponent').combogrid('grid');
            var r = g.datagrid('getSelected');	// get the selected row
            $.each($('user'), function (i, obj) {
                if ($(obj).attr("id") == r.id) {
                    alert("重复。");
                    return;
                }
            });
            html.push('<div class="layout-user" >');
            html.push('<user id="' + r.id + '" class="badge badge-' + cssColor9[0] + '">');
            html.push(r.name);
            html.push('</user>');
            html.push('</div>');
            $('#multi-content').html($('#multi-content').html() + html.join(''));
        }
    </script>
    <style>
        .layout-user {
            width: 60px;
            height: 20px;
            float: left;
            margin: 1px 1px 1px;
        }

        .photo {
            height: 90px;
            max-height: 90px;
            max-width: 90px;
            vertical-align: middle;
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
            border-width: 1px;
            border-style: solid;
            border-color: #ddd;
            border-image: initial;
        }
    </style>

</div>
</body>
</html>

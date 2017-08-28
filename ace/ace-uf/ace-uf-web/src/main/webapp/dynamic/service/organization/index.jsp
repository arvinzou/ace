<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>社会资源</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
<link rel="stylesheet" href="${portalPath}/content/common/assets/css/colorbox.css" />
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
                    辖区： <input id="cc2" name="areaCode"
                               class="easyui-combotree"
                               data-options="url:'${portalPath}/system/selectProvinceTreeList.do',method:'get',animate: true,
                lines:false,"
                               style='width: 200px;'> <a
                        href="javascript:clearAreaCode()">清除</a>
                    类别：<input
                        class="easyui-combobox" style="width: 200px" name="category"
                        data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=97&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                    名称： <input name="name" type="text"
                               style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/organization/findOrganizationList.do">
                        <i
                                class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>


                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">


                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/organization/insertOrganization.do">
                        <i
                                class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/organization/updateOrganization.do">
                        <i
                                class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/organization/deleteOrganizationByOrganizationId.do">
                        <i
                                class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
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
主键</span>
            <br>
            <span id="id">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
资源类别</span>
            <br>
            <span id="category">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
所属辖区</span>
            <br>
            <span id="areaName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
名称</span>
            <br>
            <span id="name">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
地址</span>
            <br>
            <span id="addr">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
联系电话</span>
            <br>
            <span id="tel">
</span>
        </div>

        <div class="labelItem"><span class="labelItemHeader">
维度</span>
            <br>
            <span id="latitude">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
经度</span>
            <br>
            <span id="longitude">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
服务时间开始</span>
            <br>
            <span id="serviceTimeStart">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
服务时间截止</span>
            <br>
            <span id="serviceTimeEnd">
</span>
        </div>


        <div class="labelItem"><span class="labelItemHeader">
状态</span>
            <br>
            <span id="status">
</span>
        </div>
    </div>
    <h5 class="header-title">二维码</h5>
    <div class="row" style="padding:10px" id="icon"></div>
    <h5 class="header-title">资源图片</h5>
    <div class="row" style="padding:10px" id="custom-dia">

    </div>
    <h5 class="header-title">服务途径</h5>
    <div class="row" style="padding:10px" id="serviceWay">

    </div>
    <h5 class="header-title">备注</h5>
    <div class="row" style="padding:10px" id="remark">

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
        src="${pageContext.request.contextPath}/content/service/organization/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/organization/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/organization/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/organization/view.js?version=${cfg.version}"></script>





<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script
        src="${pageContext.request.contextPath}/content/service/organization/upload.js?version=${cfg.version}"></script>

<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);
	parent.autoWidth();
}
function latitude(latitude){
    $("#latitude").val(latitude);
}
function longitude(longitude){
    $("#longitude").val(longitude);
}
function addr(addr){
    $("#addr").val(addr);
}
</script>
<style>
        .photo {
            height: 90px;
            max-height:90px;
            max-width:90px;
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
</body>
</html>
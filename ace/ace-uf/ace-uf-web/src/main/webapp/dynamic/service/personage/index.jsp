<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>统战人士</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
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
                <form action="#" id="fm-search" onsubmit="t_submit()">
                    所属辖区：<input id="cc2" name="areaCode"
                               class="easyui-combotree"
                               data-options="url:'${portalPath}/system/selectProvinceTreeList.do',method:'get',animate: true,
                lines:false,"
                               style='width: 200px;'>

                    单位：<input name="deptId" type="text"
                              style="width: 200px;"/>



                    姓名：<input name="name" type="text"
                               style="width: 200px;"/>
                    <div class="space10"></div>
                    人士类别：<input id="cc1" name="category"
                                class="easyui-combotree"
                                data-options="url:'${portalPath}/dict/selectDictAllTreeByCategoryId.do?id=98',method:'get',animate: true,
                lines:false,"
                                style='width: 200px;'>

                      职业：<input
                        class="easyui-combobox" style="width: 200px" name="careerType"
                        data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=102&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                    党派：<input
                        class="easyui-combobox" style="width: 200px" name="party"
                        data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=101&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                    民族：<input
                        class="easyui-combobox" style="width: 200px" name="nation"
                        data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=09&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/personage/findPersonageList.do">
                        <i
                                class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>



                <div class="space10"></div>
                <div id="toolbar" class="toolbar">


                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/personage/insertPersonage.do">
                        <i
                                class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/personage/updatePersonage.do">
                        <i
                                class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/personage/deletePersonageByPersonageId.do">
                        <i
                                class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-import"
                            authority="false">
                        Excel导入<i
                            class="ace-icon glyphicon glyphicon-upload  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-print"
                            authority="false">
                        打印花名册<i
                            class="ace-icon fa fa-print  align-middle bigger-125 icon-on-right"></i>
                    </button>

                </div>
                </form>
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
    <div style="margin:5px" id="tt" class="hide">

        <a href="rs.xls" style="color:red">下载模板</a>.<br>
        <a href="sm.xls" style="color:red">下载说明</a>.<br>


    </div>
</div>
<div id="dialog-message-file" class="hide">
    <div id="load" class="loading"></div>
</div>

<div id="dialog-message-view" class="hide">
    <h5 class="header-title">基本信息</h5>
    <div class="row" style="padding:10px">

        <div class="labelItem"><span class="labelItemHeader">
所属辖区</span>
            <br>
            <span id="areaName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
单位&职务</span>
            <br>
            <span id="deptName">
</span>
        </div>


        <div class="labelItem"><span class="labelItemHeader">
职业类别</span>
            <br>
            <span id="careerType">
</span>
        </div>

        <div class="labelItem"><span class="labelItemHeader">
姓名</span>
            <br>
            <span id="name">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
性别</span>
            <br>
            <span id="sex">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
出生日期</span>
            <br>
            <span id="birthday">
</span>
        </div>

        <div class="labelItem"><span class="labelItemHeader">
籍贯</span>
            <br>
            <span id="placeOfOriginName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
民族</span>
            <br>
            <span id="nation">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
学历学位</span>
            <br>
            <span id="degreee">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
职称</span>
            <br>
            <span id="academicTitle">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
职级</span>
            <br>
            <span id="rank">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
党派</span>
            <br>
            <span id="party">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
加入时间</span>
            <br>
            <span id="joinDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
行政职务</span>
            <br>
            <span id="administrativeDuty">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
现任职务时间</span>
            <br>
            <span id="currentPostDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
现任职级时间</span>
            <br>
            <span id="currentRankDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
移动电话</span>
            <br>
            <span id="mobile">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
办公电话</span>
            <br>
            <span id="tel">
</span>
        </div>




    </div>

    <h5 class="header-title">个人简介</h5>
    <div class="row" style="padding:10px" id="intro"></div>

    <h5 class="header-title">照片</h5>
    <div class="row" style="padding:10px" id="photo"></div>
        <h5 class="header-title">二维码</h5>
        <div class="row" style="padding:10px" id="remark"></div>
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
<script
        src="${pageContext.request.contextPath}/content/service/personage/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/personage/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/personage/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/personage/view.js?version=${cfg.version}"></script>

<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

<script
        src="${pageContext.request.contextPath}/content/service/personage/upload.js?version=${cfg.version}"></script>

<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight-40);
	parent.autoWidth();
}

</script>
</body>
</html>
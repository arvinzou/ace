<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>题目</title>

    <link rel="stylesheet" href="${portalPath}/content/common/assets/css/colorbox.css"/>
    <link rel="stylesheet"
          href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
          type="text/css" media="screen"/>

</head>
<jsp:include page="../../common/common.jsp"/>

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
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=69&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                    名称： <input name="name" type="text"
                               style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/evaluatCase/findEvaluatCaseList.do">
                        <i
                                class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>


                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">


                    <%--<button class="btn btn-info" id="btn-view-add"--%>
                    <%--authority="${pageContext.request.contextPath}/evaluatCase/insertEvaluatCase.do">--%>
                    <%--<i--%>
                    <%--class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>--%>
                    <%--</button>--%>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/evaluatCase/updateEvaluatCase.do">
                        <i
                                class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/evaluatCase/deleteEvaluatCaseByEvaluatCaseId.do">
                        <i
                                class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>

                </div>
            </div>
        </div>
    </div>

    <div class="easyui-layout" id="cc" style="width:100%;height:300px;">
        <div data-options="region:'center',border:false,fit:true" id="easyui-center">
            <table id="grid-table"></table>

            <div class="paginationbar"><ul id="grid-pager" class="pagination"></ul></div>
        </div>
        <div id="cc-west" class="easyui-west" data-options="region:'west',split:true" title="测试模板" style="width:200px;">
            <ul id="tt" class="easyui-tree" data-options="
               url:'${pageContext.request.contextPath}/evaluatTpl/getEvaluatTplTreeList.do',
                method: 'get',
                animate: true,
                lines:true,
                onContextMenu: function(e,node){
                    e.preventDefault();
                    $(this).tree('select',node.target);
                    autotreeq(node);
                    $('#mm').menu('show',{
                        left: e.pageX,
                        top: e.pageY
                    });
                }
            "></ul>

        </div>
        <!--
        <div data-options="region:'south',split:true" style="height:50px;"></div>
        <div data-options="region:'east',split:true" title="East" style="width:100px;"></div>
        -->
    </div>

</div>

<div id="mm" class="easyui-menu" style="width:120px;">
    <div onclick="treeappend()" data-options="iconCls:'icon-add'">添加</div>
    <div onclick="treeedit()" data-options="iconCls:'icon-edit'">编辑</div>
    <div onclick="treeremove()" data-options="iconCls:'icon-remove'">删除</div>
    <div class="menu-sep"></div>
    <div onclick="treereload()" data-options="iconCls:'icon-refresh'">刷新</div>
    <div onclick="expand()">展开</div>
    <div onclick="collapse()">收回</div>
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
所属评测模板</span>
            <br>
            <span id="evaluatTplId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
标题</span>
            <br>
            <span id="title">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
选题类型(1单选2多选)</span>
            <br>
            <span id="type">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
内容图片</span>
            <br>
            <span id="cntImg">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
分值</span>
            <br>
            <span id="score">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
答案</span>
            <br>
            <span id="solution">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
顺序</span>
            <br>
            <span id="sort">
</span>
        </div>
    </div>
    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem"><span class="labelItemHeader">
入库日期</span>
            <br>
            <span id="createDate">
</span>
        </div>
    </div>

</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script
        src="${pageContext.request.contextPath}/content/service/evaluatCase/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/evaluatCase/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/evaluatCase/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/evaluatCase/view.js?version=${cfg.version}"></script>
<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
    window.onresize = function () {
        //autoResize();
        setTimeout("autoResize()", 100);
        setTimeout("autoResize()", 1000);
    }

    function autoResize() {
        jQuery('.panel-tool').find('a').on('click', function (e) {
            setTimeout("autoResize()", 1000);
        });
        var h = window.innerHeight - 130;
        if (portalType == '2') {
            h = window.innerHeight - 250;
        }
        $('#cc').layout('resize', {
            width: $(".page-content").width(),
            height: h
        });
        $('#cc').css("height", h);
        $(cfg.grid_selector).jqGrid('setGridHeight', h - 65);
        var display = $('#cc-west').css('display');
        if (display == 'none') {
            $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width() - 10);
        } else {
            $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width() - 185);
        }
        console.log('autoResize:' + h);
        //parent.autoWidth();
    }

    jQuery(function ($) {

        jQuery('.layout-button-left').on('click', function (e) {
            setTimeout("autoResize()", 1000);
        });
    });
</script>

<script src="${pageContext.request.contextPath}/content/portal/js/dict/upload.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>绘图模板-子项</title>
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
                            authority="${pageContext.request.contextPath}/canvasTmplItem/findCanvasTmplItemList.do">
                        <i
                                class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>


                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">


                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/canvasTmplItem/insertCanvasTmplItem.do">
                        <i
                                class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/canvasTmplItem/updateCanvasTmplItem.do">
                        <i
                                class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/canvasTmplItem/deleteCanvasTmplItemByCanvasTmplItemId.do">
                        <i
                                class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>

                </div>
            </div>
        </div>
    </div>

    <table id="grid-table"></table>

    <div class="paginationbar"><ul id="grid-pager" class="pagination"></ul></div>


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
模板ID</span>
            <br>
            <span id="tmplId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
绘制类型</span>
            <br>
            <span id="itemType">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
子项名称</span>
            <br>
            <span id="itemName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
子项编码</span>
            <br>
            <span id="itemCode">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
子项示例值</span>
            <br>
            <span id="itemValue">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
绘制位置x</span>
            <br>
            <span id="positionX">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
绘制位置y</span>
            <br>
            <span id="positionY">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
绘制宽度</span>
            <br>
            <span id="width">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
绘制高度</span>
            <br>
            <span id="height">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
绘制字体</span>
            <br>
            <span id="font">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
绘制颜色</span>
            <br>
            <span id="color">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
备注</span>
            <br>
            <span id="remark">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
状态</span>
            <br>
            <span id="status">
</span>
        </div>
    </div>
    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem"><span class="labelItemHeader">
创建人编号</span>
            <br>
            <span id="createUserId">
</span>
        </div>
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
最后更新人编号</span>
            <br>
            <span id="lastModifyUserId">
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
        src="${pageContext.request.contextPath}/content/service/canvasTmplItem/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/canvasTmplItem/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/canvasTmplItem/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/canvasTmplItem/view.js?version=${cfg.version}"></script>
<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
    window.onresize = function () {
        console.log('autoWidthJqgrid');
        $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        $(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
        //parent.autoWidth();
    }
</script>
</body>
</html>
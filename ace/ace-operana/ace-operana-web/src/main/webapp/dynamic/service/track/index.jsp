<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>指标</title>
</head>
<jsp:include page="../../common/common.jsp"/>
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
                    部门： <input id="cc1" name="deptId"
                               class="easyui-combotree"
                               data-options="url:'${portalPath}/system/selectDepartmentTreeList.do',method:'get',animate: true,
                lines:false"
                               style='width: 200px; line-height: 25px; height: 25px;'/>
                    <a
                            href="javascript:clearQparams()">清除</a>
                    责任人：
                    <input name="liable" class="easyui-combogrid"  data-options="
                    panelWidth : 400,
                    idField : 'USER_ID',
                    textField : 'NAME',
                    url : '${portalPath}/system/selectUsers.do',
                    mode : 'remote',
                    fitColumns : true,
                    method : 'get',
                    columns : [[{
				field : 'NAME',
				title : '姓名',
				width : 120
			}, {
				field : 'DEPARTMENT_NAME',
				title : '所属部门',
				width : 150,
				align : 'right'
			} ]]"/>

                    任务计划时间：
                    <input class="easyui-datebox" name="startDate">
                    至
                    <input class="easyui-datebox" name="endDate">


                    状态：<input
                        class="easyui-combobox" style="width: 100px" name="status"
                        data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=92&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'"/>


                    <button class="btn btn-info" id="btn-search"
                            authority="false">
                      查询  <i
                                class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>


                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">




                </div>
            </div>
        </div>
    </div>

    <table id="grid-table"></table>

    <div id="grid-pager"></div>


</div>



<div id="dialog-message-view" class="hide">


</div>

<jsp:include page="../../common/footer-1.jsp"/>
<script
        src="${pageContext.request.contextPath}/content/service/track/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/track/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/track/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/track/view.js?version=${cfg.version}"></script>
<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight+30);
	parent.autoWidth();
}

</script>
</body>
</html>
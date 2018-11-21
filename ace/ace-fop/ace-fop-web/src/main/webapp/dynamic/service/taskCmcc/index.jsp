<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>taskCmcc</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}"/>
<link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
      rel="stylesheet" type="text/css"/>
<script type="text/javascript">
    var urlid = '${param.id}';
    var edit = false;
</script>
<style>
    .layout-user {
        width: 150px;
        height: 20px;
        float: left;
        margin: 1px 1px 1px;
    }
</style>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light ">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-5 toolbar">
                <button type="button" class="btn  green" id="btn-view-add"
                        authority="${pageContext.request.contextPath}/taskCmcc/insertTaskCmcc.do"></button>
            </div>

            <div class="col-md-7">
                <form action="#" id="fm-search">

                    <div class="input-group" style="width: 250px;float: left">
                        时间:
                        <input class="easyui-datetimebox" name="dateStart"
                               style="width:200px;height:30px;line-height: 30px;">
                    </div>
                    <div class="input-group" style="width: 250px;float: left">
                        至
                        <input class="easyui-datetimebox" name="dateEnd"
                               style="width:200px;height:30px;line-height: 30px;">
                    </div>
                    <div class="input-group" style="width: 250px;float: right">
                        <input type="text"
                               name="taskName"
                               class="form-control"
                               placeholder="请输入任务名称">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn" id="btn-search"
                                    authority="${pageContext.request.contextPath}/taskCmcc/findTaskCmccList.do">
									搜索
							</button>
						</span>
                    </div>
                </form>
            </div>

        </div>

        <table id="grid-table">

        </table>

        <div class="paginationbar">
            <ul id="grid-pager" class="pagination"></ul>
        </div>
    </div>
</div>

<div class="modal fade" role="dialog" id="modal-upload">
    <div class="modal-dialog" role="document" style="width: 830px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">图片上传</h4>
            </div>
            <div class="modal-body">
                <div id="uploader">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" role="dialog" id="modal-detail">
    <div class="modal-dialog" role="document" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">短信详情</h4>
            </div>

            <div class="modal-body">
                <div class="easyui-panel" id="msg-content" style="padding:5px;width:780px;height:100px">

                </div>
                <div style="height:5px"></div>
                <div class="easyui-panel" id="task-content" style="padding:5px;width:780px;height:200px">

                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<jsp:include page="/dynamic/common/footer.jsp"/>

<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"
        type="text/javascript"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>

<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>

<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/export.js?version=${cfg.version}"></script>

<script src="${pageContext.request.contextPath}/content/service/taskCmcc/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/taskCmcc/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/taskCmcc/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/taskCmcc/view.js?version=${cfg.version}"></script>
<%--<script src="${pageContext.request.contextPath}/content/service/question/upload.js?version=${cfg.version}"></script>--%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>学员考勤</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"/>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
<body>

<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light ">
    <div class="portlet-body">
        <div class="row custom-toolbar">
            <form action="#" id="fm-search">
                <div class="col-md-3 ">
                    <%-- <button type="button" class="btn  green" id="btn-view-import"
                             authority="${pageContext.request.contextPath}/attRecord/insertAttRecord">中控数据导入
                     </button>--%>
                    <button type="button" class="btn  green" id="btn-view-export"
                            authority="${pageContext.request.contextPath}/attRecord/insertAttRecord">导出
                    </button>
                </div>
                <div class="col-md-8">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <select id="s-cls-list" name="classId" class="form-control" style="height: 31px;"
                                onchange="setParams('classId',this.value)">
                        </select>
                    </div>

                    <div style="width:40px;float:left;line-height:30px"> 时间</div>
                    <div class="input-group date form_datetime" style="width:20%;float:left;border: 1px solid #efefef;">
                        <input id="p-startDt" type="text" size="16" name="startDate" readonly="" class="form-control"
                               onchange="validateAccTime(this.value)">
                    </div>
                    <span class="input-group-addon" style="width:40px;float:left;"><font
                            style="vertical-align: inherit;"><font
                            style="vertical-align: inherit;"> 至 </font></font></span>
                    <div class="input-group date form_datetime"
                         style="width: 20%;float:left;border: 1px solid #efefef;">
                        <input id="p-endDt" type="text" size="16" name="endDate" readonly="" class="form-control"
                               onchange="validateAccTime(this.value)">
                    </div>

                    <div class="input-group">
                        <input type="text"
                               name="userName"
                               class="form-control"
                               placeholder="请输入姓名">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn" id="btn-search"
                                    authority="${pageContext.request.contextPath}/attRecord/findAttRecordList">
									搜索
							</button>
						</span>
                    </div>

                </div>

            </form>
        </div>

        <table id="grid-table"></table>

        <div class="paginationbar">
            <ul id="grid-pager" class="pagination"></ul>
        </div>
    </div>
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<jsp:include page="/dynamic/common/footer.jsp"/>

<%--查看详情--%>
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 60%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-preview">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>
<%--详情juicer模板--%>
<script id="tpl-preview" type="text/template">
    <div class="form-group">
        <label class="col-md-2 view-label">姓名</label>
        <div class="col-md-10">
            \${data.o.userName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">注册类型</label>
        <div class="col-md-10">
            \${data.o.userTypeName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">采集来源</label>
        <div class="col-md-10">
            \${parseStatus(data.o.status)}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">考勤时间</label>
        <div class="col-md-10">
            \${data.o.attTime}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">定位经度</label>
        <div class="col-md-10">
            \${data.o.longitude}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">定位纬度</label>
        <div class="col-md-10">
            \${data.o.latitude}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">考勤图像</label>
        <div class="col-md-10">
            <img class="photo" src="\${data.o.attPhoto}">
        </div>
    </div>
</script>

<%--数据导出--%>
<div class="modal fade" role="dialog" id="modal-export">
    <div class="modal-dialog" role="document" style="width: 60%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">数据导出</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-export">
                        <form method="post" class="form-horizontal" role="form"
                              action="${pageContext.request.contextPath}/attRecord/exportAttRecord">
                            <label id="export_info" class="view-label hide"></label>

                            <div class="form-group">
                                <label class="col-md-2 view-label">
                                    班次<span style='color:red;font-size:16px;font-weight:800'>*</span>
                                </label>
                                <div class="col-md-7">
                                    <select id="ext-cls-list" name="classId" class="form-control"
                                            style="height: 31px;">
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 view-label">
                                    时间段<span style='color:red;font-size:16px;font-weight:800'>*</span>
                                </label>
                                <div class="col-md-7">
                                    <div class="date form_datetime" style="float:left;border: 1px solid #efefef;">
                                        <input id="ext-startDt" type="text" style="width:175px;" size="16"
                                               name="startDate" readonly="" class="form-control">
                                    </div>
                                    <span class="input-group-addon" style="width:50px;float:left;">
                                        <font style="vertical-align: inherit;">
                                            <font style="vertical-align: inherit;">~</font>
                                        </font>
                                    </span>
                                    <div class="date form_datetime" style="float:left;border: 1px solid #efefef;">
                                        <input id="ext-endDt" type="text" style="width:175px;" size="16"
                                               name="endDate" readonly="" class="form-control">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
                <button type="button" class="btn btn-primary" authority="false">下载excel</button>
            </div>
        </div>
    </div>
</div>

<script id="tpl-cls-option" type="text/template">
    {@each data as item, index}
    <option value="\${item.id}">\${item.name}</option>
    {@/each}
</script>

<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
<%--easyui--%>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>
<%--导出--%>
<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/export.js?version=${cfg.version}"></script>
<%--导入套件--%>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>
<%--custom view js--%>
<script src="${pageContext.request.contextPath}/content/service/attRecord/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/attRecord/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/attRecord/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/attRecord/view.js?version=${cfg.version}"></script>

<%--<script src="${pageContext.request.contextPath}/content/service/attRecord/upload.js?version=${cfg.version}"></script>--%>
<%--权限管理--%>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>

</body>
<style>
    /* css code area*/
    .photo {
        max-width: 600px;
        max-height: 600px;
    }
</style>
</html>
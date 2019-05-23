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
      href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/plugins/kalendae/kalendae.css"
      type="text/css" charset="utf-8">
<script src="${pageContext.request.contextPath}/content/common/plugins/kalendae/kalendae.standalone.js"
        type="text/javascript" charset="utf-8"></script>

/>
<body>

<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light ">
    <div class="portlet-body">
        <div class="row custom-toolbar">
            <form action="#" id="fm-search">
                <div class="col-md-4 toolbar">

                    <button type="button" class="btn  green" id="btn-view-export" authority="false"> 导出工具</button>
                    <button type="button" class="btn  green" id="btn-export-tea" authority="false"> 教职工报表</button>
                    <button type="button" class="btn  green" id="btn-export-stu" authority="false"> 学员报表</button>

                </div>
                <div class="col-md-8">

                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" authority="false"
                                class="btn btn-default active" onclick="setParams('userType','');">全部
                        </button>
                        <button type="button" authority="false"
                                class="btn btn-default" onclick="setParams('userType','student');">学员
                        </button>
                        <button type="button" authority="false"
                                class="btn btn-default" onclick="setParams('userType','teacher');">教职工
                        </button>
                    </div>
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <select id="s-cls-list" name="clsId" class="form-control" style="height: 31px;"
                                onchange="setParams('classId',this.value)">
                        </select>
                    </div>

                    <div style="width:40px;float:left;line-height:30px"> 时间</div>
                    <div class="input-group date form_datetime" style="width:10%;float:left;border: 1px solid #efefef;">
                        <input id="p-startDt" type="text" size="16" name="startDate" readonly="" class="form-control">
                    </div>
                    <span class="input-group-addon" style="width:40px;float:left;"><font
                            style="vertical-align: inherit;"><font
                            style="vertical-align: inherit;"> 至 </font></font></span>
                    <div class="input-group date form_datetime"
                         style="width: 10%;float:left;border: 1px solid #efefef;">
                        <input id="p-endDt" type="text" size="16" name="endDate" readonly="" class="form-control">
                    </div>

                    <div class="input-group" style="padding-right:5px">
                        <input type="text" name="name" class="form-control" style="height: 31px;" placeholder="请输入姓名">
                        <span class="input-group-btn">
                            <button class="btn  btn-default search_btn" id="btn-search"
                                    authority="false">
                                <%--${pageContext.request.contextPath}/attRecord/findAttRecordList--%>
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

<%--学员报表--%>
<div class="modal fade" role="dialog" id="modal-export-stu">
    <div class="modal-dialog" role="document" style="width: 95%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">学员报表</h4>
            </div>
            <div class="modal-body">

                <%--学员考勤报表查询窗体--%>
                <div class="portlet light ">
                    <div class="portlet-body">
                        <div class="row custom-toolbar">
                            <div class="col-md-3">
                                <button id="stu-page-export" type="button" class="btn green hide" authority="false">
                                    导出报表
                                </button>
                            </div>

                            <div class="col-md-9">
                                <form id="fm-search-stu">
                                    <div class="row">
                                        <div class="col-md-3" style="float:left;line-height:30px;">
                                            班次<span style='color:red;font-size:16px;'>*</span>
                                        </div>
                                        <div class="col-md-9 input-group" style="width: 75%;float:left;">
                                            <select id="ext-cls-list-stu" name="classId" class="form-control"
                                                    style="width:275px;height: 31px;">
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row" style="padding-top: 2px">
                                        <div class="col-md-3" style="float:left;line-height:30px">
                                            考勤类别<span style='color:red;font-size:16px;'>*</span>
                                        </div>
                                        <div class="col-md-9 input-group" style="width: 75%;float:left;">
                                            <div class="radio-group-container">
                                                <label>
                                                    <input type="radio" name="stuAttType" value="0" checked>
                                                    <span style="padding:10px">上课考勤</span>
                                                </label>
                                                <label>
                                                    <input type="radio" name="stuAttType" value="1">
                                                    <span style="padding:10px">住宿考勤</span>
                                                </label>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row" style="padding-top: 2px">
                                        <div class="col-md-3" style="float:left;line-height:30px">
                                            查询日期<span style='color:red;font-size:16px;'>*</span>
                                        </div>
                                        <div class="col-md-9 input-group" style="width: 75%;float:left;">
                                            <input name="queryDate" type="text" autocomplete="off"
                                                   class="auto-kal form-control" style="line-height:30px"
                                                   data-kal="mode:'multiple',format:'YYYY-MM-DD',months: 1"/>
                                        </div>
                                        <span class="input-group-btn" style="float: left;">
                                            <button id="stu-report-search" class="btn  btn-default search_btn"
                                                    authority="false" type="submit"> 查询</button>
                                        </span>
                                    </div>

                                </form>
                            </div>

                        </div>

                        <div class="table-scrollable">
                            <table class="table table-hover" id="student-report-table">

                            </table>
                        </div>

                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>

<%--教职工报表--%>
<div class="modal fade" role="dialog" id="modal-export-tea">
    <div class="modal-dialog" role="document" style="width: 95%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">教职工报表</h4>
            </div>
            <div class="modal-body">
                <%--教职工报表查询窗体--%>
                <div class="portlet light ">
                    <div class="portlet-body">
                        <div class="row custom-toolbar">
                            <div class="col-md-3">
                                <button id="tea-page-export" type="button" class="btn green hide" authority="false">
                                    导出报表
                                </button>
                            </div>

                            <div class="col-md-9">
                                <form id="fm-search-tea">
                                    <div style="float:left;line-height:30px">
                                        查询日期<span style='color:red;font-size:16px;'>*</span>
                                    </div>


                                    <div class="input-group" style="width: 75%;float:left;">
                                        <input id="ext-date-tea" name="queryDate" type="text" autocomplete="off"
                                               class="auto-kal form-control" style="line-height:30px"
                                               data-kal="mode:'multiple',format:'YYYY-MM-DD',months: 1"/>
                                    </div>

                                    <span class="input-group-btn" style="float: left;">
                                        <button id="tea-report-search" class="btn  btn-default search_btn"
                                                authority="false" type="submit"> 查询</button>
                                    </span>
                                </form>
                            </div>

                        </div>

                        <div class="table-scrollable">
                            <table class="table table-hover" id="teacher-report-table">

                            </table>
                        </div>

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>

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
                        <form enctype="application/x-www-form-urlencoded" method="post" class="form-horizontal" role="form"
                              action="${pageContext.request.contextPath}/exportExcel/exportAttRecord">
                            <label id="export_info" class="view-label hide"></label>
                            <div class="form-group">
                                <label class="col-md-2 view-label">
                                    身份类别<span style='color:red;font-size:16px;font-weight:800'>*</span>
                                </label>
                                <div class="col-md-7">
                                    <select id="ext-userType" name="userType" class="form-control"
                                            style="width:175px;height: 31px;">
                                        <option value="student">学员</option>
                                        <option value="teacher">教职工</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group cls-select">
                                <label class="col-md-2 view-label">
                                    班次<span style='color:red;font-size:16px;font-weight:800'>*</span>
                                </label>
                                <div class="col-md-7">
                                    <select id="ext-cls-list" name="clsId" class="form-control"
                                            style="width:275px;height: 31px;">
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 view-label">
                                    时间区间<span style='color:red;font-size:16px;font-weight:800'>*</span>
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

<script id="tpl-student-report" type="text/template">
    <thead>
    <tr>
        <th width="10%"> 序号</th>
        <th width="10%"> 姓名</th>
        {@each data.dateArray as date, index}
        <th width="10%"> \${parseDate(date)}</th>
        {@/each}
    </tr>
    </thead>
    <tbody>
    {@each data.report as item, index}
    <tr>
        <td width="10%">\${item.sort}</td>
        <td width="10%">\${item.studentName}</td>
        {@each data.dateArray as date, index}
        <td width="10%">\${item[date]}</td>
        {@/each}

    </tr>
    {@/each}
    </tbody>


</script>

<script id="tpl-teacher-report" type="text/template">
    <thead>
    <tr>
        <th width="10%"> 序号</th>
        <th width="10%"> 姓名</th>
        <th width="10%"> 处室</th>
        {@each data.dateArray as date, index}
        <th width="10%"> \${parseDate(date)}</th>
        {@/each}
    </tr>
    </thead>
    <tbody>
    {@each data.report as item, index}
    <tr>
        <td width="10%">\${item.sort}</td>
        <td width="10%">\${item.name}</td>
        <td width="10%">\${item.unitName}</td>
        {@each data.dateArray as date, index}
        <td width="10%">\${item[date]}</td>
        {@/each}

    </tr>
    {@/each}
    </tbody>


</script>

<script id="tpl-cls-option" type="text/template">
    {@each data as item, index}
    <option value="\${item.id}">\${item.name}</option>
    {@/each}
</script>

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

<%--导入--%>
<div class="modal fade" role="dialog" id="modal-upload">
    <div class="modal-dialog" role="document" style="width: 75%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" authority="false" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Access数据库文件导入</h4>
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
<script src="${pageContext.request.contextPath}/content/service/attRecord/upload.js?version=${cfg.version}"></script>
<%--权限管理--%>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>
<%--page excel export--%>
<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>

<script type="text/javascript">

    function startFormat(id) {
        var obj = $('#' + 'p-startDate');
        alert(obj.val());
    }
</script>
</body>
<style>
    /* css code area*/
    .photo {
        max-width: 600px;
        max-height: 600px;
    }
</style>
</html>

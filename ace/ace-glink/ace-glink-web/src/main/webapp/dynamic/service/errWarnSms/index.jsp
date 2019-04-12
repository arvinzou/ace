<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<!--[if IE 8]>
<html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]>
<html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <title>故障报警</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>

    <link rel="stylesheet" href="css/style.css">
    <%--sweetalert--%>
    <script src="${pageContext.request.contextPath}/content/common/js/sweetalert/js/sweet-alert.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/content/common/js/sweetalert/css/sweet-alert.css">
    <%--datatables--%>
    <link href="${portalPath}/content/common/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${portalPath}/content/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css"
          rel="stylesheet" type="text/css"/>
    <%--switch--%>
    <link href="${portalPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-5">
                <a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>
            </div>

            <div class="col-md-7">

                <form id="fm-search">
                    <%--分区筛选--%>
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        分区 <input type="text" name="subareaCode" class="form-control" maxlength="50"
                                  placeholder="请选择分区" style="width:225px;height: 30px;line-height: 30px">
                        <%--<a href="javascript:clearSubareaCode();">清除</a>--%>
                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入模板名称">
                        <span class="input-group-btn">
                            <button class="btn  btn-default search_btn"
                                    type="submit">
                                    搜索
                            </button>
                        </span>
                    </div>
                </form>
            </div>

        </div>


        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>

                    <th width="20%"> 模板名称</th>
                    <th width="30%"> 模板内容</th>
                    <th width="10%"> 分区名称</th>
                    <th width="10%"> 状态</th>
                    <th width="15%">操作</th>
                </tr>
                </thead>
                <tbody id="page-list">

                </tbody>
            </table>
        </div>
        <div class="paginationbar">
            <ul class="pagination" id="pagination1"></ul>
        </div>

    </div>

</div>


<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${item.smsName}</td>
        <td> \${item.smsContent}</td>
        <td> \${item.subareaName}</td>
        <td>
            {@if item.status==1}
            <span class="label label-lg label-success">\${parseStatus(item.status)}</span>
            {@else}
            <span class="label label-lg label-danger">\${parseStatus(item.status)}</span>
            {@/if}
        </td>
        <td>
            ﻿<a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>
            <a href="javascript:del('\${item.id}');">删除</a>

            <a href="#" data-toggle="modal" data-id="\${item.subareaCode}" data-title="\${item.name}"
               data-target="#modal-someone">送报人</a>
            <%--<a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"--%>
            <%--data-target="#modal-scheduler">调度规则</a>--%>
        </td>
    </tr>
    {@/each}
</script>

<%--调度规则--%>
<div class="modal fade" role="dialog" id="modal-scheduler">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">管理调度规则</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-scheduler">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script id="tpl-scheduler" type="text/template">

</script>

<%--送报人--%>
<div class="modal fade" role="dialog" id="modal-someone" data-keyboard="false">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">管理送报人</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-someone">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script id="tpl-someone" type="text/template">
    <div class="portlet light bordered">
        <div class="portlet-title">
            <div class="caption font-dark">
                <i class="icon-settings font-dark"></i>
                <span class="caption-subject bold uppercase"> 送报人列表</span>
            </div>
            <div class="actions"></div>
        </div>
        <div class="portlet-body">
            <div class="table-toolbar">
                <div class="row">
                    <div class="col-md-6">
                        <div class="btn-group">
                            <button id="btn-add-someone" class="btn sbold green">
                                添加 <i class="fa fa-plus"></i>
                            </button>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="btn-group pull-right">

                        </div>
                    </div>
                </div>
            </div>
            <table id="d-tb-someone"
                   class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
                <thead>
                <tr>
                    <th width="15%" class="sorting"> 姓名</th>
                    <th width="15%" class="sorting"> 手机</th>
                    <th width="15%" class="sorting"> 单位</th>
                    <th width="15%" class="sorting"> 分区</th>
                    <th width="10%" class="sorting"> 通知状态</th>
                    <th width="10%"> 操作</th>
                </tr>
                </thead>
                <tbody>
                {@each data as item, index}
                <tr class="gradeX odd" role="row">
                    <td> \${item.name}</td>
                    <td> \${item.mobile}</td>
                    <td> \${item.unitName}</td>
                    <td> \${item.subareaName}</td>
                    <td>
                        {@if item.status == '1'}
                        <span class="label label-sm label-success"> \${parseSomeoneStatus(item.status)}</span>
                        {@else}
                        <span class="label label-sm label-danger"> \${parseSomeoneStatus(item.status)}</span>
                        {@/if}
                    </td>
                    <td>
                        <a href="javascript:editSomeone('\${item.id}','\${item.subareaCode}')">编辑</a>
                        <a href="javascript:delSomeone('\${item.id}','\${item.subareaCode}')">删除</a>
                    </td>
                </tr>
                {@/each}
                </tbody>
            </table>
        </div>
    </div>
</script>
<%--新增送报人--%>
<div class="modal fade" role="dialog" id="modal-add-someone">
    <div class="modal-dialog" role="document" style="width: 45%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增送报人</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-add-someone">
                        <form class="form-horizontal" role="form" style="width: 90%">
                            <div class="form-body">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">
                                        姓名
                                        <span class="required" aria-required="true"> * </span>
                                    </label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="name" maxlength="50"
                                               placeholder="请输入姓名（建议字数在14个字以内，不超过50个字)">
                                        <span class="help-block"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">
                                        手机
                                        <span class="required" aria-required="true"> * </span>
                                    </label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="mobile" maxlength="50"
                                               placeholder="请输入姓名（建议字数在14个字以内，不超过50个字)">
                                        <span class="help-block"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">
                                        单位
                                    </label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="unitName" maxlength="50"
                                               placeholder="请输入备注（建议字数在14个字以内，不超过50个字)">
                                        <span class="help-block"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">
                                        通知状态
                                    </label>
                                    <div class="col-md-10 sw-content">
                                        <input name="status" type="checkbox" class="make-switch"
                                               checked="" data-on-color="success" data-off-color="danger">

                                        <span class="help-block"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-actions">
                                <div class="row">
                                    <div class="col-md-offset-5 col-md-7">
                                        <button class="btn green" type="submit" style="width:50%">保存</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<%--修改送报人--%>
<div class="modal fade" role="dialog" id="modal-upd-someone">
    <div class="modal-dialog" role="document" style="width: 45%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增送报人</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-upd-someone">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script id="tpl-fm-upd-someone" type="text/template">
    <form class="form-horizontal" role="form" style="width: 90%">
        <div class="form-body">
            <div class="form-group hide">
                <label class="col-md-2 control-label">ID</label>
                <div class="col-md-10">
                    <input type="text" name="id" class="form-control" maxlength="50" value="\${data.o.id}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    姓名
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-10">
                    <input type="text" name="name" class="form-control" maxlength="50"
                           value="\${data.o.name}"
                           placeholder="请输入姓名（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    手机
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="mobile" maxlength="50"
                           value="\${data.o.mobile}"
                           placeholder="请输入姓名（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    单位
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="unitName" maxlength="50"
                           value="\${data.o.unitName}"
                           placeholder="请输入备注（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    通知状态
                </label>
                <div class="col-md-10 sw-content">
                    <input name="status" type="checkbox" class="make-switch"
                           checked="" data-on-color="success" data-off-color="danger">
                    <span class="help-block"></span>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <div class="row">
                <div class="col-md-offset-5 col-md-7">
                    <button class="btn green" type="submit" style="width:50%">保存</button>
                </div>
            </div>
        </div>
    </form>
</script>
﻿
<%--查看--%>
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 75%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
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
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<%--查看render模板--%>
<script id="tpl-preview" type="text/template">
    <div class="form-group hide">
        <label class="col-md-2 view-label">主键</label>
        <div class="col-md-10">
            \${data.o.id}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">模板名称</label>
        <div class="col-md-10">
            \${data.o.smsName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">模板内容</label>
        <div class="col-md-10">
            \${data.o.smsContent}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">分区名称</label>
        <div class="col-md-10">
            \${data.o.subareaName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">状态 </label>
        <div class="col-md-10">
            \${parseStatus(data.o.status)}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">创建日期</label>
        <div class="col-md-10">
            \${data.o.createDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">最新更新日期</label>
        <div class="col-md-10">
            \${data.o.updateDate}
        </div>
    </div>
</script>
<style>

</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<%--datatable--%>
<script src="${portalPath}/content/common/assets/global/scripts/datatable.js" type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/datatables/datatables.min.js"
        type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js"
        type="text/javascript"></script>
<%--easyui--%>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<%--switch--%>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js?version=${cfg.version}"></script>

<%--act.js--%>
<script src="js/act.js?v=${cfg.version}"></script>
</html>

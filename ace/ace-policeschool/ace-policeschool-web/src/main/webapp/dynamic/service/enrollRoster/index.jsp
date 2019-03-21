<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>报名管理</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}"/>
<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>

<body>

<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light ">
    <div class="portlet-body">
        <div class="row custom-toolbar">
            <form action="#" id="fm-search">
                <div class="col-md-3 toolbar">
                    <button type="button" class="btn green" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/enrollRoster/insertEnrollRoster">添加
                    </button>
                    <button type="button" class="btn green" id="btn-view-import"
                            authority="false">批量导入
                    </button>
                </div>
                <div class="col-md-8">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" authority="false" class="btn btn-default"
                                onclick="setParams('status','');">全部
                        </button>
                        <button type="button" authority="false" class="btn btn-default"
                                onclick="setParams('status','1');">待注册
                        </button>
                        <button type="button" authority="false" class="btn btn-default"
                                onclick="setParams('status','2');">已注册
                        </button>
                    </div>
                    <div class="input-group" style="float:left;padding-right:5px">
                        <label class="control-label" style="float: left;padding-top: 3px">籍贯</label>
                        <input id="ctrl-areaCode" name="areaCode" class="easyui-combotree form-control"
                               data-options="url:'${portalPath}/system/selectProvinceTreeList.do?id=4307', method:'get', animate: true, lines:false,"
                               style='width: 150px; line-height: 30px; height: 30px;'>
                        <a href="javascript:clearAreaCode()">清除</a>
                    </div>
                    <div class="input-group " style="float:left;padding-right:5px">
                        <label class="control-label" style="float: left;padding-top: 3px">班次</label>
                        <select name="clsId" id="cls-list-search" class="form-control"
                                style="width: 220px; height: 30px;line-height: 30px">
                        </select>
                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入姓名">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn" id="btn-search"
                                    authority="${pageContext.request.contextPath}/enrollRoster/findEnrollRosterList">
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

<script id="tpl-cls-option" type="text/template">
    {@each data as item, index}
    <option value="\${item.id}">\${item.name}</option>
    {@/each}
</script>

<script id="tpl-del-cls-option" type="text/template">
    {@each data as item, index}
    <option value="\${item.clsId}">\${item.clsViewName}</option>
    {@/each}
</script>

<%--批量导入--%>
<div class="modal fade" role="dialog" id="modal-import">
    <div class="modal-dialog" role="document" style="width: 75%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" authority="false" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">批量导入</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-5">
                        <label class="col-md-2 control-label">籍贯<span style='color:red;'>*</span></label>
                        <div class="col-md-10">
                            <input id="ctrl-areaCode-import" name="areaCode" class="easyui-combotree form-control"
                                   data-options="url:'${portalPath}/system/selectProvinceTreeList.do?id=4307', method:'get', animate: true, lines:false,"
                                   style='width: 275px; line-height: 30px; height: 30px;'>
                        </div>
                    </div>
                    <div class="col-md-7">
                        <label class="col-md-3 control-label">班次</label>
                        <div class="col-md-9" style="float: left">
                            <select name="clsId" id="cls-list-import" class="form-control"
                                    style="width: 275px; height: 31px;line-height: 30px"
                                    onchange="importInit()">
                            </select>
                        </div>
                    </div>
                </div>

                <div id="uploader">
                </div>
                <div style="margin:5px">
                    <a href="roster_template.xls" style="color:red">下载模板</a>.<br>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>

<%--查看详情--%>
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 75%;">
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
            \${data.o.name}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">性别</label>
        <div class="col-md-10">
            \${rsd(data.o.sex,'01')}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">籍贯</label>
        <div class="col-md-10">
            \${data.o.areaCodeName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">警号</label>
        <div class="col-md-10">
            \${data.o.badgeNum}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">身份证号</label>
        <div class="col-md-10">
            \${data.o.idCard}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">出生年月</label>
        <div class="col-md-10">
            \${formatDate(data.o.birthDate)}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">班次</label>
        <div class="col-md-10">
            \${data.o.clsName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">单位</label>
        <div class="col-md-10">
            \${data.o.workUnitName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">职务</label>
        <div class="col-md-10">
            \${data.o.postName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">政治面貌</label>
        <div class="col-md-10">
            \${rsd(data.o.political,'158')}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">毕业院校</label>
        <div class="col-md-10">
            \${data.o.college}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">手机号</label>
        <div class="col-md-10">
            \${data.o.mobile}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">状态</label>
        <div class="col-md-10">
            \${parseStatus(data.o.regStatus)}
        </div>
    </div>
</script>

<%--表单提交--%>
<script type="text/javascript"
        src="/portal/content/common/js/jquery.form.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
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
<%--导入--%>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>


<script src="${pageContext.request.contextPath}/content/service/enrollRoster/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/enrollRoster/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/enrollRoster/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/enrollRoster/view.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/enrollRoster/upload.js?version=${cfg.version}"></script>

<%--权限管理--%>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>

</body>
<style>
    /* css code area*/
</style>
</html>

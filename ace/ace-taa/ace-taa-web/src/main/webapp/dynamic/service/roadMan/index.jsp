<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>路长</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}"/>
<link href="${portalPath}/content/common/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
<link href="${portalPath}/content/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${portalPath}/content/common/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
<body>

<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light ">
    <div class="portlet-body">
        <div class="row custom-toolbar">
            <form action="#" id="fm-search">
                <div class="col-md-6 toolbar">

                    <button type="button" class="btn  green" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/roadMan/insertRoadMan">添加
                    </button>
										<button type="button" class="btn  green" id="btn-view-importXls"
														authority="false">Excel导入
										</button>

                </div>
                <div class="col-md-6">

                    <div class="input-group" style="float:left;padding-right:10px">
                        行政区划 <input class="easyui-combotree" name="areaCode" data-options="url:﻿'${portalPath}/system/selectProvinceTreeList.do',method:'get',label:'',labelPosition:'top'" style="width:200px;﻿line-height: 30px;height: 30px;">
                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="name"
                               class="form-control"
                               placeholder="请输入姓名">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn" id="btn-search"
                                    authority="${pageContext.request.contextPath}/roadMan/findRoadManList">
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
    <div class="modal-dialog" role="document" style="width: 100%;">
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

<div class="modal fade" role="dialog" id="modal-preview-gps">
    <div class="modal-dialog" role="document" style="width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body" id="fm-preview-gps">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade"  role="dialog" id="modal-upload">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"  authority="false" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Excel导入</h4>
            </div>
            <div class="modal-body">
                <div id="uploader">
                </div>
                <div style="margin:5px">
                    <a href="roadMan.xls" style="color:red">下载模板</a>.<br>
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



    <div class="portlet light ">
        <div class="portlet-body">
            <div class="table-toolbar">
                <div class="row">
                    <div class="col-md-6">
                        <div class="btn-group">
                            <button id="btn-add" class="btn sbold green" onclick="importXls('\${data.o.id}')">添加

                            </button>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="btn-group pull-right">
                            <button class="btn green  btn-outline dropdown-toggle" data-toggle="dropdown">操作
                                <i class="fa fa-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-right">
                                <li>
                                    <a href="javascript:delTable('\${data.o.id}');">
                                        <i class="fa fa-del"></i> 删除 </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-bordered table-hover table-checkable order-column" id="list1">
                <thead>
                <tr>
                    <th width="5%">
                        <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                            <input type="checkbox" class="group-checkable" data-set="#list1 .checkboxes" />
                            <span></span>
                        </label>
                    </th>
                    <th width="25%">路段名称</th>
                    <th width="10%">县区</th>
                    <th width="10%">路段开始</th>
                    <th width="10%">路段截止</th>
                    <th width="10%">标号开始</th>
                    <th width="10%">标号截止</th>
                    <th width="10%">路长</th>
                    <th width="10%">操作</th>
                </tr>
                </thead>
                <tbody>

                {@each data.o as item, index}
                <tr>
                    <td>
                        <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                            <input type="checkbox" class="checkboxes" value="\${item.id}" />
                            <span></span>
                        </label>
                    </td>
                    <td>\${item.name}</td>
                    <td>\${item.areaName}</td>
                    <td>\${item.startName}</td>
                    <td>\${item.endName}</td>
                    <td>\${item.startNo}</td>
                    <td>\${item.endNo}</td>
                    <td>\${item.roadManName}</td>
                    <td><a href="javascript:previewGPS('\${item.id}')">GPS数据</a>  <a href="javascript:previewMap('\${item.id}')">地图</a></td>
                </tr>
                {@/each}

                </tbody>
            </table>
        </div>
    </div>


</script>

<script id="tpl-preview-gps" type="text/template">



<div class="portlet light ">
    <div class="portlet-body">
        <div class="table-toolbar">
            <div class="row">
                <div class="col-md-6">
                    <div class="btn-group">

                    </div>
                </div>
                <div class="col-md-6">
                    <div class="btn-group pull-right">
                        <button class="btn green  btn-outline dropdown-toggle" data-toggle="dropdown">操作
                            <i class="fa fa-angle-down"></i>
                        </button>
                        <ul class="dropdown-menu pull-right">
                            <li>
                                <a href="javascript:delTableGps('\${data.id}');">
                                    <i class="fa fa-del"></i> 删除 </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <table class="table table-striped table-bordered table-hover table-checkable order-column" id="list2">
            <thead>
            <tr>
                <th width="5%">
                    <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                        <input type="checkbox" class="group-checkable" data-set="#list2 .checkboxes" />
                        <span></span>
                    </label>
                </th>
                <th width="25%">纬度</th>
                <th width="25%">经度</th>
                <th width="25%">采集人</th>
                <th width="25%">采集时间</th>

            </tr>
            </thead>
            <tbody>

            {@each data.o as item, index}
            <tr>
                <td>
                    <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                        <input type="checkbox" class="checkboxes" value="\${item.id}" />
                        <span></span>
                    </label>
                </td>
                <td>\${item.latitude}</td>
                <td>\${item.longitude}</td>
                <td>\${item.createUserName}</td>
                <td>\${item.createDate}</td>


            </tr>
            {@/each}

            </tbody>
        </table>
    </div>
</div>


</script>
<script src="${portalPath}/content/common/assets/global/scripts/datatable.js" type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>

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

<script src="${pageContext.request.contextPath}/content/service/roadMan/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/roadMan/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/roadMan/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/roadMan/view.js?version=${cfg.version}"></script>

<%--权限管理--%>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>

<script src="${pageContext.request.contextPath}/content/service/roadMan/upload.js?version=${cfg.version}"></script>
</body>
<style>
    /* css code area*/
</style>
</html>
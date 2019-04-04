<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>路段</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}"/>

<body>

<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light ">
    <div class="portlet-body">
        <div class="row custom-toolbar">
            <form action="#" id="fm-search">
                <div class="col-md-6 toolbar">

                    <button type="button" class="btn  green" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/roadSection/insertRoadSection">添加
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
                               placeholder="请输入路长姓名">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn" id="btn-search"
                                    authority="${pageContext.request.contextPath}/roadSection/findRoadSectionList">
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
            {@if data.o.headimgurl!='' && data.o.headimgurl!=null && data.o.headimgurl!=undefined}
            <img src="\${data.o.photoUrl}" class="cover"/>
            {@else}
            <img src="${pageContext.request.contextPath}/content/common/img/default_header.png" class="cover"/>
            {@/if}
            <a>\${data.o.name}</a>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">手机号码</label>
        <div class="col-md-10">
            \${data.o.mobile}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">身份证号码</label>
        <div class="col-md-10">
            \${data.o.idCard}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">介绍</label>
        <div class="col-md-10">
            \${data.o.introduce}
        </div>
    </div>
</script>

<div class="modal fade" role="dialog" id="moda2-preview">
    <div class="modal-dialog" role="document" style="width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm2-preview">
                        <input type="text" class="form-control" style="width:450px" name="roadSectionId" maxlength="50">
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
<script id="tp2-preview" type="text/template">

    <div class="portlet light ">
        <div class="portlet-body">
            <div class="table-toolbar">
                <div class="row">
                    <div class="col-md-6">
                        <div class="btn-group">
                            <button id="btn-add" class="btn sbold green" onclick="addBranchRoadMan();">添加</button>

                        </div>
                    </div>

                </div>
            </div>
            <table class="table table-striped table-bordered table-hover table-checkable order-column" id="list2">
                <thead>
                <tr>

                    <th width="25%">行政区划</th>
                    <th width="15%">姓名</th>
                    <th width="15%">手机号</th>
                    <th width="15%">单位名称</th>
                    <th width="15%">职务名称</th>

                    <th width="15%">操作</th>
                </tr>
                </thead>
                <tbody>

                {@each data.o as item, index}
                <tr>

                    <td>\${item.areaName}</td>
                    <td>\${item.name}</td>
                    <td>\${item.mobile}</td>
                    <td>\${item.orgName}</td>
                    <td>\${item.postName}</td>
                    <td>
                        <a href="javascript:editTab('\${item.id}')">编辑</a>
                        <a href="javascript:findTab('\${item.id}')">查看</a>
                        <a href="javascript:delTab('\${item.id}','\${item.roadSectionId}')">刪除</a>
                    </td>
                </tr>
                {@/each}

                </tbody>
            </table>
        </div>
    </div>


</script>
<%--分路长添加--%>
<div class="modal fade" role="dialog" id="modal-add">
    <div class="modal-dialog" role="document" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加分路长</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-preview1">
                        <form class="form-horizontal" id="fm-add" role="form">
                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    行政区域
                                    <span class="required" aria-required="true"> * </span>
                                </label>
                                <div class="col-md-5">

                                    <input class="easyui-combotree" name="areaCode" id="areaCode"
                                           data-options="url:﻿'${portalPath}/system/selectProvinceTreeList.do',method:'get',label:'',labelPosition:'top'"
                                           style="width:200px;﻿line-height: 30px;height: 30px;">
                                    <span class="help-block"></span>
                                </div>
                            </div>

                            <input type="hidden" class="form-control" style="width:450px" id="roadSectionId"
                                   name="roadSectionId" maxlength="50">
                            <input type="hidden" class="form-control" id="roadsId" name="roadsId"/>


                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    姓名
                                    <span class="required" aria-required="true"> * </span>
                                </label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" style="width:450px" id="name" name="name"
                                           maxlength="20">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    手机号
                                    <span class="required" aria-required="true"> * </span>
                                </label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" style="width:450px" id="mobile"
                                           name="mobile"
                                           maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    单位名称
                                </label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" style="width:450px" id="orgName"
                                           name="orgName"
                                           maxlength="50">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    职位名称
                                </label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" style="width:450px" id="postName"
                                           name="postName"
                                           maxlength="50">
                                    <span class="help-block"></span>
                                </div>
                            </div>

                                <div class="row">
                                    <div class="col-md-offset-3 col-md-3">
                                        <button class="btn btn-primary" type="button" style="width:30%" id="addRoadMan"
                                                onclick="initAddModal();">保存
                                        </button>
                                    </div>

                                </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">
                                    关闭
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--分路长修改--%>
<div class="modal fade" role="dialog" id="modal-update">
    <div class="modal-dialog" role="document" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改分路长</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm4-preview">
                        <form class="form-horizontal" id="fm-update" role="form">
                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    行政区域
                                    <span class="required" aria-required="true"> * </span>
                                </label>
                                <div class="col-md-5">
                                    <input class="easyui-combotree" name="areaCode" id="areaCode1"
                                           data-options="url:﻿'${portalPath}/system/selectProvinceTreeList.do',method:'get',label:'',labelPosition:'top'"
                                           style="width:200px;﻿line-height: 30px;height: 30px;">
                                    <span class="help-block"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    姓名
                                    <span class="required" aria-required="true"> * </span>
                                </label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" style="width:450px" name="name"
                                           maxlength="20" id="name1">
                                    <input type="hidden" class="form-control" name="id" id="id1">
                                    <input type="hidden" class="form-control" name="status" id="status1">
                                    <input type="hidden" class="form-control" name="createDate" id="createDate1">
                                    <input type="hidden" class="form-control" name="roadSectionId" id="roadSectionId1">


                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    手机号
                                    <span class="required" aria-required="true"> * </span>
                                </label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" style="width:450px" name="mobile"
                                           id="mobile1"
                                           maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    单位名称
                                </label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" style="width:450px" name="orgName"
                                           maxlength="50" id="orgName1">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    职位名称
                                </label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" style="width:450px" name="postName"
                                           maxlength="50" value="\${data.o.postName}" id="postName1">
                                    <span class="help-block"></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-offset-3 col-md-3">
                                    <button class="btn btn-primary" type="button" style="width:30%" id="updateRoadMan"
                                            onclick="initUpdateModal('\${data.o.id}');">保存
                                    </button>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">
                                    关闭
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%--分路长详情--%>
<div class="modal fade" role="dialog" id="moda3-preview">
    <div class="modal-dialog" role="document" style="width: 75%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详情</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm3-preview">
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
<script id="tp3-preview" type="text/template">
    <div class="form-group">
        <label class="col-md-2 view-label">
            行政区域
            <span class="required" aria-required="true">  </span>
        </label>
        <div class="col-md-5">
            \${data.o.areaName}
            <span class="help-block"></span>
        </div>
    </div>


    <div class="form-group">
        <label class="col-md-2 view-label">
            姓名
            <span class="required" aria-required="true">  </span>
        </label>
        <div class="col-md-5">
            \${data.o.name}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">
            手机号
            <span class="required" aria-required="true">  </span>
        </label>
        <div class="col-md-5">
            \${data.o.mobile}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">
            单位名称
        </label>
        <div class="col-md-5">

            \${data.o.orgName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">
            职位名称
        </label>
        <div class="col-md-5">

            \${data.o.postName}
        </div>
    </div>
</script>

<%--easyui--%>
<script src="${portalPath}/content/common/assets/global/scripts/datatable.js" type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/datatables/datatables.min.js"
        type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js"
        type="text/javascript"></script>
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

<script src="${pageContext.request.contextPath}/content/service/roadSection/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/roadSection/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/roadSection/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/roadSection/view.js?version=${cfg.version}"></script>

<%--权限管理--%>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>

</body>
<style>
    /* css code area*/
</style>
</html>
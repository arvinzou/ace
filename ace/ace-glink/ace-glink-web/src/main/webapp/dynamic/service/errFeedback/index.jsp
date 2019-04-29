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
    <%--qq map--%>
    <script charset="utf-8" src="https://map.qq.com/api/js?v=2.exp&key=ULWBZ-54PKS-HBMOL-6YWJF-KMKY6-2XBBB"></script>
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-3">
                <%--<a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>--%>
            </div>

            <div class="col-md-9">

                <form id="fm-search">
                    <%--分区--%>
                    <div class="input-group" style="float:left;padding-right:5px">
                    </div>
                    <%--站点--%>
                    <div class="input-group" style="float:left;padding-right:5px">
                    </div>
                    <%--节点--%>
                    <div class="input-group" style="float:left;padding-right:5px">
                    </div>

                    <%--故障类型--%>

                    <label style="float:left;padding-right:5px;line-height: 31px;">故障类型：</label>
                    <div class="btn-group" role="group" style="float:left;padding-right:20px;height: 36px;">
                        <select name="errType" id="errType" class="form-control"
                                onchange="setParams('errType',this.value)">

                        </select>
                    </div>
                    <%--处理状态--%>
                    <label style="float:left;padding-right:5px;line-height: 31px;">状态：</label>
                    <div class="btn-group" role="group" style="float:left;padding-right:20px;height: 36px;">
                        <select name="status" id="status" class="form-control"
                                onchange="setParams('status',this.value)">
                            <option value="">全部</option>
                            <option value="0">未读</option>
                            <option value="1">已读</option>
                        </select>
                    </div>
                    <%--搜索--%>
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入建筑物名称/设备名称/设备编号">
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
                    <th width="10%"> 故障编号</th>
                    <th width="10%"> 分区名称</th>
                    <th width="10%"> 站点名称</th>
                    <th width="10%"> 节点名称</th>
                    <th width="10%"> 建筑名称</th>
                    <th width="10%"> 故障类型</th>
                    <th width="10%"> 故障时间</th>
                    <th width="10%"> 故障内容</th>
                    <th width="10%"> 故障回路数</th>
                    <th width="10%">操作</th>
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

<script id="tpl-errType" type="text/template">
    <%-- <button type="button" class="btn btn-default" onclick="setParams('errType','');">故障类型</button>
     {@each data['180'] as item, index}
     {@if item.CODE!=''}
     <button type="button" class="btn btn-default" onclick="setParams('errType','\${item.CODE}');">\${item.NAME}</button>
     {@/if}
     {@/each}--%>
    <option value="">全部</option>
    {@each data['180'] as item, index}
    {@if item.CODE!=''}
    <option value="\${item.CODE}">\${item.NAME}</option>
    {@/if}
    {@/each}

</script>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${item.errCode}</td>
        <td> \${item.subareaName}</td>
        <td> \${item.stationName}</td>
        <td> \${item.nodeName}</td>
        <td> \${item.buildingName}</td>
        <td> \${rsd(item.errType,'180')}</td>
        <td> \${item.errDate}</td>
        <td> \${item.errContent}</td>
        <td> \${item.errLoopNum}</td>
        <td>
            {@if item.status == '0'}
            <a href="javascript:updateStatus('\${item.id}','1');">未读</a>
            {@else}
            <a href="javascript:updateStatus('\${item.id}','0');">已读</a>
            {@/if}

            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>
        </td>
    </tr>
    {@/each}
</script>
﻿
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 80%;">
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
<script id="tpl-preview" type="text/template">
    <div class="portlet light">
        <div class="portlet-title">
            <div class="caption ">
                <i class="icon-share "></i>
                <span class="caption-subject bold uppercase"> 基本信息</span>
            </div>
        </div>
        <div class="portlet-body">
            <div class="form-group hide">
                <label class="col-md-2 view-label"> 主键:</label>
                <div class="col-md-10">
                    \${data.o.id}
                </div>
            </div>
            <div class="row">
                <label class="col-md-2 view-label"> 故障编号:</label>
                <div class="col-md-10">
                    \${data.o.errCode}
                </div>
            </div>
            <div class="row">
                <label class="col-md-2 view-label"> 分区名称:</label>
                <div class="col-md-10">
                    \${data.o.subareaName}
                </div>
            </div>
            <div class="row">
                <label class="col-md-2 view-label"> 站点名称:</label>
                <div class="col-md-10">
                    \${data.o.stationName}
                </div>
            </div>
            <div class="row">
                <label class="col-md-2 view-label"> 节点名称:</label>
                <div class="col-md-10">
                    \${data.o.nodeName}
                </div>
            </div>
            <div class="row">
                <label class="col-md-2 view-label"> 建筑物名称:</label>
                <div class="col-md-10">
                    \${data.o.buildingName}
                </div>
            </div>
            <div class="row">
                <label class="col-md-2 view-label"> 故障类型:</label>
                <div class="col-md-10">
                    \${rsd(data.o.errType,'180')}
                </div>
            </div>
            <div class="row">
                <label class="col-md-2 view-label"> 故障时间:</label>
                <div class="col-md-10">
                    \${data.o.errDate}
                </div>
            </div>
            <div class="row">
                <label class="col-md-2 view-label"> 故障内容:</label>
                <div class="col-md-10">
                    \${data.o.errContent}
                </div>
            </div>
            <div class="row">
                <label class="col-md-2 view-label"> 故障回路数:</label>
                <div class="col-md-10">
                    \${data.o.errLoopNum}
                </div>
            </div>
            <div class="row hide">
                <label class="col-md-2 view-label"> 状态:</label>
                <div class="col-md-10">
                    \${parseStatus(data.o.status)}
                </div>
            </div>
        </div>
    </div>
    <div class="portlet light">
        <div class="portlet-title">
            <div class="caption">
                <i class="icon-share"></i>
                <span class="caption-subject bold uppercase"> GIS地图</span>
            </div>
        </div>
        <div class="portlet-body body">
            <div id="map-container" style="min-width:600px;min-height:550px;">

            </div>
        </div>
    </div>
</script>

<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<%----%>
<script src="js/act.js?v=${cfg.version}"></script>
<style>
    #fm-preview .form-group {
        padding-top: 10px;
    }
</style>
</html>

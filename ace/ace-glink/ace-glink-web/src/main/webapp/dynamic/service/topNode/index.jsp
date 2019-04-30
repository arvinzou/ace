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
    <title>节点管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet"
          href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-3">
                <a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>
                <button type="button" class="btn  green" id="btn-view-import"
                        authority="false">批量导入
                </button>
                <a href="group/index.jsp?id=${param.id}" class="btn green">节点分组</a>
            </div>


            <div class="col-md-5" style="float: right;">
                <form id="fm-search">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <label style="float:left;padding-right:5px;line-height: 31px;">站点：</label>
                        <div class="btn-group" role="group" style="float:left;padding-right:20px;">
                            <select id="subStation" name="stationCode" class="form-control" style="height: 31px;"
                                    onchange="setParams('stationCode',this.value)">
                            </select>
                        </div>
                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入节点名称和节点编号">
                        <span class="input-group-btn">
                            <button class="btn  btn-default search_btn" type="submit">
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
                    <th width="10%"> 节点编号</th>
                    <th width="10%"> 节点名称</th>
                    <%--<th width="10%"> 节点描述</th>--%>
                    <%--<th width="10%"> 详细地址</th>--%>
                    <%--<th width="10%"> IPV4地址</th>--%>
                    <%--<th width="10%"> IPV6地址</th>--%>
                    <%--<th width="10%"> 端口号</th>--%>
                    <%--<th width="10%"> 分辨率-宽</th>--%>
                    <%--<th width="10%"> 分辨率-高</th>--%>
                    <%--<th width="10%"> mac地址</th>--%>
                    <th width="10%"> 控制器数量</th>
                    <th width="10%"> 建筑物</th>

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
<script id="station-list" type="text/template">
    {@each data as item, index}
    <option value="\${item.code}">\${item.name}</option>
    {@/each}
</script>
﻿
<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${item.code}</td>
        <td> \${item.name}</td>
        <%--<td> \${item.depict}</td>--%>
        <%--<td> \${item.address}</td>--%>
        <%--<td> \${item.ipv4}</td>--%>
        <%--<td> \${item.ipv6}</td>--%>
        <%--<td> \${item.port}</td>--%>
        <%--<td> \${item.resolutionWidth}</td>--%>
        <%--<td> \${item.resolutionHeight}</td>--%>
        <%--<td> \${item.macAddr}</td>--%>
        <td> \${item.ctrlNum}</td>
        <td> \${item.topBuilding?item.topBuilding.name:''}</td>
        <td>
            {@if item.status==0}
            <span class="label label-lg label-danger">删除</span>
            {@else if item.status==1}
            <span class="label label-lg label-success">正常</span>
            {@else if item.status==2}
            <span class="label label-lg label-warning">待审</span>
            {@else if item.status==3}
            <span class="label label-lg label-info">通过</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@else if item.status==4}
            <span class="label label-lg label-info">驳回</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@else}
            <span class="label label-lg label-danger">暂存</span>
            {@/if}
        </td>
        <td>
            ﻿ <a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>
            <%-- <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
                data-target="#modal-status">设置状态</a>
             {@if item.auditStatus==1}
             <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}" data-target="#modal-audit">审核</a>
             {@/if}--%>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>
            <a href="javascript:del('\${item.id}');">删除</a>

        </td>
    </tr>
    {@/each}
</script>
﻿
<div class="modal fade " id="modal-status">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">设置状态</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-status" role="form">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-2 view-label">对象</label>
                            <div class="col-md-10 status-title">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">状态</label>
                            <div class="col-md-10">
                                <div class="radio-group-container">
                                    <input type="hidden" name="id">
                                    <label>
                                        <input type="radio" name="status" value="1"><span style="padding:10px">预播</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="status" value="2"><span
                                            style="padding:10px">直播中</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="status" value="3"><span style="padding:10px">历史</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn green status">确定</button>
            </div>
        </div>
    </div>
</div>

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
                <div id="uploader">
                </div>
                <div style="margin:5px">
                    <a href="node_template.xls" style="color:red">下载模板</a><br>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>


<!--审核弹框-->
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body" style="height: 600px;overflow: auto">
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
    <div class="form-group">
        <label class="col-md-2 view-label">节点编号</label>
        <div class="col-md-10">
            \${data.code}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">节点名称</label>
        <div class="col-md-10">
            \${data.name}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">节点描述</label>
        <div class="col-md-10">
            \${data.depict}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">详细地址</label>
        <div class="col-md-10">
            <a href="${pageContext.request.contextPath}/dynamic/service/topBuilding/map.jsp?latitude=\${data.latitude}&longitude=\${data.longitude}"
               target="_blank">\${data.address}</a>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">经度</label>
        <div class="col-md-10">
            \${data.longitude}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">纬度</label>
        <div class="col-md-10">
            \${data.latitude}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">IPV4地址</label>
        <div class="col-md-10">
            \${data.ipv4}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">IPV6地址</label>
        <div class="col-md-10">
            \${data.ipv6}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">端口号</label>
        <div class="col-md-10">
            \${data.port}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">分辨率-宽</label>
        <div class="col-md-10">
            \${data.resolutionWidth}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">分辨率-高</label>
        <div class="col-md-10">
            \${data.resolutionHeight}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">mac地址</label>
        <div class="col-md-10">
            \${data.macAddr}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">控制器数量</label>
        <div class="col-md-10">
            \${data.ctrlNum}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">建筑物编号</label>
        <div class="col-md-10">
            \${data.buildingCode}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">站点编号</label>
        <div class="col-md-10">
            \${data.stationCode}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">备注</label>
        <div class="col-md-10">
            \${remark}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">状态 </label>
        <div class="col-md-10">
            \${pStatus(data.status)}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">创建人姓名</label>
        <div class="col-md-10">
            \${data.createUserName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">创建日期</label>
        <div class="col-md-10">
            \${data.createDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">更新人名称</label>
        <div class="col-md-10">
            \${data.lastModifyUserName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">更新日期</label>
        <div class="col-md-10">
            \${data.lastModifyDate}
        </div>
    </div>
</script>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>

<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>


<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
<script src="js/upload.js?v=${cfg.version}"></script>
</html>

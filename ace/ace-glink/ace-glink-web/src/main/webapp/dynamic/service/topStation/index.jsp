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
    <title>站点管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-3">
                <a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>
            </div>

            <div class="col-md-9">

                <form id="fm-search">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <select id="subArea" name="subareaCode" id="s-cls-list" class="form-control"
                                style="height: 31px;"
                                onchange="setParams('subareaCode',this.value)">
                        </select>
                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入站点名称">
                        <span class="input-group-btn">
                            <button class="btn  btn-default search_btn" type="submit">搜索</button>
                        </span>
                    </div>
                </form>
            </div>

        </div>


        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>

                    <th width="10%"> 所属分区</th>
                    <th width="15%"> 站点名称</th>
                    <th width="10%"> 站点编号</th>
                    <th width="15%"> 站点地址</th>
                    <th width="15%"> 站点描述</th>
                    <th width="10%"> 状态</th>

                    <th width="10%"> 创建日期</th>

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

        <td> \${item.subareaName}</td>
        <td> \${item.name}</td>
        <td> \${item.code}</td>
        <td> \${item.address}</td>
        <td> \${item.depict}</td>
        {@if item.status==1}
        <td> 正常</td>
        {@/if}
        <td> \${item.createDate}</td>
        <td>
            <a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>

            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>

            <a href="javascript:del('\${item.id}');">删除</a>

        </td>
    </tr>
    {@/each}
</script>
﻿



<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
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
<script id="tpl-fm" type="text/template">
    <div class="form-body">

        <div class="form-group">
            <label class="col-md-2 view-label">主键</label>
            <div class="col-md-10">
                \${data.o.id}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">分区编码</label>
            <div class="col-md-10">
                \${data.o.subareaCode}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">站点名称</label>
            <div class="col-md-10">
                \${data.o.name}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">站点描述</label>
            <div class="col-md-10">
                \${data.o.depict}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">备注</label>
            <div class="col-md-10">
                \${data.o.remark}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">状态 </label>
            <div class="col-md-10">
                \${data.o.status}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建人编号</label>
            <div class="col-md-10">
                \${data.o.createUserId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建人姓名</label>
            <div class="col-md-10">
                \${data.o.createUserName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建日期</label>
            <div class="col-md-10">
                \${data.o.createDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">更新人编号</label>
            <div class="col-md-10">
                \${data.o.lastModifyUserId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">更新人名称</label>
            <div class="col-md-10">
                \${data.o.lastModifyUserName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">更新日期</label>
            <div class="col-md-10">
                \${data.o.lastModifyDate}
            </div>
        </div>

        <h4>结果</h4>
        <hr>
        <div class="form-group " id="operation">
            <label class="col-md-2 control-label">结果</label>
            <div class="col-md-10">
                <div class="radio-group-container">
                    <label>
                        <input type="radio" name="rst" value="2"><span style="padding:10px">通过</span>
                    </label>
                    <label>
                        <input type="radio" name="rst" value="3"><span style="padding:10px">退回</span>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">说明</label>
            <div class="col-md-10">
                <input type="hidden" name="id" value="\${data.o.id}">
                <textarea name="text" style="width: 100%;height: 100px;"></textarea>
            </div>
        </div>
    </div>

</script>

<script id="tpl-preview" type="text/template">
    <div class="portlet light">
        <div class="portlet-title">
            <div class="caption font-green-sharp">
                <i class="icon-share font-green-sharp"></i>
                <span class="caption-subject bold uppercase"> 基本信息</span>
            </div>
        </div>
        <div class="portlet-body">
    <div class="form-group">
        <label class="col-md-2 view-label">分区编码</label>
        <div class="col-md-10">
            \${data.o.subareaName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">站点编码</label>
        <div class="col-md-10">
            \${data.o.code}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">站点名称</label>
        <div class="col-md-10">
            \${data.o.name}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">站点地址</label>
        <div class="col-md-10">
            \${data.o.address}
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2 view-label">站点描述</label>
        <div class="col-md-10">
            \${data.o.depict}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">备注</label>
        <div class="col-md-10">
            \${data.o.remark}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">状态 </label>
        <div class="col-md-10">
            {@if data.o.status==1}
            正常
            {@/if}
        </div>
    </div>


    <div class="form-group">
        <label class="col-md-2 view-label">创建日期</label>
        <div class="col-md-10">
            \${data.o.createDate}
        </div>
    </div>
        </div>
    </div>
    <div class="portlet light">
        <div class="portlet-title">
            <div class="caption font-green-sharp">
                <i class="icon-share font-green-sharp"></i>
                <span class="caption-subject bold uppercase"> 归属节点列表</span>
            </div>
        </div>
        <div class="portlet-body">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <table class="table table-hover table-light">
                        <thead>
                        <tr>
                            <th width="20%"> 编号</th>
                            <th width="30%"> 节点名称</th>
                            <th width="30%"> 建筑名称</th>
                            <th width="20%"> 设备数量</th>
                        </tr>
                        </thead>
                        <tbody>
                        {@each data.nodeList as item, index}
                        <tr>
                            <td> \${item.code}</td>
                            <td> \${item.name}</td>
                            <td> \${item.tbName}</td>
                            <td> \${item.deviceNum}</td>
                        </tr>
                        {@/each}
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</script>
<script id="area-list" type="text/template">
    {@each data as item, index}
    <option value="\${item.code}">\${item.name}</option>
    {@/each}
</script>
﻿

<style>
    .cover {
        width: 70px;
        height: 70px;
        object-fit: cover;
    }

    .describtion {
        padding-left: 15px;
        height: 50px;
    }

    .cost {
        padding-top: 5px;
        padding-left: 15px;
        color: #FE6500;
    }
</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
</html>

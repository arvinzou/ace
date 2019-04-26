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
    <title>设备管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet"
          href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
          type="text/css" media="screen"/>
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-3">
                <a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>
                <button type="button" class="btn  green" id="btn-view-importXls"
                        authority="false">批量导入
                </button>
            </div>
            <div class="col-md-3">

            </div>

            <div class="col-md-9">


                <label style="float:left;padding-right:5px;line-height: 31px;">节点：</label>
                <div class="btn-group" role="group" style="float:left;padding-right:20px;">
                    <select name="nodeCode" id="nodeCode" class="form-control"
                                onchange="setParams('nodeCode',this.value)">

                        </select>
                    </div>
                <label style="float:left;padding-right:5px;line-height: 31px;">设备类型：</label>
                <div class="btn-group" role="group" style="float:left;padding-right:20px;">
                    <select name="type" id="type" class="form-control" onchange="setParams('type',this.value)">

                        </select>
                    </div>
                <label style="float:left;padding-right:5px;line-height: 31px;">设备状态：</label>
                <div class="btn-group" role="group" style="float:left;padding-right:20px;">

                        <select name="status" id="status" class="form-control"
                                onchange="setParams('status',this.value)">
                            <option value="">全部</option>
                            <option value="1">上线</option>
                            <option value="2">下线</option>
                        </select>
                    </div>
                <form id="fm-search">
                    <div class="input-group">
                        <input type="text" name="keyword" class="form-control" placeholder="请输入设备名称">
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


                    <th width="12%"> 设备名称</th>
                    <th width="13%"> 设备类型</th>
                    <th width="13%"> 所属节点</th>
                    <th width="15%"> 上线时间</th>
                    <th width="13%"> 下线时间</th>
                    <th width="10%"> 状态</th>

                    <th width="20%">操作</th>
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


        <td> \${item.name}</td>
        <td> \${parseType(item.type)}</td>
        <td> \${item.nodeName}</td>
        <td> \${item.onlineDate}</td>
        <td> \${item.offlineDate}</td>


        <td>
            {@if item.status==1}
            <span class="label label-lg label-danger">上线</span>
            {@else if item.status==2}
            <span class="label label-lg label-success">下线</span>
            {@/if}
        </td>
        <td>
            ﻿ <a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>
            <a href="javascript:del('\${item.id}');">删除</a>
            <a href="javascript:online('\${item.id}');">上线</a>
            <a href="javascript:outline('\${item.id}');">下线</a>
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

<div class="modal fade" role="dialog" id="modal-upload">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" authority="false" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Excel导入</h4>
            </div>
            <div class="modal-body">
                <div id="uploader">
                </div>
                <div style="margin:5px">
                    <a href="device-template.xls" style="color:red">下载模板</a>.<br>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>

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


<script id="tpl-preview" type="text/template">

    <div class="form-group">
        <label class="col-md-2 view-label">设备编号</label>
        <div class="col-md-10">
            \${data.o.code}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">设备名称</label>
        <div class="col-md-10">
            \${data.o.name}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">设备类型</label>
        <div class="col-md-10">
            \${parseType(data.o.type)}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">所属节点</label>
        <div class="col-md-10">
            \${data.o.nodeName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">上线时间</label>
        <div class="col-md-10">
            \${data.o.onlineDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">下线时间</label>
        <div class="col-md-10">
            \${data.o.offlineDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">生产厂商</label>
        <div class="col-md-10">
            \${data.o.prcBisFirm}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">生产日期</label>
        <div class="col-md-10">
            \${data.o.prcDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">理论使用寿命</label>
        <div class="col-md-10">
            \${data.o.workingLife}
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
            <span>上线</span>
            {@else if data.o.status==2}
            <span>下线</span>
            {@/if}
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2 view-label">创建日期</label>
        <div class="col-md-10">
            \${data.o.createDate}
        </div>
    </div>

</script>

<script id="type-tpl" type="text/template">
    <option value="">全部</option>
    {@each data as item, index}
    <option value="\${item.CODE}">\${item.NAME}</option>
    {@/each}
</script>

<script id="nodeCode-tpl" type="text/template">
    <option value="">全部</option>
    {@each data as item, index}
    <option value="\${item.code}">\${item.name}</option>
    {@/each}
</script>

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
<script src="js/upload.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>

</html>

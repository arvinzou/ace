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
    <title>区域数据</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>

    <link rel="stylesheet" href="css/style.css">

    <%--sweetalert--%>
    <%--   <script src="${pageContext.request.contextPath}/content/common/js/sweetalert/js/sweet-alert.min.js"></script>
       <link rel="stylesheet" type="text/css"
             href="${pageContext.request.contextPath}/content/common/js/sweetalert/css/sweet-alert.css">--%>
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light" style="height: 700px;">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-3">

                <a href="javascript:syncProjectData();" class="btn green">同步</a>
            </div>

            <div class="col-md-5" style="float: right;">

                <form id="fm-search">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">

                    </div>
                   <%-- <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <div class="input-group" style="float:left">
                            区域数据：
                            <input id="pid" name="pid" class="easyui-combotree"
                                   data-options="url:'${pageContext.request.contextPath}/seProjectArea/selectTreeList?id=0',method:'get',animate: true,
                lines:true," style='width:200px;line-height: 30px;height: 30px;'>
                            <a href="javascript:clearQparams()" style="padding-left:10px">清除</a>
                        </div>
                    </div>--%>
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">

                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="areaName"
                               class="form-control"
                               placeholder="请输入区域名称">
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
        <div class="easyui-layout" id="cc" style="width:100%;height: 500px;">

            <div data-options="region:'center',border:false,fit:true" id="easyui-center">
                <%--  <table id="grid-table"></table>--%>

                    <table class="table table-hover">

                        <tr>
                            <%-- <th width="10%"> 上一级ID</th>--%>

                            <th width="20%"> 区域名称</th>
                            <th width="20%"> 当前目录子数量</th>
                            <th width="20%"> 区域编号</th>
                            <%--<th width="10%"> 区域类型</th>
                            <th width="10%"> 备注</th>
                            <th width="10%"> 状态</th>--%>
                            <th width="40%"> 创建日期</th>
                            <%-- <th width="10%">操作</th>--%>
                        </tr>

                        <tbody id="page-list">

                        </tbody>
                    </table>


                <div class="paginationbar" style="text-align:right;">
                    <ul class="pagination" id="pagination1" style="padding-right:15%"></ul>
                </div>

            </div>
            <div id="cc-west" class="easyui-west" data-options="region:'west',split:true" title="我的树"
                 style="width:200px;top:0px;">
                <ul id="tt" class="easyui-tree" data-options="
               url: '${pageContext.request.contextPath}/seProjectArea/selectTreeList?id=01',
                method: 'get',
                animate: true,
                lines:false,
                onClick: function(e){
                    $(this).tree('select');
                    setParams('id',e.id);

                }
            "></ul>

            </div>

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
        <td> \${item.areaName}</td>
        <td> \${item.areaNodeCount}</td>
        <td> \${item.areaNodeID}</td>
        <%-- <td> \${item.areaType}</td>
         <td> \${item.remark}</td>
         <td> \${item.status}</td>--%>
        <td> \${item.createDate}</td>

    </tr>
    {@/each}
</script>

<%--列表juicer模板--%>
<script id="tpl-find" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${item.areaName}</td>
        <td> \${item.areaNodeCount}</td>
        <td> \${item.areaNodeID}</td>
        <td> \${item.createDate}</td>
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


<!--审核弹框-->
<div class="modal fade" role="dialog" id="modal-audit">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-audit" role="form">

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn green audit">确定</button>
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
<script id="tpl-fm" type="text/template">
    <div class="form-body">

        <div class="form-group">
            <label class="col-md-2 view-label">主键</label>
            <div class="col-md-10">
                \${id}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">上一级ID</label>
            <div class="col-md-10">
                \${pid}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">项目名称</label>
            <div class="col-md-10">
                \${projectName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">区域名称</label>
            <div class="col-md-10">
                \${areaName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">当前目录子数量</label>
            <div class="col-md-10">
                \${areaNodeCount}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">区域编号</label>
            <div class="col-md-10">
                \${areaNodeID}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">区域类型</label>
            <div class="col-md-10">
                \${areaType}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">备注</label>
            <div class="col-md-10">
                \${remark}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">状态</label>
            <div class="col-md-10">
                \${status}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建日期</label>
            <div class="col-md-10">
                \${createDate}
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
    <div class="form-group">
        <label class="col-md-2 view-label">主键</label>
        <div class="col-md-10">
            \${id}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">上一级ID</label>
        <div class="col-md-10">
            \${pid}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">项目名称</label>
        <div class="col-md-10">
            \${projectName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">区域名称</label>
        <div class="col-md-10">
            \${areaName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">当前目录子数量</label>
        <div class="col-md-10">
            \${areaNodeCount}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">区域编号</label>
        <div class="col-md-10">
            \${areaNodeID}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">区域类型</label>
        <div class="col-md-10">
            \${areaType}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">备注</label>
        <div class="col-md-10">
            \${remark}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">状态</label>
        <div class="col-md-10">
            \${status}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">创建日期</label>
        <div class="col-md-10">
            \${createDate}
        </div>
    </div>
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

<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>

</html>

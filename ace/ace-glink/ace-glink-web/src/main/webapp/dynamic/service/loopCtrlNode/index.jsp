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
    <title>区级整体控制</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>

    <link rel="stylesheet" href="css/style.css">
    <%--    &lt;%&ndash;sweetalert&ndash;%&gt;
        <script src="${pageContext.request.contextPath}/content/common/js/sweetalert/js/sweet-alert.min.js"></script>
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/content/common/js/sweetalert/css/sweet-alert.css">--%>
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-3">
                <%--  <a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>--%>
            </div>

            <div class="col-md-9">


                <label style="float:left;padding-right:5px;line-height: 31px;">站点：</label>
                <div class="btn-group" role="group" style="float:left;padding-right:20px">
                        <select name="stationCode" id="stationCode" class="form-control"
                                onchange="setParams('stationCode',this.value)">

                        </select>
                    </div>
                <label style="float:left;padding-right:5px;line-height: 31px;">节点：</label>
                <div class="btn-group" role="group" style="float:left;padding-right:20px">

                        <select name="nodeCode" id="nodeCode" class="form-control"
                                onchange="setParams('nodeCode',this.value)">

                        </select>
                    </div>
                <label style="float:left;padding-right:5px;line-height: 31px;">回路类型：</label>
                <div class="btn-group" role="group" style="float:left;padding-right:20px">
                        <select name="loopType" id="loopType" class="form-control"
                                onchange="setParams('loopType',this.value)">

                        </select>
                    </div>
                <label style="float:left;padding-right:5px;line-height: 31px;">回路状态：</label>
                <div class="btn-group" role="group" style="float:left;padding-right:20px">
                        <select id="state" name="state" class="form-control"
                                onchange="setParams('state',this.value)">
                            <option value="">全部</option>
                            <option value="0">关闭</option>
                            <option value="1">开启</option>
                        </select>

                    </div>
                <form id="fm-search">
                    <%-- <div class="input-group">
                         <input type="text"
                                name="keyword"
                                class="form-control"
                                placeholder="请输入回路名称">
                         <span class="input-group-btn">
                                 <button class="btn  btn-default search_btn" type="submit">搜索</button>
                         </span>
                     </div>--%>
                </form>
            </div>

        </div>


        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="10%"> 节点名称</th>
                    <th width="10%"> 回路名称</th>
                    <th width="10%"> 回路类型</th>
                    <th width="10%"> 建筑物名称</th>
                    <th width="10%"> 回路电流（A）</th>
                    <th width="10%"> 变化时间</th>
                    <th width="10%"> 实时状态</th>
                    <th width="15%"> 实时控制</th>
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

        <td> \${item.nodeName}</td>
        <td> \${item.loopName}</td>
        <td> \${item.loopType}</td>
        <td> \${item.buName}</td>
        <td> \${item.loopCurrent}</td>
        <td> \${item.updateDate}</td>
        <td>

            {@if item.state==0}
            <span class="label label-lg label-info">关闭</span>
            {@else if item.state==1}
            <span class="label label-lg label-danger">开启</span>
            {@/if}

        </td>


        <td>
            <%--﻿ <a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>--%>
            {@if item.state==1}
            <a href="javascript:close('\${item.id}');">关闭</a>
            {@/if}
            {@if item.state==0}
            <a href="javascript:open('\${item.id}');">开启</a>
            {@/if}
            <%-- <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
                data-target="#modal-preview">查看</a>
             <a href="javascript:del('\${item.id}');">删除</a>--%>

        </td>
    </tr>
    {@/each}
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
<script id="stationCode-tpl" type="text/template">
    <option value="">全部</option>
    {@each data as item, index}
    <option value="\${item.code}">\${item.name}</option>
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
            <label class="col-md-2 view-label">节点编码</label>
            <div class="col-md-10">
                \${nodeCode}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">回路名称</label>
            <div class="col-md-10">
                \${loopName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">回路编码</label>
            <div class="col-md-10">
                \${loopKey}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">回路类型</label>
            <div class="col-md-10">
                \${loopType}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">回路电流（A）</label>
            <div class="col-md-10">
                \${loopCurrent}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">状态码</label>
            <div class="col-md-10">
                \${state}
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
                \${status}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建日期</label>
            <div class="col-md-10">
                \${createDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">更新日期</label>
            <div class="col-md-10">
                \${updateDate}
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
        <label class="col-md-2 view-label">节点编码</label>
        <div class="col-md-10">
            \${nodeCode}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">回路名称</label>
        <div class="col-md-10">
            \${loopName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">回路编码</label>
        <div class="col-md-10">
            \${loopKey}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">回路类型</label>
        <div class="col-md-10">
            \${loopType}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">回路电流（A）</label>
        <div class="col-md-10">
            \${loopCurrent}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">状态码</label>
        <div class="col-md-10">
            \${state}
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
            \${status}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">创建日期</label>
        <div class="col-md-10">
            \${createDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">更新日期</label>
        <div class="col-md-10">
            \${updateDate}
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
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>

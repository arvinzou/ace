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
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">
    <div class="portlet-body">
        <div class="row custom-toolbar">
            <div class="col-md-3">
                <button type="submit" class="btn  green" form="getCheckNode">确定</button>
            </div>
            <div class="col-md-9">
                <form id="fm-search">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <%--<button type="button" class="btn btn-default" onclick="setParams('category','');">全部</button>--%>
                        <%--<button type="button" class="btn btn-default" onclick="setParams('category','1');">已分配</button>--%>
                        <%--<button type="button" class="btn btn-default" onclick="setParams('category','2');">未分配</button>--%>
                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入节点名称、编号、建筑名称、站点名称">
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
            <form id="getCheckNode">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th width="5%">选择</th>
                        <th width="10%"> 节点编号</th>
                        <th width="10%"> 节点名称</th>
                        <th width="10%"> 控制器数量</th>
                        <th width="10%"> 建筑物</th>
                        <th width="10%"> 站点</th>
                        <th width="10%"> 状态</th>
                    </tr>
                    </thead>
                    <tbody id="page-list">

                    </tbody>
                </table>
            </form>
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
        <td><input type="checkbox" name="\${item.name}" value="\${item.id}"></td>
        <td> \${item.code}</td>
        <td> \${item.name}</td>
        <td> \${item.ctrlNum}</td>
        <td> \${item.topBuilding?item.topBuilding.name:''}</td>
        <td> \${item.topStation?item.topStation.name:''}</td>
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
    </tr>
    {@/each}
</script>


<!--审核弹框-->
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">节点分组</h4>
            </div>
            <div class="modal-body" style="height: 600px;overflow: auto">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-preview">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" class="btn green btn-default" form="upateStation">确定</button>
            </div>
        </div>
    </div>
</div>

<script id="tpl-preview" type="text/template">
    <form id="upateStation">
        <div>
            <p>已选择节点</p>
            <div class="nodelist">
                <ul>
                    {@each data.o as item, index}
                    <li class="node">
                        <input type="checkbox" checked hidden id="n\${index}" name="ids" value="\${item.value}">
                        <label for="n\${index}">\${item.name}</label>
                    </li>
                    {@/each}
                </ul>
            </div>
            <p>
                请选择站点
            </p>

            <div class="stationlist">
                <ul>
                    {@each data.s as item, index}
                    <li class="station">
                        <input type="radio" hidden id="s\${index}" name="stationCode" value="\${item.code}">
                        <label for="s\${index}">\${item.name}</label>
                    </li>
                    {@/each}
                </ul>
            </div>
        </div>

    </form>
</script>

<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>

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
    <title>定时任务-月份数据</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>

    <link rel="stylesheet" href="css/style.css">
    <%--sweetalert--%>
    <script src="${pageContext.request.contextPath}/content/common/js/sweetalert/js/sweet-alert.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/content/common/js/sweetalert/css/sweet-alert.css">
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
                        <button type="button" class="btn btn-default" onclick="setParams('status','');">全部</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','1');">预播</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','2');">直播</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','3');">历史</button>
                    </div>
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="setParams('auditStatus','');">全部</button>
                        <button type="button" class="btn btn-default" onclick="setParams('auditStatus','1');">待审
                        </button>
                        <button type="button" class="btn btn-default" onclick="setParams('auditStatus','2');">通过
                        </button>
                        <button type="button" class="btn btn-default" onclick="setParams('auditStatus','3');">驳回
                        </button>
                    </div>
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="setParams('category','');">全部</button>
                        <button type="button" class="btn btn-default" onclick="setParams('category','1');">图文</button>
                        <button type="button" class="btn btn-default" onclick="setParams('category','2');">视频</button>
                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入直播名称">
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
                    <th width="10%"> 主键</th>
                    <th width="10%"> 定时任务序号</th>
                    <th width="10%"> 月份1</th>
                    <th width="10%"> 月份2</th>
                    <th width="10%"> 月份3</th>
                    <th width="10%"> 月份4</th>
                    <th width="10%"> 月份5</th>
                    <th width="10%"> 月份6</th>
                    <th width="10%"> 月份7</th>
                    <th width="10%"> 月份8</th>
                    <th width="10%"> 月份9</th>
                    <th width="10%"> 月份10</th>
                    <th width="10%"> 月份11</th>
                    <th width="10%"> 月份12</th>
                    <th width="10%"> 备注</th>
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
        <td> \&{item.id}</td>
        <td> \&{item.timerID}</td>
        <td> \&{item.m1}</td>
        <td> \&{item.m2}</td>
        <td> \&{item.m3}</td>
        <td> \&{item.m4}</td>
        <td> \&{item.m5}</td>
        <td> \&{item.m6}</td>
        <td> \&{item.m7}</td>
        <td> \&{item.m8}</td>
        <td> \&{item.m9}</td>
        <td> \&{item.m10}</td>
        <td> \&{item.m11}</td>
        <td> \&{item.m12}</td>
        <td> \&{item.remark}</td>
        <td> \&{item.status}</td>
        <td> \&{item.createDate}</td>
        <td>
            {@if item.status==0}
            <span class="label label-lg label-danger">删除</span>
            {@else if item.status==1}
            <span class="label label-lg label-info">暂存</span>
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
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-status">设置状态</a>
            {@if item.auditStatus==1}
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}" data-target="#modal-audit">审核</a>
            {@/if}
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
            <label class="col-md-2 view-label">定时任务序号</label>
            <div class="col-md-10">
                \${timerID}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">月份1</label>
            <div class="col-md-10">
                \${m1}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">月份2</label>
            <div class="col-md-10">
                \${m2}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">月份3</label>
            <div class="col-md-10">
                \${m3}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">月份4</label>
            <div class="col-md-10">
                \${m4}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">月份5</label>
            <div class="col-md-10">
                \${m5}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">月份6</label>
            <div class="col-md-10">
                \${m6}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">月份7</label>
            <div class="col-md-10">
                \${m7}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">月份8</label>
            <div class="col-md-10">
                \${m8}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">月份9</label>
            <div class="col-md-10">
                \${m9}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">月份10</label>
            <div class="col-md-10">
                \${m10}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">月份11</label>
            <div class="col-md-10">
                \${m11}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">月份12</label>
            <div class="col-md-10">
                \${m12}
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
        <label class="col-md-2 view-label">定时任务序号</label>
        <div class="col-md-10">
            \${timerID}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">月份1</label>
        <div class="col-md-10">
            \${m1}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">月份2</label>
        <div class="col-md-10">
            \${m2}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">月份3</label>
        <div class="col-md-10">
            \${m3}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">月份4</label>
        <div class="col-md-10">
            \${m4}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">月份5</label>
        <div class="col-md-10">
            \${m5}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">月份6</label>
        <div class="col-md-10">
            \${m6}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">月份7</label>
        <div class="col-md-10">
            \${m7}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">月份8</label>
        <div class="col-md-10">
            \${m8}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">月份9</label>
        <div class="col-md-10">
            \${m9}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">月份10</label>
        <div class="col-md-10">
            \${m10}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">月份11</label>
        <div class="col-md-10">
            \${m11}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">月份12</label>
        <div class="col-md-10">
            \${m12}
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
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>

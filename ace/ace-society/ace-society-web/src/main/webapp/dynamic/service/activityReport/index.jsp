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
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <%--common css--%>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <%--custom css--%>
</head>
<body>

<%--==============common jsp-prefix==============--%>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-prefix==============--%>

<div class="portlet light">
    <div class="portlet-body">

        <div class="row custom-tollbar">
            <div class="col-sm-4">

            </div>
            <div class="col-sm-8">
                <div class="col-sm-12">
                <form onsubmit="return t_query()">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="setParams('status','');">全部</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','2');">待审</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','3');">通过</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','4');">驳回</button>
                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control input-left"
                               placeholder="请输入名称">
                        <span class="input-group-btn">
                                                        <button class="btn btn-right btn-default search_btn"
                                                                type="submit">
                                                                搜索
                                                        </button>
                                                    </span>
                    </div>
                </form>
                </div>
            </div>

        </div>


        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="35%">所属活动</th>
                    <th width="35%">报道标题</th>
                    <th width="5%">备注</th>
                    <th width="10%">状态</th>
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

<%--查看详情--%>
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-detail">
    <div class="modal-dialog" role="document" style="width: 90%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">报道信息详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-detail" role="form">
                    <%--详情模板填充--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <%--<button type="button" class="btn btn-primary">确定</button>--%>
            </div>
        </div>
    </div>
</div>


<!--审核弹框-->
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-audit">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">活动报道审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-audit" role="form">
                    <div class="form-body">
                        <div class="form-group " id="operation">
                            <label class="col-md-2 control-label">审核结果</label>
                            <div class="col-md-10">
                                <div class="radio-group-container">
                                    <label>
                                        <input checked type="radio" name="rst" value="3"><span style="padding:10px">通过</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="rst" value="4"><span style="padding:10px">退回</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">审核说明</label>
                            <div class="col-md-10">
                                <input type="hidden" name="id"/>
                                <textarea name="message" style="width: 100%;height: 100px;"></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">确定</button>
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

        <td> \${item.activityTitel}</td>
        <td> \${item.title}</td>

        {@if item.top==1}
        <td><span class="label label-lg label-info">精选</span></td>
        {@else}
        <td></td>
        {@/if}

        <td>
            {@if item.status==0}
            <span class="label label-lg label-danger">已删除</span>

            {@else if item.status==1}
            <span class="label label-lg label-info">暂存</span>

            {@else if item.status==2}
            <span class="label label-lg label-warning">提交审核</span>

            {@else if item.status==3}
            <span class="label label-lg label-success" title="\${item.auditRemark}">审核通过</span>
            {@else if item.status==4}
            <span class="label label-lg label-info" title="\${item.auditRemark}">被驳回</span>
            {@else}
            <span class="label label-lg label-danger">暂存</span>

            {@/if}
        </td>
        <td>
            <a class="operation" href="javascript:detail('\${item.id}');">查看</a>
            <%-- <a class="operation" href="javascript:edit('\${item.id}');">编辑</a>--%>
            <%--<a class="operation" href="javascript:del('\${item.id}');">删除</a>--%>

            {@if item.top==1}
            <a class="operation" href="javascript:setTop('\${item.id}');">取消精选</a>
            {@else}
            <a class="operation" href="javascript:setTop('\${item.id}');">设为精选</a>
            {@/if}
            {@if item.status==2}
            <a class="operation" href="#" data-toggle="modal" data-target="#modal-audit" data-id="\${item.id}">审核</a>
            {@/if}
        </td>
    </tr>
    {@/each}
</script>


<%--详情模板--%>
<script id="tpl-detail" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 view-label">活动名称</label>
            <div class="col-md-10">
                \${data.activityTitel}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">报道封面</label>
            <div class="col-md-10">
                <img src="\${data.coverUrl}" style="max-width:480px;"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 view-label">报道标题</label>
            <div class="col-md-10">
                \${data.title}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">报道内容</label>
            <div class="col-md-10">
                {@each data.content as item, index}
                {@if item.type==1}

                <div>\${item.content}</div>
                {@else if item.type==2}
                <div style="width:100%">
                    <img style="width:100%" src="\${item.content}" alt="">
                </div>
                {@/if}

                {@/each}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建人姓名</label>
            <div class="col-md-10">
                \${data.createUserName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">报道创建时间</label>
            <div class="col-md-10">
                \${data.createDate}
            </div>
        </div>
    </div>
</script>





<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
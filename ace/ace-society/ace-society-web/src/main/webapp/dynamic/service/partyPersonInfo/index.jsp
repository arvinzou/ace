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
    <title>个人信息</title>
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

        <div class="row custom-toolbar">
            <div class="col-sm-5">

            </div>
            <div class="col-sm-7">
                <form onsubmit="return t_query()">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="setParams('status','');">全部</button>
                        <%--<button type="button" class="btn btn-default"  onclick="setParams('status','0');">已删除</button>--%>
                        <button type="button" class="btn btn-default" onclick="setParams('status','1');">暂存</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','2');">待审核</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','3');">已通过</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','4');">被驳回</button>
                    </div>
                    <div class="input-group">
                        <input type="text" name="keyword" class="form-control " placeholder="请输入姓名">
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
                    <th width="10%">微信昵称</th>
                    <th width="10%">真实姓名</th>
                    <th width="15%">手机号码</th>
                    <th width="10%">政治面貌</th>
                    <th width="10%">累计获得爱心币</th>
                    <th width="10%">有效爱心币</th>
                    <th width="10%">注册状态</th>
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
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td>
            <div class="row">
                <div class="col-md-12">
                    <img src="\${item.headimgurl}" class="cover"/>
                    <a>\${item.nickname}</a>
                </div>
            </div>
        </td>
        <td>\${item.realName}</td>
        <td>\${item.mobilePhone}</td>
        <td>\${item.politicalStatus}</td>
        <td>\${item.accPoints}</td>
        <td>\${item.validPoints}</td>
        <td>
            <span class="label label-lg  \${item.status==0 ? 'label-danger'
            : item.status==1 ? 'label-primary'
            : item.status==2 ? 'label-info'
            : item.status==3 ? 'label-success'
            : item.status==4 ? 'label-danger' : 'label-info'}">
                 \${parseStatus(item.status)}
            </span>
        </td>
        <td>
            <a class="operation" href="javascript:detail('\${item.id}');">查看</a>
            <%--<a class="operation" href="javascript:edit('\${item.id}');">编辑</a>--%>
            <%--<a class="operation" href="javascript:del('\${item.id}');">删除</a>--%>
            {@if item.status==2}
            <a class="operation" href="#" data-toggle="modal" data-target="#modal-audit" data-id="\${item.id}">审核</a>
            {@/if}
        </td>
    </tr>
    {@/each}
</script>

<%--查看详情--%>
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-detail">
    <div class="modal-dialog" role="document" style="width: 90%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">个人信息详情</h4>
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
    <div class="modal-dialog" role="document" style="width: 90%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">个人信息审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-audit" role="form">
                    <%--<div class="form-body">--%>
                    <%--<div class="form-group " id="operation">--%>
                    <%--<label class="col-md-2 control-label">审核结果</label>--%>
                    <%--<div class="col-md-10">--%>
                    <%--<div class="radio-group-container">--%>
                    <%--<label>--%>
                    <%--<input type="radio" name="rst" value="3"><span style="padding:10px">通过</span>--%>
                    <%--</label>--%>
                    <%--<label>--%>
                    <%--<input type="radio" name="rst" value="4"><span style="padding:10px">退回</span>--%>
                    <%--</label>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-md-2 control-label">审核说明</label>--%>
                    <%--<div class="col-md-10">--%>
                    <%--<input type="hidden" name="id"/>--%>
                    <%--<textarea name="message" style="width: 100%;height: 100px;"></textarea>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>
<%--审核渲染模板--%>
<script id="tpl-fm-audit" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 view-label">微信昵称</label>
            <div class="col-md-10">
                \${data.nickname}
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 view-label">真实姓名</label>
            <div class="col-md-10">
                \${data.realName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">手机号码</label>
            <div class="col-md-10">
                \${data.mobilePhone}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">政治面貌</label>
            <div class="col-md-10">
                \${data.politicalStatus}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">累计获得爱心币</label>
            <div class="col-md-10">
                \${data.accPoints}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">有效爱心币</label>
            <div class="col-md-10">
                \${data.validPoints}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">注册状态</label>
            <div class="col-md-10">
                \${parseStatus(data.status)}
            </div>
        </div>
        <h4>结果</h4>
        <hr>
        <div class="form-group " id="operation">
            <label class="col-md-2 control-label">审核说明</label>
            <div class="col-md-10">
                <div class="radio-group-container">
                    <label>
                        <input type="radio" name="rst" value="3"><span style="padding:10px">通过</span>
                    </label>
                    <label>
                        <input type="radio" name="rst" value="4"><span style="padding:10px">驳回</span>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">审核说明</label>
            <div class="col-md-10">
                <input type="hidden" name="id" value="\${data.id}">
                <textarea name="message" style="width: 100%;height: 100px;"></textarea>
            </div>
        </div>
    </div>

</script>

<%--详情模板--%>
<script id="tpl-detail" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 view-label">微信昵称</label>
            <div class="col-md-10">
                \${data.nickname}
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 view-label">真实姓名</label>
            <div class="col-md-10">
                \${data.realName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">手机号码</label>
            <div class="col-md-10">
                \${data.mobilePhone}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">政治面貌</label>
            <div class="col-md-10">
                \${data.politicalStatus}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">累计获得爱心币</label>
            <div class="col-md-10">
                \${data.accPoints}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">有效爱心币</label>
            <div class="col-md-10">
                \${data.validPoints}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">注册状态</label>
            <div class="col-md-10">
                \${parseStatus(data.status)}
            </div>
        </div>
    </div>
</script>
<style>
    .cover {
        width: 55px;
        height: 55px;
        object-fit: cover;
    }

    .description {
        /*padding-left: 15px;*/
        /*height: 50px;*/
    }

    .cost {
        padding-top: 5px;
        padding-left: 15px;
        color: #FE6500;
    }
</style>
<%--==============common footer==============--%>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
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
    <jsp:include page="/dynamic/common/base.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/act.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/dynamic/service/\$!{bean.lowerName}/js/act.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/content/common/js/loader.js?v=${cfg.version}"></script>
</head>
<body>
<div class="page-wrapper">

    <div class="page-wrapper-row full-height">
        <div class="page-wrapper-middle">
            <div class="page-container">
                <div class="page-content-wrapper">
                    <div class="page-content">
                        <div class="container">
                            <ul class="page-breadcrumb breadcrumb">
                                <li>
                                    <a href="${pageContext.request.contextPath}/index.jsp">首页</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <li>
                                    <span>个人信息</span>
                                </li>
                            </ul>
                            <div class="page-content-inner">

                                <!---==============================================-->

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="portlet light">
                                            <div class="portlet-title">
                                                <div class="caption">
                                                    个人信息
                                                </div>
                                                <div class="actions">
                                                    <a href="javascript:void(0);" onclick="add();"
                                                       class="btn green">创建</a>
                                                </div>
                                            </div>
                                            <div class="portlet-body">

                                                <div class="row">
                                                    <div class="col-sm-8">

                                                    </div>
                                                    <div class="col-sm-4">
                                                        <form onsubmit="return t_query()">
                                                            <div class="input-group">
                                                                <input type="text"
                                                                       name="keyword"
                                                                       class="form-control input-circle-left"
                                                                       placeholder="请输入名称">
                                                                <span class="input-group-btn">
                                                        <button class="btn btn-circle-right btn-default search_btn"
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
                                                            <th width="10%">微信昵称</th>
                                                            <th width="10%">真实姓名</th>
                                                            <th width="15%">手机号码</th>
                                                            <th width="10%">政治面貌</th>
                                                            <th width="10%">累计获取积分</th>
                                                            <th width="10%">有效积分</th>
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
                                    </div>
                                </div>
                                <!--=======================================-->

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="bottom"></div>
</div>

<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td>
            <div class="row">
                <div class="col-md-3">
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
            : item.status==2 ? 'label-warning'
            : item.status==3 ? 'label-success'
            : item.status==4 ? 'label-danger' : 'label-info'}">
                 \${parseStatus(item.status)}
            </span>
        </td>
        <td>
            <a class="operation" href="javascript:detail('\${item.id}');">查看详情</a>
            <%--<a class="operation" href="javascript:edit('\${item.id}');">编辑</a>--%>
            <%--<a class="operation" href="javascript:del('\${item.id}');">删除</a>--%>
            {@if item.status==2}
            <a class="operation" href="#" data-toggle="modal" data-target="#modal-audit"
               data-id="\${item.id}">审核</a>
            {@/if}
        </td>
    </tr>
    {@/each}
</script>

<%--查看详情--%>
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-detail">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">个人信息详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-detail" role="form">
                    <div class="form-body">
                        <div class="table-scrollable" id="detail-info">
                            <%--详情模板填充--%>
                        </div>
                    </div>
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
                <h4 class="modal-title">个人信息审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-audit" role="form">
                    <div class="form-body">
                        <div class="form-group " id="operation">
                            <label class="col-md-2 control-label">审核结果</label>
                            <div class="col-md-10">
                                <div class="radio-group-container">
                                    <label>
                                        <input type="radio" name="rst" value="3"><span style="padding:10px">通过</span>
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
</body>
<%--详情模板--%>
<script id="tpl-detail" type="text/template">

    <table class="table table-bordered table-hover">
        <tr>
            <td class="active"> 微信昵称</td>
            <td class="success"> \${data.nickname}</td>
        </tr>
        <tr>
            <td class="active"> 真实性名</td>
            <td class="success"> \${data.realName}</td>
        </tr>
        <tr>
            <td class="active"> 手机号码</td>
            <td class="success"> \${data.mobilePhone}</td>
        </tr>
        <tr>
            <td class="active"> 政治面貌</td>
            <td class="success"> \${data.politicalStatus}</td>
        </tr>
        <tr>
            <td class="active"> 累计获取积分</td>
            <td class="success"> \${data.accPoints}</td>
        </tr>
        <tr>
            <td class="active"> 有效积分</td>
            <td class="success"> \${data.validPoints}</td>
        </tr>

        <tr>
            <td class="active"> 注册状态</td>
            <td class="success"> \${parseStatus(data.status)}</td>
        </tr>
    </table>

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
</html>
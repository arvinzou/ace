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
    <title>爱心币配置</title>
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

<!---==============================================-->
<div class="portlet light">
    <div class="portlet-title hide">
        <div class="caption">

        </div>
        <div class="actions">
        </div>
    </div>
    <div class="portlet-body">

        <div class="row custom-tollbar">
            <div class="col-sm-5">
                <a href="javascript:void(0);" onclick="add();" class="btn green">创建</a>

            </div>
            <div class="col-sm-7">
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
                    <th width="10%"> 类型</th>
                    <th width="10%"> 名称</th>
                    <th width="10%"> 主办者</th>
                    <th width="10%"> 参与者</th>
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
<!--=======================================-->

<%--查看详情--%>
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-detail">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">$times;</span>
                </button>
                <h4 class="modal-title">爱心币配置详情</h4>
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
                <%--
                <button type="button" class="btn btn-primary">确定</button>
                --%>
            </div>
        </div>
    </div>
</div>

<!--审核弹框-->
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-audit">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">$times;</span>
                </button>
                <h4 class="modal-title">爱心币配置审核</h4>
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

<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        {@if item.category==1}
        <td> 公益活动</td>
        {@else if item.category==2}
        <td>普及活动</td>
        {@else if item.category==3}
        <td>创意活动</td>
        {@else if item.category==4}
        <td>党建活动</td>
        {@else if item.category==5}
        <td>随手拍</td>
        {@else if item.category==6}
        <td>我有点子</td>
        {@else if item.category==7}
        <td>秀我直播</td>
        {@else if item.category==8}
        <td>邻里圈子</td>
        {@/if}
        <td> \${item.name}</td>
        <td> \${item.host}</td>
        <td> \${item.participant}</td>
        <td>
            <a class="operation" href="javascript:edit('\${item.id}');">编辑</a>
            <a class="operation" href="javascript:del('\${item.id}');">删除</a>
        </td>
    </tr>
    {@/each}
</script>
<%--详情juicer模板--%>
<script id="tpl-detail" type="text/template">
    <table class="table table-bordered table-hover">
        <tr>
            <td class="active"> 主键-GUID</td>
            <td class="success"> \${data.id}</td>
        </tr>
        <tr>
            <td class="active"> 类型</td>
            <td class="success"> \${data.category}</td>
        </tr>
        <tr>
            <td class="active"> 名称</td>
            <td class="success"> \${data.name}</td>
        </tr>
        <tr>
            <td class="active"> 主办者</td>
            <td class="success"> \${data.host}</td>
        </tr>
        <tr>
            <td class="active"> 参与者</td>
            <td class="success"> \${data.participant}</td>
        </tr>
        <tr>
            <td class="active"> 状态\r\n0-删除 \r\n1-暂存\r\n2-关闭</td>
            <td class="success"> \${data.status}</td>
        </tr>
        <tr>
            <td class="active"> 创建人编号</td>
            <td class="success"> \${data.createUserId}</td>
        </tr>
        <tr>
            <td class="active"> 创建人姓名</td>
            <td class="success"> \${data.createUserName}</td>
        </tr>
        <tr>
            <td class="active"> 创建日期</td>
            <td class="success"> \${data.createDate}</td>
        </tr>
        <tr>
            <td class="active"> 更新人编号</td>
            <td class="success"> \${data.lastModifyUserId}</td>
        </tr>
        <tr>
            <td class="active"> 更新人名称</td>
            <td class="success"> \${data.lastModifyUserName}</td>
        </tr>
        <tr>
            <td class="active"> 更新日期</td>
            <td class="success"> \${data.lastModifyDate}</td>
        </tr>
    </table>
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
<%--==============common footer==============--%>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
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
    <title>爱心场地</title>
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
            <div class="col-sm-7">
                <a onclick="javascript:add()" class="btn  green">
                    <i class="fa fa-plus"></i> 添加
                </a>
            </div>
            <div class="col-sm-5">
                <form onsubmit="return t_query()">
                    <div class="input-group">
                        <input type="text" name="keyword" class="form-control " placeholder="请输入场地名称">
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
                    <th width="20%"> 场地名称</th>
                    <th width="20%"> 场地分类</th>
                    <th width="20%"> 购买所需积分</th>
                    <th width="20%"> 场地状态</th>
                    <th width="10%"> 备注</th>
                    <th width="10%">操作</th>
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

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td>
            <img src="\${item.commodityCover}" class="cover"/>
            \${item.commodityName}
        </td>
        <td> \${item.category}</td>
        <td> \${item.costPoints}</td>
        <td>
            {@if item.state==1}
            <span class="label label-lg label-success">在售</span>
            {@else}
            <span class="label label-lg label-danger">已下架</span>
            {@/if}
        </td>
        <td> \${item.remark}</td>
        <td>
            <a class="operation" href="javascript:detail('\${item.id}');">查看详情</a>
            {@if item.state==1}
            <a class="operation" href="javascript:outline('\${item.id}');">下架</a>
            {@else}
            <a class="operation" href="javascript:edit('\${item.id}');">编辑</a>
            <a class="operation" href="javascript:del('\${item.id}');">删除</a>
            <a class="operation" href="javascript:online('\${item.id}');">上架</a>
            {@/if}
        </td>
    </tr>
    {@/each}
</script>
<%--详情juicer模板--%>
<script id="tpl-detail" type="text/template">
    <table class="table table-bordered table-hover">
        <tr>
            <td class="active"> 场地编号</td>
            <td class="success"> \${data.id}</td>
        </tr>
        <tr>
            <td class="active"> 商品类型</td>
            <td class="success"> \${parseType(data.commodityType)}</td>
        </tr>
        <tr>
            <td class="active"> 场地名称</td>
            <td class="success"> \${data.commodityName}</td>
        </tr>
        <tr>
            <td class="active"> 场地分类</td>
            <td class="success"> \${data.category}</td>
        </tr>
        <tr>
            <td class="active"> 场地封面</td>
            <td class="success"><img src="\${data.commodityCover}" class="cover"/></td>
        </tr>
        <tr>
            <td class="active"> 场地简介</td>
            <td class="success"> \${data.summary}</td>
        </tr>
        <tr>
            <td class="active"> 购买所需积分</td>
            <td class="success"> \${data.costPoints}</td>
        </tr>
        <tr>
            <td class="active"> 场地状态</td>
            <td class="success"> \${parseState(data.state)}</td>
        </tr>
        <tr>
            <td class="active"> 备注</td>
            <td class="success"> \${data.remark}</td>
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
            <td class="active"> 更新人名称</td>
            <td class="success"> \${data.lastModifyUserName}</td>
        </tr>
        <tr>
            <td class="active"> 更新日期</td>
            <td class="success"> \${data.lastModifyDate}</td>
        </tr>
    </table>
</script>

<%--查看详情--%>
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-detail">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">爱心场地详情</h4>
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">爱心商品审核</h4>
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
<style>
    .cover {
        width: 70px;
        height: 70px;
        object-fit: cover;
    }

    .description {
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
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
<%--custom js--%>
</html>
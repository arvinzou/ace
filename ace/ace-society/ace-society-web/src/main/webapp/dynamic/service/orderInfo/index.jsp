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
    <title>订单管理</title>
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

<!---================custom-body-start======================-->
<div class="portlet light">
    <div class="portlet-body">
        <!---===================custom-toolbar===========================-->
        <div class="row custom-toolbar">
            <div class="col-sm-7">
                <a onclick="javascript:add()" class="btn green hide">
                    <i class="fa fa-plus"></i> 添加
                </a>
            </div>
            <div class="col-sm-5">
                <form onsubmit="return t_query()">
                    <div class="input-group">
                        <input type="text" name="keyword" class="form-control " placeholder="请输入名称">
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
                    <th width="15%"> 客户名称</th>
                    <th width="15%"> 订单编号</th>
                    <th width="15%"> 付款方式</th>
                    <th width="10%"> 订单状态</th>
                    <th width="10%"> 付款金额</th>
                    <th width="15%"> 付款时间</th>
                    <th width="10%"> 收货类型</th>
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
<!--=================custom-body-end======================-->


<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>

<%--============================script piece============================--%>
<%--查看详情--%>
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-detail">
    <div class="modal-dialog" role="document" style="width: 90%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">订单管理详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-detail" role="form">
                            <%--详情模板填充--%>
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

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td>
            <img src="\${item.headimgurl}" class="cover"/>
            <a>\${item.nickname}</a>
        </td>
        <td> \${item.orderNo}</td>
        <td>
            {@if item.payType==1}
            <span class="label label-lg label-info">\${parsePayType(item.payType)}</span>
            {@else if item.payType==2}
            <span class="label label-lg label-success">\${parsePayType(item.payType)}</span>
            {@else if item.payType==3}
            <span class="label label-lg label-primary">\${parsePayType(item.payType)}</span>
            {@else}
            <span class="label label-lg label-danger">\${parsePayType(item.payType)}</span>
            {@/if}
        </td>
        <td>
            {@if item.orderState==1}
            <span class="label label-lg label-danger">\${parseState(item.orderState)}</span>
            {@else if item.status==2}
            <span class="label label-lg label-success">\${parseState(item.orderState)}</span>
            {@else if item.status==3}
            <span class="label label-lg label-success">\${parseState(item.orderState)}</span>
            {@else if item.status==4}
            <span class="label label-lg label-success">\${parseState(item.orderState)}</span>
            {@else}
            <span class="label label-lg label-danger">\${parseState(item.orderState)}</span>
            {@/if}
        </td>
        <td> \${item.payAmount}</td>
        <td> \${item.payDate}</td>
        <td>
            <span class="label label-lg \${item.receiveType==1?'label-warning':'label-info' }">
                \${parseReceiveType(item.receiveType)}
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
<%--详情juicer模板--%>
<script id="tpl-detail" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 view-label">订单编码</label>
            <div class="col-md-10">
                \${data.id}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">订单编号</label>
            <div class="col-md-10">
                \${data.orderNo}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">客户姓名</label>
            <div class="col-md-10">
                \${data.nickname}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">付款方式</label>
            <div class="col-md-10">
                \${parsePayType(data.payType)}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">付款金额</label>
            <div class="col-md-10">
                \${data.payAmount}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">付款时间</label>
            <div class="col-md-10">
                \${data.payDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">收货类型</label>
            <div class="col-md-10">
                \${parseReceiveType(data.receiveType)}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">订单状态</label>
            <div class="col-md-10">
                \${parseState(data.orderState)}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">备注</label>
            <div class="col-md-10">
                \${data.remark}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建日期</label>
            <div class="col-md-10">
                \${data.createDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">商品明细</label>
            <div class="col-md-10"></div>
        </div>
        <%--订单商品列表--%>
        {@each data.detailList as item, index}
        <div class="form-group">
            <label class="col-md-2 view-label">商品 \${index}</label>
            <div class="col-md-10">
                <img src="\${item.commodityCover}" class="cover"/>
                <a>\${item.commodityName} x \${item.purchaseQty}</a>
                单价：\${item.salePrice} 元 小计：\${item.subtotal} 元
            </div>
        </div>
        {@/each}
    </div>
</script>
<style>
    .cover {
        width: 50px;
        height: 50px;
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

<%--custom js--%>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>

</html>
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
    <div class="portlet-title hide">
        <div class="caption">

        </div>
        <div class="actions">
            <a href="javascript:void(0);" onclick="add();" class="btn green">创建</a>
        </div>
    </div>
    <div class="portlet-body">
        <!---===================custom-toolbar===========================-->
        <div class="row custom-toolbar">
            <div class="col-sm-5">
                <a onclick="javascript:add()" class="btn green hide">
                    <i class="fa fa-plus"></i> 添加订单管理
                </a>
            </div>
            <div class="col-sm-7">
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
                    <th width="10%"> 订单编号</th>
                    <th width="10%"> 客户名称</th>
                    <th width="10%"> 付款方式</th>
                    <th width="10%"> 订单状态</th>
                    <th width="10%"> 付款金额</th>
                    <th width="15%"> 付款时间</th>
                    <th width="10%"> 收货类型</th>
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
<!--=================custom-body-end======================-->


<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>

<%--============================script piece============================--%>
<%--查看详情--%>
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-detail">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">订单管理详情</h4>
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
                <h4 class="modal-title">订单管理审核</h4>
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

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${item.orderNo}</td>
        <td>
            <div class="row">
                <div class="col-md-3">
                    <img src="\${item.headimgurl}" class="cover"/>
                    <a>\${item.nickname}</a>
                </div>
            </div>
        </td>
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
            <a class="operation" href="javascript:detail('\${item.id}');">查看详情</a>
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
    <table class="table table-bordered table-hover">
        <tr>
            <td class="active"> 订单编码</td>
            <td class="success"> \${data.id}</td>
        </tr>
        <tr>
            <td class="active"> 订单编号</td>
            <td class="success"> \${data.orderNo}</td>
        </tr>
        <tr>
            <td class="active"> 客户姓名</td>
            <td class="success"> \${data.nickname}</td>
        </tr>
        <tr>
            <td class="active"> 付款方式</td>
            <td class="success"> \${parsePayType(data.payType)}</td>
        </tr>
        <tr>
            <td class="active"> 付款金额</td>
            <td class="success"> \${data.payAmount}</td>
        </tr>
        <tr>
            <td class="active"> 付款时间</td>
            <td class="success"> \${data.payDate}</td>
        </tr>
        <tr>
            <td class="active"> 收货类型</td>
            <td class="success"> \${parseReceiveType(data.receiveType)}</td>
        </tr>
        <%--<tr>--%>
        <%--<td class="active"> 收货人姓名</td>--%>
        <%--<td class="success"> \${data.receiveName}</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td class="active"> 收货人电话</td>--%>
        <%--<td class="success"> \${data.receivePhone}</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td class="active"> 收货地址-国家</td>--%>
        <%--<td class="success"> \${data.country}</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td class="active"> 收货地址-省</td>--%>
        <%--<td class="success"> \${data.province}</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td class="active"> 收货地址-市</td>--%>
        <%--<td class="success"> \${data.city}</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td class="active"> 收货地址-区/县</td>--%>
        <%--<td class="success"> \${data.district}</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td class="active"> 收货地址-详细地址</td>--%>
        <%--<td class="success"> \${data.address}</td>--%>
        <%--</tr>--%>
        <tr>
            <td class="active"> 订单状态</td>
            <td class="success"> \${parseState(data.orderState)}</td>
        </tr>
        <tr>
            <td class="active"> 备注</td>
            <td class="success"> \${data.remark}</td>
        </tr>
        <tr>
            <td class="active"> 创建日期</td>
            <td class="success"> \${data.createDate}</td>
        </tr>
        <tr>
            <td class="active"> 商品明细</td>
            <td class="success"></td>
        </tr>
        <%--订单商品列表--%>
        {@each data.detailList as item, index}
        <tr>
            <td class="active">
                <div>
                    <img src="\${item.commodityCover}" class="cover"/>
                    <a>\${item.commodityName} x \${item.purchaseQty}</a>
                </div>
            </td>
            <td class="success">单价：\${item.salePrice} 元 小计：\${item.subtotal} 元</td>
        </tr>
        {@/each}
    </table>
</script>
<style>
    .cover {
        width: 45px;
        height: 45px;
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
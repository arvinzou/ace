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
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>

    <jsp:include page="../../common/base.jsp"/>
    <link rel="stylesheet" href="${portalPath}/content/common/assets/pages/css/profile-2.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/css/components.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css">
    <link rel="stylesheet"
          href="${portalPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
    <script src="${pageContext.request.contextPath}/content/service/order/js/act.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/content/common/js/loader.js?v=${cfg.version}"></script>
</head>

<body class="page-container-bg-solid">
<div class="page-wrapper">
    <div class="page-wrapper-row full-height">
        <div class="page-wrapper-middle">
            <div class="page-container">
                <div class="page-content-wrapper">
                    <div class="page-content">
                        <div class="container">
                            <ul class="page-breadcrumb breadcrumb">
                                <li>
                                    <a href="index4.jsp">首页</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <li>
                                    <span>仪表盘</span>
                                </li>
                            </ul>


                            <div class="page-content-inner">
                                <div class="profile">
                                    <div class="tabbable-line tabbable-full-width">
                                        <ul class="nav nav-tabs">
                                            <li class="active">
                                                <a onclick="javascript:changeType('1')" href="#tab_1_1"
                                                   data-toggle="tab"> 咨询订单 </a>
                                            </li>
                                            <li>
                                                <a onclick="javascript:changeType('2')" href="#tab_1_3"
                                                   data-toggle="tab"> 课程订单 </a>
                                            </li>
                                            <li>
                                                <a onclick="javascript:changeType('3')" href="#tab_1_3"
                                                   data-toggle="tab"> 测试订单 </a>
                                            </li>
                                        </ul>
                                        <div class="portlet-body">
                                            <div class="mt-element-card mt-element-overlay">
                                                <div class="row">
                                                    <div class="col-sm-8">

                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="input-group">
                                                            <input type="text" style="height:33px" class="form-control input-circle-left"
                                                                   placeholder="请输入订单编号">
                                                            <span class="input-group-btn">
                                                                <button class="btn btn-circle-right btn-default search_btn"
                                                                        type="submit">
                                                                    <font style="vertical-align: inherit;"><font
                                                                            style="vertical-align: inherit;">
                                                                        搜索</font></font>
                                                                </button>
                                                            </span>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="row">

                                                    <%--content--%>

                                                    <div class="portlet-body">
                                                        <div class="mt-element-card mt-element-overlay">
                                                            <div class="col-xs-12">

                                                                <%--content--%>
                                                                <div class="table-scrollable table-scrollable-borderless"
                                                                     id="orderList">

                                                                </div>
                                                                <%--content--%>
                                                                <div class="paginationbar">
                                                                <ul class="pagination"
                                                                    id="pagination1"></ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <%--content--%>

                                                </div>
                                            </div>
                                        </div>
                                        <!--tab_1_2-->

                                        <!--end tab-pane-->
                                        </div>
                                    </div>
                            </div>
                        </div>


                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="bottom"></div>

</div>
</div>


<div class="modal fade" id="orderInfoModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="gridSystemModalLabel">订单详情</h4>
            </div>
            <div class="modal-body">
                <div class="table-scrollable" id="orderInfo">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>

<script id="temp_orderList" type="text/template">
    <table class="table table-hover table-light" border="1" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th align="center" width="5%">订单号</th>
            <th align="center" width="10%">用户昵称</th>
            {@if orderCategory == 1}
            <th align="center" width="10%">咨询方式</th>
            {@else if orderCategory == 2}
            <th align="center" width="10%">课程名称</th>
            {@/if}
            <th align="center" width="10%">咨询师名称</th>
            <th align="center" width="10%">付款金额</th>
            <th align="center" width="25%">下单时间</th>
            <th align="center" width="10%">订单状态</th>
            <th align="center" width="20%">操作</th>

        </tr>
        </thead>
        <tbody>
        {@each data as item}
        <tr>
            <td>\${item.id}</td>
            <td>\${item.consumerName}</td>
            <td>\${item.createDate}</td>
            <td>\${item.counselor.name}</td>
            {@if item.category == 1}
            <td>\${formatCProductType(item.consultProduct.type)} x \${item.amount}次</td>
            {@else if item.category == 2}
            <td>课程名称</td>
            {@/if}
            <td>￥\${item.payMoney}</td>
            <td>\${formatPayStatus(item.payStatus)}</td>
            <td><a onclick="javascript:detail('\${item.id}')" class="primary-link">查看详情</a></td>
        </tr>
        {@/each}
        </tbody>
    </table>
</script>

<script id="temp_orderInfo" type="text/template">

    <table class="table table-bordered table-hover">
        <tr>
            <td class="active"> 买家</td>
            <td class="success"> \${data.consumerName}</td>
        </tr>
        <tr>
            <td class="active"> 卖家</td>
            <td class="success"> \${data.counselor.name}</td>
        </tr>
        <tr>
            <td class="active"> 商品名称</td>
            <td class="success"> \${data.commodityName}</td>
        </tr>
        <tr>
            <td class="active"> 订单类型</td>
            <td class="success"> \${formaCategory(data.category)}</td>
        </tr>
        <tr>
            <td class="active"> 数量</td>
            <td class="success"> \${data.amount}</td>
        </tr>

        <tr>
            <td class="active"> 支付金额</td>
            <td class="success"> \${data.payMoney}</td>
        </tr>
        <tr>
            <td class="active"> 订单状态</td>
            <td class="success"> \${formaPayStatus(data.payStatus)}</td>
        </tr>
        <tr>
            <td class="active"> 下单时间</td>
            <td class="success"> \${data.createDate}</td>
        </tr>
    </table>

</script>

<style>


    .table.table-light {
        /*border-bottom: 1px solid red !important;*/
        margin-bottom: 29px !important;
    }

    .table .user-pic {
        height: 80px !important;
    }

    .portlet-body {
        background-color: #fff !important;
    }

    .active {
        color: #333;
    }
</style>


</html>


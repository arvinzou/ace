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
        <meta charset="utf-8" />
        <title>${cfg.sys_name}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="${cfg.sys_name}" name="description" />

        <jsp:include page="../../common/header.jsp" />
        <script src="s/act.js?v=${cfg.version}"></script>
    </head>

    <body>
    <jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

    <!---==============================================-->

                                                <div class="portlet light ">

                                                    <div class="portlet-body">

                                                        <div class="row custom-toolbar">
                                                            <div class="col-md-5">

                                                            </div>

                                                            <div class="col-md-7">
                                                                <form onsubmit="return t_query()">
                                                                    <div class="btn-group" id="btn-group1" role="group"  style="float:left;padding-right:5px">
                                                                        <button type="button" class="btn btn-default" onclick="changeType('1');">咨询</button>
                                                                        <button type="button" class="btn btn-default" onclick="changeType('2');">课程</button>
                                                                        <button type="button" class="btn btn-default" onclick="changeType('3');">评测</button>
                                                                    </div>



                                                                    <div class="input-group">
                                                                        <input type="text" name="keyword" class="form-control" placeholder="请输入订单号">
                                                                        <span class="input-group-btn">
                                                                        <button class="btn  btn-default search_btn" type="submit">
                                                                            搜索
                                                                        </button>
                                                                        </span>
                                                                    </div>
                                                                </form>

                                                            </div>

                                                        </div>

                                                        <div class="table-scrollable" id="orderList">
                                                        </div>
                                                        <div class="paginationbar">
                                                            <ul class="pagination" id="pagination1"></ul>
                                                        </div>
                                                    </div>
                                                </div>
    <!--=======================================-->

    <jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

        <div class="modal fade" id="orderInfoModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
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
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->

    </body>

    <script id="temp_orderList" type="text/template">
        <table class="table table-hover">
            <thead>
                <tr>

                    <th width="15%">用户昵称</th>
                    {@if orderCategory == 1}
                    <th width="30%">咨询方式</th>
                    <th width="10%">咨询师名称</th>
                    {@else if orderCategory == 2}
                    <th width="30%">课程名称</th>
                    <th width="10%">咨询师名称</th>
                    {@else if orderCategory == 3}
                    <th width="40%">评测名称</th>
                    {@/if}
                    <th width="5%">付款金额</th>
                    <th width="15%">下单时间</th>
                    <th width="10%">订单状态</th>
                    <th width="15%">操作</th>
                </tr>
            </thead>
            <tbody>
                {@each data as item}
                <tr>
                    <td>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="row" style="margin: 0px">
                                    <img class="cover" src="\${item.consumerImgUrl}">
                                </div>
                                <div class="row" style="margin: 6px 0px 0px;">
                                    \${item.consumerName}
                                </div>
                            </div>

                        </div>
                    </td>
                    {@if item.category == 1}
                    <td>\${formatCPntType(item.consultProduct.type)} x \${item.amount}次</td>
                    <td>\${item.counselor.name}</td>
                    {@else if item.category == 2}
                    <td>\${item.course.name}</td>
                    <td>\${item.counselor.name}</td>
                    {@else if orderCategory == 3}
                    <td>\${item.commodityName}</td>
                    {@/if}
                    <td>￥\${item.payMoney}</td>
                    <td>\${item.createDate}</td>
                    <td>
                        <span class="label label-lg  \${item.payStatus==1?'label-danger':item.payStatus==2?'label-success':'label-info'}">\${formatPayStatus(item.payStatus)}</span>
                    </td>
                    <td>
                        <a onclick="javascript:detail('\${item.id}')" class="primary-link">查看详情</a>
                    </td>
                </tr>
                {@/each}
            </tbody>
        </table>
    </script>

    <script id="temp_orderInfo" type="text/template">

        <table class="table table-bordered table-hover">
            <tr>
                <td class="active"> 订单编号</td>
                <td class="success"> \${data.id}</td>
            </tr>
            <tr>
                <td class="active"> 用户昵称</td>
                <td class="success"> \${data.consumerName}</td>
            </tr>
            <tr>
                <td class="active"> 咨询师名称</td>
                <td class="success"> \${data.counselor.name}</td>
            </tr>
            <tr>
                <td class="active"> 商品名称</td>
                <td class="success"> \${data.commodityName}</td>
            </tr>
            <tr>
                <td class="active"> 订单类型</td>
                <td class="success"> \${formatCategory(data.category)}</td>
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
                <td class="success"> \${formatPayStatus(data.payStatus)}</td>
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

        .cover {
            width: 50px;
            height: 50px;
            object-fit: cover;
        }

        .portlet-body {
            background-color: #fff !important;
        }

        .active {
            color: #333;
        }
    </style>
    <jsp:include page="/dynamic/common/footer.jsp" />
    <script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
    <script src="js/act.js?v=${cfg.version}"></script>
    </html>

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
    <script src="${pageContext.request.contextPath}/content/service/counselorLevel/js/act.js?v=${cfg.version}"></script>
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
                                    <a href="${pageContext.request.contextPath}/index.jsp">首页</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <li>
                                    <span>咨询师岗位列表</span>
                                </li>
                            </ul>

                            <div class="page-content-inner">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="portlet light ">
                                            <div class="portlet-title">
                                                <div class="caption">
                                                    咨询师岗位列表
                                                </div>
                                            </div>

                                            <div class="portlet-body">
                                                <div class="tabbable-line tabbable-full-width">
                                                    <%--<ul class="nav nav-tabs">--%>
                                                    <%--<li class="active">--%>
                                                    <%--<a onclick="javascript:changeType('1')" href="#tab_1_1"--%>
                                                    <%--data-toggle="tab"> 咨询订单 </a>--%>
                                                    <%--</li>--%>
                                                    <%--<li>--%>
                                                    <%--<a onclick="javascript:changeType('2')" href="#tab_1_3"--%>
                                                    <%--data-toggle="tab"> 课程订单 </a>--%>
                                                    <%--</li>--%>
                                                    <%--<li>--%>
                                                    <%--<a onclick="javascript:changeType('3')" href="#tab_1_3"--%>
                                                    <%--data-toggle="tab"> 测试订单 </a>--%>
                                                    <%--</li>--%>
                                                    <%--</ul>--%>
                                                    <div class="portlet-body">
                                                        <div class="mt-element-card mt-element-overlay">
                                                            <div class="row">
                                                                <div class="col-sm-8">

                                                                </div>
                                                                <div class="col-sm-4">
                                                                    <div class="input-group">
                                                                        <input type="text"
                                                                               class="form-control input-circle-left"
                                                                               placeholder="咨询师名称">
                                                                        <span class="input-group-btn">
                                                                <button class="btn btn-circle-right btn-default search_btn"
                                                                        type="submit">
                                                                        搜索
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
                                                                                 id="data-list">

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
        </div>
    </div>
</div>

<div class="bottom">

</div>

<div class="modal fade" id="counselorLevelModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="" id="levelList" style="display: flex;justify-content: center">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-info modify_btn" flag="true">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>

<script id="tmpl-data-list" type="text/template">
    <table class="table table-hover table-light" align="center" border="0" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th width="20%">咨询师名称</th>
            <th width="10%">当前岗位名称</th>
            <th width="10%">分成比例</th>
            <th width="10%">年份</th>
            <th width="20%">季度</th>
            <th width="10%">累计推广老师人数</th>
            <th width="10%">营业额(元)</th>
            <th width="10%">操作</th>
        </tr>
        </thead>
        <tbody>
        {@each data as item}
        <tr>
            <td>\${item.counselorName}</td>
            <td>\${item.postName}</td>
            <td>\${item.ratio}</td>
            <td>\${item.checkYear}</td>
            <td>\${item.checkQuarter}</td>
            <td>\${item.counselorNum}</td>
            <td>\${item.turnover}</td>
            <td><a href="javascript:void(0);" onclick="javascript:modifyLevel('\${item.id}','\${item.postId}')"
                   class="operation">指定岗位</a></td>
        </tr>
        {@/each}
        </tbody>
    </table>
</script>


<script id="levelListModel" type="text/template">
    <select name="postId" style="width: 50%" size="4" id="postId">
        {@each data as item}
        <option STYLE="padding: 5px" value="\${item.id}">\${item.postName}</option>
        {@/each}
    </select>
</script>

<script id="tmpl-detail-info" type="text/template">

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

    .table .user-pic {
        height: 60px !important;
    }

    .portlet-body {
        background-color: #fff !important;
    }

    .active {
        color: #333;
    }
</style>

</html>


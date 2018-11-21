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

    <jsp:include page="../../common/header.jsp"/>

    <script src="${pageContext.request.contextPath}/content/service/counselorLevel/js/act.js?v=${cfg.version}"></script>

</head>

<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

                                        <div class="portlet light ">
                                                <div class="portlet-body">
                                                    <div class="row custom-toolbar">
                                                        <div class="col-md-1">
                                                            <a onclick="javascript:calculationLevel()"
                                                               class="btn  green">
                                                                计算岗位
                                                            </a>
                                                        </div>
                                                        <div class="col-md-7">

                                                        </div>
                                                        <div class="col-md-4" >

                                                            <div class="input-group">
                                                                <input type="text"
                                                                       class="form-control "
                                                                       placeholder="咨询师名称">
                                                                <span class="input-group-btn">
                                                                <button class="btn  btn-default search_btn"
                                                                        type="submit">
                                                                        搜索
                                                                </button>
                                                            </span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="table-scrollable"
                                                         id="data-list">



                                                    </div>

                                                    <div class="paginationbar">
                                                        <ul class="pagination"
                                                            id="pagination1"></ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />


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


<div class="modal fade" id="calculationLevel" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="form-group" id="" style="display: flex;justify-content: center">
                    <div class="mt-radio-list">
                        <label class="mt-radio mt-radio-outline"> 第一季度
                            <input type="radio" value="1" name="quarter">
                            <span></span>
                        </label>
                        <label class="mt-radio mt-radio-outline"> 第二季度
                            <input type="radio" value="2" name="quarter">
                            <span></span>
                        </label>
                        <label class="mt-radio mt-radio-outline"> 第三季度
                            <input type="radio" value="3" name="quarter">
                            <span></span>
                        </label>
                        <label class="mt-radio mt-radio-outline"> 第四季度
                            <input type="radio" value="4" name="quarter">
                            <span></span>
                        </label>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-info calculation_btn" flag="true">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


</body>

<script id="tmpl-data-list" type="text/template">
    <table class="table table-hover" align="center" border="0" cellpadding="0" cellspacing="0">
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
            <td><a href="javascript:void(0);" onclick="javascript:modifyLevel('\${item.counselorId}','\${item.postId}')"
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
<jsp:include page="/dynamic/common/footer.jsp" />
</html>


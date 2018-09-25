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
    <title>线下活动</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<!--隐藏存放ID-->
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>


<div class="portlet light">

    <div class="portlet-body">

        <div class="row">
            <div class="col-sm-9">

            </div>
            <div class="col-sm-3">
                <form onsubmit="return t_query()">
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control input-left"
                               placeholder="请输入名称">
                        <span class="input-group-btn">
                            <button class="btn btn-right btn-default search_btn" type="submit">
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
                    <th width="40%">活动名称</th>
                    <th width="20%">活动类型</th>
                    <th width="20%">开展时间</th>
                    <th width="5%">活动发布者</th>
                    <th width="10%">活动进度</th>
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

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>


<%--查看详情弹框--%>
<div class="modal fade" role="dialog" id="preview">
    <div class="modal-dialog" role="document" style="width:60%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">活动详情</h4>
            </div>
            <div id="info" class="my-gallery">

                <h5 class="modal-title">基本信息</h5>
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-6">
                            <img src="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=377817030,21862400&fm=27&gp=0.jpg" alt="">
                        </div>
                        <div class="col-md-6">
                            <div class="col-md-6">活动标题</div>
                            <div class="col-md-6">沅江边上捡垃圾</div>
                            <div class="col-md-6">活动地点</div>
                            <div class="col-md-6">丹州河堤边，具体位置待定</div>
                            <div class="col-md-6">活动限定人数</div>
                            <div class="col-md-6">30人</div>
                            <div class="col-md-6">活动开始时间</div>
                            <div class="col-md-6">2018-9-30  9：00</div>
                            <div class="col-md-6">活动截止时间</div>
                            <div class="col-md-6">2018-9-30  9：00</div>
                            <div class="col-md-6">活动结束时间</div>
                            <div class="col-md-6">无</div>
                        </div>
                    </div>
                </div>
                <h5 class="modal-title">发起方信息</h5>
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-2">111</div>
                        <div class="col-md-2">111</div>
                        <div class="col-md-2">111</div>
                        <div class="col-md-2">111</div>
                        <div class="col-md-2">111</div>
                        <div class="col-md-2">111</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h4>活动目的</h4>
                        <div class="col-md-12">111</div>
                        <h4>活动方式</h4>
                        <div class="col-md-12">111</div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</body>

<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td>
            \${item.title}
        </td>
        <td>
            {@if item.category==1}
            公益活动
            {@else if item.category==2}
            普及活动
            {@else if item.category==3}
            创意活动
            {@/if}

        </td>
        <td>
            \${item.startDate}
        </td>
        <td>\${item.initiatorId}</td>
        <td>
            {@if item.status==2}
            <span class="label label-lg label-info">发布审核</span>
            {@else if item.status==3}
            <span class="label label-lg label-success">发布成功</span>
            {@else if item.status==4}
            <span class="label label-lg label-danger">发布驳回</span>
            {@else if item.status==5}
            <span class="label label-lg label-info">结束审核</span>
            {@else if item.status==6}
            <span class="label label-lg label-success">活动成功</span>
            {@else if item.status==7}
            <span class="label label-lg label-danger">活动无效</span>
            {@/if}
        </td>
        <td>
            <a class="operation" href="#" onclick="details('\${item.id}');">查看</a>
            <%--{@if item.status == '2'}--%>
            <%--<a class="operation" href="#" data-toggle="modal" data-target="#modal-audit" data-id="\${item.id}">审核</a>--%>
            <%--{@/if}--%>
            <%--<a class="operation" href="#" onclick="deleteData('\${item.id}');">删除</a>--%>
        </td>
    </tr>
    {@/each}
</script>


<%--<script id="tpl-info" type="text/template">--%>
<%--<div class="modal-body">--%>
<%--<div class="form-horizontal" novalidate="novalidate">--%>
<%--<div class="form-body">--%>
<%--<div class="form-group">--%>
<%--<label class="col-md-2 control-label">--%>
<%--<span class="required" aria-required="true">*</span>--%>
<%--活动名称--%>
<%--</label>--%>
<%--<div class="col-md-9">--%>
<%--<label class="control-label">\${data.title}</label>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group ">--%>
<%--<label class="control-label col-md-2">--%>
<%--<span class="required" aria-required="true">*</span>活动发起者--%>

<%--</label>--%>
<%--<div class="col-md-9">--%>
<%--<label class="control-label">\${data.initiatorId}</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group ">--%>
<%--<label class="control-label col-md-2">--%>
<%--<span class="required" aria-required="true">*</span>活动封面--%>

<%--</label>--%>
<%--<div class="col-md-9">--%>
<%--<img class="headimg" src="\${data.coverUrl}" alt="">--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label class="col-md-2 control-label">--%>
<%--<span class="required" aria-required="true">*</span>开展时间--%>
<%--</label>--%>
<%--<div class="col-md-9">--%>
<%--<label class="control-label">\${data.startDate}</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label class="col-md-2 control-label">--%>
<%--<span class="required" aria-required="true">*</span>结束时间--%>
<%--</label>--%>
<%--<div class="col-md-9">--%>
<%--<label class="control-label">\${data.endDate}</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label class="col-md-2 control-label">--%>
<%--<span class="required" aria-required="true">*</span>志愿者数量--%>
<%--</label>--%>
<%--<div class="col-md-9">--%>
<%--<label class="control-label">\${data.volunteerNum}</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label class="col-md-2 control-label">--%>
<%--<span class="required" aria-required="true">*</span>志愿者积分--%>
<%--</label>--%>
<%--<div class="col-md-9">--%>
<%--<label class="control-label">\${data.volunteerPoints}</label>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label class="col-md-2 control-label">--%>
<%--<span class="required" aria-required="true">*</span>活动摘要--%>
<%--</label>--%>
<%--<div class="col-md-9">--%>
<%--\$\${data.summary}--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label class="col-md-2 control-label">--%>
<%--<span class="required" aria-required="true">*</span>活动内容--%>
<%--</label>--%>
<%--<div class="col-md-9">--%>
<%--\$\${data.content}--%>
<%--</div>--%>
<%--</div>--%>

<%--</div>--%>

<%--{@if data.endDate}--%>
<%--<h4>活动现场签到</h4>--%>
<%--<div class="personnelList">--%>
<%--{@if data.startSignImgUrl}--%>
<%--<div>--%>
<%--<img src="\${data.startSignImgUrl}" alt="">--%>
<%--<div class="msgBar">--%>
<%--活动开始--%>
<%--</div>--%>
<%--</div>--%>
<%--{@/if}--%>
<%--{@if data.endSignImgUrl}--%>
<%--<div >--%>
<%--<img src="\${data.endSignImgUrl}" alt="">--%>
<%--<div class="msgBar">--%>
<%--活动结束--%>
<%--</div>--%>
<%--</div>--%>
<%--{@/if}--%>
<%--</div>--%>
<%--<h4>现场人员签到</h4>--%>
<%--<div class="personnelList">--%>
<%--{@each data.activityDetailVoList as item}--%>
<%--{@if item.signInState==1}--%>
<%--<div class="personnelInfo active_flag">--%>
<%--<img src="\${item.signImgUrl}" alt="">--%>
<%--<div class="msgBar">--%>
<%--\${item.name}--%>
<%--</div>--%>
<%--</div>--%>
<%--{@/if}--%>
<%--{@/each}--%>
<%--</div>--%>
<%--{@/if}--%>
<%--</div>--%>

<%--</div>--%>
<%--<div STYLE="display: flex;padding: 15px;border-top: 1px solid #efefef; align-items: center;justify-content: space-between">--%>
<%--<div style="height: 40px;">--%>
<%--{@if data.status==2}--%>
<%--<select name="rst" class="verify-btn"  style=" height: 100%;width: 80px;">--%>
<%--<option value="3">通过</option>--%>
<%--<option value="4">驳回</option>--%>
<%--</select>--%>
<%--<input name="remark"  class="verify-remark" style="display: none; height: 100%;width: 200px;padding: 0 10px;" type="text" placeholder="原因">--%>
<%--<select name="coinConfigId" id="coinConfig"  style=" height: 100%;">--%>

<%--</select>--%>
<%--<button type="button" class="btn btn-primary btn-audit">确定</button>--%>
<%--{@else if data.status==5}--%>
<%--<select name="rst" style=" height: 100%;width: 80px;">--%>
<%--<option value="6">活动有效</option>--%>
<%--<option value="7">活动无效</option>--%>
<%--</select>--%>
<%--<input name="remark" class="" style="height: 100%;width: 200px;padding: 0 10px;" type="text"--%>
<%--placeholder="原因">--%>
<%--<button type="button" class="btn btn-primary btn-audit">确定</button>--%>
<%--{@/if}--%>
<%--</div>--%>
<%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
<%--</div>--%>
<%--</script>--%>


<%--<script id="tpl-coinConfig" type="text/template">--%>
<%--{@each data as item}--%>
<%--<option value="\${item.id}">\${item.name}</option>--%>
<%--{@/each}--%>
<%--</script>--%>

<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
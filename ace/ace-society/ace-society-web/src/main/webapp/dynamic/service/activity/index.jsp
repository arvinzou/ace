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
    <jsp:include page="/dynamic/common/base.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/act.js?v=${cfg.version}"></script>
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
                                    <span>活动审核</span>
                                </li>
                            </ul>
                            <div class="page-content-inner">

                                <!---==============================================-->

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="portlet light">
                                            <div class="portlet-title">
                                                <div class="caption">
                                                    线下活动
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
                                                            <th width="40%">活动名称</th>
                                                            <th width="20%">活动类型</th>
                                                            <th width="20%">开展时间</th>
                                                            <th width="5%">活动发布者</th>
                                                            <th width="10%">活动进度</th>
                                                            <th width="10%">发布状态</th>
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


<!--审核弹框-->
<%--<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-audit">--%>
<%--<div class="modal-dialog" role="document">--%>
<%--<div class="modal-content">--%>
<%--<div class="modal-header">--%>
<%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>--%>
<%--</button>--%>
<%--<h4 class="modal-title">线下活动审核</h4>--%>
<%--</div>--%>
<%--<div class="modal-body">--%>
<%--<form class="form-horizontal" id="fm-audit" role="form">--%>
<%--<div class="form-body">--%>
<%--<div class="form-group " id="operation">--%>
<%--<label class="col-md-3 control-label">审核结果</label>--%>
<%--<div class="col-md-9">--%>
<%--<div class="radio-group-container">--%>
<%--<label>--%>
<%--<input type="radio" checked name="rst" value="1"><span style="padding:10px">通过</span>--%>
<%--</label>--%>
<%--<label>--%>
<%--<input type="radio" name="rst" value="2"><span style="padding:10px">退回</span>--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label class="col-md-3 control-label">审核说明</label>--%>
<%--<div class="col-md-9">--%>
<%--<input type="hidden" name="id"/>--%>
<%--<textarea name="message" style="width: 100%;height: 100px;"></textarea>--%>
<%--</div>--%>
<%--</div>--%>
<%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;<label class="col-md-3 control-label">扣除爱心币</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-9">&ndash;%&gt;--%>
<%--&lt;%&ndash;<input name="parterPoints" style="width: 100%;padding: 5px;border: #ebedf2 1px solid;">&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;<label class="col-md-3 control-label">奖励爱心币</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="col-md-9">&ndash;%&gt;--%>
<%--&lt;%&ndash;<input name="volunteerPoints" style="width: 100%;padding: 5px;border: #ebedf2 1px solid;">&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--<div class="modal-footer">--%>
<%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
<%--<button type="button" class="btn btn-primary">确定</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
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
            \${item.category}
        </td>
        <td>
            \${item.startDate}
        </td>
        <td>\${item.initiatorId}</td>
        <td>
            {@if item.status==2}
            <span class="label label-lg label-info">审核状态</span>
            {@else if item.status==3}
            <span class="label label-lg label-success">\${item.endDate?'活动结束':'活动开始'}</span>
            {@else if item.status==4}
            <span class="label label-lg label-warning">审核失败</span>
            {@/if}
        </td>
        <td>
            {@if item.status==2}
            <span class="label label-lg label-info">待审核</span>
            {@else if item.status==3}
            <span class="label label-lg label-success">审核通过</span>
            {@else if item.status==4}
            <span class="label label-lg label-warning">审核不通过</span>
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


<script id="tpl-info" type="text/template">
    <div class="modal-body">
        <div class="form-horizontal" novalidate="novalidate">
            <div class="form-body">
                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>
                        活动名称
                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.title}</label>
                    </div>
                </div>

                <div class="form-group ">
                    <label class="control-label col-md-2">
                        <span class="required" aria-required="true">*</span>活动发起者

                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.initiatorId}</label>
                    </div>
                </div>
                <div class="form-group ">
                    <label class="control-label col-md-2">
                        <span class="required" aria-required="true">*</span>活动封面

                    </label>
                    <div class="col-md-9">
                        <img class="headimg" src="\${data.coverUrl}" alt="">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>开展时间
                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.startDate}</label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>结束时间
                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.endDate}</label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>志愿者数量
                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.volunteerNum}</label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>志愿者积分
                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.volunteerPoints}</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>活动摘要
                    </label>
                    <div class="col-md-9">
                        \$\${data.summary}
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>活动内容
                    </label>
                    <div class="col-md-9">
                        \$\${data.content}
                    </div>
                </div>

            </div>

            {@if data.endDate}
            <h4>活动现场签到</h4>
            <div class="personnelList">
                {@if data.startSignImgUrl}
                <div>
                    <img src="\${data.startSignImgUrl}" alt="">
                    <div class="msgBar">
                        活动开始
                    </div>
                </div>
                {@/if}
                {@if data.endSignImgUrl}
                <div >
                    <img src="\${data.endSignImgUrl}" alt="">
                    <div class="msgBar">
                        活动结束
                    </div>
                </div>
                {@/if}
            </div>
            <h4>现场人员签到</h4>
            <div class="personnelList">
                {@each data.activityDetailVoList as item}
                {@if item.signInState==1}
                <div class="personnelInfo active_flag">
                    <img src="\${item.signImgUrl}" alt="">
                    <div class="msgBar">
                        \${item.name}
                    </div>
                </div>
                {@/if}
                {@/each}
            </div>
            {@/if}
        </div>

    </div>
    <div STYLE="display: flex;padding: 15px;border-top: 1px solid #efefef; align-items: center;justify-content: space-between">
        <div style="height: 40px;">
            {@if data.status==2}
            <select name="rst" id="" style=" height: 100%;width: 80px;">
                <option value="1">通过</option>
                <option value="2">驳回</option>
            </select>
            <input name="remark" class="" style="height: 100%;width: 200px;padding: 0 10px;" type="text"
                   placeholder="原因">
            <button type="button" class="btn btn-primary btn-audit">确定</button>
            {@/if}
        </div>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    </div>
</script>


</html>
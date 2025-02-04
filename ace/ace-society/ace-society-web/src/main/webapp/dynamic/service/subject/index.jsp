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
    <title>方案提议</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <%--common css--%>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
    <%--custom css--%>
</head>
<body>
<%--==============common jsp-prefix==============--%>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-prefix==============--%>
<div class="portlet light">
    <div class="portlet-body">
        <div class="row custom-toolbar">
                <div class="col-sm-4">
                    <a href="javascript:void(0);" onclick="add();" style="font-size: 14px !important;"
                       class="btn green specialCourse"><i class="fa fa-plus"></i> 创建</a>
                </div>
                <div class="col-sm-8">
                    <form onsubmit="return t_query()">
                        <div class="btn-group" role="group"  style="float:left;padding-right:5px">
                            <button type="button" class="btn btn-default"  onclick="setParams('status','');">全部</button>
                            <button type="button" class="btn btn-default"  onclick="setParams('status','2');">待审</button>
                            <button type="button" class="btn btn-default" onclick="setParams('status','3');">通过</button>
                            <button type="button" class="btn btn-default" onclick="setParams('status','4');">驳回</button>
                        </div>
                        <div class="input-group">
                            <input type="text"
                                   name="keyword"
                                   class="form-control input-left"
                                   placeholder="请输入名称">
                            <span class="input-group-btn">
                                                        <button class="btn btn-right btn-default search_btn"
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
                    <th width="10%">序号</th>
                    <th width="20%"> 议题标题</th>
                    <th width="10%"> 奖励积分</th>
                    <th width="15%"> 创建人姓名</th>
                    <th width="15%"> 创建日期</th>
                    <th width="10%"> 状态</th>
                    <th width="20%">操作</th>
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

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${parseInt(index)+1}</td>
        <td> \${item.title}</td>
        <td> \${item.rewardPoints}</td>
        <td> \${item.createUserName}</td>
        <td> \${item.createDate}</td>
        <td>
            {@if item.status==0}
            <span class="label label-lg label-danger">已删除</span>
            {@else if item.status==1}
            <span class="label label-lg label-info">暂存</span>
            {@else if item.status==2}
            <span class="label label-lg label-warning">提交审核</span>
            {@else if item.status==3}
            <span class="label label-lg label-info">审核通过</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@else if item.status==4}
            <span class="label label-lg label-info">被驳回</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@/if}
        </td>
        <td>
            <a class="operation" href="javascript:detail('\${item.id}');">查看</a>
            <a class="operation" href="javascript:edit('\${item.id}');">编辑</a>
            <a class="operation" href="javascript:showIdea('\${item.id}');">解决方案</a>
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
            <label class="col-md-2 view-label">议题标题</label>
            <div class="col-md-10">
                \${data.title}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">议题描述</label>
            <div class="col-md-10">
                \$\${data.content}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">奖励积分</label>
            <div class="col-md-10">
                \${data.rewardPoints}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">备注</label>
            <div class="col-md-10">
                \${data.remark}
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 view-label">状态</label>
            <div class="col-md-10">
                {@if data.status == '0'}
                删除
                {@else if data.status == '1'}
                暂存
                {@else if data.status == '2'}
                提交审核
                {@else if data.status == '3'}
                审核通过
                {@else if data.status == '4'}
                审核驳回
                {@/if}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建人姓名</label>
            <div class="col-md-10">
                \${data.createUserName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建日期</label>
            <div class="col-md-10">
                \${data.createDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">更新人名称</label>
            <div class="col-md-10">
                \${data.lastModifyUserName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">更新日期</label>
            <div class="col-md-10">
                \${data.lastModifyDate}
            </div>
        </div>
    </div>
</script>


<!--审核模板-->
<script id="tpl-fm" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 view-label">议题标题</label>
            <div class="col-md-10">
                \${data.title}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">议题描述</label>
            <div class="col-md-10">
                \$\${data.content}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">奖励积分</label>
            <div class="col-md-10">
                \${data.rewardPoints}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">备注</label>
            <div class="col-md-10">
                \${data.remark}
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 view-label">状态</label>
            <div class="col-md-10">
                {@if data.status == '0'}
                删除
                {@else if data.status == '1'}
                暂存
                {@else if data.status == '2'}
                提交审核
                {@else if data.status == '3'}
                审核通过
                {@else if data.status == '4'}
                审核驳回
                {@/if}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建人姓名</label>
            <div class="col-md-10">
                \${data.createUserName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建日期</label>
            <div class="col-md-10">
                \${data.createDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">更新人名称</label>
            <div class="col-md-10">
                \${data.lastModifyUserName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">更新日期</label>
            <div class="col-md-10">
                \${data.lastModifyDate}
            </div>
        </div>
        <h4>结果</h4>
        <hr>
        <div class="form-group " id="operation">
            <label class="col-md-2 control-label">结果</label>
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
            <label class="col-md-2 control-label">说明</label>
            <div class="col-md-10">
                <input type="hidden" name="id" value="\${data.id}">
                <textarea name="remark" style="width: 100%;height: 100px;"></textarea>
            </div>
        </div>
    </div>
</script>

<%--查看详情--%>
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-detail">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">议题详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-detail" role="form">
                    <div class="form-body">
                        <div id="detail-info">
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
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">方案提议审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-audit" role="form">
                    <div class="form-body" id="auditContent">
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
<%--==============common footer==============--%>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/common/js/jquery-form.js"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
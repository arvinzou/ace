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
    <title>提现申请记录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-4">
                <%--<a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>--%>
            </div>

            <div class="col-md-8">
                <form onsubmit="return t_query()">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="setParams('auditRst','');">全部</button>
                        <button type="button" class="btn btn-default"
                                onclick="setParams('auditRst','temp');">待审
                        </button>
                        <button type="button" class="btn btn-default"
                                onclick="setParams('auditRst','pass');">通过
                        </button>
                        <button type="button" class="btn btn-default"
                                onclick="setParams('auditRst','reject');">拒绝
                        </button>
                    </div>

                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入咨询师名称">
                        <span class="input-group-btn">
                            <button class="btn  btn-default search_btn" type="submit"> 搜索</button>
                        </span>
                    </div>
                </form>
            </div>

        </div>


        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="10%"> 咨询师</th>
                    <th width="10%"> 真实姓名</th>
                    <th width="10%"> 提现方式</th>
                    <th width="10%"> 申请金额</th>
                    <th width="10%"> 扣减金额</th>
                    <th width="10%"> 到账金额</th>
                    <th width="10%"> 申请时间</th>
                    <th width="10%"> 审核状态</th>
                    <th width="10%"> 转账状态</th>
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


<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${item.teacherName}</td>
        <td> \${item.realName}</td>
        <td>
            {@if item.withdrawType == '1'}
            <span class="label label-lg label-info">\${parseType(item.withdrawType)}</span>
            {@else if item.auditRst == '2'}
            <span class="label label-lg label-success">\${parseType(item.withdrawType)}</span>
            {@else}
            <span class="label label-lg label-danger">\${parseType(item.withdrawType)}</span>
            {@/if}
        </td>
        <td> \${item.applyAmount}</td>
        <td> \${item.taxAmount}</td>
        <td> \${item.actAmount}</td>
        <td> \${item.createDate}</td>
        <td>
            {@if item.auditRst == 'temp'}
            <span class="label label-lg label-info">\${parseAuditRst(item.auditRst)}</span>
            {@else if item.auditRst == 'pass'}
            <span class="label label-lg label-success">\${parseAuditRst(item.auditRst)}</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@else if item.auditRst=='reject'}
            <span class="label label-lg label-danger">\${parseAuditRst(item.auditRst)}</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@else}
            <span class="label label-lg label-info">\${parseAuditRst(item.auditRst)}</span>
            {@/if}
        </td>
        <td>
            {@if item.apiRst == 'SUCCESS'}
            <span class="label label-lg label-success">\${parseApiRst(item.apiRst)}</span>
            {@else if item.apiRst == 'FAILED'}
            <span class="label label-lg label-danger">\${parseApiRst(item.apiRst)}</span>
            <div style="padding-top:10px">\${item.apiRemark}</div>
            {@else}
            <span class="label label-lg label-danger">\${parseApiRst(item.apiRst)}</span>
            {@/if}
        </td>
        <td>
            <%--<a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>--%>
            {@if item.auditRst == 'temp'}
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}" data-target="#modal-audit">审核</a>
            {@else if item.auditRst=='pass'}
            <a href="javaScript:transferInfo()">转账详情</a>
            <%--<a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}" data-target="#modal-wx">转账详情</a>--%>
            {@/if}
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>

        </td>
    </tr>
    {@/each}
</script>
<!--审核弹框-->
<div class="modal fade" role="dialog" id="modal-audit">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-audit" role="form">

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn green audit">确定</button>
            </div>
        </div>
    </div>
</div>
<%--查看--%>
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-preview">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script id="tpl-fm" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 view-label">申请日期</label>
            <div class="col-md-10"> \${data.o.createDate}</div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">咨询师</label>
            <div class="col-md-10"> \${data.o.teacherName}</div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">真实姓名</label>
            <div class="col-md-10"> \${data.o.realName}</div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">提现方式</label>
            <div class="col-md-10"> \${data.o.withdrawType}</div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">申请金额</label>
            <div class="col-md-10"> \${data.o.applyAmount}</div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">扣减金额</label>
            <div class="col-md-10"> \${data.o.taxAmount}</div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">到账金额</label>
            <div class="col-md-10"> \${data.o.actAmount}</div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">备注</label>
            <div class="col-md-10"> \${data.o.remark}</div>
        </div>
        <h4>结果</h4>
        <hr>
        <div class="form-group " id="operation">
            <label class="col-md-2 control-label">结果</label>
            <div class="col-md-10">
                <div class="radio-group-container">
                    <label>
                        <input type="radio" name="rst" value="pass"><span style="padding:10px">通过</span>
                    </label>
                    <label>
                        <input type="radio" name="rst" value="reject"><span style="padding:10px">驳回</span>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">说明</label>
            <div class="col-md-10">
                <input type="hidden" name="id" value="\${data.o.id}">
                <textarea name="text" style="width: 100%;height: 100px;"></textarea>
            </div>
        </div>
    </div>

</script>

<script id="tpl-preview" type="text/template">
    <div class="form-group">
        <label class="col-md-2 view-label">交易号</label>
        <div class="col-md-10"> \${data.o.id}</div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">申请日期</label>
        <div class="col-md-10"> \${data.o.createDate}</div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">咨询师</label>
        <div class="col-md-10"> \${data.o.teacherName}</div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">真实姓名</label>
        <div class="col-md-10"> \${data.o.realName}</div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">提现方式</label>
        <div class="col-md-10"> \${parseType(data.o.withdrawType)}</div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">申请金额</label>
        <div class="col-md-10"> \${data.o.applyAmount}</div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">扣减金额</label>
        <div class="col-md-10"> \${data.o.taxAmount}</div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">到账金额</label>
        <div class="col-md-10"> \${data.o.actAmount}</div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">审核状态</label>
        <div class="col-md-10"> \${parseAuditRst(data.o.auditRst)}</div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">审核备注</label>
        <div class="col-md-10"> \${data.o.auditRemark}</div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">转账状态</label>
        <div class="col-md-10"> \${parseApiRst(data.o.apiRst)}</div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">接口信息</label>
        <div class="col-md-10"> \${data.o.apiRemark}</div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">备注</label>
        <div class="col-md-10"> \${data.o.remark}</div>
    </div>
</script>
<style>
    .cover {
        width: 70px;
        height: 70px;
        object-fit: cover;
    }

    .describtion {
        padding-left: 15px;
        height: 50px;
    }

    .cost {
        padding-top: 5px;
        padding-left: 15px;
        color: #FE6500;
    }
</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
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
    <title>试题管理</title>
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
                <a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>
            </div>

            <div class="col-md-8">

                <form id="fm-search">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="setParams('type','');">全部</button>
                        <button type="button" class="btn btn-default" onclick="setParams('type','1');">单选题</button>
                        <button type="button" class="btn btn-default" onclick="setParams('type','2');">多选题</button>
                        <button type="button" class="btn btn-default" onclick="setParams('type','3');">判断题</button>
                        <button type="button" class="btn btn-default" onclick="setParams('type','4');">问答题</button>
                        <button type="button" class="btn btn-default" onclick="setParams('type','5');">打分题</button>
                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入题目名称">
                        <span class="input-group-btn">
                            <button class="btn  btn-default search_btn"
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
                    <th width="30%"> 题目内容</th>
                    <th width="20%"> 题目类型</th>
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


<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${item.content}</td>
        <td> \${parseStatus(item.type)}</td>
        <td>
            ﻿<a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>
            <a href="javascript:del('\${item.id}');">删除</a>
        </td>
    </tr>
    {@/each}
</script>
﻿
<div class="modal fade " id="modal-status">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">设置状态</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-status" role="form">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-2 view-label">对象</label>
                            <div class="col-md-10 status-title">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">状态</label>
                            <div class="col-md-10">
                                <div class="radio-group-container">
                                    <input type="hidden" name="id">
                                    <label>
                                        <input type="radio" name="status" value="1"><span style="padding:10px">预播</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="status" value="2"><span
                                            style="padding:10px">直播中</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="status" value="3"><span style="padding:10px">历史</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn green status">确定</button>
            </div>
        </div>
    </div>
</div>


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

        <div class="form-group hide">
            <label class="col-md-2 view-label">主键</label>
            <div class="col-md-10">
                \${id}
            </div>
        </div>
        <div class="form-group hide">
            <label class="col-md-2 view-label">测试主键</label>
            <div class="col-md-10">
                \${testId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">分值</label>
            <div class="col-md-10">
                \${score}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">题目内容</label>
            <div class="col-md-10">
                \${content}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">题目类型</label>
            <div class="col-md-10">
                \${type}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">题目序号</label>
            <div class="col-md-10">
                \${index}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">测试分析</label>
            <div class="col-md-10">
                \${analysis}
            </div>
        </div>
        <div class="form-group hide">
            <label class="col-md-2 view-label">创建人编号</label>
            <div class="col-md-10">
                \${createUserId}
            </div>
        </div>
        <div class="form-group hide">
            <label class="col-md-2 view-label">创建人姓名</label>
            <div class="col-md-10">
                \${createUserName}
            </div>
        </div>
        <div class="form-group hide">
            <label class="col-md-2 view-label">创建日期</label>
            <div class="col-md-10">
                \${createDate}
            </div>
        </div>
        <div class="form-group hide">
            <label class="col-md-2 view-label">更新人编号</label>
            <div class="col-md-10">
                \${lastModifyUserId}
            </div>
        </div>
        <div class="form-group hide">
            <label class="col-md-2 view-label">更新人名称</label>
            <div class="col-md-10">
                \${lastModifyUserName}
            </div>
        </div>
        <div class="form-group hide">
            <label class="col-md-2 view-label">更新日期</label>
            <div class="col-md-10">
                \${lastModifyDate}
            </div>
        </div>


        <h4>结果</h4>
        <hr>
        <div class="form-group " id="operation">
            <label class="col-md-2 control-label">结果</label>
            <div class="col-md-10">
                <div class="radio-group-container">
                    <label>
                        <input type="radio" name="rst" value="2"><span style="padding:10px">通过</span>
                    </label>
                    <label>
                        <input type="radio" name="rst" value="3"><span style="padding:10px">退回</span>
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
    <div class="form-group hide">
        <label class="col-md-2 view-label">主键</label>
        <div class="col-md-10">
            \${id}
        </div>
    </div>
    <div class="form-group hide">
        <label class="col-md-2 view-label">测试主键</label>
        <div class="col-md-10">
            \${testId}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">分值</label>
        <div class="col-md-10">
            \${score}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">题目内容</label>
        <div class="col-md-10">
            \${content}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">题目类型</label>
        <div class="col-md-10">
            \${type}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">题目序号</label>
        <div class="col-md-10">
            \${index}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">测试分析</label>
        <div class="col-md-10">
            \${analysis}
        </div>
    </div>
    <div class="form-group hide">
        <label class="col-md-2 view-label">创建人编号</label>
        <div class="col-md-10">
            \${createUserId}
        </div>
    </div>
    <div class="form-group hide">
        <label class="col-md-2 view-label">创建人姓名</label>
        <div class="col-md-10">
            \${createUserName}
        </div>
    </div>
    <div class="form-group hide">
        <label class="col-md-2 view-label">创建日期</label>
        <div class="col-md-10">
            \${createDate}
        </div>
    </div>
    <div class="form-group hide">
        <label class="col-md-2 view-label">更新人编号</label>
        <div class="col-md-10">
            \${lastModifyUserId}
        </div>
    </div>
    <div class="form-group hide">
        <label class="col-md-2 view-label">更新人名称</label>
        <div class="col-md-10">
            \${lastModifyUserName}
        </div>
    </div>
    <div class="form-group hide">
        <label class="col-md-2 view-label">更新日期</label>
        <div class="col-md-10">
            \${lastModifyDate}
        </div>
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

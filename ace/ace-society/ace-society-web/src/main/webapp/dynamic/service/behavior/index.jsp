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
    <title>市民行为列表</title>
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
                                    <span>市民行为审核</span>
                                </li>
                            </ul>
                            <div class="page-content-inner">

                                <!---==============================================-->

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="portlet light">
                                                    <div class="portlet-title">
                                                        <div class="caption">
                                                            市民行为审核
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
                                                                        <th>序号</th>
                                                                        <th width="35%">标题</th>
                                                                        <th width="15%">提交人</th>
                                                                        <th width="15%">反馈日期</th>
                                                                        <th width="15%">状态</th>
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

<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td>\${parseInt(index)+1}</td>
        <td width="35%">\${item.title}</td>
        <td width="15%">\${item.userId}</td>
        <td width="15%">\${item.submitDate}</td>
        <td width="15%">
            {@if item.status == '0'}
            <span>删除</span>
            {@else if item.status == '1'}
            <span>暂存</span>
            {@else if item.status == '2'}
            <span>提交审核</span>
            {@else if item.status == '3'}
            <span>审核通过</span>
            {@else if item.status == '4'}
            <span>审核驳回</span>
            {@/if}
        </td>
        <td width="20%">
            <a class="operation" href="#" data-toggle="modal" data-target="#modal-show" data-id="\${item.id}">查看</a>
            {@if item.status == '2'}
            <a class="operation" href="#" data-toggle="modal" data-target="#modal-audit" data-id="\${item.id}">审核</a>
            {@/if}
            <a class="operation" href="javascript:void(0);" onclick="delete('\${item.id}');">删除</a>
        </td>
    </tr>
    {@/each}
</script>


<!--审核弹框-->
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-audit">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">市民行为详情审核</h4>
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
                                <textarea name="remark" style="width: 100%;height: 100px;"></textarea>
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

<!--查看弹框-->
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-show">
<div class="modal-dialog" role="document" style="width: 100%;height: 100%;">
    <div class="modal-content" style="width: 70%;height: 90%; margin: 0 auto;position: relative;">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title">文明随手拍详情</h4>
        </div>
        <div class="modal-body">
            <div class="table-body">
                <input type="hidden" name="id"/>
                <div id="content">

                </div>
            </div>
        </div>
        <div class="modal-footer" style="position: absolute;bottom: 0;">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        </div>
    </div>
</div>
</div>

<script id="tpl-detail" type="text/template">
    <table cellpadding="0" cellspacing="0" border="1" class="detailTable">
        <tr class="smallTr">
            <td width="15%">标题</td>
            <td width="35%">\${data.title}</td>
            <td width="15%">提交人</td>
            <td width="35%">\${data.userId}</td>
        </tr>
        <tr class="smallTr">
            <td width="15%">简述</td>
            <td colspan="3">\${data.compendium}</td>
        </tr>
        <tr class="largeTr">
            <td width="15%">备注</td>
            <td colspan="3">\${data.remark}</td>
        </tr>
    </table>
    {@each data.behaviorAnnexList as item, index}
    <table cellpadding="0" cellspacing="0" border="1" class="fileTable">
        <tr class="smallTr">
            <td width="15%">附件名称</td>
            <td width="35%">\${item.fileName}</td>
            <td width="15%">附件大小</td>
            <td width="35%">\${item.fileSize}kb</td>
        </tr>
        <tr>
            <td width="15%">附件内容</td>
            <td colspan="3">
                {@if item.fileType == '0'}
                <a href="\${item.fileUrl}" download="\${item.fileName}">\${item.fileUrl}</a>
                {@else if item.fileType == '1'}
                <img src="\${item.fileUrl}" style="width: 300px;height: 150px;"/>
                {@else if item.fileType == '2'}
                <video src="\${item.fileUrl}" width="300" height="150" controls></video>
                {@/if}
            </td>
        </tr>
    </table>
    {@/each}
</script>
</body>
<style>
    .cover{
        width: 70px;
        height: 70px;
        object-fit: cover;
    }
    .describtion{
        padding-left:15px;
        height:50px;
    }
    .cost{
          padding-top: 5px;
          padding-left:15px;
          color:#FE6500;
    }
</style>
</html>
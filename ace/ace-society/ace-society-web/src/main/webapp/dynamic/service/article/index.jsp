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
    <title>文章</title>
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
                                    <span>文章</span>
                                </li>
                            </ul>
                            <div class="page-content-inner">

                                <!---==============================================-->

<div class="row">
                                            <div class="col-md-12">
                                                <div class="portlet light">
                                                    <div class="portlet-title">
                                                        <div class="caption">
                                                            文章
                                                        </div>
                                                        <div class="actions">
                                                             <a href="javascript:void(0);" onclick="add();"  class="btn green">创建</a>
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
                                                                        <th width="35%">咨询师</th>
                                                                        <th width="15%">状态</th>
                                                                        <th width="5%">咨询数</th>
                                                                        <th width="5%">创建课程</th>
                                                                        <th width="15%">审核状态</th>

                                                                         <th width="25%">操作</th>
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

        <td >
            <div class="row">
                <div class="col-md-3"><img src="\${item.cover}" class="cover"/></div>
                <div class="col-md-9">
                    <div class="describtion">\${item.name}</div>
                    <div class="cost">\${item.srcCount}节
                        {@if item.costType != '0'}
                        ￥\${item.cost}
                        {@else}
                        免费
                        {@/if}
                    </div>
                </div>
            </div>
        </td>

        <td>
            {@if item.auditRst==1}
            <div>
                {@if item.lineState == 1}
                \${item.onlineDate}
                {@else}
                \${item.offlineDate}
                {@/if}
            </div>
            <div style="padding:10px">
                {@if item.lineState!=1}
                <span style="color:red">已下架</span>
                {@else}
                <a href="#">已上架</a>
                {@/if}
            </div>
            {@/if}
        </td>
        <td>\${item.duration}</td>
        <td>
            {@if item.status==0}
            <span class="label label-lg label-info">待审核</span>
            {@else if item.status==1}
            <span class="label label-lg label-success">审核通过</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@else}
            <span class="label label-lg label-danger">审核不通过</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@/if}
        </td>
        <td>
            <a class="operation" href="javascript:edit('\${item.id}');">编辑</a>
            <a class="operation" href="javascript:del('\${item.id}');">删除</a>
             {@if item.status==0}
                 <a class="operation" href="javascript:void(0);" data-toggle="modal" data-target="#modal-audit" data-id="\${item.id}">审核</a>
             {@/if}
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
                <h4 class="modal-title">文章审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-audit" role="form">
                      <div class="form-body">
                        <div class="form-group " id="operation">
                            <label class="col-md-2 control-label">审核结果</label>
                            <div class="col-md-10">
                                <div class="radio-group-container">
                                    <label>
                                        <input type="radio" name="rst" value="1"><span style="padding:10px">通过</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="rst" value="2"><span style="padding:10px">退回</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">审核说明</label>
                            <div class="col-md-10">
                                <input type="hidden" name="id"/>
                                <textarea name="message" style="width: 100%;height: 100px;"></textarea>
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
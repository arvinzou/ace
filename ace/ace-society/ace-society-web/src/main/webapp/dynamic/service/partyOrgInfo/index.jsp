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
    <title>党组织信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <%--common css--%>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/photoswipe.css">
    <link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/default-skin/default-skin.css">
    <%--custom css--%>
</head>
<body>
<%--==============common jsp-prefix==============--%>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-prefix==============--%>

<div class="portlet light">
    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-5">

            </div>
            <div class="col-sm-7">
                <form onsubmit="return t_query()">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="setParams('status','');">全部</button>
                        <%--<button type="button" class="btn btn-default"  onclick="setParams('status','0');">已删除</button>--%>
                        <button type="button" class="btn btn-default" onclick="setParams('status','1');">暂存</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','2');">待审核</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','3');">已通过</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','4');">被驳回</button>
                    </div>

                    <div class="input-group">
                        <input type="text" name="keyword" class="form-control " placeholder="请输入组织姓名">
                        <span class="input-group-btn">
                            <button class="btn  btn-default search_btn" type="submit">搜索</button>
                        </span>
                    </div>
                </form>
            </div>

        </div>


        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="10%"> 组织名称</th>
                    <%--<th width="10%"> 组织类型</th>--%>
                    <th width="10%"> 联系人姓名</th>
                    <th width="10%"> 联系人手机号</th>
                    <th width="10%"> 累计获得爱心币</th>
                    <th width="10%"> 有效爱心币</th>
                    <th width="10%"> 注册状态</th>
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



<div id="j-pswp" class="pswp" role="dialog" aria-hidden="true">
    <div class="pswp__bg"></div>
    <div class="pswp__scroll-wrap">
        <div class="pswp__container">
            <div class="pswp__item"></div>
            <div class="pswp__item"></div>
            <div class="pswp__item"></div>
        </div>
        <div class="pswp__ui pswp__ui--hidden">
            <div class="pswp__top-bar">
                <div class="pswp__counter"></div>
                <button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
                <button class="pswp__button pswp__button--share" title="Share"></button>
                <button class="pswp__button pswp__button--fs" title="Toggle fullscreen"></button>
                <button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
                <div class="pswp__preloader">
                    <div class="pswp__preloader__icn">
                        <div class="pswp__preloader__cut">
                            <div class="pswp__preloader__donut"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
                <div class="pswp__share-tooltip"></div>
            </div>
            <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)"></button>
            <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)"></button>
            <div class="pswp__caption">
                <div class="pswp__caption__center"></div>
            </div>
        </div>
    </div>
</div>

</body>
<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${item.orgName}</td>
        <%--<td> \${item.orgType}</td>--%>
        <td> \${item.contactPerson}</td>
        <td> \${item.contactPhone}</td>
        <td> \${item.accPoints}</td>
        <td> \${item.validPoints}</td>
        <td>
            {@if item.status==0}
            <span class="label label-lg label-danger">已删除</span>
            {@else if item.status==1}
            <span class="label label-lg label-primary">暂存</span>
            {@else if item.status==2}
            <span class="label label-lg label-info">待审审核</span>
            {@else if item.status==3}
            <span class="label label-lg label-success">审核通过</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@else if item.status==4}
            <span class="label label-lg label-danger">被驳回</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@else}
            <span class="label label-lg label-info">暂存</span>
            {@/if}
        </td>
        <td>
            <a class="operation" href="javascript:detail('\${item.id}');">查看</a>
            <%--<a class="operation" href="javascript:edit('\${item.id}');">编辑</a>--%>
            <%--<a class="operation" href="javascript:del('\${item.id}');">删除</a>--%>
            <a class="operation" href="#" data-toggle="modal" data-target="#modal-admin" data-id="\${item.id}">负责人</a>
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
            <label class="col-md-2 view-label">组织名称</label>
            <div class="col-md-10">
                \${data.orgName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">组织类型</label>
            <div class="col-md-10">
                {@if data.orgType == "2"}
                社会组织
                {@else if data.orgType == "1"}
                党组织
                {@/if}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">组织封面</label>
            <div class="col-md-10 my-gallery">
                <img src="\${data.orgCover}"/>
            </div>
        </div>
        <%-- <div class="form-group">
             <label class="col-md-2 view-label">组织介绍</label>
             <div class="col-md-10">
                 \${data.orgSummary}
             </div>
         </div>--%>

        <div class="form-group">
            <label class="col-md-2 view-label">组织地址</label>
            <div class="col-md-10">
                \${data.orgAddr}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">联系人姓名</label>
            <div class="col-md-10">
                \${data.contactPerson}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">联系人手机号</label>
            <div class="col-md-10">
                \${data.contactPhone}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">累计获得爱心币</label>
            <div class="col-md-10">
                \${data.accPoints}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">有效爱心币</label>
            <div class="col-md-10">
                \${data.validPoints}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">注册状态</label>
            <div class="col-md-10">
                \${parseStatus(data.status)}
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
                <h4 class="modal-title">社会组织信息详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-detail" role="form">
                    <%--详情模板填充--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<%--组织管理员 -- 列表juicer模板--%>
<script id="tpl-fm-admin" type="text/template">
    <tr>
        {@if data == null}
        <td>
            <div class="row">
                <div class="col-md-12">
                    <img src="${pageContext.request.contextPath}/content/common/img/default_header.png" class="cover"/>
                    <a>暂无</a>
                </div>
            </div>
        </td>
        <td> 暂无</td>
        <td> 暂无</td>
        <td> 暂无</td>
        {@else}
        <td>
            <div class="row">
                <div class="col-md-12">
                    {@if data.headimgurl!=null && data.headimgurl !='' && data.headimgurl.length>3}
                    <img src="\${data.headimgurl}" class="cover"/>
                    {@else}
                    <img src="${pageContext.request.contextPath}/content/common/img/default_header.png" class="cover"/>
                    {@/if}
                    <a>\${data.nickname}</a>
                </div>
            </div>
        </td>
        <td> \${data.realName}</td>
        <td> \${data.mobilePhone}</td>
        <td>
            <a class="operation" href="javascript:removeAdmin('\${data.orgId}');"> 移除 </a>
        </td>
        {@/if}
    </tr>
</script>

<!--组织管理员管理 -->
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-admin">
    <div class="modal-dialog" role="document" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">负责人信息</h4>
            </div>
            <div class="modal-body">
                <div class="row custom-toolbar">
                    <div class="col-md-5">
                        <a class="btn  green"> 选择 </a>&nbsp;&nbsp;
                        <select class="easyui-combogrid" style="width:300px; height: 30px; line-height: 30px;"
                                id="combogrid-admin"></select>
                    </div>
                    <div class="col-sm-7">

                    </div>
                </div>
                <div class="table-scrollable">
                    <table class="table table-hover" style="width: 80%">
                        <thead>
                        <tr>
                            <th width="30%"> 微信昵称</th>
                            <th width="30%"> 姓名</th>
                            <th width="30%"> 手机号码</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody id="admin-list">

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!--审核弹框-->
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-audit">
    <div class="modal-dialog" role="document" style="width: 90%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">社会组织信息审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-audit" role="form">

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>
<%--审核渲染模板--%>
<script id="tpl-fm-audit" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 view-label">组织名称</label>
            <div class="col-md-10">
                \${data.orgName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">组织类型</label>
            <div class="col-md-10">
                {@if data.orgType == '1'}
                党组织
                {@else if data.orgType == '2'}
                社会组织
                {@/if}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">组织封面</label>
            <div class="col-md-10 my-gallery">
                <img src="\${data.orgCover}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">组织介绍</label>
            <div class="col-md-10">
                \${data.orgSummary}
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 view-label">组织地址</label>
            <div class="col-md-10">
                \${data.orgAddr}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">联系人姓名</label>
            <div class="col-md-10">
                \${data.contactPerson}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">联系人手机号</label>
            <div class="col-md-10">
                \${data.contactPhone}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">累计获得爱心币</label>
            <div class="col-md-10">
                \${data.accPoints}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">有效爱心币</label>
            <div class="col-md-10">
                \${data.validPoints}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">注册状态</label>
            <div class="col-md-10">
                \${parseStatus(data.status)}
            </div>
        </div>
        <h4>结果</h4>
        <hr>
        <div class="form-group " id="operation">
            <label class="col-md-2 control-label">审核说明</label>
            <div class="col-md-10">
                <div class="radio-group-container">
                    <label>
                        <input type="radio" name="rst" value="3"><span style="padding:10px">通过</span>
                    </label>
                    <label>
                        <input type="radio" name="rst" value="4"><span style="padding:10px">驳回</span>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">审核说明</label>
            <div class="col-md-10">
                <input type="hidden" name="id" value="\${data.id}">
                <textarea name="message" style="width: 100%;height: 100px;"></textarea>
            </div>
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
<%--==============common footer==============--%>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>

<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>

<script src="${portalPath}/content/common/photoview/photoswipe.js"></script>
<script src="${portalPath}/content/common/photoview/photoswipe-ui-default.min.js"></script>

<script src="js/act.js?v=${cfg.version}"></script>
</html>
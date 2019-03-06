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
    <title>公益活动</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/photoswipe.css">
    <link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/default-skin/default-skin.css">
</head>
<body>

<!--隐藏存放ID-->
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>


<div class="portlet light">

    <div class="portlet-body">

        <div class="row">
            <div class="col-sm-4">

            </div>
            <div class="col-sm-8">
                <div class="col-sm-12">
                    <form onsubmit="return t_query()">
                        <div class="btn-group" role="group" style="float:left;padding-right:5px">
                            <button type="button" class="btn btn-default" onclick="setParams('status','');">全部</button>
                            <button type="button" class="btn btn-default" onclick="setParams('status','2');">待审</button>
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
        </div>


        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="40%">活动名称</th>
                    <th width="15%">开展时间</th>
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


<%--查看详情--%>
<div class="modal fade bs-example-modal-lg" role="dialog" id="modal-detail">
    <div class="modal-dialog" role="document" style="width: 90%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">活动详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-detail" role="form">
                    <%--详情模板填充--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <%--<button type="button" class="btn btn-primary">确定</button>--%>
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
                <h4 class="modal-title">活动审核</h4>
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


<!--签到弹框-->
<div class="modal fade bs-example-modal-lg" role="dialog" id="preview">
    <div class="modal-dialog" role="document" style="width: 90%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">活动签到审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal signCheck" id="fm-sign" role="form">


                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-sign-primary btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>



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


<script id="tpl-fm-sign" type="text/template">
    <div class="form-group">
        <label class="col-md-2 view-label">组织者签到</label>
        <div class="col-md-10 flexBox">
            <div class="sign-content">
                <div class="imgBox">
                    <img src="\${data.startSignImgUrl}" alt="">
                </div>
                <text>\${data.realStartDate}</text>
            </div>

            <div class="sign-content">
                <div class="imgBox">
                    <img src="\${data.endSignImgUrl}" alt="">
                </div>
                <text>\${data.realEndDate}</text>
            </div>

        </div>

        <label class="col-md-2 view-label">参与者签到</label>
        <div class="col-md-10 flexBox" style="max-height: 362px;overflow-y: auto">
            {@each data.activityDetailVoList as item, index}
            {@if item.signInState==1}
            <div class="sign-content">
                <div class="imgBox">
                    <img src="\${item.signImgUrl}" alt="">
                    <input name="list" value="\${item.userId}" checked class="check" type="checkbox">
                </div>
                <text>\${item.name}</text>
            </div>
            {@/if}
            {@/each}
        </div>

    </div>
    <h4>结果</h4>
    <hr>
    <div class="form-group ">
        <label class="col-md-2 control-label">审核说明</label>
        <div class="col-md-10">
            <div class="radio-group-container">
                <label>
                    <input checked type="radio" name="rst" value="33"><span style="padding:10px">通过</span>
                </label>
                <label>
                    <input type="radio" name="rst" value="41"><span style="padding:10px">驳回</span>
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


</script>


<%--审核渲染模板--%>
<script id="tpl-fm-audit" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 view-label">活动名称</label>
            <div class="col-md-10">
                \${data.title}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动封面</label>
            <div class="col-md-10 my-gallery">
                <img src="\${data.coverUrl}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 view-label">活动发起人</label>
            <div class="col-md-10">
                \${data.orgName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动地点</label>
            <div class="col-md-10">
                \${data.location}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动限制人数</label>
            <div class="col-md-10">
                \${data.parterNum}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动开始时间</label>
            <div class="col-md-10">
                \${data.startDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动结束时间</label>
            <div class="col-md-10">
                \${data.endDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动方式</label>
            <div class="col-md-10">
                \${data.mode}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动目的</label>
            <div class="col-md-10">
                \${data.purpose}
            </div>
        </div>
        <h4>结果</h4>
        <hr>
        <div class="form-group " id="operation">
            <label class="col-md-2 control-label">审核说明</label>
            <div class="col-md-10">
                <div class="radio-group-container">
                    <label>
                        <input checked type="radio" name="rst" value="3"><span style="padding:10px">通过</span>
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

<%--详情模板--%>
<script id="tpl-detail" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 view-label">活动名称</label>
            <div class="col-md-10">
                \${data.title}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动封面</label>
            <div class="my-gallery col-md-10">
                <img src="\${data.coverUrl}" style="max-width:480px;max-height: 480px"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 view-label">活动发起人</label>
            <div class="col-md-10">
                \${data.orgName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动地点</label>
            <div class="col-md-10">
                \${data.location}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动限制人数</label>
            <div class="col-md-10">
                \${data.parterNum}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动开始时间</label>
            <div class="col-md-10">
                \${data.startDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动结束时间</label>
            <div class="col-md-10">
                \${data.endDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动方式</label>
            <div class="col-md-10">
                \${data.mode}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">活动目的</label>
            <div class="col-md-10">
                \${data.purpose}
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 view-label">组织者签到</label>
            <div class="col-md-10 flexBox">
                <div class="sign-content">
                    <div class="imgBox my-gallery">
                        <img src="\${data.startSignImgUrl}"
                             style="width: 150px;max-width: 480px;height: 150px;max-height: 150px" alt="">
                    </div>
                    <text>\${data.realStartDate}</text>
                </div>

                <div class="sign-content">
                    <div class="imgBox my-gallery">
                        <img src="\${data.endSignImgUrl}"
                             style="width: 150px;max-width: 480px;height: 150px;max-height: 150px" alt="">
                    </div>
                    <text>\${data.realEndDate}</text>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">参与者签到列表</label>
            <div class="col-md-10">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-1 view-label"></label>
            <div class="col-md-11">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th width="20%">微信昵称</th>
                        <th width="20%">真实姓名</th>
                        <th width="20%">手机号码</th>
                        <th width="20%">签到时间</th>
                        <th width="20%">签到图片</th>
                    </tr>
                    </thead>
                    <tbody>
                    {@each data.activityDetailVoList as item, index}
                    <tr>
                        <td>
                            <div class="row">
                                <div class="col-md-12">
                                    {@if item.headimgurl!=null && item.headimgurl !=''}
                                    <img src="\${item.headimgurl}" class="cover"/>
                                    {@else}
                                    <img src="${pageContext.request.contextPath}/content/common/img/default_header.png"
                                         class="cover"/>
                                    {@/if}
                                    <a>\${item.nickname}</a>
                                </div>
                            </div>
                        </td>
                        <td>\${item.name}</td>
                        <td>\${item.mobile}</td>
                        <td>\${item.signInDate}</td>
                        <td>
                            <div class="my-gallery">
                                <img src="\${item.signImgUrl}" class="cover" alt="">
                            </div>
                        </td>
                    </tr>
                    {@/each}
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</script>

<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td>
            \${item.title}
        </td>
        <td>
            \${item.startDate}
        </td>
        <td>\${item.orgName}</td>
        <td>
            {@if item.status==2}
            <span class="label label-lg label-info">发布审核</span>
            {@else if item.status==3}
            <span class="label label-lg label-success">活动发布</span>
            {@else if item.status==4}
            <span class="label label-lg label-danger">发布驳回</span>
            {@else if item.status==31}
            <span class="label label-lg label-info">活动开始</span>
            {@else if item.arStatus==2}
            <span class="label label-lg label-info">报道待审核</span>
            {@else if item.status==32}
            <span class="label label-lg label-success">活动结束</span>
            {@else if item.status==33}
            <span class="label label-lg label-success">活动成功</span>
            {@else if item.status==41}
            <span class="label label-lg label-danger">活动无效</span>
            {@else if item.status==42}
            <span class="label label-lg label-danger">活动超时</span>
            {@/if}
        </td>
        <td>
            <a class="operation" href="javascript:details('\${item.id}');">查看</a>
            <%--<a class="operation" href="javascript:edit('\${item.id}');">编辑</a>--%>
            <%--<a class="operation" href="javascript:del('\${item.id}');">删除</a>--%>
            <a class="operation" href="#" data-toggle="modal" data-target="#modal-audit" data-id="\${item.id}">审核</a>
            {@if item.status==32&&item.arStatus==3}
            <a class="operation" href="#" onclick="signInfo('\${item.id}','\${item.amount}');">活动签到</a>
            {@/if}
            <%--{@if item.status == '2'}--%>
            <%--<a class="operation" href="#" data-toggle="modal" data-target="#modal-audit" data-id="\${item.id}">审核</a>--%>
            <%--{@/if}--%>
            <%--<a class="operation" href="#" onclick="deleteData('\${item.id}');">删除</a>--%>
        </td>
    </tr>
    {@/each}
</script>

<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/photoview/photoswipe.js"></script>
<script src="${portalPath}/content/common/photoview/photoswipe-ui-default.min.js"></script>
<script src="js/act.js?v=${cfg.version}"></script>

</html>
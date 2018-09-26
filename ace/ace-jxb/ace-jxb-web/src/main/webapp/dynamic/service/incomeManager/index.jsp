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
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/photoswipe.css">
    <link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/default-skin/default-skin.css">
    <script src="${portalPath}/content/common/photoview/photoswipe.js"></script>
    <script src="${portalPath}/content/common/photoview/photoswipe-ui-default.min.js"></script>
</head>
<body>


<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>

<!---==============================================-->


<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-5">

            </div>

            <div class="col-md-7">
                <form onsubmit="return t_query()">
                    <%--<div class="btn-group" id="btn-group1" role="group" style="float:left;padding-right:5px">--%>
                    <%--<button type="button" class="btn btn-default" onclick="changeConsultState('');">全部</button>--%>
                    <%--<button type="button" class="btn btn-default" onclick="changeConsultState('1');">上架</button>--%>
                    <%--<button type="button" class="btn btn-default" onclick="changeConsultState('-1');">下架</button>--%>
                    <%--</div>--%>
                    <%--<div class="btn-group" id="btn-group2" role="group" style="float:left;padding-right:5px">--%>
                    <%--<button type="button" class="btn btn-default" onclick="changeType('');">全部</button>--%>
                    <%--<button type="button" class="btn btn-default" onclick="changeType('0');">待审</button>--%>
                    <%--<button type="button" class="btn btn-default" onclick="changeType('1');">通过</button>--%>
                    <%--<button type="button" class="btn btn-default" onclick="changeType('2');">驳回</button>--%>
                    <%--</div>--%>
                    <div class="input-group">
                        <input type="text" name="keyword" class="form-control " placeholder="请输入咨询师姓名">
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
                    <th width="25%">姓名</th>
                    <th width="15%">咨询总收入</th>
                    <th width="15%">老师咨询收入</th>
                    <th width="15%">平台咨询收入</th>
                    <th width="15%">课程总收入</th>
                    <th width="15%">老师课程收入</th>
                    <th width="15%">平台课程收入</th>
                    <th width="15%">提成收入</th>
                    <th width="15%">老师收入总计</th>
                    <th width="15%">公司收入总计</th>
                    <th width="15%">发展老师数量</th>
                    <th width="15%">目前分成比例</th>
                </tr>
                </thead>
                <tbody id="counselorList">

                </tbody>
            </table>
        </div>
        <div class="paginationbar">
            <ul class="pagination" id="pagination1"></ul>
        </div>

    </div>

</div>
<!--=======================================-->

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
</body>

<script id="list" type="text/template">
    {@each data as item, index}
    <tr>

        <td>
            <div class="row">
                <div class="col-md-2">
                    <img src="\${item.imagePhotoUrl}" class="cover"/>
                </div>
                <div class="col-md-10">
                    <div class="description">\${item.teacherName}</div>
                    <%--<div class="certification">\${item.certification}</div>--%>
                </div>
            </div>
        </td>

        <td>\${item.consultIncomeTotal}</td>
        <td>\${item.consultIncomeTeacher}</td>
        <td>\${item.consultIncomePlatform}</td>

        <td>\${item.courseIncomeTotal}</td>
        <td>\${item.courseIncomeTeacher}</td>
        <td>\${item.courseIncomePlatform}</td>

        <td>\${item.studioIncome}</td>
        <td>\${item.teacherTotal}</td>
        <td>\${item.platformTotal}</td>

        <td>\${item.devPeopleNum}</td>
        <td>\${item.ratio}</td>
        <%--<td>--%>
        <%--{@if item.regAuditRst==1} {@if item.consultState !=1}--%>
        <%--<a class="operation" href="javascript:void(0);" onclick="online('\${item.id}');">上架</a>--%>
        <%--{@else}--%>
        <%--<a class="operation" href="javascript:void(0);" onclick="outline('\${item.id}');">下架</a>--%>
        <%--{@/if} {@/if}--%>
        <%--{@if item.regAuditRst==0}--%>
        <%--<a class="operation" href="#" data-toggle="modal" data-id="\${item.id}" data-target="#audit">审核</a>--%>
        <%--{@/if}--%>

        <%--<a class="operation" href="#" data-toggle="modal" data-id="\${item.id}" data-target="#preview">详细</a>--%>
        <%--</td>--%>
    </tr>
    {@/each}
</script>

<script id="tpl-info" type="text/template">
    <div class="modal-body">
        <div class="form-horizontal" novalidate="novalidate">
            <div class="form-body" style="padding: 30px;">
                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>
                        姓名
                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.name}</label>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>
                        性别
                    </label>
                    <div class="col-md-9">
                        <label class="control-label">
                            {@if data.sex==1}男{@else}女{@/if}
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>所在城市
                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.cityCode}</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>个人简介
                    </label>
                    <div class="col-md-9">
                        \$\${data.profile}
                    </div>
                </div>


                <div class="form-group ">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>手机号码

                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.mobile}</label>
                    </div>
                </div>


                <div class="form-group ">
                    <label class="control-label col-md-2">
                        <span class="required" aria-required="true">*</span>形象照

                    </label>
                    <div class="col-md-9">
                        <img class="headimg" src="\${data.imagePhotoUrl}" alt="">
                    </div>
                </div>
                <div class="form-group ">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>身份证号码

                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.idCard}</label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">
                        <span class="required" aria-required="true">*</span>身份证
                    </label>
                    <div class="col-md-9 ">
                        {@if data.evidenceImgUrl}<img src="\${data.evidenceImgUrl}" alt=""/>{@/if}
                        {@if data.idCardImgUrl}<img src="\${data.idCardImgUrl}" alt=""/>{@/if}
                        {@if data.idCardSideImgUrl}<img src="\${data.idCardSideImgUrl}" alt=""/> {@/if}
                    </div>
                </div>


                <div class="form-group ">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>职业名称
                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.certification}</label>
                    </div>
                </div>


                <div class="form-group ">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>从业资格证证号
                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.certificateNo}</label>
                    </div>
                </div>


                <div class="form-group ">
                    <label class="control-label col-md-2">
                        <span class="required" aria-required="true">*</span>从业资格证书

                    </label>
                    <div class="col-md-9">
                        <img src="\${data.certificateImgUrl}">
                    </div>
                </div>

                <div class="form-group ">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>个案时长
                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.duration}</label>
                    </div>
                </div>
                <div class="form-group ">
                    <label class="col-md-2 control-label">
                        <span class="required" aria-required="true">*</span>个案人数

                    </label>
                    <div class="col-md-9">
                        <label class="control-label">\${data.peopleNum}</label>
                    </div>
                </div>
            </div>

        </div>

    </div>
</script>

<div class="modal fade" role="dialog" id="preview">
    <div class="modal-dialog" role="document" style="width:60%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">咨询师详情</h4>
            </div>
            <div id="info" class="my-gallery">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
<style>
    .cover {
        width: 50px;
        height: 50px;
        object-fit: cover;
    }

    .description {
        padding-left: 0px;
    }

    .certification {
        padding-top: 5px;
        padding-left: 8px;
    }

    #info img {

        max-height: 200px;
        max-width: 200px;
        border-radius: 5px;
    }

    .pswp {
        z-index: 99999;
    }

    .my-gallery img {
        padding: 10px;
        float: left;
    }
</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>

</html>

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
    <jsp:include page="../../common/base.jsp"/>
    <link rel="stylesheet" href="${portalPath}/content/common/assets/pages/css/profile.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/css/components.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css">
    <link rel="stylesheet"
          href="${portalPath}/content/common/assets/global/plugins/bootstrap-sweetalert/sweetalert.css">
    <%--<link rel="stylesheet" href="https://cdn.bootcss.com/limonte-sweetalert2/7.21.1/sweetalert2.min.css">--%>
    <link rel="stylesheet"
          href="${portalPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css">
    <script src="${pageContext.request.contextPath}/content/service/counselor/js/act.js?v=${cfg.version}"></script>
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
                                    <span>咨询师管理</span>
                                </li>
                            </ul>
                            <div class="page-content-inner">

                                <!---==============================================-->
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="portlet light">
                                            <div class="portlet-title">
                                                <div class="caption">
                                                    咨询师管理
                                                </div>
                                                <div class="actions">
                                                    <form onsubmit="return t_query()">


                                                        <div class="input-group">
                                                            <input type="text" style="height:32px" name="name"
                                                                   class="form-control input-circle-left"
                                                                   placeholder="请输入咨询师姓名">
                                                            <span class="input-group-btn">
                                                                <button onclick="javascript:searchByName()"
                                                                        class="btn btn-circle-right btn-default search_btn"
                                                                        type="submit">
                                                                        搜索
                                                                </button>
                                                            </span>
                                                        </div>
                                                    </form>

                                                </div>
                                            </div>
                                            <div class="portlet-body">
                                                <div class="mt-element-card mt-card-round mt-element-overlay">
                                                    <div class="row">
                                                        <div id="counselorList">

                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="paginationbar">
                                                    <ul class="pagination" id="pagination1"></ul>
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



        </div>
    </div>
    <div class="bottom"></div>
</div>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="myModal"
     aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="gridSystemModalLabel">咨询师详情</h4>
            </div>
            <div id="info">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<script id="temp_counselorList" type="text/template">
    {@each data as item}
    <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
        <div class="mt-card-item">
            <div class="mt-card-avatar mt-overlay-1 mt-scroll-left">
                <img src="\${item.imagePhotoUrl}">
            </div>
            <div class="mt-card-content">
                <h3 class="mt-card-name">\${item.name}
                </h3>
                <p class="mt-card-desc font-grey-mint">\${item.certification}
                </p>
                <div class="mt-card-social" data-id="\${item.id}">
                    <ul>
                        <li>
                            <a class="info" href="javascript:;">
                                详情
                            </a>
                        </li>
                        {@if item.regAuditRst==0}
                        <li>
                            <a class="pass" href="javascript:;">
                                审核
                            </a>
                        </li>
                        {@else if item.regAuditRst==1}
                        <li>
                            已通过
                        </li>
                        {@else}
                        <li>
                            已拒绝
                        </li>
                        {@/if}
                        <li>
                            <a class="delect" href="javascript:;">
                                删除
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    {@/each}

</script>


<script id="temp_info" type="text/template">
    <div class="modal-body">
        <div class="row">
            <div class="headbox">
                <img class="headimg" src="\${data.imagePhotoUrl}" alt="">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-12">名字</div>
            <div class="col-lg-8 col-md-8 col-sm-12">\${data.name}</div>
            <div class="col-lg-4 col-md-4 col-sm-12">性别</div>
            <div class="col-lg-8 col-md-8 col-sm-12">\${data.sex}</div>
            <div class="col-lg-4 col-md-4 col-sm-12">手机号码</div>
            <div class="col-lg-8 col-md-8 col-sm-12">\${data.mobile}</div>
            <div class="col-lg-4 col-md-4 col-sm-12">城市</div>
            <div class="col-lg-8 col-md-8 col-sm-12">\${data.name}</div>
            <div class="col-lg-4 col-md-4 col-sm-12">职业名称</div>
            <div class="col-lg-8 col-md-8 col-sm-12">\${data.certification}</div>
            <div class="col-lg-12 col-md-12 col-sm-12">擅长领域</div>
            <div class="col-lg-12 col-md-12 col-sm-12">\${data.tags}</div>
            <div class="col-lg-12 col-md-12 col-sm-12">职业证书</div>
            <div class="col-lg-12 col-md-12 col-sm-12">
                <img src="\${data.certificateImgUrl}"
                     alt="">
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12">手持身份证</div>
            <div class="col-lg-12 col-md-12 col-sm-12">
                <img src="\${data.evidenceImgUrl}"
                     alt="">
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12">身份证正面</div>
            <div class="col-lg-12 col-md-12 col-sm-12">
                <img src="\${data.idCardImgUrl}"
                     alt="">
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12">身份证反面</div>
            <div class="col-lg-12 col-md-12 col-sm-12">
                <img src="\${data.idCardSideImgUrl}"
                     alt="">
            </div>
        </div>
    </div>

</script>


</body>

<style>
    .modal .headbox {
        width: 150px !important;
        height: 150px !important;
        border-radius: 50% !important;
        overflow: hidden;
        margin: 0 auto;
    }

    .modal-body {
        font-size: 16px;
        line-height: 24px;
        text-align: justify
    }

    .modal img {
        width: 100%;
        height: 100%;
    }

    .mt-card-social .delect:hover {
        color: red !important;
    }

</style>
</html>
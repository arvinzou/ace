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
          href="${portalPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/content/service/information/css/style.css">--%>
    <script src="${pageContext.request.contextPath}/content/service/studio/js/act.js?v=${cfg.version}"></script>
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
                                    <a href="index4.jsp">首页</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <li>
                                    <span>仪表盘</span>
                                </li>
                            </ul>
                            <div class="page-content-inner">
                                <div class="row">
                                    <div class="col-md-12">
                                        <!-- BEGIN PROFILE SIDEBAR -->
                                        <div class="profile-sidebar">
                                            <!-- PORTLET MAIN -->
                                            <div class="portlet light profile-sidebar-portlet ">
                                                <!-- SIDEBAR USERPIC -->
                                                <div class="profile-userpic">
                                                    <img src="${pageContext.request.contextPath}/content/service/information/img/left_pic_two.jpg"
                                                         class="user-imagePhotoUrl img-responsive" alt="">
                                                </div>
                                                <!-- END SIDEBAR USERPIC -->
                                                <!-- SIDEBAR USER TITLE -->
                                                <div class="profile-usertitle">
                                                    <div class="profile-usertitle-name user-name"></div>
                                                    <div class="profile-usertitle-job user-certification"></div>
                                                </div>
                                                <!-- END SIDEBAR USER TITLE -->
                                                <!-- SIDEBAR BUTTONS -->
                                                <div class="profile-userbuttons">
                                                    <button type="button" class="btn btn-circle green btn-sm"
                                                            onclick="javascript:window.location.href='../userInformation/index.jsp'">
                                                        个人信息
                                                    </button>
                                                    <button type="button" class="btn btn-circle red btn-sm"
                                                            onclick="javascript:window.location.href='../userStudio/index.jsp'">
                                                        工 作 室
                                                    </button>
                                                </div>
                                                <!-- END SIDEBAR BUTTONS -->
                                                <!-- SIDEBAR MENU -->
                                                <div class="profile-usermenu">
                                                    <ul class="nav">
                                                        <li class="active">
                                                            <a href="../userStudioStaff/index.jsp">
                                                                <i class="icon-settings"></i>工作室管理</a>
                                                        </li>

                                                        <li>
                                                            <a href="../userReservation/index.jsp">
                                                                <i class="icon-settings"></i>预约设置</a>
                                                        </li>

                                                        <li>
                                                            <a href="../userOrder/index.jsp">
                                                                <i class="icon-settings"></i>订单管理</a>
                                                        </li>
                                                        <li>
                                                            <a href="#">
                                                                <i class="icon-info"></i> Help </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <!-- END MENU -->
                                            </div>
                                            <!-- END PORTLET MAIN -->
                                            <!-- PORTLET MAIN -->
                                            <div class="portlet light ">
                                                <!-- STAT -->
                                                <div class="row list-separated profile-stat">
                                                    <div class="col-md-4 col-sm-4 col-xs-6">
                                                        <div class="uppercase profile-stat-title"> 37</div>
                                                        <div class="uppercase profile-stat-text"> Projects</div>
                                                    </div>
                                                    <div class="col-md-4 col-sm-4 col-xs-6">
                                                        <div class="uppercase profile-stat-title"> 51</div>
                                                        <div class="uppercase profile-stat-text"> Tasks</div>
                                                    </div>
                                                    <div class="col-md-4 col-sm-4 col-xs-6">
                                                        <div class="uppercase profile-stat-title"> 61</div>
                                                        <div class="uppercase profile-stat-text"> Uploads</div>
                                                    </div>
                                                </div>
                                                <!-- END STAT -->
                                                <div>
                                                    <h4 class="profile-desc-title">About Marcus Doe</h4>
                                                    <span class="profile-desc-text"> Lorem ipsum dolor sit amet diam nonummy nibh dolore. </span>
                                                    <div class="margin-top-20 profile-desc-link">
                                                        <i class="fa fa-globe"></i>
                                                        <a href="http://www.keenthemes.com">www.keenthemes.com</a>
                                                    </div>
                                                    <div class="margin-top-20 profile-desc-link">
                                                        <i class="fa fa-twitter"></i>
                                                        <a href="http://www.twitter.com/keenthemes/">@keenthemes</a>
                                                    </div>
                                                    <div class="margin-top-20 profile-desc-link">
                                                        <i class="fa fa-facebook"></i>
                                                        <a href="http://www.facebook.com/keenthemes/">keenthemes</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- END PORTLET MAIN -->
                                        </div>
                                        <!-- END BEGIN PROFILE SIDEBAR -->
                                        <!-- BEGIN PROFILE CONTENT -->


                                        <div class="profile-content">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="portlet light portlet-fit ">
                                                        <div class="portlet-title">
                                                            <div class="caption">
                                                                <i class=" icon-layers font-green"></i>
                                                                <span class="caption-subject font-green bold uppercase"><font
                                                                        style="vertical-align: inherit;"><font
                                                                        style="vertical-align: inherit;">工作室</font></font></span>
                                                            </div>
                                                            <div class="actions">
                                                                <a onclick="javascript:modify()"
                                                                   class="btn btn-circle red-sunglo btn-sm">
                                                                    <i class="fa fa-plus"></i><font
                                                                        style="vertical-align: inherit;"><font
                                                                        style="vertical-align: inherit;">
                                                                    创建工作室</font></font></a>
                                                            </div>
                                                        </div>
                                                        <div class="portlet-body">
                                                            <div class="mt-element-card mt-element-overlay">
                                                                <div class="row">

                                                                    <%--content--%>

                                                                    <div class="table-scrollable table-scrollable-borderless">
                                                                        <table class="table table-hover table-light">
                                                                            <thead>
                                                                            <tr class="uppercase">
                                                                                <th colspan="2"> 名称</th>
                                                                                <th> 负责人</th>
                                                                                <th> 状态</th>
                                                                                <th colspan="2"> 操作</th>
                                                                            </tr>
                                                                            </thead>
                                                                            <tbody id="studioList">

                                                                            </tbody>
                                                                        </table>
                                                                    </div>

                                                                    <%--content--%>

                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <!-- END PROFILE CONTENT -->
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

<div class="modal fade bs-example-modal-lg" id="studioInfoModal" tabindex="-1" role="dialog"
     aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="gridSystemModalLabel">工作室编辑</h4>
            </div>
            <div class="modal-body">

                <div class="profile-content">

                    <div class="form-horizontal" novalidate="novalidate">
                        <div class="form-body">

                            <div class="form-group form-md-line-input">
                                <label class="col-md-3 control-label">名字
                                    <span class="required" aria-required="true">*</span>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" id="notNull" class="form-control"
                                           placeholder="" name="form_name">
                                    <span class="error_message"></span>
                                    <div class="form-control-focus"></div>
                                </div>
                            </div>

                            <div class="form-group form-md-checkboxes">
                                <label class="control-label col-md-3">工作室logo
                                    <span class="required" aria-required="true">*</span>
                                </label>
                                <div class="col-md-9">
                                    <div>
                                        <div class="studiologo" id="studiologo">
                                            <img class="select_img form_logoImgUrl"
                                                 id="logo" data-cover="logo"
                                                 data-toggle="modal"
                                                 data-againadd="false"
                                                 data-xsize="300" data-ysize="300"
                                                 data-target="#img-uploader"
                                                 src="${pageContext.request.contextPath}/content/service/studio/img/addImg.png?v=${cfg.version}">
                                        </div>
                                        <p>工作室logo</p>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group form-md-checkboxes">
                                <label class="control-label col-md-3">封面轮播图
                                    <span class="required" aria-required="true">*</span>
                                </label>
                                <div class="col-md-9 idCardBoxs">


                                    <div id="indexImg">
                                        <div class="idCardBox">
                                            <img class="select_img form_idCardImgUrl"
                                                 id="uploadImgBtn"
                                                 data-cover="banner"
                                                 data-xsize="240" data-ysize="150"
                                                 data-againadd="true"
                                                 data-target="#img-uploader"
                                                 data-toggle="modal"
                                                 src="${pageContext.request.contextPath}/content/service/studio/img/addImg1.png?v=${cfg.version}">
                                        </div>
                                    </div>

                                </div>
                            </div>


                            <div class="form-group form-md-line-input">
                                <label class="col-md-3 control-label">个人简介
                                    <span class="required" aria-required="true">*</span>
                                </label>
                                <div class="col-md-9">
                                                                <textarea class="form-control" id="notNull1"
                                                                          name="form_introduce"
                                                                          rows="5"></textarea>
                                    <span class="error_message"></span>
                                    <div class="form-control-focus"></div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>

                <button type="button" class="btn btn-info submit_btn">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>

<style>

    .error_message {
        color: red;
    }

    .studiologo {
        width: 100px;
        height: 100px;
        overflow: hidden;
        background-color: #BDE1FF;
    }

    .studiologo img {
        text-align: center;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .studiologo + p {
        line-height: 0.05rem;
    }

    .input_style {
        border-bottom: 1px solid #c2cad8 !important;
    }

    .portlet-body {
        background-color: #fff !important;
        padding: 41px 98px 45px 20px;
    }

    .idCardBoxs > div, .certificateBoxs > div {
        width: 192px;
        float: left;
        margin-right: 30px;
        margin-bottom: 15px;
    }

    .idCardBoxs > div > p, .certificateBoxs > div > p {
        text-align: center;
        line-height: 0.05rem;
    }

    .idCardBox {
        position: relative;
        height: 128px;
        overflow: hidden;
        background-color: #BDE1FF;
    }

    .idCardBox .deleteBtn {
        position: absolute;
        right: 0;
        top: 0;
        width: 30px;
        height: 30px;
        background-color: #ff000088;
        border-radius: 50% !important;
        line-height: 30px;
        text-align: center;
        color: #fff;
        cursor: pointer;
    }

    .idCardBox img {
        text-align: center;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .modal-lg {
        width: 950px !important;
    }
</style>


<script id="temp_studioList1" type="text/template">
    <tr>
        <td class="fit">
            <img class="user-pic"
                 src="\${data.logoImgUrl?data.logoImgUrl:'headImg.png'}">
        </td>
        <td>
            \${data.name}
        </td>
        <td> \${data.dutyName}</td>
        <td> \${data.status}</td>
        <td>

        </td>
        <td>
            <a onclick="javascript:detail(\${data.id})" class="primary-link">查看</a>
        </td>
    </tr>
</script>

<script id="temp_studioList" type="text/template">
    {@each data as item}
    <tr>
        <td class="fit">
            <img class="user-pic"
                 src="\${item.logoImgUrl?item.logoImgUrl:'headImg.png'}">
        </td>
        <td>
            \${item.name}
        </td>
        <td> 我创建的</td>
        <td> \${item.status}</td>
        <td>
            <a onclick="javascript:modify('\${item.id}')" class="primary-link">修改</a>
        </td>
        <td>
            <a onclick="javascript:detail('\${item.id}')" class="primary-link">查看</a>

        </td>
    </tr>
    {@/each}
</script>


</html>


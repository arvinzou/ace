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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/service/information/css/style.css">
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
                                                    <button type="button" class="btn btn-circle green btn-sm">Follow
                                                    </button>
                                                    <button type="button" class="btn btn-circle red btn-sm">Message
                                                    </button>
                                                </div>
                                                <!-- END SIDEBAR BUTTONS -->
                                                <!-- SIDEBAR MENU -->
                                                <div class="profile-usermenu">
                                                    <ul class="nav">
                                                        <li>
                                                            <a href="../information/information.jsp">
                                                                <i class="icon-home"></i>完善信息
                                                            </a>
                                                        </li>
                                                        <li class="active">
                                                            <a href="#">
                                                                <i class="icon-settings"></i>工作室</a>
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
                                            <div class="portlet-body">
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
                                                                             data-xsize="300" data-ysize="300"
                                                                             data-target="#img-uploader"
                                                                             src="${pageContext.request.contextPath}/content/service/studio/img/addImg.png?v=${cfg.version}">
                                                                    </div>
                                                                    <p>工作室logo</p>
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

                                                        <div class="form-actions">
                                                            <div class="row">
                                                                <div class="col-md-offset-3 col-md-9">
                                                                    <button type="reset" class="btn default">重置</button>
                                                                    <button class="btn green submit_btn">提交</button>
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


    <style>
        .hc-checkbox {
            width: 10rem;
        }

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
            line-height: 1.05rem;
        }

        .input_style {
            border-bottom: 1px solid #c2cad8 !important;
        }

        .portlet-body {
            background-color: #fff !important;
            padding: 41px 98px 45px 20px;
        }
    </style>
</div>
</body>


</html>


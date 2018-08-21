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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/city-select.css">
    <link rel="stylesheet"
          href="${portalPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/service/information/css/style.css">
    <script src="${pageContext.request.contextPath}/content/service/information/js/act.js?v=${cfg.version}"></script>
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
                                                        工作室信息
                                                    </button>
                                                </div>
                                                <!-- END SIDEBAR BUTTONS -->
                                                <!-- SIDEBAR MENU -->
                                                <div class="profile-usermenu">
                                                    <ul class="nav">
                                                        <li>
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
                                                    <div class="portlet light portlet-fit">


                                                        <div class="portlet-title">
                                                            <div class="caption">
                                                                <i class=" icon-layers font-green"></i>
                                                                <span class="caption-subject font-green bold uppercase"><font
                                                                        style="vertical-align: inherit;"><font
                                                                        style="vertical-align: inherit;">信息完善</font></font></span>
                                                            </div>
                                                            <div class="actions">
                                                                <div class="btn-group btn-group-devided"
                                                                     data-toggle="buttons">
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="portlet-body">
                                                            <div class="form-horizontal" novalidate="novalidate">
                                                                <div class="form-body">

                                                                    <div class="form-group ">
                                                                        <label class="col-md-3 control-label">名字
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9">
                                                                            <input type="text" id="chineseName" class="form-control"
                                                                                   placeholder="" name="form_name">
                                                                            <span class="error_message"></span>
                                                                            <div class="form-control-focus"></div>
                                                                        </div>
                                                                    </div>


                                                                    <div class="form-group form-md-radios">
                                                                        <label class="col-md-3 control-label">性别
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9">
                                                                            <div class="md-radio-inline">
                                                                                <div class="md-radio">
                                                                                    <input type="radio" checked id="checkbox1_8"
                                                                                           name="form_sex" value="1"
                                                                                           class="md-radiobtn">
                                                                                    <label for="checkbox1_8">
                                                                                        <span class="inc"></span>
                                                                                        <span class="check"></span>
                                                                                        <span class="box"></span>男</label>
                                                                                </div>

                                                                                <div class="md-radio">
                                                                                    <input type="radio" id="checkbox1_9"
                                                                                           name="form_sex"
                                                                                           value="2"
                                                                                           class="md-radiobtn">
                                                                                    <label for="checkbox1_9">
                                                                                        <span></span>
                                                                                        <span class="check"></span>
                                                                                        <span class="box"></span>女
                                                                                    </label>
                                                                                </div>

                                                                            </div>
                                                                        </div>
                                                                    </div>


                                                                    <div class="form-group ">
                                                                        <label class="col-md-3 control-label">所在城市
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9 city-select"
                                                                             id="single-select-1">
                                                                            <%--<input class="input_style form-control" id="city_edit"--%>
                                                                            <%--type="text"--%>
                                                                            <%--placeholder="请选择所属区域" name="form_area"--%>
                                                                            <%--autocomplete="off" readonly="true"/>--%>
                                                                            <input type="text"
                                                                                   class="input-search form-control"
                                                                                   value="" placeholder="请选择城市"/>
                                                                            <span class="error_message"></span>
                                                                        </div>
                                                                    </div>

                                                                    <div class="form-group ">
                                                                        <label class="col-md-3 control-label">个人简介
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9">
                                                                <textarea class="form-control" id="notNull_profile"
                                                                          name="form_profile"
                                                                          rows="5"></textarea>
                                                                            <span class="error_message"></span>
                                                                            <div class="form-control-focus"></div>
                                                                        </div>
                                                                    </div>


                                                                    <div class="form-group ">
                                                                        <label class="col-md-3 control-label">手机号码
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9">
                                                                            <input type="text" class="form-control" id="mobilePhone"
                                                                                   placeholder=""
                                                                                   name="form_mobile">
                                                                            <span class="error_message"></span>
                                                                            <div class="form-control-focus"></div>
                                                                        </div>
                                                                    </div>


                                                                    <div class="form-group form-md-checkboxes">
                                                                        <label class="control-label col-md-3">形象照
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9">
                                                                            <div>
                                                                                <div class="headimgbox" id="headimg">
                                                                                    <img class="select_img form_imagePhotoUrl"
                                                                                         id="headimg1"
                                                                                         data-toggle="modal"
                                                                                         data-xsize="300" data-ysize="300"
                                                                                         data-cover="headimg1"
                                                                                         data-target="#img-uploader"
                                                                                         src="${pageContext.request.contextPath}/content/service/information/img/head.png?v=${cfg.version}">
                                                                                </div>
                                                                                <p>形象照</p>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="form-group ">
                                                                        <label class="col-md-3 control-label">身份证号码
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9">
                                                                            <input type="text" class="form-control" id="IDcard"
                                                                                   placeholder=""
                                                                                   name="form_idCard">
                                                                            <span class="error_message"></span>
                                                                            <div class="form-control-focus"></div>
                                                                        </div>
                                                                    </div>


                                                                    <div class="form-group form-md-checkboxes">
                                                                        <label class="control-label col-md-3">身份证
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9 idCardBoxs">
                                                                            <div>
                                                                                <div class="idCardBox">
                                                                                    <img class="select_img form_idCardImgUrl"
                                                                                         id="IDcardz"
                                                                                         data-cover="IDcardz" data-toggle="modal"
                                                                                         data-xsize="240" data-ysize="150"
                                                                                         data-target="#img-uploader"
                                                                                         src="${pageContext.request.contextPath}/content/service/information/img/idcardz.png?v=${cfg.version}">


                                                                                </div>
                                                                                <p>身份证正面</p>
                                                                            </div>

                                                                            <div>
                                                                                <div class="idCardBox">
                                                                                    <img class="select_img" id="IDcardf"
                                                                                         data-cover="IDcardf" data-toggle="modal"
                                                                                         data-xsize="240" data-ysize="150"
                                                                                         data-target="#img-uploader"
                                                                                         src="${pageContext.request.contextPath}/content/service/information/img/idcardf.png?v=${cfg.version}">
                                                                                </div>
                                                                                <p>身份证反面</p>
                                                                            </div>


                                                                            <div>
                                                                                <div class="idCardBox">
                                                                                    <img class="select_img form_evidenceImgUrl"
                                                                                         id="IDcardsc"
                                                                                         data-cover="IDcardsc" data-toggle="modal"
                                                                                         data-xsize="240" data-ysize="150"
                                                                                         data-target="#img-uploader"
                                                                                         src="${pageContext.request.contextPath}/content/service/information/img/idcardsc.png?v=${cfg.version}">
                                                                                </div>
                                                                                <p>手持身份证</p>
                                                                            </div>
                                                                        </div>
                                                                    </div>


                                                                    <div class="form-group form-md-radios">
                                                                        <label class="col-md-3 control-label">职业名称
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9">
                                                                            <div class="md-radio-inline"
                                                                                 id="certification">
                                                                                <div class="md-radio">
                                                                                    <input type="radio"
                                                                                           id="radios_1"
                                                                                           name="form_certification"
                                                                                           value="2"
                                                                                           class="md-radiobtn">
                                                                                    <label
                                                                                            for="radios_1"><span class="inc"></span>
                                                                                        <span class="check"></span>
                                                                                        <span class="box"></span>国家二级咨询师</label>
                                                                                </div>
                                                                                <div class="md-radio"><input type="radio"
                                                                                                             id="radios_2"
                                                                                                             name="form_certification"
                                                                                                             value="3"
                                                                                                             class="md-radiobtn">
                                                                                    <label
                                                                                            for="radios_2"><span class="inc"></span>
                                                                                        <span class="check"></span>
                                                                                        <span class="box"></span>国家三级咨询师</label>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>


                                                                    <div class="form-group ">
                                                                        <label class="col-md-3 control-label">从业资格证证号
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9">
                                                                            <input type="text" class="form-control" id="notNull1"
                                                                                   placeholder=""
                                                                                   name="form_certificateNo">
                                                                            <span class="error_message"></span>
                                                                            <div class="form-control-focus"></div>
                                                                        </div>
                                                                    </div>


                                                                    <div class="form-group form-md-checkboxes">
                                                                        <label class="control-label col-md-3">从业资格证书
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9 certificateBoxs">
                                                                            <div>
                                                                                <div class="certificateBox">
                                                                                    <img class="select_img form_certificateImgUrl"
                                                                                         id="certificateimg"
                                                                                         data-cover="certificateimg"
                                                                                         data-toggle="modal"
                                                                                         data-xsize="240" data-ysize="150"
                                                                                         data-target="#img-uploader"
                                                                                         src="${pageContext.request.contextPath}/content/service/information/img/certificateimg.png?v=${cfg.version}">
                                                                                </div>
                                                                                <p>从业资格证书</p>
                                                                            </div>
                                                                        </div>
                                                                    </div>


                                                                    <div class="form-group form-md-checkboxes">
                                                                        <label class="col-md-3 control-label">擅长领域
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9">
                                                                            <div class="md-checkbox-inline" id="tags">
                                                                                <div class="md-checkbox hc-checkbox">
                                                                                    <input type="checkbox" id="checkbox_0"
                                                                                           name="tags" value="1" class="md-check">
                                                                                    <label for="checkbox_0">
                                                                                        <span class="inc"></span>
                                                                                        <span class="check"></span>
                                                                                        <span class="box"></span>
                                                                                        二孩
                                                                                    </label>
                                                                                </div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_1"
                                                                                        name="tags" value="2"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_1"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>儿童发展</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_2"
                                                                                        name="tags" value="3"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_2"><span class="inc"></span>
                                                                                    <span class="check"></span> <span
                                                                                            class="box"></span>父母成长</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_3"
                                                                                        name="tags" value="4"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_3"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>行为纠正</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_4"
                                                                                        name="tags" value="5"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_4"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>习惯培养</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_5"
                                                                                        name="tags" value="6"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_5"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>育儿困惑</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_6"
                                                                                        name="tags" value="7"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_6"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>亲子冲突</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_7"
                                                                                        name="tags" value="8"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_7"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>厌学</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_8"
                                                                                        name="tags" value="9"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_8"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>自闭症</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_9"
                                                                                        name="tags" value="10"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_9"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>多动症</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_10"
                                                                                        name="tags" value="11"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_10"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>不合群</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_11"
                                                                                        name="tags" value="12"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_11"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>敏感自卑</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_12"
                                                                                        name="tags" value="13"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_12"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>怕生</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_13"
                                                                                        name="tags" value="14"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_13"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>注意力不集中</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_14"
                                                                                        name="tags" value="15"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_14"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>阅读障碍</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_15"
                                                                                        name="tags" value="16"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_15"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>顽皮淘气</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_16"
                                                                                        name="tags" value="17"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_16"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>顽皮淘气</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_17"
                                                                                        name="tags" value="18"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_17"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>孩子叛逆 </label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_18"
                                                                                        name="tags" value="19"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_18"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>小动作</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_19"
                                                                                        name="tags" value="20"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_19"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>网瘾</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_20"
                                                                                        name="tags" value="21"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_20"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>不爱学习</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_21"
                                                                                        name="tags" value="22"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_21"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>攀比心强</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_22"
                                                                                        name="tags" value="23"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_22"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>自控力差</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_23"
                                                                                        name="tags" value="24"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_23"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>早恋</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_24"
                                                                                        name="tags" value="25"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_24"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>沟通问题</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_25"
                                                                                        name="tags" value="26"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_25"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>依赖性强</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_26"
                                                                                        name="tags" value="27"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_26"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>考试焦虑</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_27"
                                                                                        name="tags" value="28"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_27"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>胆小懦弱</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_28"
                                                                                        name="tags" value="29"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_28"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>青春期</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_29"
                                                                                        name="tags" value="30"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_29"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>内向</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_30"
                                                                                        name="tags" value="31"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_30"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>不听话</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_31"
                                                                                        name="tags" value="32"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_31"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>做事拖拉</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_32"
                                                                                        name="tags" value="33"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_32"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>坏习惯</label></div>
                                                                                <div class="md-checkbox hc-checkbox"><input
                                                                                        type="checkbox" id="checkbox_33"
                                                                                        name="tags" value="34"
                                                                                        class="md-check"> <label
                                                                                        for="checkbox_33"><span class="inc"></span>
                                                                                    <span
                                                                                            class="check"></span> <span
                                                                                            class="box"></span>考前紧张</label></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="form-group ">
                                                                        <label class="col-md-3 control-label">个案时长
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9">
                                                                            <div class="input-icon right">
                                                                                <input type="text" class="form-control"
                                                                                       id="naturalNumber"
                                                                                       name="form_duration"
                                                                                       placeholder="一共从事了多少小时">
                                                                                <span class="error_message"></span>
                                                                                <div class="form-control-focus"></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group ">
                                                                        <label class="col-md-3 control-label">个案人数
                                                                            <span class="required" aria-required="true">*</span>
                                                                        </label>
                                                                        <div class="col-md-9">
                                                                            <div class="input-icon right">
                                                                                <input type="text" class="form-control"
                                                                                       id="naturalNumber1"
                                                                                       name="form_peopleNum"
                                                                                       placeholder="一共辅导了多少人">
                                                                                <span class="error_message"></span>
                                                                                <div class="form-control-focus"></div>
                                                                            </div>
                                                                        </div>
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
        width: 10em;
    }

    .error_message {
        color: red;
    }

    .idCardBoxs > div, .certificateBoxs > div {
        width: 192px;
        float: left;
        margin-right: 30px;
        margin-bottom: 15px;
    }

    .idCardBoxs > div > p, .certificateBoxs > div > p {
        text-align: center;
        line-height: 1.05rem;
    }

    .idCardBox, .certificateBox {
        height: 128px;
        overflow: hidden;
        background-color: #BDE1FF;
    }

    .idCardBox img, .certificateBox img {
        text-align: center;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .headimgbox {
        width: 100px;
        height: 100px;
        overflow: hidden;
        background-color: #BDE1FF;
    }

    .headimgbox img {
        text-align: center;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .headimgbox + p {
        line-height: 1.05rem;
    }

    .input_style {
        border-bottom: 1px solid #c2cad8 !important;
    }

    .AreaS {
        background-color: #05920a !important;
        color: #fff !important;
    }

    .portlet-body {
        background-color: #fff !important;
        padding: 14px 98px 45px 20px !important;
    }

    /*.city-select .city-info {*/
    /*border: 1px solid #ccc;*/
    /*background-color: #fcfcfc;*/
    /*cursor: pointer;*/
    /*width: 280px;*/
    /*overflow: hidden;*/
    /*padding: 8px;*/
    /*padding-top: 0;*/
    /*position: relative;*/
    /*z-index: 2;*/
    /*}*/

</style>
</body>


</html>


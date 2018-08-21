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
    <link rel="stylesheet" href="${portalPath}/content/common/simditor/styles/simditor.css">
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
                                                            <a href="../userReservation/index.jsp">
                                                                <i class="icon-settings"></i>预约设置</a>
                                                        </li>
                                                        <li>
                                                            <a href="../userOrder/index.jsp">
                                                                <i class="icon-settings"></i>订单管理</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <!-- END MENU -->
                                            </div>
                                            <!-- END PORTLET MAIN -->
                                            <!-- PORTLET MAIN -->
                                            <%-- <div class="portlet light ">
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
                                             </div>--%>
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

<script id="temp_tags" type="text/template">
    {@each data as item,index}
    <div class="md-checkbox hc-checkbox"><input
            type="checkbox" id="checkbox_\${index}"
            name="tags" value="34"
            class="md-check"> <label
            for="checkbox_\${index}"><span class="inc"></span>
        <span
                class="check"></span> <span
                class="box"></span>\${item.name}</label></div>
    {@/each}
</script>


</html>


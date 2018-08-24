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
    <link rel="stylesheet" href="${portalPath}/content/common/simditor/styles/simditor.css">
    <link rel="stylesheet" href="${portalPath}/content/common/swiper-4.3.5/dist/css/swiper.css">
    <link rel="stylesheet"
          href="${portalPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/content/service/information/css/style.css">--%>
    <script src="${pageContext.request.contextPath}/content/service/studio/js/act.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/content/common/js/loader.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/swiper-4.3.5/dist/js/swiper.js?v=${cfg.version}"></script>
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
                                                    <div class="portlet light portlet-fit ">
                                                        <div class="portlet-title">
                                                            <div class="caption">
                                                                工作室
                                                            </div>
                                                            <div class="actions">
                                                                <a onclick="javascript:createStudio()"
                                                                   class="btn green">创建工作室</a>
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

                            <div class="form-group ">
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
                                                 data-xsize="75" data-ysize="48"
                                                 data-againadd="true"
                                                 data-target="#img-uploader"
                                                 data-toggle="modal"
                                                 src="${pageContext.request.contextPath}/content/service/studio/img/addImg1.png?v=${cfg.version}">
                                        </div>
                                    </div>

                                </div>
                            </div>


                            <div class="form-group ">
                                <label class="col-md-3 control-label">个人简介
                                    <span class="required" aria-required="true">*</span>
                                </label>
                                <div class="col-md-9">
                                    <textarea class="form-control" id="notNull1"
                                              name="form_introduce"
                                              rows="5">
                                    </textarea>
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


<div class="modal fade " id="studioInfo" tabindex="-1" role="dialog"
     aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="">工作室详情</h4>
            </div>
            <div class="modal-body" id="modalstudioInfo">


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



</body>

<style>


    .swiper-container img {
        width: 100%;
    }

    .swiper-container {
        width: 500px;
        height: 320px;
    }

    .info_cotent {
        width: 500px;
        height: 700px;
        overflow-y: auto;
        margin: auto;
    }

    .info_cotent .info_text {
        position: relative;
    }

    .info_cotent .info_text .top {
        position: absolute;
        width: 500px;
        height: 106px;
        top: -53px;
    }

    .info_cotent .info_text .bottom1 .doc {
        width: 100%;
        overflow: hidden;
        word-wrap: break-word;
    }

    .info_cotent .info_text .bottom1 h4 {
        text-align: center;
        color: rgba(28, 40, 67, 1);
        font-size: 15px;
        font-weight: bold;
    }

    .info_cotent .info_text .bottom1 {
        padding-top: 78px;
    }

    .info_cotent .info_text .top .box .left_div img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .info_cotent .info_text .top .box .right_div {
        float: right;
        width: 320px;
        display: table;
        height: 100%;
        margin-right: 20px;
    }

    .info_cotent .info_text .top .box .right_div span {
        display: table-cell;
        vertical-align: middle;
        font-size: 16px;
        word-wrap: break-word;
        color: rgba(28, 40, 67, 1);
        font-weight: bold;
        line-height: 24px;
    }

    .info_cotent .info_text .top .box .left_div {
        width: 68px;
        height: 68px;
        border-radius: 50% !important;
        overflow: hidden;
        float: left;
        margin: 20px 0 0 20px;
    }

    .info_cotent .info_text .top .box {
        position: relative;
        width: 460px;
        margin: auto;
        height: 100%;
        background-color: #fff;
        z-index: 99;
        border-radius: 10px !important;
        box-shadow: 0px 6px 20px 0px #0000002e;
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
            <a onclick="javascript:userStudioStaff('\${item.id}')" class="primary-link">\${item.name}</a>
        </td>
        <td> \${data.dutyName}</td>
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


<script id="temp_modalstudioInfo" type="text/template">
    <div class="info_cotent">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                {@each data.imgList as item}
                <div class="swiper-slide"><img src="\${item.imgUrl}" alt=""></div>
                {@/each}
            </div>
            <!-- Add Arrows -->
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
        </div>
        <div class="info_text">
            <div class="top">
                <div class="box">
                    <div class="left_div">
                        <img src="\${data.logoImgUrl}" alt="">
                    </div>
                    <div class="right_div">
                        <span>\${data.name}</span>
                    </div>

                </div>
            </div>
            <div class="bottom1">
                <h4>工作室简介</h4>
                <div class="doc">
                    \$\${data.introduce}
                </div>
            </div>
        </div>
    </div>
</script>
</html>


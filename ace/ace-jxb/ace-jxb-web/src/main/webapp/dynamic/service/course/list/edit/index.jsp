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
        <meta charset="utf-8" />
        <title>${cfg.sys_name}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="${cfg.sys_name}" name="description" />
        <!--公共部分开始-->
        <jsp:include page="/dynamic/common/base.jsp" />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/css/components.min.css" />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css" />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout3/css/themes/default.min.css"
        />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
        />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout3/css/custom.min.css" />
        <!--公共部分结束-->

        <!--私有部分开始-->
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css" />
        <!--私有部分结束-->

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
                                            <span>课件管理</span>
                                        </li>
                                    </ul>
                                    <div class="page-content-inner">

                                        <!---==============================================-->


                                        <div class="row">
                                            <div class="col-md-12">
                                                <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                <div class="portlet light ">
                                                    <div class="portlet-title">
                                                        <div class="caption">
                                                            课件编辑
                                                        </div>
                                                    </div>
                                                    <div class="portlet-body">
                                                        <div class="form-panel">
                                                            <!--具体界面元素开始-->

                                                        </div>

                                                    </div>
                                                </div>
                                                <!-- END SAMPLE TABLE PORTLET-->
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

    </body>

    <!--公共部分开始-->
    <jsp:include page="/dynamic/common/footer.jsp" />
    <!--公共部分结束-->
    <!--私有部分开始-->
    <script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/assets/global/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/module.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/hotkeys.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/uploader.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/simditor.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/portal/js/main/menu4.js" type="text/javascript"></script>
    <script src="${portalPath}/content/common/plupload/plupload.full.min.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/localization/messages_zh.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/dynamic/service/course/list/edit/js/act.js?v=${cfg.version}"></script>

    <!--私有部分结束-->
    <style>
        .error {
            color: red;
            padding-top: 7px;
        }

        .form-group .col-md-10 {
            text-align: left;
        }
    </style>
    <script id="tpl-fm" type="text/template">
        <form class="form-horizontal" id="fm-add" role="form">
            <div class="form-body">
                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="label-red">*</span>课件名称</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="name" value="\${data.name}" maxlength="28" placeholder="请输入课件名称（建议字数在14个字以内，不超过28个字)">
                        <span class="help-block"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="label-red">*</span>音频地址</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="mediUrl" value="\${data.mediUrl}" maxlength="200" placeholder="音频资源的地址如:http://mp3.aile.com/mp3/a.mp3">
                        <span class="help-block"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="label-red">*</span>课件时长</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="duration" value="\${data.duration}" maxlength="10" placeholder="请输入课件时长（如：300)，单位秒">
                        <span class="help-block"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="label-red">*</span>课件文稿</label>
                    <div class="col-md-10">
                        <div style="text-align:left">
                            <textarea name="introduction" id="introduction">\${data.introduction}</textarea>
                        </div>
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">
                        <span class="label-red">*</span>可试听</label>
                    <div class="col-md-10">
                        <div class="radio-group-container">
                            <label class="mt-radio mt-radio-outline">
                                <input type="radio" name="free" value="0" {@if data.free=='1'}checked{@/if}>是
                                <span></span>
                            </label>
                            <label class="mt-radio mt-radio-outline">
                                <input type="radio" name="free" value="1" {@if data.free=='0'}checked{@/if}>否
                                <span></span>
                            </label>
                        </div>
                    </div>
                </div>


            </div>
            <div class="form-actions">
                <div class="row">
                    <div class="col-md-offset-3 col-md-7">
                        <button class="btn  btn-lg  green" type="submit" style="width:30%">保存</button>
                    </div>
                </div>
            </div>
        </form>
    </script>

    </html>

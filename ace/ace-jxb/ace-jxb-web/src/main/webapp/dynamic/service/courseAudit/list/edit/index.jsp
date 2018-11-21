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
        <jsp:include page="/dynamic/common/header.jsp" />


        <!--私有部分开始-->
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css" />
        <!--私有部分结束-->

    </head>

    <body>
    <jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

                                                <div class="portlet light ">

                                                    <div class="portlet-body">
                                                        <div class="form-panel">
                                                            <!--具体界面元素开始-->

                                                        </div>

                                                    </div>
                                                </div>
                                                <jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

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
                    <div class="col-md-2">
                        <a href="javascript:void(0);" id="uploadSource" style="font-size: 14px !important;" class="btn green commonCourse">上传资源</a>
                    </div>
                    <div class="col-md-8">
                        <input id="mediUrl" type="text" class="form-control" name="mediUrl" value="\${data.mediUrl}" maxlength="200" placeholder="音频资源的地址如:http://mp3.aile.com/mp3/a.mp3">
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12"><span class="uploadPloadprogress"></span></div>
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
                    <label class="col-md-2 control-label">课件文稿</label>
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
                        <button class="btn    green" type="submit" style="width:30%">保存</button>
                    </div>
                </div>
            </div>
        </form>
    </script>

    </html>

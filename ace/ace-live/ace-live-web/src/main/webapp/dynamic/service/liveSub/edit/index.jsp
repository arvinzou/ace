 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html lang="en">


    <head>
        <meta charset="utf-8" />
        <title>${cfg.sys_name}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="${cfg.sys_name}" name="${cfg.sys_name}" />

        <jsp:include page="/dynamic/common/header.jsp" />
        <link rel="stylesheet" href="css/style.css">

        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css" />
        <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
		<link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
    </head>

    <body>

        <jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />


        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="portlet light">

            <div class="portlet-body" id="courseSource">
                <div class="form-panel">
                    <!--具体界面元素开始-->
                    <form class="form-horizontal" id="fm-edit" role="form">

                    </form>
                </div>
                <!--具体界面元素结束-->
            </div>
        </div>

        
            <jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
  
    </body>
    <script id="tpl-fm" type="text/template">
<div class="form-body">
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">
                                            <span class="label-red">*</span>
                                            名称
                                        </label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" name="name" maxlength="100" placeholder="请输入名称（建议字数在28个字以内，不超过100个字)">
                                            <div class="error-name"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">
                                            <span class="label-red">*</span>
                                            直播类型
                                        </label>
                                        <div class="col-md-10">
                                            <label class="mt-radio mt-radio-outline">
                                                <input type="radio" name="category" value="1">图文
                                                <span></span>
                                            </label>
                                            <label class="mt-radio mt-radio-outline">
                                                <input type="radio" name="category" value="2">视频
                                                <span></span>
                                            </label>
                                            <div class="error-category"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">
                                            <span class="label-red">*</span>
                                            封面
                                        </label>
                                        <div class="col-md-10">
                                            <input type="hidden" name="imageSrc">
											<div style="padding: 10px;">建议图片大小为480*270</div>
                                            <img style="max-width:480px;cursor:pointer" id="imageSrc" data-toggle="modal" data-xsize="480" data-ysize="270" data-cover="imageSrc"
                                                data-target="#img-uploader" src="${portalPath}/content/common/image/upload-default.jpg">

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">
                                            <span class="label-red">*</span>
                                            直播时间
                                        </label>
                                        <div class="col-md-2">
                                            <input type="text" class="form-control" name="startTime">
                                            <div class="error-startTime"></div>
                                        </div>
                                        <div class="col-md-8">

                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">
                                            <span class="label-red">*</span>
                                            摘要
                                        </label>
                                        <div class="col-md-10">
                                            <textarea rows="5" cols="100" class="form-control" name="remark" maxlength="500" placeholder="请输入摘要（建议字数在100个字以内，不超过500个字)"></textarea>
                                            <div class="error-remark"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">
                                            <span class="label-red">*</span>
                                            活动介绍
                                        </label>
                                        <div class="col-md-10">
                                            <textarea type="text" name="content">
                                            </textarea>
                                            <div class="error-content"></div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">
                                            直播流地址
                                        </label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" name="rtmpUrl" maxlength="200" placeholder="请输入直播流地址（如:rtmp://a.baidu.com/284099，不超过200个字)">
                                            <span class="help-block"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">
                                            回放地址
                                        </label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" name="mp4Url" maxlength="200" placeholder="请输入回放地址（如:http://a.baidu.com/284099.mp4，不超过200个字)">
                                            <span class="help-block"></span>
                                        </div>
                                    </div>


                                </div>
                                <div class="form-actions">
                                    <div class="row">
                                        <div class="col-md-offset-3 col-md-7">
                                            <button class="btn  green" type="submit" style="width:30%">保存</button>
                                        </div>
                                    </div>
                                </div>
    </script>

    <jsp:include page="/dynamic/common/footer.jsp" />
   <script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js?v=${cfg.version}"></script>
   <script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
   <script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
   <script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
   <script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
   <script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
   <script type="text/javascript" src="${portalPath}/content/common/js/upload.js"></script>
   <script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
   <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
   <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
   <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
   <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>

    </html>

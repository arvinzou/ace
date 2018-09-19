<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html lang="en">


    <head>
        <meta charset="utf-8" />
        <title>${cfg.sys_name}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="${cfg.sys_name}" name="${cfg.sys_name}" />
        <jsp:include page="/dynamic/common/header.jsp"/>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
        <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
    </head>

    <body>

    <jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>


                                                <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                <div class="portlet light">

                                                    <div class="portlet-body" id="courseSource">
                                                        <div class="form-panel">
                                                            <!--具体界面元素开始-->
                                                            <form class="form-horizontal" id="fm-add" role="form">
                                                                <div class="form-body">
                                                                                                                                                                                                                 <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                直播间编号
                                                                                                                                                                                                                <span class="required" aria-required="true"> * </span>
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="rid" maxlength="50" placeholder="请输入直播间编号（建议字数在14个字以内，不超过50个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                用户编号
                                                                                                                                                                                                                <span class="required" aria-required="true"> * </span>
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="uid" maxlength="50" placeholder="请输入用户编号（建议字数在14个字以内，不超过50个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                直播内容
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="content" maxlength="2147483647" placeholder="请输入直播内容（建议字数在14个字以内，不超过2147483647个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                顺序
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="sort" maxlength="10" placeholder="请输入顺序（建议字数在14个字以内，不超过10个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                多媒体类型1视频2音频3图片
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="mediaType" maxlength="20" placeholder="请输入多媒体类型1视频2音频3图片（建议字数在14个字以内，不超过20个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                多媒体资源
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="mediaContent" maxlength="2147483647" placeholder="请输入多媒体资源（建议字数在14个字以内，不超过2147483647个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                入库日期
                                                                                                                                                                                                                <span class="required" aria-required="true"> * </span>
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="createTime" maxlength="" placeholder="请输入入库日期（建议字数在14个字以内，不超过个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                点赞人数
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="likeNum" maxlength="19" placeholder="请输入点赞人数（建议字数在14个字以内，不超过19个字)">
                                                                                <span class="help-block"></span>
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
                                                        </div>

                                                    </div>
                                                </div>



    <jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
    </body>
    <jsp:include page="/dynamic/common/footer.jsp"/>
    <script type="text/javascript"  src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
    <script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
    <script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
    <script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
    <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>
    <script src="js/act.js?v=${cfg.version}"></script>
    </html>

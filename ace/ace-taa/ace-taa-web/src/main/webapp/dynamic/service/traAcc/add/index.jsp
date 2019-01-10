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
                                                                                事故发生地点
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="address" maxlength="200" placeholder="请输入事故发生地点（建议字数在14个字以内，不超过200个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                纬度
                                                                                                                                                                                                                <span class="required" aria-required="true"> * </span>
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="latitude" maxlength="10" placeholder="请输入纬度（建议字数在14个字以内，不超过10个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                经度
                                                                                                                                                                                                                <span class="required" aria-required="true"> * </span>
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="longitude" maxlength="10" placeholder="请输入经度（建议字数在14个字以内，不超过10个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                行政区划
                                                                                                                                                                                                                <span class="required" aria-required="true"> * </span>
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="areaCode" maxlength="50" placeholder="请输入行政区划（建议字数在14个字以内，不超过50个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                天气
                                                                                                                                                                                                                <span class="required" aria-required="true"> * </span>
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="weather" maxlength="50" placeholder="请输入天气（建议字数在14个字以内，不超过50个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                车型
                                                                                                                                                                                                                <span class="required" aria-required="true"> * </span>
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="vehicleType" maxlength="50" placeholder="请输入车型（建议字数在14个字以内，不超过50个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                事故时间
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="accidentTime" maxlength="" placeholder="请输入事故时间（建议字数在14个字以内，不超过个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                所属路段
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="roadSectionId" maxlength="50" placeholder="请输入所属路段（建议字数在14个字以内，不超过50个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                所属路长
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="roadManId" maxlength="50" placeholder="请输入所属路长（建议字数在14个字以内，不超过50个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                死亡人数
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="deadthToll" maxlength="10" placeholder="请输入死亡人数（建议字数在14个字以内，不超过10个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                受伤人数
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="injuries" maxlength="10" placeholder="请输入受伤人数（建议字数在14个字以内，不超过10个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                             <div class="form-group">
                                                                            <label class="col-md-2 control-label">
                                                                                事故原因
                                                                                                                                                                                 </label>
                                                                            <div class="col-md-10">
                                                                                <input type="text" class="form-control" name="cause" maxlength="50" placeholder="请输入事故原因（建议字数在14个字以内，不超过50个字)">
                                                                                <span class="help-block"></span>
                                                                            </div>
                                                                        </div>
                                                                                                                                         

                                                                </div>
                                                                <div class="form-actions">
                                                                    <div class="row">
                                                                        <div class="col-md-offset-3 col-md-7">
                                                                            <button class="btn   green" type="submit" style="width:30%">保存</button>
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

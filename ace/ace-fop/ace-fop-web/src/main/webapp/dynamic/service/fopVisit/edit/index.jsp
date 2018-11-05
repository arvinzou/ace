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
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css" />
        <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
    </head>

    <body>

    <jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>


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

    <%--=============common jsp-suffix===============--%>
    <jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
    <%--==============common jsp-suffix==============--%>
    </body>
    <script id="tpl-fm" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 control-label">
                走访企业
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-8">
                <input type="text" class="form-control" name="companyId" maxlength="50"
                       placeholder="请输入企业名称（不超过50个字)" value="\${data.o.companyId}">
                <input type="hidden" name="id" value="\${data.o.id}">
                <input type="hidden" name="status" value="\${data.o.status}">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                标题
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-8">
                <input type="text" class="form-control" name="title" value="\${data.o.title}" maxlength="200"
                       placeholder="请输入活动标题（建议字数在30个字以内，不超过200个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                走访时间
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-8">
                <input type="text" class="form-control" name="visitDate" value="\${data.o.visitDate}">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                走访内容
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-8">
                <textarea rows="5" cols="100" class="form-control" name="content" maxlength="500">\${data.o.content}</textarea>
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                备注
            </label>
            <div class="col-md-8">
                <textarea rows="5" cols="100" class="form-control" name="remark" maxlength="500">\${data.o.remark}</textarea>
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                附件

            </label>
            <div class="col-md-10">

                <div id="filelist-history"></div>
                <div id="filelist"></div>
                <div id="container">
                    <a id="pickfiles" href="javascript:;">[添加附件]</a> <a id="uploadfiles" href="javascript:;">[上传]</a>
                </div>
                <br/>
                <pre id="console"></pre>
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
    </script>


    <jsp:include page="/dynamic/common/footer.jsp"/>
    <script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js?v=${cfg.version}"></script>
    <script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>

    <script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
    <script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
    <script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>
    <script src="js/upload.js?v=${cfg.version}"></script>
    <script src="js/act.js?v=${cfg.version}"></script>
    </html>

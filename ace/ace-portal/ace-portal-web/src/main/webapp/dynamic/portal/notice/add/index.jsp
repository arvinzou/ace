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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
    </head>

    <body>
        <%--==============common jsp-prefix==============--%>
            <jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
            <%--==============common jsp-prefix==============--%>

                <!-- BEGIN SAMPLE TABLE PORTLET-->
                <div class="portlet light">

                    <div class="portlet-body" id="courseSource">
                        <div class="form-panel">
                            <!--具体界面元素开始-->
                            <form class="form-horizontal" id="fm-add" role="form">
                                <input type="hidden" name="noticeId" value="">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">
                                            
                                            标题
																						<span class="required" aria-required="true"> * </span>
                                        </label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" name="title" maxlength="100" placeholder="请输入标题（建议字数在28个字以内，不超过100个字)">
                                            <div class="error-title"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">
                                            
                                            类型
																						<span class="required" aria-required="true"> * </span>
                                        </label>
                                        <div class="col-md-10 radio-group-container">
                                            <label>
                                                <input type="radio" name="category" value="1"><span style="padding:10px">系统</span>
                                                
                                            </label>
                                            <label>
                                                <input type="radio" name="category" value="2"><span style="padding:10px">业务</span>
                                               
                                            </label>
                                            <div class="error-category"></div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">
                                           
                                            有效日期
																						<span class="required" aria-required="true"> * </span>
                                        </label>
                                        <div class="col-md-4">
                                            <input type="text" class="form-control" name="deadline">
                                            <div class="error-deadline"></div>
                                        </div>
                                        <div class="col-md-6">

                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">
                                           
                                            内容
																						<span class="required" aria-required="true"> * </span>
                                        </label>
                                        <div class="col-md-10">
                                            <textarea rows="5" cols="100" class="form-control" name="content" maxlength="500"></textarea>
                                            <div class="error-content"></div>


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
                                                附件：<a id="pickfiles" href="javascript:;">[添加附件]</a> <a id="uploadfiles" href="javascript:;">[上传]</a>
                                            </div>
                                            <br/>
                                            <pre id="console"></pre>
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
                            </form>
                        </div>
                        <!--具体界面元素结束-->
                    </div>
                </div>
                <!-- END SAMPLE TABLE PORTLET-->

               
                    <jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
                    
    </body>

        <jsp:include page="/dynamic/common/footer.jsp" />

        <script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js?v=${cfg.version}"></script>
        <script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
        <script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
        <script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>

        <script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
        <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
        <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
        <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
        <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>


    <script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/content/portal/js/notice/upload.js?version=${cfg.version}"></script>

        <script src="js/act.js?v=${cfg.version}"></script>
        <style>
            textarea {
                text-align: left;
            }
        </style>

    </html>

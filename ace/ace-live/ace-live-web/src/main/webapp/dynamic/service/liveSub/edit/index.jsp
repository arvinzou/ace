<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html lang="en">


    <head>
        <meta charset="utf-8" />
        <title>${cfg.sys_name}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="${cfg.sys_name}" name="${cfg.sys_name}" />
        <%--common css--%>
        <jsp:include page="/dynamic/common/header.jsp"/>
        <link rel="stylesheet" href="css/style.css">
        <%--custom css--%>
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
        <link rel="stylesheet"
              href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
              type="text/css" media="screen"/>
        <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
    </head>

    <body>
    <%--==============common jsp-prefix==============--%>
    <jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
    <%--==============common jsp-prefix==============--%>

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
                                        <span class="label-red">*</span>
                                        名称
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="name" value="\{data.o.name}" maxlength="100" placeholder="请输入名称（建议字数在14个字以内，不超过100个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        <span class="label-red">*</span>
                                        直播类型
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="category" value="\{data.o.category}" maxlength="20" placeholder="请输入直播类型（建议字数在14个字以内，不超过20个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        <span class="label-red">*</span>
                                        组织单位
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="deptId" value="\{data.o.deptId}" maxlength="100" placeholder="请输入组织单位（建议字数在14个字以内，不超过100个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        <span class="label-red">*</span>
                                        直播时间
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="startTime" value="\{data.o.startTime}" maxlength="" placeholder="请输入直播时间（建议字数在14个字以内，不超过个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="endTime" value="\{data.o.endTime}" maxlength="" placeholder="请输入（建议字数在14个字以内，不超过个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        <span class="label-red">*</span>
                                        摘要
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="remark" value="\{data.o.remark}" maxlength="500" placeholder="请输入摘要（建议字数在14个字以内，不超过500个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        <span class="label-red">*</span>
                                        活动介绍
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="content" value="\{data.o.content}" maxlength="2147483647" placeholder="请输入活动介绍（建议字数在14个字以内，不超过2147483647个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        <span class="label-red">*</span>
                                        参与人数
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="nop" value="\{data.o.nop}" maxlength="19" placeholder="请输入参与人数（建议字数在14个字以内，不超过19个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        <span class="label-red">*</span>
                                        点赞数
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="pop" value="\{data.o.pop}" maxlength="19" placeholder="请输入点赞数（建议字数在14个字以内，不超过19个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        地点
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="addr" value="\{data.o.addr}" maxlength="200" placeholder="请输入地点（建议字数在14个字以内，不超过200个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        直播流地址
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="rtmpUrl" value="\{data.o.rtmpUrl}" maxlength="200" placeholder="请输入直播流地址（建议字数在14个字以内，不超过200个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        回放地址
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="mp4Url" value="\{data.o.mp4Url}" maxlength="200" placeholder="请输入回放地址（建议字数在14个字以内，不超过200个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        <span class="label-red">*</span>
                                        封面
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="imageSrc" value="\{data.o.imageSrc}" maxlength="200" placeholder="请输入封面（建议字数在14个字以内，不超过200个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        维度
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="latitude" value="\{data.o.latitude}" maxlength="10" placeholder="请输入维度（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        经度
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="longitude" value="\{data.o.longitude}" maxlength="10" placeholder="请输入经度（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        <span class="label-red">*</span>
                                        审核状态1待审2通过3驳回
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="auditStatus" value="\{data.o.auditStatus}" maxlength="1" placeholder="请输入审核状态1待审2通过3驳回（建议字数在14个字以内，不超过1个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        最后审核记录ID
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="lastAuditLogId" value="\{data.o.lastAuditLogId}" maxlength="50" placeholder="请输入最后审核记录ID（建议字数在14个字以内，不超过50个字)">
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
    </script>

    <%--==============common footer==============--%>
    <jsp:include page="/dynamic/common/footer.jsp"/>
    <script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
    <%--custom js--%>
    <script type="text/javascript"
            src="/portal/content/common/js/jquery.form.js?version=${cfg.version}"></script>
    <script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
    <script type="text/javascript"
            src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
    <script type="text/javascript"
            src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
    <script type="text/javascript"
            src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
    <script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>

    <script src="${pageContext.request.contextPath}/content/common/js/cropUpload.js?version=${cfg.version}"></script>
    <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>

    <script src="js/act.js?v=${cfg.version}"></script>
    </html>

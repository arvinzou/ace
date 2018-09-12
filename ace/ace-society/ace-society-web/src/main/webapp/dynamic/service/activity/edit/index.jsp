<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html lang="en">


    <head>
        <meta charset="utf-8" />
        <title>${cfg.sys_name}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="${cfg.sys_name}" name="${cfg.sys_name}" />
        <!--公共部分开始-->
        <jsp:include page="/dynamic/common/base.jsp" />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/css/components.min.css" />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css" />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout3/css/themes/default.min.css"/>
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"/>
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout3/css/custom.min.css" />
        <!--公共部分结束-->

        <!--私有部分开始-->
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css" />
        <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
        <link rel="stylesheet" href="css/style.css">
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
                                            <span>线下活动管理</span>
                                        </li>
                                    </ul>
                                    <div class="page-content-inner">

                                        <!---==============================================-->
                                        <div class="row">
                                            <div class="col-md-12">
                                                <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                <div class="portlet light">
                                                    <div class="portlet-title">
                                                        <div class="caption">
                                                            编辑线下活动
                                                        </div>
                                                        <div class="actions">

                                                        </div>
                                                    </div>
                                                    <div class="portlet-body" id="courseSource">
                                                        <div class="form-panel">
                                                            <!--具体界面元素开始-->
                                                            <form class="form-horizontal" id="fm-edit" role="form">

                                                            </form>
                                                        </div>
                                                        <!--具体界面元素结束-->
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
    <jsp:include page="../../../common/footer.jsp" />
    <!--公共部分结束-->
    <!--私有部分开始-->
    <script src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>
    <script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/assets/global/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/module.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/hotkeys.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/uploader.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/simditor.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/portal/js/main/menu4.js" type="text/javascript"></script>
    <script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/plupload/plupload.full.min.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/content/common/js/upload.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/localization/messages_zh.js?v=${cfg.version}"></script>
    <script src="js/act.js?v=${cfg.version}"></script>

    <!--私有部分结束-->

    <script id="tpl-fm" type="text/template">
    <div class="form-body">
                             <div class="form-group">
                <label class="col-md-2 control-label">
                                        发起人ID
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="initiatorId" value="\{data.o.initiatorId}" maxlength="50" placeholder="请输入发起人ID（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        <span class="label-red">*</span>
                                        活动名称
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="title" value="\{data.o.title}" maxlength="50" placeholder="请输入活动名称（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        活动类型
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="category" value="\{data.o.category}" maxlength="2" placeholder="请输入活动类型（建议字数在14个字以内，不超过2个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        活动摘要
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="summary" value="\{data.o.summary}" maxlength="300" placeholder="请输入活动摘要（建议字数在14个字以内，不超过300个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        活动内容
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="content" value="\{data.o.content}" maxlength="2147483647" placeholder="请输入活动内容（建议字数在14个字以内，不超过2147483647个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        活动封面
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="coverUrl" value="\{data.o.coverUrl}" maxlength="300" placeholder="请输入活动封面（建议字数在14个字以内，不超过300个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        报名截止时间
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="dendline" value="\{data.o.dendline}" maxlength="" placeholder="请输入报名截止时间（建议字数在14个字以内，不超过个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        <span class="label-red">*</span>
                                        开展时间
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="startDate" value="\{data.o.startDate}" maxlength="" placeholder="请输入开展时间（建议字数在14个字以内，不超过个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        结束时间
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="endDate" value="\{data.o.endDate}" maxlength="" placeholder="请输入结束时间（建议字数在14个字以内，不超过个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        志愿者数量
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="volunteerNum" value="\{data.o.volunteerNum}" maxlength="10" placeholder="请输入志愿者数量（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        志愿者获得积分
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="volunteerPoints" value="\{data.o.volunteerPoints}" maxlength="10" placeholder="请输入志愿者获得积分（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        参与者数量
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="parterNum" value="\{data.o.parterNum}" maxlength="10" placeholder="请输入参与者数量（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        参与者扣除积分
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="parterPoints" value="\{data.o.parterPoints}" maxlength="10" placeholder="请输入参与者扣除积分（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
                     <div class="form-group">
                <label class="col-md-2 control-label">
                                        备注
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="remark" value="\{data.o.remark}" maxlength="200" placeholder="请输入备注（建议字数在14个字以内，不超过200个字)">
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
    </html>

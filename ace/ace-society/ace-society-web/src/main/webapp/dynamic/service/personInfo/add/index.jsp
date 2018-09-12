<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="utf-8"/>
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="${cfg.sys_name}"/>
    <!--公共部分开始-->
    <jsp:include page="/dynamic/common/base.jsp"/>
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/css/components.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/layouts/layout3/css/themes/default.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/layouts/layout3/css/custom.min.css"/>
    <!--公共部分结束-->

    <!--私有部分开始-->
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
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
                                    <span>个人信息管理</span>
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
                                                    创建个人信息
                                                </div>
                                                <div class="actions">

                                                </div>
                                            </div>
                                            <div class="portlet-body" id="courseSource">
                                                <div class="form-panel">
                                                    <!--具体界面元素开始-->
                                                    <form class="form-horizontal" id="fm-add" role="form">
                                                        <div class="form-body">
                                                            <div class="form-group">
                                                                <label class="col-md-2 control-label">
                                                                    <span class="label-red">*</span>
                                                                    姓名
                                                                </label>
                                                                <div class="col-md-10">
                                                                    <input type="text" class="form-control"
                                                                           name="realName" maxlength="50"
                                                                           placeholder="请输入姓名（建议字数在14个字以内，不超过50个字)">
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-md-2 control-label">
                                                                    <span class="label-red">*</span>
                                                                    手机号
                                                                </label>
                                                                <div class="col-md-10">
                                                                    <input type="text" class="form-control"
                                                                           name="mobilePhone" maxlength="20"
                                                                           placeholder="请输入手机号（建议字数在14个字以内，不超过20个字)">
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-md-2 control-label">
                                                                    政治面貌
                                                                </label>
                                                                <div class="col-md-10">
                                                                    <input type="text" class="form-control"
                                                                           name="politicalStatus" maxlength="2"
                                                                           placeholder="请输入政治面貌（建议字数在14个字以内，不超过2个字)">
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-md-2 control-label">
                                                                    累计获取积分
                                                                </label>
                                                                <div class="col-md-10">
                                                                    <input type="text" class="form-control"
                                                                           name="accPoints" maxlength="10"
                                                                           placeholder="请输入累计获取积分（建议字数在14个字以内，不超过10个字)">
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-md-2 control-label">
                                                                    可用积分
                                                                </label>
                                                                <div class="col-md-10">
                                                                    <input type="text" class="form-control"
                                                                           name="validPoints" maxlength="10"
                                                                           placeholder="请输入可用积分（建议字数在14个字以内，不超过10个字)">
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-md-2 control-label">
                                                                    备注
                                                                </label>
                                                                <div class="col-md-10">
                                                                    <input type="text" class="form-control"
                                                                           name="remark" maxlength="200"
                                                                           placeholder="请输入备注（建议字数在14个字以内，不超过200个字)">
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>


                                                        </div>
                                                        <div class="form-actions">
                                                            <div class="row">
                                                                <div class="col-md-offset-3 col-md-7">
                                                                    <button class="btn  btn-lg  green" type="submit"
                                                                            style="width:30%">保存
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
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
<jsp:include page="../../../common/footer.jsp"/>
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
</html>

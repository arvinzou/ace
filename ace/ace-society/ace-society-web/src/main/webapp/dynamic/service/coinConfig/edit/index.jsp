<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="utf-8"/>
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="${cfg.sys_name}"/>
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
    <div class="portlet-title">
        <div class="caption">
            编辑爱心币配置
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

<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>
<script id="tpl-fm" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 control-label">
                <span class="label-red">*</span>
                类型
            </label>
            <div class="col-md-10">
                <select name="category" id="">
                    <option \${data.o.category==1?'selected':''} value="1">公益活动</option>
                    <option \${data.o.category==2?'selected':''} value="2">普及活动</option>
                    <option \${data.o.category==3?'selected':''} value="3">创意活动</option>
                    <option \${data.o.category==4?'selected':''} value="4">党建活动</option>
                    <option \${data.o.category==5?'selected':''} value="5">随手拍</option>
                    <option \${data.o.category==6?'selected':''} value="6">我有点子</option>
                    <option \${data.o.category==7?'selected':''} value="7">秀我直播</option>
                    <option \${data.o.category==8?'selected':''} value="8">邻里圈子</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                名称
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="name" value="\${data.o.name}" maxlength="50"
                       placeholder="请输入名称（建议字数在10个字以内，不超过20个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                主办者
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="host" value="\${data.o.host}" maxlength="10"
                       placeholder="请输入爱心币的数量。">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                参与者
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="participant" value="\${data.o.participant}" maxlength="10"
                       placeholder="请输入爱心币的数量。（正数是奖励，负数是扣除,可以不填）">
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

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="utf-8"/>
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="${cfg.sys_name}"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet"
          href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
          type="text/css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
    <link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
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
            <div class="form-group">
                <label class="col-md-2 control-label">
                    路况路患地点
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-5">
                    <%--   <input type="text" class="form-control" name="address" maxlength="200" readonly
                              placeholder="请选择路况路患地点"
                              style="float:left;width:80% " value="\${data.o.address}">

                       <a href="javascript:window.open('${portalPath}/dynamic/common/map.jsp')"
                          style="float:right;line-height: 28px;">选择</a>--%>
                    <a href="${pageContext.request.contextPath}/dynamic/service/roadSection/previewMap.jsp?latitude=\${data.o.latitude}&longitude=\${data.o.longitude}"
                       target="_blank">\${data.o.address}</a>
                    <span class="help-block"></span>
                    <input name="address" type="hidden" value="\${data.o.address}"/>
                    <input name="latitude" type="hidden" value="\${data.o.latitude}"/>
                    <input name="longitude" type="hidden" value="\${data.o.longitude}"/>
                    <input name="areaCode" type="hidden" value="\${data.o.areaCode}"/>
                    <input name="id" type="hidden" value="\${data.o.id}"/>
                    <input name="userId" type="hidden" value="\${data.o.userId}"/>
                    <input name="dptId" type="hidden" value="\${data.o.dptId}"/>
                    <input name="createDate" type="hidden" value="\${data.o.createDate}"/>
                    <input name="reportDate" type="hidden" value="\${data.o.reportDate}"/>
                    <input name="createUserId" type="hidden" value="\${data.o.createUserId}"/>
                    <input name="createUserName" type="hidden" value="\${data.o.createUserName}"/>
                    <input name="status" type="hidden" value="2"/>
                    <div class="error-address"></div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-2 control-label">
                    所属路段
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-5">
                    <input type="text" class="form-control" style="width:450px" id="roadSectionId" name="roadSectionId"
                           maxlength="50" readonly
                           placeholder="请输入所属路段（建议字数在14个字以内，不超过50个字)" value="\${data.o.roadSectionId}">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    所属路长
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-5">
                    <input type="text" id="roadManId" class="form-control" style="width:450px"
                           name="roadManId" readonly
                           maxlength="50" placeholder="请输入所属路长（建议字数在14个字以内，不超过50个字)" value="\${data.o.roadManId}">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    路患类型
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-5">
                    {@each data.dict['175'] as item, index}
                    {@if item.CODE!=''}
                    {@if item.CODE === data.o.type}
                    <input type="hidden" class="form-control" name="type" value="\${item.CODE}">\${item.NAME}
                        <span></span>
                    {@/if}
                    {@/if}
                    {@/each}
                    <div class="error-type"></div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    上报时间
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-5">
                    <input type="text" class="form-control" name="reportDate"
                           value="\${data.o.reportDate}">
                    <span class="help-block"></span>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">
                    路患描述
                </label>
                <div class="col-md-6">
                    <div class="radio-group-container">
                        <textarea name="remark" cols="80" rows="3" readonly>\${data.o.remark} </textarea>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    现场照片
                </label>
                <div id="image" class="col-md-5">
                    <ul>
                        {@each data.o.picList as pic, index}
                        <li><img src="\${pic.fileUrl}" class="cover"/></li>
                        {@/each}
                    </ul>

                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">
                    整改措施
                </label>
                <div class="col-md-6">
                    <div class="radio-group-container">
                        <textarea name="takeSteps" cols="80" rows="4">\${data.o.takeSteps} </textarea>

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    整改照片
                </label>
                <div id="changeimage" class="col-md-5">
                    <ul>
                        {@each data.o.changedList as pic2, index}
                        <li><img src="\${pic2.fileUrl}" class="cover"/></li>
                        {@/each}
                    </ul>

                </div>

            </div>
            <div class="form-group">
                <div class="col-md-2  col-md-offset-2">
                    <button type="button" class="btn  green" id="btn-upload-add" authority="false">上传图片</button>
                </div>
            </div>

            <div class="form-group">
                <div id="images" class="col-md-5 col-md-offset-2">
                    <ul>
                    </ul>
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

<div class="modal fade" role="dialog" id="modal-upload">
    <div class="modal-dialog" role="document" style="width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" authority="false" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">图片上传</h4>
            </div>
            <div class="modal-body">

                <div id="uploader">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/dynamic/common/footer.jsp"/>

script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
<
script
src = "${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}" ></script>
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"
        type="text/javascript"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>

<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
<script src="js/upload.js?version=${cfg.version}"></script>
</html>

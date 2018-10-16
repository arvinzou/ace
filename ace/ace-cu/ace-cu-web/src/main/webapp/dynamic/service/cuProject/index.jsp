<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>慈善项目</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}"/>
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
<link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
      rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${portalPath}/content/common/assets/css/colorbox.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"/>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light ">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-8">
                <button type="button" class="btn green" id="btn-view-add"
                        authority="${pageContext.request.contextPath}/cuProject/insertCuProject">添加
                </button>
            </div>

            <div class="col-md-4">
                <form action="#" id="fm-search">
                    <div class="input-group" style="float: left;">
                        类别 <input name="type" class="easyui-combobox" style="width: 200px;height: 30px;line-height:30px"
                                  data-options="
                            url:'${portalPath}/dict/findListByCategoryId.do?categoryId=142&selected=false',
                            method:'get',
                            valueField:'code',
                            textField:'name',
                            panelHeight:'auto'">
                    </div>
                    <div class="input-group" style="width: 250px;float: right">
                        <input type="text"
                               name="projectName"
                               class="form-control"
                               style="width: 200px"
                               placeholder="请输入项目名称">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn" id="btn-search"
                                    authority="${pageContext.request.contextPath}/cuProject/findCuProjectList">
									搜索
							</button>
						</span>
                    </div>
                </form>
            </div>

        </div>

        <table id="grid-table">

        </table>

        <div class="paginationbar">
            <ul id="grid-pager" class="pagination"></ul>
        </div>
    </div>
</div>
<%--消息框--%>

<div class="modal fade" role="dialog" id="modal-upload">
    <div class="modal-dialog" role="document" style="width:80%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">文件上传</h4>
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
<div class="modal fade" role="dialog" id="modal-upload-view">
    <div class="modal-dialog" role="document" style="width:60%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">文件</h4>
            </div>
            <div class="modal-body">
                <div id="load" class="loading"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>
<%--下线描述框--%>
<div class="modal fade" role="dialog" id="modal-showdown">
    <div class="modal-dialog" role="document" style="width:30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <%--<h4 class="modal-title">下线原因</h4>--%>
            </div>
            <div class="modal-body">
                <form action="/cuProject/showDown" id="fm-showDown">
                    <fieldset>
                        <span style="display: block;float: left;padding-right: 20px;">下线原因 </span>
                        <textarea id="shutdown_desc" cols="65" rows="15"></textarea><span
                            style="color:red;font-size:16px;font-weight:800">*</span>
                    </fieldset>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
                <button type="button" class="btn green" authority="false" id="btn-view-showdown">确定</button>
            </div>
        </div>
    </div>
</div>
<%--审核框--%>
<div class="modal fade" role="dialog" id="modal-audit">
    <div class="modal-dialog" role="document" style="width:30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">审核</h4>
            </div>
            <div class="modal-body">
                <form action="/cuProject/audit" id="fm-audit">
                    <fieldset>
                        <span style="display:  block;float: left;padding-right: 10px">审核结果 </span>
                        <div>
                            <input id="audit_pass" name="audit_result" type="radio" value="0"
                                   style="padding-left: 10px"/> 通过
                            <input id="audit_unpass" name="audit_result" type="radio" value="1"
                                   style="padding-left: 10px"/> 不通过
                        </div>
                    </fieldset>
                    <fieldset style="padding-top: 20px">
                        <span style="display:  block;float: left;padding-right: 10px">审核备注 </span>
                        <textarea id="audit_opinion" cols="65" rows="10"></textarea>
                    </fieldset>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
                <button type="button" class="btn green" authority="false" id="btn-view-audit">确定</button>
            </div>
        </div>
    </div>
</div>
<%--详情框--%>
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width:80%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">查看详情</h4>
            </div>
            <div class="modal-body">
                <%--modal-body-start--%>
                <h5 class="header-title">基本信息</h5>
                <div class="row" style="padding:10px">
                    <div class="labelItem hide">
                        <span class="labelItemHeader">主键</span>
                        <br>
                        <span id="id"></span>
                    </div>
                    <div class="labelItem hide">
                        <span class="labelItemHeader">隶属于</span>
                        <br>
                        <span id="parentId"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">项目名称</span>
                        <br>
                        <span id="projectName"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">项目类型</span>
                        <br>
                        <span id="type"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">项目开始时间</span>
                        <br>
                        <span id="startDate"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">项目结束时间</span>
                        <br>
                        <span id="endDate"></span>
                    </div>
                    <div class="labelItem hide">
                        <span class="labelItemHeader">目标金额</span>
                        <br>
                        <span id="targetAmount"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">已募集金额</span>
                        <br>
                        <span id="collectAmount"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">已支出金额</span>
                        <br>
                        <span id="payoutAmount"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">结余金额</span>
                        <br>
                        <span id="balanceAmount"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">备注</span>
                        <br>
                        <span id="remark"></span>
                    </div>
                    <div class="labelItem hide">
                        <span class="labelItemHeader">状态</span>
                        <br>
                        <span id="status"></span>
                    </div>
                </div>

                <h5 class="header-title">项目标题</h5>
                <div id="title" class="row" style="padding:10px">
                </div>

                <h5 class="header-title">项目封面</h5>
                <div id="commodityCover" class="row" style="padding:10px">
                </div>

                <h5 class="header-title">项目详情</h5>
                <div id="description" class="row" style="padding:10px">
                </div>

                <h5 class="header-title">操作信息</h5>
                <div class="row" style="padding:10px">
                    <div class="labelItem hide">
                        <span class="labelItemHeader">创建人编号</span>
                        <br>
                        <span id="createUserId"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">创建人姓名</span>
                        <br>
                        <span id="createUserName"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">入库日期</span>
                        <br>
                        <span id="createDate"></span>
                    </div>
                    <div class="labelItem hide">
                        <span class="labelItemHeader">最后更新人编号</span>
                        <br>
                        <span id="lastModifyUserId"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">最后更新人姓名</span>
                        <br>
                        <span id="lastModifyUserName"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">最后更新时间</span>
                        <br>
                        <span id="lastModifyDate"></span>
                    </div>
                </div>

                <%--modal-body-end--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>


<jsp:include page="/dynamic/common/footer.jsp"/>

<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>

<script src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"
        type="text/javascript"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>


<script src="${pageContext.request.contextPath}/content/service/cuProject/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cuProject/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cuProject/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cuProject/view.js?version=${cfg.version}"></script>

<script src="${pageContext.request.contextPath}/content/service/cuProject/upload.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

<style>
    .form-group {
        padding-left: 10px;
        padding-right: 10px;
        float: left;
    }

    .labelItem {
        width: 180px;
        height: 38px;
        float: left;
        margin: 4px 4px 4px;
    }

    .labelItemHeader {
        font-weight: 800;
        font-size: 14px;
    }

    .ui-jqgrid .ui-jqgrid-hdiv {
        overflow-x: hidden;
    }

</style>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>救急难申请表</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}"/>
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
<link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
      rel="stylesheet" type="text/css"/>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light ">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-4"></div>

            <div class="col-md-8">
                <form action="#" id="fm-search">
                    <div class="input-group" style="width: 250px;float: right">
                        <input type="text"
                               name="nickname"
                               class="form-control"
                               style="width: 200px"
                               placeholder="请输入微信昵称">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn" id="btn-search"
                                    authority="${pageContext.request.contextPath}/cuProjectApply/findCuProjectApplyList">
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


<%--强制下线弹框--%>
<div class="modal fade" role="dialog" id="modal-shutdown">
    <div class="modal-dialog" role="document" style="width:30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">审核</h4>
            </div>
            <div class="modal-body">
                <form action="/cuProject/showDown" id="fm-showDown">
                    <fieldset>
                        下线缘由： <textarea id="shutdown_reason" cols="30" rows="10"></textarea><span
                            style="color:red;font-size:16px;font-weight:800">*</span>
                    </fieldset>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
                <button type="button" class="btn green" authority="false" id="btn-view-shutdown">确定</button>
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
                <h5 class="header-title">筹款标题</h5>
                <div class="row" style="padding:10px" id="title">
                </div>
                <h5 class="header-title">筹款说明</h5>
                <div class="row" style="padding:10px" id="description">
                </div>
                <h5 class="header-title">基本信息</h5>
                <div class="row" style="padding:10px">
                    <div class="labelItem hide">
                        <span class="labelItemHeader">主键</span>
                        <br>
                        <span id="id"></span>
                    </div>
                    <div class="labelItem hide">
                        <span class="labelItemHeader">申请人ID</span>
                        <br>
                        <span id="applyUserId"></span>
                    </div>
                    <div class="labelItem hide">
                        <span class="labelItemHeader">申请人微信openId</span>
                        <br>
                        <span id="applyOpenId"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">目标金额(元)</span>
                        <br>
                        <span id="targetAmount"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">受益人真实姓名</span>
                        <br>
                        <span id="realName"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">受益人联系号码</span>
                        <br>
                        <span id="mobileNumber"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">受益人身份证号码</span>
                        <br>
                        <span id="idCard"></span>
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
                <h5 class="header-title">上传资料</h5>


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


<script src="${pageContext.request.contextPath}/content/service/cuProjectApply/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cuProjectApply/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cuProjectApply/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cuProjectApply/view.js?version=${cfg.version}"></script>
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

</style>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>捐献记录</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}"/>
<link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
      rel="stylesheet" type="text/css"/>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light ">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-2 toolbar">

            </div>

            <div class="col-md-10">
                <form action="#" id="fm-search">
                    <div class="input-group form-group">
                        索要票据 <input name="needReceipt" class="easyui-combobox"
                                    style="width: 200px; height:30px;line-height: 30px;"
                                    data-options="
                                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=146&selected=false',
                                    method:'get',
                                    valueField:'code',
                                    textField:'name',
                                    panelHeight:'auto'">
                    </div>

                    <div style="width:40px;float:left;line-height:30px"> 时间</div>
                    <div class="input-group date form_datetime"
                         style="width:15%;float:left;border: 1px solid #efefef;">
                        <input type="text" size="16" name="startDate" readonly="false" class="form-control">
                    </div>

                    <span class="input-group-addon" style="width:40px;float:left;"><font
                            style="vertical-align: inherit;"><font
                            style="vertical-align: inherit;"> 至 </font></font></span>
                    <div class="input-group date form_datetime"
                         style="width:15%;float:left;border: 1px solid #efefef;">
                        <input type="text" size="16" name="endDate" readonly="false" class="form-control">

                    </div>

                    <div class="input-group" style="width: 250px;float: right">
                        <input type="text"
                               name="donatePostName"
                               class="form-control"
                               placeholder="请输入捐款单位名称">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn" id="btn-search"
                                    authority="${pageContext.request.contextPath}/cuDonateList/findCuDonateListList">
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
                <h5 class="header-title">基本信息</h5>
                <div class="row" style="padding:10px">
                    <div class="labelItem hide">
                        <span class="labelItemHeader">主键</span>
                        <br>
                        <span id="id"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">项目名称</span>
                        <br>
                        <span id="projectName"></span>
                    </div>
                    <div class="labelItem hide">
                        <span class="labelItemHeader">用户ID</span>
                        <br>
                        <span id="userId"></span>
                    </div>
                    <div class="labelItem hide">
                        <span class="labelItemHeader">支付订单ID</span>
                        <br>
                        <span id="orderId"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">微信昵称</span>
                        <br>
                        <span id="nickname"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">捐献金额(元)</span>
                        <br>
                        <span id="donateAmount"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">捐献时间</span>
                        <br>
                        <span id="donateDate"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">捐款人姓名</span>
                        <br>
                        <span id="donateName"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">捐款人手机号码</span>
                        <br>
                        <span id="mobileNumber"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">捐款人单位名称</span>
                        <br>
                        <span id="donatePostName"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">索要票据</span>
                        <br>
                        <span id="needReceipt"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">收货人名称</span>
                        <br>
                        <span id="consigneeName"></span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">收货人号码</span>
                        <br>
                        <span id="consigneeMobileNumber"></span>
                    </div>
                </div>

                <h5 class="header-title">收货人地址</h5>
                <div id="consigneeAddr" class="row" style="padding:10px"></div>

                <h5 class="header-title">备注</h5>
                <div id="remark" class="row" style="padding:10px"></div>

                <h5 class="header-title">操作信息</h5>
                <div class="row" style="padding:10px">
                    <div class="labelItem hide">
                        <span class="labelItemHeader">状态</span>
                        <br>
                        <span id="status"></span>
                    </div>
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

<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/export.js?version=${cfg.version}"></script>


<script src="${pageContext.request.contextPath}/content/service/cuDonateList/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cuDonateList/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cuDonateList/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/cuDonateList/view.js?version=${cfg.version}"></script>
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
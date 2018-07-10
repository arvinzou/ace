<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>入驻成员列表</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<script type="text/javascript">


</script>
<body>
<div class="page-content">
    <div class="widget-box" id="widget-box">
        <div class="widget-header">
            <h5 class="widget-title smaller">设置查询条件</h5>
            <div class="widget-toolbar"></div>
        </div>
        <div class="widget-body">
            <div class="widget-main padding-6">
                <form action="#" id="fm-search">
                    类别：<input name="category" class="easyui-combobox" style="width: 200px"
                              data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=69&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                    名称： <input name="name" type="text" style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/vipDepartment/findVipDepartmentList">
                        <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">

                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/vipDepartment/insertVipDepartment">
                        <i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/vipDepartment/updateVipDepartment">
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/vipDepartment/deleteVipDepartmentByVipDepartmentId">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>

                </div>
            </div>
        </div>
    </div>

    <table id="grid-table"></table>

    <div id="grid-pager"></div>


</div>
<div id="dialog-message" class="hide">
    <div id="uploader">
        <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
    </div>
</div>
<div id="dialog-message-file" class="hide">
    <div id="load" class="loading"></div>
</div>

<div id="dialog-message-view" class="hide">
    <h5 class="header-title">基本信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem"><span class="labelItemHeader">
编号</span>
            <br>
            <span id="departmentId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
</span>
            <br>
            <span id="id">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
所属协会</span>
            <br>
            <span id="parentDepartmentId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
企业名称</span>
            <br>
            <span id="departmentName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
简称</span>
            <br>
            <span id="shortName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
类型</span>
            <br>
            <span id="category">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
所属地区</span>
            <br>
            <span id="areaCode">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
名称简拼</span>
            <br>
            <span id="pcode">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
联系人姓名</span>
            <br>
            <span id="contactName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
联系人电话</span>
            <br>
            <span id="contactTel">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
联系人手机号</span>
            <br>
            <span id="contactMobile">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
联系人QQ</span>
            <br>
            <span id="contactQq">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
联系人电子邮箱</span>
            <br>
            <span id="contactEmail">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
联系人传真</span>
            <br>
            <span id="contactFax">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
法定联系人</span>
            <br>
            <span id="legalPersonName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
法人证件类型</span>
            <br>
            <span id="legalPersonIdType">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
法定人证件号</span>
            <br>
            <span id="legalPersonIdNo">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
法人联系人电话</span>
            <br>
            <span id="legalPersonTel">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
法人联系人手机号</span>
            <br>
            <span id="legalPersonMobile">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
</span>
            <br>
            <span id="legalPersonAddr">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
注册时间</span>
            <br>
            <span id="regDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
注册资本</span>
            <br>
            <span id="regCapital">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
注册区号</span>
            <br>
            <span id="regAreaCode">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
注册地址</span>
            <br>
            <span id="regAddr">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
经济性质[国有、集体、私营、联营、三资、其他]</span>
            <br>
            <span id="nature">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
营业执照号</span>
            <br>
            <span id="bussLicenseNo">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
经营地址</span>
            <br>
            <span id="bussAddr">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
经营区号</span>
            <br>
            <span id="bussAreaCode">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
传真</span>
            <br>
            <span id="fax">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
公司邮箱</span>
            <br>
            <span id="email">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
交通部门批文号</span>
            <br>
            <span id="transDepartApprovalNo">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
道路运输经营许可证号</span>
            <br>
            <span id="transBussLicenseNo">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
许可证有效日期</span>
            <br>
            <span id="transBussLicenseValidDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
道路运输经营许可证经营范围[普通货运货物专用运输(集装箱)货物专用运输(冷藏保鲜)货物专用运输(罐式)</span>
            <br>
            <span id="transBussScope">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
从业人员数</span>
            <br>
            <span id="employeesNum">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
驾驶员数</span>
            <br>
            <span id="driverNum">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
无证人数</span>
            <br>
            <span id="unlicensedDriverNum">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
持证人数</span>
            <br>
            <span id="licensedDriverNum">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
本年度安全事故情况</span>
            <br>
            <span id="accidentOfYear">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
有无投诉处罚情况</span>
            <br>
            <span id="complaintsRemark">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
是状态：1=正常、2=注销</span>
            <br>
            <span id="status">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
企业类型</span>
            <br>
            <span id="type">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
小图片</span>
            <br>
            <span id="simage">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
大图片</span>
            <br>
            <span id="bimage">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
</span>
            <br>
            <span id="createUserId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
创建的用户名</span>
            <br>
            <span id="createUserName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
</span>
            <br>
            <span id="createTime">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后修改时间</span>
            <br>
            <span id="lastModifyTime">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后修改用户ID</span>
            <br>
            <span id="lastModifyUserId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后修改用户名</span>
            <br>
            <span id="lastModifyUserName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
</span>
            <br>
            <span id="latitude">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
</span>
            <br>
            <span id="longitude">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
</span>
            <br>
            <span id="serviceTimeStart">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
</span>
            <br>
            <span id="serviceTimeEnd">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
</span>
            <br>
            <span id="qrcode">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
</span>
            <br>
            <span id="serviceWay">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
系统标示</span>
            <br>
            <span id="syid">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
LOGO</span>
            <br>
            <span id="logo">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
水印1</span>
            <br>
            <span id="watermark1">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
水印2</span>
            <br>
            <span id="watermark2">
</span>
        </div>
    </div>
    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
    </div>

</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script src="${pageContext.request.contextPath}/content/service/vipDepartment/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/vipDepartment/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/vipDepartment/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/vipDepartment/view.js?version=${cfg.version}"></script>
<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
    window.onresize = function () {
        console.log('autoWidthJqgrid');
        $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        $(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
        parent.autoWidth();
    }
</script>
</body>
</html>
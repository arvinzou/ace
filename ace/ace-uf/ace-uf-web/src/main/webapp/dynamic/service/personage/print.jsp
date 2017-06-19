<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>统战人士花名册</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<script type="text/javascript">
var params={};
params.areaCode='${param.areaCode}';
params.category='${param.category}';
params.limit=9999999

</script>
<style>
@media print {
    .noprint {
        display: none
    }
}


</style>
<!-- 这个是普通样式 -->
<style type="text/css">
.print table{border-right:1px solid #000000;border-bottom:1px solid #000000}
.print table td{border-left:1px solid #000000;border-top:1px solid #000000}
.print table th{border-left:1px solid #000000;border-top:1px solid #000000;text-align:center}
</style>
<body>
<div class="page-content">

    <div style="text-align: right" class="noprint">
        <button class="btn btn-app btn-light btn-xs" autdority="false"
                id="btn-print">
            <i class="ace-icon fa fa-print bigger-160"></i> 打印
        </button>

    </div>
    <div style="text-align:center">
        <h1>统战人士花名册</h1>
        <table width="80%" border="0" align="center" cellpadding="0"
               cellspacing="0">
            <tr>
                <td style="text-align:left">填报单位：<span id="deptName"></span></td>
                <td style="text-align:right">填报时间:<span id="date"></span></td>
            </tr>
        </table>
        <div class="print">
            <table id="report" width="90%" border="0" align="center" cellpadding="2" cellspacing="0" class="tabp">
                <thead>
                    <tr>
                    <th> 姓名</th>
                    <th>性别</th>
                    <th>民族</th>
                    <th>籍贯</th>
                    <th>出生年月</th>
                    <th>参加工作时间</th>
                    <th>文化程度</th>
                    <th>单位职务及职称</th>
                    <th>现任职时间</th>
                    <th>政治安排情况</th>
                    <th>联系电话</th>
                    <th>备注</th>
                    </tr>
                </thead>
                <tbody id="content">

                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="../../common/footer-1.jsp"/>
<script
        src="${portalPath}/content/common/js/areaCode.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/personage/print.js?version=${cfg.version}"></script>

</body>
</html>
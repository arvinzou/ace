<%@page import="com.huacainfo.ace.common.tools.CommonUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>memberCenter</title>
</head>
<jsp:include page="../../../common/common.jsp" />
<script type="text/javascript">
    var depatId="  ";
	var parDeptId = '${SESSION_USERPROP_KEY.parentCorpId}';
</script>
<body>
	<div class="page-content" style="margin:20px 20px;">
		<div style="color:red;font-size:16px;">申请提交完毕！</div>
		
		<h3>1.请尽快下载与填写 “深圳市公路货运与物流行业协会企业会员入会申请表” 一式二份，单位负责人签名，加盖公司公章。</h3>
		<h3>2.备企业法人营业执照副本复印件一份。</h3>
		<h3>3.凭填好的“入会申请表”和 “企业法人营业执照复印件”，到协会秘书处办理入会手续；</h3></hr></hr>
		
		<div>协会秘书处地址: 深圳市福田区车公庙泰然九路一号盛唐大厦西座2713；联系人：杨小姐  13798292094；</div>
		附件下载：<a href="javascript:download_application();">深圳市公路货运与物流行业协会企业会员入会申请表.doc</a>
		<div class="space-12"></div>
		<div align="center">
			<a class="btn btn-info" id="btn-editDepetInfo" href="#" authority="false" style="width:100px;font-size:16px;text-align:'center'" 
				onclick="javascript:approver_end();">完成</a>
		</div>
	</div>

	<jsp:include page="../../../common/footer-1.jsp" />
	<jsp:include page="../../../common/footer-2.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/companyUserCenter/controller.js?version=${cfg.version}"></script>
	<script type="text/javascript">
		window.onresize = function() {
			autoSize();
		}
		parent.onresize = function() {
			autoSize();
		}
		function autoSize() {
			parent.autoWidth();
		}
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<meta name="viewport"
		  content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<title>个性化配置</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

	<div class="portlet-body">
		<div style="width:100%;" id="fm">

		</div>

	</div>
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

<jsp:include page="/dynamic/common/footer.jsp" />
<script src="js/act.js?version=${cfg.version}"></script>

<script id="tpl-fm" type="text/template">

	<table class="portalType" align="center">
		<tr>
			<td valign="middle">
				<img src="img/1.png">
			</td >
			<td valign="middle">
				<img src="img/2.png">
			</td>
		</tr>
		<tr>
			<td valign="middle">

				<input type="radio" name="portalType" value="1" \${data.portalType==1?"checked":""}>
			</td>
			<td valign="middle">
				<input type="radio" name="portalType" value="3" \${data.portalType==3?"checked":""}>
			</td>
		</tr>
		<tr>
			<td colspan="2"><button class="btn green" type="button" style="width:150px" id="btn-submit">保存</button></td>
		</tr>


	</table>

</script>

<style>
.portalType td{
padding:10px;
text-align:center;
}
.portalType img{
border:1px solid blue
}
</style>
</body>
</html>
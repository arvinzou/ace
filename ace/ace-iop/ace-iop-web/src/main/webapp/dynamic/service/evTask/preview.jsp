<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String evTaskId = request.getParameter("evTaskId");
	if (SnailUtils.isBlankString(evTaskId)) {
		Object obj = session.getAttribute(CommonKeys.SystemUser);
		SystemUser systemUser = (SystemUser) obj;
	}
	request.setAttribute("evTaskId", evTaskId);
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>评测任务配置</title>
</head>
<script type="text/javascript">
	var evTaskId = '${evTaskId}';
</script>
<jsp:include page="../../common/common.jsp" />
<style>
@media print {
    .noprint {
        display: none
    }
}
</style>

<body>
	<div class="page-content">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<td align="center"><strong>基本信息</strong></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center">
						<table style="width: 80%">
							<tr>
								<td align="right">编号：</td>
								<td id="evTaskId"></td>
								<td align="right">名称：</td>
								<td id="evTaskName"></td>
								<td align="right">评测对象：</td>
								<td id="evObjName"></td>
								<td align="right">开始时间：</td>
								<td id="evStartDate"></td>
								<td align="right">截止时间：</td>
								<td ><span class='badge badge-info' id="evDeadline"></span></td>
							</tr>
							<tr>
								<td align="right">状态：</td>
								<td id="status"></td>
								<td align="right">创建时间：</td>
								<td id="createTime"></td>
								<td align="right">评测模板：</td>
								<td id="evTempleteId"></td>
								<td align="right">操作员：</td>
								<td id="adminName"></td>
								<td align="right">类别：</td>
								<td id="category"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center"><strong
						class="label label-lg label-info arrowed-in arrowed-right">第一步
							准备工作</strong></td>
				</tr>

				<tr>
					<td align="left">
						<div id="evContent"></div> <div style="width: 100%;text-align:center">
							
								<a href="javascript:setEvContent()">设置评测说明</a>
							
						</div>
					</td>
				</tr>
				<tr>
					<td align="center"><strong
						class="label label-lg label-info arrowed-in arrowed-right">第二步
							开启投票</strong></td>
				</tr>
				<tr>
					<td align="center">

					<a href="javascript:startEv()">开启投票</a> <a
						href="javascript:preview()">查看样表</a>

						</td>
				</tr>
				<tr>
					<td align="center" >

					<strong
						class="label label-lg label-info arrowed-in arrowed-right" id="preview-cns1">第三步
							分发账号</strong>

							</td>
				</tr>
				<tr>
					<td align="center" id="preview-cns2">

					<a href="javascript:setTaskUser()">分发账号</a> <a
						href="javascript:viewTaskUser()">账号列表</a>


						</td>
				</tr>
				<tr>
					<td align="center"><strong
						class="label label-lg label-info arrowed-in arrowed-right">第四步
							投票监控</strong></td>
				</tr>
				<tr>
					<td align="center"  id="jk">测评事务结束，本次测评共分发账号：14个，实际参与 14 人，参评率 100.0%。</td>
				</tr>
				<tr>
					<td align="center"><strong
						class="label label-lg label-info arrowed-in arrowed-right">第五步
							查看结果</strong></td>
				</tr>
				<tr>
					<td align="center">投票结束，共查看相关统计数据。 <a href="javascript:report()">查看统计结果数据</a></td>
				</tr>
			</tbody>
		</table>


	</div>








	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/evTask/preview.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<style type="text/css">
a {
	color: blue;
	text-decoration: none;
}

a:link {
	color: blue;
	text-decoration: none;
}

a:visited {
	color: blue;
	text-decoration: none;
}

a:hover {
	color: #ff2020;
	text-decoration: underline;
}

a:actived {
	color: #2c850d;
	text-decoration: none;
}
</style>
</body>
</html>
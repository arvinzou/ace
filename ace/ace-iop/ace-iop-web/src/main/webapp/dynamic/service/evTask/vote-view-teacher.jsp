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
<%@page import="org.platform.snail.ev.dao.*"%>
<%@page import="org.platform.snail.ev.vo.*"%>
<%@ page import="java.util.*"%>
<%
	javax.servlet.ServletContext servletContext = request.getSession()
			.getServletContext();
	org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils
			.getRequiredWebApplicationContext(servletContext);

	EvTaskMapper evTaskMapper = (EvTaskMapper) webApplicationContext
			.getBean("evTaskMapper");
	EvTargetMapper evTargetMapper = (EvTargetMapper) webApplicationContext
			.getBean("evTargetMapper");
	EvScoreTemleteSubMapper evScoreTemleteSubMapper = (EvScoreTemleteSubMapper) webApplicationContext
			.getBean("evScoreTemleteSubMapper");
	//EvTaskVo evTaskVo=evTaskMapper.selectByPrimaryKey(evTaskId);
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


<body style="font-size: 14px">
	<form id="fm-evTask" action="vote-teacher.jsp">
		<div class="page-content">
			<div style="text-align: center;background:#CCFFFF">
				<h1>教师评测</h1>
			</div>

			<fieldset>
				<legend>
					<strong class="label label-lg label-info arrowed-in arrowed-right">第一步
						身份选择</strong>
				</legend>
				<div>
					评测学校

						<input name="deptId" id="departmentId" class="easyui-combotree"
							data-options="url:'${sessionScope.portalContextPath}/system/selectDepartmentTreeList.do?id=01',method:'get',animate: true,
                lines:false,"
							style='width: 200px; height: 20px; line-height: 20px;'>

					</div>
				
				<div >
					所属年级
						<input class="easyui-combobox"
							style="width: 200px; height: 20px; line-height: 20px;"
							name="gradeId" id="gradeId"
							data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=14&deptId=${SystemUser.users.departmentId}',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">
				

				</div>
				<div >
					任教班级
						<input class="easyui-combobox"
							style="width: 200px; height: 20px; line-height: 20px;"
							id="classesTaught" name="classesId"
							data-options="
					multiple:false,
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=15&gradeId=1&deptId=${SystemUser.users.departmentId}',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">
					<input type="hidden" name="evTaskId" value="${evTaskId}">

				</div>
			</fieldset>
			<button class="btn btn-info btn-block" type="submit"
				authority="false">一下步</button>
		</div>



	</form>




	<jsp:include page="../../common/footer-1.jsp" />


<script>
jQuery(function($) {
	$('#gradeId').combobox({
		onChange: function(newValue,oldValue){
			var deptId=$('#departmentId').combotree('getValue'); 
			var url=portalContextPath+'/dict/findListByCategoryId.do?categoryId=15&gradeId='+newValue+'&deptId='+deptId;
			$('#classesTaught').combobox('reload',url);  // reload list data using new URL
		}
	});
	$('#departmentId').combotree({
		onChange: function(newValue,oldValue){ 
			var url=portalContextPath+'/dict/findListByCategoryId.do?categoryId=14&deptId='+newValue;
			//alert(url);
			$('#gradeId').combobox('reload',url);  // reload list data using new URL
			
			var deptId=$('#departmentId').combotree('getValue'); 
			var gradeId=$('#gradeId').combobox('getValue');
			var url=portalContextPath+'/dict/findListByCategoryId.do?categoryId=15&gradeId='+gradeId+'&deptId='+deptId;
			$('#classesTaught').combobox('reload',url); 
		}
	});
	setTimeout(init,1000); 
});
function init(){
	var deptId='${SystemUser.users.departmentId}'; 
	var gradeId=$('#gradeId').combobox('getValue');
	var url=portalContextPath+'/dict/findListByCategoryId.do?categoryId=15&gradeId='+gradeId+'&deptId='+deptId;
	$('#classesTaught').combobox('reload',url);
	$('#departmentId').combotree('setValue','${SystemUser.users.departmentId}');
	
}
</script>
	<jsp:include page="../../common/footer-2.jsp" />
	<style type="text/css">
.layout-user {
	width: 150px;
	height: 25px;
	float: left;
	margin: 2px 2px 2px;
}

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

.datalist {
	border: 1px solid #FCFCFC; /* 表格边框 */
	font-family: Arial;
	border-collapse: collapse; /* 边框重叠 */
	background-color: #ffffff; /* 表格背景色 */
}

.datalist th {
	border: 1px solid #000000; /* 行名称边框 */
	background-color: #ffffff; /* 行名称背景色 */
	color: #000000; /* 行名称颜色 */
	font-weight: bold;
	padding-top: 4px;
	padding-bottom: 4px;
	padding-left: 12px;
	padding-right: 12px;
	text-align: left;
}

.datalist td {
	border: 1px solid #FCFCFC; /* 单元格边框 */
	text-align: left;
	padding-top: 4px;
	padding-bottom: 4px;
	padding-left: 10px;
	padding-right: 10px;
}
</style>
</body>
</html>
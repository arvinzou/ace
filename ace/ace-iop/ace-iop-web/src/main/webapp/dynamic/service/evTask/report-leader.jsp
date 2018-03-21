<%@page import="java.math.BigDecimal"%>
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
	
	List<Map<String,Object>> list=evTaskMapper.selectTaskTjById(evTaskId);
	
	EvTaskUsersMapper evTaskUsersMapper = (EvTaskUsersMapper) webApplicationContext
			.getBean("evTaskUsersMapper");
	EvTaskUsersQVo condition=new EvTaskUsersQVo();
	condition.setEvTaskId(evTaskId);
	condition.setStatus("2");
	List<EvTaskUsersVo> listUsers=evTaskUsersMapper.findList(condition, 0, 999999, null);

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

	<div class="page-content">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<td align="center"><strong>结果统计</strong> </td>
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
								<td><span class='badge badge-info' id="evDeadline"></span></td>
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

			</tbody>
		</table>
<div style="text-align:right">
			
			
											<a href="excel-leader.jsp?evTaskId=${evTaskId}" target="_blank">导出Excel</a>
			
			
			</div>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<td>序号</td>
					<td>指标名称</td>
					<td>总分</td>
				
					<td>A类投票数</td>
					<td>B类投票数</td>
					<td>C类投票数</td>
					<td>D类投票数</td>
				</tr>
			</thead>


			<tbody>
			<%
			int i=1;
			BigDecimal score=new BigDecimal(0);
			BigDecimal scorea=new BigDecimal(0);
			BigDecimal scoreb=new BigDecimal(0);
			BigDecimal scorec=new BigDecimal(0);
			BigDecimal scored=new BigDecimal(0);
			for(Map<String,Object> o:list){
			
				score=score.add((BigDecimal)o.get("score"));
				scorea=scorea.add((BigDecimal)o.get("A"));
				scoreb=scoreb.add((BigDecimal)o.get("B"));
				scorec=scorec.add((BigDecimal)o.get("C"));
				scored=scored.add((BigDecimal)o.get("D"));
				
			%>
				<tr>
					<td><%=i %></td>
					<td><%=o.get("name") %></td>
					<td><%=o.get("score") %></td>
				
					<td><%=o.get("A") %></td>
					<td><%=o.get("B") %></td>
					<td><%=o.get("C") %></td>
					<td><%=o.get("D") %></td>
				</tr>
<% 
i++;

			}%>
			<%
			BigDecimal av=score.divide(new BigDecimal(listUsers.size()),0,BigDecimal.ROUND_HALF_EVEN);
			%>
			<tr>
					<td></td>
					<td align="right">统计</td>
					
					<td>总分<%=score %> 均分<%=av%></td>
					
					<td><%=scorea %></td>
					<td><%=scoreb %></td>
					<td><%=scorec %></td>
					<td><%=scored %></td>
				</tr>
			</tbody>
		</table>
		
				<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
				<td>
					序号
					</td>
					<td>
					姓名
					</td>
					<td>
					身份
					</td>
					<td>
					建议
					</td>
				</tr>
				
			</thead>
			<tbody>
<%
			int j=1;

			for(EvTaskUsersVo o:listUsers){

				if(o.getAdvise()!=null){
			%>
				<tr>
				<td><%=j %></td>
					<td><%=o.getUserName() %></td>
					<td><%=o.getGradeName()==null?"":o.getGradeName() %></td>
					<td><%=o.getAdvise()%></td>
					
				</tr>
<% 
j++;}

			}%>
			</tbody>
		</table>
	</div>








	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/evTask/preview.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<style type="text/css">
.layout-user {
	width: 130px;
	height: 40px;
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
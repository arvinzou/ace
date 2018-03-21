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
	
	
	List<Map<String,Object>> list=evTaskMapper.selectTaskTjTeacherById(evTaskId);




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
			
			
											<a href="excel-teacher.jsp?evTaskId=${evTaskId}" target="_blank">导出Excel</a>
			
			
			</div>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<td>项目
					</td>


					<td>很满意</td>
					<td>满意</td>
					<td>基本满意</td>
					<td>不满意</td>

					<td>体罚或变相体罚学生，歧视侮辱学生，或有其他侵犯学生合法权益</td>
					<td>举办或参与校外办学机构，组织或参与有偿教育、有偿招生</td>
					<td>组织或参与向学生推销学习资料和用品，违规乱收费，或向学生家长索要钱、物、有价值证券</td>
					<td>专业基础薄弱，工作中经常出现错误，教学能力差</td>
					<td>德育工作差，指教书不育人，班级管理，课堂管理混乱</td>
					<td>在学校公共场所吸烟或酒后上课，上课时带手机通讯设备进课堂打电话，玩微信，工作时间打牌、炒股、玩游戏</td>
					<td>衣着仪表不端庄，校园内穿拖鞋，男教师穿背心和西短裤，女教师穿吊带衫和超短裙	</td>
<td>
					建议
					</td>
				</tr>
				
			</thead>
			<tbody>
<%
			int i=1;
			for(Map<String,Object> o:list){
			     List<Map<String,Object>> list3=  evTaskMapper.selectFeedbackById(evTaskId,(String)o.get("user_id"));
			%>
				<tr>
					<td><%=o.get("name") %></td>


					<td><%=o.get("a") %></td>
					<td><%=o.get("b") %></td>
					<td><%=o.get("c") %></td>
					<td><%=o.get("d") %></td>
					<td><%=o.get("e") %></td>
					<td><%=o.get("f") %></td>
					<td><%=o.get("g") %></td>
					<td><%=o.get("h") %></td>
					<td><%=o.get("i") %></td>
					<td><%=o.get("j") %></td>
					<td><%=o.get("k") %></td>
					<td>

					 <%
                    					    int l=1;
                    					    for(Map<String,Object> p:list3){
                    					    %>
                    					       <div> <%=l%>.<%=(String)p.get("feedback")%>(<%=p.get("c")%>)</div>
                    					    <%
                    					    l++;
                    					    }
                    					    %>

					</td>

				</tr>
<% 
i++;

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
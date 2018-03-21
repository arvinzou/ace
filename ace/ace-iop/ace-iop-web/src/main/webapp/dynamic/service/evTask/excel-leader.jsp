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
	response.setHeader("Content-disposition","attachment; filename=report.xls");

%>



<body style="font-size: 14px">


		<table border="1">
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
		
				<table border="1">
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
	








</body>
</html>
<%@page import="java.math.BigDecimal"%>
<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="application/vnd.ms-excel; charset=utf-8"
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



	response.setHeader("Content-disposition","attachment; filename=report.xls");
%>




<body>


<table border="2">
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





</body>
</html>
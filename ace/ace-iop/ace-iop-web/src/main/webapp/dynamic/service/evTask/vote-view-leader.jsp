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
	EvTaskVo evTaskVo=evTaskMapper.selectByPrimaryKey(evTaskId);
	EvTargetQVo c1=new EvTargetQVo();
	c1.setEvTempleteId(evTaskVo.getEvTempleteId());
	List<EvTargetVo> list=evTargetMapper.findList(c1, 0, 99999, null);

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


<body style="font-size:14px;">
<form id="fm-evTask">
	<div class="page-content">
		<div style="text-align: center;background:#CCFFFF">
			<h1>领导班子评测</h1>
		</div>
		<div style="background:#FFCCCC">
			事务名称：<span id="evTaskName"></span> 评测对象：<span id="evObjName"></span>
			请您务必在时间: <span id="evDeadline" class='badge badge-info'></span>之前完成以下
			5 个步骤的操作，逾期将作弃权操作处理：
		</div>
		<fieldset>
			<legend>
				<strong class="label label-lg label-info arrowed-in arrowed-right">第一步
					准备工作</strong>
			</legend>
			<div id="evContent" style="text-align: left;background:#FFFFCC" ></div>
		</fieldset>
		<fieldset>
			<legend>
				<strong class="label label-lg label-info arrowed-in arrowed-right">第二步
					身份选择</strong>
			</legend>
			<div style="background:#FFCCCC" >
				<table style="width: 100%" >
					
					<tr id="trgrade">

						<td onmouseover="this.style.backgroundColor='blue';this.style.color='#FFFFFF'" onmouseout="this.style.backgroundColor='';;this.style.color='#000000'">
						
						<input name="grade" type="radio" value="1"
							onclick="javascript:setOpeter(this)" />班子成员</td>

						<td onmouseover="this.style.backgroundColor='blue';this.style.color='#FFFFFF'" onmouseout="this.style.backgroundColor='';;this.style.color='#000000'"><input name="grade" type="radio" value="2"
							onclick="javascript:setOpeter(this)" />中层骨干</td>

						<td onmouseover="this.style.backgroundColor='blue';this.style.color='#FFFFFF'" onmouseout="this.style.backgroundColor='';;this.style.color='#000000'"><input name="grade" type="radio" value="3"
							onclick="javascript:setOpeter(this)" />一般教师</td>

						<td onmouseover="this.style.backgroundColor='blue';this.style.color='#FFFFFF'" onmouseout="this.style.backgroundColor='';;this.style.color='#000000'"><input name="grade" type="radio" value="4"
							onclick="javascript:setOpeter(this)" />离退休教师</td>

						
					</tr>
				</table>


			</div>
		</fieldset>
		<fieldset>
			<legend>
				<strong class="label label-lg label-info arrowed-in arrowed-right">第三步
					评测</strong>
			</legend>
			<div style="background:#FFCCCC" >
				1、本测评表每个评价指标有多个等次，请移动鼠标选中符合自己看法的相应评价等次，如“<img src="select.gif"
					width="14" height="14" border="0" />”所示。每个指标选项都必须填写，如有遗漏，系统自动提示。<br />
				2、测评表填好后，点击下面的“提交”按钮完成投票，投票结果一经确认，不可更改。
			</div>
			<div id="vo-content" style="text-align:center;background:#FFCCCC">
			<%
			int i=1;
			StringBuffer html=new StringBuffer();
			List<EvScoreTemleteSubVo> listSub=new ArrayList<EvScoreTemleteSubVo>();
			html.append("<table border='0' align='center'>");
			for(EvTargetVo o:list){
				EvScoreTemleteSubQVo c2=new EvScoreTemleteSubQVo();
				c2.setScoreTempleId(o.getCategory());
				listSub=evScoreTemleteSubMapper.findList(c2, 0, 9999, null);
				html.append("<tr><td align='left'>");
				html.append(String.valueOf(i));
				html.append("、");
				html.append(o.getEvTargetName());
				html.append("</td></tr>");
				//html.append("<div>");
				int j=1;
				html.append("<tr><td onmouseover=\"this.style.backgroundColor='green';this.style.color='#FFFFFF'\" onmouseout=\"this.style.backgroundColor='';;this.style.color='#000000'\">");
				html.append("<input name='N"+o.getEvTargetId()+"' type='hidden' value='"+i+"'>");
				for(EvScoreTemleteSubVo e:listSub){
					html.append("<div class='layout-user' onmouseover=\"this.style.backgroundColor='blue';this.style.color='#FFFFFF'\" onmouseout=\"this.style.backgroundColor='';;this.style.color='#000000'\">");
					
					html.append("<input name='M"+o.getEvTargetId()+"' type='radio' id='radio' onclick='styleRadio()' value='"+e.getId()+"'>&nbsp;&nbsp;");
					html.append(e.getSkey());
					html.append(".");
					html.append(e.getName());
					html.append("</div>");
					j++;
				}
				html.append("</td></tr>");
				i++;
				
			}
			html.append("</table>");
			out.println(html.toString());
			%>
			</div>
		</fieldset>
<fieldset>
			<legend>
				<strong class="label label-lg label-info arrowed-in arrowed-right">第四步
					建议</strong>
			</legend>
			
			<table border="0" width="100%">
			<tr>
			<td>除上述各项，您对领导班子还有哪些意见和建议?</td>
			</tr>
			<tr>
						
						<td ><textarea name="advise" rows="6"
								style="width:100%">我的建议：</textarea></td>
					</tr>
					<tr>
						<td><input type="hidden" name="evTaskId"
							value="${evTaskId }">
							

							<button class="btn btn-info btn-block" id="btn-evTask-submit"
								authority="false">提交</button></td>
					</tr>
			</table>
		</fieldset>
	</div>



</form>

<script type="text/javascript">
		function styleRadio() {
			var radios = document.getElementById("fm-evTask").radio;
			for (var i = 0; i < radios.length; i++) {
				if (radios[i].checked) {
					console.log("1000");
					radios[i].parentNode.style.backgroundColor = 'blue';
					radios[i].parentNode.style.color = '#FFFFFF';
				}
			}
		}
	</script>


	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/evTask/vote-view.js"></script>
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
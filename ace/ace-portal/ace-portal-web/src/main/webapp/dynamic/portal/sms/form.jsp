<%@page import="com.huacainfo.ace.common.tools.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>基本信息</title>
</head>
<script type="text/javascript">
	var id = '${param.id}';
</script>
<jsp:include page="../../common/common.jsp" />


<body>
	<div class="page-content">

		<form id="fm-notice">
			<div>
				<input type="hidden" name="id" value=""/>
				<input type="hidden" name="templateCode" id="templateCode" value=""/>
				手机号码:<input type="text" id="mobile" name="mobile" value=""/>
			</div>
			<br/>
			<div>
			 短信模板:<select id="template"></select>
			</div>
			<br/>
			<div>
			短信内容:<textarea id="content" rows="8" cols="70" name="content"></textarea>
			</div>
			<br/>
			<div>
				<button type="button" class="btn btn-info" id="btn-notice-submit"
					authority="false">
					<i class="ace-icon fa fa-check bigger-110"></i> 保存
				</button>
			</div>
		</form>

	</div>

	<!-- /section:elements.tab.option -->






	<jsp:include page="../../common/footer-1.jsp" />
 
	 
	<jsp:include page="../../common/footer-2.jsp" />
	<script>
	jQuery(function($) {
		 
		$.ajax({
			type : "get",
			url : contextPath + "/sms/getAllTemplate.do",
			async:false,
			data:{},
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(rst, textStatus) {
				 
				 for(i in rst.value)
					 { 
				 		$("#template").append("<option templateCode='"+rst.value[i].templateCode+"' value='"+rst.value[i].content+"'>"+rst.value[i].name+"</option>");
					 }
				 
				   $("#content").val($("#template").val());
					$("#templateCode").val($("#template :checked").attr("templateCode"));
			},
			complete : function(XMLHttpRequest, textStatus) {
				
			},
			error : function() {
			}
		});
		$("#template").on("change",function(){
			$("#content").val($("#template").val());
			$("#templateCode").val($("#template :checked").attr("templateCode"));
		});
		
		
		$("#btn-notice-submit").on("click",function(){
			 
			$.ajax({
				type : "get",
				url : contextPath + "/sms/insert.do",
				async:false,
				data: {mobile:$("#mobile").val(),content:$("#content").val(),sendStatus:"0",templateCode:$("#templateCode").val()} ,
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(rst, textStatus) {
					 if(rst.status=="0")
						 alert("添加成功!");
					 else
						 alert(rst.errorMessage);
				},
				complete : function(XMLHttpRequest, textStatus) {
					
				},
				error : function() {
				}
			});
		});
		
	});
	
	</script>

</body>
</html>
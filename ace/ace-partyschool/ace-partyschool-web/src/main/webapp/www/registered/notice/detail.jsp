<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>通知公告</title>
		<jsp:include page="../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/detail.css"/>
	</head>
	<body>
		<div class="index" id="detail">

		</div>

		<script id="detail-tpl" type="text/template">
			<div class="title">\${data.title}</div>
			<div class="status">
				<span class="publisher">\${data.publisher}</span>
				<span class="date">\${data.pushDate}</span>
			</div>
			<div class="content">
				\$\${data.content}
			</div>
			<div class="filelist-title">附件</div>
			<div class="fileList">
				{@each data.files as item, index}
				<div class="item">
					<div class="file-type">
						{@if item.type == 'doc' || item.type == 'docx'}
						<img src="img/icon_word.png" class="file-type-img" />
						{@else if item.type == 'xls'|| item.type == 'xlsx'}
						<img src="img/icon-excel.png" class="file-type-img" />
						{@else if item.type == 'ppt'|| item.type == 'pptx'}
						<img src="img/icon_ppt.png" class="file-type-img" />
						{@else if item.type == 'jpg'|| item.type == 'png'}
						<img src="img/icon-jpg.png" class="file-type-img" />
						{@else if item.type == 'pdf'}
						<img src="img/icon-pdf.png" class="file-type-img" />
						{@else if item.type == 'txt'}
						<img src="img/icon-text.png" class="file-type-img" />
						{@/if}
					</div>
					<div class="file-detail">
						<div class="file-title">\${item.fileName}</div>
						<div class="file-footer">
							<span>\${data.pushDate}</span>
							<a href="#" class="download" onclick="downloadFile('\${item.fileUrl}');">下载</a>
						</div>
					</div>
				</div>
				{@/each}
			</div>
	    </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/autoLogin.js"></script>
		<script type="text/javascript" src="js/detail.js"></script>
	</body>
</html>

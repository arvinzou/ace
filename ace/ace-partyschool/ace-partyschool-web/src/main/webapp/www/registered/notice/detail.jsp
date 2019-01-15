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
				<span class="date">2018-11-20 13:20:15</span>
			</div>
			<div class="content">
				\$\${data.content}
			</div>
			<div class="filelist-title">附件</div>
			<div class="fileList">
				<div class="item">
					<div class="file-type">
						{@if data.type == 'doc' || data.type == 'docx'}
						<img src="img/icon_word.png" class="file-type-img" />
						{@else if data.type == 'xls'|| data.type == 'xlsx'}
						<img src="img/icon-excel.png" class="file-type-img" />
						{@else if data.type == 'ppt'|| data.type == 'pptx'}
						<img src="img/icon_ppt.png" class="file-type-img" />
						{@else if data.type == 'jpg'|| data.type == 'png'}
						<img src="img/icon-jpg.png" class="file-type-img" />
						{@else if data.type == 'pdf'}
						<img src="img/icon-pdf.png" class="file-type-img" />
						{@else if data.type == 'txt'}
						<img src="img/icon-text.png" class="file-type-img" />
						{@/if}
					</div>
					<div class="file-detail">
						<div class="file-title">\${data.filename}</div>
						<div class="file-footer">
							<span>2018-11-20</span>
							<a href="\${data.fileUrl}" download="\${data.filename}" class="download">下载</a>
						</div>
					</div>
				</div>
			</div>
	    </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script type="text/javascript" src="js/detail.js"></script>
	</body>
</html>

<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>班级文件</title>
		<jsp:include page="../../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
	</head>
	<body>
		<div class="index">
			<div class="search">
				<input class="serach-input" type="text" id="search" onfocus="focusInput();" onblur="blurInput();"/>
				<img id="search-icon" src="img/icon_search.png" class="icon-search" onclick="searchIconClick();"/>
				<span id="search-title" class="search-title" onclick="searchIconClick();">搜索</span>
			</div>
			<div class="list">
				<div class="item">
					<div class="file-type">
						<img src="img/icon_ppt.png" class="file-type-img" />
					</div>
					<div class="file-detail">
						<div class="file-title">空想社会主义思想家对共产主义社会的向往—纪念托马斯·莫尔《乌托邦》发表哈哈哈哈哈哈哈哈哈</div>
						<div class="file-footer">
							<span>2018-11-20</span>
							<span>来自</span>
							<span>孟岩</span>
						</div>
					</div>
				</div>
				
				<div class="item">
					<div class="file-type">
						<img src="img/icon-excel.png" class="file-type-img" />
					</div>
					<div class="file-detail">
						<div class="file-title">省委党校干部教育学院常德学区人员名单</div>
						<div class="file-footer">
							<span>2018-11-20</span>
							<span>来自</span>
							<span>教务处</span>
						</div>
					</div>
				</div>
				
				<div class="item">
					<div class="file-type">
						<img src="img/icon-pdf.png" class="file-type-img" />
					</div>
					<div class="file-detail">
						<div class="file-title">省委党校干部教育学院常德学区人员名单</div>
						<div class="file-footer">
							<span>2018-11-20</span>
							<span>来自</span>
							<span>教务处</span>
						</div>
					</div>
				</div>
				
				<div class="item">
					<div class="file-type">
						<img src="img/icon-jpg.png" class="file-type-img" />
					</div>
					<div class="file-detail">
						<div class="file-title">省委党校干部教育学院常德学区人员名单</div>
						<div class="file-footer">
							<span>2018-11-20</span>
							<span>来自</span>
							<span>教务处</span>
						</div>
					</div>
				</div>
				
				<div class="item">
					<div class="file-type">
						<img src="img/icon-text.png" class="file-type-img" />
					</div>
					<div class="file-detail">
						<div class="file-title">省委党校干部教育学院常德学区人员名单</div>
						<div class="file-footer">
							<span>2018-11-20</span>
							<span>来自</span>
							<span>教务处</span>
						</div>
					</div>
				</div>
				<div class="item">
					<div class="file-type">
						<img src="img/icon-jpg.png" class="file-type-img" />
					</div>
					<div class="file-detail">
						<div class="file-title">省委党校干部教育学院常德学区人员名单</div>
						<div class="file-footer">
							<span>2018-11-20</span>
							<span>来自</span>
							<span>教务处</span>
						</div>
					</div>
				</div>
				
				<div class="item">
					<div class="file-type">
						<img src="img/icon-text.png" class="file-type-img" />
					</div>
					<div class="file-detail">
						<div class="file-title">省委党校干部教育学院常德学区人员名单</div>
						<div class="file-footer">
							<span>2018-11-20</span>
							<span>来自</span>
							<span>教务处</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<img src="img/icon-upload.png" class="upload"/>



		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
	</body>
</html>

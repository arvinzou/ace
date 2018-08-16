<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>课程目录</title>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript" src="../../common/js/loader.js"></script>
		<script type="text/javascript" src="js/act.js"></script>
	</head>

	<body>
		<div class="container" id="chapterList">

		</div>

	<script id="chapterTemp" type="text/template">
		{@each data as chapter, index}
		<div class="row chapter">
			<div class="row">
				<h3 class="title">\${chapter.name}</h3>
			</div>
			{@each chapter.sourceList as item , num}
			{@if item.name != '' && item.name != undefined}
			<div class="row">
				<div class="row">
					<div class="col-xs-2 col-md-2"><img src="img/icon-play.png" class="play" /></div>
					<div class="col-xs-10 col-md-10">
						<span class="number">\${parseInt(num)+1}</span>
						<span class="chapter_title">\${item.name}</span>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2 col-md-2"></div>
					<div class="col-xs-10 col-md-10">
						{@if item.free == '0'}
						<span class="try">试听</span>
						{@/if}
						<span class="duration">\${item.duration}分钟</span>
					</div>
				</div>
			</div>
			{@/if}
			{@/each}
		</div>
		{@/each}
	</script>
	</body>
</html>
<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>通讯录</title>
		<jsp:include page="../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/index.css" />
	</head>

	<body>
		<div class="index">
			<div class="box">
				<div class="search">
					<input class="serach-input" type="text" id="search" onfocus="focusInput();" onblur="blurInput();" oninput="initMailList();"/>
					<img id="search-icon" src="img/icon_search.png" class="icon-search" onclick="searchIconClick();" />
					<span id="search-title" class="search-title" onclick="searchIconClick();">搜索</span>
				</div>
			</div>
			<div class="box">
				<li class="list" id="treeList">
					<%--<div class="row rb">
						<div class="title01">中共常德市委党校全体教职工</div>
						<div class="option" onclick="dorpAndDown(this,'oneMenu')"><img src="img/icon_up.png" class="icon-up" name="up"/></div>
					</div>--%>

				</li>
			</div>
			<div class="box" id="otherList">

			</div>
		</div>

		<script id="tree-tpl" type="text/template">
			{@each data as item, index}
			<ul id="oneMenu">
				{@if item.href == "" || item.href == null}
				<div class="row rb">
					<div class="title02">
						{@if item.children}
						\${item.text}(\${item.children.length})
						{@else}
						\${item.text}(0)
						{@/if}
					</div>
					<div class="option" onclick="dorpAndDown(this,'secondMenu')"><img src="img/icon_up.png" class="icon-up" name="up"/></div>
				</div>
				<ul id="secondMenu" style="display: none;">
					{@each item.children as child, index01}
					<div class="row rb">
						<div class="item-left">
							<img src="img/default_header.png" class="header" />
							<span class="username">\${child.text}</span>
						</div>
						<div class="item-right">
							<span class="mobile">\${child.href}</span>
						</div>
						<div class="item-call">
							<a href="tel:\${child.href}"><img src="img/icon_phone.png"></a>
						</div>
					</div>
					{@/each}
				</ul>
				{@/if}
			</ul>
			{@/each}
		</script>

		<script id="other-tpl" type="text/template">
			<div class="title">未分组</div>
			{@each data as item, index}
			{@if item.href != "" && item.href != null}
			<div class="row rb">
				<div class="item-left">
					<img src="img/default_header.png" class="header" />
					<span class="username">\${item.text}</span>
				</div>
				<div class="item-right">
					<span class="mobile">\${item.href}</span>
				</div>
				<div class="item-call">
					<a href="tel:\${item.href}"><img src="img/icon_phone.png"></a>
				</div>
			</div>
			{@/if}
			{@/each}
		</script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
	</body>

</html>
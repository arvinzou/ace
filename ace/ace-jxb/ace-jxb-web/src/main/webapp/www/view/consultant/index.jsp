<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>咨询师列表</title>
		<link rel="stylesheet" type="text/css" href="css/base.css"/>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="js/act.js"></script>
		<script type="text/javascript" src="../../common/js/loader.js"></script>
	</head>

	<body>
		<div class="container">
			<div class="row banner">
				<img src="img/banner.png" style="width: 100%;height: 100%;"/>
			</div>
			<!--筛选-->
			<div class="row navbox">
				<div class="selectbox">
					<div class="selemediv">
						<div class="selemenu">擅长领域</div>
						<div class="citylist menuList1" id="menuList">
							<li>行为纠正</li>
							<li>亲子冲突</li>
							<li>厌学</li>
							<li>自闭症</li>
							<li>不合群</li>
							<li>敏感自卑</li>
							<li>阅读障碍</li>
							<li>孩子叛逆</li>
							<li>不爱学习</li>
							<li>早恋</li>
							<li>考试焦虑</li>
							<li>内向</li>
							<li>坏习惯</li>
							<li>沟通问题</li>
							<li>胆小懦弱</li>
							<li>不听话</li>
							<li>考前紧张</li>
							<li>做事拖拉</li>
							<li>网瘾</li>
							<li>自控力差</li>
						</div>
					</div>
					<div class="selemediv">
						<div class="selemenu">咨询方式</div>
						<div class="citylist menuList2">
							<li style="margin-left: 1rem;">电话咨询</li>
							<li>视频咨询</li>
							<li>面对面咨询</li>
						</div>
					</div>
					<div class="selemediv">
						<div class="selemenu">综合排序</div>
						<div class="citylist menuList3">
							<li style="margin-left: 1rem;">咨询最多</li>
							<li>价格最低</li>
							<li>价格最高</li>
						</div>
					</div>
				</div>
			</div>
			<div class="container" id="list">

			</div>
		</div>

		<script id="consultant" type="text/template">
			{@each data as item,index}
				<div class="row consulelist">
					<div class="col-xs-3 col-sm-3 imgbox">
					<img src="\${item.imagePhotoUrl}" />
					</div>
					<div class="col-xs-9 col-sm-9">
					<div class="row content_01">
					<div class="col-xs-6 col-sm-6 left">\${item.name}</div>
					<div class="col-xs-6 col-sm-6 right online" onclick="createOrder('\${item.id}');">
					<img src="img/online.png" />预约咨询
					</div>
					</div>
					<div class="row content_02" onclick="showInfo('\${item.id}');">
					<p>
					\${item.profile}
					</p>
				</div>
				<div class="row content_03">
					<span class="money">¥100-500</span>
					<span class="help_times">帮助过23次</span>
					</div>
					<div class="row content_04">
					<ul class="clabel">
						{@each item.tags.split(',') as tag,num}
						<li>\${tag}</li>
						{@/each}
					</ul>
					</div>
					</div>
					</div>
			{@/each}
		</script>
		<div hidden class="lode" style="position: absolute; top :0;width: 100%;height: 100%; z-index:200; background-color: rgba(60,60,60,0.8);"></div>
	</body>

</html>
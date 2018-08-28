<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>课程列表</title>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="../common/css/nav.css" />
		<script type="text/javascript" src="../../common/js/loader.js"></script>
		<script type="text/javascript" src="js/act.js"></script>
	</head>

	<body>
		<div class="mainContainer">
			<div class="row menu">
				<div class="col-xs-2 col-md-2" style="padding-right: 0;">
					<div class="selectbox">
						<div class="selemediv">
							<div class="selemenu ">
								<input style="display: none;" value="" id="ability"/>
								<input style="display: none;" value="" id="csinput"/>
								<input style="display: none;" value="" id="quinput"/>
								<span class="">筛选</span>
							</div>
							<div class="citylist2">
								<div class="xzk">
									<div class="leibie">针对能力</div>
									<ul class="shangquan">
										<li class="active">不限</li>
										<li data-value="00">学习方法</li>
										<li data-value="01">团队合作</li>
										<li data-value="02">沟通表达</li>
										<li data-value="03">自我认知</li>
										<li data-value="04">阅读习惯</li>
										<li data-value="05">情商培养</li>
										<li data-value="06">习惯养成</li>
										<li data-value="07">亲子沟通</li>
										<li data-value="08">心理教育</li>
										<li data-value="09">性格养成</li>
										<li data-value="10">品格教养</li>
										<li data-value="11">思维训练</li>
										<li data-value="13">入学焦虑</li>
										<li data-value="14">幼小衔接</li>
										<li data-value="15">其他</li>
									</ul>
								</div>
								<div class="xzk">
									<div class="leibie">课程类型</div>
									<ul class="chengshi">
										<li class="active">不限</li>
										<li>单节课程</li>
										<li>系列课程</li>
									</ul>
								</div>
								<div class="xzk">
									<div class="leibie">价格</div>
									<ul class="qu">
										<li class="active">不限</li>
										<li>免费</li>
										<li>付费</li>
                                        <%--<li>会员免费</li>--%>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-10 col-md-10"
					 style="background-color: #fff; padding-left: 0!important;padding-right: 0!important;">
					<div class="navigation">
						<div class="news-title">
							<ul class="news-module course_nav_ul clear">
								<li class="active" onclick="courseList();">全部</li>
								<li onclick="courseList('00');">幼儿</li>
								<li onclick="courseList('01');">小学</li>
								<li onclick="courseList('02');">初中</li>
								<li onclick="courseList('03');">高中</li>
							</ul>
							<div class="news-slider"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="course_List">
				<ul id="courseList">

				</ul>
			</div>
		</div>
	</body>


	<script id="courseTemp" type="text/template">
		{@each data as item}
		<li data-id="\${item.id}" onclick="courseDetail('\${item.id}');">
			<div class="left_div">
				<img src="\${item.cover}" alt="">
			</div>
			<div class="right_div">
				<p class="test_title">
					\${item.name}
				</p>
				<p class="test_remark">
					{@if item.objects == '00'}
					<span>幼儿</span>
					{@else if item.objects == '01'}
					<span>小学</span>
					{@else if item.objects == '02'}
					<span>初中</span>
					{@else if item.objects == '03'}
					<span>高中</span>
					{@/if}
					<span>·</span>
					{@if item.purport == '00'}
					<span>学习方法</span>
					{@else if item.purport == '01'}
					<span>团队合作</span>
					{@else if item.purport == '02'}
					<span>沟通表达</span>
					{@else if item.purport == '03'}
					<span>自我认知</span>
					{@else if item.purport == '04'}
					<span>阅读习惯</span>
					{@else if item.purport == '05'}
					<span>情商培养</span>
					{@else if item.purport == '06'}
					<span>习惯养成</span>
					{@else if item.purport == '07'}
					<span>亲子沟通</span>
					{@else if item.purport == '08'}
					<span>心理教育</span>
					{@else if item.purport == '09'}
					<span>性格养成</span>
					{@else if item.purport == '10'}
					<span>品格教养</span>
					{@else if item.purport == '11'}
					<span>思维训练</span>
					{@else if item.purport == '13'}
					<span>入学焦虑</span>
					{@else if item.purport == '14'}
					<span>幼小衔接</span>
					{@else if item.purport == '15'}
					<span>其他</span>
					{@/if}
				</p>

				<span class="price">
                    {@if item.cost==0||item.costType == '0'}
                    <span class="free">免费</span>
                    {@else}
                    <span class="no_free">
                        <span class="now_price">￥\${item.cost}</span>
                        <span class="old_price">￥\${item.primeCost}</span>
                    </span>
                    {@/if}
            	</span>
			</div>
		</li>
		{@/each}
	</script>
</html>
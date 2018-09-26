<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>心阳光联盟</title>
		<script type="text/javascript" src="../common/js/init-rem.js"></script>
		<script type="text/javascript">
			(function() {
				var html = document.documentElement;
				var htmlw = html.getBoundingClientRect().width;
				html.style.fontSize = htmlw / 10 + 'px'
			})()
		</script>
		<link rel="stylesheet" type="text/css" href="css/swiper.min.css" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
        <%
            session.setAttribute("portalPath", "/portal");
        %>
        <script type="text/javascript">
            var contextPath = '${pageContext.request.contextPath}';
            var portalPath = '${portalPath}';
            var version = '${cfg.version}';
            var fastdfs_server = '${cfg.fastdfs_server}';
            var activeSyId = '${SESSION_USERPROP_KEY.activeSyId}';
            var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';
        </script>
	</head>

	<body>
		<div class="content">
			<div class="search">
				<div class="container">
					<div class="search_box" id="activeSearch">
						输入课程名称
					</div>
					<div class="notice">
						<i class="iconfont">&#xe702;</i>
					</div>
				</div>
			</div>
			<div class="panel">
				<div class="container">
					<div class="swiper-container swiper-container_panel">
						<div class="swiper-wrapper" id="banner">

						</div>
						<div class="swiper-pagination"></div>
					</div>
				</div>
			</div>
			<div class="menu">
				<div class="container">
					<div class="menu-item" onclick="consultClick();">
						<img src="img/icon-consult.png" class="menu-icon" />
						<p class="menu-title">顾问在线</p>
					</div>
					<div class="menu-item" onclick="courseClick();">
						<img src="img/icon-course.png" class="menu-icon" />
						<p class="menu-title">心理课程</p>
					</div>
					<div class="menu-item" onclick="liveClick();">
						<img src="img/icon-live.png" class="menu-icon" />
						<p class="menu-title">在线直播</p>
					</div>
					<div class="menu-item" onclick="testClick();">
						<img src="img/icon-test.png" class="menu-icon" />
						<p class="menu-title">心理评测</p>
					</div>
				</div>
			</div>
		</div>

		<!--限时免费开始-->
		<div class="content">
			<div class="free-course">
				<div class="container">
					<div class="course-title">限时免费</div>
					<div class="free-courselist">
						<div class="swiper-container swiper-container_course">
							<div class="swiper-wrapper" id="freeCourse">

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--限时免费结束-->

		<!--精品课程开始-->
		<div class="content">
			<div class="quality-course">
				<div class="container">
					<div class="course-title">精品课程</div>
					<div class="quality-courselist" id="qualityCourse">

					</div>
					<div class="quality-course-footer" onclick="qualityCourse();">
						<span class="batch"><img src="img/batch.png"/></span>
						<span class="change-batch">换一批</span>
					</div>
				</div>
			</div>
		</div>
		<!--精品课程结束-->

		<!--人气老师开始-->
		<div class="content" style="margin-bottom: 1.5rem;">
			<div class="teacher">
				<div class="container">
					<div class="course-title">人气老师</div>
					<div class="teacherlist">
						<div class="swiper-container swiper-container_teacher">
							<div class="swiper-wrapper" id="hotTeachers">

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--人气老师结束-->
		
		<!--底部开始-->
		<div class="footer">
			<div class="menu_footer">
				<div class="menu_footer_box" style="margin: 0 auto;" onclick="index();"><img src="img/icon-index-active.png" class="footer-icon"/><span class="footer-title">心阳光联盟</span></div>
			</div>
			<div class="menu_footer">
				<div class="menu_footer_box" style="margin: 0 auto;" onclick="mine();"><img src="img/icon-mine-active.png" class="footer-icon"/><span class="footer-title">个人中心</span></div>
			</div>
		</div>

		<div class="search_list">
			<div class="search">
				<div class="container">
					<div class="search_box">
						<input type="search" name="" id="search_input" value="输入课程名称" onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
							   onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color:rgba(196,200,214,1);" />
					</div>
					<div class="notice">
						<i class="iconfont">&#xe67c;</i>
					</div>
				</div>
			</div>
			<div class="test_list search_test_list">
				<ul id="testListss">
				</ul>
			</div>
		</div>
		<!--底部结束-->

        <%--人气老师模板--%>
        <script id="teacher-tpl" type="text/template">
            {@each data as item, index}
            <div class="swiper-slide" onclick="loadCounselor('\${item.id}');">
                <div class="teacher-box">
                    <div class="teacher-cover">
                        <img src="\${item.headimgurl}" class="cover-img" />
                    </div>
                    <div class="teacher-info"><span class="teacher-name">\${item.name}</span></div>
                    <div class="teacher-info"><span class="teacher-certification">\${item.certification}</span></div>
                    <div style="padding-top: 0.4rem;padding-bottom: 0.4rem;">
                        <a href="javascript:void(0);" class="consult" onclick="loadCounselor('\${item.id}');">咨询TA</a>
                    </div>
                </div>
            </div>
            {@/each}
        </script>

        <%--限时免费模板--%>
        <script id="free-tpl" type="text/template">
            {@each data as item, index}
            <div class="swiper-slide" onclick="courseDetail('\${item.id}');">
                <div class="course-cover"><img src="\${item.cover}" style="width: 100%;height: 100%;object-fit: cover;border-radius: 0.133333rem;"/></div>
                <div class="course-info">
                    <span class="course-info-title">\${item.name}</span>
                </div>
                <div class="course-info">
                    <span class="free">免费</span>
                    {@if item.primeCost != undefined && item.primeCost !=null && item.primeCost!=''}
                    <span class="prime">¥\${item.primeCost}</span>
                    {@/if}
                </div>
            </div>
            {@/each}
        </script>

        <%--精品课程模板--%>
        <script id="quality-tpl" type="text/template">
            {@each data as item, index}
            {@if index % 2 == 0}
            <div class="quality-course-item box_margin" onclick="courseDetail('\${item.id}');">
                <div class="quality-course-cover"><img src="\${item.cover}" style="width: 100%;height: 100%;object-fit: cover;border-radius: 0.133333rem;"/></div>
                <div class="course-info"><span class="course-info-title">\${item.name}</span></div>
                <div class="course-info">
								<span>
									{@each item.consumerList.list as item01, index01}
									{@if index01 == 0}
									<img src="\${item01.consumerImgUrl}" class="head-cover"/>
									{@else}
									<img src="\${item01.consumerImgUrl}" class="head-cover stack"/>
									{@/if}
									{@/each}
								</span>
                    <span><img src="img/icon-more.png" class="more"/></span>
                    <span class="user-nums">\${item.consumerList.total}人在听</span>
                </div>
            </div>
            {@else}
            <div class="quality-course-item" onclick="courseDetail('\${item.id}');">
                <div class="quality-course-cover"><img src="\${item.cover}" style="width: 100%;height: 100%;object-fit: cover;border-radius: 0.133333rem;"/></div>
                <div class="course-info"><span class="course-info-title">\${item.name}</span></div>
                <div class="course-info">
								<span>
									{@each item.consumerList.list as item01, index01}
									{@if index01 == 0}
									<img src="\${item01.consumerImgUrl}" class="head-cover"/>
									{@else}
									<img src="\${item01.consumerImgUrl}" class="head-cover stack"/>
									{@/if}
									{@/each}
								</span>
                    <span><img src="img/icon-more.png" class="more"/></span>
                    <span class="user-nums">\${item.consumerList.total}人在听</span>
                </div>
            </div>
            {@/if}
            {@/each}
        </script>

		<script id="banner-tpl" type="text/template">
			{@each data.rows as item, index}
			<div class="swiper-slide">
				<div class="swiper-slide"><img src="\${item.imgUrl}" /></div>
			</div>
			{@/each}
		</script>

		<%--搜索列表--%>
		<script id="temp_testLists" type="text/template">
			{@each data as item}
			<li data-id="\${item.id}" onclick="courseDetail('\${item.id}');">
				<div class="left_div">
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
                           {@if item.costType==0}
                            <span class="free">免费</span>
                            {@else}
                            <span class="no_free">
                                <span class="now_price">￥\${item.cost}</span>
                                <span class="old_price">￥\${item.primeCost}</span>
                            </span>
                            {@/if}
					</span>

				</div>
				<div class="right_div">
					<img src="\${item.cover}" alt="">
				</div>
			</li>
			{@/each}
		</script>

		<script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script type="text/javascript" src="js/swiper.min.js"></script>
		<script type="text/javascript" src="js/act.js"></script>
	</body>

</html>
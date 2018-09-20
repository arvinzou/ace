<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>近心帮首页</title>
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
					<div class="search_box">
						<input type="search" name="" id="search_input" value="输入咨询师姓名或关键字" style="color:rgba(196,200,214,1);" />
					</div>
					<div class="notice">
						<i class="iconfont">&#xe702;</i>
					</div>
				</div>
			</div>
			<div class="panel">
				<div class="container">
					<div class="swiper-container swiper-container_panel">
						<div class="swiper-wrapper">
							<div class="swiper-slide">
								<div class="swiper-slide"><img src="img/1.jpg" /></div>
							</div>
							<div class="swiper-slide">
								<div class="swiper-slide"><img src="img/2.jpg" /></div>
							</div>
							<div class="swiper-slide">
								<div class="swiper-slide"><img src="img/3.jpg" /></div>
							</div>
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
							<div class="swiper-wrapper">
								<div class="swiper-slide">
									<div class="course-cover"></div>
									<div class="course-info">
										<span class="course-info-title">建立亲子关系的规则感</span>
									</div>
									<div class="course-info">
										<span class="free">免费</span><span class="prime">¥9.99</span>
									</div>
								</div>
								<div class="swiper-slide">
									<div class="course-cover"></div>
									<div class="course-info">
										<span class="course-info-title">如何和孩子相处</span>
									</div>
									<div class="course-info">
										<span class="free">免费</span><span class="prime">¥9.99</span>
									</div>
								</div>
								<div class="swiper-slide">
									<div class="course-cover"></div>
									<div class="course-info">
										<span class="course-info-title">面对多动症</span>
									</div>
									<div class="course-info">
										<span class="free">免费</span><span class="prime">¥9.99</span>
									</div>
								</div>
								<div class="swiper-slide">
									<div class="course-cover"></div>
									<div class="course-info">
										<span class="course-info-title">孩子自觉学习很重要</span>
									</div>
									<div class="course-info">
										<span class="free">免费</span><span class="prime">¥9.99</span>
									</div>
								</div>
								<div class="swiper-slide">
									<div class="course-cover"></div>
									<div class="course-info">
										<span class="course-info-title">早睡早起是一种习惯</span>
									</div>
									<div class="course-info">
										<span class="free">免费</span><span class="prime">¥9.99</span>
									</div>
								</div>
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
					<div class="quality-courselist">
						<div class="quality-course-item box_margin">
							<div class="quality-course-cover"></div>
							<div class="course-info"><span class="course-info-title">如何提高孩子的创新能力</span></div>
							<div class="course-info">
								<span>
									<img src="img/head1.png" class="head-cover"/>
									<img src="img/head2.png" class="head-cover stack"/>
									<img src="img/head1.png" class="head-cover stack"/>
									<img src="img/head2.png" class="head-cover stack"/>
								</span>
								<span><img src="img/icon-more.png" class="more"/></span>
								<span class="user-nums">688人在听</span>
							</div>
						</div>
						<div class="quality-course-item">
							<div class="quality-course-cover"></div>
							<div class="course-info"><span class="course-info-title">教孩子从日常写作中写作</span></div>
							<div class="course-info">
								<span>
									<img src="img/head1.png" class="head-cover"/>
									<img src="img/head2.png" class="head-cover stack"/>
									<img src="img/head1.png" class="head-cover stack"/>
									<img src="img/head2.png" class="head-cover stack"/>
								</span>
								<span><img src="img/icon-more.png" class="more"/></span>
								<span class="user-nums">688人在听</span>
							</div>
						</div>
						<div class="quality-course-item box_margin">
							<div class="quality-course-cover"></div>
							<div class="course-info"><span class="course-info-title">童年缺失如何在成年后补救</span></div>
							<div class="course-info">
								<span>
									<img src="img/head1.png" class="head-cover"/>
									<img src="img/head2.png" class="head-cover stack"/>
									<img src="img/head1.png" class="head-cover stack"/>
									<img src="img/head2.png" class="head-cover stack"/>
								</span>
								<span><img src="img/icon-more.png" class="more"/></span>
								<span class="user-nums">688人在听</span>
							</div>
						</div>
						<div class="quality-course-item">
							<div class="quality-course-cover"></div>
							<div class="course-info"><span class="course-info-title">孩子不看书怎么办？</span></div>
							<div class="course-info">
								<span>
									<img src="img/head1.png" class="head-cover"/>
									<img src="img/head2.png" class="head-cover stack"/>
									<img src="img/head1.png" class="head-cover stack"/>
									<img src="img/head2.png" class="head-cover stack"/>
								</span>
								<span><img src="img/icon-more.png" class="more"/></span>
								<span class="user-nums">688人在听</span>
							</div>
						</div>
					</div>
					<div class="quality-course-footer">
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
				<div class="menu_footer_box" style="margin: 0 auto;"><img src="img/icon-index-active.png" class="footer-icon"/><span class="footer-title">心阳光联盟</span></div>
			</div>
			<div class="menu_footer">
				<div class="menu_footer_box" style="margin: 0 auto;"><img src="img/icon-mine-active.png" class="footer-icon"/><span class="footer-title">个人中心</span></div>
			</div>
		</div>
		<!--底部结束-->

        <%--人气老师模板--%>
        <script id="teacher-tpl" type="text/template">
            {@each data as item, index}
            <div class="swiper-slide">
                <div class="teacher-box">
                    <div class="teacher-cover">
                        <img src="img/head1.png" class="cover-img" />
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

		<script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script type="text/javascript" src="js/swiper.min.js"></script>
		<script type="text/javascript" src="js/act.js"></script>
	</body>

</html>
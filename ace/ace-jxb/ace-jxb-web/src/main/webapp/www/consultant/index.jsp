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
		<jsp:include page="../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="js/act.js"></script>
		<script type="text/javascript" src="../common/js/loader.js"></script>
	</head>

	<body>
		<div class="container">
			<div class="row banner">

			</div> 
			<div class="row navbox">
				<section class="job-module">
					<dl class="retrie">
						<dt>
							<div class="col-xs-4 col-sm-4 navtitle"><a id="area" href="javascript:;">擅长领域 </a></div>
							<div class="col-xs-4 col-sm-4 navtitle"><a id="wage" href="javascript:;">咨询方式</a></div>
							<div class="col-xs-4 col-sm-4 navtitle"><a id="sort" href="javascript:;">综合排序</a></div>
						</dt>
						<dd class="area">
							<ul class="slide downlist" id="field01">
								<li class="ll mt_01" style="margin-left: 0.4rem;" onclick="screen01(this);">
									<a href="#">不限</a>
								</li>
								<li class="ll mt_01" onclick="screen01(this);">
									<a href="#">厌学</a>
								</li>
								<li class="ll mt_01" onclick="screen01(this);">
									<a href="#">自闭</a>
								</li>
								<li class="mt_01" onclick="screen01(this);">
									<a href="#">多动症</a>
								</li>
								<li class="ll mt_02 mb" style="margin-left: 0.4rem;" onclick="screen01(this);">
									<a href="#">不合群</a>
								</li>
								<li class="ll mt_02 mb" onclick="screen01(this);">
									<a href="#">敏感自卑</a>
								</li>
								<li class="ll mt_02 mb" onclick="screen01(this);">
									<a href="#">不爱学习</a>
								</li>
								<li class="mt_02 mb" onclick="screen01(this);">
									<a href="#">自控力差</a>
								</li>
								<!--<div class="row screen">
									<div class="col-xs-6 col-sm-6 reset" id="reset01">重置</div>
									<div class="col-xs-6 col-sm-6 confirm" id="confirm01">确定</div>
								</div>-->
							</ul>

						</dd>
						<dd class="wage">
							<ul class="slide downlist"  id="field02">
								<li class="mt_01 mb" style="margin-right: 0.746666rem;margin-left: 0.746666rem;" onclick="screen02(this);">
									<a href="#">电话咨询</a>
								</li>
								<li class="mt_01 mb" style="margin-right: 0.746666rem;" onclick="screen02(this);">
									<a href="#">微信咨询</a>
								</li>
								<li class="mt_01 mb" onclick="screen02(this);">
									<a href="#">面对面咨询</a>
								</li>
								<!--<div class="row screen01">
									<div class="col-xs-6 col-sm-6 reset" id="reset02">重置</div>
									<div class="col-xs-6 col-sm-6 confirm" id="confirm02">确定</div>
								</div>-->
							</ul>
						</dd>
						<dd class="sort">
							<ul class="slide downlist"  id="field02">
								<li class="ll mt_01" style="margin-right: 0.746666rem;margin-left: 0.746666rem;" onclick="screen03(this);">
									<a href="#">综合排序</a>
								</li>
								<li class="ll mt_01" style="margin-right: 0.746666rem;" onclick="screen03(this);">
									<a href="#">咨询最多</a>
								</li>
								<li class="mt_01" onclick="screen03(this);">
									<a href="#">只查看在线</a>
								</li>
								<li class="ll mt_02 mb" style="margin-right: 0.746666rem;margin-left: 0.746666rem;" onclick="screen03(this);">
									<a href="#">价格降序</a>
								</li>
								<li class="mt_02 mb" onclick="screen03(this);">
									<a href="#">价格升序</a>
								</li>
								<!--<div class="row screen">
									<div class="col-xs-6 col-sm-6 reset" id="reset03">重置</div>
									<div class="col-xs-6 col-sm-6 confirm" id="confirm03">确定</div>
								</div>-->
							</ul>
						</dd>
					</dl>
				</section>
			</div>
			<div class="container">
				<div class="row consulelist">
					<div class="col-xs-3 col-sm-3 imgbox">
						<img src="img/headImg.png" />
					</div>
					<div class="col-xs-9 col-sm-9">
						<div class="row content_01">
							<div class="col-xs-6 col-sm-6 left">肖海平</div>
							<div class="col-xs-6 col-sm-6 right online" onclick="window.location.href='order.jsp'">
								<img src="img/online.png" />预约咨询
							</div>
						</div>
						<div class="row content_02" onclick="window.location.href='counselor.jsp'">
							<p>
								应用心理学学士，湖南省心理咨询鉴定中心考评员，近心帮技术总监，近心帮职业培训学校校长…
							</p>
						</div>
						<div class="row content_03">
							<span class="money">¥100-500</span>
							<span class="help_times">帮助过23次</span>
						</div>
						<div class="row content_04">
							<!--<span class="clabel">儿童发展</span>
							<span class="clabel">亲子关系</span>
							<span class="clabel">家庭关系</span>
							<span class="clabel">沟通问题</span>-->
							<ul class="clabel">
								<li>儿童发展</li>
								<li>亲子关系</li>
								<li>家庭关系</li>
								<li>沟通问题</li>
							</ul>
						</div>
					</div>
				</div>

				<div class="row consulelist"  onclick="window.location.href='counselor.jsp'">
					<div class="col-xs-3 col-sm-3 imgbox">
						<img src="img/headImg.png" />
					</div>
					<div class="col-xs-9 col-sm-9">
						<div class="row content_01">
							<div class="col-xs-6 col-sm-6 left">肖海平</div>
							<div class="col-xs-6 col-sm-6 right online">
								<img src="img/online.png" />预约咨询
							</div>
						</div>
						<div class="row content_02" onclick="window.location.href='order.jsp'">
							<p>
								应用心理学学士，湖南省心理咨询鉴定中心考评员，近心帮技术总监，近心帮职业培训学校校长…
							</p>
						</div>
						<div class="row content_03">
							<span class="money">¥100-500</span>
							<span class="help_times">帮助过23次</span>
						</div>
						<div class="row content_04">
							<!--<span class="clabel">儿童发展</span>
							<span class="clabel">亲子关系</span>
							<span class="clabel">家庭关系</span>
							<span class="clabel">沟通问题</span>-->
							<ul class="clabel">
								<li>儿童发展</li>
								<li>亲子关系</li>
								<li>家庭关系</li>
								<li>沟通问题</li>
							</ul>
						</div>
					</div>
				</div>

				<div class="row consulelist">
					<div class="col-xs-3 col-sm-3 imgbox">
						<img src="img/headImg.png" />
					</div>
					<div class="col-xs-9 col-sm-9">
						<div class="row content_01">
							<div class="col-xs-6 col-sm-6 left">肖海平</div>
							<div class="col-xs-6 col-sm-6 right offline">
								<img src="img/offline.png" />预约咨询
							</div>
						</div>
						<div class="row content_02" onclick="window.location.href='counselor.jsp'">
							<p>
								应用心理学学士，湖南省心理咨询鉴定中心考评员，近心帮技术总监，近心帮职业培训学校校长…
							</p>
						</div>
						<div class="row content_03">
							<span class="money">¥100-500</span>
							<span class="help_times">帮助过23次</span>
						</div>
						<div class="row content_04">
							<!--<span class="clabel">儿童发展</span>
							<span class="clabel">亲子关系</span>
							<span class="clabel">家庭关系</span>
							<span class="clabel">沟通问题</span>-->
							<ul class="clabel">
								<li>儿童发展</li>
								<li>亲子关系</li>
								<li>家庭关系</li>
								<li>沟通问题</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div hidden class="lode" style="position: absolute; top :0;width: 100%;height: 100%; z-index:200; background-color: rgba(60,60,60,0.8);"></div>
	</body>

</html>
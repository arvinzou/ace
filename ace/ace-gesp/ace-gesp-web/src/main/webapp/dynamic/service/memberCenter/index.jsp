<%@page import="com.huacainfo.ace.common.tools.CommonUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>memberCenter</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">
	var memberCode = '${SESSION_USERPROP_KEY.corpId}';
<%if (CommonUtils.isNotEmpty(request.getParameter("deptId"))) {%>
	var deptId = '${param.deptId}';
<%}%>
	
</script>
<body>
	<div class="page-content">
		<div class="col-xs-12 col-sm-3 center">
			<div>
				<!-- #section:pages/profile.picture -->
				<span class="profile-picture" class="hide"> <img id="avatar"
					class="editable img-responsive editable-click editable-empty"
					alt="Alex's Avatar" src="/portal/content/portal/images/qy.jpg"></img>
				</span>
				<!-- /section:pages/profile.picture -->
				<div class="space-4"></div>

				<div
					class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
					<div class="inline position-relative">
						<a href="#" class="user-title-label dropdown-toggle"
							data-toggle="dropdown"> <i
							class="ace-icon fa fa-circle light-green"></i> &nbsp; <span
							class="white" id="level"></span>
						</a>
						<%-- <ul
							class="align-left dropdown-menu dropdown-caret dropdown-lighter">
							<li class="dropdown-header">Change Status</li>

							<li><a href="#"> <i class="ace-icon fa fa-circle green"></i>
									&nbsp; <span class="green">Available</span>
							</a></li>

							<li><a href="#"> <i class="ace-icon fa fa-circle red"></i>
									&nbsp; <span class="red">Busy</span>
							</a></li>

							<li><a href="#"> <i class="ace-icon fa fa-circle grey"></i>
									&nbsp; <span class="grey">Invisible</span>
							</a></li>
						</ul> --%>
					</div>
				</div>
			</div>

			<div class="space-6"></div>

			<!-- #section:pages/profile.contact -->
			<div class="profile-contact-info">
				<div class="profile-contact-links align-left" id="look-info">
				</div>
				<div class="space-6"></div>

				<%-- <div class="profile-social-links align-center">
					<a href="#" class="tooltip-info" title=""
						data-original-title="Visit my Facebook"> <i
						class="middle ace-icon fa fa-facebook-square fa-2x blue"></i>
					</a> <a href="#" class="tooltip-info" title=""
						data-original-title="Visit my Twitter"> <i
						class="middle ace-icon fa fa-twitter-square fa-2x light-blue"></i>
					</a> <a href="#" class="tooltip-error" title=""
						data-original-title="Visit my Pinterest"> <i
						class="middle ace-icon fa fa-pinterest-square fa-2x red"></i>
					</a>
				</div> --%>
			</div>

			<!-- /section:pages/profile.contact -->
			<div class="hr hr12 dotted"></div>

			<!-- #section:custom/extra.grid -->
			<%-- <div class="clearfix">
				<div class="grid2">
					<span class="bigger-175 blue">25</span> <br> Followers
				</div>

				<div class="grid2">
					<span class="bigger-175 blue">12</span> <br> Following
				</div>
			</div> --%>
			
			<!-- /section:custom/extra.grid -->
			<div class="hr hr16 dotted"></div>
		</div>

		<div class="col-xs-12 col-sm-9">
			<%-- <div class="center">
				<span class="btn btn-app btn-sm btn-light no-hover"> <span
					class="line-height-1 bigger-170 blue"> 1,411 </span> <br> <span
					class="line-height-1 smaller-90"> Views </span>
				</span> <span class="btn btn-app btn-sm btn-yellow no-hover"> <span
					class="line-height-1 bigger-170"> 32 </span> <br> <span
					class="line-height-1 smaller-90"> Followers </span>
				</span> <span class="btn btn-app btn-sm btn-pink no-hover"> <span
					class="line-height-1 bigger-170"> 4 </span> <br> <span
					class="line-height-1 smaller-90"> Projects </span>
				</span> <span class="btn btn-app btn-sm btn-grey no-hover"> <span
					class="line-height-1 bigger-170"> 23 </span> <br> <span
					class="line-height-1 smaller-90"> Reviews </span>
				</span> <span class="btn btn-app btn-sm btn-success no-hover"> <span
					class="line-height-1 bigger-170"> 7 </span> <br> <span
					class="line-height-1 smaller-90"> Albums </span>
				</span> <span class="btn btn-app btn-sm btn-primary no-hover"> <span
					class="line-height-1 bigger-170"> 55 </span> <br> <span
					class="line-height-1 smaller-90"> Contacts </span>
				</span>
			</div> --%>

			<div class="space-12"></div>

			<jsp:include page="../include/deptInfo.jsp" />
			<div class="stamper">
				<span class="t1"></span>
			</div>
		</div>
	</div>
	<style>
		.stamper{padding-top:10px;height:10px;}
		.stamper span{float:left;display:inline-block;height:100%;width:100px;}
	</style>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${portalPath}/content/common/js/jquery.stamper.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberCenter/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberCenter/controller-deptInfo.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		window.onresize = function() {
			autoSize();
		}
		parent.onresize = function() {
			autoSize();
		}
		function autoSize() {
			parent.autoWidth();
		}
	</script>
</body>
</html>
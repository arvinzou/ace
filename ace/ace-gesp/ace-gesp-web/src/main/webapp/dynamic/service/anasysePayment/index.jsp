<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>pressPayment</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">
	
</script>
<body>
	<div class="page-content">
		<div class="widget-box" id="widget-box">
			<div class="widget-header">
				<h5 class="widget-title smaller">设置查询条件</h5>

				<div class="widget-toolbar"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-6">
					<form action="#" id="fm-search">
						
年度：<input
							class="easyui-combobox" style="width: 100px" name="year"
							data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=year',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">
                    
                    收费项目：<input
							class="easyui-combobox" style="width:130px" name="chargingItemId"
							data-options="
                    url:'${pageContext.request.contextPath}/chargingItem/selectListByDeptId.do?selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    editable:false,
                    panelHeight:'150'">
						<button class="btn btn-info" id="btn-search"
							authority="false">
							查询
							<i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">


					

					</div>
				</div>
			</div>
		</div>

		
	<div class="widget-box">
					<div class="widget-header">
						<h5 class="widget-title">会费统计</h5>

						<!-- #section:custom/widget-box.toolbar -->
						<div class="widget-toolbar">


							<a href="#" data-action="fullscreen" class="orange2"> <i
								class="ace-icon fa fa-expand"></i>
							</a> <a href="#" data-action="reload"> <i
								class="ace-icon fa fa-refresh"></i>
							</a> <a href="#" data-action="collapse"> <i
								class="ace-icon fa fa-chevron-up"></i>
							</a> <a href="#" data-action="close"> <i
								class="ace-icon fa fa-times"></i>
							</a>
						</div>

						<!-- /section:custom/widget-box.toolbar -->
					</div>

					<div class="widget-body">
						<div class="widget-main">
							<div id="ct1" class="charts-portal-ct1"></div>

						</div>
					</div>
				</div>

	</div>

	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${portalPath}/content/common/js/echarts-2.27/echarts.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/anasysePayment/model.js?version=${cfg.version}"></script>
		<script
		src="${pageContext.request.contextPath}/content/service/anasysePayment/view.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/anasysePayment/controller.js?version=${cfg.version}"></script>

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
			var h = window.innerHeight;
			var w = window.innerWidth;

			
			var charh = 300;
			var charw = parseInt($(".page-content").width())-20;
			charh = parseInt(charw * 0.3);
			$('.charts-portal-ct1').css("height", charh);
			$('.charts-portal-ct1').css("width", charw);
			if (myChart1) {
				myChart1.resize();
			}

		}
	</script>

</body>
</html>
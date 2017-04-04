<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<!-- basic scripts -->
		<!--[if lte IE 8]>
			<script type="text/javascript" src="/portal/content/common/assets/js/gz/jquery1x.min.js?version=${cfg.version}"></script>
		<![endif]-->
	    <script type="text/javascript">
			window.jQuery || document.write("<script src='/portal/content/common/assets/js/gz/jquery.min.js?version=${cfg.version}'>"+"<"+"/script>");
		</script>
<!-- build:css display/styles/displaymodel-style.css -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/content/display/jquery.qtip.min.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/content/display/displaymodel.css" />
	<script>
var modelId="15023";
</script>
<!-- endbuild -->

<!-- Configuration -->
<!--
         todo
         Remove this? shouldn't this be on the page already,
         and wouldn't it override settings form app-cfg-on-premise & others
         -->
<!--
        <script type="text/javascript" src="${pageContext.request.contextPath}/content/scripts/app-cfg.js?v=1"></script>
       -->
<!-- build:js display/scripts/displaymodel-logic.js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/display/jquery.qtip.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/display/raphael.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/display/bpmn-draw.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/display/bpmn-icons.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/display/Polyline.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/display/displaymodel.js"></script>



<link href="/activiti-app/styles/common/bootstrap.min.css" rel="stylesheet">
    <!-- endbuild -->

    <!-- build:css styles/style.css -->
    <link href="/activiti-app/styles/common/style.css" rel="stylesheet">
    <link href="/activiti-app/styles/common/style-retina.css" rel="stylesheet">
    <link href="//activiti-app/workflow/styles/style-workflow.css" rel="stylesheet">
    <link rel="stylesheet" href="/activiti-app/libs/angular-loading-bar-0.7.0/loading-bar.min.css" />
    <!-- endbuild -->
    
    <link rel="Stylesheet" href="/activiti-app/libs/ui-grid_3.0.0/ui-grid.css" type="text/css"/>
</head>
<body>
	<div class="model-preview-wrapper">
		<div id="bpmnModel" data-model-id="15023" data-model-type="runtime"
			style="width: 643px; height: 350px;">
			
		</div>
	</div>
	
	 <!--[if lt IE 9]>
    <script src="/activiti-app/libs/es5-shim-15.3.4.5/es5-shim.js"></script>
    <script src="/activiti-app/libs/json3_3.2.6/lib/json3.min.js"></script>
    <![endif]-->

    <!-- 3rd party libs -->
    <script src="/activiti-app/libs/jquery_1.11.0/jquery.min.js"></script>
    <script src="/activiti-app/libs/jquery-ui-1.10.3.custom.min.js"></script>

    <script src="/activiti-app/libs/angular_1.3.13/angular.min.js"></script>
    <script src="/activiti-app/libs/angular-animate_1.3.13/angular-animate.min.js"></script>
    <script src="/activiti-app/libs/bootstrap_3.1.1/js/bootstrap.min.js"></script>
    <script src="/activiti-app/libs/angular-resource_1.3.13/angular-resource.min.js"></script>
    <script src="/activiti-app/libs/angular-cookies_1.3.13/angular-cookies.min.js"></script>
    <script src="/activiti-app/libs/angular-sanitize_1.3.13/angular-sanitize.min.js"></script>
    <script src="/activiti-app/libs/angular-route_1.3.13/angular-route.min.js"></script>
    <script src="/activiti-app/libs/angular-translate_2.4.2/angular-translate.min.js"></script>
    <script src="/activiti-app/libs/angular-translate-storage-cookie/angular-translate-storage-cookie.js"></script>
    <script src="/activiti-app/libs/angular-translate-loader-static-files/angular-translate-loader-static-files.js"></script>
    <script src="/activiti-app/libs/angular-strap_2.1.6/angular-strap.min.js"></script>
    <script src="/activiti-app/libs/angular-strap_2.1.6/angular-strap.tpl.min.js"></script>
    <script src="/activiti-app/libs/angular-dragdrop_1.0.11/angular-dragdrop.min.js"></script>
    <script src="/activiti-app/libs/ng-file-upload/ng-file-upload-shim.min.js"></script>
    <script src="/activiti-app/libs/ng-file-upload/ng-file-upload.min.js"></script>
    <script src="/activiti-app/libs/momentjs_2.5.1/momentjs.min.js"></script>
    <script src="/activiti-app/libs/ui-grid_3.0.0/ui-grid.js" type="text/javascript"></script>
    <script src="/activiti-app/libs/angular-loading-bar-0.7.0/loading-bar.min.js"></script>
    <script src="/activiti-app/libs/angular-hotkeys_1.4.5/hotkeys--activiti-patch.js"></script>

    <!-- Configuration -->
    <script src="/activiti-app/scripts/app-cfg.js?v=2"></script>

    <!-- build:js scripts/scripts.js -->
    <script src="/activiti-app/workflow/scripts/workflow-app.js"></script>
    <script src="/activiti-app/workflow/scripts/services/comment-service.js"></script>
    <script src="/activiti-app/workflow/scripts/services/process-service.js"></script>
    <script src="/activiti-app/workflow/scripts/services/task-service.js"></script>
    <script src="/activiti-app/workflow/scripts/services/form-service.js"></script>
    <script src="/activiti-app/workflow/scripts/services/app-definition-service.js"></script>
    <script src="/activiti-app/workflow/scripts/controllers.js"></script>
    <script src="/activiti-app/workflow/scripts/controllers/document-preview.js"></script>
    <script src="/activiti-app/workflow/scripts/controllers/tasks.js"></script>
    <script src="/activiti-app/workflow/scripts/controllers/task.js"></script>
    <script src="/activiti-app/workflow/scripts/controllers/processes.js"></script>
    <script src="/activiti-app/workflow/scripts/controllers/process.js"></script>
    <script src="/activiti-app/workflow/scripts/controllers/render-form.js"></script>
    <script src="/activiti-app/workflow/scripts/controllers/start-form.js"></script>
    <script src="/activiti-app/workflow/scripts/controllers/start-process.js"></script>
    <script src="/activiti-app/workflow/scripts/workflow-directives.js"></script>

    <script src="/activiti-app/scripts/common/services/authentication-service.js"></script>
	<script src="/activiti-app/scripts/common/services/user-service.js"></script>
	<script src="/activiti-app/scripts/common/services/functional-group-service.js"></script>
	<script src="/activiti-app/scripts/common/services/related-content-service.js"></script>
    <script src="/activiti-app/scripts/common/services/runtime-app-definition-service.js"></script>
    <script src="/activiti-app/scripts/common/directives.js"></script>
    <script src="/activiti-app/scripts/common/providers-config.js"></script>
    <script src="/activiti-app/scripts/common/services/resource-service.js"></script>
    <script src="/activiti-app/scripts/common/services/recursion-helper.js"></script>

    <script src="/activiti-app/scripts/common/controllers/about.js"></script>
    <!-- endbuild -->

    <!-- Integration extensions -->
    <script src="/activiti-app/scripts/resource-loader.js?v=2" app="workflow"></script>
</body>
</html>
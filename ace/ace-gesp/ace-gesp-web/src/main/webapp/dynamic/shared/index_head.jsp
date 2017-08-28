<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
    <head>
        <meta charset="utf-8" />
        <title>${ Title}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <script type="text/javascript">
	    function RootPath() {
	    	return '${pageContext.request.contextPath}';
	    }
	    
	    function contextPath() {
	    	return '${pageContext.request.contextPath}';
	    }
	    </script>
        <!--BEGIN 全局样式 -->
        <link href="${pageContext.request.contextPath}/assets/global/plugins/font-awesome/css/font-awesome.min.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/simple-line-icons/simple-line-icons.min.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <!-- END 全局样式 -->
        <!-- BEGIN 页面插件样式 -->
        <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-table/bootstrap-table.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/icheck/skins/all.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/tree/tree.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/jqgrid/css/ui.jqgrid.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/jquery-ui/jquery-ui.min.css?v=${cfg.version}" rel="stylesheet" />
       <%--  <link href="${pageContext.request.contextPath}/assets/global/plugins/layui/css/layui.css?v=${cfg.version}" rel="stylesheet" /> --%>
        
        
        <!-- END 页面插件样式-->
        <!-- BEGIN 主题全局样式 -->
        <link href="${pageContext.request.contextPath}/assets/global/css/components.css?v=${cfg.version}" rel="stylesheet" id="style_components" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/css/plugins.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <!-- END 主题全局样式 -->
        <!-- BEGIN 页面布局样式 -->
        <link href="${pageContext.request.contextPath}/assets/layouts/layout2/css/layout.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <!-- END 页面布局样式 -->
        <!-- <link rel="shortcut icon" href="favicon.ico" /> -->
        <!-- BEGIN  自定义 -->
        <link href="${pageContext.request.contextPath}/assets/styles/ui-framework.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/styles/subindex.css?v=${cfg.version}" rel="stylesheet" type="text/css" />
    	<!-- END 自定义 -->
   
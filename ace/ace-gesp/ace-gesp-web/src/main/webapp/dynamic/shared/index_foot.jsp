<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 		<!-- END FOOTER -->
		<!--[if lt IE 9]>
		<script src="${pageContext.request.contextPath}/assets/global/plugins/respond.min.js?v=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/assets/global/plugins/excanvas.min.js?v=${cfg.version}"></script> 
		<![endif]-->
        <!-- BEGIN 核心插件脚本 -->
        <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/scripts/utils/utils.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/js.cookie.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.blockui.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/layer-v3.0.1/layer/layer.js?v=${cfg.version}" type="text/javascript"></script>
        <!-- END 核心插件脚本 -->
        <!-- BEGIN 页面级别脚本 -->
         <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery-ui/jquery-ui.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/moment.min.js?v=${cfg.version}" type="text/javascript"></script>
        
        <%-- <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js?v=${cfg.version}" type="text/javascript"></script> 
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js?v=${cfg.version}" type="text/javascript"></script> --%>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/laydate-v1.1/laydate/laydate.js?v=${cfg.version}" type="text/javascript"></script>
        
        <script src="${pageContext.request.contextPath}/assets/global/plugins/jqgrid/js/i18n/grid.locale-cn.js?v=${cfg.version}" type="text/javascript"></script>
  		<script src="${pageContext.request.contextPath}/assets/global/plugins/jqgrid/js/jquery.jqGrid.min.js?v=${cfg.version}" type="text/javascript"></script>
        
        <script src="${pageContext.request.contextPath}/assets/global/plugins/tree/tree.js?v=${cfg.version}" type="text/javascript"></script>
        
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-table/bootstrap-table.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-table/extensions/cookie/bootstrap-table-cookie.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-table/extensions/export/bootstrap-table-export.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="http://rawgit.com/hhurz/tableExport.jquery.plugin/master/tableExport.js?v=${cfg.version}" type="text/javascript"></script>
        
        <script src="${pageContext.request.contextPath}/assets/global/plugins/icheck/icheck.min.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/layout/jquery.layout.js?v=${cfg.version}"></script>
       
        <!-- END 页面级别脚本 -->
        <!-- BEGIN 全局皮肤脚本 -->
        <script src="${pageContext.request.contextPath}/assets/global/scripts/app.js?v=${cfg.version}" type="text/javascript"></script> 
        <script src="${pageContext.request.contextPath}/assets/scripts/ui-framework.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/scripts/utils/ui-componets.js?v=${cfg.version}" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/scripts/validator/ui-validator.js?v=${cfg.version}" type="text/javascript"></script>
        
        <!--END 自定义脚步 -->
         <script type="text/javascript">
         	 
            var param=UI.Page.getAllparam();//所有传递的get参数
         	$(document).ready(function () {
         		$.jgrid.defaults.styleUI="Bootstrap";
		    	//UI.Module.partialButton();//按钮
		    	UI.PublicResource.bindingDicUI();//字典绑定 dic-code="" dic-def="====全部===="
		    });
         	
         	/*===========================默认配置,有不同请重写==========================================*/
         	//查询form 的id="queryForm"
         	var pageConfig={ modulePath:UI.Page.modulePath(),jspPath:UI.Page.jspPath()};//页面配置
         	var gridConfig={heightSub:140,pk:"id"};//表格配置
        	var dialogConfig={width:800,height:600,title:""};//对话框配置 
		</script>
		<script type="text/javascript">
		/*==================================默认增删改方法,可按需要重写=================================*/
		
		/*搜索*/
	    function btn_Search() {
	    	UI.BootstrapTable.reload("gridTable");
	    }
	    /*
	   	 新增
	    */
	    function btn_add() {
	            var url = pageConfig.jspPath+"/form.jsp";
	            UI.Dialog.open(url, StringUtils.hashCode(url), "新增"+dialogConfig.title, dialogConfig.width,dialogConfig.height, function (iframe) {
	                iframe.AcceptClick();
	            });
	    }
	    /*
	   	 编辑
	    */
	    function btn_edit() {
	    	var KeyValue = UI.BootstrapTable.getRowValue("#gridTable", gridConfig.pk);
	        if (UI.Dialog.checkEdit(KeyValue)) {
	            var url = pageConfig.jspPath+"/form.jsp?KeyValue=" + KeyValue;
	            UI.Dialog.open(url, StringUtils.hashCode(url), "编辑"+dialogConfig.title, dialogConfig.width, dialogConfig.height, function (iframe) {
	            	iframe.AcceptClick();
	            });
	        }
	    }
	    /*
	   	 删除
	    */
	    function btn_delete() {
	        var KeyValue = UI.BootstrapTable.getRowValue("#gridTable", gridConfig.pk);
	        if (UI.Dialog.checkDelete(KeyValue)) {
	            var delparm = 'KeyValue=' + KeyValue;
	            UI.Dialog.doDelete(pageConfig.modulePath+'/deleteById.do', delparm, KeyValue.split(",").length);
	        }
	    }

	    /*
	   	 刷新表格
	    */
	    function tableRefresh() {
	    	UI.BootstrapTable.refresh("gridTable");
	    }
	    
	    //关闭当前tab
	    function btn_back() {
	        UI.Top.closeTab();
	    }
        //刷新
	    function Replace() {
	        location.reload();
	        return false;
	    }
 </script>
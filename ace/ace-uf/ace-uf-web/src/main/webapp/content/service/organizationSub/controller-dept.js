function initController() {

	$('#btn-sub-view-add')
			.on(
					'click',
					function() {
					var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
                    	var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);

						var config = {
							extensions : "jpg,gif,png,bmp,jpeg",
							url : contextPath
									+ '/organizationSub/uploadFile.do',
							target : "grid",
							multipart_params : {
								id : r.departmentId
							}
						};
						reset_uploader(config);
						$("#tt").addClass('hide');
						var dialog = $("#dialog-message")
								.removeClass('hide')
								.dialog(
										{
											modal : true,
											width : 750,
											title : "<div class='widget-header widget-header-small'><div class='widget-header-pd' >文件上传</div></div>",
											title_html : true,
											buttons : [{
												html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
												"class" : "btn btn-info btn-xs",
												click : function() {
													$(this).dialog("close");
												}
											}]
										});

					});

	$('#btn-sub-view-del').on(
			'click',
			function() {

				var gr = jQuery(cfgsub.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				jQuery(cfgsub.grid_selector).jqGrid(
						'delGridRow',
						gr,
						{
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
			});
}

var params = {};
jQuery(function($) {
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;'
			if (("title_html" in this.options)
					&& this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));
	$('#fm-search').ajaxForm({
    			beforeSubmit : function(formData, jqForm, options) {

    				$.each(formData, function(n, obj) {
    					params[obj.name] = obj.value;
    				});
    				$.extend(params, {
    					time : new Date()
    				});
    				// console.log(params);
    				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
    					page : 1,
    					postData : params
    				}).trigger("reloadGrid");
    				return false;
    			}
    		});
	$('#btn-search').on('click', function() {

	});

	$('#btn-view-add').on(
			'click',
			function() {
				jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						'new',
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : false,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
				appendUploadBtn("photo");
				appendUploadBtn("qrcode");

			});
	$('#btn-view-edit').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext)
				}
				jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						gr,
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : true,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
				appendUploadBtn("photo");
                				appendUploadBtn("qrcode");

			});
	$('#btn-view-del').on(
			'click',
			function() {

				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				jQuery(cfg.grid_selector).jqGrid(
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

	$('#btn-view-import')
			.on(
					'click',
					function() {

						var config = {
							extensions : "xls,xlsx",
							url : contextPath + '/personage/importXls.do',
							target : null,
							multipart_params : {}
						};
						reset_uploader(config);
						$("#tt").removeClass('hide');
						var dialog = $("#dialog-message")
								.removeClass('hide')
								.dialog(
										{
											modal : true,
											width : 750,
											title : "<div class='widget-header widget-header-small'><div class='widget-header-pd' >文件上传</div></div>",
											title_html : true,
											buttons : [
													{
														html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
														"class" : "btn btn-info btn-xs",
														click : function() {
															$(this).dialog(
																	"close");
														}
													},
													{
														html : "<i class='ace-icon glyphicon glyphicon-refresh bigger-110'></i>&nbsp; 重置",
														"class" : "btn btn-info btn-xs",
														id : 'ajax_button',
														click : function() {
															reset_uploader(config);
														}
													}

											]
										});
					});

	$('#btn-view-print').on(
			'click',
			function() {
				var url = "print.jsp";
				window.open(url);
			});

});

function preview(id, title) {
	var dialog = $("#dialog-message-view")
			.removeClass('hide')
			.dialog(
					{
						modal : false,
						width : 800,
						title : "<div class='widget-header widget-header-small'><div class='widget-header-pd'>"
								+ title + "</div></div>",
						title_html : true,
						buttons : [

								{
									html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
									"class" : "btn btn-info btn-xs",
									click : function() {
										$(this).dialog("close");
									}
								},
								{
									html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
									"class" : "btn btn-xs",
									click : function() {
										$(this).dialog("close");
									}
								}]
					});
					$(dialog).parent().css("top","1px");
                    		$(dialog).css("max-height",window.innerHeight-layoutTopHeight+50);
	loadView(id);
}
function loadView(id) {
	$.ajax({
		type : "post",
		url : cfg.view_load_data_url,
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			$.each(rst.value, function(key, value) {
				if (key == 'sex') {
					value = rsd(value, '01');
				}
				if (key == 'category') {
					value = rsd(value, '98');
				}
				if (key == 'nation') {
					value = rsd(value, '09');
				}
				if (key == 'degreee') {
					value = rsd(value, '10');
				}
				if (key == 'academicTitle') {
					value = rsd(value, '99');
				}
				if (key == 'rank') {
					value = rsd(value, '100');
				}

				if (key == 'party') {
					value = rsd(value, '101');
				}
				if (key == 'careerType') {
                	value = rsd(value, '102');
                }
				if (key == 'academicTitle') {
					value = rsd(value, '99');
				}
				if (key == 'status') {
					value = value == "1" ? "正常" : "关闭";
				}
				if (key.indexOf('Date') != -1 || key.indexOf('time') != -1
						|| key.indexOf('Time') != -1
						|| key.indexOf('birthday') != -1) {
					value = Common.DateFormatter(value);
				}
				if (key == 'photo'||key == 'remark') {
					if (value != '') {
						value = '<image src="' + fastdfs_server + value
								+ '" />';
					} else {
						value = '待上传';
					}

				}
				$("#dialog-message-view").find('#' + key).html(value);
			});
		},
		error : function() {
			alert("加载错误！");
		}
	});
}

function clearAreaCode() {
	$('#cc2').combotree('setValue', '');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1,
		postData : {
			areaCode : ''
		}
	}).trigger("reloadGrid");
}
function t_submit(){


    return false;
}
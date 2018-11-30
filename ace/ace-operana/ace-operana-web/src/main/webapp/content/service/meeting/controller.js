var meetingId,topicId,normId,_title;
jQuery(function($) {
	$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
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

							}
						})
			});


			$('#btn-view-copy').on(
            			'click',
            			function() {

            				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
            						'selrow');
            				if (!gr) {
            					$.jgrid.info_dialog($.jgrid.nav.alertcap,
            							$.jgrid.nav.alerttext);
            					return;
            				}
            				var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
            				copyMeetingById(r.name,r.id);
            			});
});

function preview(id, title) {
	$('#modal-meeting').modal('show');
    meetingId=id,_title=title;
	act1(meetingId);
	
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
				if (key == 'category') {
					value = rsd(value, '83');
				}
				if (key == 'site') {
                	 value = rsd(value, '106');
                }
				if (key == 'status') {
					value = value == "1" ? "on" : "off";
				}
				if (key.indexOf('Date') != -1 || key.indexOf('time') != -1
						|| key.indexOf('Time') != -1
						|| key.indexOf('birthday') != -1) {
					value = value.substr(0, 16);
				}
				$("#dialog-message-view").find('#' + key).html(value);
			});
		},
		error : function() {
			alert("加载错误！");
		}
	});
}



function edit(rowid) {
	console.log(rowid);
	jQuery(cfg.grid_selector).jqGrid(
		'editGridRow',
		rowid, {
			closeAfterAdd: true,
			recreateForm: true,
			viewPagerButtons: true,
			beforeShowForm: function(e) {
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find(
					'.ui-jqdialog-titlebar').wrapInner(
					'<div class="widget-header" />')

			}
		});
}
var show = false;
function del(rowid) {
	console.log(rowid);
	jQuery(cfg.grid_selector).jqGrid(
		'delGridRow',
		rowid, {
			beforeShowForm: function(e) {
				var form = $(e[0]);
				if (!show) {
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
				}
				show = true;
			}
		});
}
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
								style_edit_form(form);
							}
						})
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
                                $("#TblGrid_grid-table").after("<div id='custom-dia'></div>");
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
                                    '<div class="widget-header" />');
                                var gd = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
								style_edit_form(form);
                                initMedia(gd.id);
							}
						})
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
});

function preview(id,title){
        var dialog = $( "#dialog-message-view" ).removeClass('hide').dialog({
			modal: false,
			width:800,
			title: "<div class='widget-header widget-header-small'><div class='widget-header-pd'>"+title+"</div></div>",
			title_html: true,
			buttons: [

				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					click: function() {
                       $( this ).dialog( "close" );
					}
				},
				{
					html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
					"class" : "btn btn-xs",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
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
				if (key == 'category') {
                	value = rsd(value, '83');
                }
                if (key == 'status') {
                   value == "1" ? "正常" : "关闭";
                }
                if (key == 'trafficSubVo') {
                    viewImgAndVdo(value)
                }
                // if (key.indexOf('Date')!=-1||key.indexOf('time')!=-1||key.indexOf('Time')!=-1||key.indexOf('birthday')!=-1) {
                //  	value = Common.DateFormatter(value);
                // }
				$("#dialog-message-view").find('#' + key).html(value);
			});
		},
		error : function() {
			alert("加载错误！");
		}
	});
}

function viewImgAndVdo(data) {
    $('.traffic-file .fileList').empty();
    for (var i = 0; i < data.length; i++) {
        var template = liImageTemplate;
        if (data[i].category == '5') {
            template = liVideoTempLate;
        }
        template = template.replace('[fileUrl]', fastdfs_server + data[i].fileUrl);
        template = template.replace('[deviceName]', data[i].deviceName ? data[i].deviceName : "拍摄方式");
        template = template.replace('[category]', rsd(data[i].category, '124'));
        template = template.replace('[fileTime]', data[i].inspectTime);
        $('.traffic-file .fileList').append($(template));
    }
}

var liImageTemplate = '<li>' +
    '       <img src="[fileUrl]">' +
    '       <p>' +
    '             <span>[deviceName]：[category]</span>' +
    '             <span class="time">[fileTime]</span>' +
    '       </p>' +
    '</li>';

var liVideoTempLate = '<li>' +
    '       <video src="[fileUrl]"  controls="controls">' +
    '       </video>' +
    '       <p>' +
    '             <span>[deviceName]：[category]</span>' +
    '             <span class="time">[fileTime]</span>' +
    '       </p>' +
    '</li>';
var editor;
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
							beforeSubmit : function(postdata) {
                                    postdata.docText=editor.getValue();
                                    return [true,"",""];

                            },
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
								initSimditor($("textarea[name=docText]"),null);
								appendMapBtn("activityLocation");
                                appendUploadBtn("photo");
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
							beforeSubmit : function(postdata) {
                                    postdata.docText=editor.getValue();
                                    return [true,"",""];

                            },
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
								$("#TblGrid_grid-table").after("<div id='custom-dia'></div>");
								var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
                                var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
                                loadText(gd.id);
                                initPhoto(gd.id);
                                appendMapBtn("activityLocation");
                                appendUploadBtn("photo");
							}
						});


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

				$( "#btn-view-multi" ).on('click', function(e) {
            		e.preventDefault();
            		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
            		'selrow');
            		if (!gr) {
            			$.jgrid.info_dialog($.jgrid.nav.alertcap,
            					$.jgrid.nav.alerttext);
            			return;
            		}
            		var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
            		var activityId=r.id;
            		var dialog = $( "#dialog-multi" ).removeClass('hide').dialog({
            			modal: true,
            			width:610,
            			title: "<div class='widget-header widget-header-small'><h4 class='smaller'></i>参与人员</h4></div>",
            			title_html: true,
            			buttons: [

            				{
            					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 保存",
            					"class" : "btn btn-info btn-xs",
            					id:'btn-multi',
            					click: function() {
            						insertActivityUser(activityId);

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
            		selectListByActivityId(activityId);

            	});

});

function preview(id,title){
        var dialog = $( "#dialog-message-view" ).removeClass('hide').dialog({
			modal: false,
			width:950,
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
		selectActivityUserListByActivityId(id);
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
                	value = rsd(value, '103');
                }
                if (key == 'status') {
                   value == "1" ? "正常" : "关闭";
                }if (key.indexOf('Date')!=-1||key.indexOf('time')!=-1||key.indexOf('Time')!=-1||key.indexOf('birthday')!=-1) {
                 	value = Common.DateFormatter(value);
                }
				$("#dialog-message-view").find('#' + key).html(value);
			});
		},
		error : function() {
			alert("加载错误！");
		}
	});
	initPhoto(id);
}
function loadText(id) {
	$.ajax({
		type : "post",
		url : cfg.view_load_data_url,
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
            initSimditor($("textarea[name=docText]"),rst.value.docText);
		},
		error : function() {
			alert("加载错误！");
		}
	});
}
function appendMapBtn(id) {
	var html = new Array();
	html
			.push("<a id='btn-map-add-"
					+ id
					+ "' class='ace-icon fa fa-location-arrow bigger-110' href='javascript:false'>选取</a>");
	$("#" + id).after(html.join(''));
	$('#btn-map-add-'+ id).on('click', function() {
            window.open(portalPath+"/dynamic/common/map.jsp");
    	});
}
function latitude(latitude){
    $("#latitude").val(latitude);
}
function longitude(longitude){
    $("#longitude").val(longitude);
}
function addr(addr){
    $("#activityLocation").val(addr);
}
function initSimditor(textarea,text){
            editor = new Simditor({
                 textarea:textarea,
                 params :{},
                 toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough','fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
                             upload: {
                                 url: portalPath+'/files/uploadImage.do', //文件上传的接口地址
                                 params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
                                 fileKey: 'file', //服务器端获取文件数据的参数名
                                 connectionCount: 3,
                                 leaveConfirm: '正在上传文件'
                             }
             });
             if(text){
                   editor.setValue(text);
             }
}

function insertActivityUser(activityId){
	var data=new Array();
	$.each($('user'),function(i,obj){
		data.push({userId:$(obj).attr("id"),activityId:activityId});
	});
	$.ajax({
		type : "post",
		url : contextPath + "/activityUser/insertActivityUser.do",
		data:{text:JSON.stringify(data)},
		beforeSend : function(XMLHttpRequest) {
			$("btn-multi").attr("disabled",true);
		},
		success : function(rst, textStatus) {
		    $("btn-multi").attr("disabled",false);
			if (rst) {
				bootbox.dialog({
					title:'系统提示',
					message:rst.errorMessage,
					detail:rst.detail,
					buttons:
					{
						"success" :
						 {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback": function() {
								$("#dialog-multi").dialog( "close" );
							}
						}
					}
				});
			}
		},
		error : function() {
            $("btn-multi").attr("disabled",false);
		}
	});
}
function selectListByActivityId(activityId){
	$.ajax({
		type : "post",
		url : contextPath + "/activityUser/selectListByActivityId.do",
		data:{id:activityId},
		success : function(rst, textStatus) {
			var html=new Array();
			$.each($(rst),function(i,o){
				html.push('<div class="layout-user">');
				html.push('<user id="'+o.userId+'" class="badge badge-'+cssColor9[0]+'">');
				html.push(o.name);
				html.push('</user>');
				html.push('</div>');
			});
			$('#multi-content').html(html.join(''));
		}
	});
}
function selectActivityUserListByActivityId(activityId){
	$.ajax({
		type : "post",
		url : contextPath + "/activityUser/selectListByActivityId.do",
		data:{id:activityId},
		success : function(rst, textStatus) {
			var html=new Array();
			$.each($(rst),function(i,o){
				html.push('<div class="layout-user">');
				html.push('<user id="'+o.userId+'" class="badge badge-'+cssColor9[0]+'">');
				html.push(o.name);
				html.push('</user>');
				html.push('</div>');
			});
			$('#activityUser').html(html.join(''));
		}
	});
}

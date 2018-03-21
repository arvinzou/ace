var editor;
jQuery(function($) {
	$.fn.spin = function(opts) {
		this.each(function() {
			var $this = $(this), data = $this.data();

			if (data.spinner) {
				data.spinner.stop();
				delete data.spinner;
			}
			if (opts !== false) {
				data.spinner = new Spinner($.extend({
					color : $this.css('color')
				}, opts)).spin(this);
			}
		});
		return this;
	};

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
                                postdata.evContent=editor.getValue();
                                return [true,"",""];

                             },
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
								initSimditor($("textarea[name=evContent]"),null);
								$('#category').change(function(){
									var p1=$(this).children('option:selected').val();
									if(p1=='1462546395182'||p1=='1503814369360'){
										var url=contextPath + '/evTask/selectDepAndUsersTreeList.do?id=01';
										$('#evObj').combotree('reload',url);
										$('#evObj').combotree({
										    cascadeCheck:true,
										    checkbox:true,
							                multiple:true
										});

									}
									if(p1=='1462546395184'||p1=='1462546202593'){
										var url=portalContextPath+'/system/selectDepartmentTreeList.do?id=01';
										$('#evObj').combotree({
											checkbox:false,
							                multiple:false
										});
										$('#evObj').combotree('reload',url);

									}
									if(p1=='1503814369360'){
									    $("#evTempleteId").attr("disabled",true);
									}else{
									    $("#evTempleteId").removeAttr("disabled",true);
									}

									})

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
                                postdata.evContent=editor.getValue();
                                return [true,"",""];

                             },
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);

								var gd = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);

								if(gd.category=='1462546395182'||gd.category=='1503814369360'){
									$('#evObj').combotree({
									    cascadeCheck:true,
									    checkbox:true,
						                multiple:true
									});


								}else{
									$('#evObj').combotree({
										checkbox:false,
						                multiple:false
									});
								}

                                var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
                                loadText(gd.evTaskId);

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
	$("#btn-view-cfg").on(
			'click',
			function(e) {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext)
							return;
				}
				var gd = jQuery(cfg.grid_selector).jqGrid('getRowData', gr)
				parent.addPanel(gd.evTaskName, contextPath
						+ '/dynamic/service/evTask/preview.jsp?id=' + urlid
						+ '&evTaskId=' + gd.evTaskId + "&time="
						+ new Date(), true);

			});
	function style_ajax_button(btnId, status) {
		console.log(status);
		var btn = $('#' + btnId);
		if (status) {
			btn.find('i').removeClass('fa-check');
			btn.find('i').addClass('fa-spinner fa-spin');
			btn.attr('disabled', "true");

		} else {
			btn.find('i').removeClass('fa-spinner');
			btn.find('i').removeClass('fa-spin');
			btn.find('i').addClass('fa-check');
			btn.removeAttr("disabled");
		}
	}
});

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
            initSimditor($("textarea[name=evContent]"),rst.response.evContent);
		},
		error : function() {
			alert("加载错误！");
		}
	});
}

function initSimditor(textarea,text){
            editor = new Simditor({
                 textarea:textarea,
                 params :{},
                 toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough','fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
                             upload: {
                                 url: '/files/uploadImage.do', //文件上传的接口地址
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
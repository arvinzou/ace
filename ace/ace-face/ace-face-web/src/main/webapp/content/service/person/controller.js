
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
						appendUploadBtn("photo");
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
			});
	$('#btn-view-del').on('click',function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				jQuery(cfg.grid_selector).jqGrid('delGridRow',gr,{beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
				})
			});

			$('#btn-view-detect').on(
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
            				if(r.photo){
            				    detect(r.id,fastdfs_server + r.photo);
            				}

            });
            $('#btn-view-faceadd').on('click',function() {
                    var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
                            'selrow');
                    if (!gr) {
                        $.jgrid.info_dialog($.jgrid.nav.alertcap,
                                $.jgrid.nav.alerttext);
                        return;
                    }
                    var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
                    if(r.faceFoken){
                        removeface(r.faceFoken);
                    }
            });
            $('#btn-view-removeall').on('click',function() {
                removeallface();
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
	$(dialog).parent().css("top", "1px");
	$(dialog).css("max-height", window.innerHeight - layoutTopHeight + 50);
	loadView(id);
}
function loadView(id) {
    var o=null;
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
				if (key == 'status') {
					value == "1" ? "正常" : "关闭";
				}
				if (key.indexOf('Date') != -1 || key.indexOf('time') != -1
						|| key.indexOf('Time') != -1
						|| key.indexOf('birthday') != -1) {
					value = Common.DateFormatter(value);
				}
				if (key == 'photo') {
                    if (value != '') {
                        value = '<image src="' + fastdfs_server + value
                                + '" />';
                    } else {
                        value = '待上传';
                    }

                }
                if (key == 'attributes') {
                    o=JSON.parse(value);
                	console.log(o);
                }

				$("#dialog-message-view").find('#' + key).html(value);
			});
			if(o){
			    $("#dialog-message-view").find('#gender').html(gender[o.gender.value]);
                $("#dialog-message-view").find('#age').html(o.age.value);
                $("#dialog-message-view").find('#smile').html(o.smile.threshold);
                $("#dialog-message-view").find('#glass').html(glass[o.glass.value]);
                //$("#dialog-message-view").find('#headpose').html("抬头:"+o.headpose.pitch_angle+" 旋转:"+o.headpose.roll_angle+" 摇头:"+o.headpose.yaw_angle);
                if(o.emotion.anger>=50){
                    o.emotion="anger";
                }
                if(o.emotion.disgust>=50){
                    o.emotion="disgust";
                }
                if(o.emotion.surprise>=50){
                    o.emotion="surprise";
                }
                if(o.emotion.fear>=50){
                    o.emotion="fear";
                }
                if(o.emotion.happiness>=50){
                    o.emotion="happiness";
                }
                if(o.emotion.neutral>=50){
                    o.emotion="neutral";
                }
                if(o.emotion.sadness>=50){
                    o.emotion="sadness";
                }
                $("#dialog-message-view").find('#emotion').html(emotion[o.emotion]);
                $("#dialog-message-view").find('#ethnicity').html(ethnicity[o.ethnicity.value]);
                var beauty=o.beauty.female_score;
                if(o.gender.value=="Male"){
                    beauty=o.beauty.male_score;
                }
                $("#dialog-message-view").find('#beauty').html(beauty);

                $("#dialog-message-view").find('#health').html(o.skinstatus.health);
                $("#dialog-message-view").find('#stain').html(o.skinstatus.stain);
                $("#dialog-message-view").find('#acne').html(o.skinstatus.acne);
                $("#dialog-message-view").find('#dark_circle').html(o.skinstatus.dark_circle);
                $("#html_rst").removeClass("hide");



			}



		},
		error : function() {
			alert("加载错误！");
		}
	});
}

function updatePersonFaceTokenAttributes(id,faceToken,attributes){
    console.log(faceToken);
	$.ajax({
		type : "post",
		url : contextPath+'/person/updatePersonFaceTokenAttributes.do',
		data : {
			id : id,
			faceToken:faceToken,
			attributes:attributes
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
		    console.log(rst);
		    reloadGrid();
		    alert(rst["errorMessage"]);
		},
		error : function() {
			alert("加载错误！");
		}
	});
}

function updatePersonStatus(id,status){
    console.log(id);
	$.ajax({
		type : "post",
		url : contextPath+'/person/updatePersonStatus.do',
		data : {
			id : id,
			status:status
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
		    console.log(rst);
		    reloadGrid();
		    alert(rst["errorMessage"]);

		},
		error : function() {
			alert("HTTP错误！");
		}
	});
}

function reloadGrid(){
    jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger("reloadGrid");
}

function updatePersonAllStatus(status){
	$.ajax({
		type : "post",
		url : contextPath+'/person/updatePersonAllStatus.do',
		data : {
			status:status
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
		    console.log(rst);
		    reloadGrid();

		},
		error : function() {
			alert("HTTP错误！");
		}
	});
}
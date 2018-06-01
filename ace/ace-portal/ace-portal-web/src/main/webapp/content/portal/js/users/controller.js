 var dialog1, dialog2;
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
$('#btn-search-weixin').on('click', function() {
        $.ajax({
                type : "post",
                url : contextPath + "/users/selectAllWxUserList.do",
                data:{role:"",nickname:$("#q1").val()},
                success : function(rst, textStatus) {
                    var html=[];
                    $.each($(rst),function(i,o){
                            html.push('<div class="layout-user">');
                            html.push('<a href="javascript:insertWeixinUser(\''+o.unionid+'\',\''+o.openid+'\')">');
                            html.push('<img  class="photo" src="'+o.headimgurl+'">');
                            html.push('</a>');
                            html.push('<div style="text-align:center">');
                            html.push(o.nickname);
                            html.push('</div>');
                            html.push('</div>');
                    });
                    $("#box-weixin").html(html.join(""));
                }
         });
    });


    $('#btn-search-sapp').on('click', function() {
            $.ajax({
                    type : "post",
                    url : contextPath + "/users/selectAllAppWxUserList.do",
                    data:{role:"",nickName:$("#q2").val()},
                    success : function(rst, textStatus) {
                        var html=[];
                        $.each($(rst),function(i,o){
                                html.push('<div class="layout-user">');
                                html.push('<a href="javascript:insertSappUser(\''+o.unionId+'\',\''+o.openId+'\')">');
                                html.push('<img  class="photo" src="'+o.avatarUrl+'">');
                                html.push('</a>');
                                html.push('<div style="text-align:center">');
                                html.push(o.nickName);
                                html.push('</div>');
                                html.push('</div>');
                        });
                        $("#box-sapp").html(html.join(""));
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
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);

							}
						});
						console.log("==============================================");
	                        var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
                             $("#tr_title1 h5").after("<div id='custom-weixin'></div>");
                             $("#tr_title2 h5").after("<div id='custom-sapp'></div>");
                            initWeixin(r.userId);
                            initSapp(r.userId);

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
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));
	$( "#btn-view-da" ).on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
		// var row=jQuery(cfg.grid_selector).jqGrid('getGridParam','selarrrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext);
			return;
		}
		// console.log(gr);
		var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
		// console.log(r);
		var ajax_button;
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:750,
			title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' > "+r.name+"</div></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 提交",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
						var rowIds = jQuery("#myrole-grid-table").jqGrid('getDataIDs');
						if(rowIds.length<1){
							bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 至少要分配一个角色!</h4></div>", function(result) {
								
							});
							return;
						}
						
						$( "#dialog-confirm" ).removeClass('hide').dialog({
							resizable: false,
							modal: false,
							title: "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 确认分配角色吗?</h4></div>",
							title_html: true,
							buttons: [
								{
									html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 提交",
									"class" : "btn btn-info btn-xs",
									click: function() {
										$( this ).dialog( "close" ); 
										var s='';
										var rowIds = jQuery("#myrole-grid-table").jqGrid('getDataIDs');
										if(rowIds){
											s=rowIds.join(',')
										}
										$.ajax({
											type : "post",
											url : contextPath + "/users/insertUsersRole.do",
											data:{userId:gr,roleId:s},
											beforeSend : function(XMLHttpRequest) {
												style_ajax_button('ajax_button',true);
											},
											success : function(rst, textStatus) {
												style_ajax_button('ajax_button',false);
												if (rst) {
													bootbox.dialog({
														title:'系统提示',
														message:rst.errorMessage,
														buttons: 			
														{
															"success" :
															 {
																"label" : "<i class='ace-icon fa fa-check'></i>确定",
																"className" : "btn-sm btn-success",
																"callback": function() {
																	dialog.dialog( "close" ); 
																}
															}
														}
													});
											
												}
											},
											complete : function(XMLHttpRequest, textStatus) {
												style_ajax_button('ajax_button',false);
											},
											error : function() {
												style_ajax_button('ajax_button',true);
											}
										});
									}
								}
								,
								{
									html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
									"class" : "btn btn-xs",
									click: function() {
										$( this ).dialog( "close" );
									}
								}
							]
						});
						
						
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
		
		jQuery('#myrole-grid-table').jqGrid('setGridParam', {url : contextPath+"/users/selectRoleListByUserId.do",postData : {userId:gr}}).trigger("reloadGrid");
	});
	$( "#btn-view-initpwd" ).on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext);
			return;
		}
		var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
		var ajax_button;
		var dialog = $( "#dialog-message-initpwd" ).removeClass('hide').dialog({
			modal: true,
			width:380,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> "+r.name+r.userId+" 初始化密码</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button_initpwd',
					click: function() {
						if ($('#init_passwd').val()!=$('#init_rpasswd').val()) {
							bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 两次输入密码不一致!</h4></div>", function(result) {
								
							});
							return;
						}
						
						$( "#dialog-confirm-initpwd" ).removeClass('hide').dialog({
							resizable: false,
							modal: false,
							title: "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 确认初始化吗?</h4></div>",
							title_html: true,
							buttons: [
								{
									html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
									"class" : "btn btn-info btn-xs",
									click: function() {
										$( this ).dialog( "close" ); 
										$.ajax({
											type : "post",
											url : contextPath + "/users/updateUsersForInitPassword.do",
											data:{userId:gr,password:$('#init_passwd').val()},
											beforeSend : function(XMLHttpRequest) {
												style_ajax_button('ajax_button_initpwd',true);
											},
											success : function(rst, textStatus) {
												style_ajax_button('ajax_button_initpwd',false);
												if (rst) {
													bootbox.dialog({
														title:'系统提示',
														message:rst.errorMessage,
														buttons: 			
														{
															"success" :
															 {
																"label" : "<i class='ace-icon fa fa-check'></i>确定",
																"className" : "btn-sm btn-success",
																"callback": function() {
																	dialog.dialog( "close" ); 
																}
															}
														}
													});
											
												}
											},
											complete : function(XMLHttpRequest, textStatus) {
												style_ajax_button('ajax_button_initpwd',false);
											},
											error : function() {
												style_ajax_button('ajax_button_initpwd',true);
											}
										});
									}
								}
								,
								{
									html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
									"class" : "btn btn-xs",
									click: function() {
										$( this ).dialog( "close" );
									}
								}
							]
						});
						
						
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
	});
	$( "#btn-view-da-add" ).on('click', function(e) {
		var gr = jQuery('#allrole-grid-table').jqGrid('getGridParam','selrow');
		if (!gr) {
			bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 请选择要分配的角色!</h4></div>", function(result) {
				
			});
			return;
		}
		var srows=jQuery('#allrole-grid-table').jqGrid('getGridParam','selarrrow');
		var rowIds = jQuery("#myrole-grid-table").jqGrid('getDataIDs');
		var repeat=false,rpn=[];
		$.each(srows,function(i,o){
			var rd=jQuery('#allrole-grid-table').jqGrid('getRowData',o);
			if($.inArray(o,rowIds) > -1){
				repeat=true,rpn.push(rd.roleName);
			}else{
				jQuery("#myrole-grid-table").jqGrid('addRowData',o, rd);
			}
		});
		if(repeat){
			bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 请选择要分配的角色!</h4></div>重复的角色<b>"+rpn.join(',')+"<b>!</h4></div>", function(result) {
				
			});
		}
		
	});
	$( "#btn-view-da-del" ).on('click', function(e) {
		var gr = jQuery('#myrole-grid-table').jqGrid('getGridParam','selrow');
		if (!gr) {
			bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i>请选择要移除的角色!</h4></div>", function(result) {
				
			});
			return;
		}
		var srows=jQuery('#myrole-grid-table').jqGrid('getGridParam','selarrrow');		
		$.each(srows,function(i,o){
			jQuery("#myrole-grid-table").jqGrid('delRowData',o);
		});
		$.each(srows,function(i,o){
			jQuery("#myrole-grid-table").jqGrid('delRowData',o);
		});
	});
	/*function style_ajax_button(btn,status){
		//console.log(status);
		if(status){
			$('#'+btn).find('i').removeClass('fa-check');
			$('#'+btn).find('i').addClass('fa-spinner fa-spin');
		}else{
			$('#'+btn).find('i').removeClass('fa-spinner');
			$('#'+btn).find('i').removeClass('fa-spin');
			$('#'+btn).find('i').addClass('fa-check');
		}
		$('#'+btn).disabled=status;
	}*/
	function style_ajax_button(btnId,status){
		console.log(status);
		var btn=$('#'+btnId);
		if(status){
			btn.find('i').removeClass('fa-check');
			btn.find('i').addClass('fa-spinner fa-spin');
			btn.attr('disabled',"true");
			
		}else{
			btn.find('i').removeClass('fa-spinner');
			btn.find('i').removeClass('fa-spin');
			btn.find('i').addClass('fa-check');
			btn.removeAttr("disabled");
		}
	}
		$( "#btn-view-import" ).on('click', function(e) {
    		e.preventDefault();
    		reset_uploader();
    		var dialog = $( "#dialog-message-upload" ).removeClass('hide').dialog({
    			modal: true,
    			width:750,
    			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> 导入</h4></div>",
    			title_html: true,
    			buttons: [

    				/*{
    					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 重置",
    					"class" : "btn btn-info btn-xs",
    					id:'ajax_button',
    					click: function() {
    						reset_uploader();

    					}
    				},*/
    				{
    					html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 关闭",
    					"class" : "btn btn-xs",
    					click: function() {
    						$( this ).dialog( "close" );
    					}
    				}
    			]
    		});

    	});
});
function clearAreaCode(){
	$('#cc2').combotree('setValue', '');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1,
		postData : {areaCode:''}
	}).trigger("reloadGrid");
}


function initWeixin(userId){
    $.ajax({
		type : "get",
		url : contextPath + "/users/selectWxUser.do",
		data:{userId:userId},
		success : function(rst, textStatus) {
			renderWeixin(rst);
		}
	});
}
function renderWeixin(rst){
        var html=new Array();
        html.push('<ul class="ace-thumbnails clearfix">');
        $.each($(rst),function(i,o){
				html.push('<li>');
                html.push('<a href="'+o.headimgurl+'" title="'+o.nickname+'" data-rel="colorbox" class="cboxElement">');
                html.push('<img class="photo" src="'+o.headimgurl+'">');
                html.push('</a>');
                html.push('<div class="tools tools-bottom">');
                html.push('<a href="javascript:delWeixin(\''+o.unionid+'\')">');
                html.push('<i class="ace-icon fa fa-times red"></i>');
                html.push('</a>');
                html.push('</div>');
                html.push('<div style="text-align:center">');
                html.push(o.nickname);
                html.push('</div>');
                html.push('</li>');
		});
                html.push('<li>');
                html.push('<a href="javascript:false"><img style="padding:20px" alt="60x60" id="btn-image-upload-weixin" class="photo" src="'+portalPath+'/content/common/image/add.png"></a>');
                html.push('</li>');
        html.push('</ul>');


    $("#custom-weixin").html(html.join(""));

    var $overflow = '';
    	var colorbox_params = {
    		rel: 'colorbox',
    		reposition:true,
    		scalePhotos:true,
    		scrolling:false,
    		previous:'<i class="ace-icon fa fa-arrow-left"></i>',
    		next:'<i class="ace-icon fa fa-arrow-right"></i>',
    		close:'&times;',
    		current:'{current} of {total}',
    		maxWidth:'100%',
    		maxHeight:'100%',
    		onOpen:function(){
    			$overflow = document.body.style.overflow;
    			document.body.style.overflow = 'hidden';
    		},
    		onClosed:function(){
    			document.body.style.overflow = $overflow;
    		},
    		onComplete:function(){
    			$.colorbox.resize();
    		}
    	};

    	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
    	$('#btn-image-upload-weixin').on('click',function() {
            dialog1 = $("#dialog-message-weixin").removeClass('hide').dialog({
                modal : true,
                width : 750,
                title : "<div class='widget-header widget-header-small'><div class='widget-header-pd'>绑定</div></div>",
                title_html : true,
                buttons : [{
                    html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                    "class" : "btn btn-info btn-xs",
                    click : function() {
                        $(this).dialog("close");
                    }
                }]
            });
            $(dialog1).parent().css("top","1px");
            $(dialog1).css("max-height",window.innerHeight-layoutTopHeight+50);

        });
}
function delWeixin(id){
var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
$.ajax({
		type : "post",
		url : contextPath + "/users/deleteOpenIdById.do",
		data:{userId:r.userId},
		success : function(rst, textStatus) {
			initWeixin(r.userId);
		}
	});
}
function insertWeixinUser(unionid,openId){
var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
$.ajax({
		type : "post",
		url : contextPath + "/users/updateOpenIdById.do",
		data:{userId:r.userId,openId:unionid},
		success : function(rst, textStatus) {
			initWeixin(r.userId);
			 $(dialog1).dialog("close");
			$("#dialog-message-weixin").addClass('hide');
		}
	});
}


/**********************************************************************************************************************/




function initSapp(userId){
    $.ajax({
		type : "get",
		url : contextPath + "/users/selectAppWxUser.do",
		data:{userId:userId},
		success : function(rst, textStatus) {
			renderSapp(rst);
		}
	});
}
function renderSapp(rst){
        var html=new Array();
        html.push('<ul class="ace-thumbnails clearfix">');
        $.each($(rst),function(i,o){
				html.push('<li>');
                html.push('<a href="'+o.avatarUrl+'" title="'+o.nickName+'" data-rel="colorbox" class="cboxElement">');
                html.push('<img class="photo" src="'+o.avatarUrl+'">');
                html.push('</a>');
                html.push('<div class="tools tools-bottom">');
                html.push('<a href="javascript:delSapp(\''+o.unionId+'\')">');
                html.push('<i class="ace-icon fa fa-times red"></i>');
                html.push('</a>');
                html.push('</div>');
                html.push('<div style="text-align:center">');
                html.push(o.nickName);
                html.push('</div>');
                html.push('</li>');
		});
                html.push('<li>');
                html.push('<a href="javascript:false"><img style="padding:20px" alt="60x60" id="btn-image-upload-sapp" class="photo" src="'+portalPath+'/content/common/image/add.png"></a>');
                html.push('</li>');
        html.push('</ul>');


    $("#custom-sapp").html(html.join(""));

    var $overflow = '';
    	var colorbox_params = {
    		rel: 'colorbox',
    		reposition:true,
    		scalePhotos:true,
    		scrolling:false,
    		previous:'<i class="ace-icon fa fa-arrow-left"></i>',
    		next:'<i class="ace-icon fa fa-arrow-right"></i>',
    		close:'&times;',
    		current:'{current} of {total}',
    		maxWidth:'100%',
    		maxHeight:'100%',
    		onOpen:function(){
    			$overflow = document.body.style.overflow;
    			document.body.style.overflow = 'hidden';
    		},
    		onClosed:function(){
    			document.body.style.overflow = $overflow;
    		},
    		onComplete:function(){
    			$.colorbox.resize();
    		}
    	};

    	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
    	$('#btn-image-upload-sapp').on('click',function() {
            dialog2 = $("#dialog-message-sapp").removeClass('hide').dialog({
                modal : true,
                width : 750,
                title : "<div class='widget-header widget-header-small'><div class='widget-header-pd'>绑定</div></div>",
                title_html : true,
                buttons : [{
                    html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                    "class" : "btn btn-info btn-xs",
                    click : function() {
                        $(this).dialog("close");
                    }
                }]
            });
            $(dialog2).parent().css("top","1px");
            $(dialog2).css("max-height",window.innerHeight-layoutTopHeight+50);

        });
}
function delSapp(id){
var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
$.ajax({
		type : "post",
		url : contextPath + "/users/deleteAppOpenIdById.do",
		data:{userId:r.userId},
		success : function(rst, textStatus) {
			initSapp(r.userId);
		}
	});
}
function insertSappUser(unionid,openId){
var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
$.ajax({
		type : "post",
		url : contextPath + "/users/updateUserAppOpenId.do",
		data:{userId:r.userId,appOpenId:unionid},
		success : function(rst, textStatus) {
			initSapp(r.userId);
			 $(dialog2).dialog("close");
			$("#dialog-message-sapp").addClass('hide');
		}
	});
}
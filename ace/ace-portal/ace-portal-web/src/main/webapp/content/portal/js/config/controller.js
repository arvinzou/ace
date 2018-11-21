var params = {};
jQuery(function($) {
	$('#btn-search').on('click', function() {
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

	

	
	$('#btn-view-deploy').on(
			'click',
			function() {
				var basePath="/sysConfig/deployConfig.do"
				var url1=contextPath+basePath;
				
				if(confirm("发布后系统将刷新参数，确定要发布吗?")){
					batchDeploy(url1);
				}
				
				
			});
			 $(".btn-group .btn").bind('click', function (event) {
                        $(event.target).siblings().removeClass("active");
                        console.log(event);
                        $(event.target).addClass("active");
                    });
});
function batchDeploy(url){
	$.ajax({
		type : "post",
		url : url,
		data:{time:new Date()},
		beforeSend : function(XMLHttpRequest) {
			startLoad();
		},
		success : function(rst, textStatus) {
			stopLoad();
		},
		complete : function(XMLHttpRequest, textStatus) {
			stopLoad();
		},
		error : function() {
			stopLoad();
		}
	});

}


function edit(rowid){
    console.log(rowid);
	jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						rowid,
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : true,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')

							}
						});
}
var show=false;
function del(rowid){
    console.log(rowid);
	jQuery(cfg.grid_selector).jqGrid(
    						'delGridRow',
    						rowid,
    						{
    							beforeShowForm : function(e) {
    								var form = $(e[0]);
    								if(!show){
    								    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
    								}

    								show=true;

    							}
    						});
}

function setParams(key, value) {
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam',{postData : params}).trigger("reloadGrid");
}
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
								style_edit_form(form);
							}
						})
						initForm();
						appendUploadBtn("remark");
			});

	
	$('#btn-view-deploy').on(
			'click',
			function() {
				if(confirm("发布后系统将刷新字典，确定要发布吗?")){

                    $.ajax({
                        type : "post",
                        url : contextPath+"/dict/deploy.do",
                        data:{time:new Date()},
                        beforeSend : function(XMLHttpRequest) {
                                startLoad();
                        },
                        success : function(rst, textStatus) {
                            if (rst) {
                                alert(rst.errorMessage);
                            }
                        },
                        complete : function(XMLHttpRequest, textStatus) {
                            stopLoad();
                        },
                        error : function() {
                            stopLoad();
                        }
                    });
				}
				
			});
$( "#btn-view-import" ).on('click', function(e) {
		e.preventDefault();
		reset_uploader({
                extensions : "xls,xlsx",
                url : portalPath+'/dict/importXls.do',
                multipart_params : {}
         });
		$('#modal-upload').modal('show');

	});

});


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
    var params = {};
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam',{postData : params}).trigger("reloadGrid");
}
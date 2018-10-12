var roleId;
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

							}
						})
			});


    $("#btn-view-save" ).on('click', function(e) {
    	e.preventDefault();
    	save();
    });


});

function save(){
        var nodes = $('#tt').tree('getChecked', ['checked','indeterminate']);
        var s = '';
        for(var i=0; i<nodes.length; i++){
            if (s != '') s += ',';
            s += nodes[i].id;
        }
        console.log(s);
        $.ajax({
            type : "post",
            url : contextPath + "/role/insertRoleResources.do",
            data:{roleId:roleId,resourcesId:s},
            beforeSend : function(XMLHttpRequest) {
                startLoad();
            },
            success : function(rst, textStatus) {
                if (rst) {
                    $('#modal-role').modal('hide');
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

function role(roleId){
     roleId=roleId;
     $('#modal-role').modal('show');
     $('#tt').tree('options').url=contextPath+'/role/selectRoleResourceTreeList.do?roleId='+roleId
     $("#tt").tree('reload');
     $('#tt').tree({cascadeCheck:true});
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
	jQuery(cfg.grid_selector).jqGrid('delGridRow',
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
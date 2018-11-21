jQuery(function ($) {
    $('#btn-search').on('click', function () {
        $('#fm-search').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var params = {};
                $.each(formData, function (n, obj) {
                    params[obj.name] = obj.value;
                });
                $.extend(params, {
                    time: new Date()
                });
                // console.log(params);
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    page: 1,
                    postData: params
                }).trigger("reloadGrid");
                return false;
            }
        });
    });
 $(".btn-group .btn").bind('click', function (event) {
            $(event.target).siblings().removeClass("active");
            console.log(event);
            $(event.target).addClass("active");
    });
});

function preview(id, title, flowType, fromId) {
    if (flowType == '0') {
        window.open(contextPath + '/dynamic/service/company/view.jsp?companyId=' + fromId);
    } else if (flowType == '1') {
        window.open(contextPath + '/dynamic/service/association/view.jsp?aid=' + fromId);
    }
}

function audit(rowid){
    console.log(rowid);
    var rowData = jQuery(cfg.grid_selector).jqGrid('getRowData', rowid);
    //1-未审核，2-已审核
    if (rowData.status == "2") {
        alert("该流程已被处理过了!");
        return;
    }

	jQuery(cfg.grid_selector).jqGrid('editGridRow',rowid,{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : true,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />');

							}
						});
}

var params={};
function setParams(key, value) {
	params[key] = value;
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		postData: params
	}).trigger("reloadGrid");
}
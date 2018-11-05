

function preview(id, title) {
    window.open(contextPath + '/dynamic/service/company/view.jsp?companyId=' + id);
}
function appendButtons() {
    appendMapBtn("address");
    appendUploadBtn("companyLogo");
}
/**
 * 1、添加地图位置的指针*/
function appendMapBtn(id) {
    var html = new Array();
    html.push("<a id='btn-map-add-"
        + id
        + "' class='ace-icon fa fa-location-arrow bigger-110' href='javascript:false'>选取</a>");
    $("#" + id).after(html.join(''));
    $('#btn-map-add-' + id).on('click', function () {
        window.open(portalPath + "/dynamic/common/map.jsp");
    });
}
/**
 * 2、自动填写地址
 * @param latitude
 */
function latitude(latitude) {
    $("#latitude").val(latitude);
}
function longitude(longitude) {
    $("#longitude").val(longitude);
}
function addr(addr) {
    $("#address").val(addr);
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
										'<div class="widget-header" />');
										  appendButtons();

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
var _colNames = [];
var _colModel = []; 

function aceSwitch(cellvalue, options, cell) {
	console.log('aceSwitch');
	setTimeout(function() {
		$(cell).find('input[type=checkbox]').addClass(
				'ace ace-switch ace-switch-5').after(
				'<span class="lbl"></span>');
	}, 0);
}
// enable datepicker
function pickDate(cellvalue, options, cell) {
	setTimeout(function() {
		$(cell).find('input[type=text]').datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true
		});
	}, 0);
}

function renderBtn(cur) {
	var id=$.jgrid.getAccessor(cur, 'id');
	var html=[];
	html.push('<div title="查看" style="float:left;cursor:pointer;" ');
	html.push('class="ui-pg-div ui-inline-edit" ');
	html.push(' onclick="preview(\''+id+'\')" ');
	html.push('onmouseover="jQuery(this).addClass("ui-state-hover");" ');
	html.push('onmouseout="jQuery(this).removeClass("ui-state-hover")" ');
	html.push(' data-original-title="">');
	html.push('<span class="ui-icon ace-icon fa fa-eye"></span>');
	html.push('</div>');
	return html.join(' ');
}
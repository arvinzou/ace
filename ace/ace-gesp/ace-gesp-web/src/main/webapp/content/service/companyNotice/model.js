var _colNames = [ '公告编号', '标题','状态', '发布者', '是否置顶','发布时间','公告类型' ,'有效日期'];
var _colModel = function() {
	return [
			{
				name : 'noticeId',
				width : 100,
				hidden : true
			},
			{
				name : 'title',
				width : 300
			},
			{
				name : 'status',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "22");
				},
				editoptions : {
					value : odparse("22")
				}
			},{
				name : 'name',
				width : 60
			},{
				name : 'top',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "23");
				},
				editoptions : {
					value : odparse("23")
				}
			}, {
				name : 'publishTime',
				width : 120,
				sortable : true,
				editable : false
			}, {
				name : 'category',
				width : 120,
				sortable : true,
				editable : false,
				renderer : function(value) {
					return rsd(value, "21");
				}
			}, {
				name : 'deadline',
				width : 120,
				sortable : true,
				editable : false
			} ];
}
function aceSwitch(cellvalue, options, cell) {
	//console.log('aceSwitch');
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
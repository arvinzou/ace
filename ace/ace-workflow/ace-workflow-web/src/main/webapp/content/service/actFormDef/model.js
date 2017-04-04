var _colNames = [ '序号', '类型', '名称', '描述','创建日期'];
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 100,
				editable : true,
				hidden:true,
				edittype : "combogrid",
				sorttype : "int",
				dataoptions : {
					panelWidth : 400,
					idField : 'code',
					textField : 'name',
					url : contextPath + '/actFormDef/selectFormDefList.do',
					mode : 'remote',
					fitColumns : true,
					method : 'get',
					columns : [ [ {
						field : 'name',
						title : '名称',
						width : 400
					}] ]
				},
				editoptions : {
					style : 'height:25px;width:250px'
				}
			},
			{
				name : 'category',
				width : 100,
				editable : true,
				edittype : "combotree",
				dataoptions : {
					url : contextPath + '/actFormCategory/selectTreeList.do',
					animate: true,
	                lines:false
				},
				editoptions : {
					style:'width:250px;line-height: 25px;height: 25px;'
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'categoryName');
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'name',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50",
					style:'width:250px;line-height: 25px;height: 25px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'discribtion',
				width : 100,
				editable : true,
				edittype:"textarea",
				editoptions : {
					size : "20",
					maxlength : "50",
					rows:"4",cols:"50"
					
				}
			},
			{
				name : 'createDate',
				width : 100,
				editable : false
			}

	];
}
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
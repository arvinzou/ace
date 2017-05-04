var _colNames = ['编号', '父编号', '类型', '编码', '名称', '拼音码', '创建时间', '备注'];
var _colModel = function() {
	return [
			{
				name : 'id',
				index : 'id',
				width : 100,
				sortable : false,
				editable : true,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'pcode',
				index : 'pcode',
				width : 100,
				sortable : false,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readonly : true
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : false
				}
			},
			{
				name : 'categoryId',
				index : 'categoryId',
				width : 100,
				editable : true,
				edittype : "combobox",
				dataoptions : {
					url : contextPath
							+ '/dictCategory/findDictCategoryListAll.do',
					method : 'get',
					valueField : 'categoryId',
					textField : 'name'
				},
				editoptions : {
					style : 'width:176px;line-height: 25px;height: 25px;'
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'categoryName');
				}
			},
			{
				name : 'code',
				index : 'code',
				width : 100,
				sortable : false,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
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
				index : 'name',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "30"
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
				name : 'spell',
				index : 'spell',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "30"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			}, {
				name : 'createTime',
				width : 150,
				sortable : true,
				editable : false
			}, {
				name : 'remark',
				index : 'remark',
				width : 150,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "30"
				}
			}];
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
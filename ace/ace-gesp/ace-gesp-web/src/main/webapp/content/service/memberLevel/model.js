var _colNames = [ 'ID', '会员级别名称', '所属协会', '备注', '创建时间', '创建人',
		'最后修改时间',  '最后修改用户名' ];
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 80,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readOnly:true
				}
			},{
				name : 'name',
				width : 80,
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
				name : 'departmentName',
				width : 100,
				editable : false,
				sortable : false,
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
			}, {
				name : 'remark',
				hidden:true,
				width : 120,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'createDate',
				width : 100,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'createUserName',
				width : 80,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'lastModifyDate',
				width : 80,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'lastModifyUserName',
				width : 80,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
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
var _colNames = ['主键', '名称', '级别','包含路段数', '简介', '状态 ', '创建人编号', '创建人姓名', '创建日期', '更新人编号', '更新人名称', '更新日期', '操作'];
var _colModel = function() {
	return [{
			name: 'id',
			editable: false,
			width: 100,
			hidden:true,
			formoptions: {
				elmprefix: "",
				elmsuffix: "<span style='color:red;'>*</span>"
			},
			editrules: {
				required: true
			}
		}, {
			name: 'name',
			editable: true,
			width: 200,
			editoptions: {
				style:'width:95%',
				maxlength: "50"
			},
			formoptions: {
				elmprefix: "",
				elmsuffix: "<span style='color:red;'>*</span>"
			},
			editrules: {
				required: true
			}
		}, {
			name: 'category',
			editable: true,
			width: 50,
			edittype: "select",
			renderer: function(value) {
				return rsd(value, "170");
			},
			editoptions: {
				value: odparse("170"),
				style:'width:95%'
			},

			formoptions: {
				elmprefix: "",
				elmsuffix: "<span style='color:red;'>*</span>"
			},
			editrules: {
				required: true
			}
		}, {
         			name: 'sectionCount',
         			hidden:false,
         			editable: false,
         			width: 60
         		}, {
			name: 'intro',
			hidden:true,
			editable: true,
			width: 100,
			edittype: "textarea",
			editoptions: {
				rows: "8",
				style:'width:95%'
			}
		}, {
			name: 'status',
			editable: true,
			width: 50,
			edittype: "checkbox",
			editoptions: {
				value: "1:0"
			},
			unformat: aceSwitch,
			renderer: function(value) {
				var rst = "";
				switch (value) {
					case '1':
						rst = "ON";
						break;
					case '0':
						rst = "OFF";
						break;
					default:
						rst = "N/A";
				}
				return rst;
			},

			formoptions: {
				elmprefix: "",
				elmsuffix: ""
			},
			editrules: {
				required: false
			}
		}, {
			name: 'createUserId',
			hidden: true,
			editable: false,
			width: 100
		}, {
			name: 'createUserName',
			hidden: true,
			editable: false,
			width: 100
		}, {
			name: 'createDate',
			hidden: true,
			editable: false,
			width: 100
		}, {
			name: 'lastModifyUserId',
			hidden: true,
			editable: false,
			width: 100
		}, {
			name: 'lastModifyUserName',
			hidden: true,
			editable: false,
			width: 100
		}, {
			name: 'lastModifyDate',
			hidden: true,
			editable: false,
			width: 100
		},
		{
			name: 'opt',
			width: 60,
			hidden: false,
			editable: false,
			sortable: false,
			renderer: function(value, cur) {
				return renderBtn(cur);
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
			format: 'yyyy-mm-dd',
			autoclose: true
		});
	}, 0);
}

function renderBtn(cur) {
	var rowid = $.jgrid.getAccessor(cur, 'id');
	var title = $.jgrid.getAccessor(cur, 'name');

	var opt = [];
	if (authorConfig.hasOwnProperty(cfg.grid_edit_data_url)) {
		opt.push('<a href="javascript:edit(\'' + rowid + '\')">编辑</a> ');
	}
	if (authorConfig.hasOwnProperty(cfg.grid_delete_data_url)) {
		opt.push('<a href="javascript:del(\'' + rowid + '\')">删除</a>  ');
	}

	opt.push('<a href="#" data-toggle="modal" data-id="' + rowid + '" data-title="' + title + '" ' +
		'data-target="#modal-preview">查看</a> ');


		opt.push('<a href="javascript:importXls(\'' + rowid + '\')">路段导入</a>  ');

	return opt.join(' ');
}

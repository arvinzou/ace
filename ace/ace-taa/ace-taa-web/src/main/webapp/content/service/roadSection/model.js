var _colNames = ['主键', '所属道路',  '名称', '归属路长', '行政区划','路段开始', '路段截止', '标号开始', '标号截止', '简介', '状态 ', '创建人编号', '创建人姓名',
	'创建日期', '更新人编号', '更新人名称', '更新日期', '操作'
];
var _colModel = function() {
	return [{
		name: 'id',
		editable: false,
		hidden: true,
		width: 100,
		editoptions: {

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
		name: 'roadId',
		editable: true,
		hidden: true,
		width: 100,
		edittype: "combogrid",
		dataoptions: {
			panelWidth: 500,
			idField: 'id',
			textField: 'name',
			url: contextPath + '/road/getListByCondition',
			mode: 'remote',
			fitColumns: true,
			method: 'get',
			columns: [
				[ {
					field: 'name',
					title: '名称',
					width: 300
				}]
			]
		},
		editoptions: {
			style: 'height:34px;width:435px'
		},
		renderer: function(value, cur) {
			return $.jgrid.getAccessor(cur, 'roadManName');
		},
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
		width: 100,
		editoptions: {

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
       		name: 'roadMan',
       		editable: true,
       		width: 60,
       		edittype: "combogrid",
       		dataoptions: {
       			panelWidth: 500,
       			idField: 'id',
       			textField: 'name',
       			url: contextPath + '/roadMan/getListByCondition',
       			mode: 'remote',
       			fitColumns: true,
       			method: 'get',
       			columns: [
       				[ {
       					field: 'name',
       					title: '姓名',
       					width: 100
       				}, {
       					field: 'orgName',
       					title: '单位',
       					width: 100
       				}, {
       					field: 'areaName',
       					title: '县区',
       					width: 100
       				}]
       			]
       		},
       		editoptions: {
       			style: 'height:34px;width:435px'
       		},
       		renderer: function(value, cur) {
       			return $.jgrid.getAccessor(cur, 'roadManName');
       		},
       		formoptions: {
       			elmprefix: "",
       			elmsuffix: "<span style='color:red;'>*</span>"
       		},
       		editrules: {
       			required: true
       		}
       	},{
     		name: 'areaCode',
     		editable: true,
     		hidden: false,
     		width: 60,
     		edittype: "combotree",
     		editoptions: {
     			style: 'width:435px;line-height: 1.42857;height: 34px;'
     		},
     		dataoptions: {
     			url: portalPath + '/system/selectProvinceTreeList.do',
     			required: false
     		},
     		renderer: function(value, cur) {
     			return $.jgrid.getAccessor(cur, 'areaName');
     		},
     		formoptions: {
     			elmprefix: "",
     			elmsuffix: "<span style='color:red;'>*</span>"
     		},
     		editrules: {
     			required: true
     		}
     	}, {
		name: 'startName',
		editable: true,
		width: 80,
		editoptions: {

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
		name: 'endName',
		editable: true,
		width: 80,
		editoptions: {

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
		name: 'startNo',
		editable: true,
		width: 60,
		editoptions: {

			maxlength: "50"
		},
	}, {
		name: 'endNo',
		editable: true,
		width: 60,
		editoptions: {

			maxlength: "50"
		},
	}, {
		name: 'intro',
		editable: false,
		hidden: true,
		width: 100,
		editoptions: {

			maxlength: "50"
		},
	}, {
		name: 'status',
		editable: true,
		width: 50,
		edittype: "checkbox",
		editoptions: {
			value: "1:0"
		},
		cellattr: function(rowId, val, rawObject, cm, rdata) {

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
			elmsuffix: "<span style='color:red;'>*</span>"
		},
		editrules: {
			required: true
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
	}, {
		name: 'opt',
		width: 100,
		hidden: false,
		editable: false,
		sortable: false,
		renderer: function(value, cur) {
			return renderBtn(cur);
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

	opt.push('<a href="javascript:previewMap(\'' + rowid + '\')">查看</a>  ');

	return opt.join(' ');
}

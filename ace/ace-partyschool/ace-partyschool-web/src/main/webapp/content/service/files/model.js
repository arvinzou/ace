var _colNames = ['主键', '类型', '文件名称', '班级', '文件地址', '发布时间 ', '发布人', '状态', '操作'];
var _colModel = function() {
	return [{
		name: 'id',
		editable: false,
		width: 100,
		hidden: true,
		editoptions: {
			size: "20",
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
		editable: false,
		width: 100,
		hidden: true,
		editoptions: {
			size: "20",
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
		name: 'title',
		editable: true,
		width: 150,
		renderer: function(value,cur) {
			var url = $.jgrid.getAccessor(cur, 'url');
			return "<a href='"+url+"' target=_blank''>"+value+"</a>"
		},
		editoptions: {
			size: "20",
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
		name: 'classesId',
		editable: true,
		hidden: true,
		edittype: "combogrid",
		width: 150,
		editoptions: {
			style: 'height: 35px;',
			size: "20",
			maxlength: "50"
		},
		dataoptions: {
			panelWidth: 300,
			idField: 'id',
			textField: 'name',
			url: contextPath + '/classes/findClassesList',
			mode: 'remote',
			fitColumns: true,
			method: 'get',
			pageSize: 100,
			columns: [
				[{
					field: 'name',
					title: "--请搜索班级--",
					width: 50
				}]
			]
		},
		formoptions: {
			elmprefix: "",
			elmsuffix: "<span style='color:red;'>*</span>"
		},
		editrules: {
			required: true
		}
	}, {
		name: 'url',
		editable: true,
		width: 100,
		hidden: true,
		editoptions: {
			readonly: true,
			size: "20",
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
		name: 'pushDate',
		editable: false,
		width: 100,
		edittype: "datebox",
		dataoptions: {
			formatter: function(date) {
				var y = date.getFullYear();
				var m = date.getMonth() + 1;
				var d = date.getDate();
				return y + '-' + (m < 10 ? ('0' + m) : m) + '-' +
					(d < 10 ? ('0' + d) : d);
			},
			parser: function(s) {
				if (!s)
					return new Date();
				var ss = (s.split('-'));
				var y = parseInt(ss[0], 10);
				var m = parseInt(ss[1], 10);
				var d = parseInt(ss[2], 10);
				if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
					return new Date(y, m - 1, d);
				} else {
					return new Date();
				}
			}
		},
		editoptions: {
			size: "20",
			maxlength: "50",
		},
		formoptions: {
			elmprefix: "",
			elmsuffix: "<span style='color:red;'>*</span>"
		},
		editrules: {
			required: true
		}

	}, {
		name: 'publisher',
		editable: false,
		width: 100,
		editoptions: {
			size: "20",
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
		name: 'status',
		editable: false,
		hidden: true,
		width: 50,
		edittype: "checkbox",
		editoptions: {
			value: "1:0"
		},
		cellattr: function(rowId, val, rawObject, cm, rdata) {
			if (rawObject.status == '0') {
				return "style='color:red;'";
			}
			if (rawObject.status == '1') {
				return "style='color:green;'";
			}
		},
		unformat: aceSwitch,
		renderer: function(value) {
			var rst = "";
			switch (value) {
				case '1':
					rst = "正常";
					break;
				case '0':
					rst = "注销";
					break;
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
		name: 'opt',
		width: 30,
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
	var title = $.jgrid.getAccessor(cur, 'title');

	var opt = [];
	opt.push('<a href="javascript:del(\'' + rowid + '\')">删除</a>  ');


	return opt.join(' ');
}

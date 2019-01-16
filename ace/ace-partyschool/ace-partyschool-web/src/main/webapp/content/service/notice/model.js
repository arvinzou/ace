var _colNames = ['主键', '类型', '班级', '标题', '文件地址', '内容', '发布时间 ', '发布人', '状态', '操作'];
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
			elmsuffix: "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules: {
			required: true
		}
	}, {
		name: 'category',
		editable: true,
		hidden: true,
		width: 50,
		editoptions: {
			size: "20",
			maxlength: "50"
		},
		formoptions: {
			elmprefix: "",
			elmsuffix: "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules: {
			required: true
		}
	}, {
		name: 'classesId',
		editable: true,
		hidden: true,
		edittype: "combogrid",
		width: 50,
		editoptions: {
			style: "width:847px",
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
			elmsuffix: "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules: {
			required: true
		}
	}, {
		name: 'title',
		editable: true,
		width: 250,
		editoptions: {
			size: "20",
			maxlength: "50"
		},
		formoptions: {
			elmprefix: "",
			elmsuffix: "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules: {
			required: true
		}
	}, {
		name: 'fileUrl',
		editable: true,
		width: 100,
		hidden: true,
		editoptions: {
			size: "20",
			maxlength: "50"
		},
		formoptions: {
			elmprefix: "",
			elmsuffix: "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules: {
			required: true
		}
	}, {
		name: 'content',
		editable: true,
		hidden: true,
		width: 100,
		edittype: "textarea",
		editoptions: {
			colspan: true,
			style: 'width:750px;line-height: 25px;height: 100px;'
		},
		formoptions: {
			elmprefix: "",
			elmsuffix: "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules: {
			required: true
		}
	}, {
		name: 'pushDate',
		editable: true,
		width: 100,
		hidden: true,
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
			elmsuffix: "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules: {
			required: true
		}

	}, {
		name: 'publisher',
		editable: true,
		width: 100,
		hidden: true,
		editoptions: {
			size: "20",
			maxlength: "50"
		},
		formoptions: {
			elmprefix: "",
			elmsuffix: "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules: {
			required: true
		}
	}, {
		name: 'status',
		editable: true,
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
			elmsuffix: "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules: {
			required: true
		}
	}, {
		name: 'opt',
		width: 50,
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
	opt.push('<a href="javascript:edit(\'' + rowid + '\')">编辑</a> ');
	opt.push('<a href="javascript:del(\'' + rowid + '\')">删除</a>  ');
	opt.push('<a href="#" data-toggle="modal" data-id="' + rowid + '" data-title="' + title + '" ' +
		'data-target="#modal-preview">查看</a>');

	return opt.join(' ');
}

var _colNames = ['主键', '诉求名称', '封面', '二维码', '处理人', '处理通知模板', '答复通知模板', '状态', '诉求简介', '创建人编号', '创建人姓名', '入库日期',
	'最后更新人编码',
	'最后更新人姓名', '最后更新时间', '操作'
];
var _colModel = function() {
	return [{
			name: 'id',
			editable: false,
			hidden: true,
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
			name: 'name',
			editable: true,
			width: 200,
			editoptions: {
				style: "width:400px",
				colspan: true,
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
			name: 'cover',
			editable: true,
			hidden: true,
			width: 100,
			editoptions: {
				style: "width:400px",
				colspan: true
			},
			formoptions: {
				elmprefix: "",
				elmsuffix: "<span style='color:red;'>*</span>"
			},
			editrules: {
				required: true
			}
		}, {
			name: 'qrcoteUrl',
			hidden: true,
			editable: true,
			width: 100,
			editoptions: {
				style: "width:400px",
				colspan: true
			},
			formoptions: {
				elmprefix: "",
				elmsuffix: "<span style='color:red;'>*</span>"
			},
			editrules: {
				required: true
			}
		}, {
			name: 'openId',
			editable: true,
			width: 100,
			edittype: "combogrid",
			dataoptions: {
				panelWidth: 400,
				idField: 'openid',
				textField: 'nickname',
				url: portalPath + '/system/selectUserinfo.do',
				mode: 'remote',
				fitColumns: true,
				method: 'get',
				columns: [
					[{
						field: 'openid',
						title: 'OPENID',
						width: 200
					}, {
						field: 'nickname',
						title: '昵称',
						width: 100
					}]
				]
			},
			editoptions: {
				style: 'height:25px;width:400px',
				colspan: true
			},
			renderer: function(value, cur) {
				return $.jgrid.getAccessor(cur, 'nickname');
			},
			formoptions: {
				elmprefix: "",
				elmsuffix: "<span style='color:red;'>*</span>"
			},
			editrules: {
				required: true
			}
		},
		{
			name: 'tplCode',
			width: 100,
			editable: true,
			hidden: true,
			edittype: "select",
			renderer: function(value) {
				return rsd(value, "136");
			},
			editoptions: {
				value: odparse("136"),
				colspan: true
			},
			formoptions: {
				elmprefix: "",
				elmsuffix: "<span style='color:red;'>*</span>"
			},
			editrules: {
				required: true
			}
		}, {
			name: 'answerTplCode',
			width: 100,
			editable: true,
			hidden: true,
			edittype: "select",
			renderer: function(value) {
				return rsd(value, "136");
			},
			editoptions: {
				value: odparse("136"),
				colspan: true
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
			editable: true,
			width: 50,
			hidden:true,
			edittype: "checkbox",
			editoptions: {
				value: "1:0"
			},
			cellattr: function(rowId, val, rawObject, cm, rdata) {
				if (rawObject.status == '0') {
					return "style='background:red;color:#fff'";
				}
				if (rawObject.status == '1') {
					return "style='background:green;color:#fff'";
				}
				if (rawObject.status == '2') {
					return "style='background:green;color:#fff'";
				}
				if (rawObject.status == '3') {
					return "style='background:#F9F900;color:#000000'";
				}
				if (rawObject.status == '4') {
					return "style='background:#FF9224;color:#000000'";
				}
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
			name: 'remark',
			hidden: true,
			edittype: 'textarea',
			editable: true,
			width: 100,
			editoptions: {
				colspan: true,
				rows: "10",
				cols: "63"
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
			width: 50,
			renderer: function(value, cur) {
				var rowid = $.jgrid.getAccessor(cur, cfg.dataId);
				var opt = [];
				if (authorConfig.hasOwnProperty(cfg.grid_edit_data_url)) {
					opt.push('<a href="javascript:edit(\'' + rowid + '\')">编辑</a> ');
				}
				if (authorConfig.hasOwnProperty(cfg.grid_delete_data_url)) {
					opt.push('<a href="javascript:del(\'' + rowid + '\')">删除</a>');
				}

				return opt.join('');
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

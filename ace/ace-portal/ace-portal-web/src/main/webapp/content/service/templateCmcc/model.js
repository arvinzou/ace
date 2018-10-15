var _colNames = ['编号', '名称', '模板', '创建时间', '操作'];
var _colModel = function() {
	return [{
		name: 'templateCmccId',
		index: 'templateCmccId',
		width: 150,
		sortable: false,
		editable: true,
		editoptions: {
			readonly: false
		}
	}, {
		name: 'name',
		index: 'name',
		width: 130,
		editable: true,
		editoptions: {
			maxlength: "30"
		}
	}, {
		name: 'content',
		index: 'content',
		width: 350,
		editable: true,
		edittype: "textarea",
		editoptions: {
			rows: "6",
			cols: "10",
			maxlength: "200",
			style:'width:100%'
		}
	}, {
		name: 'createTime',
		width: 150,
		sortable: true,
		editable: false

	}, {
		name: 'opt',
		sortable: false,
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
	}];
}

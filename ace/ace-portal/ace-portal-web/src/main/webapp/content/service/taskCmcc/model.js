var _colNames = ['编号', '发送状态', '任务名称', '短信内容', '创建时间', '发送人', '操作'];
var _colModel = function() {
	return [{
		name: 'taskId',
		index: 'taskId',
		width: 100,
		sortable: false,
		editable: true,
		editoptions: {
			readonly: true
		}
	}, {
		name: 'status',
		index: 'status',
		width: 60,
		sortable: false,
		editable: false,
		renderer: function(value) {
			var rst = "";
			switch (value) {
				case '1':
					rst = "YES";
					break;
				case '0':
					rst = "NO";
					break;
				default:
					rst = "N/A";
			}
			return rst;
		}
	}, {
		name: 'taskName',
		index: 'taskName',
		width: 150,
		editable: true,
		editoptions: {
			size: "20",
			maxlength: "30"
		}
	}, {
		name: 'msg',
		index: 'msg',
		width: 300,
		editable: true,
		editoptions: {
			size: "20",
			maxlength: "30"
		}
	}, {
		name: 'createTime',
		width: 150,
		sortable: true,
		editable: false
	}, {
		name: 'name',
		index: 'name',
		width: 80,
		editable: true,
		editoptions: {
			size: "20",
			maxlength: "30"
		}
	}, {
		name: 'opt',
		sortable: false,
		width: 100,
		renderer: function(value, cur) {
			var rowid = $.jgrid.getAccessor(cur, cfg.dataId);
			var opt = [];
			if (authorConfig.hasOwnProperty(cfg.grid_delete_data_url)) {
				opt.push('<a href="javascript:del(\'' + rowid + '\')">删除</a>  ');
			}
			opt.push('<a href="javascript:preview(\'' + rowid + '\')">查看</a>  ');
			opt.push('<a href="javascript:resend(\'' + rowid + '\')">重发送</a>');

			return opt.join('');
		}
	}];
}

jQuery(function($) {
	$("#btn-add").on('click', function(e) {
		e.preventDefault();
		selectMobile();
	});
	$('#combogrid-tmp').combogrid({
		onSelect: function(index, row) {
			selectMobile();
		}
	});
	$("#btn-view-save").on('click', function(e) {
		e.preventDefault();
		save();
	});
	$("#btn-view-add").on('click', function(e) {
		e.preventDefault();
		$('#modal-tree').modal('show');
	});
	$("#btn-view-remove").on('click', function(e) {
		e.preventDefault();
		$('#task-content').html('');
	});
	$("#btn-view-remove-last").on('click', function(e) {
		$('#task-content').find('div:last').remove();
	});
	$("#btn-remove").on('click', function(e) {
		e.preventDefault();
		$('#task-content-view').html('');
	});
	$("#btn-remove-last").on('click', function(e) {
		$('#task-content-view').find('div:last').remove();
	});
	$("#btn-view-submit").on('click', function(e) {
		e.preventDefault();
		send();
	});
});

jQuery(function($) {
	$('#tree-dept').tree({
		checkbox: true,
		url: '/uf/personage/selectPersonageCheckTreeList.do',
		onBeforeExpand: function(node, param) {

		},
		onClick: function(node) {

		}
	});

	$('#tree-free').tree({
		checkbox: true,
		url: portalPath + '/group/selectFreeGroupTreeRoot.do',
		onBeforeExpand: function(node, param) {
			$('#tree-free').tree('options').url = portalPath + '/group/selectFreeGroupTreeByPid.do?pid=' +
				node.id;
		},
		onClick: function(node) {

		}
	});

	$('#combogrid-tmp').combogrid({
		panelWidth: 530,
		idField: 'id',
		textField: 'name',
		url: '/uf/personage/selectPersonage.do',
		mode: 'remote',
		fitColumns: false,
		method: 'get',
		columns: [
			[{
					field: 'name',
					title: '姓名',
					width: 100
				},
				{
					field: 'mobile',
					title: '手机号',
					width: 150,
					align: 'right'
				},
				{
					field: 'deptName',
					title: '单位',
					width: 250,
					align: 'right'
				}
			]
		],
		keyHandler: {

			up: function() {},

			down: function() {},

			enter: function() {},

			query: function(q) {
				$('#combogrid-tmp').combogrid("grid").datagrid("reload", {
					'q': q
				});
				$('#combogrid-tmp').combogrid("setValue", q);
			}

		}
	});
});


function save() {
	var html = new Array();
	var nodes = $('#tree-dept').tree('getChecked');
	$.each($(nodes), function(i, o) {
		if (o.href != '') {
			html.push('<div class="layout-user" >');
			html.push('<user tel="' + o.href + '" class="badge badge-' + cssColor9[0] + '">');
			html.push(o.text);
			html.push('</user>');
			html.push('</div>');

		}
	});
	nodes = $('#tree-free').tree('getChecked');
	$.each($(nodes), function(i, o) {
		if (o.href != '') {
			html.push('<div class="layout-user" >');
			html.push('<user tel="' + o.href + '" class="badge badge-' + cssColor9[0] + '">');
			html.push(o.text);
			html.push('</user>');
			html.push('</div>');
		}
	});
	$('#task-content').html(html.join('') + $('#task-content-view').html());
	$('#task-content-view').html('');
}
function selectMobile() {
	var html = new Array();
	var g = $('#combogrid-tmp').combogrid('grid'); // get datagrid object
	var r = g.datagrid('getSelected'); // get the selected row
	var isExit = false;
	if (r && r.mobile) {
		$.each($('user'), function(i, obj) {
			if ($(obj).attr("tel") == r.mobile) {
				alert("重复的手机号。");
				isExit = true;
				return;
			}
		});
		html.push('<div class="layout-user" >');
		html.push('<user tel="' + r.mobile + '" class="badge badge-' + cssColor9[0] + '">');
		html.push(r.name);
		html.push('</user>');
		html.push('</div>');
		if (!isExit) {
			$('#task-content-view').html($('#task-content-view').html() + html.join(''));
		}

	} else {
		alert("请选择人员且手机号不能为空。");
	}
}

function send() {
	var tel = new Array();
	$.each($('user'), function(i, obj) {
		tel.push($(obj).attr("tel") + "," + $(obj).html());
	});
	var telext = $('#telext').val();
	if (telext != '') {
		var reg = /^1\d{10}(;1\d{10})*$/;
		var r = telext.match(reg);
		if (r == null) {
			alert('对不起，多个手机号用;隔开,请注意英文分号!');
			return;
		}

		var o = telext.split(';');
		for (var i = 0; i < o.length; i++) {
			tel.push(o[i] + ",临时");
		}
	}
	var taskCmcc = {};
	taskCmcc['msg'] = $('#msg').val();
	taskCmcc['taskName'] = $('#taskName').val();
	taskCmcc['tel'] = tel.join(';');
	var json = JSON.stringify(taskCmcc);
	$.ajax({
		type: "post",
		url: contextPath + "/taskCmcc/insertTaskCmcc.do",
		data: {
			jsons: json
		},
		beforeSend: function(XMLHttpRequest) {
			startLoad();
		},
		success: function(rst, textStatus) {
			if (rst) {
				alert(rst.errorMessage);
			}
		},
		complete: function(XMLHttpRequest, textStatus) {
			stopLoad();
		},
		error: function() {
			stopLoad();
		}
	});
}

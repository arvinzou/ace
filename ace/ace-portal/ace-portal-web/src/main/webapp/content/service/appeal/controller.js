jQuery(function($) {
	$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit: function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
				});
				$.extend(params, {
					time: new Date()
				});
				// console.log(params);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page: 1,
					postData: params
				}).trigger("reloadGrid");
				return false;
			}
		});
	});

	$('#btn-view-add').on(
		'click',
		function() {
			location.href="add/index.jsp?id="+urlParams.id;
		});

});



function edit(rowid) {
	location.href="edit/index.jsp?id="+urlParams.id+"&did="+rowid;
}
var show = false;

function del(rowid) {
	console.log(rowid);
	jQuery(cfg.grid_selector).jqGrid('delGridRow',
		rowid, {
			beforeShowForm: function(e) {
				var form = $(e[0]);
				if (!show) {
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
				}

				show = true;

			}
		});
}

function setParams(key, value) {
	params[key] = value;
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		postData: params
	}).trigger("reloadGrid");
}

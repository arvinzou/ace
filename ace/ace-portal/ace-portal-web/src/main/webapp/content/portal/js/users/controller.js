var userId;
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
 				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
 					page: 1,
 					postData: params
 				}).trigger("reloadGrid");
 				return false;
 			}
 		});
 	});
 	$("#btn-view-save").on('click', function() {
 		insertUserRole();
 	});
 	$("#btn-view-passwd").on('click', function() {
 		resetPasswd();
 	});
 	$('#btn-view-add').on(
 		'click',
 		function() {
 			jQuery(cfg.grid_selector).jqGrid(
 				'editGridRow',
 				'new', {
 					closeAfterAdd: true,
 					recreateForm: true,
 					viewPagerButtons: false,
 					beforeShowForm: function(e) {

 					}
 				})
 		});

 	$("#btn-view-initpwd").on('click', function(e) {
 		e.preventDefault();
 	});
 	$("#btn-view-da-add").on('click', function(e) {
 		var gr = jQuery('#allrole-grid-table').jqGrid('getGridParam', 'selrow');
 		if (!gr) {
 			alert("请选择要分配的角色");
 			return;
 		}
 		var srows = jQuery('#allrole-grid-table').jqGrid('getGridParam', 'selarrrow');
 		var rowIds = jQuery("#myrole-grid-table").jqGrid('getDataIDs');
 		var repeat = false,
 			rpn = [];
 		$.each(srows, function(i, o) {
 			var rd = jQuery('#allrole-grid-table').jqGrid('getRowData', o);
 			if ($.inArray(o, rowIds) > -1) {
 				repeat = true, rpn.push(rd.roleName);
 			} else {
 				jQuery("#myrole-grid-table").jqGrid('addRowData', o, rd);
 			}
 		});
 		if (repeat) {
 			alert("重复的角色");
 		}

 	});
 	$("#btn-view-da-del").on('click', function(e) {
 		var gr = jQuery('#myrole-grid-table').jqGrid('getGridParam', 'selrow');
 		if (!gr) {
 			alert("请选择要移除的角色");
 			return;
 		}
 		var srows = jQuery('#myrole-grid-table').jqGrid('getGridParam', 'selarrrow');
 		$.each(srows, function(i, o) {
 			jQuery("#myrole-grid-table").jqGrid('delRowData', o);
 		});
 		$.each(srows, function(i, o) {
 			jQuery("#myrole-grid-table").jqGrid('delRowData', o);
 		});
 	});

 });
 function role(id) {
 	userId = id;
 	$('#modal-role').modal('show');
 	jQuery('#myrole-grid-table').jqGrid('setGridParam', {
 		url: contextPath + "/users/selectRoleListByUserId.do",
 		postData: {
 			userId: userId
 		}
 	}).trigger("reloadGrid");

 }
 function passwd(id) {
 	userId = id;
 	$('#modal-passwd').modal('show');
 }
 function edit(rowid) {
 	console.log(rowid);
 	jQuery(cfg.grid_selector).jqGrid(
 		'editGridRow',
 		rowid, {
 			closeAfterAdd: true,
 			recreateForm: true,
 			viewPagerButtons: true,
 			beforeShowForm: function(e) {
 				var form = $(e[0]);
 				form.closest('.ui-jqdialog').find(
 					'.ui-jqdialog-titlebar').wrapInner(
 					'<div class="widget-header" />')

 			}
 		});
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
 function insertUserRole() {
 	var rowIds = jQuery("#myrole-grid-table").jqGrid('getDataIDs');
 	if (rowIds.length < 1) {
 		alert("知识分配一下角色。");
 		return;
 	}
 	var s = '';
 	if (rowIds) {
 		s = rowIds.join(',')
 	}
 	$.ajax({
 		type: "post",
 		url: contextPath + "/users/insertUsersRole.do",
 		data: {
 			userId: userId,
 			roleId: s
 		},
 		beforeSend: function(XMLHttpRequest) {
 			startLoad();
 		},
 		success: function(rst, textStatus) {
 			$('#modal-role').modal('hide');
 			if (rst) {
 				alert(rst.errorMessage);
 			}
 		},
 		complete: function(XMLHttpRequest, textStatus) {
 			stopLoad();
 		},
 		error: function() {
 			stopLoad();
 			alert("对不起出错了。");
 		}
 	});
 }

 function resetPasswd() {
 	$.ajax({
 		type: "post",
 		url: contextPath + "/users/updateUsersForInitPassword.do",
 		data: {
 			userId: userId,
 			password: $('#init_passwd').val()
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
 			alert("对不起出错了。");
 		}
 	});
 }

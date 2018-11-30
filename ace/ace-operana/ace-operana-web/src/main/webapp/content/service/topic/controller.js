jQuery(function($) {
	$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
				});
				$.extend(params, {
					time : new Date()
				});
				// console.log(params);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1,
					postData : params
				}).trigger("reloadGrid");
				return false;
			}
		});
	});

	$('#btn-view-add').on(
			'click',
			function() {
				jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						'new',
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : false,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
							}
						})
			});

});

function preview(id, title) {
    $('#modal-norm').modal('show');
	viewNorm(id);
}
function loadView(id) {
	$.ajax({
		type : "post",
		url : cfg.view_load_data_url,
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			$.each(rst.value, function(key, value) {
				if (key == 'category') {
					value = rsd(value, '90');
				}
				if (key == 'status') {
					value = value == "1" ? "on" : "off";
				}
				if (key.indexOf('Date') != -1 || key.indexOf('time') != -1
						|| key.indexOf('Time') != -1
						|| key.indexOf('birthday') != -1) {
					value = Common.DateFormatter(value);
				}
				$("#dialog-message-view").find('#' + key).html(value);
			});
		},
		error : function() {
			alert("加载错误！");
		}
	});
}

function normCfg(id, title) {
$('#modal-norm-cfg').modal('show');
	selectAllNorm(id);
}

function selectAllNorm(id, name) {
	$.ajax({
		type : "post",
		url : contextPath + "/norm/selectAllNorm.do",
		data : {
			topicId : id,
			name : name
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var defaultValue = '';
			if (name) {
				defaultValue = name;
			}
			var html = [];
			html.push("<form id='fm-norm-cfg' onsubmit='return ckform()'>");
            html.push("<div style='text-align:right;padding-bottom:2px'>");
			html.push("搜索 <input type='text' id='_q' name='_q' value='"
					+ defaultValue + "' />");
			html.push("</div>");
			html.push("<div>");
			html.push("<div>");
			html.push("<input type='hidden' name='topicId' value='" + id);
			html.push("'/>");
			html.push("<table>");
			$.each(rst, function(i, o) {
				html.push("<tr><td>");
				html.push("<input type='");
				html.push("checkbox' id='");
				html.push(o.category.code);
				html.push("' class='xcheckgroup");
				html.push(o.category.code);
				html.push(" checkAllCurrent' /> ");
				html.push("<label style='");
				html.push("font-weight:800' for='");
				html.push(o.category.code);
				html.push("'>");
				html.push(o.category.name);
				html.push("</label>");
				html.push("</td></tr>");
				html.push("<tr><td>");
				$.each(o.items, function(j, e) {
					var checked = "";
					if (e.value) {
						checked = "checked";
					}
					html.push("<div class='checkboxitem'>");
					html
							.push("<input type='checkbox' name='" + e.id
									+ "' id='");
					html.push(e.id);
					html.push("' class='xcheckgroup");
					html.push(o.category.code);
					html.push(" checkItem' value='");
					html.push(e.id);
					html.push("' ");
					html.push(checked);
					html.push("/> <label for='");
					html.push(e.id);
					html.push("' >");
					html.push(e.name);
					html.push("</label>");
					html.push("</div>");
				});
				html.push("</td></tr>");
				$.XCheck({
					groupClass : ".xcheckgroup" + o.category.code,
					afterCheckItem : function() {
						var ops = this.options;
						console.log('当前的options为：' + JSON.stringify(ops));
						console.log("afterCheckItem");
					}
				});
			});
			html.push("</table>");
			html.push("</div></div></form>");
			$("#modal-norm-cfg .modal-body").html(html.join(""));
			$('#_q').bind('keypress', function(event) {
            				if (event.keyCode == "13") {
            					//alert('你输入的内容为：' + $('#_q').val());
            					selectAllNorm(id, $('#_q').val());
            				}
            			});
			$('#fm-norm-cfg').ajaxForm({
				beforeSubmit : function(formData, jqForm, options) {
					var params = {};
					$.each(formData, function(n, obj) {
						params[obj.name] = obj.value;
					});
					inertTopicNorm(params.topicId, params);
					return false;
				}
			});
			$("#btn-norm-cfg-submit" ).on('click', function(e) {
                e.preventDefault();
                $('#fm-norm-cfg').submit();
             });


		},
		error : function() {
			alert("加载错误！");
		}
	});
}
function inertTopicNorm(id, p) {
	var params = [];

	$.each(p, function(key, value) {
		if ((key != 'topicId') && (key != '_q')) {
			params.push({
				topicId : id,
				normId : value
			});
		}
	});
	console.log(params);
	$.ajax({
		type : "post",
		url : contextPath + "/topic/insertTopicNorm.do",
		data : {
			topicId : id,
			name:params._q,
			jsons : JSON.stringify(params)
		},
		beforeSend : function(XMLHttpRequest) {
			$("#btn-submit").attr('disabled', true);
		},
		success : function(rst, textStatus) {
			$("#btn-submit").removeAttr("disabled");
			alert(rst.errorMessage);

		},
		error : function() {
			$("#btn-submit").removeAttr("disabled");
			alert("加载错误！");
		}
	});
}
function ckform() {
	return false;
}

function viewNorm(topicId) {
	var tableId = "detail";
	if ($('#' + tableId).hasClass('dataTable')) {
		dttable = $('#' + tableId).dataTable();
		dttable.fnClearTable(); // 清空一下table
		dttable.fnDestroy(); // 还原初始化了的datatable
	}
	$('#' + tableId).DataTable(
			{
				ajax : {
					url : contextPath + '/norm/selectNormByTopicId.do?topicId='
							+ topicId,
					dataSrc : 'data'
				},
				columns : [{
					data : 'rownum'
				}, {
					data : 'name'
				}, {
					data : 'remark'
				}],
				bAutoWidth : false,
				"fnInitComplete" : function() {
					this.fnAdjustColumnSizing(true);
				},
				"createdRow" : function(row, data, dataIndex) {
					$(row).children('td').eq(0).attr('style',
							'text-align: center;font-weight:800')

				},
				"aLengthMenu" : [5, 10, 15, 20],
				"oLanguage" : {
					"sLengthMenu" : "每页显示 _MENU_ 条记录",
					"sZeroRecords" : "对不起，查询不到任何相关数据",
					"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
					"sInfoEmpty" : "第 0 到 0 条记录，共 0 条",
					"sInfoFiltered" : "数据表中共为 _MAX_ 条记录)",
					"sProcessing" : "正在加载中...",
					"sSearch" : "搜索 ",
					"sUrl" : "",
					"oPaginate" : {
						"sFirst" : "",
						"sPrevious" : "",
						"sNext" : "",
						"sLast" : ""
					},
					"oAria" : {
						"sSortAscending" : ": 升序排列",
						"sSortDescending" : ": 降序排列"
					}
				}
			});

}
function sort(id,name){
       $('#modal-sort').modal('show');
        initSortList(id);
}
function initSortList(id){
     $.ajax({
		type : "post",
		url : contextPath + "/norm/selectNormByTopicId.do",
		data : {
			topicId : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
		    var html=[];
		    $(rst.data).each(function(i,o){
		         html.push('<li draggable="false"  data-id="'+o.id+'" data-name="'+o.name+'">'+o.name+'</li>');
		    });
		    $(".sortable").html(html.join(""));

		     var el = document.getElementById('sortable');
                    var sortable = Sortable.create(el, {
                        group: "words",
                        animation: 150,
                        onAdd: function (evt) {
                            console.log('onAdd.bar:', evt.item);
                        },
                        onUpdate: function (evt) {
                            console.log('onUpdate.bar:', evt.item);
                        },
                        onRemove: function (evt) {
                            console.log('onRemove.bar:', evt.item);
                        },
                        onStart: function (evt) {
                            console.log('onStart.foo:', evt.item);
                        },
                        onEnd: function (evt) {
                            console.log('onEnd.foo:', evt.item);
                            console.log(sortable.toArray());
                            updateSort(sortable.toArray());
                        }
                    });

		},
		error : function() {
			alert("加载错误！");
		}
	});
}
function updateSort(arr){
    var data=[];
    for(var i=0;i<arr.length;i++){
        data.push({id:arr[i],sort:i});
    }
    $.ajax({
        type : "post",
        url : contextPath +"/norm/updateSort.do",
        data:{jsons:JSON.stringify(data)},
        success : function(rst, textStatus) {
            if (!rst.state) {
                bootbox.dialog({
                    title:'系统提示',
                    message:rst.errorMessage,
                    detail:rst.detail,
                    buttons:
                    {
                        "success" :
                         {
                            "label" : "<i class='ace-icon fa fa-check'></i>确定",
                            "className" : "btn-sm btn-success",
                            "callback": function() {
                            }
                        }
                    }
                });

            }
        }
    });
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
	jQuery(cfg.grid_selector).jqGrid(
		'delGridRow',
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
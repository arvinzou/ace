jQuery(function($) {
			//查询
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
			//添加
			$('#btn-view-add').on('click', function() {
					jQuery(cfg.grid_selector).jqGrid('editGridRow', 'new', {
							closeAfterAdd: true,
							recreateForm: true,
							viewPagerButtons: false,
							beforeShowForm: function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog')
									.find('.ui-jqdialog-titlebar')
									.wrapInner(' <div class = "widget-header" / > ');

									
									}
							})
					});

				//初始化事件
				initEvents();
			});

		/*页面渲染*/
		function render(obj, data, tplId) {
			var tpl = document.getElementById(tplId).innerHTML;
			var html = juicer(tpl, {
				data: data,
			});

			$(obj).html(html);
		}

		function initEvents() {
			$('#modal-preview').on('show.bs.modal', function(event) {
				var relatedTarget = $(event.relatedTarget);
				var id = relatedTarget.data('id');
				var title = relatedTarget.data('title');
				var modal = $(this);
				console.log(relatedTarget);
				initPreview(id);
			})
		}

		function initPreview(id) {
        			startLoad();
                    $.ajax({
                        url: contextPath + '/roadSection/getList',
                        type: "post",
                        async: false,
                        data: {
                            roadMan: id
                        },
                        success: function(result) {
                            stopLoad();
                            if (result.status == 0) {
                                var data = {};
                                data['o'] = result.value;
                                render('#fm-preview', data, 'tpl-preview');
                                initTable();
                            } else {
                                alert(result.errorMessage);
                            }
                        },
                        error: function() {
                            stopLoad();
                            alert("对不起出错了！");
                        }
                    });
        		}


		function edit(rowid) {
			console.log(rowid);
			jQuery(cfg.grid_selector).jqGrid('editGridRow', rowid, {
					closeAfterAdd: true,
					recreateForm: true,
					viewPagerButtons: true,
					beforeShowForm: function(e) {
						var form = $(e[0]);
						form.closest('.ui-jqdialog')
							.find('.ui-jqdialog-titlebar')
							.wrapInner(' <div class = "widget-header" / > ');

							}
					});
			}

			var show = false;

			function del(rowid) {
				console.log(rowid);
				jQuery(cfg.grid_selector).jqGrid('delGridRow', rowid, {
						beforeShowForm: function(e) {
							var form = $(e[0]);
							if (!show) {
								form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner(' <div class = "widget-header" / > ');
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


function initTable(){
        var table = $('#list1');

        // begin first table
        table.dataTable({

            // Internationalisation. For more info refer to http://datatables.net/manual/i18n
            "language": {
                "aria": {
                    "sortAscending": ": activate to sort column ascending",
                    "sortDescending": ": activate to sort column descending"
                },
                "emptyTable": "没有找到数据",
                "info": "显示 _START_ 到 _END_ 共 _TOTAL_ 记录",
                "infoEmpty": "没有查找到数据",
                "infoFiltered": "",
                "lengthMenu": "显示 _MENU_",
                "search": "搜索:",
                "zeroRecords": "没有找到数据",
                "paginate": {
                    "previous":"Prev",
                    "next": "Next",
                    "last": "Last",
                    "first": "First"
                }
            },

            // Or you can use remote translation file
            //"language": {
            //   url: '//cdn.datatables.net/plug-ins/3cfcc339e89/i18n/Portuguese.json'
            //},

            // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
            // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js).
            // So when dropdowns used the scrollable div should be removed.
            //"dom": "<'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",

            "bStateSave": true, // save datatable state(pagination, sort, etc) in cookie.

            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 5,
            "pagingType": "bootstrap_full_number",
            "columnDefs": [
                {  // set default column settings
                    'orderable': false,
                    'targets': [0]
                },
                {
                    "searchable": false,
                    "targets": [0]
                },
                {
                    "className": "dt-right",
                    //"targets": [2]
                }
            ],
            "order": [
                [1, "asc"]
            ] // set first column as a default sort by asc
        });



        table.find('.group-checkable').change(function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            jQuery(set).each(function () {
                if (checked) {
                    $(this).prop("checked", true);
                    $(this).parents('tr').addClass("active");
                } else {
                    $(this).prop("checked", false);
                    $(this).parents('tr').removeClass("active");
                }
            });
        });

        table.on('change', 'tbody tr .checkboxes', function () {
            $(this).parents('tr').toggleClass("active");
        });


}

function delTable(id){
    var ids=[];
    $.each($('input:checkbox'),function(){
        if(this.checked){
            console.log($(this).val());
            ids.push($(this).val());
        }
    });
    delByIds(ids.join(","),id);
}
function delByIds(ids,id) {
        startLoad();
        $.ajax({
            url: contextPath + "/roadSection/deleteRoadSectionByRoadSectionIds",
            type: "post",
            async: false,
            data: {ids:ids},
            success: function (rst) {
                stopLoad();
                alert(rst.errorMessage);
                if (rst.status == 0) {
                   initPreview(id);
                }
            },
            error: function () {
                stopLoad();
                alert("对不起出错了！");
            }
        });
    }
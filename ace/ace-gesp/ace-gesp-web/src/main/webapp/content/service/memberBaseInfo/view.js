
function buidDataTable() {

	var oTable1 = $('#table1').wrap("<div class='dataTables_borderWrap' />")
			.dataTable({
				bAutoWidth : false,
				"fnInitComplete" : function() {
					this.fnAdjustColumnSizing(true);
				},
				"oLanguage" : {
					"sLengthMenu" : "每页显示 _MENU_ 条记录",
					"sZeroRecords" : "对不起，查询不到任何相关数据",
					"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
					// "sInfoEmtpy" : "找不到相关数据",
					"sInfoEmpty" : "第 0 到 0 条记录，共 0 条",
					"sInfoFiltered" : "数据表中共为 _MAX_ 条记录)",
					"sProcessing" : "正在加载中...",
					"sSearch" : "搜索",
					"sUrl" : "", // 多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
					"oPaginate" : {
						"sFirst" : "第一页",
						"sPrevious" : " 上一页 ",
						"sNext" : " 下一页 ",
						"sLast" : " 最后一页 "
					},
					"oAria" : {
						"sSortAscending" : ": 升序排列",
						"sSortDescending" : ": 降序排列"
					}
				}, // 多语言配置
				"sScrollY" : "100px",
				// "bPaginate": false,

				"sScrollX" : "100%"
			// Note: if you are applying horizontal scrolling (sScrollX) on a
			// ".table-bordered"
			// you may want to wrap the table inside a
			// "div.dataTables_borderWrap" element

			// "iDisplayLength": 50
			});

	$(document).on(
			'click',
			'th input:checkbox',
			function() {
				var that = this;
				$(this).closest('table').find(
						'tr > td:first-child input:checkbox').each(function() {
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
			});

	$('[data-rel="tooltip"]').tooltip({
		placement : tooltip_placement
	});
	function tooltip_placement(context, source) {
		var $source = $(source);
		var $parent = $source.closest('table')
		var off1 = $parent.offset();
		var w1 = $parent.width();

		var off2 = $source.offset();
		// var w2 = $source.width();

		if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2))
			return 'right';
		return 'left';
	}

}
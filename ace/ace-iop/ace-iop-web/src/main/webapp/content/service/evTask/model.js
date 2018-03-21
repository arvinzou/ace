var _colNames = ['序号', '所属机构', '评测类别', '名称', '被评测对象', '投票率', '测评模板', '状态',
		'管理员', '开始日期', '截止日期', '评测结论', '评测说明', '创建时间'];
var _colModel = function() {
	return [
			{
				name : 'evTaskId',
				width : 100,
				sortable : false,
				editable : true,
				editoptions : {
					readonly : true,
					style : 'width:300px'
				}
			},
			{
				name : 'deptId',
				width : 200,
				editable : true,
				edittype : "combotree",
				dataoptions : {
					url : portalContextPath
							+ '/system/selectDepartmentTreeList.do?id=01',
					animate : true,
					lines : false
				},
				editoptions : {
					style : 'width:300px;line-height: 30px;height: 30px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'deptName');
				}
			},
			{
				name : 'category',
				id : 'category',
				width : 150,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_20");
				},
				editoptions : {
					value : odparse("STATIC_DATA_20"),
					style : 'width:300px'
				}
			},
			{
				name : 'evTaskName',
				width : 200,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:300px'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'evObj',
				width : 200,
				editable : true,
				edittype : "combotree",
				dataoptions : {
					url : contextPath
							+ '/evTask/selectDepAndUsersTreeList.do?id=01',
					animate : true,
					lines : false
				},
				editoptions : {
					style : 'width:300px;line-height: 30px;height: 30px;'

				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'evObjName');
				}
			},
			{
				name : 'evObj',
				width : 200,
				editable : false,
				renderer : function(value, cur) {

					var voteUsers = $.jgrid.getAccessor(cur, 'voteUsers');
					var allUsers = $.jgrid.getAccessor(cur, 'allUsers');
					var b = parseInt(((allUsers - voteUsers) / allUsers) * 100);
					return b + "%";
				}
			},
			{
				name : 'evTempleteId',
				width : 200,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_53");
				},
				editoptions : {
					value : odparse("STATIC_DATA_53"),
					style : 'width:300px'
				}
			},
			{
				name : 'status',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_54");
				},
				editoptions : {
					value : odparse("STATIC_DATA_54"),
					style : 'width:300px'
				}
			},
			{
				name : 'admin',
				width : 60,
				editable : true,
				editoptions : {
					readonly : true,
					size : "20",
					maxlength : "50",
					style : 'width:300px'
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'adminName');
				}
			},
			{
				name : 'evStartDate',
				width : 100,
				editable : true,
				edittype : "datetimebox",
				editoptions : {
					style : 'width:300px;height:30px'
				},
				dataoptions : {

				},
				renderer : function(value) {
					return value == null ? "" : value;
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'evDeadline',
				width : 100,
				editable : true,
				edittype : "datetimebox",
				editoptions : {
					style : 'width:300px;height:30px'
				},
				dataoptions : {

				},
				renderer : function(value) {
					return value == null ? "" : value;
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			}, {
				name : 'evDiscribtion',
				width : 100,
				edittype : "textarea",
				editable : true,
				hidden : true,
				editoptions : {
					colspan : true,
					style : 'width:750px;line-height: 25px;height: 100px;'
				}
			}, {
				name : 'evContent',
				width : 100,
				hidden : true,
				editable : true,
				edittype : "textarea",
				editoptions : {
					colspan : true,
					style : 'width:750px;line-height: 25px;height: 100px;'
				}
			}, {
				name : 'createTime',
				sortable : true,
				editable : false
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
			format : 'yyyy-mm-dd',
			autoclose : true
		});
	}, 0);
}
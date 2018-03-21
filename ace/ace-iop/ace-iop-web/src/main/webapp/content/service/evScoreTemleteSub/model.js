var _colNames = [ '序号', '所属指标分值', '选择标示', '名称', '顺序', '分值', '创建时间' ];
var _colModel = function() {
	return [
			{
				name : 'id',
				index : 'id',
				width : 100,
				sortable : false,
				editable : true,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'scoreTempleId',
				index : 'scoreTempleId',
				width : 100,
				editable : true,
				edittype : "combobox",
				dataoptions : {
					url : contextPath + '/evScoreTemlete/selectListAll.do',
					method : 'get',
					valueField : 'id',
					textField : 'name'
				},
				editoptions : {
					style : 'width:155px;line-height: 30px;height: 30px;'
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'templeName');
				}
			},

			{
				name : 'skey',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
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
				name : 'name',
				width : 100,
				editable : true,
				editoptions: {

                           style:'width:200px;line-height: 25px;height: 100px;'
                        },
                        formoptions: {
                            elmprefix: "",
                            elmsuffix: "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
                        },
                        editrules: {
                            required: true
                        },
                        edittype : "textarea"
			},
			{
				name : 'sqe',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
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
				name : 'score',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			}, {
				name : 'createTime',
				sortable : true,
				editable : false
			} ];
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
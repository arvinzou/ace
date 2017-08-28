var _colNames = [ 'ID','项目名称','所属协会','项目分类','是否按会员等级收费',
'是否按周期收费','收费周期','周期', '收费次数','状态', '创建时间', '创建人','最后修改时间',  '最后修改用户名' ];//, 'PID','是否按自定义方式收费','自定义类型','收费方式'
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 100,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readOnly:true
				}
			/*},{
				name : 'pid',
				width : 100,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readOnly:true
				}*/
			},{
				name : 'name',
				width : 120,
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
				name : 'departmentName',
				width : 180,
				editable : false,
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
			},{
				name : 'itemType',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readOnly:true
				},
				renderer : function(value, cur) {
					return rsd(value, "103");
				}
			},{
				name : 'whetherMemberlevel',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readOnly:true
				},
				renderer : function(value, cur) {
					return rsd(value, "82");
				}
			},{
				name : 'whetherPeriod',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readOnly:true
				},
				renderer : function(value, cur) {
					return rsd(value, "82");
				}
			}, {
				name : 'period',
				width : 150,
				hidden : false,
				renderer : function(value, cur) {
					var result = "";
					var t = rsd(value, "104");
					var times = $.jgrid.getAccessor(cur, 'times');
					var year = $.jgrid.getAccessor(cur, 'year');
					if(year!=0&&times!=0){
						result = year+''+t+''+times+'次';
					}else{
						result = '单次';
					}
					return result;
				}
			}, {
				name : 'year',
				width : 150,
				hidden : true
			}, {
				name : 'times',
				width : 150,
				hidden : true
			/*},{
				name : 'whetherCustom',
				width : 100,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readOnly:true
				}
			},{
				name : 'custom',
				width : 100,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readOnly:true
				}
			},{
				name : 'payMethod',
				width : 100,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readOnly:true
				}*/
			},{
				name : 'status',
				width : 60,
				editable : true,
				hidden:true,
				edittype : "checkbox",
				editoptions : {
					value : "1:0"
				},
				unformat : aceSwitch,
				renderer : function(value) {
					var rst = "";
					switch (value) {
					case '1':
						rst = "<span class='badge badge-success'>ON</span>";
						break;
					case '0':
						rst = "<span class='badge badge-danger'>OFF</span>";
						break;
					default:
						rst = "N/A";
					}
					return rst;
				}
			}, {
				name : 'createDate',
				width : 150,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'createUserName',
				hidden:true,
				width : 100,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'lastModifyDate',
				hidden:true,
				width : 150,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'lastModifyUserName',
				width : 100,
				hidden:true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}

	];
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
var _colNames = [ '编号','协会名称','收费项目','会员级别','缴费方式','缴费金额', '创建时间'];
var _colModel = function() {
	return [ {
		name : 'id',
		index : 'id',
		width : 100,
		sortable : false,
		editable : true,
		hidden:true,
		editoptions : {
			readonly : true,
			style:'width:176px;line-height: 25px;height: 25px;'
		}
	},{
		name : 'departmentName',
		index : 'departmentName',
		width : 200,
		sortable : false,
		editable : false,
		editoptions : {
			readonly : true
		}
	}, {
		name : 'chargingItemId',
		index : 'chargingItemId',
		width :100,
		editable : true,
		edittype : "combobox",
		dataoptions:{
			 url: contextPath +'/chargingItem/selectListByDeptId.do',
		        method: 'get',
		        valueField:'code',
		        textField:'name'
		},
		editoptions : {
			editable:false,
			style:'width:176px;line-height: 25px;height: 25px;'
		},
		renderer : function(value, cur) {
			return $.jgrid.getAccessor(cur, 'chargingItemName');
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'memberLevelId',
		index : 'memberLevelId',
		width :100,
		editable : true,
		edittype : "combobox",
		dataoptions:{
			 url: contextPath +'/memberLevel/selectListByDeptId.do',
		        method: 'get',
		        valueField:'code',
		        textField:'name'
		},
		editoptions : {
			editable:false,
			style:'width:176px;line-height: 25px;height: 25px;'
		},
		renderer : function(value, cur) {
			var s = $.jgrid.getAccessor(cur, 'memberLevelName');
			if(s==undefined){
				s="";
			}
			return s;
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'payType',
		index : 'payType',
		width : 100,
		hidden : true,
		editable : true,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "69");
		},
		editoptions : {
			value : odparse("69"),
			editable:false,
			style:'width:176px;line-height: 25px;height: 25px;'
		}
	},{
		name : 'payNum',
		index : 'payNum',
		width : 80,
		hidden : false,
		editoptions : {
			size : "20",
			maxlength : "50",
			style:'width:176px;line-height: 25px;height: 25px;'
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'createDate',
		width : 150,
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
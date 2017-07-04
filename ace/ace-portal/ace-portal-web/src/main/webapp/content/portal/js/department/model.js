var _colNames = [ '部门编号', '归属部门编号', '部门名称', '简称', '联系人姓名', '联系人Email','法定联系人','法人证件类型','法定人证件号','经济性质','营业执照号','企业类型','机构类型', '地区',
		'创建时间', '状态' ];
var _colModel = function() {
	return [ {
		name : 'departmentId',
		index : 'id',
		width : 150,
		sortable : false,
		editable : false,
		editoptions : {
			readonly : true,
			style:'width:175px;line-height: 25px;height: 25px;'
		}
	}, {
		name : 'parentDepartmentId',
		width : 80,
		editable : true,
		hidden:true,
		sorttype : "int",
		editoptions : {
			readonly : true,
			style:'width:175px;line-height: 25px;height: 25px;'
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'departmentName',
		index : 'departmentName',
		width : 250,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30",
			style:'width:175px;line-height: 25px;height: 25px;'
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'shortName',
		index : 'shortName',
		width : 150,
		hidden:true,
		editable : false,
		editoptions : {
			size : "20",
			maxlength : "250",
			style:'width:175px;line-height: 25px;height: 25px;'
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'contactName',
		index : 'contactName',
		width : 150,
		hidden:true,
		editable : false,
		editoptions : {
			size : "20",
			maxlength : "50",
			style:'width:175px;line-height: 25px;height: 25px;'
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		},
		dataoptions:{
			validType:['chinese','length[0,30]']
		}
	},{
		name : 'contactEmail',
		index : 'contactEmail',
		width : 150,
		hidden:true,
		editable : false,
		editoptions : {
			size : "20",
			maxlength : "50",
			style:'width:175px;line-height: 25px;height: 25px;'
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		dataoptions:{
			validType:['email','length[0,30]']
		},
		editrules : {
			required : true
		}
	},{
		name : 'legalPersonName',
		index : 'legalPersonName',
		width : 150,
		hidden:true,
		editable : false,
		editoptions : {
			size : "20",
			maxlength : "50",
			style:'width:175px;line-height: 25px;height: 25px;'
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	},{
		name : 'legalPersonIdType',
		index : 'legalPersonIdType',
		width : 100,
		hidden:true,
		editable : false,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "70");
		},
		editoptions : {
			value : odparse("70"),
			style:'width:175px;line-height: 25px;height: 25px;'
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	},{
		name : 'legalPersonIdNo',
		index : 'legalPersonIdNo',
		width : 150,
		hidden:true,
		editable : false,
		editoptions : {
			size : "20",
			maxlength : "50",
			style:'width:175px;line-height: 25px;height: 25px;'
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	},{
		name : 'nature',
		index : 'nature',
		width : 80,
		hidden:true,
		editable : false,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "03");
		},
		editoptions : {
			value : odparse("03"),
			style:'width:175px;line-height: 25px;height: 25px;'
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	},{
		name : 'bussLicenseNo',
		index : 'bussLicenseNo',
		width : 150,
		hidden:true,
		editable : false,
		editoptions : {
			size : "20",
			maxlength : "50",
			style:'width:175px;line-height: 25px;height: 25px;'
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'type',
		width : 60,
		hidden:true,
		editable : false,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "04");
		},
		editoptions : {
			value : odparse("04"),
			style:'width:175px;line-height: 25px;height: 25px;'
		}
	}, {
		name : 'category',
		width : 60,
		hidden:true,
		editable : false,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "66");
		},
		editoptions : {
			value : odparse("66"),
			style:'width:175px;line-height: 25px;height: 25px;'
		}
	}, {
		name : 'areaCode',
		width : 100,
		hidden:true,
		editable : true,
		edittype : "combotree",
		editoptions : {
			style:'width:175px;line-height: 25px;height: 25px;'
		},
		dataoptions:{
			url: contextPath +'/system/selectProvinceTreeList.do',
			required:false
		},
		renderer : function(value, cur) {
			return $.jgrid.getAccessor(cur, 'areaName');
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
		width : 150,
		sortable : true,
		editable : false
	}, {
		name : 'status',
		index : 'status',
		width : 90,
		editable : true,
		edittype : "checkbox",
		editoptions : {
			value : "1:0"
		},
		unformat : aceSwitch,
		renderer : function(value) {
			// console.log(value);
			var rst = "";
			switch (value) {
			case '1':
				rst = "YES";
				break;
			case '0':
				rst = "NO";
				break;
			default:
				rst = "N/A";
			}
			return rst;
		}
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
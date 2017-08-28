var _colNames = [ '编号', '状态','企业名称','是否会员','道路经营许可证号','注册资本','从业人员数','车辆数','联系人','联系人手机','注册日期','操作' ];
var _colModel = function() {
	return [
			{
				name : 'departmentId',
				width : 80,
				hidden : false,
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
				name : 'status',
				width : 50,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'departmentName',
				width : 150,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'type',
				width : 50,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'transBussLicenseNo',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'regCapital',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "40"
				}
			}, {
				name : 'employeesNum',
				width : 70,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "40"
				}
			}, {
				name : 'driverNum',
				width : 70,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "40"
				}
			}, {
				name : 'contactName',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "40"
				}
			}, {
				name : 'contactMobile',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "40"
				}
			}, {
				name : 'regDate',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "40"
				}
			}, {
				name : 'opt',
				width : 60,
				editable : true,
				hidden:false,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value, cur) {
					
					return renderName(cur);
				}
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


function renderName(cur) {
	var id = $.jgrid.getAccessor(cur, 'id');
	var status = $.jgrid.getAccessor(cur, 'status');
	var type = $.jgrid.getAccessor(cur, 'type');
	var departmentName = $.jgrid.getAccessor(cur, 'departmentName');
	var memberCode = $.jgrid.getAccessor(cur, 'departmentId');
	if(type=='是'){
		type=1;
	}else{
		type=0;
	}
	var html = [];
	html.push('<a target="_blank" href="');
	html.push('javascript:lookMemberInfo(\''+id+'\',\''+status+'\',\''+departmentName+'\',\''+memberCode+'\',\''+type+'\')');
	html.push('"');
	html.push('><span class="badge badge-info">查看</span></a>');
	return html.join(' ');
}

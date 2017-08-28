var _colNames = [ '编号', '姓名', '身份证号码', '联系电话', '合作方式', '准驾车型', '从业资格类别', '初领驾驶证日期' ,'操作'];
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 100,
				editable : true,
				hidden : true,
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
				name : 'name',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'idCard',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'tel',
				width : 80,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'cooperationMode',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value, cur) {
					return rsd(value, "75");
				}
			} , {
				name : 'driverLicCarType',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value, cur) {
					return rsd(value, "77");
				}
			}, {
				name : 'certType',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value, cur) {
					return rsd(value, "73");
				}
			}, {
				name : 'initDrivingLicDate',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
				//,renderer : function(value, cur) {
				//	if(value!=undefined){
				//		return value;
				//	}
					//return rsd(value, "75");
				//}
			}, {
				name : 'opt',
				width : 60,
				editable : true,
				sortable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value, cur) {
					
					return renderName(cur);
				}
			}];
}


function renderName(cur) {
	var id = $.jgrid.getAccessor(cur, 'id');
	var name = $.jgrid.getAccessor(cur, 'name');
	var html = [];
	html.push('<a target="_blank" href="');
	html.push('javascript:lookDetail(\'' + id + '\',\'' + name + '\')');
	html.push('"');
	html.push('><span class="badge badge-info">查看</span></a>');
	return html.join(' ');
}
function lookDetail(id,name){
	parent.addPanel(name,contextPath+'/dynamic/service/industryResourceDriver/preview.jsp?id='+id,true);
}
var _colNames = [ '编号', '车牌号','车牌颜色','车辆类型','经营方式','所属企业','行驶证登记日期','操作' ];
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 80,
				editable : true,
				hidden:true,
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
				name : 'plateNumber',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'plateColor',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value) {
					return rsd(value, "90");
				}
			}, {
				name : 'transportMode',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value) {
					return rsd(value, "86");
				}
			}, {
				name : 'bussType',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value) {
					return rsd(value, "89");
				}
			}, {
				name : 'departmentName',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			} , {
				name : 'drivingLicCreatetime',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
				/*,renderer : function(value, cur) {
					if(value!=undefined){
						return value;
					}
					//return renderName(cur);
				}*/
			} , {
				name : 'opt',
				width : 60,
				editable : false,
				sortable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value, cur) {
					return renderName(cur);
				}
			} ];
}
function renderName(cur) {
	var id = $.jgrid.getAccessor(cur, 'id');
	var name = $.jgrid.getAccessor(cur, 'plateNumber');
	var html = [];
	html.push('<a target="_blank" href="');
	html.push('javascript:lookDetail(\'' + id + '\',\'' + name + '\')');
	html.push('"');
	html.push('><span class="badge badge-info">查看</span></a>');
	return html.join(' ');
}
function lookDetail(id,name){
	parent.addPanel(name,contextPath+'/dynamic/service/industryResourceCar/preview.jsp?id='+id,true);
}

var _colNames = [ '编号','企业名称','检测单号', '车牌号','车牌颜色','检测站','检测类别','检测结果','检测日期' ];//,'操作'
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 80,
				hidden : true
			},{
				name : 'companyName',
				width: 80
			}, {
				name : 'repairTicketsNo',
				width: 80
			}, {
				name : 'plateNo',
				width : 100
			}, {
				name : 'color',
				width : 80
			}, {
				name : 'repairCompany',
				width : 80
			}, {
				name : 'repairType',
				width : 80,
				renderer : function(value) {
					return rsd(value, "89");
				}
			}, {
				name : 'repairResult',
				width : 100
			} , {
				name : 'repairDate',
				width : 80
			/*} , {
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
				}*/
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

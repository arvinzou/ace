var _colNames = [ '编号','案件编号', '执法机关','企业名称','车辆号码','车牌颜色','案由','罚款金额','违法时间','违法地点','类型','当前状态' ];//,'操作'
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 80,
				hidden : true
			},{
				name : 'Code',
				width: 100
			}, {
				name : 'Organization',
				width : 130
			}, {
				name : 'companyName',
				width : 130
			}, {
				name : 'plateNo',
				width : 80
			}, {
				name : 'color',
				width : 60
			}, {
				name : 'Name',
				width : 100
			}, {
				name : 'Amerce',
				width : 60
			}, {
				name : 'Time',
				width : 80
			}, {
				name : 'Address',
				width : 100
			}, {
				name : 'Type',
				width : 80
			}, {
				name : 'Tache',
				width : 80
			/*}, {
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
/*function renderName(cur) {
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
}*/

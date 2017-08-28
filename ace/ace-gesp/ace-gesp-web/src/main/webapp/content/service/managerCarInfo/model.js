var _colNames = [ '编号', '车牌号', '车牌颜色','经营许可证号','企业名称','车辆类型','核定吨位','审验有效期','审验状态','操作' ];//,'外观尺寸'
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 20,
				editable : true,
				hidden:true
			}, {
				name : 'plateNo',
				width : 50
			}, {
				name : 'color',
				width : 50,
				hidden : false
			}, {
				name : 'companyLicense',
				width : 60
			}, {
				name : 'companyName',
				width : 80
			}, {
				name : 'carBigType',
				width : 70,
				renderer : function(value) {
					return rsd(value, "84");
				}
			}, {
				name : 'approvedTon',
				width : 60
			}, {
				name : 'checkValidityDate',
				width : 60
			},{
				name : 'checkValidityStatus',
				width : 50,
				renderer : function(value) {
					var html = [];
					if(value=='0'){
						html.push('<span class="badge badge-success">正常</span>');
					}else if(value=='1'){
						html.push('<span class="badge badge-warning">即将</span>');
					}else{
						html.push('<span class="badge badge-danger">已逾期</span>');
					}
					return html.join(' ');
				}
			}, {
				name : 'opt',
				width : 40,
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
	var name = $.jgrid.getAccessor(cur, 'plateNo');
	var color = $.jgrid.getAccessor(cur, 'color');
	var html = [];
	html.push('<a target="_blank" href="');
	html.push('javascript:lookDetail(\'' + id + '\',\'' + name + '\',\'' + color + '\')');
	html.push('"');
	html.push('><span class="badge badge-info">查看</span></a>');
	
	/*html.push('<a target="_blank" href="');
	html.push('javascript:lookTechDoc(\'' + id + '\',\'' + name + '\',\'' + color + '\')');
	html.push('"');
	html.push('><span class="badge badge-info">车辆技术档案</span></a>');*/
	return html.join(' ');
}
function lookDetail(id,name,color){
	parent.addPanel(name,contextPath+'/dynamic/service/managerCarInfo/preview.jsp?id='+id+"&plateNo="+encodeURIComponent(name)+'&color='+encodeURIComponent(color),true);
}

function lookTechDoc(id,name,color){
	parent.addPanel(name,contextPath+'/dynamic/service/managerCarInfo/techdoc.jsp?plateNo='+encodeURIComponent(name)+'&color='+encodeURIComponent(color),true);
}



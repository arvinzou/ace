var _colNames = [ '编号','手机号','姓名','短信内容','入队时间' ,'发送时间','返回'];
var _colModel = function() {
	return [ {
		name : 'queueId',
		index : 'queueId',
		width : 200,
		sortable : false,
		editable : false
	},{
		name : 'tel',
		index : 'tel',
		width : 100,
		sortable : false,
		editable : false
	},{
		name : 'name',
		index : 'name',
		width : 60,
		sortable : false,
		editable : false
	},{
		name : 'msg',
		index : 'msg',
		width : 200,
		sortable : false,
		editable : false
	}, {
		name : 'addTime',
		width : 130,
		sortable : true,
		editable : false
	}, {
		name : 'createTime',
		width : 130,
		sortable : true,
		editable : false
	} , {
		name : 'remark',
		width : 150,
		sortable : true,
		editable : false
	}];
}
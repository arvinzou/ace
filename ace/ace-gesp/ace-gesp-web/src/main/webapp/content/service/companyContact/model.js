var _colNames = [ '编号', '企业名称','姓名', '职位','手机','邮箱','电话','QQ','传真'];
var _colModel = function() {
	return [
			{
				name : 'userId',
				width : 80,
				hidden : true
			}, {
				name : 'corpName',
				width : 80
			}, {
				name : 'name',
				width : 50,
				editable : false
			}, {
				name : 'userLevel',
				width : 50,
				editable : false,
				renderer : function(value) {
					return rsd(value, "05");
				}
			}, {
				name : 'mobile',
				width : 50,
				editable : false
			}, {
				name : 'email',
				width : 50,
				editable : false
			}, {
				name : 'telphone',
				width : 50,
				editable : false
			}, {
				name : 'qq',
				width : 50,
				editable : false
			}, {
				name : 'fax',
				width : 50,
				editable : false
			}];
}


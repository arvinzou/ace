var _colNames = [ '编号','姓名', '职位','手机','邮箱','电话','QQ','传真'];
var _colModel = function() {
	return [
			{
				name : 'userId',
				width : 80,
				hidden : true
			}, {
				name : 'name',
				width : 60,
				editable : false
			}, {
				name : 'userLevel',
				width : 60,
				editable : false,
				renderer : function(value) {
					return rsd(value, "05");
				}
			}, {
				name : 'mobile',
				width : 60,
				editable : false
			}, {
				name : 'email',
				width : 60,
				editable : false
			}, {
				name : 'telphone',
				width : 60,
				editable : false
			}, {
				name : 'qq',
				width : 60,
				editable : false
			}, {
				name : 'fax',
				width : 60,
				editable : false
			}];
}


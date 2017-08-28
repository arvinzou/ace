var _colNames = [ '编号','账号', '性别','用户名', '职位','手机','邮箱','创建时间'];
var _colModel = function() {
	return [
			{
				name : 'userId',
				width : 50,
				hidden : true
			}, {
				name : 'account',
				width : 50
			}, {
				name : 'sex',
				width : 30,
				renderer : function(value) {
					return rsd(value, "01");
				}
			}, {
				name : 'name',
				width : 50
			}, {
				name : 'userLevel',
				width : 40,
				renderer : function(value) {
					return rsd(value, "05");
				}
			}, {
				name : 'mobile',
				width : 40
			}, {
				name : 'email',
				width : 40
			}, {
				name : 'createtime',
				width : 40
			}];
}


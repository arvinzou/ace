var _colNames = [ '公告编号', '标题','状态', '发布者', '是否置顶','发布时间' ,'有效日期','操作'];
var _colModel = function() {
	return [
			{
				name : 'noticeId',
				width : 100,
				hidden:true
			},
			{
				name : 'title',
				width : 300
			},
			{
				name : 'status',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "22");
				},
				editoptions : {
					value : odparse("22")
				}
			},{
				name : 'name',
				width : 60
			},{
				name : 'top',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "23");
				},
				editoptions : {
					value : odparse("23")
				}
			}, {
				name : 'publishTime',
				width : 120,
				sortable : true,
				editable : false
			}, {
				name : 'deadline',
				width : 120,
				sortable : true,
				editable : false
			} , {
                        name : 'opt',
                        width : 50,
                        renderer : function(value, cur) {
                            var rowid=$.jgrid.getAccessor(cur, cfg.dataId);
                            var status=$.jgrid.getAccessor(cur, 'status');
                            var tops=$.jgrid.getAccessor(cur, 'top');
                            var opt=[];
                            if(authorConfig.hasOwnProperty(cfg.grid_edit_data_url )){
                                opt.push('<a href="javascript:edit(\''+rowid+'\')">编辑</a> ');
                            }

                            if(authorConfig.hasOwnProperty(cfg.grid_delete_data_url)){
                                opt.push('<a href="javascript:del(\''+rowid+'\')">删除</a>');
                            }
                            if(tops=='0'){
                                opt.push('<a href="javascript:optTop(\''+rowid+'\')">置顶</a>');
                            }



                            return opt.join('');
                        }
                    }];
}
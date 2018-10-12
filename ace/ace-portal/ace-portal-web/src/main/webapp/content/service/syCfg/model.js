var _colNames = [ '系统编号', '系统名称',  '创建日期','操作' ];
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'name',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;'>*</span>"
				},
				editrules : {
					required : true
				}
			}, {
				name : 'createDate',
				width : 100,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				editable : false,

				hidden : false
			}, {
                name : 'opt',
                sortable : false,
                width : 50,
                renderer : function(value, cur) {
                    var rowid=$.jgrid.getAccessor(cur, cfg.dataId);
                    var opt=[];
                    if(authorConfig.hasOwnProperty(cfg.grid_edit_data_url )){
                        opt.push('<a href="javascript:edit(\''+rowid+'\')">编辑</a> ');
                    }
                    if(authorConfig.hasOwnProperty(cfg.grid_delete_data_url)){
                        opt.push('<a href="javascript:del(\''+rowid+'\')">删除</a>');
                    }

                    return opt.join('');
                }
            }

	];
}

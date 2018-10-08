var _colNames = ['编号', 'KEY', '所属系统', '类型', '名称', '值', '创建时间', '备注','操作'];
var _colModel = function() {
	return [
			{
				name : 'id',
				index : 'id',
				width : 100,
				sortable : false,
				editable : true,
				hidden : true,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'configKey',
				index : 'configKey',
				width : 100,
				sortable : false,
				editable : true,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'syid',
				index : 'syid',
				width : 100,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "08");
				},
				editoptions : {
					value : odparse("08")
				}
			},
			{
				name : 'category',
				index : 'category',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "30");
				},
				editoptions : {
					value : odparse("30")
				}
			},
			{
				name : 'configName',
				index : 'configName',
				width : 130,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "30"
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
				name : 'configValue',
				index : 'configValue',
				width : 200,
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
				name : 'createTime',
				width : 150,
				sortable : true,
				editable : false
			}, {
				name : 'remark',
				index : 'remark',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "30"
				}
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
                         }];
}

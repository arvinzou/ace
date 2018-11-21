var _colNames = ['上级编码', '辖区编码', '辖区名称', '操作'];
var _colModel = function() {
	return [
			{
				name : 'parent_code',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readOnly:true
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
				name : 'code',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readOnly:true
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;'></span>"
				},
				editrules : {
					required : false
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
			},  {
                               name : 'opt',
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

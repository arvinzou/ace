var _colNames = [ '类型编号', '所属系统','名称',  '自定义','创建时间','操作' ];
var _colModel = function() {
	return [ {
		name : 'categoryId',
		index : 'categoryId',
		width : 6,
		sortable : false,
		editable : true,
		editoptions : {
			readonly : true
		}
	}, {
		name : 'syid',
		index : 'syid',
		width : 6,
		hidden:true,
		editable : false,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "08");
		},
		editoptions : {
			value : odparse("08")
		}
	}, {
		name : 'name',
		index : 'name',
		width : 10,
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
	}, {
		name : 'remark',
		index : 'remark',
		width : 10,
		editable : true,
		edittype : "textarea",
		editoptions : {
			style:'width:350px;height: 50px;',
			size : "20",
			maxlength : "200"
		}
	}, {
		name : 'createTime',
		width : 10,
		sortable : true,
		editable : false
	}, {
                                               name : 'opt',
                                               sortable : false,
                                               width : 5,
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
                                           } ];
}

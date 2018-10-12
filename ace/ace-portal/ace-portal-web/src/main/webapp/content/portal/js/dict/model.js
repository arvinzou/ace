var _colNames = ['名称','编号', '父编号', '类型', '编码',  '拼音码', '创建时间', '图标','操作'];
var _colModel = function() {
	return [{
            				name : 'name',
            				index : 'name',
            				width : 100,
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
				name : 'id',
				hidden:true,
				width : 100,
				sortable : false,
				editable : false,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'pcode',
				index : 'pcode',
				width : 100,
				sortable : false,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readonly : false
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
				name : 'categoryId',
				index : 'categoryId',
				width : 100,
				editable : true,
				hidden:false,
				edittype : "combobox",
				dataoptions : {
					url : contextPath
							+ '/dictCategory/findDictCategoryListAll.do',
					method : 'get',
					valueField : 'categoryId',
					textField : 'name'
				},
				editoptions : {
					style : 'width:176px;line-height: 25px;height: 25px;'
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'categoryName');
				}
			},
			{
				name : 'code',
				index : 'code',
				width : 100,
				sortable : false,
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
				name : 'spell',
				index : 'spell',
				hidden:true,
				width : 100,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "30"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;'></span>"
				},
				editrules : {
					required : false
				}
			}, {
				name : 'createTime',
				width : 150,
				sortable : true,
				editable : false
			}, {
				name : 'remark',
				index : 'remark',
				hidden:true,
				width : 150,
				editable : true,
				editoptions : {
					style : 'width:300px;',
					maxlength : "200",
					colspan : true
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


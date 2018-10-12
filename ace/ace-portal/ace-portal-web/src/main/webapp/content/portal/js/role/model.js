var _colNames = [ '角色编号','角色名称','类型', '创建时间', '备注','系统','操作' ];
var _colModel = function() {
	return [ {
		name : 'roleId',
		index : 'roleId',
		width : 8,
		hidden:true,
		sortable : false,
		editable : true,
		editoptions : {
			readonly : true
		}
	}, {
		name : 'roleName',
		index : 'roleName',
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
	},{
		name : 'type',
		width : 10,
		editable : true,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "64");
		},
		editoptions : {
			value : odparse("64")
		}
	}, {
		name : 'createTime',
		width : 10,
		sortable : true,
		editable : false
	}, {
		name : 'remark',
		index : 'remark',
		width : 5,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		}
	} ,{
      		name : 'syid',
      		width : 5,
      		editable : true,
      		edittype : "select",
      		renderer : function(value) {
      			return rsd(value, "08");
      		},
      		editoptions : {
      			value : odparse("08")
      		}
      	}, {
          name : 'opt',
          width : 5,
          renderer : function(value, cur) {
              var rowid=$.jgrid.getAccessor(cur, cfg.dataId);
              var opt=[];
              if(authorConfig.hasOwnProperty(cfg.grid_edit_data_url )){
                  opt.push('<a href="javascript:edit(\''+rowid+'\')">编辑</a> ');
              }
              if(authorConfig.hasOwnProperty(cfg.insertRoleResources)){
                  opt.push('<a href="javascript:role(\''+rowid+'\')">分配权限</a>  ');
              }
              if(authorConfig.hasOwnProperty(cfg.grid_delete_data_url)){
                  opt.push('<a href="javascript:del(\''+rowid+'\')">删除</a>');
              }

              return opt.join('');
          }
      }];
}
function aceSwitch(cellvalue, options, cell) {
	console.log('aceSwitch');
	setTimeout(function() {
		$(cell).find('input[type=checkbox]').addClass(
				'ace ace-switch ace-switch-5').after(
				'<span class="lbl"></span>');
	}, 0);
}
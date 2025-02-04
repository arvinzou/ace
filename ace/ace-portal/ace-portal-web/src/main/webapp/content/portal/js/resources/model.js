var _colNames = [ '资源编号', '上级编号', '所属系统','资源名称','资源URL','ICON','资源类别', '状态' , '菜单顺序','操作'];
var _colModel = function() {
	return [ {
		name : 'resourcesId',
		index : 'id',
		hidden:true,
		width : 80,
		sortable : false,
		editable : true,
		editoptions : {
			readonly : false
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'parentResourcesId',
		width : 80,
		editable : true,
		sorttype : "int",
		hidden:true,
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;'>*</span>"
		},
		editrules : {
			required : true
		}

	}, {
		name : 'syid',
		index : 'syid',
		hidden:true,
		width : 150,
		editable : false,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "08");
		},
		editoptions : {
			value : odparse("08")
		}
	}, {
		name : 'resourcesName',
		index : 'resourcesName',
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
	}, {
		name : 'resourcesUrl',
		index : 'resourcesUrl',
		width : 250,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "250"
		}
	},  {
		name : 'remark',
		index : 'remark',
		width : 100,
		editable : true,
		hidden:true,
		editoptions : {
			size : "20",
			maxlength : "250"
		}
	}, {
		name : 'resourcesType',
		width : 60,
		editable : true,
		hidden:true,
		edittype : "select",
		renderer : function(value) {
			return rsd(value,"06");
		},
		editoptions : {
			value : odparse("06")
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'stauts',
		index:'stauts',
		hidden:true,
		width : 60,
		editable : true,
		edittype : "checkbox",
		editoptions : {
			value : "1:0"
		},
		unformat : aceSwitch,
		renderer : function(value) {
			// console.log(value);
			var rst = "";
			switch (value) {
			case '1':
				rst = "YES";
				break;
			case '0':
				rst = "NO";
				break;
			default:
				rst = "N/A";
			}
			return rst;
		}
	},  {
		name : 'sequence',
		index : 'sequence',
		width : 60,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "250"
		}
	} , {
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
function aceSwitch(cellvalue, options, cell) {
    console.log('aceSwitch');
    setTimeout(function () {
        $(cell).find('input[type=checkbox]').addClass(
            'ace ace-switch ace-switch-5').after(
            '<span class="lbl"></span>');
    }, 0);
}
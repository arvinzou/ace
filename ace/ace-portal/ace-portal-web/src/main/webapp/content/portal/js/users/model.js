var _colNames = ['姓名', '用户编号', '账户','密码', '性别', '身份证号',  '归属部门','所属地区', '出生日期', '状态',
		'最后登录时间', '手机号', '电子邮箱', '职务', '座位', '创建时间' ];
var _colModel = function() {
	return [
            {
                name : 'name',
                //width : 100,
                editable : true,
                editoptions : {

                    size : "20",
                    maxlength : "25"
                },
                formoptions : {
                    elmprefix : "",
                    elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
                },
                editrules : {
                    required : true
                }
            },
            {
				name : 'userId',
				//width : 120,
				sorttype : "int",
				editable : false,
				hidden:true,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'account',
				//width : 120,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "30"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'password',
				//width : 80,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "100"
				},
				renderer : function(value) {
					return "***";
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				editrules : {
					required : false
				}
			},
			{
				name : 'sex',
				//width : 60,
				editable : true,
				hidden:true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "01");
				},
				editoptions : {
					value : odparse("01")
				},
				formoptions : {
                    elmprefix : "",
                    elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
                },
                editrules : {
                    required : true
                }
			},
			{
				name : 'idCard',
				hidden:true,
				//width : 150,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "18"
				}
			},
			{
				name : 'departmentId',
				//width : 150,
				editable : true,
				edittype : "combotree",
				dataoptions : {
					url : contextPath + '/system/selectDepartmentTreeList.do?id=01',
					animate: true,
	                 lines:false
				},
				editoptions : {
					style:'width:175px;line-height: 25px;height: 25px;'
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'departmentName');
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			 {
				name : 'areaCode',
				//width : 150,
				hidden:true,
				editable : false,
				edittype : "combotree",
				editoptions : {
					style:'width:175px;line-height: 25px;height: 25px;',
					readonly : true
				},
				dataoptions:{
					url: contextPath +'/system/selectProvinceTreeList.do',
					required:false
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'areaName');
				}
			},
			{
				name : 'birthday',
				//width : 120,
				editable : true,
				hidden:true,
				edittype : "datebox",
				editoptions : {
					style : 'width:175px;height:30px'
				},
				dataoptions : {
					formatter : function(date) {
						var y = date.getFullYear();
						var m = date.getMonth() + 1;
						var d = date.getDate();
						return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
								+ (d < 10 ? ('0' + d) : d);
					},
					parser : function(s) {
						if (!s)
							return new Date();
						var ss = (s.split('-'));
						var y = parseInt(ss[0], 10);
						var m = parseInt(ss[1], 10);
						var d = parseInt(ss[2], 10);
						if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
							return new Date(y, m - 1, d);
						} else {
							return new Date();
						}
					}
				},
				renderer : function(value) {
					return value == null ? "" : value.substring(0, 10);
				},
				editrules : {
					required : false
				}
			}, {
				name : 'stauts',
				//width : 90,
				editable : true,
				hidden:true,
				edittype : "checkbox",
				editoptions : {
					value : "1:0"
				},
				unformat : aceSwitch,
				renderer : function(value) {
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
			}, {
				name : 'lastLoginTime',
				//width : 160,
				hidden:true,
				sortable : true,
				editable : false
			}, {
				name : 'mobile',
				width : 150,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "11"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				editrules : {
					required : false
				}
			}, {
				name : 'email',
				//width : 150,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				editrules : {
					required : false
				}
			}, {
				name : 'userLevel',
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "05");
				},
				editoptions : {
					value : odparse("05")
				},
				formoptions : {
                    elmprefix : "",
                    elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
                },
                editrules : {
                    required : true
                }
			}, {
				name : 'seat',
				hidden:true,
				//width : 150,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'createTime',
				//width : 200,
				sortable : true,
				editable : false
			} ];
}
function aceSwitch(cellvalue, options, cell) {
	//console.log('aceSwitch');
	setTimeout(function() {
		$(cell).find('input[type=checkbox]').addClass(
				'ace ace-switch ace-switch-5').after(
				'<span class="lbl"></span>');
	}, 0);
}
// enable datepicker
function pickDate(cellvalue, options, cell) {
	setTimeout(function() {
		$(cell).find('input[type=text]').datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true
		});
	}, 0);
}
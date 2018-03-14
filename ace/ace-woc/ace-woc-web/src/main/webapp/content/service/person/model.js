var _colNames = ['主键','姓名','身份证','电话号码','所属机构','所在地区',
    '所住地址','从业资格证','驾驶证件号','头像','备注','状态','创建人编号', '创建人姓名',
    '入库日期', '最后更新人编号', '最后更新人姓名', '最后更新时间', '操作'];
var _colModel = function () {
    return [
        /*主键*/
        {
            name : 'id',
            editable : false,
            hidden : true,
            width : 100,
        },
        /*姓名*/
        {
            name : 'name',
            editable : true,
            width : 200,
            editoptions : {
                style : 'width:200px;',
                maxlength : "50",
                colspan : false
            },
            formoptions : {
                elmprefix : "",
                elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
            },
            editrules : {
                required : true
            }
        },
        /*身份证id*/
        {
            name : 'paperworkId',
            editable : true,
            hidden : true,
            width : 200,
            editoptions : {
                style : 'width:200px;',
                maxlength : "18",
                minlength:"18",
                colspan : false
            },
            formoptions : {
                elmprefix : "",
                elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
            },
        },
        /*电话号码*/
        {
            name : 'phoneNumber',
            editable : true,
            hidden : true,
            width : 100,
            editoptions : {
                style: 'width:200px;',
                maxlength : "20",
                colspan: false
            }
        },
        /*所属机构*/
        {
            name : 'companyId',
            editable : true,
            hidden : true,
            width : 200,
            edittype : "combotree",
            editoptions : {
                style : 'width:200px;height:25px;'
            },
            dataoptions : {
                url : portalPath + '/department/selectDepartmentTreeList.do',
                required : false
            },
            renderer : function(value, cur) {
                return $.jgrid.getAccessor(cur, 'department_name');
            },
        },
        /*所在机构*/
        {
            name : 'areaCode',
            editable : true,
            hidden : true,
            edittype : "combotree",
            width : 200,
            editoptions : {
                style : 'width:200px;',
            },
            dataoptions : {
                url : portalPath + '/system/selectProvinceTreeList.do',
                required : false
            },
            renderer : function(value, cur) {
                return $.jgrid.getAccessor(cur, 'areaName');
            },
            formoptions : {
                elmprefix : "",
                elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
            },
            editrules : {
                required : true
            }
        },
        /*居住地址*/
        {
            name : 'address',
            editable : true,
            width : 200,
            editoptions : {
                style : 'width:200px;',
                maxlength : "100"
            },
        },
        /*从业资格证*/
        {
            name : 'certNumber',
            editable : true,
            width : 200,
            editoptions : {
                style : 'width:200px;',
                maxlength : "50"
            },
        },
        /*驾驶证件号*/
        {
            name : 'driverLicenseCode',
            editable : true,
            width : 200,
            editoptions : {
                style : 'width:200px;',
                maxlength : "50"
            },
        },
        /*头像地址*/
        {
            name: 'headImgUrl',
            editable: true,
            hidden: true,
            width: 100,
            editoptions: {
                style: 'width:600px;',
                maxlength: "300",
                colspan: true
            },
        },
        /*备注*/
        {
            name : 'remark',
            editable : true,
            hidden : true,
            width : 100,
            editoptions : {
                style : 'width:600px;',
                colspan : true,
                size : "200",
                maxlength : "200"
            }
        },
        /*状态*/
        {
            name : 'status',
            editable : true,
            hidden : true,
            width : 100,
            edittype : "checkbox",
            editoptions : {
                value : "1:0"
            },
            unformat : aceSwitch,
            renderer : function(value) {
                var rst = "";
                switch (value) {
                    case '1' :
                        rst = "ON";
                        break;
                    case '0' :
                        rst = "OFF";
                        break;
                    default :
                        rst = "N/A";
                }
                return rst;
            }
        },
        {
            name: 'createUserId',
            hidden: true,
            editable: false,
            width: 100
        }, {
            name: 'createUserName',
            hidden: true,
            editable: false,
            width: 100
        }, {
            name: 'createDate',
            hidden: true,
            editable: false,
            width: 100
        }, {
            name: 'lastModifyUserId',
            hidden: true,
            editable: false,
            width: 100
        }, {
            name: 'lastModifyUserName',
            hidden: true,
            editable: false,
            width: 100
        }, {
            name: 'lastModifyDate',
            hidden: true,
            editable: false,
            width: 100
        },
        {
            name: 'opt',
            width: 100,
            hidden: false,
            editable: false,
            sortable: false,
            renderer: function (value, cur) {
                return renderBtn(cur);
            }
        }
    ];
}
function aceSwitch(cellvalue, options, cell) {
	console.log('aceSwitch');
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
function renderBtn(cur) {
	var id = $.jgrid.getAccessor(cur, 'id');
	var title = $.jgrid.getAccessor(cur, 'name');
	var html = [];
	html.push('<a target="_blank" href="');
	html.push('javascript:preview(\'' + id + '\',\'' + title + '\')');
	html.push('"');
	html.push('><span class="badge badge-info">查看</span></a>');
	return html.join(' ');
}
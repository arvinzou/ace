var _colNames = ['主键','卡点名称','卡点编码','所在地区','车道数',
    '检测点数量','卡点详细地址','维度','经度','建造时间','归属道路编码',
    '归属管辖单位','卡点运营状态','备注','状态','创建人编号', '创建人姓名',
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
        /*卡点名称*/
        {
            name : 'name',
            editable : true,
            width : 200,
            editoptions : {
                style : 'width:200px;',
                maxlength : "128",
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
        /*卡点编号*/
        {
            name : 'name',
            editable : true,
            width : 200,
            editoptions : {
                style : 'width:200px;',
                maxlength : "50",
                colspan : false
            },
            editrules : {
                required : false
            }
        },
        /*所在地区编号*/
        {
            name : 'areaCode',
            editable : true,
            hidden : true,
            edittype : "combotree",
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
            width : 100,
            formoptions : {
                elmprefix : "",
                elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
            },
            editrules : {
                required : true
            }
        },
        /*车道数*/
        {
            name : 'roadwayNum',
            editable : true,
            hidden : true,
            editoptions : {
                style : 'width:200px;',
            },
            width : 100,
            editrules : {
                required : false,
                number: true,
            }
        },
        /*检测点数量*/
        {
            name : 'checkpointsNum',
            editable : true,
            hidden : true,
            editoptions : {
                style : 'width:200px;',
            },
            width : 100,
            editrules : {
                required : false,
                number: true,
            }
        },
        /*卡点地址*/
        {
            name : 'address',
            editable : true,
            hidden : true,
            width : 100,
            editoptions : {
                style : 'width:200px;',
                maxlength : "100",
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
        /*维度*/
        {
            name : 'latitude',
            hidden : true,
            editable : true,
            width : 100,
            editoptions : {
                style : 'width:200px;',
                size : "20",
                maxlength : "50"
            },
        },
        /*精度*/
        {
            name : 'longitude',
            hidden : true,
            editable : true,
            width : 100,
            editoptions : {
                style : 'width:200px;',
                size : "20",
                maxlength : "50"
            },
        },
        /*建造时间*/
        {
            name: 'constructDate',
            editable: true,
            width: 50,
            edittype: "datebox",
            formoptions : {
                elmprefix : "",
                elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
            },
            dataoptions: {
                formatter: function (date) {
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
                        + (d < 10 ? ('0' + d) : d);
                },
                parser: function (s) {
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
            renderer: function (value) {
                return value == null ? "" : value.substring(0, 10);
            },
            editrules : {
                required : true
            },
        },
        /*归属道路编号*/
        {
            name : 'roadId',
            editable : true,
            width : 200,
            editoptions : {
                style : 'width:200px;'
            }
        },
        /*归属管辖单位*/
        {
            name : 'adminDepId',
            editable : true,
            width : 200,
            editoptions : {
                style : 'width:200px;'
            }
        },
        /*卡点运行状态*/
        {
            name : 'sitStatus',
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
    setTimeout(function () {
        $(cell).find('input[type=checkbox]').addClass(
            'ace ace-switch ace-switch-5').after(
            '<span class="lbl"></span>');
    }, 0);
}

// enable datepicker
function pickDate(cellvalue, options, cell) {
    setTimeout(function () {
        $(cell).find('input[type=text]').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true
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
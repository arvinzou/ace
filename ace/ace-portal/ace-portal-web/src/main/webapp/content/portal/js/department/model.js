var _colNames = ['基本信息', '编号', '归属部门编号', '部门名称', '简称', '注册日期', '注册资本',
		'注册辖区', '注册地址', '维度', '经度', '经济性质', '营业执照号', '企业类型', '机构类型', '运营地区',
		'状态', '联系人信息', '联系人姓名', '联系人电话', '联系人手机号', '联系人QQ', '联系人邮箱', '法人信息',
		'法定联系人', '法人证件类型', '法定人证件号', '法定人电话', '法定人手机号', '法定人地址', '创建时间','图片资源'];
var _colModel = function() {
	return [
			{
				name : 'title1',
				editable : false,
				hidden : true,
				editoptions : {
					title : true
				}
			},
			{
				name : 'departmentId',
				index : 'id',
				hidden : true,
				width : 150,
				sortable : false,
				editable : false,
				editoptions : {
					readonly : true,
					style : 'width:175px;line-height: 25px;height: 25px;'
				}
			},
			{
				name : 'parentDepartmentId',
				width : 80,
				editable : true,
				hidden : true,
				sorttype : "int",
				editoptions : {
					readonly : true,
					style : 'width:175px;line-height: 25px;height: 25px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'departmentName',
				index : 'departmentName',
				width : 250,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "30",
					style : 'width:175px;line-height: 25px;height: 25px;'
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
				name : 'shortName',
				index : 'shortName',
				width : 150,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "250",
					style : 'width:175px;line-height: 25px;height: 25px;'
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
				name : 'regDate',
				//width : 120,
				editable : false,
				hidden : true,
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
			},
			{
				name : 'regCapital',
				index : 'regCapital',
				width : 150,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:175px;line-height: 25px;height: 25px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span>（万）</span>"
				},
				editrules : {
					required : false
				},
				dataoptions : {
					validType : ['length[0,20]']
				}
			},
			{
				name : 'regAreaCode',
				editable : false,
				hidden : true,
				width : 100,
				edittype : "combotree",
				editoptions : {
					style : 'width:175px;',
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
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				editrules : {
					required : false
				}
			},
			{
				name : 'regAddr',
				editable : false,
				hidden : true,
				width : 100,
				editoptions : {
					style : 'width:175px;',
					maxlength : "50",
					colspan : false
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
				name : 'latitude',
				editable : false,
				hidden : true,
				width : 100,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:175px;line-height: 25px;height: 25px;'
				}
			},
			{
				name : 'longitude',
				editable : false,
				hidden : true,
				width : 100,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:175px;line-height: 25px;height: 25px;'
				}
			},
			{
				name : 'nature',
				index : 'nature',
				width : 80,
				hidden : true,
				editable : false,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "03");
				},
				editoptions : {
					value : odparse("03"),
					style : 'width:175px;line-height: 25px;height: 25px;'
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
				name : 'bussLicenseNo',
				index : 'bussLicenseNo',
				width : 150,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:175px;line-height: 25px;height: 25px;'
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
				name : 'type',
				width : 60,
				hidden : true,
				editable : false,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "04");
				},
				editoptions : {
					value : odparse("04"),
					style : 'width:175px;line-height: 25px;height: 25px;'
				}
			},
			{
				name : 'category',
				width : 60,
				hidden : true,
				editable : false,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "102");
				},
				editoptions : {
					value : odparse("102"),
					style : 'width:175px;line-height: 25px;height: 25px;'
				}
			},
			{
				name : 'areaCode',
				width : 100,
				hidden : true,
				editable : false,
				edittype : "combotree",
				editoptions : {
					style : 'width:175px;line-height: 25px;height: 25px;'
				},
				dataoptions : {
					url : contextPath + '/system/selectProvinceTreeList.do',
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
					required : false
				}
			},
			{
				name : 'status',
				index : 'status',
				width : 90,
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
						case '1' :
							rst = "YES";
							break;
						case '0' :
							rst = "NO";
							break;
						default :
							rst = "N/A";
					}
					return rst;
				}
			},
			{
				name : 'title3',
				editable : false,
				hidden : true,
				editoptions : {
					title : true
				}
			},
			{
				name : 'contactName',
				index : 'contactName',
				width : 150,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:175px;line-height: 25px;height: 25px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				editrules : {
					required : false
				},
				dataoptions : {
					validType : ['chinese', 'length[0,30]']
				}
			},
			{
				name : 'contactTel',
				index : 'contactTel',
				width : 150,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:175px;line-height: 25px;height: 25px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				editrules : {
					required : false
				},
				dataoptions : {
					validType : ['length[0,30]']
				}
			},
			{
				name : 'contactMobile',
				index : 'contactMobile',
				width : 150,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:175px;line-height: 25px;height: 25px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				editrules : {
					required : false
				},
				dataoptions : {
					validType : ['length[0,30]']
				}
			},
			{
				name : 'contactQQ',
				index : 'contactQQ',
				width : 150,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:175px;line-height: 25px;height: 25px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				editrules : {
					required : false
				},
				dataoptions : {
					validType : ['length[0,30]']
				}
			},
			{
				name : 'contactEmail',
				index : 'contactEmail',
				width : 150,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:175px;line-height: 25px;height: 25px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				dataoptions : {
					validType : ['email', 'length[0,30]']
				},
				editrules : {
					required : false
				}
			},
			{
				name : 'title2',
				editable : false,
				hidden : true,
				editoptions : {
					title : true
				}
			},
			{
				name : 'legalPersonName',
				index : 'legalPersonName',
				width : 150,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:175px;line-height: 25px;height: 25px;'
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
				name : 'legalPersonIdType',
				index : 'legalPersonIdType',
				width : 100,
				hidden : true,
				editable : false,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "70");
				},
				editoptions : {
					value : odparse("70"),
					style : 'width:175px;line-height: 25px;height: 25px;'
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
				name : 'legalPersonIdNo',
				index : 'legalPersonIdNo',
				width : 150,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:175px;line-height: 25px;height: 25px;'
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
				name : 'legalPersonTel',
				index : 'legalPersonTel',
				width : 150,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:175px;line-height: 25px;height: 25px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				editrules : {
					required : false
				},
				dataoptions : {
					validType : ['length[0,30]']
				}
			},
			{
				name : 'legalPersonMobile',
				index : 'legalPersonMobile',
				width : 150,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:175px;line-height: 25px;height: 25px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				editrules : {
					required : false
				},
				dataoptions : {
					validType : ['length[0,30]']
				}
			},
			{
				name : 'legalPersonAddr',
				index : 'legalPersonAddr',
				width : 150,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50",
					style : 'width:550px;line-height: 25px;height: 25px;',
					colspan:true
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				editrules : {
					required : false
				},
				dataoptions : {
					validType : ['length[0,200]']
				}
			}, {
				name : 'createTime',
				width : 150,
				sortable : true,
				editable : false
			},{
              				name : 'title5',
              				editable : false,
              				hidden : true,
              				editoptions : {
              					title : true
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
// enable datepicker
function pickDate(cellvalue, options, cell) {
	setTimeout(function() {
		$(cell).find('input[type=text]').datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true
		});
	}, 0);
}
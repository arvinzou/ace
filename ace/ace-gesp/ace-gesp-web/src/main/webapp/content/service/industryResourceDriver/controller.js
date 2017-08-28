jQuery(function($) {


	$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
				});
				$.extend(params, {
					time : new Date()
				});
				// console.log(params);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1,
					postData : params
				}).trigger("reloadGrid");
				return false;
			}
		});
	});

	
	$('#btn-view-edit').on('click', function() {
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
		var grName = jQuery(cfg.grid_selector).jqGrid('getCell',gr, 'name');
		if (gr) {
			//console.log(gr+"-----------------------------"+grName);
			updateDriverInfo(gr,grName);
		}else{
			alert("请选择您要修改的数据");
		}
	});

	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;'
			if (("title_html" in this.options)
					&& this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));
	

	setValues('updateDriverInfo','sex','01');//性别
	setValues('updateDriverInfo','certPersonType',78);//从业人员类别
	setValues('updateDriverInfo','certType',73);//从业资格类别
	setValues('updateDriverInfo','filingCertType',74);//备案资格类别
	setValues('updateDriverInfo','driverLicCarType',77);//准驾车型
	setValues('updateDriverInfo','certCompanyName',76);//从业发证机构
	setValues('updateDriverInfo','cooperationMode',75);//合作方式
	setValues('updateDriverInfo','recordTime',79);//备案状态
});

function setValues(id,name,cateId){
	$("#"+id).find('input[name='+name+']').combobox(
			{
				url : portalPath +'/dict/findListByCategoryId.do?categoryId='+cateId+'&selected=false',
				method:'get',
				editable:false,
                valueField:'code',
                textField:'name',
                panelHeight:'auto'
			});
}


var dialog = {};
function updateDriverInfo(id,name) {
	$('#updateDriverInfo').form('clear');
	dialog = $("#dialog-message").removeClass('hide')
		.dialog({
			modal : true,
			width : 900,
			title : "<div class='widget-header widget-header-small'><div class='widget-header-pd' >"+name+"</div></div>",
			title_html : true,
			buttons : [
				{
					html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id : 'btn-pay',
					click : function() {
						var form = $('#updateDriverInfo');
						var params = form.serializeObject();
						$.ajax({
							type : "post",
							url : contextPath + "/industryResource/updateDriverInfoById.do",
							data :  {
								jsons : JSON.stringify(params)
							},
							beforeSend : function(XMLHttpRequest) {
							},
							success : function(rst, textStatus) {
								$("#dialog-message").dialog("close");
								parent.reloadGrid();
								if (rst) {
									bootbox.dialog({
										title : '系统提示',
										message : rst.errorMessage,
										detail : rst.detail,
										buttons : {
											"cancel" : {
												"label" : "<i class='ace-icon fa fa-check'></i>确定",
												"className" : "btn-sm btn-success",
												"callback" : function() {
													
												}
											}
										}
									});
								}
							},
							complete : function(XMLHttpRequest, textStatus) {},
							error : function() {
								alert("修改有误！");
							}
						});
					}
				},
				{
					html : "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
					"class" : "btn btn-xs",
					click : function() {
						$(this).dialog("close");
					}
				} ]
		});
	
	$.ajax({
		type : "post",
		url : contextPath + "/industryResource/selectDriverInfoById.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var r = rst.value;
			$('#recordTime').combobox('setValue', r.recordTime);
			$('#sex').combobox('setValue', r.sex);
			$('#birthdate').datebox('setValue', r.birthdate);
			$('#initDrivingLicDate').datebox('setValue', r.initDrivingLicDate);
			$('#certPersonType').combobox('setValue', r.certPersonType);
			$('#certType').combobox('setValue', r.certType);
			$('#filingCertType').combobox('setValue', r.filingCertType);
			$('#driverLicCarType').combobox('setValue', r.driverLicCarType);
			$('#entryTime').datebox('setValue', r.entryTime);
			$('#certCompanyName').combobox('setValue', r.certCompanyName);
			$('#cooperationMode').combobox('setValue', r.cooperationMode);
			$('#tel').numberbox('setValue', r.tel);
			$.each(r, function(key, value) {
				$("#updateDriverInfo").find('input[name='+key+']').val(value);
			});
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("请重新打开！");
		}
	});
}
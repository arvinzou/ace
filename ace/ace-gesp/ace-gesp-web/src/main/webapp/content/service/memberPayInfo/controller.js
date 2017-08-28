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
	$('#fm-dialog').ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			console.log(params);
			pay(params);
			return false;
		}
	});
});
var dialog = {};
function showPayDia(memberCode, status, departmentName,memberLevel,payStatus) {
	if (status != 1) {
		alert("未审核的数据！");
		return;
	}
	if(payStatus==0){
		bootbox.dialog({
			title : '系统提示',
			message : "正在缴费确认中，请先完成当前缴费确认，再进行新的收费操作",
			buttons : {
				"cancel" : {
					"label" : "<i class='ace-icon fa fa-check'></i>确定",
					"className" : "btn-sm btn-success",
					"callback" : function() {
						
					}
				}
			}
		});
		return ;
	}
	$.ajax({
		type : "post",
		url : contextPath + "/payCfg/selectCount.do",
		data : {
			memberCode : memberCode,
			chargingItemId : 1,
			status: 0
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			if (rst.status!=0) {
				alert(rst.errorMessage);
				return;
			}else{
				$('#fm-dialog').form('clear');
				dialog = $("#dialog-message")
					.removeClass('hide').dialog({
							modal : true,
							width : 600,
							title : "<div class='widget-header widget-header-small'><div class='widget-header-pd' >收款</div></div>",
							title_html : true,
							buttons : [
									{
										html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
										"class" : "btn btn-info btn-xs",
										id : 'btn-pay',
										click : function() {
											params.memberCode = memberCode;
											params.chargingItemId = $('#cc').combotree('getValue');
											$('#fm-dialog').submit();
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
				loadPayInfo(memberLevel,memberCode,departmentName);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {

		}
	});
	
}
var params = {};
function beforeSubmit() {
	return false;
}
function pay(params) {
	console.log(params);
	$.ajax({
		type : "post",
		url : contextPath + "/memberPayInfo/insertMemberPayInfo.do",
		data : {
			jsons : JSON.stringify(params)
		},
		beforeSend : function(XMLHttpRequest) {
			$('#btn-pay').attr('disabled', "true");
		},
		success : function(rst, textStatus) {
			$('#btn-pay').removeAttr("disabled");
			bootbox.dialog({
				title : '系统提示',
				message : rst.errorMessage,
				detail : rst.detail,
				buttons : {
					"cancel" : {
						"label" : "<i class='ace-icon fa fa-check'></i>确定",
						"className" : "btn-sm btn-success",
						"callback" : function() {
							$(dialog).dialog("close");
						}
					}
				}
			});
			jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger(
					"reloadGrid");
		},
		complete : function(XMLHttpRequest, textStatus) {
			$('#btn-pay').removeAttr("disabled");
		},
		error : function() {
			$('#btn-pay').removeAttr("disabled");
		}
	});
}

/*收费信息初始化*/
function loadPayInfo(memberLevel,memberCode,departmentName){
	$("#cc").combotree({
		onChange : function(newValue, oldValue) {
			getPayInfo(memberCode,$('input[name=memberLevelId]').val(), newValue,'');
		},
		onLoadSuccess:function(node, data){
			$.ajax({
				type : "post",
				url : contextPath + "/payCfg/getPayInfo.do",
				data : {
					memberCode : memberCode,
					memberLevelId : memberLevel,
					chargingItemId : '1'
				},
				beforeSend : function(XMLHttpRequest) {

				},
				success : function(rst, textStatus) {
					if (rst.state) {
						var chargingItem = rst.value.chargingItemId;
						$("#cc").combotree('setValue', chargingItem);
						$('#endDate').datebox('setValue', rst.value.endDate);
						$('#payAmount').val(rst.value.payAmount);
						$('#memberLevelId').combobox('setValue', rst.value.memberLevelId);
						$("#cc").combotree("disable");
						/*$.ajax({
							type : "post",
							url : contextPath + "/memberPayInfo/isExitPayInfo.do",
							data : {
								memberCode : memberCode,
								chargingItemId : chargingItem,
								status : 0
							},
							beforeSend : function(XMLHttpRequest) {

							},
							success : function(rst, textStatus) {
								if(rst.status!=0){
									
									$("#dialog-message").dialog("close");
								}
							},
							complete : function(XMLHttpRequest, textStatus) {
							},
							error : function() {

							}
						});*/
					} else {
						$("#cc").combotree('setValue', "");
						alert("请在收费配置中设置收费金额！");
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
				},
				error : function() {

				}
			});
		}
	});
	$("#fm-dialog").find('input[name=memberLevelId]')
		.combobox({
					url : contextPath + '/memberLevel/selectListByDeptId.do?selected=true',
					valueField : 'code',
					textField : 'name',
					onLoadSuccess : function() {
						//alert(memberLevel);
						$("#fm-dialog").find('input[name=memberLevelId]').val(memberLevel);
						//getPayInfo(memberCode, $('input[name=memberLevelId]').val(), $('input[name=chargingItemId]').val());
					},
					onSelect : function(record) {
						console.log(record);
						var t = $('#cc').combotree('tree');	// get the tree object
						var n = t.tree('getSelected');
						getPayInfo(memberCode, record.code, n.id,'2');
					}
				});
	$('input[name=departmentName]').val(departmentName);
	$('#tt').datetimebox('setValue',nowTime);	
	$('input[name=createUserName]').val(userProp.name);
}


/*获取收费金额*/
function getPayInfo(memberCode, memberLevelId, chargingItemId,flag) {
	console.log(params);
	$.ajax({
		type : "post",
		url : contextPath + "/payCfg/getPayInfo.do",
		data : {
			memberCode : memberCode,
			memberLevelId : memberLevelId,
			chargingItemId : chargingItemId
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			//alert( JSON.stringify(rst));
			if (rst.state) {
				//$('#fm-dialog').form('load', rst.value);
				$('#endDate').datebox('setValue', rst.value.endDate);
				$('#payAmount').val(rst.value.payAmount);
				if(!flag){
					$('#memberLevelId').combobox('setValue', rst.value.memberLevelId);
				}
				
			} else {
				alert(rst.errorMessage);
			}

		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {

		}
	});
}

/*查看会员详细信息*/
function lookMemberInfo(id,status,departmentName,memberCode){
	parent.addPanel(departmentName,contextPath+'/dynamic/service/memberCenter/index.jsp?deptId='+memberCode,true);
}
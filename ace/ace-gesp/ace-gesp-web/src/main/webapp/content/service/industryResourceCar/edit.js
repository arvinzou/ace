function setValues(id,name,cateId){
	$("#"+id).find('input[name='+name+']').combobox(
			{
				url : portalPath +'/dict/findListByCategoryId.do?categoryId='+cateId+'&selected=false',
				method:'get',
                valueField:'code',
                editable :false,
                textField:'name',
                panelHeight:'200'
			});
}

jQuery(function($) {
	$('#btn-editCarInfo').on('click', function() {
		
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
	setValues('tab1','plateColor','90');
	setValues('tab1','bussType','89');
	setValues('tab1','bussStatus','88');
	setValues('tab1','isGAT','91');
	setValues('tab1','insuranceCompany','83');
	setValues('tab1','isInstallOBD','91');
	setValues('tab1','isInstallGPS','91');
	setValues('tab1','fuelType','87');
	setValues('tab1','transportMode','86');
	setValues('tab2','carCategory','84');
	setValues('tab2','carType','86');
	setValues('tab2','carUseType','87');
/*	setValues('tab2','carStatus','88');
	setValues('tab3','freeInspection','82');
	setValues('tab3','fuel','82');
	setValues('tab3','exempt','82');
	setValues('tab3','green','82');
	setValues('tab3','fuelType','87');
	setValues('tab3','steeringType','93');*/

	loadView(id);
	loadViewCarProduction(id);

});
//车辆基本信息
function loadView(id) {
	$.ajax({
		type : "post",
		url : contextPath + "/industryResource/selectCarInfoById.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var r = rst.value;
			$.each(r, function(key, value) {
				$("#tab1").find('input[name='+key+']').val(value);
			});
			//loadViewCarType(rst.value.carType);
			$('#plateColor').combobox('setValue',r.plateColor);//车牌颜色
			$("#drivingLicCreatetime").datebox('setValue', r.drivingLicCreatetime);//行驶证登记时间
			$("#bussType").combobox('setValue', r.bussType);//经营方式
			$("#isGAT").combobox('setValue', r.isGAT);//是否港运通
			$("#insuranceCompany").combobox('setValue', r.insuranceCompany);//保险公司
			$("#coverage").numberbox('setValue', r.coverage);//保额
			$("#insuranceDate").datebox('setValue', r.insuranceDate);//投保时间
			$("#lastComperformCheckDate").datebox('setValue', r.lastComperformCheckDate);//上次综合性能检测时间
			$("#lastClassIIDate").datebox('setValue', r.lastClassIIDate);//上次二级维护时间
			$("#isInstallOBD").combobox('setValue', r.isInstallOBD);//是否安装OBD
			$("#isInstallGPS").combobox('setValue', r.isInstallGPS);//是否安装GPS
			$("#fuelType").combobox('setValue', r.fuelType);//燃料类型
			$("#validEndDate").datebox('setValue', r.validEndDate);//有效期止
			$("#scrapEndDate").datebox('setValue', r.scrapEndDate);//强制报废期至
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("加载错误！");
		}
	});
}
//车辆出厂信息
function loadViewCarProduction(id) {
	$.ajax({
		type : "post",
		url : contextPath + "/industryResource/selectCarProductionInfoById.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var r = rst.value;
			$.each(r, function(key, value) {
				/*if (key == 'legalPersonIdType') {
					value = rsd(value, '70');
				}
				if (key == 'carCategory') {
					value = rsd(value, '84');
				}
				if (key == 'carType') {
					value = rsd(value, '86');
				}
				if (key.indexOf('Date')!=-1||key.indexOf('time')!=-1||key.indexOf('Time')!=-1) {
					value = Common.DateFormatter(value);
				}*/
				$("#tab2").find('input[name='+key+']').val(value);
			});

			$('#carCategory').combobox('setValue',r.carCategory);//车辆种类
			$('#carTypes').combobox('setValue',r.carType);//车辆类型
			$('#carUseType').combobox('setValue',r.carUseType);//燃料类型
			$('#totalMass').numberbox('setValue',r.totalMass);//总质量
			$('#loadQuality').numberbox('setValue',r.loadQuality);//载质量
			$('#factoryTime').datebox('setValue',r.factoryTime);//出厂时间
			$('#batteryGroupTotalCapacity').numberbox('setValue',r.batteryGroupTotalCapacity);//电池组总容量(kWh)
			$('#batteryKeepYears').numberbox('setValue',r.batteryKeepYears);//电池质保年限
			$('#motorTypeNo').numberbox('setValue', r.motorTypeNo);//额定功率
			$('#motorKeepYears').numberbox('setValue', r.motorKeepYears);//电机质保年限
			//$('#carStatus').combobox('setValue', r.carStatus);//运营状态
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("加载错误！");
		}
	});
}
//车辆车型信息
function loadViewCarType(id) {
	$.ajax({
		type : "post",
		url : contextPath + "/industryResource/selectCarTypeInfoById.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var va = rst.value;
			if(va){
				$.each(va, function(key, value) {
					/*if (key == 'legalPersonIdType') {
						value = rsd(value, '70');
					}
					if (key.indexOf('Date')!=-1||key.indexOf('time')!=-1||key.indexOf('Time')!=-1) {
						value = Common.DateFormatter(value);
					}*/

					$("#tab3").find('input[name='+key+']').val(value);
					
				});
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("加载错误！");
		}
	});
}

function updateCarInfo_save(formId1,formId2){
	var form = $(formId1);
	var params = form.serializeObject();
	$.ajax({
		type : "post",
		url : contextPath + "/industryResource/updateCarTypeInfoById.do",
		data : {
			jsons : JSON.stringify(params)
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			form = $(formId2);
			params = form.serializeObject();
			$.ajax({
				type : "post",
				url : contextPath + "/industryResource/updateCarProductionInfoById.do",
				data :  {
					jsons : JSON.stringify(params)
				},
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(rst, textStatus) {
					alert(rst.errorMessage);
					parent.reloadGrid();
					parent.removePanel();
				},
				complete : function(XMLHttpRequest, textStatus) {},
				error : function() {
					alert("修改有误！");
				}
			});
		},
		complete : function(XMLHttpRequest, textStatus) {},
		error : function() {
			alert("修改有误！");
		}
	});
}



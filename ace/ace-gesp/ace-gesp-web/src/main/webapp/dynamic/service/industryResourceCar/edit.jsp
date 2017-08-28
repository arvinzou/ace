<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>industryResourceCar</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">
	var id = '${param.id}';
</script>
<body>
		<div class="page-content">
			<ul class="nav nav-tabs padding-18">
				<li class="active"><a data-toggle="tab" href="#tab1"> <i
						class="blue ace-icon fa fa-bars bigger-120"></i> 车辆基础信息
				</a></li>
				<li class=""><a data-toggle="tab" href="#tab2"> <i
						class="blue ace-icon fa fa-bars bigger-120"></i>车辆出厂信息
				</a></li>
				<%-- <li class=""><a data-toggle="tab" href="#tab3"> <i
						class="blue ace-icon fa fa-bars bigger-120"></i> 车辆车型信息
				</a></li> --%>
			</ul>
			<div class="tab-content no-border padding-24">
				<div id="tab1" class="tab-pane active">
					<form id="update1Form" method="post" novalidate>
						<fieldset>
							<div class="profile-user-info profile-user-info-striped">
								<div class="profile-info-row">
									<div class="profile-info-name">车牌</div>
									<div class="profile-info-value">
										<input type="hidden" name="id" id="id"/>
										<input style="width:220px" name="plateNumber" id="plateNumber" readonly="readonly"/>
									</div>
									<div class="profile-info-name">车牌颜色</div>
									<div class="profile-info-value">
										<input id="plateColor" style="width:220px" name="plateColor" readonly="readonly"/>
									</div>
									<div class="profile-info-name">车辆识别代码(VIN)</div>
									<div class="profile-info-value">
										<input id="vin" style="width:220px" name="vin"/>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">车辆型号</div>
									<div class="profile-info-value">
										<input id="carType" style="width:220px" name="carType"/>
									</div>
									<div class="profile-info-name">车辆类型</div>
									<div class="profile-info-value">
										<input id="transportMode" style="width:220px" name="transportMode"/>
									</div>
									<div class="profile-info-name">燃料类型</div>
									<div class="profile-info-value">
										<input id="fuelType" style="width:220px" name="fuelType"/>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">行驶证登记时间</div>
									<div class="profile-info-value">
										<input class="easyui-datebox" name="drivingLicCreatetime"  readonly="readonly"
											style="width:220px;" id="drivingLicCreatetime" />
									</div>
									<div class="profile-info-name">所有企业名称</div>
									<div class="profile-info-value">
										<input id="ownerName" style="width:220px" name="ownerName" readonly="readonly"/>
									</div>
									<div class="profile-info-name">经营方式</div>
									<div class="profile-info-value">
										<input id="bussType" style="width:220px" name="bussType"/>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">上次综合性能检测时间</div>
									<div class="profile-info-value">
										<input id="lastComperformCheckDate" class="easyui-datebox" style="width:220px" name="lastComperformCheckDate"/>
									</div>
									<div class="profile-info-name">上次二级维护时间</div>
									<div class="profile-info-value">
										<input id="lastClassIIDate" class="easyui-datebox" style="width:220px" name="lastClassIIDate"/>
									</div>
									<div class="profile-info-name">营运证号</div>
									<div class="profile-info-value">
										<input id="transportNo" style="width:220px" name="transportNo"/>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">保险公司</div>
									<div class="profile-info-value">
										<input id="insuranceCompany" style="width:220px" name="insuranceCompany"/>
									</div>
									<div class="profile-info-name">保额</div>
									<div class="profile-info-value">
										<input id="coverage" class="easyui-numberbox" style="width:220px" name="coverage" />
									</div>
									<div class="profile-info-name">投保时间</div>
									<div class="profile-info-value">
										<input class="easyui-datebox" id="insuranceDate" style="width:220px" name="insuranceDate"/>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">有效期止</div>
									<div class="profile-info-value">
										<input id="validEndDate" class="easyui-datebox" style="width:220px" name="validEndDate" />
									</div>
									<div class="profile-info-name">强制报废期至</div>
									<div class="profile-info-value">
										<input id="scrapEndDate" class="easyui-datebox" style="width:220px" name="scrapEndDate" />
									</div>
									<div class="profile-info-name">通行证</div>
									<div class="profile-info-value">
										<input id="passNo" style="width:220px" name="passNo"/>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">是否港运通</div>
									<div class="profile-info-value">
										<input id="isGAT" style="width:220px" name="isGAT"/>
									</div>
									<div class="profile-info-name">港运通证号</div>
									<div class="profile-info-value">
										<input id="gATNo" style="width:220px" name="gATNo"/>
									</div>
									<div class="profile-info-name">是否安装OBD</div>
									<div class="profile-info-value">
										<input id="isInstallOBD" style="width:220px" name="isInstallOBD" />
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">ODB设备号</div>
									<div class="profile-info-value">
										<input id="odbDeviceId" style="width:220px" name="odbDeviceId" />
									</div>
									<div class="profile-info-name">是否安装GPS</div>
									<div class="profile-info-value">
										<input id="isInstallGPS" style="width:220px" name="isInstallGPS"/>
									</div>
									<div class="profile-info-name">制造厂商</div>
									<div class="profile-info-value">
										<input id="makerName" style="width:220px" name="makerName" />
									</div>
								</div>
								<%-- <div class="profile-info-row">
									<div class="profile-info-name">电池组号</div>
									<div class="profile-info-value">
										<input id="batteryGroupNo" style="width:220px" name="batteryGroupNo"/>
									</div>
									<div class="profile-info-name">电机号</div>
									<div class="profile-info-value">
										<input id="motorNo" style="width:220px" name="motorNo"/>
									</div>
								</div> --%>
							</div>
						</fieldset>
					</form>
				</div>
				<div id="tab2" class="tab-pane">
					<form id="update2Form" method="post" novalidate>
						<fieldset>
							<div class="profile-user-info profile-user-info-striped">
								<div class="profile-info-row">
									<div class="profile-info-name">车辆唯一编码</div>
									<div class="profile-info-value">
										<input type="hidden" name="id" id="id"/>
										<input id="identiCode" name="identiCode" style="width:220px;"/>
									</div>
									<div class="profile-info-name">车辆种类</div>
									<div class="profile-info-value">
										<input id="carCategory" style="width:220px;" name="carCategory"/> 
									</div>
									<div class="profile-info-name">车辆类型</div>
									<div class="profile-info-value">
										<input id="carTypes" style="width:220px;" name="carType"/> 
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">燃料类型</div>
									<div class="profile-info-value">
										<input id="carUseType" style="width:220px;" name="carUseType"/>
									</div>
									<div class="profile-info-name">车辆识别代码(VIN)</div>
									<div class="profile-info-value">
										<input id="vin" name="vin" style="width:220px;"/>
									</div>
									<div class="profile-info-name">出厂时间</div>
									<div class="profile-info-value">
										<input id="factoryTime" class="easyui-datebox" name="factoryTime" style="width:220px;" />
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">补贴标识</div>
									<div class="profile-info-value">
										<input id="subsidyId" name="subsidyId" style="width:220px;"/>
									</div>
									<div class="profile-info-name">外型尺寸(长/宽/高)</div>
									<div class="profile-info-value">
										<input id="overallSize" name="overallSize" style="width:220px;"/>
									</div>
									<div class="profile-info-name">总质量(Kg)</div>
									<div class="profile-info-value">
										<input id="totalMass" class="easyui-numberbox" name="totalMass" style="width:220px;" data-options="min:0,precision:2"/>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">载质量</div>
									<div class="profile-info-value">
										<input id="loadQuality" class="easyui-numberbox" name="loadQuality" style="width:220px;" data-options="min:0,precision:2">
									</div>
									<div class="profile-info-name">货箱尺寸(长/宽/高)</div>
									<div class="profile-info-value">
										<input id="bodySize" name="bodySize" style="width:220px;"/>
									</div>
									<div class="profile-info-name">车身颜色</div>
									<div class="profile-info-value">
										<input id="bodyColor" name="bodyColor" style="width:220px;"/>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">单体型号</div>
									<div class="profile-info-value">
										<input id="batteryUnitTypeNo" name="batteryUnitTypeNo" style="width:220px;"/>
									</div>
									<div class="profile-info-name">单体生产企业</div>
									<div class="profile-info-value">
										<input id="batteryUnitMakerName" name="batteryUnitMakerName" style="width:220px;"/>
									</div>
									<div class="profile-info-name">成箱型号</div>
									<div class="profile-info-value">
										<input id="batteryBoxTypeNo" name="batteryBoxTypeNo" style="width:220px;"/>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">电池组号</div>
									<div class="profile-info-value">
										<input id="batteryGroupNo" name="batteryGroupNo" style="width:220px;"/>
									</div>
									<div class="profile-info-name">电池组总容量(kWh)</div>
									<div class="profile-info-value">
										<input id="batteryGroupTotalCapacity" class="easyui-numberbox" name="batteryGroupTotalCapacity" style="width:220px;" data-options="min:0,precision:0"/>
									</div>
									<div class="profile-info-name">电池组生成企业</div>
									<div class="profile-info-value">
										<input id="batteryGroupMakerName" name="batteryGroupMakerName" style="width:220px;"/>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">电池质保年限</div>
									<div class="profile-info-value">
										<input id="batteryKeepYears" name="batteryKeepYears" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:0" />
									</div>
									<div class="profile-info-name">电机号</div>
									<div class="profile-info-value">
										<input id="motorNo" name="motorNo" style="width:220px;"/>
									</div>
									<div class="profile-info-name">电机型号</div>
									<div class="profile-info-value">
										<input id="motorTypeNo" name="motorTypeNo" style="width:220px;"/>
									</div>
								</div>
								<div class="profile-info-row">
									<%-- <div class="profile-info-name">生产企业</div>
									<div class="profile-info-value">
										<input id="motorMakerName"></span>
									</div> --%>
									<div class="profile-info-name">电机质保年限</div>
									<div class="profile-info-value">
										<input id="motorKeepYears" name="motorKeepYears" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:0"/>
									</div>
									<div class="profile-info-name">额定功率(kW)</div>
									<div class="profile-info-value">
										<input id="motorRatedPower" name="motorRatedPower" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:2" />
									</div>
									<%-- <div class="profile-info-name">运营状态</div>
									<div class="profile-info-value">
										<input id="carStatus" style="width:220px" name="carStatus"/>
									</div>
								</div>
								<div class="profile-info-row"> --%>
									<div class="profile-info-name">生产企业名称</div>
									<div class="profile-info-value">
										<input id="makerName" name="makerName" style="width:220px;"/>
									</div>
								</div>
							</div>
						<fieldset>
					</form>
				</div>
				<%-- <div id="tab3" class="tab-pane">
					<form id="fm3" onsubmit="return before3Submit()">
					<fieldset>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">id</div>
							<div class="profile-info-value">
								<input type="hidden" id="id" name="id"/>
							</div>
							<div class="profile-info-name">识别代号</div>
							<div class="profile-info-value">
								<input type="hidden" id="id" name="id"/>
								<input id="identiCode" name="identiCode" style="width:220px;"/>
							</div>
							<div class="profile-info-name">车辆型号</div>
							<div class="profile-info-value">
								<input id="carCode" name="carCode" style="width:220px;" />
							</div>
							<div class="profile-info-name">车辆名称</div>
							<div class="profile-info-value">
								<input id="carName" name="carName" style="width:220px;" />
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">车辆类别</div>
							<div class="profile-info-value">
								<input id="carType" name="carType" style="width:220px;" />
							</div>
							<div class="profile-info-name">车型图片Id</div>
							<div class="profile-info-value">
								<input id="imageFileIds"></span>
							</div>
							<div class="profile-info-name">是否免检</div>
							<div class="profile-info-value">
								<input id="freeInspection" style="width:220px;" name="freeInspection"/>
							</div>
							<div class="profile-info-name">是否燃油</div>
							<div class="profile-info-value">
								<input id="fuel" style="width:220px;" name="fuel"/>
							</div>
							<div class="profile-info-name">是否免征</div>
							<div class="profile-info-value">
								<input id="exempt" style="width:220px;" name="exempt"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">是否环保</div>
							<div class="profile-info-value">
								<input id="green" style="width:220px;" name="green"/>
							</div>
							<div class="profile-info-name">公告批次</div>
							<div class="profile-info-value">
								<input id="noticeBatches" name="noticeBatches" style="width:220px;" />
							</div>
							<div class="profile-info-name">中文品牌</div>
							<div class="profile-info-value">
								<input id="chineseBrand" name="chineseBrand" style="width:220px;" />
							</div>
						</div>
						<!-- <div class="profile-info-row">
							<div class="profile-info-name">目录序号</div>
							<div class="profile-info-value">
								<input id="directoryNum"></span>
							</div>
						</div> -->
						<div class="profile-info-row">
							<div class="profile-info-name">生产厂家</div>
							<div class="profile-info-value">
								<input id="carMakerName" name="carMakerName" style="width:220px;" />
							</div>
							<div class="profile-info-name">发动机型号</div>
							<div class="profile-info-value">
								<input id="engineModel" name="engineModel" style="width:220px;" />
							</div>
							<div class="profile-info-name">发动机功率</div>
							<div class="profile-info-value">
								<input id="engineCapacity" name="engineModel" style="width:220px;" />
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">发动机排量</div>
							<div class="profile-info-value">
								<input id="engineDisplacement" name="engineDisplacement" style="width:220px;"/>
							</div>
							<div class="profile-info-name">发动机生产商</div>
							<div class="profile-info-value">
								<input id="engineMakerName" name="engineMakerName" style="width:220px;"/>
							</div>
							<div class="profile-info-name">发动机商标</div>
							<div class="profile-info-value">
								<input id="engineBrand" name="engineBrand" style="width:220px;"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">燃料种类</div>
							<div class="profile-info-value">
								<input id="fuelType" style="width:220px;" name="fuelType"/>
							</div>
							<div class="profile-info-name">外型尺寸(长/宽/高)</div>
							<div class="profile-info-value">
								<input id="overallSize" name="overallSize" style="width:220px;"/>
							</div>
							<div class="profile-info-name">货厢(长/宽/高)</div>
							<div class="profile-info-value">
								<input id="bodySize" name="bodySize" style="width:220px;"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">总质量(Kg)</div>
							<div class="profile-info-value">
								<input id="totalMass" name="totalMass" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:2"/>
							</div>
							<div class="profile-info-name">载质量</div>
							<div class="profile-info-value">
								<input id="loadQuality" name="loadQuality" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:2"/>
							</div>
							<div class="profile-info-name">整备质量(kg)</div>
							<div class="profile-info-value">
								<input id="SetupQuality" name="SetupQuality" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:2"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">额定质量(kg)</div>
							<div class="profile-info-value">
								<input id="ratedQuality" name="ratedQuality" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:2"/>
							</div>
							<div class="profile-info-name">挂车质量(kg)</div>
							<div class="profile-info-value">
								<input id="trailerQuality" name="trailerQuality" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:2"/>
							</div>
							<div class="profile-info-name">半挂鞍座(kg)</div>
							<div class="profile-info-value">
								<input id="semitrailer" name="semitrailer" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:2"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">额定载客(人)</div>
							<div class="profile-info-value">
								<input id="ratedCapacity" name="ratedCapacity" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:0"/>
							</div>
							<div class="profile-info-name">前排乘客(人)</div>
							<div class="profile-info-value">
								<input id="frontCapacity" name="frontCapacity" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:0"/>
							</div>
							<div class="profile-info-name">驾驶室(人)</div>
							<div class="profile-info-value">
								<input id="cab" name="cab" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:0"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">发布日期</div>
							<div class="profile-info-value">
								<input id="releaseDate" name="releaseDate" style="width:220px;" class="easyui-datebox"/>
							</div>
							<div class="profile-info-name">前悬</div>
							<div class="profile-info-value">
								<input id="frontOverhang" name="frontOverhang" style="width:220px;"/>
							</div>
							<div class="profile-info-name">后悬</div>
							<div class="profile-info-value">
								<input id="rearOverhang" name="rearOverhang" style="width:220px;"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">接近角</div>
							<div class="profile-info-value">
								<input id="approachAngle" name="approachAngle" style="width:220px;"/>
							</div>
							<div class="profile-info-name">离去角</div>
							<div class="profile-info-value">
								<input id="departureAngle" name="departureAngle" style="width:220px;"/>
							</div>
							<div class="profile-info-name">轴距(mm)</div>
							<div class="profile-info-value">
								<input id="wheelbase" name="wheelbase" style="width:220px;"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">轴荷</div>
							<div class="profile-info-value">
								<input id="axleLoad" name="axleLoad" style="width:220px;"/>
							</div>
							<div class="profile-info-name">轴数</div>
							<div class="profile-info-value">
								<input id="axleCount" name="axleCount" style="width:220px;"/>
							</div>
							<div class="profile-info-name">最高车速 (km/h)</div>
							<div class="profile-info-value">
								<input id="MaximumSpeed" name="cab" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:2"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">油耗</div>
							<div class="profile-info-value">
								<input id="fuelConsumption" name="cab" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:2"/>
							</div>
							<div class="profile-info-name">弹簧片数</div>
							<div class="profile-info-value">
								<input id="leafSpringCount" name="leafSpringCount" style="width:220px;"/>
							</div>
							<div class="profile-info-name">轮胎数</div>
							<div class="profile-info-value">
								<input id="tireCount" name="tireCount" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:0"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">轮胎规格</div>
							<div class="profile-info-value">
								<input id="tireSpec" name="tireSpec" style="width:220px;"/>
							</div>
							<div class="profile-info-name">前轮距</div>
							<div class="profile-info-value">
								<input id="frontGauge" name="cab" style="width:220px;" class="easyui-numberbox" data-options="min:0,precision:2"/>
							</div>
							<div class="profile-info-name">制动前</div>
							<div class="profile-info-value">
								<input id="brakeFront" name="brakeFront" style="width:220px;"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">制动后</div>
							<div class="profile-info-value">
								<input id="brakeAfter" name="brakeAfter" style="width:220px;"/>
							</div>
							<div class="profile-info-name">制操前</div>
							<div class="profile-info-value">
								<input id="operationFront" name="operationFront" style="width:220px;"/>
							</div>
							<div class="profile-info-name">制操后</div>
							<div class="profile-info-value">
								<input id="operationAfter" name="operationAfter" style="width:220px;"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">转向形式</div>
							<div class="profile-info-value">
								<input id="steeringType" name="steeringType" style="width:220px;"/>
							</div>
							<div class="profile-info-name">起动方式</div>
							<div class="profile-info-value">
								<input id="startType" name="startType" style="width:220px;"/>
							</div>
							<div class="profile-info-name">传动型式</div>
							<div class="profile-info-value">
								<input id="drivingType" name="drivingType" style="width:220px;"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">依据标准</div>
							<div class="profile-info-value">
								<input id="basicStandard" name="basicStandard" style="width:220px;"/>
							</div>
							<div class="profile-info-name">底盘依据标准</div>
							<div class="profile-info-value">
								<input id="chassisBasicStandard" name="chassisBasicStandard" style="width:220px;"/>
							</div>
							<div class="profile-info-name">后轮距</div>
							<div class="profile-info-value">
								<input id="rearTrack" name="rearTrack" style="width:220px;"/>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">备注</div>
							<div class="profile-info-value">
								<input id="remark" name="remark" style="width:220px;"/>
							</div>
							<div class="profile-info-name">删除状态</div>
							<div class="profile-info-value">
								<input id="deleteMark"></span>
							</div>
						</div>
					</div>
				<fieldset>
				</form>
			</div> --%>
		</div>
		<div align="center">
			<a class="btn btn-info" id="btn-editCarInfo" href="#" authority="false" style="width:90px;font-size:16px;" onclick="javascript:updateCarInfo_save('#update1Form','#update2Form')">提交</a>
		</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/industryResourceCar/edit.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/common/js/jquery.serializeObject.min.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />

</body>
</html>
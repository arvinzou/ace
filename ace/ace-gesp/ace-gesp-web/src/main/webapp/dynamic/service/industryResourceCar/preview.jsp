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
				<fieldset>
					<legend>基本信息</legend>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">车牌</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="plateNumber"></span>
							</div>
							<div class="profile-info-name">车牌颜色</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="plateColor"></span>
							</div>
							<div class="profile-info-name">车辆识别代码(VIN)</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="vin"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">车辆型号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="carType"></span>
							</div>
							<div class="profile-info-name">车辆类型</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="transportMode"></span>
							</div>
							<div class="profile-info-name">燃料类型</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="fuelType"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">行驶证登记日期</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="drivingLicCreatetime"></span>
							</div>
							<div class="profile-info-name">司机</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="dirverName"></span>
							</div>
							<div class="profile-info-name">所有企业名称</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="ownerName"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">经营方式</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="bussType"></span>
							</div>
							<div class="profile-info-name">上次综合性能检测日期</div>
							<div class="profile-info-value">
								<span class="editable editable-click"
									id="lastComperformCheckDate"></span>
							</div>
							<div class="profile-info-name">上次二级维护日期</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="lastClassIIDate"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">初次登记日期</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="initRegDate"></span>
							</div>
							<div class="profile-info-name">有效期止</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="validEndDate"></span>
							</div>
							<div class="profile-info-name">强制报废期至</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="ScrapEndDate"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">是否港运通</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="IsGAT"></span>
							</div>
							<div class="profile-info-name">港运通证号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="gATNo"></span>
							</div>
							<div class="profile-info-name">是否安装GPS</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="IsInstallGPS"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">是否安装OBD</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="IsInstallOBD"></span>
							</div>
							<div class="profile-info-name">ODB设备号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="odbDeviceId"></span>
							</div>
						</div>
						<%-- <div class="profile-info-row">
							<div class="profile-info-name">制造厂商</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="makerName"></span>
							</div>
							<div class="profile-info-name">身份证号码</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="ownerIdCardNo"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">电池组号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="batteryGroupNo"></span>
							</div>
							<div class="profile-info-name">电机号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="motorNo"></span>
							</div>
						</div> --%>
					</div>
				</fieldset>
				<fieldset>
					<legend>车辆商业保险信息</legend>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">保险公司</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="insuranceCompany"></span>
							</div>
							<div class="profile-info-name">保额</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="coverage">0</span>万元
							</div>
							<div class="profile-info-name">投保日期</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="insuranceDate"></span>
							</div>
						</div>
					</div>
				</fieldset>
				<fieldset>
					<legend>修订信息</legend>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">创建人姓名</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="createUserName"></span>
							</div>
							<div class="profile-info-name">最后修改时间</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="modifyDate"></span>
							</div>

							<div class="profile-info-name">最后修改人姓名</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="modifyUserName"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">创建时间</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="createDate"></span>
							</div>
						</div>
					</div>
				</fieldset>
			</div>
			<div id="tab2" class="tab-pane">
				<fieldset>
					<legend>基础信息</legend>
					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">车辆唯一编码</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="identiCode"></span>
							</div>
							<div class="profile-info-name">车辆种类</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="carCategory"></span>
							</div>
							<div class="profile-info-name">车辆类型</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="carType"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">车辆用途</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="carUseType"></span>
							</div>
							<div class="profile-info-name">车辆识别代码(VIN)</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="vin"></span>
							</div>
							<div class="profile-info-name">补贴标识</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="subsidyId"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">外型尺寸(长/宽/高)</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="overallSize"></span>
							</div>
							<div class="profile-info-name">总质量(Kg)</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="totalMass"></span>
							</div>
							<div class="profile-info-name">载质量</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="loadQuality"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">出厂时间</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="factoryTime"></span>
							</div>
							<div class="profile-info-name">货箱尺寸(长/宽/高)</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="bodySize"></span>
							</div>
							<div class="profile-info-name">电池组号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="batteryGroupNo"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">单体型号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="batteryUnitTypeNo"></span>
							</div>
							<div class="profile-info-name">单体生产企业</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="batteryUnitMakerName"></span>
							</div>
							<div class="profile-info-name">成箱型号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="batteryBoxTypeNo"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">电池组总容量(kWh)</div>
							<div class="profile-info-value">
								<span class="editable editable-click"
									id="batteryGroupTotalCapacity"></span>
							</div>
							<div class="profile-info-name">电池组生成企业</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="batteryGroupMakerName"></span>
							</div>
							<div class="profile-info-name">电池质保年限</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="batteryKeepYears"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">电机号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="motorNo"></span>
							</div>
							<div class="profile-info-name">电机型号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="motorTypeNo"></span>
							</div>
							<div class="profile-info-name">额定功率(kW)</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="motorRatedPower"></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">生产企业</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="motorMakerName"></span>
							</div>
							<div class="profile-info-name">电机质保年限</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="motorKeepYears"></span>
							</div>
							<div class="profile-info-name">车身颜色</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="bodyColor"></span>
							</div>
						</div>
						<div class="profile-info-row">

							<div class="profile-info-name">生产企业名称</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="makerName"></span>
							</div>
							<div class="profile-info-name">运营状态</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="carStatus"></span>
							</div>
							<%-- <div class="profile-info-name">sim卡号</div>
							<div class="profile-info-value">
								<span class="editable editable-click" id="simNo"></span>
							</div> --%>
						</div>
					</div>
					<fieldset>
						<legend>修订信息</legend>
						<div class="profile-user-info profile-user-info-striped">
							<div class="profile-info-row">
								<div class="profile-info-name">创建人姓名</div>
								<div class="profile-info-value">
									<span class="editable editable-click" id="createUserName"></span>
								</div>
								<div class="profile-info-name">最后修改时间</div>
								<div class="profile-info-value">
									<span class="editable editable-click" id="modifyDate"></span>
								</div>
								<div class="profile-info-name">最后修改人姓名</div>
								<div class="profile-info-value">
									<span class="editable editable-click" id="modifyUserName"></span>
								</div>
							</div>
						</div>
					</fieldset>
			</div>
			<%-- <div id="tab3" class="tab-pane">
				<div class="profile-user-info profile-user-info-striped">
					<div class="profile-info-row">
						<div class="profile-info-name">id</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="id"></span>
						</div>
						<div class="profile-info-name">识别代号</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="identiCode"></span>
						</div>
						<div class="profile-info-name">车辆型号</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="carCode"></span>
						</div>
						<div class="profile-info-name">车辆名称</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="carName"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">车辆类别</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="carType"></span>
						</div>
						<div class="profile-info-name">车型图片Id</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="imageFileIds"></span>
						</div>
						<div class="profile-info-name">免检</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="freeInspection"></span>
						</div>
						<div class="profile-info-name">燃油</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="fuel"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">免征</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="exempt"></span>
						</div>
						<div class="profile-info-name">环保</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="green"></span>
						</div>
						<div class="profile-info-name">公告批次</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="noticeBatches"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">目录序号</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="directoryNum"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">中文品牌</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="chineseBrand"></span>
						</div>
						<div class="profile-info-name">生产厂家</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="carMakerName"></span>
						</div>
						<div class="profile-info-name">发动机型号</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="engineModel"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">发动机功率</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="engineCapacity"></span>
						</div>
						<div class="profile-info-name">发动机排量</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="engineDisplacement"></span>
						</div>
						<div class="profile-info-name">发动机生产商</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="engineMakerName"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">发动机商标</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="engineBrand"></span>
						</div>
						<div class="profile-info-name">燃料种类</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="fuelType"></span>
						</div>
						<div class="profile-info-name">外型尺寸(长/宽/高)</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="overallSize"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">货厢(长/宽/高)</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="bodySize"></span>
						</div>
						<div class="profile-info-name">总质量(Kg)</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="totalMass"></span>
						</div>
						<div class="profile-info-name">载质量</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="loadQuality"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">整备质量(kg)</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="SetupQuality"></span>
						</div>
						<div class="profile-info-name">额定质量(kg)</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="ratedQuality"></span>
						</div>
						<div class="profile-info-name">挂车质量(kg)</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="trailerQuality"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">半挂鞍座(kg)</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="semitrailer"></span>
						</div>
						<div class="profile-info-name">额定载客(人)</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="ratedCapacity"></span>
						</div>
						<div class="profile-info-name">前排乘客(人)</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="frontCapacity"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">驾驶室(人)</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="cab"></span>
						</div>
						<div class="profile-info-name">发布日期</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="releaseDate"></span>
						</div>
						<div class="profile-info-name">前悬</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="frontOverhang"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">后悬</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="rearOverhang"></span>
						</div>
						<div class="profile-info-name">接近角</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="approachAngle"></span>
						</div>
						<div class="profile-info-name">离去角</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="departureAngle"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">轴距(mm)</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="wheelbase"></span>
						</div>
						<div class="profile-info-name">轴荷</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="axleLoad"></span>
						</div>
						<div class="profile-info-name">轴数</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="axleCount"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">最高车速 (km/h)</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="MaximumSpeed"></span>
						</div>
						<div class="profile-info-name">油耗</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="fuelConsumption"></span>
						</div>
						<div class="profile-info-name">弹簧片数</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="leafSpringCount"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">轮胎数</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="tireCount"></span>
						</div>
						<div class="profile-info-name">轮胎规格</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="tireSpec"></span>
						</div>
						<div class="profile-info-name">前轮距</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="frontGauge"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">制动前</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="brakeFront"></span>
						</div>
						<div class="profile-info-name">制动后</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="brakeAfter"></span>
						</div>
						<div class="profile-info-name">制操前</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="operationFront"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">制操后</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="operationAfter"></span>
						</div>
						<div class="profile-info-name">转向形式</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="steeringType"></span>
						</div>
						<div class="profile-info-name">起动方式</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="startType"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">传动型式</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="drivingType"></span>
						</div>
						<div class="profile-info-name">依据标准</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="basicStandard"></span>
						</div>
						<div class="profile-info-name">底盘依据标准</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="chassisBasicStandard"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">后轮距</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="rearTrack"></span>
						</div>
						<div class="profile-info-name">其它</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="remark"></span>
						</div>
						<div class="profile-info-name">删除状态</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="deleteMark"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">创建日期</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="createDate"></span>
						</div>
						<div class="profile-info-name">创建人ID</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="createUserId"></span>
						</div>
						<div class="profile-info-name">创建人姓名</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="createUserName"></span>
						</div>
						<div class="profile-info-name">最后修改日期</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="modifyDate"></span>
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">最后修改人ID</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="modifyUserId"></span>
						</div>
						<div class="profile-info-name">最后修改人姓名</div>
						<div class="profile-info-value">
							<span class="editable editable-click" id="modifyUserName"></span>
						</div>
					</div>
					<div class="profile-info-row"></div>
				</div>
			</div> --%>
		</div>
	</div>

	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/industryResourceCar/preview.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />

</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<title>路段</title>
	</head>
	<script type="text/javascript" src="${portalPath}/system/getUserProp.do"></script>
	<link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css?v=${cfg.version}" />
	<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/plugins/font-awesome/css/font-awesome.min.css?v=${cfg.version}" />
	<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/css/components.min.css?v=${cfg.version}" />
	<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/layout.min.css?v=${cfg.version}" />
	<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/themes/default.min.css?v=${cfg.version}" />
	<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css?v=${cfg.version}" />
	<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/custom.min.css?v=${cfg.version}" />
	<script charset="utf-8" src="https://map.qq.com/api/js?v=2.exp&key=ALFBZ-5Z2CJ-TK6F7-KVINX-AX5L7-UFBXL&libraries=drawing,geometry"></script>
	<script type="text/javascript">
		var contextPath = '${pageContext.request.contextPath}';
		var portalPath = '${portalPath}';
		var version = '${cfg.version}';
		var fastdfs_server = '${cfg.fastdfs_server}';
		var activeSyId = '${SESSION_USERPROP_KEY.activeSyId}';
		var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';
	</script>
	<script>
		var id='${param.id}';
</script>
	<link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
	 rel="stylesheet" type="text/css" />
	<body>
		<style type="text/css">
			html,
			body {
				height: 100%;
				margin: 0px;
				padding: 0px;
				background: #fff;
			}

			#Map {
				height: 100%;
			}

			#FullScreen {
				position: absolute;
				top: 50%;
				left: -1px;
				margin-top: -40px;
				z-index: 201;
				width: 21px;
				height: 58px;
				cursor: pointer;
			}

			#MapViewPanel {
				position: relative;
				overflow: hidden;
				top: 33px;
			}

			.InlineBlock {
				display: inline-block;
				zoom: 1;
				*display: inline;
				_display: inline;
			}

			.portlet.light>.portlet-title {
				border-bottom: 0px solid #eef1f5;
			}

			.portlet.light>.portlet-title {
				padding: 0;
				min-height: 20px;
				font-weight: 800;
				padding-top:15px;
			}

			.portlet.light .portlet-body {
				padding-top: 1px;
			}

			.portlet.light {
				padding: 12px 12px 15px;
				background-color: #fff;
			}

			#Main {
				border-top: 1px solid #e6e6e6;
				overflow: hidden;
			}

			#TextViewPanel {
				position: relative;
				float: left;
				width: 300px;
				margin-left: 0;
				_margin-right: -3px;
				border-right: 1px solid #cad3e0;
				overflow: hidden;
			}

			#Main.RightDiv {
				position: relative;
				background-color: #eee;
				overflow: hidden;
			}

			#Header {
				width: 100%;
				height: 40px;
				position: relative;
				z-index: 1000;
			}

			.Toolbar {
				background: #f5f5f5;
				border-bottom: 1px solid #dcdcdc;
				height: 34px;
				line-height: 36px;
				width: 100%;
			}

			.MapTools {
				float: right;
				height: 34px;
				line-height: 34px;
				margin: 2px 0 1px 0;
				position: relative;
				text-align: center;
				color: #406593;
			}

			.formField {
				padding-bottom: 15px;
			}
		</style>
		<div id="Header">
			常德市交通事故分析
		</div>

		<div id="Main">
			<div id="TextViewPanel" style="margin-left: -380px;">
				<form id="fm">
					<div class="portlet light">
						<div class="portlet-title">精准搜索</div>
						<div class="portlet-body">
							<div class="row" style="padding-left:25px;padding-right:25px;">
								
									<input class="easyui-combotree" style="width:255px;height: 34px;" name="areaCode" data-options="url:﻿'${portalPath}/system/selectProvinceTreeList.do',method:'get',label:'',labelPosition:'top'"
									 style="width:200px;﻿line-height: 30px;height: 30px;">
								
								<input type="text" name="roadManName" class="form-control" placeholder="路长姓名">
							</div>
						</div>
						<div class="portlet-title">事故时间</div>
						<div class="portlet-body">
							<div class="row" style="padding-left:25px;padding-right:25px;">
								<input style="border: 1px solid #efefef;" type="text" size="16" name="startDate" readonly="" class="form-control">
								<input style="border: 1px solid #efefef;" type="text" size="16" name="endDate" readonly="" class="form-control">
							</div>
						</div>
						<div class="portlet-title">道理级别</div>
						<div class="portlet-body">
							<div class="row" style="padding-left:25px;padding-right:25px;">
								<div class="btn-group" id="check-group-category" role="group" style="float:left;padding-right:5px">
								</div>
							</div>
						</div>
						
						
						
						<div class="portlet-title">死亡人数</div>
						<div class="portlet-body">
							<div class="row" style="padding-left:25px;padding-right:25px;">
								<input type="text" size="16" name="downDeathNum" class="form-control" style="border: 1px solid #efefef;"
								 oninput="value=value.replace(/[^\d]/g,'')">
								<input type="text" size="16" name="upDeathNum" class="form-control" style="border: 1px solid #efefef;" oninput="value=value.replace(/[^\d]/g,'')">
							</div>
						
						</div>
						<div class="portlet-body">
							<div class="row" style="padding:15px;">
								<button class="btn   green" type="submit" style="width:255px">确定</button>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="RightDiv">
				<div id="FullScreen" title="收起左栏" class="InlineBlock" pgv="86" style="background-image: url(https://3gimg.qq.com/webmap_pc/v/themes/default/img/arrow.png?v=v5.1.132); background-position: -44px 0px;"></div>
				
				<div id="Map">

				</div>

			</div>

		</div>
		<script id="tpl-check-group" type="text/template">

			{@each data.list as item, index}
    {@if item.CODE}
    <button type="button" authority="false" class="btn btn-default"
            onclick="setParams('\${data.key}','\${item.CODE}');">\${item.NAME}
    </button>
    {@else}
    <button type="button" authority="false" class="btn btn-default" onclick="setParams('\${data.key}','');">全部</button>
    {@/if}

    {@/each}

</script>

<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-preview">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<script id="tpl-preview" type="text/template">
    <div class="form-group">
        <label class="col-md-2 view-label">事故时间</label>
        <div class="col-md-10">
            \${data.o.accidentTime}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">事故发生地点</label>
        <div class="col-md-10">
            <a href="${pageContext.request.contextPath}/dynamic/service/roadSection/previewMap.jsp?latitude=\${data.o.latitude}&longitude=\${data.o.longitude}"
               target="_blank">\${data.o.address}</a>
        </div>
    </div>
   <div class="form-group">
       <label class="col-md-2 view-label">所属路段</label>
       <div class="col-md-10">
           \${data.o.roadSectionName}
       </div>
   </div>
   <div class="form-group">
       <label class="col-md-2 view-label">路长</label>
       <div class="col-md-10">
           \${data.o.roadManName}
       </div>
   </div>
   
    <div class="form-group">
        <label class="col-md-2 view-label">天气</label>
        <div class="col-md-10">
            \${rsd(data.o.weather,'171')}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">车型</label>
        <div class="col-md-10">

            \${rsd(data.o.vehicleType,'172')}
        </div>
    </div>

    
    
    <div class="form-group">
        <label class="col-md-2 view-label">死亡人数</label>
        <div class="col-md-10">
            \${data.o.deadthToll}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">受伤人数</label>
        <div class="col-md-10">
            \${data.o.injuries}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">事故原因</label>
        <div class="col-md-10">

            \${rsd(data.o.cause,'173')}
        </div>
    </div>

    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">快报人姓名</label>
        <div class="col-md-10">
            \${data.o.createUserName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">采集日期</label>
        <div class="col-md-10">
            \${data.o.createDate}
        </div>
    </div>

    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">续报人姓名</label>
        <div class="col-md-10">
            \${data.o.lastModifyUserName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">续报日期</label>
        <div class="col-md-10">
            \${data.o.lastModifyDate}
        </div>
    </div>
</script>
		<!--[if lt IE 9]>
<script src="${portalPath}/content/common/assets/global/plugins/respond.min.js"></script>
<script src="${portalPath}/content/common/assets/global/plugins/excanvas.min.js"></script>
<script src="${portalPath}/content/common/assets/global/plugins/ie8.fix.min.js"></script>
<![endif]-->
		<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}" type="text/javascript"></script>
		<script src="${portalPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js?v=${cfg.version}" type="text/javascript"></script>
		<script src="${portalPath}/content/common/assets/global/plugins/js.cookie.min.js?v=${cfg.version}" type="text/javascript"></script>
		<script src="${portalPath}/content/common/js/init-rem.js?v=${cfg.version}" type="text/javascript"></script>
		<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
		<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
		<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
		<script src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>

		<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
		<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
		<script type="text/javascript" src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
		<script type="text/javascript" src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
		<script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>

		<script src="js/act.js?version=${cfg.version}"></script>
	</body>
</html>

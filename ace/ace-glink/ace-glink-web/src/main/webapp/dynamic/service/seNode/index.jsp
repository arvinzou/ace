<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<!--[if IE 8]>
<html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]>
<html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <title>配电箱数据</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-7">
                <a href="javascript:syncData();" class="btn green">同步数据</a>
                <a href="javascript:syncMonitorData();" class="btn green">同步监测数据</a>

            </div>

            <div class="col-md-5">

                <form id="fm-search">
                    <div class="input-group" role="group" style="float:left;padding-right:5px">
                        所属区域：
                        <input id="areaNodeID" name="a" class="easyui-combotree"
                               data-options="url:'${pageContext.request.contextPath}/seProjectArea/selectTreeList?id=01',method:'get',animate: true,
                lines:true," style='width:255px;line-height: 30px;height: 30px;'>
                        <a href="javascript:clearQparams()" style="padding-left:10px">清除</a>
                    </div>
                    <div class="input-group">
                        <input type="text" name="keyword"
                               class="form-control" placeholder="请输入配电箱编号">
                        <span class="input-group-btn">
                            <button class="btn  btn-default search_btn" type="submit"> 搜索</button>
                        </span>
                    </div>
                </form>
            </div>
        </div>

        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="10%"> 配电箱编号</th>
                    <th width="10%"> 位置标识</th>
                    <th width="10%"> 网关IP地址</th>
                    <th width="10%"> 电表品牌</th>
                    <th width="10%"> 维护人员</th>
                    <th width="10%"> 维护人员电话</th>
                    <th width="10%">操作</th>
                </tr>
                </thead>
                <tbody id="page-list">

                </tbody>
            </table>
        </div>
        <div class="paginationbar">
            <ul class="pagination" id="pagination1"></ul>
        </div>

    </div>

</div>


<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>

</body>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${item.nodeID}</td>
        <td> \${item.local}</td>
        <td> \${item.iPAddress}</td>
        <td> \${item.meterType}</td>
        <td> \${item.engineer}</td>
        <td> \${item.tel}</td>
        <td>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>
            <a href="#" data-toggle="modal" data-id="\${item.nodeID}" data-title="\${item.name}"
               data-target="#modal-monitor">查看监测</a>
        </td>
    </tr>
    {@/each}
</script>

<%--监测数据-模块回路--%>
<div class="modal fade" role="dialog" id="modal-monitor-dch">
    <div class="modal-dialog" role="document" style="width: 55%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-monitor-dch">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script id="tpl-monitor-dch" type="text/template">
    <div class="form-group">
        <label class="col-md-2 view-label">回路名称</label>
        <div class="col-md-10">
            \${data.o.chName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">模块代码</label>
        <div class="col-md-10">
            \${data.o.deviceCode}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">回路状态</label>
        <div class="col-md-10">
            \${parseCHState(data.o.status)}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">回路状态报告时间</label>
        <div class="col-md-10">
            \${data.o.cHReportTime}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">A相电压</label>
        <div class="col-md-10">
            \${data.o.va}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">B相电压</label>
        <div class="col-md-10">
            \${data.o.vb}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">C相电压</label>
        <div class="col-md-10">
            \${data.o.vc}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">A相电流</label>
        <div class="col-md-10">
            \${data.o.ia}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">B相电流</label>
        <div class="col-md-10">
            \${data.o.ib}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">C相电流</label>
        <div class="col-md-10">
            \${data.o.ic}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">A相视在功率</label>
        <div class="col-md-10">
            \${data.o.pa}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">B相视在功率</label>
        <div class="col-md-10">
            \${data.o.pb}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">C相视在功率</label>
        <div class="col-md-10">
            \${data.o.pc}
        </div>
    </div>
</script>

<%--监测数据--%>
<div class="modal fade" role="dialog" id="modal-monitor" data-keyboard="false">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-monitor">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script id="tpl-monitor" type="text/template">
    <div class="portlet light">

        <h4> 基本信息</h4>

        <div class="portlet-body">
            <div class="form-group">
                <label class="col-md-2 view-label">配电箱编号</label>
                <div class="col-md-10">
                    \${data.o.nodeID}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">报告时间</label>
                <div class="col-md-10">
                    \${data.o.reportTime}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">网关状态</label>
                <div class="col-md-10">
                    \${parseOFState(data.o.gateStatus)}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">网关状态上报时间</label>
                <div class="col-md-10">
                    \${data.o.gateReportTime}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">路由器状态</label>
                <div class="col-md-10">
                    \${parseOFState(data.o.routeStatus)}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">路由器信号值</label>
                <div class="col-md-10">
                    \${data.o.routeSignal}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">路由器状态报告时间</label>
                <div class="col-md-10">
                    \${data.o.routeReportTime}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">当前场景</label>
                <div class="col-md-10">
                    \${data.o.currentPreset}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">当前场景描述</label>
                <div class="col-md-10">
                    \${data.o.presetCaption}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">场景报告时间</label>
                <div class="col-md-10">
                    \${data.o.presetReportTime}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">温度</label>
                <div class="col-md-10">
                    \${data.o.temperature}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">湿度</label>
                <div class="col-md-10">
                    \${data.o.humidity}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">温湿度上报时间</label>
                <div class="col-md-10">
                    \${data.o.wSDReportTime}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">门状态</label>
                <div class="col-md-10">
                    \${data.o.doorStatus}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">电表号</label>
                <div class="col-md-10">
                    \${data.o.meterID}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">电表读数</label>
                <div class="col-md-10">
                    \${data.o.meterValue}\${data.o.meterValueUnit}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">电表上报时间</label>
                <div class="col-md-10">
                    \${data.o.meterReportTime}
                </div>
            </div>
        </div>
    </div>
    <div class="portlet light">

        <h4> 模块监控数据</h4>

        <div class="portlet-body">
            <div class="table-scrollable">
                <table class="table table-hover table-light">
                    <thead>
                    <tr>
                        <th width="5%"> 模块类型</th>
                        <th width="5%"> 模块代码</th>
                        <th width="5%"> 模块地址</th>
                        <th width="5%"> 模块状态</th>
                        <th width="5%"> 模块报告时间</th>
                        <th width="5%"> 回路1</th>
                        <th width="5%"> 回路2</th>
                        <th width="5%"> 回路3</th>
                        <th width="5%"> 回路4</th>
                        <th width="5%"> 回路5</th>
                        <th width="5%"> 回路6</th>
                        <th width="5%"> 回路7</th>
                        <th width="5%"> 回路8</th>
                        <th width="5%"> 回路9</th>
                        <th width="5%"> 回路10</th>
                        <th width="5%"> 回路11</th>
                        <th width="5%"> 回路12</th>
                    </tr>
                    </thead>
                    <tbody>
                    {@each data.monitorDeviceList as item, index}
                    <tr>
                        <th width="5%"> \${item.deviceType}</th>
                        <th width="5%"> \${item.deviceCode}</th>
                        <th width="5%"> \${item.deviceBox}</th>
                        <th width="5%"> \${parseOFState(item.deviceStatus)}</th>
                        <th width="5%"> \${item.deviceReportTime}</th>
                        <th width="5%">
                            <a href="javascript:showMonitorDeviceCH('\${item.deviceCode}','CH1Value');">详情</a>
                        </th>
                        <th width="5%">
                            <a href="javascript:showMonitorDeviceCH('\${item.deviceCode}','CH2Value');">详情</a>
                        </th>
                        <th width="5%">
                            <a href="javascript:showMonitorDeviceCH('\${item.deviceCode}','CH3Value');">详情</a>
                        </th>
                        <th width="5%">
                            <a href="javascript:showMonitorDeviceCH('\${item.deviceCode}','CH4Value');">详情</a>
                        </th>
                        <th width="5%">
                            <a href="javascript:showMonitorDeviceCH('\${item.deviceCode}','CH5Value');">详情</a>
                        </th>
                        <th width="5%">
                            <a href="javascript:showMonitorDeviceCH('\${item.deviceCode}','CH6Value');">详情</a>
                        </th>
                        <th width="5%">
                            <a href="javascript:showMonitorDeviceCH('\${item.deviceCode}','CH7Value');">详情</a>
                        </th>
                        <th width="5%">
                            <a href="javascript:showMonitorDeviceCH('\${item.deviceCode}','CH8Value');">详情</a>
                        </th>
                        <th width="5%">
                            <a href="javascript:showMonitorDeviceCH('\${item.deviceCode}','CH9Value');">详情</a>
                        </th>
                        <th width="5%">
                            <a href="javascript:showMonitorDeviceCH('\${item.deviceCode}','CH10Value');">详情</a>
                        </th>
                        <th width="5%">
                            <a href="javascript:showMonitorDeviceCH('\${item.deviceCode}','CH11Value');">详情</a>
                        </th>
                        <th width="5%">
                            <a href="javascript:showMonitorDeviceCH('\${item.deviceCode}','CH12Value');">详情</a>
                        </th>
                    </tr>
                    {@/each}
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</script>
﻿
<%--查看--%>
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 85%;">
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
    <div class="portlet light">

        <h4> 基本信息</h4>

        <div class="portlet-body">
            <div class="form-group">
                <label class="col-md-2 view-label">配电箱编号</label>
                <div class="col-md-10">
                    \${data.o.nodeID}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">位置标识</label>
                <div class="col-md-10">
                    \${data.o.local}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">网关IP地址</label>
                <div class="col-md-10">
                    \${data.o.iPAddress}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">路由器IP地址</label>
                <div class="col-md-10">
                    \${data.o.routeIPAddress}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">电表表号</label>
                <div class="col-md-10">
                    \${data.o.meterID}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">电表品牌或类型</label>
                <div class="col-md-10">
                    \${data.o.meterType}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">维护人员</label>
                <div class="col-md-10">
                    \${data.o.engineer}
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 view-label">维护人员电话</label>
                <div class="col-md-10">
                    \${data.o.tel}
                </div>
            </div>
        </div>
    </div>
    <div class="portlet light">

        <h4> 模块列表</h4>

        <div class="portlet-body">
            <div class="table-scrollable">
                <table class="table table-hover table-light">
                    <thead>
                    <tr>
                        <th width="5%"> 模块类型</th>
                        <th width="5%"> 模块代码</th>
                        <th width="5%"> 模块地址</th>
                        <th width="5%"> 回路1名称</th>
                        <th width="5%"> 回路2名称</th>
                        <th width="5%"> 回路3名称</th>
                        <th width="5%"> 回路4名称</th>
                        <th width="5%"> 回路5名称</th>
                        <th width="5%"> 回路6名称</th>
                        <th width="5%"> 回路7名称</th>
                        <th width="5%"> 回路8名称</th>
                        <th width="5%"> 回路9名称</th>
                        <th width="5%"> 回路10名称</th>
                        <th width="5%"> 回路11名称</th>
                        <th width="5%"> 回路12名称</th>
                        <th width="5%"> 回路1控制</th>
                        <th width="5%"> 回路2控制</th>
                        <th width="5%"> 回路3控制</th>
                        <th width="5%"> 回路4控制</th>
                        <th width="5%"> 回路5控制</th>
                        <th width="5%"> 回路6控制</th>
                        <th width="5%"> 回路7控制</th>
                        <th width="5%"> 回路8控制</th>
                        <th width="5%"> 回路9控制</th>
                        <th width="5%"> 回路10控制</th>
                        <th width="5%"> 回路11控制</th>
                        <th width="5%"> 回路12控制</th>
                    </tr>
                    </thead>
                    <tbody>
                    {@each data.deviceList as item, index}
                    <tr>
                        <th width="5%"> \${item.deviceType}</th>
                        <th width="5%"> \${item.deviceCode}</th>
                        <th width="5%"> \${item.deviceBox}</th>

                        <th width="5%"> \${item.cH1Name}</th>
                        <th width="5%"> \${item.cH2Name}</th>
                        <th width="5%"> \${item.cH3Name}</th>
                        <th width="5%"> \${item.cH4Name}</th>
                        <th width="5%"> \${item.cH5Name}</th>
                        <th width="5%"> \${item.cH6Name}</th>
                        <th width="5%"> \${item.cH7Name}</th>
                        <th width="5%"> \${item.cH8Name}</th>
                        <th width="5%"> \${item.cH9Name}</th>
                        <th width="5%"> \${item.cH10Name}</th>
                        <th width="5%"> \${item.cH11Name}</th>
                        <th width="5%"> \${item.cH12Name}</th>

                        <th width="5%"> \${item.cH1Control}</th>
                        <th width="5%"> \${item.cH2Control}</th>
                        <th width="5%"> \${item.cH3Control}</th>
                        <th width="5%"> \${item.cH4Control}</th>
                        <th width="5%"> \${item.cH5Control}</th>
                        <th width="5%"> \${item.cH6Control}</th>
                        <th width="5%"> \${item.cH7Control}</th>
                        <th width="5%"> \${item.cH8Control}</th>
                        <th width="5%"> \${item.cH9Control}</th>
                        <th width="5%"> \${item.cH10Control}</th>
                        <th width="5%"> \${item.cH11Control}</th>
                        <th width="5%"> \${item.cH12Control}</th>

                    </tr>
                    {@/each}
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</script>
<style>
    #modal-monitor {
        z-index: 10052;
    }

    #modal-monitor-dch {
        z-index: 10053;
    }

</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<%--easyui--%>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<%----%>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>

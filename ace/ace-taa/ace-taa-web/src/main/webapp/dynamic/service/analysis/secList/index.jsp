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
    <title>交通事故倒序表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet" type="text/css"/>

</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-4">
            </div>

            <div class="col-md-8">

                <%--search form--%>
                <form id="fm-search">
                    <div class="btn-group" id="check-group-category" role="group"
                         style="float:left;padding-right:5px">
                        <button type="button" authority="false" class="btn btn-default"
                                onclick="setParams('category','occurTimes');">事故次数倒叙表
                        </button>
                        <button type="button" authority="false" class="btn btn-default"
                                onclick="setParams('category','deathNum');">死亡人数倒叙表
                        </button>
                    </div>
                    <%--时间范围--%>
                    <div class="input-group" role="group" style="float:left;padding-right:5px">
                        <div style="width:auto;float:left;line-height:30px"> 时间段:</div>
                        <div class="date form_datetime"
                             style="width:auto;float:left;border: 1px solid #efefef;">
                            <input type="text" size="16" name="startDate" readonly="" class="form-control">
                        </div>
                        <span class="input-group-addon" style="width:10%;float:left;">
                            <font style="vertical-align: inherit;">
                                <font style="vertical-align: inherit;">至</font>
                            </font>
                        </span>
                        <div class="date form_datetime" style="width:auto;float:left;border: 1px solid #efefef;">
                            <input type="text" size="16" name="endDate" readonly="" class="form-control">
                        </div>
                    </div>
                    <%--行政区划--%>
                    <div class="input-group" style="float:left;padding-right:10px">
                        行政区划 <input class="easyui-combotree" name="areaCode"
                                    data-options="url:﻿'${portalPath}/system/selectProvinceTreeList.do',method:'get',label:'',labelPosition:'top'"
                                    style="width:220px;﻿line-height: 30px;height: 30px;">
                    </div>

                    <div class="input-group">
                        <span class="input-group-btn" style="padding-right: 5px">
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
                    <th width="20%"> 路段名称</th>
                    <th width="20%"> 归属路长</th>
                    <th width="20%"> 行政区划</th>
                    <th width="20%"> 事故次数</th>
                    <th width="20%"> 死亡人数</th>
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

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${item.roadSectionName}</td>
        <td> \${item.roadManName}</td>
        <td> \${item.areaCodeName}</td>
        <td> \${item.occurTimes}</td>
        <td> \${item.deathNum}</td>
    </tr>
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
    <div class="form-group hide">
        <label class="col-md-2 view-label">主键</label>
        <div class="col-md-10">
            \${data.o.id}
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
        <label class="col-md-2 view-label">纬度</label>
        <div class="col-md-10">
            \${data.o.latitude}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">经度</label>
        <div class="col-md-10">
            \${data.o.longitude}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">行政区划</label>
        <div class="col-md-10">
            \${data.o.areaName}
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
        <label class="col-md-2 view-label">事故时间</label>
        <div class="col-md-10">
            \${data.o.accidentTime}
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
<style>
    .cover {
        width: 70px;
        height: 70px;
        object-fit: cover;
    }

    .describtion {
        padding-left: 15px;
        height: 50px;
    }

    .cost {
        padding-top: 5px;
        padding-left: 15px;
        color: #FE6500;
    }
</style>


<jsp:include page="/dynamic/common/footer.jsp"/>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>


<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
</html>

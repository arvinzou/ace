<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8" %>

<%
String companyId = request.getParameter("companyId");
request.setAttribute("companyId", companyId);
%>

<%
session.setAttribute("portalPath", "/portal");
%>
<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var portalPath = '${portalPath}';
    var version = '${cfg.version}';
    var fastdfs_server = '${cfg.fastdfs_server}';
    var activeSyId = '${SESSION_USERPROP_KEY.activeSyId}';
    var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';
    var default_page_list=[${cfg.default_page_list}];
</script>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>基本信息</title>
    <script type="text/javascript" src="${portalPath}/system/getUserProp.do"></script>
    <script type="text/javascript" src="${portalPath}/system/getButtonAuthority.do?id=${param.id}"></script>
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css?v=${cfg.version}" />
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/plugins/font-awesome/css/font-awesome.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/css/components.min.css?v=${cfg.version}" />
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/layout.min.css?v=${cfg.version}" />
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/themes/default.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/custom.min.css?v=${cfg.version}" />
    <link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />

    <script>

var  urlParams = {};

function rsd(value, kernelKey, staticDictObjects) {
	try {
		if (!staticDictObjects) {
			staticDictObjects = parent.staticDictObject;
		}

		var name = value;

		if ((value + "") && ("" + value).indexOf(',') < 0) {
			if (staticDictObjects && kernelKey && staticDictObjects[kernelKey]) {
				for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
					if (staticDictObjects[kernelKey][i].CODE == value) {
						name = staticDictObjects[kernelKey][i].NAME;
						break;
					}
				}
			}
		} else {
			if (value) {
				var nameArray = [];
				var v = (value + "").split(',');
				for (var j = 0; j < v.length; j++) {
					for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
						if (staticDictObjects[kernelKey][i].CODE == v[j]) {
							nameArray.push(staticDictObjects[kernelKey][i].NAME);
							break;
						}
					}
				}
				name = nameArray.join(',');
			}
		}
	} catch (err) {
		console.log("渲染错误", value + ":" + kernelKey + ":" + err);
	}
	return name;
}

function odparse(kernelKey, staticDictObjects) {
	var rst = [];
	try {
		if (!staticDictObjects) {
			staticDictObjects = parent.staticDictObject;
		}
		if (staticDictObjects && kernelKey && staticDictObjects[kernelKey]) {
			for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
				rst.push(staticDictObjects[kernelKey][i].CODE + ":"
						+ staticDictObjects[kernelKey][i].NAME);
			}
		}
	} catch (err) {
		console.log("渲染错误", value + ":" + kernelKey + ":" + err);
	}
	return rst.join(";");
}
function getQueryString() {
      var qs = location.search.substr(1),
        args = {},
        items = qs.length ? qs.split("&") : [],
        item = null,
        len = items.length;

      for(var i = 0; i < len; i++) {
        item = items[i].split("=");
        var name = decodeURIComponent(item[0]),
          value = decodeURIComponent(item[1]);
        if(name) {
          args[name] = value;
        }
      }
      return args;
}
urlParams=getQueryString();
</script>

</head>



<script type="text/javascript">
    var taskId = '${taskId}';
    var edit = '${edit}';
</script>

<style>
    .main_box {
        width: 95%;
        margin: 0 auto;
        background-color: #FFFFFF;
        padding-left: 30px;
        padding-right: 30px;
        padding-bottom: 30px;
    }

    .title_box_title_01 {
        font-size: 30px;
        font-weight: bolder;
    }

    .info_title_01 {
        width: 300px;
        height: 35px;
        line-height: 35px;
        display: block;
        float: left;
        font-size: 16px;
    }

    .info_title_btn {
        width: 50px;
        height: 35px;
        line-height: 35px;
        display: block;
        float: right;
        cursor: pointer;
    }

    .form_info {
        width: 100%;
        margin: auto;
        font-size: 14px;
        border-collapse: collapse;
    }

    .form_info td {
        padding-left: 15px;
        padding-right: 15px;
        border: 1px solid #E1E5ED;
    }

    .form_info tr {
        height: 30px;
        line-height: 30px;
    }

    .form_info textarea {
        border: none;
        outline: none;
    }

    .form_info input {
        border: none;
        outline: none;
        background-color: #fff;
    }

    .form_info select {
        border: none;
        outline: none;
        background-color: #fff;
    }

    .maper {
        color: #1A56A8;
    }

    .tdbg {
        background-color: #FBFCFD;
    }




</style>


<body>
<div class="page-content">
    <div class="main_box">
    </div>
</div>


<script id="tpl-view-page" type="text/template">

        <div class="title_box">
            <div class="col-lg-12 col-md-12 col-xs-12" style="text-align: center">
                <span class="title_box_title_01">团体信息</span>
            </div>
        </div>
        <div class="form_box">
            <!--基本信息-->
            <div class="info">
                <div class="info_title"><span class="info_title_01">基本信息</span></div>
                <div class="info_box">
                    <table class="form_info" cellspacing="0" cellpadding="0" border="1">
                        <tr>
                            <td align="right" class="tdbg">团体名称</td>
                            <td>
                                <input type="text" name="fullName" readonly="true" value="\${fullName}"/></td>
                            <td align="right" class="tdbg">创建时间</td>
                            <td>
                                <input type="text" name="establishDate" readonly="true" value="\${establishDate}"/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" class="tdbg">办公电话</td>
                            <td><input type="text" name="phoneNumber" readonly="true" value="\${phoneNumber}"/></td>
                            <td align="right" class="tdbg">地址</td>
                            <td>
                                <a name="address" href="location.jsp?did=\${id}" target="_blank">\${address} </a>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" class="tdbg">所属工商联</td>
                            <td><input type="text" name="belongTo" readonly="true" value="\${belongTo}"/></td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr style="display: none;">
                            <td colspan="4">
                                <input type="text" id="latitude"/>
                                <input type="text" id="longitude"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <!--商协会领导班子情况-->
            <div class="info">
                <div class="info_title"><span class="info_title_01">商协会领导班子情况</span></div>
                <div class="info_box">
                    <table class="form_info" cellpadding="0" cellspacing="0" border="1">
                        <tr>
                            <td align="right" class="tdbg">理事数量</td>
                            <td><input type="text" name="directorNum" readonly="true" value="\${directorNum}"/></td>
                            <td align="right" class="tdbg">副会长数量</td>
                            <td><input type="text" name="viceNum" readonly="true" value="\${viceNum}"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="info">
                <a id="btn-view-add" href="javascript:void(0);" style="float:right;padding-top:15px">添加</a>
            </div>
            <div class="info">
                <div class="info_title">
                    <span class="info_title_01">党员信息 </span>
                </div>





                <div id="pm" class="portlet-body">


                    <table id="grid-table"></table>


                </div>
            </div>


        </div>

</script>

<script type="text/javascript">
    window.onload = function () {
        var url = window.location.search.substring(1);
        var cid = url.substring(url.indexOf('=') + 1);

        loadView(cid);

    }
</script>


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

<script src="${pageContext.request.contextPath}/content/service/association/act.js"></script>

<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/pm/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/pm/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/pm/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/pm/view.js?version=${cfg.version}"></script>
</body>
</html>
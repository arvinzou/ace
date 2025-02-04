<%@page import="java.util.Date"%>
<%@page import="com.huacainfo.ace.common.tools.CommonUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="cn">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<meta name="viewport"
		  content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<title>role</title>
</head>
<%
session.setAttribute("portalPath", "/portal");
request.setAttribute("now", CommonUtils.formatDate(new Date()));
com.huacainfo.ace.common.model.UserProp user=(com.huacainfo.ace.common.model.UserProp)session.getAttribute("SESSION_USERPROP_KEY");
String portalType=(String)user.getCfg().get("portalType");

%>
<script type="text/javascript">
	var contextPath = '${portalPath}';
	var portalPath = '${portalPath}';
	var layoutTopHeight=280;
	var fastdfs_server='${cfg.fastdfs_server}';
	var default_page_list=[${cfg.default_page_list}];
	var now='${now}';
	var portalType='${SESSION_USERPROP_KEY.cfg.portalType}';
	var activeSyId ='${SESSION_USERPROP_KEY.activeSyId}';
	var version='${cfg.version}';
</script>
<script type="text/javascript" src="${portalPath}/system/getButtonAuthority.do?id=${param.id}"></script>
<script type="text/javascript" src="${portalPath}/system/getUserProp.do"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/base.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/loading.js?v=${cfg.version}"></script>
<link rel="stylesheet" href="${portalPath}/content/common/assets/css/bootstrap.min.css?version=${cfg.version}" />


<style>
	/*Grid*/
.ui-jqgrid {position: relative;}
.ui-jqgrid .ui-jqgrid-view {position: relative;left:0; top: 0; padding: 0; font-size:11px;}
/* caption*/
.ui-jqgrid .ui-jqgrid-titlebar {padding: .3em .2em .2em .3em; position: relative; font-size: 12px; border-left: 0 none;border-right: 0 none; border-top: 0 none;}
.ui-jqgrid .ui-jqgrid-caption {text-align: left;}
.ui-jqgrid .ui-jqgrid-title { margin: .1em 0 .2em; }
.ui-jqgrid .ui-jqgrid-titlebar-close { position: absolute;top: 50%; width: 19px; margin: -10px 0 0 0; padding: 1px; height:18px; cursor:pointer;}
.ui-jqgrid .ui-jqgrid-titlebar-close span { display: block; margin: 1px; }
.ui-jqgrid .ui-jqgrid-titlebar-close:hover { padding: 0; }
/* header*/
.ui-jqgrid .ui-jqgrid-hdiv {position: relative; margin: 0;padding: 0; border-left: 0 none !important; border-top : 0 none !important; border-right : 0 none !important; overflow-x: hidden;}
.ui-jqgrid .ui-jqgrid-hbox {float: left; padding-right: 20px;}
.ui-jqgrid .ui-jqgrid-htable {table-layout:fixed;margin:0;}
.ui-jqgrid .ui-jqgrid-htable th {height:22px;padding: 0 2px 0 2px;}
.ui-jqgrid .ui-jqgrid-htable th div {overflow: hidden; position:relative; height:17px;}
.ui-th-column, .ui-jqgrid .ui-jqgrid-htable th.ui-th-column {overflow: hidden;white-space: nowrap;text-align:center;border-top : 0 none;border-bottom : 0 none;}
.ui-th-ltr, .ui-jqgrid .ui-jqgrid-htable th.ui-th-ltr {border-left : 0 none;}
.ui-th-rtl, .ui-jqgrid .ui-jqgrid-htable th.ui-th-rtl {border-right : 0 none;}
.ui-first-th-ltr {border-right: 1px solid; }
.ui-first-th-rtl {border-left: 1px solid; }
.ui-jqgrid .ui-th-div-ie {white-space: nowrap; zoom :1; height:17px;}
.ui-jqgrid .ui-jqgrid-resize {height:20px !important;position: relative; cursor :e-resize;display: inline;overflow: hidden;}
.ui-jqgrid .ui-grid-ico-sort {overflow:hidden;position:absolute;display:inline; cursor: pointer !important;}
.ui-jqgrid .ui-icon-asc {margin-top:-3px; height:12px;}
.ui-jqgrid .ui-icon-desc {margin-top:3px;height:12px;}
.ui-jqgrid .ui-i-asc {margin-top:0;height:16px;}
.ui-jqgrid .ui-i-desc {margin-top:0;margin-left:13px;height:16px;}
.ui-jqgrid .ui-jqgrid-sortable {cursor:pointer;}
.ui-jqgrid tr.ui-search-toolbar th { border-top-width: 1px !important; border-top-color: inherit !important; border-top-style: ridge !important }
tr.ui-search-toolbar input {margin: 1px 0 0 0}
tr.ui-search-toolbar select {margin: 1px 0 0 0}
/* body */
.ui-jqgrid .ui-jqgrid-bdiv {position: relative; margin: 0; padding:0; overflow: auto; text-align:left;}
.ui-jqgrid .ui-jqgrid-btable {table-layout:fixed; margin:0; outline-style: none; }
.ui-jqgrid tr.jqgrow { outline-style: none; }
.ui-jqgrid tr.jqgroup { outline-style: none; }
.ui-jqgrid tr.jqgrow td {font-weight: normal; overflow: hidden; white-space: pre; height: 22px;padding: 0 2px 0 2px;border-bottom-width: 1px; border-bottom-color: inherit; border-bottom-style: solid;}
.ui-jqgrid tr.jqgfirstrow td {padding: 0 2px 0 2px;border-right-width: 1px; border-right-style: solid;}
.ui-jqgrid tr.jqgroup td {font-weight: normal; overflow: hidden; white-space: pre; height: 22px;padding: 0 2px 0 2px;border-bottom-width: 1px; border-bottom-color: inherit; border-bottom-style: solid;}
.ui-jqgrid tr.jqfoot td {font-weight: bold; overflow: hidden; white-space: pre; height: 22px;padding: 0 2px 0 2px;border-bottom-width: 1px; border-bottom-color: inherit; border-bottom-style: solid;}
.ui-jqgrid tr.ui-row-ltr td {text-align:left;border-right-width: 1px; border-right-color: inherit; border-right-style: solid;}
.ui-jqgrid tr.ui-row-rtl td {text-align:right;border-left-width: 1px; border-left-color: inherit; border-left-style: solid;}
.ui-jqgrid td.jqgrid-rownum { padding: 0 2px 0 2px; margin: 0; border: 0 none;}
.ui-jqgrid .ui-jqgrid-resize-mark { width:2px; left:0; background-color:#777; cursor: e-resize; cursor: col-resize; position:absolute; top:0; height:100px; overflow:hidden; display:none; border:0 none; z-index: 99999;}
/* footer */
.ui-jqgrid .ui-jqgrid-sdiv {position: relative; margin: 0;padding: 0; overflow: hidden; border-left: 0 none !important; border-top : 0 none !important; border-right : 0 none !important;}
.ui-jqgrid .ui-jqgrid-ftable {table-layout:fixed; margin-bottom:0;}
.ui-jqgrid tr.footrow td {font-weight: bold; overflow: hidden; white-space:nowrap; height: 21px;padding: 0 2px 0 2px;border-top-width: 1px; border-top-color: inherit; border-top-style: solid;}
.ui-jqgrid tr.footrow-ltr td {text-align:left;border-right-width: 1px; border-right-color: inherit; border-right-style: solid;}
.ui-jqgrid tr.footrow-rtl td {text-align:right;border-left-width: 1px; border-left-color: inherit; border-left-style: solid;}
/* Pager*/
.ui-jqgrid .ui-jqgrid-pager { border-left: 0 none !important;border-right: 0 none !important; border-bottom: 0 none !important; margin: 0 !important; padding: 0 !important; position: relative; height: 25px;white-space: nowrap;overflow: hidden;font-size:11px;}
.ui-jqgrid .ui-pager-control {position: relative;}
.ui-jqgrid .ui-pg-table {position: relative; padding-bottom:2px; width:auto; margin: 0;}
.ui-jqgrid .ui-pg-table td {font-weight:normal; vertical-align:middle; padding:1px;}
.ui-jqgrid .ui-pg-button  { height:19px !important;}
.ui-jqgrid .ui-pg-button span { display: block; margin: 1px; float:left;}
.ui-jqgrid .ui-pg-button:hover { padding: 0; }
.ui-jqgrid .ui-state-disabled:hover {padding:1px;}
.ui-jqgrid .ui-pg-input { height:13px;font-size:.8em; margin: 0;}
.ui-jqgrid .ui-pg-selbox {font-size:.8em; line-height:18px; display:block; height:18px; margin: 0;}
.ui-jqgrid .ui-separator {height: 18px; border-left: 1px solid #ccc ; border-right: 1px solid #ccc ; margin: 1px; float: right;}
.ui-jqgrid .ui-paging-info {font-weight: normal;height:19px; margin-top:3px;margin-right:4px;}
.ui-jqgrid .ui-jqgrid-pager .ui-pg-div {padding:1px 0;float:left;position:relative;}
.ui-jqgrid .ui-jqgrid-pager .ui-pg-button { cursor:pointer; }
.ui-jqgrid .ui-jqgrid-pager .ui-pg-div  span.ui-icon {float:left;margin:0 2px;}
.ui-jqgrid td input, .ui-jqgrid td select .ui-jqgrid td textarea { margin: 0;}
.ui-jqgrid td textarea {width:auto;height:auto;}
.ui-jqgrid .ui-jqgrid-toppager {border-left: 0 none !important;border-right: 0 none !important; border-top: 0 none !important; margin: 0 !important; padding: 0 !important; position: relative; height: 25px !important;white-space: nowrap;overflow: hidden;}
.ui-jqgrid .ui-jqgrid-toppager .ui-pg-div {padding:1px 0;float:left;position:relative;}
.ui-jqgrid .ui-jqgrid-toppager .ui-pg-button { cursor:pointer; }
.ui-jqgrid .ui-jqgrid-toppager .ui-pg-div  span.ui-icon {float:left;margin:0 2px;}
/*subgrid*/
.ui-jqgrid .ui-jqgrid-btable .ui-sgcollapsed span {display: block;}
.ui-jqgrid .ui-subgrid {margin:0;padding:0; width:100%;}
.ui-jqgrid .ui-subgrid table {table-layout: fixed;}
.ui-jqgrid .ui-subgrid tr.ui-subtblcell td {height:18px;border-right-width: 1px; border-right-color: inherit; border-right-style: solid;border-bottom-width: 1px; border-bottom-color: inherit; border-bottom-style: solid;}
.ui-jqgrid .ui-subgrid td.subgrid-data {border-top:  0 none !important;}
.ui-jqgrid .ui-subgrid td.subgrid-cell {border-width: 0 0 1px 0;}
.ui-jqgrid .ui-th-subgrid {height:20px;}
/* loading */
.ui-jqgrid .loading {position: absolute; top: 45%;left: 45%;width: auto;z-index:101;padding: 6px; margin: 5px;text-align: center;font-weight: bold;display: none;border-width: 2px !important; font-size:11px;}
.ui-jqgrid .jqgrid-overlay {display:none;z-index:100;}
/* IE * html .jqgrid-overlay {width: expression(this.parentNode.offsetWidth+'px');height: expression(this.parentNode.offsetHeight+'px');} */
* .jqgrid-overlay iframe {position:absolute;top:0;left:0;z-index:-1;}
/* IE width: expression(this.parentNode.offsetWidth+'px');height: expression(this.parentNode.offsetHeight+'px');}*/
/* end loading div */
/* toolbar */
.ui-jqgrid .ui-userdata {border-left: 0 none;    border-right: 0 none;	height : 21px;overflow: hidden;	}
/*Modal Window */
.ui-jqdialog { display: none; width: 300px; position: absolute; padding: .2em; font-size:11px; overflow:visible;}
.ui-jqdialog .ui-jqdialog-titlebar { padding: .3em .2em; position: relative;  }
.ui-jqdialog .ui-jqdialog-title { margin: .1em 0 .2em; }
.ui-jqdialog .ui-jqdialog-titlebar-close { position: absolute;  top: 50%; width: 19px; margin: -10px 0 0 0; padding: 1px; height: 18px; cursor:pointer;}

.ui-jqdialog .ui-jqdialog-titlebar-close span { display: block; margin: 1px; }
.ui-jqdialog .ui-jqdialog-titlebar-close:hover, .ui-jqdialog .ui-jqdialog-titlebar-close:focus { padding: 0; }
.ui-jqdialog-content, .ui-jqdialog .ui-jqdialog-content { border: 0; padding: .3em .2em; background: none; height:auto;}
.ui-jqdialog .ui-jqconfirm {padding: .4em 1em; border-width:3px;position:absolute;bottom:10px;right:10px;overflow:visible;display:none;height:80px;width:220px;text-align:center;}
.ui-jqdialog>.ui-resizable-se { bottom: -3px; right: -3px}
.ui-jqgrid>.ui-resizable-se { bottom: -3px; right: -3px }
/* end Modal window*/
/* Form edit */
.ui-jqdialog-content .FormGrid {margin: 0;}
.ui-jqdialog-content .EditTable { width: 100%; margin-bottom:0;}
.ui-jqdialog-content .DelTable { width: 100%; margin-bottom:0;}
.EditTable td input, .EditTable td select, .EditTable td textarea {margin: 0;}
.EditTable td textarea { width:auto; height:auto;}
.ui-jqdialog-content td.EditButton {text-align: right;border-top: 0 none;border-left: 0 none;border-right: 0 none; padding-bottom:5px; padding-top:5px;}
.ui-jqdialog-content td.navButton {text-align: center; border-left: 0 none;border-top: 0 none;border-right: 0 none; padding-bottom:5px; padding-top:5px;}
.ui-jqdialog-content input.FormElement {padding:.3em}
.ui-jqdialog-content select.FormElement {padding:.3em}
.ui-jqdialog-content .data-line {padding-top:.1em;border: 0 none;}

.ui-jqdialog-content .CaptionTD {vertical-align: middle;border: 0 none; padding: 2px;white-space: nowrap;}
.ui-jqdialog-content .DataTD {padding: 2px; border: 0 none; vertical-align: top;}
.ui-jqdialog-content .form-view-data {white-space:pre}
.fm-button { display: inline-block; margin:0 4px 0 0; padding: .4em .5em; text-decoration:none !important; cursor:pointer; position: relative; text-align: center; zoom: 1; }
.fm-button-icon-right { padding-right: 1.9em; }
.fm-button-icon-left .ui-icon { right: auto; left: .2em; margin-left: 0; position: absolute; top: 50%; margin-top: -8px; }
.fm-button-icon-right .ui-icon { left: auto; right: .2em; margin-left: 0; position: absolute; top: 50%; margin-top: -8px;}
#nData, #pData { float: left; margin:3px;padding: 0; width: 15px; }
/* End Eorm edit */
/*.ui-jqgrid .edit-cell {}*/
.ui-jqgrid .selected-row, div.ui-jqgrid .selected-row td {font-style : normal;border-left: 0 none;}
/* inline edit actions button*/
.ui-inline-del.ui-state-hover span, .ui-inline-edit.ui-state-hover span,
.ui-inline-save.ui-state-hover span, .ui-inline-cancel.ui-state-hover span {
    margin: -1px;
}
/* Tree Grid */
.ui-jqgrid .tree-wrap {float: left; position: relative;height: 18px;white-space: nowrap;overflow: hidden;}
.ui-jqgrid .tree-minus {position: absolute; height: 18px; width: 18px; overflow: hidden;}
.ui-jqgrid .tree-plus {position: absolute;	height: 18px; width: 18px;	overflow: hidden;}
.ui-jqgrid .tree-leaf {position: absolute;	height: 18px; width: 18px;overflow: hidden;}
.ui-jqgrid .treeclick {cursor: pointer;}
/* moda dialog */
* iframe.jqm {position:absolute;top:0;left:0;z-index:-1;}
/*	 width: expression(this.parentNode.offsetWidth+'px');height: expression(this.parentNode.offsetHeight+'px');}*/
.ui-jqgrid-dnd tr td {border-right-width: 1px; border-right-color: inherit; border-right-style: solid; height:20px}
/* RTL Support */
.ui-jqgrid .ui-jqgrid-caption-rtl {text-align: right;}
.ui-jqgrid .ui-jqgrid-hbox-rtl {float: right; padding-left: 20px;}
.ui-jqgrid .ui-jqgrid-resize-ltr {float: right;margin: -2px -2px -2px 0;}
.ui-jqgrid .ui-jqgrid-resize-rtl {float: left;margin: -2px 0 -1px -3px;}
.ui-jqgrid .ui-sort-rtl {left:0;}
.ui-jqgrid .tree-wrap-ltr {float: left;}
.ui-jqgrid .tree-wrap-rtl {float: right;}
.ui-jqgrid .ui-ellipsis {-moz-text-overflow:ellipsis;text-overflow:ellipsis;}

/* Toolbar Search Menu */
.ui-search-menu { position: absolute; padding: 2px 5px;}
.ui-jqgrid .ui-search-table { padding: 0; border: 0 none; height:20px; width:100%;}
.ui-jqgrid .ui-search-table .ui-search-oper { width:20px; }
a.g-menu-item, a.soptclass, a.clearsearchclass { cursor: pointer; }
.ui-jqgrid .ui-search-table .ui-search-input>input,
.ui-jqgrid .ui-search-table .ui-search-input>select
{
    display: block;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}
.ui-jqgrid .ui-jqgrid-view input,
.ui-jqgrid .ui-jqgrid-view select,
.ui-jqgrid .ui-jqgrid-view textarea,
.ui-jqgrid .ui-jqgrid-view button {
    font-size: 11px
}
.widget-header {
    -webkit-box-sizing: content-box;
    -moz-box-sizing: content-box;
    box-sizing: content-box;
    position: relative;
    min-height: 38px;
    background: #f7f7f7;
    background-image: -webkit-linear-gradient(top, #fff 0, #eee 100%);
    background-image: linear-gradient(to bottom, #fff 0, #eee 100%);
    background-repeat: repeat-x;
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff',
 endColorstr='#ffeeeeee', GradientType=0 );
    color: #669fc7;
    border-bottom: 1px solid #e7ecf1;
    padding-left: 12px;
}
.ui-jqgrid .ui-jqgrid-view,.ui-jqgrid .ui-paging-info,.ui-jqgrid .ui-pg-table,.ui-jqgrid .ui-pg-selbox
	{
	font-size: 13px
}

.ui-jqgrid .ui-jqgrid-title {
	float: left;
	margin: 8px
}

.ui-jqgrid .ui-jqgrid-title-rtl {
	float: right;
	margin: 8px
}

.ui-jqgrid-view>.ui-jqgrid-titlebar {
    height: 40px;
    line-height: 24px;
    padding: 0;
    font-size: 15px;
    background-image: -webkit-linear-gradient(top, #fff 0, #eee 100%);
    background-image: linear-gradient(to bottom, #fff 0, #eee 100%);
    background-repeat: repeat-x;
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff',
    endColorstr='#ffeeeeee', GradientType=0 );
    border-image: none;
    border-bottom: 1px solid solid;
    color: #669FC7;
    min-height: 38px;
    position: relative
}

.ui-jqgrid tr.jqgrow.ui-row-rtl td:last-child {
	border-right: none;
	border-left: 1px solid #e7ecf1
}

.ui-jqgrid .ui-jqgrid-hdiv {
	background-color: #eff3f8;
	border: 1px solid #D3D3D3;
	border-width: 1px 0 0 1px;
	line-height: 15px;
	font-weight: bold;
	color: #777;
	text-shadow: none
}

.ui-jqgrid .ui-jqgrid-htable thead {
	background-color: #eff3f8
}

.ui-jqgrid .ui-jqgrid-htable th span.ui-jqgrid-resize {
	height: 45px !important
}

.ui-jqgrid .ui-jqgrid-htable th div {
	padding-top: 12px;
	padding-bottom: 12px
}

.ui-jqgrid-hdiv .ui-jqgrid-htable {
	border-top: none
}

.ui-jqgrid-hdiv .ui-jqgrid-htable {
	border-top: 1px solid #e7ecf1
}

.ui-jqgrid-titlebar {
	position: relative;
	top: 1px;
	z-index: 1
}

.ui-jqgrid tr.jqgrow,.ui-jqgrid tr.ui-row-ltr,.ui-jqgrid tr.ui-row-rtl {
	border: none
}

.ui-jqgrid tr.ui-row-ltr td,.ui-jqgrid tr.ui-row-rtl td {
	border-bottom: 1px solid #e7ecf1;
	padding: 6px 4px;
	border-color: #e7ecf1
}

.ui-jqgrid tr.ui-state-highlight.ui-row-ltr td {
	border-right-color: #C7D3A9
}

.ui-jqgrid tr.ui-state-highlight.ui-row-rtl td {
	border-left-color: #C7D3A9
}

.ui-jqgrid-btable .ui-widget-content.ui-priority-secondary {
	background-image: none;
	background-color: #F9F9F9;
	opacity: 1
}

.ui-jqgrid-btable .ui-widget-content.ui-state-hover {
	background-image: none;
	background-color: #EFF4F7;
	opacity: 1
}

.ui-jqgrid-btable .ui-widget-content.ui-state-highlight {
	background-color: #E4EFC9
}

.ui-jqgrid .ui-jqgrid-pager {
	line-height: 15px;
	height: 55px;
	padding-top: 3px !important;
	padding-bottom: 5px !important;
	background-color: #eff3f8 !important;
	border-bottom: 1px solid #e7ecf1 !important;
	border-top: 1px solid #e7ecf1 !important
}

.ui-jqgrid .ui-pg-input {
	font-size: inherit;
	width: 24px;
	height: 20px;
	line-height: 16px;
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
	text-align: center;
	padding-top: 1px;
	padding-bottom: 1px
}

.ui-jqgrid .ui-pg-selbox {
	display: block;
	height: 24px;
	width: 60px;
	margin: 0;
	padding: 1px;
	line-height: normal
}

.ui-jqgrid .ui-jqgrid-htable th div {
	overflow: visible
}

.ui-jqgrid .ui-pager-control {
	height: 50px;
	position: relative;
	padding-left: 9px;
	padding-right: 9px
}

.ui-jqgrid .ui-jqgrid-toppager {
	height: auto !important;
	background-color: #eff3f8;
	border-bottom: 1px solid #e7ecf1 !important
}

.ui-jqgrid .jqgrow .editable {
	max-width: 90%;
	max-width: calc(92%) !important
}

.ui-pg-table .navtable .ui-corner-all {
	border-radius: 0
}

.ui-jqgrid .ui-pg-button:hover {
	padding: 1px
}

.ui-jqgrid .ui-pg-button .ui-separator {
	margin-left: 4px;
	margin-right: 4px;
	border-color: #C9D4DB
}

.ui-jqgrid .ui-jqgrid-btable {
	border-left: 1px solid #e7ecf1
}

.ui-jqgrid .ui-jqgrid-bdiv {
	border-top: 1px solid #e7ecf1
}

.ui-jqgrid .loading {
	position: absolute;
	top: 45%;
	left: 45%;
	width: auto;
	height: auto;
	z-index: 101;
	padding: 6px;
	margin: 5px;
	text-align: center;
	font-weight: bold;
	font-size: 12px;
	background-color: #FFF;
	border: 2px solid #8EB8D1;
	color: #E2B018
}

.ui-jqgrid .ui-search-toolbar {
	border-top: 1px solid #e7ecf1
}

.ui-jqgrid .ui-jqgrid-labels {
	border-bottom: none;
	background: #F2F2F2;
	background-image: -webkit-linear-gradient(top, #f8f8f8 0, #ececec 100%);
	background-image: linear-gradient(to bottom, #f8f8f8 0, #ececec 100%);
	background-repeat: repeat-x;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fff8f8f8',
		endColorstr='#ffececec', GradientType=0 );
	padding: 0 !important;
	border-left: 1px solid #e7ecf1 !important
}

.ui-jqgrid .ui-jqgrid-labels th {
	border-right: 1px solid #e7ecf1 !important;
	text-align: left !important
}

.ui-jqgrid-labels th[id*="_cb"]:first-child>div {
	padding-top: 0;
	text-align: center !important
}

.ui-jqgrid-sortable {
	padding-left: 4px;
	font-size: 13px;
	color: #777;
	font-weight: bold
}

.ui-jqgrid-sortable:hover {
	color: #547ea8
}


.ui-jqdialog-content,.ui-jqdialog .ui-jqdialog-content {
	font-size: 13px;
	padding: 4px 0 0
}

.ui-jqdialog-content .formdata,.ui-jqdialog .ui-jqdialog-content .formdata
	{
	font-size: 13px;
	padding: 6px 12px
}

.ui-jqdialog-content .form-view-data,.ui-jqdialog .ui-jqdialog-content .form-view-data
	{
	vertical-align: middle;
	font-size: 13px
}

.ui-jqdialog-content[id*="alertcnt_"],.ui-jqdialog .ui-jqdialog-content[id*="alertcnt_"]
	{
	padding: 8px 11px
}

.ui-jqdialog-content .CaptionTD {
	font-size: 12px;
	text-align: right;
	color: #666
}

.ui-jqdialog-content .FormData {
	border-bottom: 1px dotted #E8E8E8
}

.ui-jqdialog-content .FormData:last-child {
	border-bottom: none
}

.ui-jqdialog-content .FormData>td {
	padding-top: 6px;
	padding-bottom: 6px
}

.ui-jqdialog-content input.FormElement {
	width: auto
}

.ui-jqdialog-content select.FormElement {
	padding: 1px;
	height: 25px;
	line-height: 25px;
	width: auto
}

.ui-jqdialog-content td.EditButton {
	padding: 8px
}

.EditTable {
	background-color: #eff3f8;
	border-top: 1px solid #D6E1EA !important;
	padding: 8px
}

.EditTable tr:first-child {
	display: none
}

.EditTable .navButton .fm-button {
	float: none !important;
	width: auto !important;
	margin: 1px 1px 2px !important;
	background-color: transparent;
	border-radius: 100%
}

.EditTable .navButton .fm-button:hover {
	background-color: transparent
}

.EditTable .navButton .fm-button:focus {
	outline: none
}

.EditTable .navButton .fm-button .ace-icon {
	display: inline-block;
	color: #999;
	border: 1px solid #AAA;
	width: 26px;
	height: 26px;
	line-height: 26px;
	text-align: center;
	border-radius: 100%;
	background-color: #FFF
}

.EditTable .navButton .fm-button:hover .ace-icon {
	color: #699AB5;
	border-color: #699AB5
}

.EditTable .navButton .fm-button.ui-state-disabled .ace-icon,.EditTable .navButton .fm-button.ui-state-disabled:hover .ace-icon
	{
	color: #BBB;
	border-color: #e7ecf1;
	-moz-transform: scale(0.88);
	-webkit-transform: scale(0.88);
	-o-transform: scale(0.88);
	-ms-transform: scale(0.88);
	transform: scale(0.88)
}

.FormGrid .EditTable {
	background-color: #FFF;
	border-top: none !important;
	padding: 0
}

.FormGrid .EditTable tr:first-child {
	display: none
}

.ui-jqgrid .ui-jqgrid-view input,.ui-jqgrid .ui-jqgrid-view select,.ui-jqgrid .ui-jqgrid-view textarea,.ui-jqgrid .ui-jqgrid-view button
	{
	font-size: 13px
}

.ui-jqdialog-content .searchFilter select {
	padding: 1px;
	height: 26px;
	line-height: 26px;
	width: auto;
	max-width: 95%;
	margin-bottom: 0
}

.ui-jqdialog-content .searchFilter .input-elm {
	margin-bottom: 0;
	height: 18px;
	line-height: 18px;
	width: 95% !important;
	padding-left: 1px;
	padding-right: 1px;
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box
}

.ui-jqdialog-content .searchFilter table {
	margin-left: 4px
}

.ui-jqdialog-content .searchFilter tr td {
	padding: 5px 0
}

.ui-jqdialog-content .searchFilter .add-group,.ui-jqdialog-content .searchFilter .add-rule,.ui-jqdialog-content .searchFilter .delete-group
	{
	margin-left: 4px !important;
	font-size: 15px !important
}

.ui-jqdialog-content .searchFilter .delete-rule {
	border: none;
	background-color: #FFF;
	color: #D15B47;
	font-size: 20px;
	width: 22px;
	line-height: 10px;
	padding: 0;
	text-shadow: none !important;
	display: inline-block;
	-webkit-transition: all 0.1s;
	transition: all 0.1s;
	opacity: 0.85
}

.ui-jqdialog-content .searchFilter .delete-rule:hover {
	-moz-transform: scale(1.1);
	-webkit-transform: scale(1.1);
	-o-transform: scale(1.1);
	-ms-transform: scale(1.1);
	transform: scale(1.1);
	color: #B74635;
	opacity: 1
}

.ui-jqdialog-content .searchFilter .queryresult {
	margin-bottom: 11px
}

.ui-jqdialog-content .searchFilter .queryresult td.query {
	padding: 6px 11px;
	border: 1px solid #e7ecf1;
	background-color: #EEEEEE
}

.ui-jqdialog-content .searchFilter .queryresult td.query:empty {
	display: none
}

.ui-state-error {
	background-color: #f2dede;
	border: 1px solid #ebccd1;
	color: #a94442;
	margin: 4px 4px 8px;
	padding: 6px 10px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
	font-size: 13px
}

.ui-jqdialog .ui-widget-header {
	background-image: -webkit-linear-gradient(top, #fff 0, #eee 100%);
	background-image: linear-gradient(to bottom, #fff 0, #eee 100%);
	background-repeat: repeat-x;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff',
		endColorstr='#ffeeeeee', GradientType=0 );
	border-image: none;
	border-bottom: 1px solid solid;
	color: #669FC7;
	min-height: 38px;
	position: relative
}

.ui-jqdialog .ui-widget-header .ui-jqdialog-title {
	line-height: 38px;
	margin: 0;
	padding: 0;
	padding-left: 12px;
	text-align: left
}

.widget-header .ui-jqdialog-title {
	padding-left: 0 !important;
	padding-right: 0 !important
}

.ui-jqdialog .ui-widget-header .widget-header {
	border-bottom: none
}

.ui-jqdialog .ui-jqdialog-titlebar {
	border-bottom: 1px solid #e7ecf1 !important
}

.fm-button {
	margin: 0 4px
}

.fm-button:not (.btn ){
	background-color: #abbac3;
	border-radius: 0;
	box-shadow: none;
	color: #FFFFFF;
	cursor: pointer;
	display: inline-block;
	font-size: 13px;
	line-height: 28px;
	padding: 0 12px 1px;
	margin: 0 8px;
	position: relative;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
	-webkit-transition: all 0.15s;
	transition: all 0.15s;
	vertical-align: middle
}

.fm-button.ui-state-default:hover {
	background-color: #8b9aa3
}

.ui-jqgrid .ui-jqgrid-htable .ui-search-toolbar th {
	height: 30px;
	padding-top: 2px;
	white-space: normal
}

.ui-jqgrid .ui-jqgrid-htable .ui-search-toolbar th div {
	padding-top: 0;
	padding-bottom: 0;
	height: 30px;
	line-height: 26px
}

.ui-jqgrid .ui-jqgrid-titlebar-close {
	top: 10%;
	height: auto;
	padding: 0;
	margin: 2px 8px 0 0;
	text-align: center;
	border-radius: 4px
}

.ui-jqgrid .ui-jqgrid-titlebar-close:hover {
	background-color: rgba(255, 255, 255, 0.2)
}

.ui-jqgrid .ui-jqgrid-titlebar-close .ui-icon:before {
	display: inline-block;
	font-family: FontAwesome;
	content: "\f077";
	color: #FFF
}

.ui-jqgrid .ui-jqgrid-titlebar-close .ui-icon-circle-triangle-s:before {
	content: "\f078"
}

.ui-jqgrid .tree-wrap-ltr {
	margin: 0 4px;
	float: none;
	display: inline
}

.ui-jqgrid .tree-wrap-rtl {
	margin: 2px 4px 0
}

.ui-jqgrid .ui-subgrid {
	border-bottom: 1px solid #e7ecf1;
	background-color: #F6FAFF
}

.ui-jqgrid .ui-subgrid .ui-jqgrid-btable {
	background-color: #FFF
}

.ui-jqgrid .ui-subgrid .ui-jqgrid .ui-jqgrid-hdiv {
	background-color: transparent;
	margin-top: 4px
}

.ui-jqgrid .ui-subgrid .ui-jqgrid .ui-jqgrid-hdiv .ui-jqgrid-htable .ui-jqgrid-labels
	{
	border-bottom: 1px solid #e7ecf1;
	background: #F1F1F1
}

.ui-jqgrid .ui-subgrid .ui-jqgrid .ui-jqgrid-hdiv .ui-jqgrid-htable th[aria-selected="true"]
	{
	background: #E5E9EF
}

.ui-jqgrid .ui-subgrid .ui-jqgrid .ui-jqgrid-hdiv .ui-jqgrid-htable th .ui-jqgrid-sortable
	{
	font-size: 12px
}

.ui-jqgrid .ui-subgrid .ui-jqgrid .ui-jqgrid-hdiv .ui-jqgrid-htable th div
	{
	padding-top: 8px;
	padding-bottom: 8px
}

.ui-jqgrid .ui-subgrid .ui-jqgrid .ui-jqgrid-hdiv .ui-jqgrid-htable th span.ui-jqgrid-resize
	{
	height: 36px !important
}

.ui-jqgrid .ui-subgrid .ui-jqgrid .ui-jqgrid-bdiv {
	height: auto !important;
	max-height: 150px;
	margin-bottom: 4px;
	border-top-width: 0;
	border-bottom: 1px solid #e7ecf1
}

.ui-jqgrid .ui-sgcollapsed>a:hover {
	text-decoration: none
}

.error-container {
	margin: 20px;
	padding: 0;
	background: #FFF
}

@media only screen and (max-width:767px) {
	.error-container {
		margin: 12px
	}
}

@media only screen and (max-width:479px) {
	.error-container {
		margin: 6px
	}
}



.ui-dialog,.ui-jqdialog {
	z-index: 1050 !important;
	background-color: #FFF;
	padding: 0;
	border: 1px solid #e7ecf1;
	-webkit-box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2)
}

.ui-dialog .ui-dialog-titlebar,.ui-jqdialog .ui-dialog-titlebar,.ui-dialog .ui-jqdialog-titlebar,.ui-jqdialog .ui-jqdialog-titlebar
	{
	background-color: #F1F1F1;
	font-size: 16px;
	color: #669fc7;
	padding: 0
}

.ui-dialog .ui-dialog-title,.ui-jqdialog .ui-dialog-title,.ui-dialog .ui-jqdialog-title,.ui-jqdialog .ui-jqdialog-title
	{
	float: none !important;
	width: auto
}

.ui-dialog .widget-header,.ui-jqdialog .widget-header {
	margin: 0;
	border-width: 0 0 1px 0
}

.ui-dialog .ui-dialog-buttonpane,.ui-jqdialog .ui-dialog-buttonpane,.ui-dialog .ui-jqdialog-buttonpane,.ui-jqdialog .ui-jqdialog-buttonpane
	{
	background-color: #eff3f8;
	border-top: 1px solid #e4e9ee
}

.ui-dialog .ui-dialog-buttonpane button,.ui-jqdialog .ui-dialog-buttonpane button,.ui-dialog .ui-jqdialog-buttonpane button,.ui-jqdialog .ui-jqdialog-buttonpane button
	{
	font-size: 14px
}

.ui-dialog .ui-dialog-titlebar-close,.ui-jqdialog .ui-dialog-titlebar-close,.ui-dialog .ui-jqdialog-titlebar-close,.ui-jqdialog .ui-jqdialog-titlebar-close
	{
	border: none;
	opacity: 0.4;
	color: #999;
	padding-right: 20px;
	top: 50%;
	text-align: center;
	width: 9px;
    height: 9px;
    background-repeat: no-repeat!important;
    background-image: url(../global/img/remove-icon-small.png)!important;
}

.ui-dialog .ui-dialog-titlebar-close:before,.ui-jqdialog .ui-dialog-titlebar-close:before,.ui-dialog .ui-jqdialog-titlebar-close:before,.ui-jqdialog .ui-jqdialog-titlebar-close:before
	{
	content: "";
	display: inline;
	font-family: FontAwesome;
	font-size: 16px;

}

.ui-dialog .ui-dialog-titlebar-close:hover,.ui-jqdialog .ui-dialog-titlebar-close:hover,.ui-dialog .ui-jqdialog-titlebar-close:hover,.ui-jqdialog .ui-jqdialog-titlebar-close:hover
	{
	opacity: 1;
	text-decoration: none;
	padding-right: 20px;
}

.ui-dialog .ui-dialog-titlebar-close .ui-button-text,.ui-jqdialog .ui-dialog-titlebar-close .ui-button-text,.ui-dialog .ui-jqdialog-titlebar-close .ui-button-text,.ui-jqdialog .ui-jqdialog-titlebar-close .ui-button-text
	{
	text-indent: 0;
	visibility: hidden
}

.ui-dialog .widget-header .ui-dialog-titlebar-close,.ui-jqdialog .widget-header .ui-dialog-titlebar-close,.ui-dialog .widget-header .ui-jqdialog-titlebar-close,.ui-jqdialog .widget-header .ui-jqdialog-titlebar-close
	{
	right: 10px !important
}


.ui-jqgrid .ui-jqgrid-pager {
  line-height: 15px;
  height: 36px;
  padding-top: 2px !important;
  padding-bottom: 2px !important;
  border-bottom: 1px solid #e7ecf1 !important;
  border-top: 1px solid #e7ecf1 !important;
  border-left: 1px solid #e7ecf1 !important;
  border-right: 1px solid #e7ecf1 !important;
}
.ui-jqgrid .ui-jqgrid-htable th span.ui-jqgrid-resize {
  height: 29px !important;
}
.ui-jqgrid .ui-jqgrid-htable th div {
  padding-top: 10px;
  padding-bottom: 10px;
}
.ui-jqdialog .ui-widget-header .widget-header {
    border-bottom: none;
    min-height: 30px;
    height: 30px;
    padding:15px;
}
.widget-header>.widget-title {
    color:#666
}
.ui-jqdialog .ui-widget-header {
    color:#666
}
.ui-jqdialog .ui-widget-header .ui-jqdialog-title {
    color:#666
}
.ui-jqdialog-content td.EditButton {
    padding: 5px;
    color:#666
}
.ui-jqdialog-content td.navButton {
    padding-bottom: 3px;
    padding-top: 3px;
}
.ui-dialog .ui-dialog-buttonpane {
    margin-top: .5em;
    padding: .0em .0em .0em .0em;
}
.widget-toolbar {
    display: inline-block;
    padding: 0 10px;
    line-height: 28px;
    float: right;
    position: relative;
}
.ui-jqgrid .ui-jqgrid-htable th div {
    overflow: hidden;
    position: relative;
    height: 35px;
}

.ui-dialog .ui-dialog-titlebar, .ui-jqdialog .ui-dialog-titlebar, .ui-dialog .ui-jqdialog-titlebar, .ui-jqdialog .ui-jqdialog-titlebar {
    background-color: #F1F1F1;
    font-size: 14px;
    color: #669fc7;
    padding: 0;
}
.ui-dialog .ui-dialog-buttonpane button, .ui-jqdialog .ui-dialog-buttonpane button, .ui-dialog .ui-jqdialog-buttonpane button, .ui-jqdialog .ui-jqdialog-buttonpane button {
     font-size: 12px;
}

/*****end dataTable***/
.header-title {
    line-height: 20px;
    margin-bottom: 5px;
    margin-top: 5px;
    padding-bottom: 4px;
    border-bottom: 1px solid #e7ecf1;
    font-weight:800;
    font-size:14px;
}
.header-title-jqgrid {
    line-height: 20px;
    margin-bottom: 5px;
    margin-top: 5px;
    padding-bottom: 4px;
    border-bottom: 1px solid #e7ecf1;
    font-weight:800;
    font-size:14px;
    color:#000000;
    margin-right: 30px;

}
.header-title-custom {
    line-height: 20px;
    margin-bottom: 2px;
    margin-top: 2px;
    padding-bottom: 2px;
    border-bottom: 1px solid #CCC;
    font-weight:800;
    font-size:14px;
}


.ui-jqgrid .ui-jqgrid-pager {
    background-color: #ffffff !important;
}
.EditTable {
   background-color: #fff;
   height:60px;
}
.widget-header {
    background: #fff;
    background-image: -webkit-linear-gradient();
    background-image: linear-gradient();
    background-repeat: repeat-x;
    filter: progid:DXImageTransform.Microsoft.gradient();
}
.ui-jqdialog-content .FormData {
     border-bottom: 0px dotted #E8E8E8;
}
.ui-jqgrid .ui-jqgrid-labels {
    background: #fff;
    background-image: -webkit-linear-gradient();
   background-image: linear-gradient();
     background-repeat: repeat-x;
    filter: progid:DXImageTransform.Microsoft.gradient();
}
.ui-jqgrid tr.ui-row-ltr td, .ui-jqgrid tr.ui-row-rtl td {
    border-color: #e7ecf1;
}
.ui-jqgrid .ui-jqgrid-labels th {
    border-right: 1px solid #e7ecf1 !important;
    text-align: left !important;
}
.ui-jqgrid-btable .ui-widget-content.ui-priority-secondary{
    background-color: #fbfcfd;

}

.ui-jqgrid-btable .ui-widget-content.ui-state-highlight {
    background-color: #C8D046;
}
.ui-dialog .ui-dialog-buttonpane, .ui-jqdialog .ui-dialog-buttonpane, .ui-dialog .ui-jqdialog-buttonpane, .ui-jqdialog .ui-jqdialog-buttonpane {
    background-color: #ffffff;
}

input[readonly] {
    color: #000000;
    background: #fff !important;
    border: 0px solid #fff;
    cursor: default;
}
.ui-jqgrid-view>.ui-jqgrid-titlebar {
    height: 25px;
    line-height: 25px;
    padding: 0;
    font-size: 14px;
     background-image: -webkit-linear-gradient();
   background-image: linear-gradient(rgb(255, 255, 255) 0px, rgb(255, 255, 255) 100%);
    background-repeat: repeat-x;
    filter: progid:DXImageTransform.Microsoft.gradient();
    border-bottom: 1px solid solid;
    color: #000000;
    min-height: 25px;
}
.ui-dialog, .ui-jqdialog {
       border: 1px solid rgba(0,0,0,.2);
       border-radius: 0px;
       -webkit-box-shadow: 0 3px 9px rgba(0,0,0,.5);
       box-shadow: 0 5px 15px rgba(0,0,0,.5);
       outline: 0;
}
th[aria-selected=true] {
    background-image: -webkit-linear-gradient(top, #fff 0, #fff 100%);
    background-image: linear-gradient(to bottom, #fff 0, #fff 100%);
    filter: progid:DXImageTransform.Microsoft.gradient();
}
.ui-jqdialog-content .FormGrid {
    margin: 0;
    padding: 15px 15px 15px 15px;
}
.dataTable>thead>tr>th.sorting_desc, .dataTable>thead>tr>th.sorting_asc {
     background-image: -webkit-linear-gradient(top, #fff 0, #fff 100%);
    background-image: linear-gradient(to bottom, #fff 0, #fff 100%);
    background-repeat: repeat-x;
    filter: progid:DXImageTransform.Microsoft.gradient();
}
.ui-jqgrid .ui-jqgrid-hdiv {
    overflow-x: '';
}

 .ui-jqgrid tr.ui-row-ltr td, .ui-jqgrid tr.ui-row-rtl td {
     border-bottom: 1px solid #e7ecf1;
     padding: 7px 4px;
     border-color: #e7ecf1;
 }

.ui-dialog .widget-header, .ui-jqdialog .widget-header {
    margin: 0;
    padding: 15px;
    border-width: 0 0 1px 0;
}
.ui-icon {
    text-indent: 0;
    color: #307ecc;
    float: none;
    right: 2px;
}
.ui-jqgrid tr.jqgrow td { white-space: normal !important; height:auto; }
.paginationbar {
    width: 100%;
    text-align: right;
}
.ui-jqgrid tr.ui-row-ltr td {
    text-align: left;
    border-right-width: 0px;
    border-right-color: inherit;
    border-right-style: solid;
}
.ui-jqgrid .ui-jqgrid-labels th {
    border-right: 0px solid #e7ecf1 !important;
    text-align: left !important;
}
.ui-jqgrid .ui-jqgrid-btable {
    border-left: 0px solid #e7ecf1;
}
.ui-jqgrid-hdiv .ui-jqgrid-htable {
    border-top: 0px solid #e7ecf1;
}
.ui-jqgrid-hdiv .ui-jqgrid-htable {
    border-right: 0px solid #e7ecf1;
}
.ui-jqgrid .ui-jqgrid-labels {
    border-left: 0px solid #e7ecf1 !important;
}
</style>


<link rel="stylesheet" href="${portalPath}/content/common/css/ui.multiselect.css?version=${cfg.version}" />
<link href="${portalPath}/content/common/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<%if(portalType.equals("1")){%>
<link href="${portalPath}/content/common/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css"/>
<link href="${portalPath}/content/common/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color"/>
<%}else{%>
<link href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css" rel="stylesheet" type="text/css"/>
<link href="${portalPath}/content/common/assets/layouts/layout3/css/themes/default.min.css" rel="stylesheet" type="text/css"/>
<%}%>
<link rel="shortcut icon" href="favicon.ico"/>
<%if(portalType.equals("4")){%>
<link href="${portalPath}/content/common/assets/layouts/layout3/css/custom.min.css" rel="stylesheet" type="text/css"/>
<%}%>
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/css/components.min.css?v=${cfg.version}" />

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">

<div class="page-content">
	<div class="widget-box" id="widget-box">
		<div class="widget-header">
			<h5 class="widget-title smaller">设置查询条件</h5>

			<div class="widget-toolbar"></div>
		</div>

		<div class="widget-body">
			<div class="widget-main padding-6">
				<form action="#" id="fm-search">
					角色名称： <input name="roleName" type="text"
								 style="width: 200px;height:25px" />

					<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/role/findRoleList.do">
						<i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
					</button>

				</form>
				<div class="space10"></div>
				<div id="toolbar" class="toolbar">

					<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/role/insertRole.do">
						<i
								class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
					</button>
					<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/role/updateRole.do">
						<i
								class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
					</button>
					<button class="btn btn-purple" id="btn-view-da"
							authority="${pageContext.request.contextPath}/role/insertRoleResources.do">
						<i
								class="ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right"></i>
					</button>
					<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/role/deleteRoleByRoleId.do">
						<i
								class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
					</button>

				</div>
			</div>
		</div>
	</div>

	<table id="grid-table"></table>
	<div class="paginationbar"><ul id="grid-pager" class="pagination"></ul></div>



	<div id="dialog-message" class="hide">
		<div class="easyui-panel" style="padding:5px;width:350px;height:400px">
			<ul id="tt" class="easyui-tree" data-options="url:'${pageContext.request.contextPath}/role/selectRoleResourceTreeList.do?roleId=1',method:'get',animate:true,checkbox:true,lines:false">
			</ul>
		</div>
		<div id="spinner-preview"></div>
	</div>
	<div id="dialog-confirm" class="hide">
		<div class="alert alert-info bigger-110">
			重新分配权限后，分配此角色的用户将获取新的权限.

		</div>

		<div class="space-6"></div>

		<p class="bigger-110 bolder center grey">
			<i class="ace-icon fa fa-hand-o-right blue bigger-120"></i>
			您确定吗?
		</p>
	</div>
</div>
<jsp:include page="../../common/footer-1.jsp" />
<script
		src="${pageContext.request.contextPath}/content/portal/js/logs/config.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/logs/model.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/logs/controller.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/logs/view.js?version=${cfg.version}"></script>
<jsp:include page="../../common/footer-2.jsp" />
<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);
	////parent.autoWidth();
}





</script>
</body>
</html>
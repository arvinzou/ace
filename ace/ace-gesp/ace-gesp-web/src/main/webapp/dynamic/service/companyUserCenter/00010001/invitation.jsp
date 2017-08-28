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
<title>memberCenter</title>
</head>
<jsp:include page="../../../common/common.jsp" />
<script type="text/javascript">var flag="${param.flag}";</script>
<body>
	<div class="page-content">
		<div class="col-xs-12 col-sm-12">
			<h2 align="center" style="font-color:red;">深圳市公路货运与物流行业协会入会邀请函</h2>

       深圳市公路货运与物流行业协会（SHENZHEN TRUCKLOAD&LOGISTICS  ASSOCIATION）是由深圳市交通运输委员会批准，并在深圳市民政局正式注册登记的具有独立法人资格的地方性、行业性的非营利社团组织。</p>
        协会成立于2011年6月，现有会员750家。会员主要包括深圳市的公路货运枢纽、货运场站中心、货运交易场、公路货运物流中心；城际公路快运企业；全国干线运输与配载企业；区域配送企业；仓储物流企业；冷链物流企业；特种公路货物运输企业；
        综合型物流服务商；以及为公路货运提供产品或服务等相关行业的企业、机构、单位。会员企业现已建成和运营310万平方米的货运枢纽和物流中心，拥有和管理7万台各式货车。日均处理货物65万吨。服务网络覆盖全国和东南亚国家。为深圳和珠三角提供领先全国的公路货运和物流服务。</p>

<strong>协会的宗旨和服务：</strong></p>
遵守宪法、法律、法规，维护行业利益；</p>
承接政府主管部门授权委托的深圳市公路货运企业和货运车辆年审预审、新增运力预审、行业安全培训等职能和服务；</p>
由市交委授权开展交通行业（含客运）安全达标考评；</p>
反映会员的要求与呼声、协调解决纠纷和问题；</p>
加强与国内外同行业的联系与交流，帮助会员企业拓展市场；</p>
组团在全国物流节点城市开发、建设、运营物流产业园；</p>
促进深圳公路货运业繁荣发展，推进深圳公路货运行业标准化、正规化、信息化，增强深圳公路货运企业核心竞争力；</p>
由中国物流与采购联合会授权在深圳开展“物流企业分类与评估”和“质押监管企业评估”。</p>
通过“深圳市城市配送公共服务平台”推动深圳市“共同配送 集中配送 智慧配送”。</p>
协会设立行政部（办公室）、会员部、传媒中心、物流企业分类与评估办公室、质押监管企业评估办公室、培训中心、南方物流咨询与研究中心，承担服务公路货运与物流行业和承接政府外包服务的职能。</p></p>

<strong>会员援助服务</strong></p>
• 为会员提供法律援助服务、保险顾问服务、税务咨询服务；</p>
• 为会员提供管理体系培训、物流管理体系标准等认证咨询服务；</p>
• 代表企业加强与政府在税收、保险、海关等方面的沟通，保护会员的正当权益；</p>
• 代会员企业办理投资手续，为会员提供必需的证明、办证和办照等报批手续。</p></p>

<h4 style="font-color:red;font-size:15px;">入 会 程 序</h4>

1、阅读了解“协会简介”，认可“协会章程”，明白会员的权利和义务；</p>
2、填写 “深圳市公路货运与物流行业协会企业会员入会申请表” 一式二份，单位负责人签名，加盖公司公章。</p>
3、备企业法人营业执照副本复印件一份。</p>
4、凭填好的“入会申请表”和 “企业法人营业执照复印件”，到协会秘书处办理入会手续；</p>
5、会费收取标准：</p>
（1）普通会员2000元/年</p>
（2）理事单位5000元/年</p>
（3）副会长单位10000元/年</p>
（4）常务副会长单位15000元/年</p>
（5） 会长单位30000元/年</p>
6、上述手续办妥后，即可成为正式会员，协会发给会员证。</p>
7、深圳市公路货运与物流行业协会的联系方式：</p>
地  址: 深圳市福田区车公庙泰然九路一号盛唐大厦西座2713</p>
 邮  编：518048</p>
电  话：0755-23619971     传  真：0755-23619973</p>
联系人：杨小姐  13798292094</p>
网  址：www.sztla.org.cn    </p>
			<div class="space-12"></div>
		</div>
		<div class="space-12"></div>
		<div align="center" id="invitation_hidden">
			<a class="btn btn-info"  href="../00010001/application.jsp" authority="false" style="width:90px;font-size:16px;" 
				onclick="javascript:;">我要入会</a>
		</div>
	</div>

	<jsp:include page="../../../common/footer-1.jsp" />
	<jsp:include page="../../../common/footer-2.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/companyUserCenter/model.js?version=${cfg.version}"></script>
	<script type="text/javascript">
		window.onresize = function() {
			autoSize();
		}
		parent.onresize = function() {
			autoSize();
		}
		function autoSize() {
			parent.autoWidth();
		}
	</script>
</body>
</html>
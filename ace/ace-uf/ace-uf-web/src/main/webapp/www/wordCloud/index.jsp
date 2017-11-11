<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

	<script type="text/javascript">
			window.jQuery || document.write("<script src='/portal/content/common/assets/js/gz/jquery.min.js?version=10'>"+"<"+"/script>");
			var id='${param.id}';
	</script>
	<script src='https://cdn.bootcss.com/echarts/3.7.0/echarts.simple.js'></script>
	<script src='js/echarts-wordcloud.js'></script>
	<link href="css/style.css" rel="stylesheet" type="text/css"/>
<title></title>
</head>


<body>
<div id="wrapper">

	<div class="box3">

		<table  class="table3">
			<thead>
			<tr>
				<th class="bgleftc">属性</th>
				<th class="bgrightc">值</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td class="bgleftc">年龄</td>
				<td class="bgrightc">13-20</td>
			</tr>
			<tr>
				<td class="bgleftc">住址</td>
				<td class="bgrightc"> </td>
			</tr>
			</tbody>
		</table>
	</div>
	<div id='main' class="box4"></div>
</div>

<script src='index.js'></script>
</body>
</html>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="css/test.css" />
	<link rel="stylesheet" type="text/css" href="css/detail.css" />
	<jsp:include page="/dynamic/common/header.jsp"/>
	<script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
	<jsp:include page="../../common/common.jsp"/>
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="title1" id="title">
	<p>党史讲座：学党史、知党情</p>
</div>
<div class="testContent" id="test">
</div>

<div class="submit back">
	返回
</div>
<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
<script id="tpl_test" type="text/template">

	<div class="testItem" data-name="固定题" data-introduce="本门课程设置的必要性（不考虑教师的授课水平）">
		<div class="testTitle">
			<span class="text">01.本门课程设置的必要性（不考虑教师的授课水平）</span>
		</div>
		<div class="testScore">
			<div class="option">
				<input value="1" \${data.rst.judge=="1"?"checked":''} type="radio" name="test_1" id="option_1"/>
				<label for="option_1">很有必要</label>
			</div>
			<div class="option">
				<input value="2" \${data.rst.judge=="2"?"checked":''} type="radio" name="test_1" id="option_2"/>
				<label for="option_2">可以开设</label>
			</div>
			<div class="option">
				<input value="3" \${data.rst.judge=="3"?"checked":''} type="radio" name="test_1" id="option_3"/>
				<label for="option_3">可有可无</label>
			</div>
			<div class="option">
				<input value="4" \${data.rst.judge=="4"?"checked":''} type="radio" name="test_1" id="option_4"/>
				<label for="option_4">无需开设</label>
			</div>
		</div>
	</div>

	{@each data.list as item,index}
	<div class="testItem items">
		<div class="testTitle">
			<span class="text">\${index|formatIndex}.\${item.name}:\${item.introduce}</span>
		</div>
		<div class="testScore">
			<div class="core">
				<div class="score">
					<p data-total="\${item.score}" class="number">\${item.score}</p>
				</div>
			</div>
		</div>
	</div>
	{@/each}
	<div class="testItem">
		<div class="testTitle">
			<span class="text" id="question">最后:您对提高培训质量和满意度有何意见</span>
		</div>
		<div class="input_text">
			<div id="textarea" class="message" >\${data.rst.content}</div>
		</div>
	</div>
</script>


<script id="tpl_title" type="text/template">
	<p>\${data.name}</p>
</script>
<script src="js/detail.js" type="text/javascript" charset="utf-8"></script>
</html>

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
	<title>课程管理</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1" name="viewport"/>
	<meta content="${cfg.sys_name}" name="description"/>
	<jsp:include page="/dynamic/common/header.jsp"/>
	<link rel="stylesheet" type="text/css" href="css/test.css"/>
	<script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>

</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

	<div class="portlet-body">
		<div class="table-scrollable">
			<div class="title1" id="title">
				<p></p>
			</div>
			<div class="testContent" id="test">
			</div>

			<div class="submit">
				点击提交
			</div>
		</div>
	</div>

</div>






<script id="tpl_test" type="text/template">


	{@each data as item,index}
	<div class="testItem items" data-name="\${item.name}" data-introduce="\${item.introduce}">
		<div class="testTitle">
			<span class="text">\${index|formatIndex}.\${item.name}:\${item.introduce}（0-\${item.score}分）</span>
		</div>
		<div class="testScore">
			<div class="core">
				<div class="button subBtn">
				</div>
				<div class="score">
					<input type="number" onblur="checkNumber(this,'\${item.score}')" max="\${item.score}" data-total="\${item.score}" class="number" value="\${item.score|parseIntF}">
				</div>
				<div class="button addBtn"></div>
			</div>
		</div>
	</div>
	{@/each}
	<div class="testItem" data-name="固定题" data-introduce="本门课程设置的必要性（不考虑教师的授课水平）">
		<div class="testTitle">
			<span class="text">本门课程设置的必要性（不考虑教师的授课水平）</span>
		</div>
		<div class="testScore">
			<div class="option">
				<input value="1" checked type="radio" name="test_1" id="option_1"/>
				<label for="option_1">很有必要</label>
			</div>
			<div class="option">
				<input value="2" type="radio" name="test_1" id="option_2"/>
				<label for="option_2">可以开设</label>
			</div>
			<div class="option">
				<input value="3" type="radio" name="test_1" id="option_3"/>
				<label for="option_3">可有可无</label>
			</div>
			<div class="option">
				<input value="4" type="radio" name="test_1" id="option_4"/>
				<label for="option_4">无需开设</label>
			</div>
		</div>
	</div>
	<div class="testItem">
		<div class="testTitle">
			<span class="text" id="question">最后:您对提高培训质量和满意度有何意见</span>
		</div>
		<div class="input_text">
			<textarea id="textarea" maxlength="200" class="message" placeholder="请在此输入您要填写的内容~200字以内"></textarea>
			<span class="font_number">200</span>
		</div>
	</div>
</script>


<script id="tpl_title" type="text/template">
	<p>\${data.name}</p>
</script>



<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
<script src="js/test.js"></script>
</html>


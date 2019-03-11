<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/test.css" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/flexible.js"></script>
		<jsp:include page="../../common/common.jsp"/>
	</head>

	<body>
		<div class="title" id="title">
			<p></p>
		</div>
		<div class="testContent" id="test">

		</div>

		<div class="submit">
			点击提交
		</div>
	</body>
	<script id="tpl_test" type="text/template">
				{@each data as item, index} 
					{@if item.type==1||item.type==3}	
			<div class="testItem items testStyle2">
				<div class="testTitle">
					<span class="text">Q\${formatIndex(index)}、\${item.content} \${formatScore(item.tscore)}</span>
				</div>
				<div class="testScore">
					{@each item.topicOptList as itm, idx} 
					<div class="option">
						<input value="\${idx}" data-answer="\${itm.answer}" type="radio" name="test_\${index}" id="option_\${index}_\${idx}"/>
						<label for="option_\${index}_\${idx}">\${itm.content}</label>
					</div>
					{@/each}
				</div>
			</div>
						
			{@else if item.type == 2}
		<div class="testItem items testStyle2">
				<div class="testTitle">
					<span class="text">Q\${formatIndex(index)}、\${item.content} \${formatScore(item.tscore)}</span>
				</div>
				<div class="testScore">
					{@each item.topicOptList as itm, idx} 
					<div class="option">
						<input value="\${idx}" data-answer="\${itm.answer}" type="checkbox" name="test_\${index}" id="option_\${index}_\${idx}"/>
						<label for="option_\${index}_\${idx}">\${itm.content}</label>
					</div>
					{@/each}
				</div>
		</div>
		{@else if item.type == 4}


			<div class="testItem">
				<div class="testTitle">
					<span class="text">Q\${formatIndex(index)}、\${item.content} \${formatScore(item.tscore)}</span>
				</div>
				<div class="input_text">
					<textarea class="message textTest_\${index}"  maxlength="200"  placeholder="请在此输入您要填写的内容~200字以内"></textarea>
				</div>
			</div>
		
		{@else if item.type == 5}
		<div class="testItem items" data-name="\\${item.name}" data-introduce="\\${item.introduce}">
				<div class="testTitle">
					<span class="text">Q\${formatIndex(index)}、\${item.content} \${formatScore(item.tscore)}</span>
				</div>
				<div class="testScore">
					<div class="core">
						<div class="button subBtn">
						</div>
						<div class="score">
							<p data-total="\${item.tscore}"  class="number textTest_\${index}">\${item.tscore|parseIntF}</p>
						</div>
						<div class="button addBtn"></div>
					</div>
				</div>
			</div>
		{@else}
		<div>111</div>
		{@/if}
		{@/each}

	</script>

	<script id="tpl_title" type="text/template">
		<p>\${data.name}</p>
	</script>
	<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
	<script src="js/test.js" type="text/javascript" charset="utf-8"></script>

</html>
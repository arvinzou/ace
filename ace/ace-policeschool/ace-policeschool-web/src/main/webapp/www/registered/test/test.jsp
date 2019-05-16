<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/test.css"/>
    <script src="js/flexible.js"></script>
    <jsp:include page="../../common/common.jsp"/>
</head>
<body>
<div class="title" id="title">
    <p>党史讲座：学党史、知党情</p>
</div>
<div class="testContent" id="test">
</div>

<div class="submit">
    点击提交
</div>
</body>
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
                    <p data-total="\${item.score}" class="number">\${item.score|parseIntF}</p>
                </div>
                <div class="button addBtn"></div>
            </div>
        </div>
    </div>
    {@/each}
    <div class="testItem">
        <div class="testTitle">
            <span class="text">最后:您对提高培训质量和满意度有何意见</span>
        </div>
        <div class="input_text">
            <textarea id="textarea" maxlength="200" class="message"
                      placeholder="请在此输入您要填写的内容~200字以内,感谢您的支持和建议!"></textarea>
            <span class="font_number">200</span>
        </div>
    </div>
</script>


<script id="tpl_title" type="text/template">
    <p>\${data.name}</p>
</script>
<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="js/test.js" type="text/javascript" charset="utf-8"></script>
</html>

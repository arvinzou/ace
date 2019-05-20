<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/test.css"/>
    <link rel="stylesheet" type="text/css" href="css/detail.css"/>
    <script src="js/flexible.js"></script>
    <jsp:include page="../../common/common.jsp"/>
</head>
<body>
<div class="title" id="title">
    <p></p>
</div>
<div class="testContent" id="test">
</div>

<div class="submit back">
    返回
</div>
</body>
<script id="tpl_test" type="text/template">
    {@each data.list as item,index}
    <div class="testItem items">
        <div class="testTitle">
            <span class="text">\${index|formatIndex}.\${item.name}:\${item.introduce}（0-\${item.score}分）</span>
        </div>
        <div class="testScore">
            <div class="core">
                <div class="score">
                    <p data-total="\${item.score}" class="number">\${item.score|parseIntF}</p>
                </div>
            </div>
        </div>
    </div>
    {@/each}
    <div class="testItem">
        <div class="testTitle">
            <span class="text">最后:您对提高培训质量和满意度有何意见</span>
        </div>
        <div class="input_text">
            <div id="textarea" class="message">\${data.rst.content}</div>
        </div>
    </div>
</script>


<script id="tpl_title" type="text/template">
    <p>\${data.name}</p>
</script>
<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="js/detail.js" type="text/javascript" charset="utf-8"></script>
</html>

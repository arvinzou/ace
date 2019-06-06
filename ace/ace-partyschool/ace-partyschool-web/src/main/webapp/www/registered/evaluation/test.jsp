<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/test.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/flexible.js"></script>
    <jsp:include page="../../common/common.jsp"/>
</head>
<body>
<div class="title" id="title">

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
            <textarea id="textarea" maxlength="200" class="message"
                      placeholder="请在此输入您要填写的内容~200字以内"></textarea>
            <span class="font_number">200</span>
        </div>
    </div>
</script>


<script id="tpl_title" type="text/template">
    <p>\${data.name}</p>
</script>
<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
<script src="js/test.js" type="text/javascript" charset="utf-8"></script>
</html>

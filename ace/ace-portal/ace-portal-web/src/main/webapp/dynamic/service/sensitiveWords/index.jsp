<%--
  Created by IntelliJ IDEA.
  User: HuaCai003
  Date: 2018/3/1
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="${portalPath}/content/common/js/jquery-3.2.1.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/bootstrap/js/bootstrap.min.js?version=${cfg.version}"></script>
<link href="${portalPath}/content/common/bootstrap/css/bootstrap.min.css" rel="stylesheet" id="style_components"
      type="text/css"/>
<style>
    .sensitive-words {
        margin: 20px 0px;
        padding: 10px 15px;
        height: 500px;
        overflow-y: auto;
        border: 1px dashed #666;
        border-radius: 8px;
    }

    .sensitive-words ul {
        font-size: 20px;
        list-style: none;
        padding: 0px;
        margin: 0px;
    }

    .sensitive-words ul li {
        color: #666;
        margin: 10px 10px;
        padding: 0px 20px;
        line-height: 40px;
        border-radius: 20px;
        float: left;
        cursor: pointer;
        border: 1px #666 solid;
    }

    .sensitive-words ul li:hover {
        color: #fff;
        border: 1px #fff solid;
        background-color: #0D0D0D;
    }

    .sensitive-word {
        width: 400px;
        display: inline;
    }

    .prompt{
        color:red;
        font-size: 14px;
    }
</style>
<head>
    <title>敏感词管理</title>
</head>
<body>
<div class="container">
    <div class="container sensitive-words">
        <ul>
        </ul>
    </div>
    <div class="container">
        <input type="text" class="sensitive-word form-control">
        <button class="btn btn-primary">确定</button>
        <span class="prompt">
        </span>
    </div>
</div>
</body>
<script>
    var contextPath = '${pageContext.request.contextPath}';
    var userProp;
    $(function () {
        init();
        $('.container').on('click', '.btn-primary', addWordDo)
        $('.container').on('click', 'li', removeWordDo)
        $('.container').on('focus', '.sensitive-word', removeInputDo)
    });

    function removeInputDo() {
        $('.prompt').text('');
    }

    function removeWordDo() {
        var $this=$(this);
        var id = $this.data('id');
        if (id) {
            var url = contextPath + "/sensitiveWords/deleteSensitiveWords.do";
            var data = {
                'id': id
            }
            $.getJSON(url, data, function (result) {
                if (0 == result.status) {
                    $this.remove();
                }
            });
        }
        return false;
    }

    function init() {
        var userUrl = contextPath + '/system/getUserProp.do';
        $.get(userUrl, function () {
            loadwords();
        });
    }

    function loadwords() {
        var url = contextPath + "/sensitiveWords/findSensitiveWordsList.do";
        var data = {
            'deptId': userProp.corpId
        }
        $.getJSON(url, data, function (result) {
            if (0 == result.status) {
                viewWords(result.rows);
            }
        });
    }

    function viewWords(data) {
        for (var i = 0; i < data.length; i++) {
            addWordAction(data[i].word, data[i].id);
        }
    }

    function addWordDo() {
        var word = $('.container .sensitive-word').val();
        if(word.length>=20){
            $('.prompt').text('字数不能超过20');
            return;
        }
        if (word == null || word.trim() == "") {
            return;
        }
        var url = contextPath + "/sensitiveWords/insertSensitiveWords.do";
        var data = {
            'word': word
        }
        $.post(url, data, function (result) {
            if (0 == result.status) {
                addWordAction(result.value.word, result.value.id)
                $('.container .sensitive-word').val('');
            }
            else{
                $('.prompt').text(result.errorMessage);
            }
        });
        return false;
    }

    function addWordAction(word, id) {
        var liWord = sensitiveWordTemplate.replace('[sensitiveWord]', word);
        $('.sensitive-words ul').append($(liWord).data('id', id));
    }
    var sensitiveWordTemplate = "<li title='移除'>[sensitiveWord]</li>";
</script>
</html>

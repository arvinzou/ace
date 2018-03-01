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
    .sensitive-words ul{
        font-size: 20px;
        list-style: none;
        padding: 0px;
        margin: 0px;
    }

    .sensitive-words ul li{
        margin: 10px 10px;
        padding: 0px 20px;
        line-height: 40px;
        border-radius: 20px;
        float:left;
        background-color:forestgreen;
    }

    .sensitive-words{
        margin: 20px 0px;
        padding:10px 15px;
        height: 500px;
        overflow-y: auto;
        border: 1px dashed #666;
        border-radius: 8px;
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
        <div class="col-lg-6 col-md-6 col-xs-10 col-sm-10">
            <input type="text" class="sensitive-word form-control">
        </div>
        <div class="col-lg-1 col-md-1 col-xs-2 col-sm-2">
            <button class="btn btn-primary">确定</button>
        </div>
    </div>
</div>
</body>
<script>
    $(function(){
        init();
        $('.container').on('click','.btn-primary',addWordDo)
    });
    
    function init() {
        var url=contextPath + "/sensitiveWords/findSensitiveWordsList.do";
        var data={
            'deptId':"00010001"
        }
        $.getJSON(url,data,function (result) {
            console.log(result);
        });
    }

    function addWordDo(){
        var word=$('.container .sensitive-word').val();
        if(word==null&&word.trim()==""){
           return;
        }
        var url=contextPath + "/sensitiveWords/deleteSensitiveWords.do";
        var data={
            'word':word
        }
        $.post(url,data,function (result) {
            console.log(result);
            addWordAction(word)
            $('.container .sensitive-word').val('');

        });
        return false;
    }
    
    function  addWordAction(word) {
        var liWord=sensitiveWordTemplate.replace('[sensitiveWord]',word);
        $('.sensitive-words ul').append($(liWord));
    }
    var sensitiveWordTemplate ="<li>[sensitiveWord]</li>";
</script>
</html>

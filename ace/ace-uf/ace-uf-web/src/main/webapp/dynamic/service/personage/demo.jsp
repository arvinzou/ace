<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>统战人士</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
<script type="text/javascript">


</script>
<body>

<div class="page-content">

<select id="languages" class="multiselect" value="[a,b,c]" style="width: 800px;	height: 150px;" multiple="multiple" name="languages">
</select>
    <a href="javascript:initData()">initData</a>
<a href="javascript:getValue()">getValue</a>
    <a href="javascript:setValue()">setValue</a>
    <div id="console"></div>
</div>

<jsp:include page="../../common/footer-1.jsp"/>


<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>


<jsp:include page="../../common/footer-2.jsp"/>
<script>

        $(function(){
			$("#languages").multiselect();
		});
		function initData(){
		    var dict=parent.staticDictObject;
		    var data=dict["98"];
		    var data2=[];
		    $(data).each(function(i,o){
		        if(o.CODE!=""){
		            data2.push({CODE:o.CODE,NAME:"["+o.CODE+"]"+o.NAME});
		        }
		    });
		    $("#languages").multiselect("addOptions",data2);
		}
		function getValue(){
            var o=$("#languages").multiselect("selectedValues");
		    console.log(o);
		    $("#console").append(JSON.stringify(o)+"<br>");
		}
		function setValue(){
		    var data=[{"CODE":"h","NAME":"H"},{"CODE":"j","NAME":"J"},{"CODE":"k","NAME":"K"}];
		     $("#languages").multiselect("selectList",data);
		    $(data).each(function(i,o){
               // $("#languages").multiselect("select",o.NAME);
		    });
		}
    </script>

</body>
</html>
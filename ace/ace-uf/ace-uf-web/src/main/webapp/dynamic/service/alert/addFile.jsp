<%--
  Created by IntelliJ IDEA.
  User: AFOC
  Date: 2017/10/30
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="modal fade in" id="modalBasic_2" tabindex="-1" role="dialog"
     aria-labelledby="modalBasicLabel" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                <h4 class="modal-title" id="modalBasicLabel_2">添加文件<span id="tishi" style="font-size:15px;"></span></h4>
                <div id="add-file" class=" window-show">
                    <div>文件主题<input name="fileTitle" type="text" ></div>
                    <div>文件分类<select class="fileType">
                        <option value="-1">请选择</option>
                        <option value="0">type1</option>
                        <option value="1">type2</option>
                        <option value="2">type3</option>
                    </select></div>
                    <div>文件备注<input name="fileRemarks" type="text" ></div>
                    <div>选择文件<button type="button"></button></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

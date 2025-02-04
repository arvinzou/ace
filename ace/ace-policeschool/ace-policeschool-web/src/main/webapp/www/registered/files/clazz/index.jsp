<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>班级文件</title>
    <jsp:include page="../../../common/common.jsp"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/www/common/css/mobileSelect.css"/>
</head>
<body>
<div class="index">
    <div class="header">
        <div class="search">
            <input class="serach-input" type="text" id="search" name="keyWord" onfocus="focusInput();"
                   onblur="blurInput();" oninput="activeSearch();"/>
            <img id="search-icon" src="img/icon_search.png" class="icon-search" onclick="searchIconClick();"/>
            <span id="search-title" class="search-title" onclick="searchIconClick();">搜索</span>
        </div>
        <div class="classFilter" id="classFilter">
            <img class="icon-class" src="img/icon-class.png">
            <div class="class-select" type="text" id="clazz"></div>
            <img class="class-right" src="img/icon_select.png"/>
        </div>
    </div>

    <div class="list" id="fileList">

    </div>

    <img src="img/icon_upload.png" class="upload" onclick="startUpload();"/>
</div>

<!--老师上传文件模态框层-->
<div class="modal" id="uploadModal" style="display: none;">
    <div class="modal-index">
        <div class="modal-title02 title02 mbr">上传班级文件</div>
        <div class="opt mbr" id="fileBox">
            <img src="img/icon_confirm_add.png" class="option-add" id="upload"/>
        </div>
        <div class="opt">
            <img src="img/icon_confirm_upload.png" class="option-upload" onclick="addFiles();"/>
        </div>
        <img src="img/del-red.png" class="close" onclick="closeModal();"/>
    </div>
</div>

<script id="list-tpl" type="text/template">
    {@each data as item,index}
    <div class="item">
        <div class="file-type">
            {@if fileType(item.url)=='excel'}
            <img src="img/icon-excel.png" class="file-type-img"/>
            {@else if fileType(item.url)=='word'}
            <img src="img/icon_word.png" class="file-type-img"/>
            {@else if fileType(item.url)=='ppt'}
            <img src="img/icon_ppt.png" class="file-type-img"/>
            {@else if fileType(item.url)=='img'}
            <img src="img/icon-jpg.png" class="file-type-img"/>
            {@else if fileType(item.url)=='pdf'}
            <img src="img/icon-pdf.png" class="file-type-img"/>
            {@else if fileType(item.url)=='text'}
            <img src="img/icon-text.png" class="file-type-img"/>
            {@/if}
        </div>
        <div class="file-detail">
            <div class="file-title">
                <div class="inner-middle">
                    {@if fileType(item.url)=='img'}
                    <a href="\${item.url}" class="download">\${item.title}</a>
                    {@else}
                    <a href="\${item.url}" class="download">\${item.title}</a>
                    <%--<a class="download" href="/policeschool/www/download/file?id=\${item.id}">\${item.title}</a>--%>

                    <%--<a class="download" onclick="downloadFile('\${item.url}');">\${item.title}</a>--%>
                    {@/if}
                </div>
            </div>
            <div class="file-footer">
                <span>\${item.pushDate}</span>
                <span>来自</span>
                <span>\${item.publisherName}</span>
            </div>
        </div>
    </div>
    {@/each}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/mobileSelect.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</body>
</html>

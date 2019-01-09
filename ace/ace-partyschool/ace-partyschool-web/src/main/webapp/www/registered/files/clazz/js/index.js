$(function(){
    fileList();
    $('#search').change(activeSearch);
});

function focusInput(){
	$("#search-icon").hide();
	$("#search-title").hide();
}

function blurInput(){
	var searchText = $("#search").val();
	if(searchText == "" || searchText == undefined || searchText == null){
		$("#search-icon").show();
		$("#search-title").show();
	}
}

function searchIconClick(){
	$("#search-icon").hide();
	$("#search-title").hide();
	$("#search").focus();
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function fileTypes(fileName){
    var fileType = null;
    var fileIndex = fileName.lastIndexOf(".");
    var fileLen = fileName.length;
    var type = fileName.substring(fileIndex+1, fileLen);
    if(type == "xls" || type == "xlsx"){
        fileType = "excel";
    }else if(type == "doc" || type == "docx"){
        fileType = "word";
    }else if(type == "ppt" || type == "pptx"){
        fileType = "ppt";
    }else if(type == "png" || type == "jpg" || type == "psd"){
        fileType = "img";
    }else if(type == "pdf"){
        fileType == "pdf";
    }else if(type == "txt"){
        fileType == "text";
    }
    return fileType;
}

function fileList(){
    var keyWord = $("input[name='keyWord']").val();
    $.ajax({
        url: contextPath+ "/www/files/findFilesList",
        type:"post",
        async:false,
        data:{
            title: keyWord
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                juicer.register('fileType', fileTypes);
                renderPage('fileList', result.data, 'list-tpl');
                renderPage('fileParamList', result.data, 'list-tpl');
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function activeSearch() {
    fileList();
}

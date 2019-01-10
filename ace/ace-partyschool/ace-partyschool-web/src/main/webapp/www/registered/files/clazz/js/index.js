var fileUrl = null;
var title = "";
var clazz = null;
$(function(){
    fileList();
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

function initClasses(){
    $.ajax({
        url: contextPath+ "/www/files/getMyClasses",
        type:"post",
        async:false,
        data:{

        },
        success:function(result){
            if(result.status == 0) {
               console.log(result);
               var data = result.data;
               var classesArr = [];
               for(var i=0; i<data.length; i++){
                    var o = {};
                    o.id = data[i].id;
                    o.value = data[i].name;
                   classesArr.push(o);
               }
                var classesSelect= new MobileSelect({
                    trigger: '#clazz',
                    title: '党校班级选择',
                    wheels: [
                        {data: classesArr}
                    ],
                    position:[1], //初始化定位 打开时默认选中的哪个 如果不填默认为0
                    transitionEnd:function(indexArr, data){
                        clazz = data;
                    },
                    callback:function(indexArr, data){
                        clazz = data;
                    }
                });
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

function startUpload(){
    $("#uploadModal").show();
    upload();
    initClasses();
}
function upload(){
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'upload',
        url: '/portal/www/upload.do',
        file_data_name: 'file',
        multi_selection: false,
        resize: {
            width: 1024,
            height: 1024,
            crop: true,
            quality: 60,
            preserve_headers: false
        },
        filters: {
            max_file_size: '102400mb',
            mime_types: [

            ]
        }
    });
    uploader.init();
    uploader.bind("FileFiltered",function(uploader,file){
        var index = file.name.lastIndexOf(".");
        title = file.name.substring(0,index);
        console.log("name="+title);
        uploader.start();
        return false;
    });
    uploader.bind("FileUploaded",function (uploader,file,responseObject) {
        var rst = JSON.parse(responseObject.response);
        fileUrl = rst.file_path;
    });
}

function addFiles(){
    if(clazz == null || clazz == undefined){
        alert("请选择班级！");
        return;
    }
    if(fileUrl == null || fileUrl == "" || fileUrl == undefined){
        alert("请选择上传的文件！");
        return;
    }
    $.ajax({
        url: contextPath+ "/www/files/insertFiles",
        type:"post",
        async:false,
        data:{
            classesId: clazz[0].id,
            url: fileUrl
        },
        success:function(result){
            if(result.status == 0) {
                alert(result.info);
                $("#uploadModal").hide();
                fileList();
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
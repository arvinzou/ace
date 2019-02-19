var fileUrl = null;
var title = "";
var clazz = null;
var regType = null;
$(function(){
    $.ajax({
        url: contextPath+ "/www/sign/getAcctInfo",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
        },
        success:function(result){
            if(result.status == 0) {
                regType = result.data.regType;
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
    if(regType == "student"){
        fileList();
    }else{
        fileListByClass();
    }
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
    if(type.toLowerCase() == "xls" || type.toLowerCase() == "xlsx"){
        fileType = "excel";
    }else if(type.toLowerCase() == "doc" || type.toLowerCase() == "docx"){
        fileType = "word";
    }else if(type.toLowerCase() == "ppt" || type.toLowerCase() == "pptx"){
        fileType = "ppt";
    }else if(type.toLowerCase() == "png" || type.toLowerCase() == "jpg" || type.toLowerCase() == "jpeg" || type.toLowerCase() == "bmp" || type.toLowerCase() == "gif"){
        fileType = "img";
    }else if(type.toLowerCase() == "pdf"){
        fileType = "pdf";
    }else if(type.toLowerCase() == "txt"){
        fileType = "text";
    }
    return fileType;
}

function fileListByClass(){
    var keyWord = $("input[name='keyWord']").val();
    $.ajax({
        url: contextPath+ "/www/files/findFilesListVo",
        type:"post",
        async:false,
        data:{
            title: keyWord,
            start: 0,
            limit: 999
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                var list = result.rows;
                for(var i=0; i<list.length; i++){
                    if(list[i].title.length > 33){
                        list[i].title = list[i].title.substring(0,35)+"...";
                    }
                }
                juicer.register('fileType', fileTypes);
                renderPage('fileList', list, 'list-tpl');
                renderPage('fileParamList', result.rows, 'list-tpl');
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

function fileList(){
    var keyWord = $("input[name='keyWord']").val();
    $.ajax({
        url: contextPath+ "/www/files/findFilesList",
        type:"post",
        async:false,
        data:{
            title: keyWord,
            start: 0,
            limit: 999
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                var list = result.data;
                for(var i=0; i<list.length; i++){
                    if(list[i].title.length > 33){
                        list[i].title = list[i].title.substring(0,35)+"...";
                    }
                }
                juicer.register('fileType', fileTypes);
                renderPage('fileList', list, 'list-tpl');
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
    if(regType == "student"){
        fileList();
    }else{
        fileListByClass();
    }
}

function initClasses(){
    $.ajax({
        url: contextPath+ "/www/classes/findClassList",
        type:"post",
        async:false,
        data:{
            status: "1",
        },
        success:function(result){
            if(result.status == 0) {
              if(result.rows.length <1 && regType == 'teacher'){
                  alert("对不起！您没有班级文件上传权限。");
                  $("#uploadModal").hide();
                  return;
              }else{
                  if(regType == 'student'){
                      $("#classBox").hide();
                      return;
                  }
                  var data = result.rows;
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
              }
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

function closeModal(){
    $("#fileBox").html('<img src="img/icon_confirm_add.png" class="option-add" id="upload"/>');
    fileUrl = null;
    $("#uploadModal").hide();
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
        max_file_size: '100mb',
        //一次上传数据大小
        chunk_size: '100mb',
        resize: {
            width: 1024,
            height: 1024,
            crop: true,
            quality: 60,
            preserve_headers: false
        },
        filters: [

        ]
    });
    uploader.init();
    uploader.bind("FileFiltered",function(uploader,file){
        title = file.name;
        uploader.start();
        return false;
    });
    uploader.bind("FileUploaded",function (uploader,file,responseObject) {
        var rst = JSON.parse(responseObject.response);
        fileUrl = rst.file_path;
        $("#fileBox").html("<span>"+title+"</span>");

        uploader.removeFile(file);
    });

}

function addFiles(){
    if((clazz == null || clazz == undefined) && regType == 'teacher'){
        alert("请选择班级！");
        return;
    }
    if(fileUrl == null || fileUrl == "" || fileUrl == undefined){
        alert("请选择上传的文件！");
        return;
    }
    var classesId = clazz == null?null: clazz[0].id;
    $.ajax({
        url: contextPath+ "/www/files/insertFiles",
        type:"post",
        async:false,
        data:{
            classesId: classesId,
            url: fileUrl
        },
        success:function(result){
            if(result.status == 0) {
                alert(result.info);
                $("#uploadModal").hide();
                $("#fileBox").html('<img src="img/icon_confirm_add.png" class="option-add" id="upload"/>');
                fileUrl = null;
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

/**
 * 下载查看班级文件
 * @param id
 */
function downloadFile(id){
    window.location.href = contextPath+ "/www/download/file?id="+id;
}
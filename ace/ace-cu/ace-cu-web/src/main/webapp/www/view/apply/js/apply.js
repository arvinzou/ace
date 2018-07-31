var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var hostPath = "http://127.0.0.1";
var app =angular.module(ngAppName, []);

var coverUrl01 = null;
var coverUrl02 = null;
var coverUrl03 = null;
var coverUrl04 = null;
var coverUrl05 = null;
var coverUrl06 = null;
var id = "";
app.controller(ngControllerName,function($scope){
	var uploader1 = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: 'positive',
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
                max_file_size: '2048mb',
                mime_types: [
                   /* {title: "Image files", extensions: "jpg,gif,png,jpeg"}*/
                ]
            },
            init: {
                FileFiltered: function (up, files) {
                    showUploadText('.picture_01 img', '.text_01');
                    up.start();
                    return false;
                },
                UploadProgress: function(e, t) {
                    var r = t.percent;
                    $(".progress_01").html("开始上传（" + r + "%）")
                },
                FileUploaded: function (uploader, file, responseObject) {
                	console.log(responseObject);
                    var rst = JSON.parse(responseObject.response);
                    viewCover(rst.file_path, '.container_01','.picture_01 img', '.text_01');
                    coverUrl01 = rst.file_path;
                }
            }
        });
        uploader1.init();
        
        var uploader2 = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: 'unpositive',
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
                max_file_size: '2048mb',
                mime_types: [
                   /* {title: "Image files", extensions: "jpg,gif,png,jpeg"}*/
                ]
            },
            init: {
                FileFiltered: function (up, files) {
                    showUploadText('.picture_02 img', '.text_02');
                    up.start();
                    return false;
                },
                UploadProgress: function(e, t) {
                    var r = t.percent;
                    $(".progress_02").html("开始上传（" + r + "%）")
                },
                FileUploaded: function (uploader, file, responseObject) {
                    var rst = JSON.parse(responseObject.response);
                    viewCover(rst.file_path, '.container_02','.picture_02 img', '.text_02');
                    coverUrl02 = rst.file_path;
                }
            }
        });
        uploader2.init();
        
         var uploader3 = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: 'prove1',
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
                max_file_size: '2048mb',
                mime_types: [
                    {title: "Image files", extensions: "jpg,gif,png,jpeg"}
                ]
            },
            init: {
                FileFiltered: function (up, files) {
                    showUploadText('.picture_03 img', '.text_03');
                    up.start();
                    return false;
                },
                UploadProgress: function(e, t) {
                    var r = t.percent;
                    $(".progress_03").html("开始上传（" + r + "%）")
                },
                FileUploaded: function (uploader, file, responseObject) {
                    var rst = JSON.parse(responseObject.response);
                    viewCover(rst.file_path, '.container_03','.picture_03 img', '.text_03');
                    coverUrl03 = rst.file_path;
                    uploader4.init();
                    $("#datum02").show();
                }
            }
        });
        uploader3.init();
        
        var uploader4 = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: 'prove2',
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
                max_file_size: '2048mb',
                mime_types: [
                   /* {title: "Image files", extensions: "jpg,gif,png,jpeg"}*/
                ]
            },
            init: {
                FileFiltered: function (up, files) {
                    showUploadText('.picture_04 img', '.text_04');
                    up.start();
                    return false;
                },
                UploadProgress: function(e, t) {
                    var r = t.percent;
                    $(".progress_04").html("开始上传（" + r + "%）")
                },
                FileUploaded: function (uploader, file, responseObject) {
                    var rst = JSON.parse(responseObject.response);
                    viewCover(rst.file_path, '.container_04','.picture_04 img', '.text_04');
                    coverUrl04 = rst.file_path;
                    uploader6.init();
                    $("#datum03").show();
                }
            }
        });

    var uploader5 = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'handIdentity',
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
            max_file_size: '2048mb',
            mime_types: [
                /* {title: "Image files", extensions: "jpg,gif,png,jpeg"}*/
            ]
        },
        init: {
            FileFiltered: function (up, files) {
                showUploadText('.picture_05 img', '.text_05');
                up.start();
                return false;
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $(".progress_05").html("开始上传（" + r + "%）")
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                viewCover(rst.file_path, '.container_05','.picture_05 img', '.text_05');
                coverUrl05 = rst.file_path;
            }
        }
    });
    uploader5.init();

    var uploader6 = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'prove3',
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
            max_file_size: '2048mb',
            mime_types: [
                /* {title: "Image files", extensions: "jpg,gif,png,jpeg"}*/
            ]
        },
        init: {
            FileFiltered: function (up, files) {
                showUploadText('.picture_06 img', '.text_06');
                up.start();
                return false;
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $(".progress_06").html("开始上传（" + r + "%）")
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                viewCover(rst.file_path, '.container_06','.picture_06 img', '.text_06');
                coverUrl06 = rst.file_path;
            }
        }
    });

        /**
         * 发起申请
         */
	$scope.applyProject = function(){
        var realName = $("input[name='realName']").val();
		var idCard = $("input[name='idCard']").val();
		var phoneNum = $("input[name='phoneNum']").val();
		var flagAmount = $("input[name='flagAmount']").val();
		var raiseTitle = $("input[name='raiseTitle']").val();
		var raiseDes = $("textarea[name='raiseDes']").val();
		
		if(realName == '' || realName == undefined){
			alert("真实姓名不能为空！");
			return;
		}
		if(idCard == '' || idCard == undefined){
			alert("身份证号不能为空！");
			return;
		}
		if(phoneNum == '' || phoneNum == undefined){
			alert("联系方式不能为空！");
			return;
		}
		if((coverUrl01==null || coverUrl02 == null) ||(coverUrl01==undefined || coverUrl02 == undefined)){
			alert("身份证照未上传！");
			return;
		}
		if(coverUrl05 == null || coverUrl05 == undefined){
            alert("手持身份证照未上传！");
            return;
        }
		if(flagAmount == '' || flagAmount == undefined){
			alert("目标金额不能为空！");
			return
		}
		if(raiseTitle == '' || raiseTitle == undefined){
			alert("筹款标题不能为空！");
			return;
		}
		if(raiseDes == '' || raiseDes == undefined){
			alert("筹款说明不能为空！");
			return;
		}
		if((coverUrl03 ==null && coverUrl04 == null) || (coverUrl03==undefined && coverUrl04==undefined)){
			alert("相关证明不能为空！");
			return;
		}
		var list = [
			{
				"resName":"身份证正面照",
				"resUrl":coverUrl01,
				"type":"0"
			},
			{
				"resName":"身份证反面照",
				"resUrl":coverUrl02,
				"type":"1"
			},
            {
                "resName":"手持身份证照",
                "resUrl":coverUrl05,
                "type":"2"
            }
		];
		if(coverUrl03 != null && coverUrl03 != undefined){
			var temp = {
				"resName":"证明文件",
				"resUrl":coverUrl03,
				"type":"3"
			}
			list.push(temp);
		}
		if(coverUrl04 != null && coverUrl04 != undefined){
			var temp = {
				"resName":"证明文件",
				"resUrl":coverUrl04,
				"type":"3"
			}
			list.push(temp);
		}
		if(coverUrl06 !=null && coverUrl06!= undefined){
            var temp = {
                "resName":"证明文件",
                "resUrl":coverUrl06,
                "type":"2"
            }
            list.push(temp);
        }
		
		$.ajax({
        url: "/cu/www/project/applyProject",
        type:"post",
        async:false,
        data:{
        	json: JSON.stringify({
                "realName": realName,
                "idCard": idCard,
                "mobileNumber": phoneNum,
                "targetAmount": flagAmount,
                "description": raiseDes,
                "title": raiseTitle,
                "resList":list
            })
        },
        success:function(result){
            if(result.status == 0) {
                id = result.data.id;
                $scope.projectId = id;
                layer.open({
                    type:1,
                    content: $("#success_box").html(),
                    shadeClose:false
                });
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
    
	}
});

function showProjectProgress(){
    window.location.href = '/cu/www/view/me/apply_progress.html?projectId='+id;
}
/*图片上传成功后*/
function viewCover(img, clazz, imgClazz, textClazz) {
    $(clazz).data('imgSrc',img);
    var imagePath=img;
    showUploadImg(imagePath, imgClazz, textClazz);
}

/*显示上传文字*/
function showUploadText(imgClazz, textClazz) {
    $(imgClazz).prop('src','');
    $(textClazz).show();
}

/*显示上传图片*/
function showUploadImg(imgpath, imgClazz, textClazz) {
    $(imgClazz).removeClass('undis').addClass('dis');
    $(imgClazz).prop('src',imgpath);
    $(textClazz).hide();
}
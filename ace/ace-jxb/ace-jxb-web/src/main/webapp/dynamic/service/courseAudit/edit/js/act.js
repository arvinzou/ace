var loading = {};
var payType = "0";

function App() {
    console.log("=============================App Start==============================");

}

function initEditor() {
    var editor = new Simditor({
        textarea: $('textarea[name=introduce]'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol',
            'ul', 'blockquote', 'code', 'table', '|', 'link', 'image','video', 'hr', '|', 'indent', 'outdent'
        ],
        upload: {
            url: portalPath + '/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
}
/*图片上传成功后*/
function viewCover(img, clazz, imgClazz, textClazz) {
    $(clazz).data('imgSrc', img);
    var imagePath = img;
    showUploadImg(imagePath, imgClazz, textClazz);
}

/*显示上传文字*/
function showUploadText(imgClazz, textClazz) {
    $(imgClazz).prop('src', '');
    $(textClazz).show();
}

/*显示上传图片*/
function showUploadImg(imgpath, imgClazz, textClazz) {
    $(imgClazz).removeClass('hidder').addClass('displayer');
    $(imgClazz).prop('src', imgpath);
    $(textClazz).hide();
}

function payTypeCheck(dom) {
    if (dom == 'noPay') {
        payType = '0';
        $('.price-panel').hide();
    } else {
        payType = '1';
        $('.price-panel').removeClass('hide');
        $('.price-panel').show();
    }
}

function save(params) {
    $.extend(params, {
        type: urlParams.type,
        id:urlParams.did,
        category: '1',
        mediType: '1',
        cover: $("#courseCover").attr('src'),
        demandNum: 0,
        likeNum: 0
    });
    startLoad();
    $.ajax({
        url: contextPath + "/course/updateCourse",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
            alert(result.errorMessage);
            if (result.status == 0) {
                window.location.href = contextPath + '/dynamic/service/courseAudit/index.jsp?id='+urlParams.id;
            }
        },
        error: function () {
            alert("系统服务内部异常！");
        }
    });
}




function renderPage(dom, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(dom).html(html);
}

function initPage() {
    $('#fm-add').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date()
            });
            console.log(params);
            save(params);
            return false;
        }
    });


    initForm();
    initUpload();


}
function initForm(){
 $.ajax({
        url: contextPath + "/course/selectCourseByPrimaryKey",
        type:"post",
        async:false,
        data:{
            id: urlParams.did
        },
        success:function(result){
            if(result.status == 0) {
               var data={};
               data['o']=result.value;
               data['dict149']=staticDictObject['149'];
               data['dict150']=staticDictObject['150'];
               renderPage($("#fm-add"),data, 'tpl-fm');
               initEditor();
               $('input[name=name]').maxlength({
                       alwaysShow: true
                });
               $('input[name=applicationObject]').maxlength({
                    alwaysShow: true
               });

                $("#fm-add").validate({
                        onfocusout: function(element) { $(element).valid(); },
               			rules: {
               				name: {
               					required: true,
               					minlength: 4,
               					maxlength:28
               				},
               				introduce: {required: true}
               			},
               			messages: {
               				introduce:{
               				    required: "请输入课程简介"
               				},
               				name: {
               					required: "请输入课程名称",
               					minlength:"课程名称至少四个字符",
               					maxlength:"课程名称长度不能超过28"
               				}
               			}
               		});

            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}
window.onload=function(){
    jQuery(function ($) {
        initPage();
        $(".breadcrumb").append("<li><span>课程编辑</span></li>");
    });
}

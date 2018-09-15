var loading = {};
var editor;
var cityCode,citySelect;
window.onload = function (){
    jQuery(function ($) {
       initForm();
    });
}
function App() {
    console.log("=============================App Start==============================");
}
function initEditor() {
        editor = new Simditor({
        textarea: $('textarea[name=profile]'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol',
            'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'
        ],
        upload: {
            url: portalPath + '/files/uploadImage.do',
            params: null,
            fileKey: 'file',
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
}
/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

function initPage() {
    initEditor();
    initUpload();
    initCitySelect();
    initEvents();
}
function initEvents(){
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function(element) { $(element).valid(); },
        rules: {
           name: {required: true,maxlength:4},
           cityCode: {required: true},
           profile: {required: true,minlength:100,maxlength:20000},
           idCard: {required: true,minlength:18,maxlength:18},
           certificateNo: {required: true,minlength:10,maxlength:18},
           duration: {required: true,digits:true },
           peopleNum: {required: true,digits:true }

        },
        messages: {
            name: {
                required: "请输入咨询师姓名",
                maxlength:"咨询师姓名长度不能超过4"
            },
            cityCode: {
                            required: "请选择所在城市"
                        },
            profile: {
                required: "请输入个人简介",
                maxlength:"个人简介长度不能超过20000",
                minlength:"个人简介长度至少100"
            },
             idCard: {
                 required: "请输入身份证号",
                 maxlength:"身份证号长度不能超过18位",
                 minlength:"身份证号长度至少18位"
             },
           certificateNo: {
               required: "请输入从业资格证证号",
               maxlength:"从业资格证证号长度不能超过18位",
               minlength:"从业资格证证号长度至少10位"
           },
           duration: {
               required: "请输入个案时长",
               digits:"个案时长必须是整数"
           }
           ,
                      peopleNum: {
                          required: "请输入个案人数",
                          digits:"个案人数必须是整数"
                      }
        }
        });
     /*监听表单提交*/
    $('#fm-edit').ajaxForm({
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
}
/*保存表单**/
function save(params) {
    $.extend(params, {

    });
    if (!$(".protocol[type='checkbox']").prop('checked')) {
            alert("需要同意近心帮协议");
            return ;
    }
    if (!cityCode) {
                alert("请选择所属城市");
                return ;
        }
    startLoad();
    $.ajax({
        url: contextPath + "/counselor/updateUserinfo",
        type: "post",
        async: false,
        data:params,
        success: function (result) {
            stopLoad();

			if(result.status==0){
			    if(params.regAuditRst==1){
			        alert("提交成功！");
			    }else{
			        alert("提交成功，平台官方会在2个工作日内反馈申请结果！");
			    }
			}else{
			    alert(result.errorMessage);
			}
        },
        error: function () {
            alert("对不起出错了！");
        }
    });
}

function initForm(){
    $.ajax({
        url: contextPath + "/counselor/getMyinfo",
        type:"post",
        async:false,
        data:{

        },
        success:function(result){
            if(result.status == 0) {
               result.value.regAuditRst='0';
               render($("#fm-edit"),result.value,'tpl-fm');
               initPage();
               citySelect.setCityVal(result.value.cityCode + "市");
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("对不起出错了！");
        }
    });
}
function initCitySelect(){
    citySelect = $('#cityCode').citySelect({
            dataJson: cityData,
            multiSelect: false,
            whole: false,
            shorthand: true,
            search: true,
            addInputClass: 'form-control',
            onInit: function () {
                console.log(this)
            },
            onTabsAfter: function (target) {
                console.log(target)
            },
            onCallerAfter: function (target, values) {
                console.log(JSON.stringify(values));
                cityCode = values.name;
            }
        });

}
var loading = {};
Array.prototype.contains = function ( needle ) {
  for (i in this) {
    if (this[i] == needle) return true;
  }
  return false;
}
window.onload = function (){
    jQuery(function ($) {
       initForm();
    });
}
function App() {
    console.log("=============================App Start==============================");
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
    initEvents();

}
function initEvents(){
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function(element) { $(element).valid(); },
        errorPlacement: function(error, element) {
            $(element).closest( "form" ).find( ".error-" + element.attr( "name" )).append( error );
        },
        rules: {
           objects: {required:true},
           field: {required:true},
           telephoneCon: {required:true,number:true},
           wecharCon: {required:true,number:true},
           facetofaceCon: {required:true,number:true},
           city: {required:true}

        },
        messages: {
            objects: {
                required: "请选择咨询对象"
            },
            field: {
                required: "请选择擅长领域"
            }

        }
        });
     /*监听表单提交*/
    $('#fm-edit').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            var objects=[];
            var field=[];
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
                if(obj.name=='objects'){
                   objects.push(obj.value);
                }
                if(obj.name=='field'){
                   field.push(obj.value);
                }
            });
            $.extend(params, {
                time: new Date()
            });
            $.extend(params, {
                objects:objects.join(',')
            });
            $.extend(params, {
                field: field.join(',')
            });
            console.log(params);
            save(params);
            return false;
        }
    });
}
/*保存表单**/
function save(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/consult/modifyConsult",
        type: "post",
        async: false,
        data:params,
        success: function (result) {
            stopLoad();

			alert(result.errorMessage);
        },
        error: function () {
            alert("对不起出错了！");
        }
    });
}

function initForm(){
    $.ajax({
        url: contextPath + "/consult/getMyConsultInfo",
        type:"post",
        async:false,
        data:{

        },
        success:function(result){
            if(result.status == 0) {
               var data={};
               data['o']=true;
               if(result.data){
                   data['o']=result.data;
               }
               data['dict149']=staticDictObject['149'];
               data['dict152']=staticDictObject['152'];
               render($("#fm-edit"),data,'tpl-fm');
               initPage();
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("对不起出错了！");
        }
    });
}
function getCheckBoxVal(chenked){
     var tem=new Array();
     for(var i=0;i<chenked.length;i++){
        tem.push(chenked[i].value);
     }
     return tem.join(',');
}
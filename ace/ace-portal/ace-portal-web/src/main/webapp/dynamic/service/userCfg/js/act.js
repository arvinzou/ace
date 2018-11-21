 function updateCfg(value){
         var params=[];
         params.push({cfgKey:"portalType",cfgValue:value,cfgName:"页面主题"});
        $.ajax({
            type : "post",
            url : contextPath+'/userCfg/saveOrUpdateUserCfg.do',
            data:{jsons:JSON.stringify(params)},
            beforeSend : function(XMLHttpRequest) {
                startLoad();
            },
            success : function(rst, textStatus) {
                stopLoad();
                alert(rst.errorMessage);
            },
            complete:function(xhr, ts){
                stopLoad();
            },
            error : function() {
                stopLoad();
            }
        });
  }

 function App(){


 }





function renderPage(dom, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(dom).html(html);
}

function initPage() {
   var data={};
  data['portalType']=portalType;
  renderPage($("#fm"),data, 'tpl-fm');
  $(".todo-header").html("个性化配置");
  $("#btn-submit")
  $('#btn-submit').on('click', function() {
      var portalType=$('input:radio[name="portalType"]:checked').val();
       updateCfg(portalType);
  });
}
window.onload=function(){
    jQuery(function ($) {
        initPage();
    });
}
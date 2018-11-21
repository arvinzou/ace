
function App(){
    console.log("=============================App Start==============================");
    loader({path:contextPath,url:'/content/service/tpl/css/style.css',type:'css'});
    getList();
}

function getList(){
    $.ajax({
            url : contextPath + '/tpl/getList.do',
            type : 'POST',
            timeout : 30000,
            dataType : 'json',
            beforeSend:function(){
                loading=startLoading();
                if(loading) {
                   loading.settext("正在加载，请稍后......");
                }
            },
            success : function(data) {
                console.log(data);
                var tpl = document.getElementById('tpl-box').innerHTML;
                var html = juicer(tpl,{data:data});
                $(".page-content-inner .listBox").append(html);
                if(loading){
                    loading.remove();
                }
             }
    });

}

function insertTplPageByTplId(tplId){
    if(confirm("确定选择此模板建立页面吗？")){
        $.ajax({
                    url : contextPath + '/tplPage/insertTplPageByTplId.do',
                    type : 'POST',
                    timeout : 30000,
                    dataType : 'json',
                    data:{tplId:tplId},
                    beforeSend:function(){
                        loading=startLoading();
                        if(loading) {
                           loading.settext("正在加载，请稍后......");
                        }
                    },
                    success : function(data) {
                        console.log(data);
                        if(loading){
                            loading.remove();
                        }
                        if(data.status==0){
                            location.href=contextPath+"/dynamic/service/pages/index.jsp?id=4502";
                        }else{
                            alert(data.errorMessage);
                        }
                     }
            });
    }

}
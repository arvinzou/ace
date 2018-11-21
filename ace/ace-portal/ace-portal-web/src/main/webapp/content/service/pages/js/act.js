
function App(){
    console.log("=============================App Start==============================");
    loader({path:contextPath,url:'/content/service/pages/css/style.css',type:'css'});
    loader({path:contextPath,url:'/content/common/assets/global/plugins/clipboardjs/clipboard.min.js',type:'js',
    callback: function () {
                getList();
            }
    });


}

function getList(){
    $.ajax({
            url : contextPath + '/tplPage/getList.do',
            type : 'POST',
            timeout : 30000,
            dataType : 'json',
            beforeSend:function(){
                try {
                                loading = startLoading();
                            } catch (e) {};
                            if (loading) {
                                loading.settext("请求中，请稍后......");
                            }
            },
            success : function(data) {
                console.log(data);
                var tpl = document.getElementById('tpl-box').innerHTML;
                var html = juicer(tpl,{data:data});
                $("section").html(html);
                if(loading){
                    loading.remove();
                }
                $(function () {
                  $('[data-toggle="tooltip"]').tooltip()
                })
                $(".sys").mouseover(function(){
                  $(this).find('img:last-child').attr("src","http://www.xmypage.com/backend/qrcode.php?data="+$(this).data('url'));
                  $(this).find('img:last-child').show();
                });
                $(".sys").mouseout(function(){
                   $(".sys img:last-child").hide();
                });
                var clipboard = new Clipboard('.list_menu .btn-url');

                clipboard.on('success', function(e) {
                    console.info('Action:', e.action);
                    console.info('Text:', e.text);
                    console.info('Trigger:', e.trigger);

                    //e.clearSelection();
                });

                clipboard.on('error', function(e) {
                    console.error('Action:', e.action);
                    console.error('Trigger:', e.trigger);
                });

             }
    });

}
function sort_overflow_hidden(th){
	if(th.contentWindow.document.getElementsByClassName('tab_sort')[0]){
	    th.contentWindow.document.getElementsByClassName('tab_sort')[0].style.overflow="hidden";
	}
}
function delete_page(id,name){
    if(confirm("确定要删除"+name+"吗？")){
$.ajax({
        url: contextPath + '/tplPage/deleteTplPageByTplPageId.do',
        type: 'POST',
        timeout: 30000,
        dataType: 'json',
        data: {
                        jsons: JSON.stringify({
                            id: id,
                            name:name
                        })
                    },
        beforeSend: function () {
            try {
                loading = startLoading();
            } catch (e) {};
            if (loading) {
                loading.settext("请求中，请稍后......");
            }
        },
        success: function (data) {
            console.log(data);
            if (loading) {
                loading.remove();
            }
            if (data.status == 0) {
                 getList();
            } else {
                alert(data.errorMessage);
            }
        }
    });
    }
}
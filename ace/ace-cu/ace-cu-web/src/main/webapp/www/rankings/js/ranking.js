window.onload = function(){

    var counter = 0;
    // 每页展示4个
    var num = 20;
    var page = 0;

    // dropload
    var datas = [];
    $('.rank_list').dropload({
        scrollArea : window,
        loadDownFn : function(me){
            $.ajax({
                type: 'post',
                url: '/cu/www/report/donateRank',
                data:{start:counter, limit: num, needOpenId: "2"},
                success: function(result){
                    if(result.data.length < 1){
                            me.lock();
                            // 无数据
                            me.noData();
                            me.resetload();
                        }else{
                            page++;
                            counter = page * num;
                            var temp = result.data;
                        for(var i=0; i<temp.length; i++){
                            datas.push(temp[i]);
                        }
                        renderPage('list', datas, 'list-tpl');
                        me.resetload();
                    }
                },
                error: function(xhr, type){
                    alert('出错了！');
                    me.resetload();
                }
            });
        }
    });
};

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}
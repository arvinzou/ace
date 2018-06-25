$(function(){
    $("#searchBtn").click(function(){
        var type = $('input:radio[name="radio"]:checked').val();
        var keyword = $("input[name='keyword']").val();

        if(type == '' || type== undefined){
            layer.alert("请选择搜索类型！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(keyword == '' || keyword == undefined){
            layer.alert("请输入搜索关键字！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        window.open('searchRet.html?type='+type+'&keyword='+keyword);
    });
});
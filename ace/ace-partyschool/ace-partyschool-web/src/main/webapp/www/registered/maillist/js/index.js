$(function(){
    initMailList();
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

function dorpAndDown(obj, iDom){
	var temp = $(obj).find("img").attr("name");
	if(temp == "up"){
		$(obj).html('<img src="img/icon_down.png" class="icon-down" name="down"/>');
		$(obj).parent().siblings().show();
	}else{
		$(obj).html('<img src="img/icon_up.png" class="icon-up" name="up"/>');
        $(obj).parent().siblings().hide();
	}
}

function initMailList(){
    $.ajax({
        url: contextPath+ "/mailList/www/getTreeList",
        type:"post",
        async:false,
        data:{
        },
        success:function(result){
        	renderPage("treeList", result, 'tree-tpl');
            renderPage("otherList", result, 'other-tpl');
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}
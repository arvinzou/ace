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
		$("#"+iDom).show();
	}else{
		$(obj).html('<img src="img/icon_up.png" class="icon-up" name="up"/>');
		$("#"+iDom).hide();
	}
	
}

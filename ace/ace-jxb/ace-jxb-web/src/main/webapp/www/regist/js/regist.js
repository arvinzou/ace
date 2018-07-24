
$(function(){
	$(document).on("swipeleft", ".ui-page", function () {
        var thePage = $(this),
                next = thePage.jqmData("next");
        if (next) {
            $(":mobile-pagecontainer").pagecontainer("change", "#" + next, {transition: "slide"});
        }
    });
    $(document).on("swiperight", ".ui-page", function () {
        var thePage = $(this),
                prev = thePage.jqmData("prev");
        if (prev) {
            $(":mobile-pagecontainer").pagecontainer("change", "#" + prev, {transition: "slide", reverse: true});
        }
    });
    
});

function selectType(id){
	if(id == 'parent'){
		$("#"+id).attr('src','img/parent.png');
		$("#teacher").attr('src','img/unteacher.png');
	}else{
		$("#"+id).attr('src','img/teacher.png');
		$("#parent").attr('src','img/unparent.png')
	}
	$("#nextStep").children().attr('src','img/next_active.png')
}
    
function phoneInput(){
	var phoneNum = $("input[name='phoneNum']").val();
	if(phoneNum != '' && phoneNum != undefined){
		$("#confirm").attr('src','img/confirm_active.png');
	}else{
		$("#confirm").attr('src','img/confirm_unactive.png');
	}
}

function codeInput(){
	var code = $("input[name='code']").val();
	if(code != '' && code != undefined){
		$("#login").attr('src','img/login_active.png');
	}else{
		$("#login").attr('src','img/login_unactive.png');
	}
}

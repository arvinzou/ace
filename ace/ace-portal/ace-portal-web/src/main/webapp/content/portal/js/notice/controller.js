jQuery(function($) {
$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
				});
				$.extend(params, {
					time : new Date()
				});
				// console.log(params);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1,
					postData : params
				}).trigger("reloadGrid");
				return false;
			}
		});
	});
    $(".btn-group .btn").bind('click', function (event) {
            $(event.target).siblings().removeClass("active");
            console.log(event);
            $(event.target).addClass("active");
    });
    $("#btn-view-add").bind('click', function (event) {
        location.href="add/index.jsp?id="+urlParams.id;
    });


});
function reloadGrid(){
	console.log('reloadGrid');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
	}).trigger("reloadGrid");
}

function edit(rowid){
   location.href="edit/index.jsp?id="+urlParams.id+"&did="+rowid;
}
var show=false;
function del(rowid){
    console.log(rowid);
	jQuery(cfg.grid_selector).jqGrid('delGridRow',
    rowid,
    {
        beforeShowForm : function(e) {
            var form = $(e[0]);
            if(!show){
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
            }

            show=true;

        }
    });
}
var params={};
function setParams(key, value) {
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam',{postData : params}).trigger("reloadGrid");
}

function optTop(noticeId){
	$.ajax({
		type : "get",
		url : contextPath + "/notice/updateForTopByPrimaryKey.do",
		data:{noticeId:noticeId},
		beforeSend : function(XMLHttpRequest) {
            startLoad();
		},
		success : function(rst, textStatus) {
			alert(rst.errorMessage);
		},
		complete : function(XMLHttpRequest, textStatus) {
            stopLoad();
		},
		error : function() {
		    stopLoad();
		}
	});
}
function status(noticeId){
	$.ajax({
		type : "get",
		url : contextPath + "/notice/updateForStatusByPrimaryKey.do",
		data:{noticeId:noticeId,status:'1',groupId:'2'},
		beforeSend : function(XMLHttpRequest) {
             startLoad();
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				reloadGrid();
			}else{
				alert(rst.errorMessage);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
            stopLoad();
		},
		error : function() {
		    stopLoad();
		}
	});
}
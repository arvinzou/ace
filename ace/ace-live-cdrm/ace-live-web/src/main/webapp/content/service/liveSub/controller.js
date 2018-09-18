jQuery(function($) {
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;'
			if (("title_html" in this.options)
					&& this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));
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

	$('#btn-view-add').on(
			'click',
			function() {
				jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						'new',
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : false,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
			});
	$('#btn-view-edit').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext)
				}
				jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						gr,
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : true,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
			});
    $('#btn-view-del').on(
			'click',
			function() {
				
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				jQuery(cfg.grid_selector).jqGrid(
						'delGridRow',
						gr,
						{
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
			});
});

function preview(id,title){
        var dialog = $( "#dialog-message-view" ).removeClass('hide').dialog({
			modal: false,
			width:800,
			title: "<div class='widget-header widget-header-small'><div class='widget-header-pd'>"+title+"</div></div>",
			title_html: true,
			buttons: [

				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					click: function() {
                       $( this ).dialog( "close" );
					}
				},
				{
					html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
					"class" : "btn btn-xs",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});
		$(dialog).parent().css("top","1px");
        $(dialog).css("max-height",window.innerHeight-layoutTopHeight+50);
		loadView(id);
}
function loadView(id) {
	$.ajax({
		type : "post",
		url : cfg.view_load_data_url,
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			$.each(rst.value, function(key, value) {
				if (key == 'category') {
                	value = rsd(value, '83');
                }
                if (key == 'status') {
                   value == "1" ? "正常" : "关闭";
                }if (key.indexOf('Date')!=-1||key.indexOf('time')!=-1||key.indexOf('Time')!=-1||key.indexOf('birthday')!=-1) {
                 	value = Common.DateFormatter(value);
                }
				$("#dialog-message-view").find('#' + key).html(value);
			});
		},
		error : function() {
			alert("加载错误！");
		}
	});
}

function audit(id,status,rid,message){
        $.ajax({
            type : "post",
            url : contextPath+"liveSub/updateLiveSubStatus.do",
            data : {
                id : id,
                status:status,
                rid:rid,
                message:message
            },
            success : function(rst, textStatus) {
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger("reloadGrid");
            },
            error : function() {
                alert("错误！");
            }
        });
}
function rowadd(data) {
    console.log(data);
	jQuery(cfg.grid_selector).jqGrid('addRowData', uuid(),data);
}
function add() {
	var data={
        id:"ieu44009999",
        rid:"c15f484b-bd30-4111-904d-123ca617180e",
        uid:"oFvIjw8x1--0lQkUhO1Ta3L59o3c",
        content:"大家好",
        status:"1"
	}
	data.status=rsd(data.status, "113");
	data.opt=renderOpt(data.id,data.rid,data.rid,data.content);
	rowadd(data);
}
function renderOpt(id,title,rid,message) {
	var html = [];
	html.push('<a data-rel="tooltip" data-placement="top" title="通过" class="blue" href="javascript:audit(\'' + id+ '\',2,\'' + rid+ '\',\'' + message+ '\')"><i class="ace-icon fa fa-unlock bigger-130"></i></a>');
    html.push('<a data-rel="tooltip" data-placement="top" title="退回" class="blue" href="javascript:audit(\'' + id+ '\',3)"><i class="ace-icon fa fa-lock bigger-130"></i></a>');
	html.push('<a target="_blank" href="');
	html.push('javascript:preview(\'' + id + '\',\'' + title + '\')');
	html.push('"');
	html.push('><span class="badge badge-info">查看</span></a>');
	return html.join(' ');
}
//self.setInterval("add()",5000)

var websocket1 = null;
//判断当前浏览器是否支持WebSocket
if('WebSocket' in window){
  websocket1 = new ReconnectingWebSocket("ws://"+websocketurl+"/live/websocketsub/rsub/sys");
}
else{
  console.log('Not support websocket1');
}

//连接发生错误的回调方法
websocket1.onerror = function(){
  console.log(" websocket1.onerror");
};

//连接成功建立的回调方法
websocket1.onopen = function(event){
console.log("onopen");
};

//接收到消息的回调方法
websocket1.onmessage = function(){
    var data =JSON.parse(event.data);
    data.status="1";
    data.status=rsd(data.status, "113");
  	data.opt=renderOpt(data.id,data.rid,data.rid,data.content);
  	rowadd(data);
  	var lastTr = $("#grid-table tr:last");
    lastTr.focus();
};

//连接关闭的回调方法
websocket1.onclose = function(){

};

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket1连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function(){
  websocket1.close();
};

//将消息显示在网页上
function setMessageInnerHTML(innerHTML){
  document.getElementById('message').innerHTML += innerHTML + '<br/>';
}

//关闭连接
function closeWebSocket(){
  websocket1.close();
}
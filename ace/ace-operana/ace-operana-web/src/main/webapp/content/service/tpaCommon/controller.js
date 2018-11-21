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


			$('#btn-view-add').on('click', function() {

            		jQuery(cfg.grid_selector).jqGrid('addRowData', uuid(), {
            			id : uuid(),
            			meetingId : meetingId,
            			topicId : topicId,
            			normId : normId
            		});
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
                    value = value=="1"?"on":"off";
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
function reload() {
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1
	}).trigger("reloadGrid");
		$(".tooltip").css("display","none");
}
function reload() {
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1
	}).trigger("reloadGrid");
		$(".tooltip").css("display","none");
}
function add() {
	jQuery(cfg.grid_selector).jqGrid('addRowData', uuid(), {
		id : uuid(),
		meetingId : meetingId,
		topicId : topicId,
		normId : normId
	});
}

function deleteAttach(fileName){
	$.ajax({
		type : "get",
		url : "/portal/attach/deleteAttachByFileName.do",
		data:{fileName:fileName},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
jQuery(cfg.grid_selector).jqGrid('setGridParam', {
				}).trigger("reloadGrid");
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {
		}
	});
}

function addAttach(id,obj){
    var html=document.getElementById('tpl-uploader').innerHTML;
    $("#upload"+id).html(html);
    $("#file"+id).remove();
    initUploader(id);
}



var uploader;
function initUploader(noticeId) {
	    uploader = new plupload.Uploader({
		runtimes : 'html5',
		browse_button : 'pickfiles',
		container: document.getElementById('container'),
		url : '/portal/attach/uploadFile.do?noticeId='+noticeId+'&collectionName=notice',
		filters : {
			max_file_size : '100mb',
			mime_types: [
				{title : "Image files", extensions : "jpg,gif,png"},
	            {title : "Office files", extensions : "xls,xlsx,doc,docx,ppt,pdf"},
	            {title : "Artive files", extensions : "zip,rar,gzip"}
			]
		},
		init: {
			PostInit: function() {
				document.getElementById('filelist').innerHTML = '';
				document.getElementById('uploadfiles').onclick = function() {
					uploader.start();
					return false;
				};
			},
			FilesAdded: function(up, files) {
				plupload.each(files, function(file) {
					document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
				});
			},
			UploadProgress: function(up, file) {
				document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
			},
			Error: function(up, err) {
				document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
			}
		}
	});
	uploader.init();
	uploader.bind("FileUploaded", function (uploader,file,responseObject) {
       jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger("reloadGrid");
    });
}


function exportExcel(){
    var type ='excel';
    var doExport = function () {
            if(!cfg.fileName){
                cfg.fileName="表格数据";
            }
            var dd = $('.ui-jqgrid-htable thead').clone();//找到<thead>
            var ee = $(cfg.grid_selector).clone();
            ee.find('.jqgfirstrow').remove();//干掉多余的无效行
            ee.find('tbody').before(dd);//合并表头和表数据
            ee.find('tr.ui-search-toolbar').remove();//干掉搜索框
            $(ee.find('th')).each(function(i,obj){
                if($(obj).css("display")=="none"){
                    $(obj).remove();
                }
            });
            $(ee.find('td')).each(function(i,obj){
                if($(obj).css("display")=="none"){
                    $(obj).remove();
                }
            });
            //console.log($(ee).html());
           console.log({type:type,fileName: cfg.fileName+'v'+getNowFormatDate()});
            //Regex reg = new Regex(@"(?i)<(/?(?:table|tr|td))\b[^>]*>");
            var domstr=$(ee).html();
            var html=domstr.replace(/<(?!(table|tbody|th|thead|tr|td)[ >])[^>/]*>/gi,"");
            html = html.replace(/[\r\n]/g, "");
            html=html.replace(/<\/?span[^>]*>/gi,'');
            html=html.replace(/<\/?div[^>]*>/gi,'');
            html=html.replace(/style=".*?"/gi,'');
            html=html.replace(/class=".*?"/gi,'');
            html=html.replace(/role=".*?"/gi,'');
            html=html.replace(/id=".*?"/gi,'');
            html=html.replace(/aria-describedby=".*?"/gi,'');
            html=html.replace(/title=".*?"/gi,'');
            html=html.replace(/tabindex=".*?"/gi,'');
            html=html.replace(/^\s+|\s+$/ig, '');
            html=html.replace(/&nbsp;/ig, '');
            html=html.replace(/ /ig, '');
            html="<table id='exportHandle'><caption>"+cfg.fileName+"</caption>"+html+"</table>";
            console.log(html);
            $("#tableExport").html(html);
            $("#tableExport").removeClass("hide");
            $("#exportHandle").tableExport({type:type,fileName: cfg.fileName+'v'+getNowFormatDate()});
            $("#tableExport").addClass("hide");
        };
   doExport();
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "";
    var seperator2 = "";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + "" + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}
jQuery(function($) {
	$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
				});
				$.extend(params, {
					time : new Date(),
					resourcesId : '',
				});
				//console.log(params);
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
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
				'selrow');
				if (gr) {
					var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
					$('#parentResourcesId').val(r.resourcesId);
					$('#resourcesLevel').val(parseInt(r.resourcesLevel)+1);
				}
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

	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));

	$('#tt').tree({
		onClick: function(node){
			autotreeq(node);
		}
	});
	$( "#btn-view-import" ).on('click', function(e) {
		e.preventDefault();
		reset_uploader();
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:750,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> 导入</h4></div>",
			title_html: true,
			buttons: [ 
				
				/*{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 重置",
					"class" : "btn btn-info btn-xs",
					id:'ajax_button',
					click: function() {
						reset_uploader();				
						
					} 
				},*/
				{
					html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 关闭",
					"class" : "btn btn-xs",
					click: function() {
						$( this ).dialog( "close" ); 
					} 
				}
			]
		});
		
	});

		$( "#btn-view-sort" ).on('click', function(e) {
    		e.preventDefault();
    		var dialog = $( "#dialog-sort" ).removeClass('hide').dialog({
    			modal: false,
    			width:600,
    			title: "<div class='widget-header widget-header-small'><h4 class='smaller'>排序</h4></div>",
    			title_html: true,
    			buttons: [

    				/*{
    					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 重置",
    					"class" : "btn btn-info btn-xs",
    					id:'ajax_button',
    					click: function() {
    						reset_uploader();

    					}
    				},*/
    				{
    					html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 关闭",
    					"class" : "btn btn-xs",
    					click: function() {
    						$( this ).dialog( "close" );
    					}
    				}
    			]
    		});
    		Travel("tt");
    		$( ".sortable" ).sortable({
                      cursor: "move",
                      items :"li",                        //只是li可以拖动
                      opacity: 0.6,                       //拖动时，透明度为0.6
                      revert: true,                       //释放时，增加动画
                      stop : function(event, ui){       //更新排序之后
                          updateSequence($(this).sortable("toArray"));
                      }
            });



    	});
});
function autotreeq(node){
	//$('#fm-search').find(":input[name='resourcesId']").val(node.id);
	//console.log(params);
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1,
		postData : {resourcesId:node.id,parentResourcesId:''}
	}).trigger("reloadGrid");
	
}
function treeAutoSelect(){
	var node = $('#tt').tree('getSelected');
	if(node){
		$(cfg.grid_selector).setSelection(node.id);
	}
	
}
function treeappend(){
	if(!authorConfig.hasOwnProperty(cfg.grid_add_data_url)){
		alert('受限的权限！');
		return;
	}
    var t = $('#tt');
    var node = t.tree('getSelected');
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
	$('#parentResourcesId').val(node.id);
}
function treeedit(){
	if(!authorConfig.hasOwnProperty(cfg.grid_edit_data_url)){
		alert('受限的权限！');
		return;
	}
	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
	'selrow');
	if(!gr){
		var node = $('#tt').tree('getSelected');
		$(cfg.grid_selector).setSelection(node.id);
	}
	gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
	'selrow');
	
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
function treeremove(){
	if(!authorConfig.hasOwnProperty(cfg.grid_delete_data_url)){
		alert('受限的权限！');
		return;
	}
	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
	'selrow');
	if(!gr){
		var node = $('#tt').tree('getSelected');
		$(cfg.grid_selector).setSelection(node.id);
	}
	gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
	'selrow');
	jQuery(cfg.grid_selector).jqGrid(
			'delGridRow',
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
/*
function append(){
    var t = $('#tt');
    var node = t.tree('getSelected');
    t.tree('append', {
        parent: (node?node.target:null),
        data: [{
            text: 'new item1'
        },{
            text: 'new item2'
        }]
    });
}
function removeit(){
    var node = $('#tt').tree('getSelected');
    $('#tt').tree('remove', node.target);
}*/
function collapse(){
    var node = $('#tt').tree('getSelected');
    $('#tt').tree('collapse',node.target);
}
function expand(){
    var node = $('#tt').tree('getSelected');
    $('#tt').tree('expand',node.target);
}
function treereload(){
	$('#tt').tree('reload');
}

function clearQparams(){
	$('#cc1').combotree('setValue', '');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1,
		postData : {parentResourcesId:'',resourcesId:''}
	}).trigger("reloadGrid");
}
function Travel(treeID){//参数为树的ID，注意不要添加#

   var node = $('#'+treeID).tree('getSelected');
   var html=[];
   if(node){
        var children = $('#'+treeID).tree('getChildren', node.target);
        for (var i = 0; i < children.length; i++) {
            //console.log(children[i].cls=='folder');
            if(children[i].cls=='folder'){
                html.push('<li class="dd-handle"  id="'+children[i].id+'">'+children[i].text+'</li>');
            }

        }
   }else{
        var roots=$('#'+treeID).tree('getRoots');
        for(i=0;i<roots.length;i++){
            html.push('<li class="dd-handle"  id="'+roots[i].id+'">'+roots[i].text+'</li>');
        }
   }
    $(".sortable").html(html.join(""));
}
function updateSequence(arr){
    var data=[];
    for(var i=0;i<arr.length;i++){
        data.push({resourcesId:arr[i],sequence:i});
    }
    $.ajax({
        type : "post",
        url : contextPath +"/resources/updateSequence.do",
        data:{jsons:JSON.stringify(data)},
        beforeSend : function(XMLHttpRequest) {
        },
        success : function(rst, textStatus) {
            if (!rst.state) {
                bootbox.dialog({
                    title:'系统提示',
                    message:rst.errorMessage,
                    detail:rst.detail,
                    buttons:
                    {
                        "success" :
                         {
                            "label" : "<i class='ace-icon fa fa-check'></i>确定",
                            "className" : "btn-sm btn-success",
                            "callback": function() {
                            }
                        }
                    }
                });

            }
        },
        complete : function(XMLHttpRequest, textStatus) {

        },
        error : function() {

        }
    });
}
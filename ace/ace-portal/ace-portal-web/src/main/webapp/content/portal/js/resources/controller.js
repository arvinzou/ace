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



	$('#tt').tree({
		onClick: function(node){
			autotreeq(node);
		}
	});
	$( "#btn-view-import" ).on('click', function(e) {
		e.preventDefault();
		reset_uploader();
		$('#modal-upload').modal('show');
		
	});

		$( "#btn-view-sort" ).on('click', function(e) {
    		e.preventDefault();
    		$('#modal-sort').modal('show');
    		Travel("tt");


              var el = document.getElementById('sortable');

                        var sortable = Sortable.create(el, {
                            group: "words",
                            animation: 150,
                            onAdd: function (evt) {
                                console.log('onAdd.bar:', evt.item);
                            },
                            onUpdate: function (evt) {
                                console.log('onUpdate.bar:', evt.item);
                            },
                            onRemove: function (evt) {
                                console.log('onRemove.bar:', evt.item);
                            },
                            onStart: function (evt) {
                                console.log('onStart.foo:', evt.item);
                            },
                            onEnd: function (evt) {
                                console.log('onEnd.foo:', evt.item);
                                console.log(sortable.toArray());
                                updateSequence(sortable.toArray());
                            }
                        });



    	});
});
function autotreeq(node){
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

				}
			})
}
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
            if(children[i].cls=='folder'||children[i].text.length>2){
                html.push('<li draggable="false"  data-id="'+children[i].id+'" data-name="'+children[i].text+'">'+children[i].text+'</li>');
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
                alert(rst.errorMessage);
            }
        },
        complete : function(XMLHttpRequest, textStatus) {

        },
        error : function() {

        }
    });
}


function edit(rowid){
    console.log(rowid);
	jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						rowid,
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : true,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')

							}
						});
}
var show=false;
function del(rowid){
    console.log(rowid);
	jQuery(cfg.grid_selector).jqGrid(
    						'delGridRow',
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

function setParams(key, value) {
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam',{postData : params}).trigger("reloadGrid");
}

jQuery(function($) {
	jQuery('.layout-button-left').on( 'click', function(e) {
            setTimeout("resizeJqGrid()",500);
	});
});

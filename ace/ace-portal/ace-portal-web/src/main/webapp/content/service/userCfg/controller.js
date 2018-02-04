jQuery(function($) {

$("#pg").propertygrid({
 						width: 'auto',
 				        height: 'auto',
 				        columns:[[
 			             	{ field:'name', title:'名称', width:100, sortable:true },
 			             	{ field:'cfgKey', title:'cfgKey', width:100, sortable:true },
 			         		{
 			         			field:'value',
 			         			title:'值',
 			         			width:200,
 			         			formatter: function(value,rowData,rowIndex){
 			         			    switch(rowData.cfgKey){
 			         			        case 'portalType':
                                            switch(value)
                                            {
                                                case '1':
                                                    return '菜单在左侧';
                                                case '2':
                                                    return '菜单在顶部';
                                                default:
                                                    return value;
                                            }
                                        default:
                                            return value;
 			         			    }
 			         			}
 			         		}
 			            ]],
 				        showGroup: true,
 				        scrollbarSize: 0,
 				        url:contextPath+'/userCfg/selectUserCfgByUserId.do'
 					});

 						$('#btn-view-save').on(
                    			'click',
                    			function() {
                    				updateCfg()
                    			});
 });

 function updateCfg(){
         var rows = $('#pg').propertygrid('getChanges');
         var params=[];
         $(rows).each(function(i,o){
             params.push({cfgKey:o.cfgKey,cfgValue:o.value,cfgName:o.name});
         });
        $.ajax({
            type : "post",
            url : contextPath+'/userCfg/saveOrUpdateUserCfg.do',
            data:{jsons:JSON.stringify(params)},
            beforeSend : function(XMLHttpRequest) {
                $("#btn-view-save").attr('disabled',"true");
            },
            success : function(rst, textStatus) {
                $("#btn-view-save").removeAttr("disabled");
                if (rst) {
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
            error : function() {
                $("#btn-view-save").removeAttr("disabled");
            }
        });
  }
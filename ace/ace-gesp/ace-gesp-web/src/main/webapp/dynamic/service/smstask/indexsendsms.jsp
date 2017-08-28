<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <jsp:include page="../../shared/index_head.jsp" /> 
 </head>
<body> 
<div class="subpage-content">
	 <div class="row">
	            <div class="col-md-12 query-panel">
	            <div class="panel panel-default section">
                                            <div class="panel-body">
												<form role="form" id="queryForm"  >
	                                                <label class="  control-label">发送状态:&nbsp;</label>
	                                                <select id="sendStatus" name="sendStatus"   class="form-control input-medium " style="display:inline" placeholder="">
	                                                  <option value="" >全部</option>
	                                                   <option value="0" >未发送</option>
	                                                    <option value="1" >发送成功</option>
	                                                     <option value="2" >发送中</option>
	                                                     <option value="3" >发送失败</option>
	                                                </select>
	                                              
	                                                <label class=" control-label  ">&nbsp;&nbsp;&nbsp;&nbsp;</label>
	                                                <a href="javascript:btn_Search();" class=" btn yellow querybtn"> <i class="icon-magnifier-add m-icon-white"></i>&nbsp;&nbsp;查 询&nbsp;&nbsp; </a>  
                                				</form>
 											</div>
                                        </div>
	            </div>
	</div>
	 <div class="row">
	            <div class="col-md-12 ">
	            <div class="portlet light section">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-list"></i>
                                        <span class="caption-subject bold">数据列表</span>
                                    </div>
                                      <!-- <div class="tools">
                                        <a href="" class="fullscreen" data-original-title="" title=""> </a>
                                    </div> -->
                                </div>
                                <div class="portlet-body">
                                <div class="">
		                                <div id="toolbar">
		                                <!-- <button type="button" class="btn blue-hoki" onclick="Replace();"> <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>&nbsp;&nbsp;刷新&nbsp;&nbsp;</button> 
		                                <button type="button" class="btn blue" onclick="btn_add();"> <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;&nbsp;短信发送&nbsp;&nbsp;</button> 
		                                <button type="button" class="btn green" onclick="btn_edit();"> <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;&nbsp;重新发送&nbsp;&nbsp;</button> 
		                                <button type="button" class="btn red" onclick="btn_delete();"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;删除&nbsp;&nbsp;</button> 
		                                <button type="button" class="btn grey" onclick="btn_back();"> <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>&nbsp;&nbsp;离开&nbsp;&nbsp;</button>
		                                  --></div>
									   <table   id="gridTable" 
									       data-toggle="table" 
									       data-click-to-select="true"
									       data-pagination="true"  
									       data-show-refresh="true"  
									       data-show-toggle="true"  
									       data-showColumns="true"
									       data-show-export="true"
									      
               							   data-cookie-id-table="globalParam_Index" >
									       <!--   data-cookie="true" -->
               							     
									       <thead>  
									       </thead>  
									       <tbody>  
									       </tbody>  
									</table> 
									</div>
                                </div>
                            </div>
		            
	            </div>
	 </div>

</div>
<jsp:include page="../../shared/index_foot.jsp" />                             
 <script type="text/javascript">  
    //
    pageConfig.modulePath="/smsTask";
    dialogConfig.title="短信管理";//对话框配置
    
   /*
  	 文档加载完成
   */
   $(document).ready(function () {
   	  	getGrid();
	   	$(window).resize(function () {
	   		UI.BootstrapTable.resetLayout("gridTable",gridConfig.heightSub);
	   });
    });
   
    /*
       显示表格
    */
    function getGrid() {
    	UI.BootstrapTable.get("gridTable",{
    		url:pageConfig.modulePath+"/findListForSendSMS.do?id="+param.id,
    		height:$(window).height()-gridConfig.heightSub,
    		columns:[
                       {checkbox:true,field:"state",title:"" }
                       ,{field:"id",title:"主键",visible:false}
                       ,{ title: "手机号码", field: "mobile",sortable:false }
                       
                        
                       ,{ title: "发送状态", field: "sendStatus",formatter:function(value,row,index){
                      	  
                          if (value == '0') return '未发送';
                          if (value == '1') return '发送成功';
                          if (value == '2') return '发送中';
                          if (value == '3') return '发送失败';
                      	 } 
                       
                       }
                       ,{ title: "备注", field: "smsRemark",sortable:false  }
                        
                       /* ,{ title: "有效", field: "isEnable",align:"center",formatter:function(value,row,index){
                     	  if (value == '1') return '<i class="font-green-seagreen font-lg icon-check "></i>';
                           if (value == '0') return '<i class="font-red-soft font-lg icon-close "></i>';
                       	 } 
                        } */
    	              ]}
    	);
    }
     
    
    
</script>                               
</body>
 </html>
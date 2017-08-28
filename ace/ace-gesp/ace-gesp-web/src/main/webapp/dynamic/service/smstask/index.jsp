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
	                                                <label class="  control-label">任务名称:&nbsp;</label>
	                                                <input id="name" name="name" type="text" class="form-control input-medium " style="display:inline" placeholder="">
	                                              
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
		                                <button type="button" class="btn blue-hoki" onclick="Replace();"> <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>&nbsp;&nbsp;刷新&nbsp;&nbsp;</button> 
		                                <button type="button" class="btn blue" onclick="btn_add();"> <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;&nbsp;短信发送&nbsp;&nbsp;</button> 
		                                <button type="button" class="btn green" onclick="btn_resend();"> <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;&nbsp;重新发送&nbsp;&nbsp;</button> 
		                                <button type="button" class="btn red" onclick="btn_delete();"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;删除&nbsp;&nbsp;</button> 
		                                <button type="button" class="btn grey" onclick="btn_back();"> <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>&nbsp;&nbsp;离开&nbsp;&nbsp;</button>
		                                 </div>
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
    		url:pageConfig.modulePath+"/findList.do",
    		height:$(window).height()-gridConfig.heightSub,
    		columns:[
                       {checkbox:true,field:"state",title:"" }
                       ,{field:"id",title:"主键",visible:false}
                       ,{ title: "任务名称", field: "name",sortable:false }
                       ,{ title: "发送内容", field: "content",sortable:false  }
                       ,{ title: "发送时间", field: "sendTime",formatter:function(value,row,index){
                      	        if(value)
                      	         {
                      	        	return DateUtils.format(new Date(value),"yyyy-MM-dd hh:mm:ss");
                      	         }
                      	        return '-';
                      	 }}
                       ,{ title: "未发送个数", field: "unSendCount"}
                       ,{ title: "发送中个数", field: "sendingCount"}
                       ,{ title: "发送成功", field: "successCount"}
                       ,{ title: "发送失败", field: "failCount"}
                       ,{ title: "操作", field: "",formatter:function(value,row,index){
                      	   return '<button type="button" class="btn green" onclick="btn_view(\''+row.id+'\');">  &nbsp;&nbsp;查看状态&nbsp;&nbsp;</button>';
                      	 }}
                       /* ,{ title: "有效", field: "isEnable",align:"center",formatter:function(value,row,index){
                     	  if (value == '1') return '<i class="font-green-seagreen font-lg icon-check "></i>';
                           if (value == '0') return '<i class="font-red-soft font-lg icon-close "></i>';
                       	 } 
                        } */
    	              ]}
    	);
    }
     
    
    /*
  	 新增
   */
   function btn_add() {
           var url = pageConfig.jspPath+"/form.jsp";
           UI.Dialog.open(url, StringUtils.hashCode(url), "发送短信", dialogConfig.width,dialogConfig.height, function (iframe) {
               iframe.AcceptClick();
           });
   }
    
   /*
 	 再次发送
  */
  function btn_resend() {
  	var KeyValue = UI.BootstrapTable.getRowValue("#gridTable", gridConfig.pk);
      if (UI.Dialog.checkEdit(KeyValue)) {
    	  //;
    	  var url = pageConfig.modulePath+"/update.do?id=" + KeyValue;
    	  UI.Dialog.confirm("系统提示", "您确定要重新发送任务中的全部短信?发送后将无法撤回!", function (r) {
		        if (r) {
		        	var dlg_index=UI.Dialog.loading(true, "正在重新发送...");
		        	UI.Ajax.post(url,{},function(data){
		        		//;
		        		self.layer.close(dlg_index);
		        		if(self.tableRefresh)
		        		{
		        			self.tableRefresh();
		        		}
		        		var _message="重新发送成功";
		        		var _icon=5;
		        		if (data.status == 0) {
		        			_icon=6;
		                } 
		        		self.layer.msg(_message, {
		                 	  icon: _icon,
		                 	  time: 3000 // 
		                 	}, function(){
		                  });
		        	},{async:false});
		        }
		    });
    	  
    	   
      }
  }
    
    function btn_view(taskId)
    {
    	var url = pageConfig.jspPath+"/indexsendsms.jsp?id="+taskId;
        UI.Dialog.open(url, StringUtils.hashCode(url), "查看发送情况", 800,510, function (iframe) {
            //iframe.AcceptClick();
        },{isFull:false,showButton:false});
    }
</script>                               
</body>
 </html>
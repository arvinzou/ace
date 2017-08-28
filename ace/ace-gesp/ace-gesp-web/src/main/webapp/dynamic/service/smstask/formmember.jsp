<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <jsp:include page="../../shared/form_head.jsp" /> 
 </head>
<body  >

<div class="subpage-content">
  <div id="message"></div>
	 <form id="form1" action="#" class="form-horizontal form-bordered form-row-stripped">
         <div class="form-body">
             <div class="form-group">
                 <label class="control-label col-md-2">会员类型： </label>
                 <div class="col-md-2">
                     <select id="isMember" class="form-control">
                     	<option value="">全部</option>
                     	<option value="0">非会员</option>
                     	<option value="1">会员</option>
                     </select>
                 </div>
             </div>
             
             <div class="form-group">
                 <label class="control-label col-md-2">车辆数： </label>
                 <div class="col-md-2">
                     <select id="driver_num" class="form-control">
                     	 <option value="">全部</option>
                     	 <option value="less10">10台以下(不包含10台)</option>
                     	 <option value="more10">10台以上(包含10台)</option>
                     </select>
                 </div>
             </div>
             
             <div class="form-group">
                 <label class="control-label col-md-2">地区： </label>
                 <div class="col-md-2">
                     <select id="area_code" class="form-control">
                     	 <option value="4403">全深圳</option>
                     	  <option value="440301">市辖区</option>
                     	  <option value="440303">罗湖区</option>
                     	  <option value="440304">福田区</option>
                     	  <option value="440305">南山区</option>
                     	  <option value="440306">宝安区</option>
                     	  <option value="440307">龙岗区</option>
                     	  <option value="440308">盐田区</option>
                     	  <option value="440309">龙华区</option>
                     	  <option value="440310">坪山新区</option>
                     	  <option value="440311">光明新区</option>
                     	  <option value="440312">大鹏新区</option>
                     	   
                     </select>
                 </div>
             </div>
              
              
         </div>
          
     </form>			      
 </div>
  <jsp:include page="../../shared/form_foot.jsp" /> 
 <script type="text/javascript">
    //已经初始化
    pageConfig.modulePath="/smsTask";
    //KeyValue
      dialogConfig={width:800,height:500,title:""};//对话框配置
   
    /*文档加载完成*/
    $(function () {
        initControl();
    });
    
    /*设置页面表单内容*/
    function initControl() {
    	 
    }
    /*保存事件*/
    function AcceptClick() {
    	 
    	//UI.Form.submit("#form1",pageConfig.modulePath+"/findListByMember.do?KeyValue=" + KeyValue,post);
    	var message=StringUtils.format("【会员类型：{0}】；【车辆数：{1}】；【地区：{2}】"
		    				,$("#isMember :checked").text()
		    				,$("#driver_num :checked").text()
		    				,$("#area_code :checked").text());
    	var post= UI.Form.get("form1");
    	UI.Ajax.post(pageConfig.modulePath+"/findListByMember.do",post,function(rst){
    		if(rst&&rst.value)
    		 { 
    			   var tels=[];
		    		for(i in rst.value)
					 { 
				 		 var one=rst.value[i];
				 		 tels.push($.trim(one.mobile));
					 }
		    		 
		    		window.parent.findListByMember(tels,message);
		    		
    		 }
    		  
    	},{async:false});
    	 UI.Dialog.closeMyself();
    }
    
    
</script>                               
</body>
 </html>
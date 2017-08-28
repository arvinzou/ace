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
                 <label class="control-label col-md-2">任务名称：<span class="required" aria-required="true"> * </span></label>
                 <div class="col-md-8">
                     <input type="text" id="name" placeholder="" err="任务名称" class="form-control" checkexpession="NotNull"/>
                 </div>
             </div>
             <div class="form-group">
                 <label class="control-label col-md-2">发送给(企业)： </label>
                 <div class="col-md-8">
                 <button type="button" class="btn green" onclick="btn_selectMember();"> <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;&nbsp;企业筛选&nbsp;&nbsp;</button>&nbsp;&nbsp;
                 <button type="button" class="btn red" onclick="btn_clearselectMember();"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;清除&nbsp;&nbsp;</button>
                     <label  class="form-control" id="lbl_selectMember">未筛选企业.........	</label>
                     <!-- <input type="text" id="paramValue" placeholder="" err="值" class="form-control" checkexpession="NotNull"/> -->
                 </div>
             </div>
             
             <div class="form-group">
                 <label class="control-label col-md-2">发送给(电话)：</label>
                 <div class="col-md-8">
                   <textarea id="tels" name="tels"  placeholder="" err="" class="form-control" rows="6" onchange="tels_change()"></textarea>
                    <label id="tels_message" style="color:#C0E012" >需要用换行来区分多个电话号码</label>
                 </div>
             </div>
              <div class="form-group">
                 <label class="control-label col-md-2">模板选择：<span class="required" aria-required="true"> * </span></label>
                 <div class="col-md-8">
                  	<select id="template" class="form-control" err="" checkexpession="NotNull" > </select>
                  	
                 </div>
             </div>
              
              <div class="form-group">
                 <label class="control-label col-md-2">模板内容：</label>
                 <div class="col-md-8">
                   <label style="color:#0089FF"  id="templateContent" class="form-control" ></label>
                                 <i>发送的内容需要替换<font color="red">红色</font>内容,<font color="#0089FF">蓝色</font>文字为模板格式,不能删除或更改</i>
                 </div>
              </div>
              
              <div class="form-group">
                 <label class="control-label col-md-2">发送的内容：<span class="required" aria-required="true"> * </span></label>
                 <div class="col-md-8">
                   <textarea id="content" name="content" placeholder="" err="发送的内容" class="form-control" rows="6" checkexpession="NotNull"></textarea>
                 </div>
              </div>
              
              
             <div class="form-group">
                 <label class="control-label col-md-2">备注：</label>
                 <div class="col-md-8">
                   <textarea id="remark" name="remark" maxlength="200" placeholder="" err="" class="form-control" rows="2"></textarea>
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
    var vailMobileList=[];
    var vailMobileList_member=[];
    /*文档加载完成*/
    $(function () {
        initControl();
    });
    
    /*设置页面表单内容*/
    function initControl() {
    	UI.Ajax.post("/portal/sms/getAllTemplate.do",{},function(rst){
    		if(rst&&rst.value)
    		 { 
		    		for(i in rst.value)
					 { 
				 		$("#template").append("<option content='"+HtmlUtils.encode(rst.value[i].content)+"' templateCode='"+rst.value[i].templateCode+"' value='"+rst.value[i].templateCode+"'>"+rst.value[i].name+"</option>");
					 }
    		 }
    		 var templateContent=$("#template :checked").attr("content");
    		 //if(!$("#content").val())
    	     var tc2=changeTemplateContent(templateContent);
		     $("#content").val(tc2);
		     $("#templateContent").html(tc2);
    	},{async:false});
    	
    	$("#template").on("change",function(){
    		  var templateContent=$("#template :checked").attr("content");
			  //$("#content,#templateContent").val(templateContent);
			  var tc2=changeTemplateContent(templateContent);
    		  $("#templateContent").html(tc2);
    		  $("#content").val(tc2);
		});
    }
    /*保存事件*/
    function AcceptClick() {
        if (!CheckDataValid('#form1')) {
            return false;
        }
        var errMessage= checkContent();
        if(errMessage)
        {
        	tipCss("#content",errMessage);
        	return false;
        }
        if (!checkTemplate()) {
        	tipCss("#content","发送的内容和模板的格式不匹配!");
            return false;
        }
        var  mlist=vailMobileList.concat(vailMobileList_member);
        if(mlist.length==0)
        {
        	tipCss("#tels","至少一个手机有效的手机号码!");
            return false;
        }
   	    var post={tels:MathUtils.distinct(mlist).join(",")};
         UI.Dialog.confirm ("系统提示", "您确定要发送短信?发送后将无法撤回!", function(){
        	 
             UI.Form.submit("#form1",pageConfig.modulePath+"/insert.do?KeyValue=" + KeyValue,post);
        }) ;
       
    }
    
    function checkContent()
    {
    	var content=$.trim($("#content").val());
    	if(!content)
    		return "发送内容不能为空!";
    	else
    	 {
    		 if(content.indexOf("[")>-1||
    		    content.indexOf("]")>-1||
    		    content.indexOf("$")>-1||
    		    content.indexOf("【")>-1||
    		    content.indexOf("】")>-1||
    		    content.indexOf("{")>-1||
    		    content.indexOf("}")>-1)
    			 {
    			    return "发送内容不能存在特殊字符[]【】{}$";
    			 }
    	 }
    }
    
    function  changeTemplateContent(templateContent)
    {
    	var matchArray= templateContent.match(/\$\{.+?\}/gm);
    	for(var i=0;i<matchArray.length;i++)
    	{
    		var rcode=matchArray[i];
    		templateContent=templateContent.replace(rcode,"<font color='red'>请输入内容</font>");
    	}
    	return templateContent;
    }
    
    function checkTemplate()
    {
    	//检测文档
    	var templateContent=$("#template :checked").attr("content");
    	var content=$.trim($("#content").val());
    	 
    	var count= templateContent.split("$").length;
    	if(!content)
    		return false;
    	
    	
    	for(var i=0;i<count;i++)
    	{
    		templateContent=templateContent.replace(/\$\{.+?\}/,"(.+)"); 
    	}
    	var patt1 = new RegExp(templateContent);
    	var isOk= patt1.test(content);
    	
    	return isOk;
    	//return false;
    }
    
     
    function tels_change()
    {
    	vailMobileList=[];
    	var tels= $("#tels").val();
    	var lines= tels.split("\n");
    	var okCount=0;
    	var errorCount=0;
    	for(var i=0;i<lines.length;i++)
    	{
    		var tel=$.trim(lines[i]);
    		if(!tel)
    			continue;
    		if(isMobile(tel))
    			{
    				vailMobileList.push(tel);
    				okCount++;
    			}else
    				{
    				errorCount++;
    				}
    	}
    	$("#tels_message").html(StringUtils.format("正确手机号码个数{0},错误手机号码个数{1}",okCount,errorCount));
    }
    
    
    
    function findListByMember(tels,mess)
    {
    	$("#lbl_selectMember").html(mess+"筛选电话 "+tels.length+" 个");
    	if(tels&&tels.length>0)
    		vailMobileList_member=tels;
       
    }
    
    function btn_selectMember()
    {
    	var url = pageConfig.jspPath+"/formmember.jsp";
        UI.Dialog.open(url, StringUtils.hashCode(url), "筛选企业",400,400, function (iframe) {
            iframe.AcceptClick();
        },{isFull:false});
    }
    
    function btn_clearselectMember()
    {
    	vailMobileList_member=[];
    	$("#lbl_selectMember").html("未筛选企业.........");
    }
</script>                               
</body>
 </html>
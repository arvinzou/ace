
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
 var activeSyId ='${SESSION_USERPROP_KEY.activeSyId}';
 if(activeSyId=='sys'||activeSyId=='page'){
    location.href="/portal/dynamic/portal/main.jsp";
 }else{
    location.href="/${SESSION_USERPROP_KEY.activeSyId}/index.jsp";
 }
</script>